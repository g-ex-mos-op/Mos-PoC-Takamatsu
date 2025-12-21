package jp.co.isid.mos.bird.storemanage.anshinreport.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstMiseDao;
import jp.co.isid.mos.bird.common.dao.MstOnerDao;
import jp.co.isid.mos.bird.common.dao.UIMiseListDao;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.entity.MstOner;
import jp.co.isid.mos.bird.common.entity.UIMiseList;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.storemanage.anshinreport.code.TaishoJoken;
import jp.co.isid.mos.bird.storemanage.anshinreport.dao.MstOnerHonbuDao;
import jp.co.isid.mos.bird.storemanage.anshinreport.dao.UIMiseListHonbuDao;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.entity.FileInfo;
import jp.co.isid.mos.bird.storemanage.anshinreport.entity.ResultInfo;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.AnshinReportFileDataLogic;



public class AnshinReportFileDataLogicImpl implements AnshinReportFileDataLogic{

    private MstOnerDao mstOnerDao;
    private UIMiseListDao uiMiseListDao;
    private MstMiseDao mstMiseDao;
    private MstOnerHonbuDao mstOnerHonbuDao;
    private UIMiseListHonbuDao uiMiseListHonbuDao;
    
    /*LogicID*/
    public static final String LOGIC_ID =    "BSM018L03";
    
    // ファイルセパレーター
    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    
    /**
     * @param args
     */
    public void execute(AnshinReportDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo){
         //Dtoへシステム日付設定
        dto.setSysDate(dto.getBirdDateInfo().getSysDate());
        
        //店リスト取得
        List mstUserMiseList = getMiseList(dto, userInfo, dateInfo);
        
        //ファイル名称取得
        if(mstUserMiseList != null){
            getFileList(dto,mstUserMiseList);
        }else {
            throw new NoResultException("");
        }

    }
    
    /**
     * 店名称取得
     * @param anshinReportDto
     * @return 
     */
    private List getMiseList(AnshinReportDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo){
        List miseList = new ArrayList();
        
        if (UserType.isHonbu(dto.getUsertypeCd())) {
            // 本部ユーザの場合
            if (TaishoJoken.CODE_ONER.equals(dto.getTaishoJoken())) {
                // 対象条件がオーナーの場合
                MstOner mstOner = getMstOnerHonbuDao().select(dto.getUserId(),dto.getUsertypeCd(),
                        userInfo.isLimit(),dto.getCompanyCd(),dto.getOnerCd());

                if (mstOner == null || mstOner.getOnerCd() == null) {
                    dto.clearList();
                    throw new NotExistException("オーナーコード");
                }
                // オーナー情報設定   
                dto.setOnerCd(mstOner.getOnerCd());
                dto.setOnerName(mstOner.getOnerNameKj());
                

                // 指定オーナー対象店舗情報(クローズ店含めクローズ日ソートなし)を実行
                miseList = getUiMiseListHonbuDao().selectByOnerCd(dto.getUserId(),dto.getUsertypeCd(),
                        userInfo.isLimit(),dto.getCompanyCd(),dto.getOnerCd(),dateInfo.getAppDate());
                
                
            } else {
                // 対象条件が店舗の場合
                UIMiseList uiMise = getUiMiseListHonbuDao().select(dto.getUserId(),dto.getUsertypeCd(),
                        userInfo.isLimit(),dto.getCompanyCd(),dto.getMiseCd(),dateInfo.getAppDate());

                if (uiMise == null || uiMise.getMiseCd() == null) {
                    dto.clearList();
                    throw new NotExistException("店コード");
                }
                
                miseList.add(uiMise);
            }
        } else if (UserType.isOner(dto.getUsertypeCd())) {
            // オーナーユーザの場合
            if (TaishoJoken.CODE_ONER.equals(dto.getTaishoJoken())) {
                // 対象条件がオーナーの場合
                List onerList = (List)userInfo.getUserOner();
                UIUserOner userOner;
                String onerCd = null;
                // 選択した会社に対応したオーナコードを取得する
                for (int i = 0; i < onerList.size();i++) {
                    userOner = (UIUserOner)onerList.get(i);
                    if (dto.getCompanyCd().equals(userOner.getCompanyCd())) {
                        onerCd = userOner.getOnerCd();
                        dto.setOnerCd(onerCd);
                        break;
                    }
                }
                
                MstOner mstOner = getMstOnerDao().selectOner(dto.getCompanyCd(),onerCd);
                
                if (mstOner == null || mstOner.getOnerCd() == null) {
                    dto.clearList();
                    throw new NotExistException("オーナーコード");
                }
                // オーナー情報設定   
                dto.setOnerCd(mstOner.getOnerCd());
                dto.setOnerName(mstOner.getOnerNameKj());
                
                // 指定オーナー対象店舗情報(クローズ店含めクローズ日ソートなし)を実行
                miseList = getUiMiseListDao().getCloseInSortMise(dto.getCompanyCd(),dto.getOnerCd(),dateInfo.getAppDate());
                
            } else {
                // 対象条件が店舗の場合
                for (int i = 0; i < dto.getMiseList().size(); i++) {
                    // プルダウンで選択した情報を設定
                    CodHyojiTaisho hyojiTaisho = (CodHyojiTaisho)dto.getMiseList().get(i);
                    if (dto.getMiseCd().equals(hyojiTaisho.getHyojitaishoCd())) {
                        UIMiseList uiMise = new UIMiseList();
                        uiMise.setMiseCd(hyojiTaisho.getHyojitaishoCd());
                        uiMise.setMiseNameKj(hyojiTaisho.getHyojitaishoName());
                        miseList.add(uiMise);
                        break;
                    };
                }
            }
        } else if(UserType.isTenpo(dto.getUsertypeCd())) {
            // 店舗ユーザの場合

            MstMise mstMise = mstMiseDao.selectMise(dto.getCompanyCd(),dto.getMiseCd());

            if (mstMise == null || mstMise.getMiseCd() == null) {
                dto.clearList();
                throw new NotExistException("店コード");
            }
            
            // オブジェクト切替
            UIMiseList uiMise = new UIMiseList();
            uiMise.setMiseCd(mstMise.getMiseCd());
            uiMise.setMiseNameKj(mstMise.getMiseNameKj());
            miseList.add(uiMise);
        }
        
        return miseList;

    }
    
