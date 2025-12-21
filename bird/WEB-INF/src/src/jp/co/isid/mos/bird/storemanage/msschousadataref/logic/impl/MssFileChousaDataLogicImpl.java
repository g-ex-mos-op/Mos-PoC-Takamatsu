package jp.co.isid.mos.bird.storemanage.msschousadataref.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dao.MssCodBunsekiKbnDao;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dao.MssMstUserMiseDao;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dao.MssMstUserOnerDao;
import jp.co.isid.mos.bird.storemanage.msschousadataref.dto.MssChousaDataRefDto;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.CodBunsekiName;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MssFileInfo;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.MstUserMise;
import jp.co.isid.mos.bird.storemanage.msschousadataref.entity.UITabData;
import jp.co.isid.mos.bird.storemanage.msschousadataref.logic.MssFileChousaDataLogic;



public class MssFileChousaDataLogicImpl implements MssFileChousaDataLogic{

    private MssMstUserMiseDao mssMstUserMiseDao;
    private MssMstUserOnerDao mssMstUserOnerDao;
    private MssCodBunsekiKbnDao MssCodBunsekiKbnDao;
    // ファイルセパレーター
    private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /*LogicID*/
    public static final String LOGIC_ID =    "BSM011L03";
    /**
     * @param args
     */
    public void execute(MssChousaDataRefDto mssChousaDataRefDto){
        setBunsekiName(mssChousaDataRefDto);
         //Dtoへシステム日付設定
        mssChousaDataRefDto.setSysDate(mssChousaDataRefDto.getBirdDateInfo().getSysDate());
        if(mssChousaDataRefDto.getOnerCd() != null){
            //オーナー名称設定
            String strMstUserOner = getMssMstUserOnerDao().selectUserOner(mssChousaDataRefDto.getOnerCd(),mssChousaDataRefDto.getCompanyCd());
            mssChousaDataRefDto.setOnerName(strMstUserOner);
        }
        //店リスト名称取得
        List mstUserMiseList = getMise(mssChousaDataRefDto);
        if(!mssChousaDataRefDto.getCompanyCd().equals(COMPANY_CD_MOS)){
            throw new NoResultException("");
        }
        //ファイル名称取得
        if(mstUserMiseList != null){
            getFileList(mssChousaDataRefDto,mstUserMiseList);
        }else {
            throw new NoResultException("");
        }

        

    }
    /**
     * 分析名取得
     * @param mssChousaDataRefDto
     */
    private void setBunsekiName(MssChousaDataRefDto mssChousaDataRefDto) {
        List codBunsekiList =  getMssCodBunsekiKbnDao().selectBunsekiName();
        CodBunsekiName codBunsekiName = (CodBunsekiName) codBunsekiList.get(0);
        mssChousaDataRefDto.setBunsekiName(codBunsekiName.getBunsekiName());
    }
    /**
     * 店名称取得
     * @param mssChousaDataRefDto
     * @return 
     */
    private List getMise(MssChousaDataRefDto mssChousaDataRefDto){
        List mstUserMiseList;
        if(mssChousaDataRefDto.getSelectFlg() != 0){
            mstUserMiseList = getMssMstUserMiseDao().selectOnerMise(mssChousaDataRefDto.getOnerCd(),mssChousaDataRefDto.getCompanyCd());
        }else{
            mstUserMiseList = getMssMstUserMiseDao().selectMise(mssChousaDataRefDto.getMiseCd(),mssChousaDataRefDto.getCompanyCd());
        }

        mssChousaDataRefDto.setMiseList(mstUserMiseList);
        
        return mstUserMiseList;

    }
    /**
     * ファイルリスト取得
     */
    private void getFileList(MssChousaDataRefDto mssChousaDataRefDto,List mstUserMiseList) {
        String path = BirdContext.getProperty("fileProperty", "filePathMss");
        String filePth =  path + FILE_SEPARATOR + FILE_SEPARATOR + mssChousaDataRefDto.getJissiNendo();
        File dir = new File(filePth);
        File pdfFiles[] = dir.listFiles();
        List fileList = new ArrayList();
       if(pdfFiles != null && pdfFiles.length > 0){
            String kai = "";
             for(int i=0; i<pdfFiles.length; i++){
                MssFileInfo mssFileInfo = new MssFileInfo();
                String strFilename = pdfFiles[i].getName();
                MstUserMise mstUserMise;
                String thisKai = strFilename.substring(4,6);
                int ikai = Integer.valueOf(thisKai).intValue();
                if(strFilename.length() == 10){
                    mssFileInfo.setFileName(strFilename);
                    mssFileInfo.setNendo(mssChousaDataRefDto.getJissiNendo());
                    mssFileInfo.setKai(String.valueOf(ikai));
                    mssFileInfo.setDataName("ミステリーショッパーズの視点"+ kai);
                    fileList.add(mssFileInfo);
                }else{
                    for(int j=0;j<mstUserMiseList.size();j++){
                        mstUserMise = (MstUserMise)mstUserMiseList.get(j);
                         if(strFilename.substring(0,4).equals(mssChousaDataRefDto.getJissiNendo())){
                            if(mstUserMise.getMiseCd().equals(strFilename.substring(6,11))){
                         
                                
                                mssFileInfo.setFileName(strFilename);
                                mssFileInfo.setNendo(mssChousaDataRefDto.getJissiNendo());
                                mssFileInfo.setKai(String.valueOf(ikai));
                                mssFileInfo.setMiseCd(mstUserMise.getMiseCd());
                                mssFileInfo.setMiseName(mstUserMise.getMiseNameKj());
                                mssFileInfo.setOnerCd(mssChousaDataRefDto.getOnerCd());
                                mssFileInfo.setDataKbn(strFilename.substring(12,13));
                                mssFileInfo.setOnerName(mssChousaDataRefDto.getOnerName());
                                mssFileInfo.setDataName(mssChousaDataRefDto.getBunsekiName());
                                fileList.add(mssFileInfo);
    
                            }
                        }
                    }
                }
            }
            if(fileList.size() <= 0){
                throw new NoResultException("");
            }
            Comparator sortComparator = new Comparator() {
                public boolean equals(Object obj) {
                    return (super.equals(obj));
                }
                public int compare(Object obj1, Object obj2) {
                    String key1 = "";
                    String key2 = "";
                    
                    if(((MssFileInfo) obj1).getFileName().lastIndexOf(".") != -1){
                        key1 = ((MssFileInfo) obj1).getFileName().substring(0, ((MssFileInfo) obj1).getFileName().lastIndexOf("."));
                        key2 = ((MssFileInfo) obj2).getFileName().substring(0, ((MssFileInfo) obj2).getFileName().lastIndexOf("."));
                    }else{
                        key1 = ((MssFileInfo) obj1).getFileName();
                        key2 = ((MssFileInfo) obj2).getFileName();
                    }
                    return key1.compareTo(key2);
                }
            };
            Collections.sort(fileList, sortComparator);

        }else {
            throw new NoResultException("");
        }
       mssChousaDataRefDto.setReExecuteFlg("1");
       mssChousaDataRefDto.setFileList(fileList);
       //タブリスト設定
       mssChousaDataRefDto.setTabList(createTabList(fileList));

    }
    /**
     * タブ情報作成処理
     * 
     * @param fileList
     * @return
     */
    private List createTabList(List fileList) {
        if(fileList == null){
            return null;
        }
        //タブリスト
        List tabList = new ArrayList(); 
        int tabIndex = 0;
        String kai = "";
        
        for(int i = 0; i<fileList.size(); i++){
            MssFileInfo mssFileInfo = (MssFileInfo)fileList.get(i);
            String thisKai = mssFileInfo.getKai();
            if(!kai.equals(thisKai)) {
                kai = thisKai;
                List listData = new ArrayList();
                for(int s=i; s<fileList.size(); s++){
                    mssFileInfo = (MssFileInfo)fileList.get(s);
                    thisKai = mssFileInfo.getKai();
                    if(!kai.equals(thisKai)) {
                        i = s-1;
                        break;
                    }
                    listData.add(mssFileInfo);
                }
                /* タブデータの生成を行います */
                UITabData entity = new UITabData();
                //インデックス設定
                entity.setTabIndex(tabIndex);
                //タブ表示用名称
                entity.setTabName("第"+kai+"回");
                //タブ対象データ
                entity.setListData(listData);
                tabIndex++;
                tabList.add(entity);
            }
        }
        return tabList;
    }
    /**
     * mssChousaDataRefDtoを取得します
     * @param mssChousaDataRefDto
     */
    public MssMstUserMiseDao getMssMstUserMiseDao() {
        return mssMstUserMiseDao;
    }
    /**
     * mssMstUserMiseDaoをセット
     * @param mssMstUserMiseDao
     */
    public void setMssMstUserMiseDao(MssMstUserMiseDao mssMstUserMiseDao) {
        this.mssMstUserMiseDao = mssMstUserMiseDao;
    }
    /**
     * mssMstUserOnerDaoを取得
     * @return mssMstUserOnerDao
     */
    public MssMstUserOnerDao getMssMstUserOnerDao() {
        return mssMstUserOnerDao;
    }
    /**
     * mssMstUserOnerDaoをセット
     * @param mssMstUserOnerDao
     */
    public void setMssMstUserOnerDao(MssMstUserOnerDao mssMstUserOnerDao) {
        this.mssMstUserOnerDao = mssMstUserOnerDao;
    }
    /**
     * mssChousaDataRefDtoをセットします
     * @param mssChousaDataRefDto
     */
    public MssCodBunsekiKbnDao getMssCodBunsekiKbnDao() {
        return MssCodBunsekiKbnDao;
    }
    /**
     * mssCodBunsekiKbnDaoをセット
     * @param mssCodBunsekiKbnDao
     */
    public void setMssCodBunsekiKbnDao(MssCodBunsekiKbnDao mssCodBunsekiKbnDao) {
        MssCodBunsekiKbnDao = mssCodBunsekiKbnDao;
    }
}