    /**
     * ファイルリスト取得
     */
    private void getFileList(AnshinReportDto dto,List miseList) {
        String filePth = BirdContext.getProperty("fileProperty", "filePathAnshin") + FILE_SEPARATOR + dto.getJissiNendo();
        File dir = new File(filePth);
        File files[] = dir.listFiles();
        List resultList = new ArrayList();
        
        if((files != null && files.length > 0) && (miseList != null && miseList.size() > 0)){
            UIMiseList uiMise;
            ResultInfo resultInfo;
            FileInfo fileInfo;
            List fileList;
            
            for(int j=0;j<miseList.size();j++){
                uiMise = (UIMiseList)miseList.get(j);
                fileList = new ArrayList();
                for(int i=0; i<files.length; i++){
                fileInfo = new FileInfo();
                String strFileName = files[i].getName();
                String dispFileName;
                if (strFileName.length() > 13) {

                        // 年度、店舗名が一致したファイルをリストに追加
                        if(strFileName.substring(0,4).equals(dto.getJissiNendo()) &&
                                strFileName.substring(4,6).equals(dto.getCompanyCd()) &&
                                strFileName.substring(6,11).equals(uiMise.getMiseCd())){
                            dispFileName = strFileName.substring(13);
                            fileInfo.setFullFileName(strFileName);
                            fileInfo.setFileName(dispFileName);
                            fileInfo.setSortNo(strFileName.substring(11,13));
                            fileList.add(fileInfo);
                        }
                    }
                }
                // 一致するファイル情報があれば結果リストに追加
                if (fileList != null && fileList.size() > 0) {

                    // ソート処理
                    Comparator sortComparator = new Comparator() {
                        public boolean equals(Object obj) {
                            return (super.equals(obj));
                        }
                        public int compare(Object obj1, Object obj2) {
                            String key1 = ((FileInfo) obj1).getSortNo();
                            String key2 = ((FileInfo) obj2).getSortNo();
                            
                            return key1.compareTo(key2);
                        }
                    };
                    Collections.sort(fileList, sortComparator);
                    
                    // 結果設定
                    resultInfo = new ResultInfo();
                    resultInfo.setMiseCd(uiMise.getMiseCd());
                    resultInfo.setMiseName(uiMise.getMiseNameKj());
                    resultInfo.setJissiNendo(dto.getJissiNendo());
                    resultInfo.setFileList(fileList);
                    resultList.add(resultInfo);
                }
            }
            if(resultList.size() <= 0){
                dto.clearList();
                throw new NoResultException("");
            }

        }else {
            dto.clearList();
            throw new NoResultException("");
        }
       dto.setReExecuteFlg("1");
       dto.setResultList(resultList);

    }
    /**
     * 
     * @return mstOnerDao を戻します。
     */
    public MstOnerDao getMstOnerDao() {
        return mstOnerDao;
    }
    /**
     * 
     * @param mstOnerDao を設定します。
     */
    public void setMstOnerDao(MstOnerDao mstOnerDao) {
        this.mstOnerDao = mstOnerDao;
    }
    /**
     * 
     * @return uiMiseListDao を戻します。
     */
    public UIMiseListDao getUiMiseListDao() {
        return uiMiseListDao;
    }
    /**
     * 
     * @param uiMiseListDao を設定します。
     */
    public void setUiMiseListDao(UIMiseListDao uiMiseListDao) {
        this.uiMiseListDao = uiMiseListDao;
    }
    /**
     * 
     * @return mstMiseDao を戻します。
     */
    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }
    /**
     * 
     * @param mstMiseDao を設定します。
     */
    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
    /**
     * 
     * @return mstOnerHonbuDao を戻します。
     */
    public MstOnerHonbuDao getMstOnerHonbuDao() {
        return mstOnerHonbuDao;
    }
    /**
     * 
     * @param mstOnerHonbuDao を設定します。
     */
    public void setMstOnerHonbuDao(MstOnerHonbuDao mstOnerHonbuDao) {
        this.mstOnerHonbuDao = mstOnerHonbuDao;
    }
    /**
     * 
     * @return uiMiseListHonbuDao を戻します。
     */
    public UIMiseListHonbuDao getUiMiseListHonbuDao() {
        return uiMiseListHonbuDao;
    }
    /**
     * 
     * @param uiMiseListHonbuDao を設定します。
     */
    public void setUiMiseListHonbuDao(UIMiseListHonbuDao uiMiseListHonbuDao) {
        this.uiMiseListHonbuDao = uiMiseListHonbuDao;
    }
}
