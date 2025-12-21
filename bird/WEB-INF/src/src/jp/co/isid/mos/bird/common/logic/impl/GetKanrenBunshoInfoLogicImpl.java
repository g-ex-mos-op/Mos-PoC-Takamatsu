/*
 * 作成日: 2006/02/07
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.TrnDocAccessDao;
import jp.co.isid.mos.bird.common.dao.UIKanrenBunshoInfoDao;
import jp.co.isid.mos.bird.common.dao.UIViewKanrenBunshoDao;
import jp.co.isid.mos.bird.common.dao.UIViewTenpoInfoDao;
import jp.co.isid.mos.bird.common.entity.TrnDocAccess;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.entity.UIKanrenBunshoInfo;
import jp.co.isid.mos.bird.common.entity.UIViewTenpoInfo;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * 関連文書の取得
 * @author xytamura
 */
public class GetKanrenBunshoInfoLogicImpl implements GetKanrenBunshoInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L03";
    
    /**
     * 関連文書Dao
     */
    private UIKanrenBunshoInfoDao uIKanrenBunshoInfoDao;
    /**
     * 関連文書Dao
     */
    private UIViewKanrenBunshoDao uIviewKanrenBunshoDao;

    private TrnDocAccessDao trnDocAccessDao;
    
    private UIViewTenpoInfoDao uIViewTenpoInfoDao;
    
    private static final String INFO_SHU_TUTATU = "02";
    private static final String INFO_SHU_BUNSHO = "03";
    private static final String INFO_SHU_FORM = "04";
    private final String TYPE_MISE = "MISE";
    private final String TYPE_AREA = "AREA";
    
    /**
     * 関連文書の取得
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @return 関連文書情報
     */
    public List execute(String infoShu, String regDate, String seq) {
        //関連文書を取得
        List  listKanrenBunsho =  getUIKanrenBunshoInfoDao().getKanrenBunsho(infoShu, regDate, seq);
        //文書情報を作成
        return  createUIDocSearch(listKanrenBunsho);
    }

    /**
     * 関連文書の取得
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @return 関連文書情報
     */
    public List execute(String infoShu, String regDate, String seq, BirdUserInfo birdUserInfo, String sysDate) {
        //関連文書を取得
        List  listKanrenBunsho =  getUIKanrenBunshoInfoDao().getViewKanrenBunsho(infoShu, regDate, seq ,sysDate);
        //文書情報を作成
        return  createUIDocSearch(listKanrenBunsho,birdUserInfo,sysDate);
    }
    /**
     * 関連文書の取得
     * 
     * @param userId
     * @param infoShu
     * @param regDate
     * @param seq
     * @param pubDate
     * @param pubDateTutatu
     */
    public List execute(String userId
    		, String infoShu, String regDate, String seq
    		, String pubDate, String pubDateTutatu)
    {
        //１．DAO【関連文書情報】
        List  listKanrenBunsho =  getUIviewKanrenBunshoDao().select(
        		pubDate, userId
        		, infoShu, regDate, seq
        		, pubDate, pubDateTutatu
        		, null, null, null, null, null, null, null);
        //２．List[[関連文書]]をリターンします。
        return  listKanrenBunsho;
    }
    /**
     * 関連文書の取得
     * 
     * @param userId[必須]        ログインユーザーID
     * @param infoShu[必須]       情報種別
     * @param pubDate[必須]　　　 文書フォーム用有効公開日
     * @param pubDateTutatu[必須] 通達用有効公開日
     * @param pubDtFrom     パラメータ情報種別の開始公開日
     * @param pubDtTo       パラメータ情報種別の終了公開日
     * @param title               パラメータ情報種別のタイトル
     * @param gyotaiKbn     パラメータ情報種別の業態区分
     * @param cateId        パラメータ情報種別のカテゴリー
     * @param subCateId     パラメータ情報種別のサブカテゴリー
     * @param searchEnginFileList パラメータ情報種別の検索エンジン結果(登録日+シーケンス番号の文字列)
     * @return
     */
    public List execute(String userId
   		, String infoShu
		, String pubDate, String pubDateTutatu
		, String pubDtFrom, String pubDtTo
		, String title, String gyotaiKbn
		, String cateId, String subCateId
		, List searchEnginFileList)
    {
        //１．DAO【関連文書情報】
        List  listKanrenBunsho =  getUIviewKanrenBunshoDao().select(
        		pubDate, userId
        		, infoShu, null, null
        		, pubDate, pubDateTutatu
        		, pubDtFrom, pubDtTo, title
        		, gyotaiKbn, cateId, subCateId, searchEnginFileList);
        //２．List[[関連文書]]をリターンします。
        return  listKanrenBunsho;
    }

    /**
     * 関連文書Daoを取得します。
     * @return 関連文書Dao
     */
    public UIKanrenBunshoInfoDao getUIKanrenBunshoInfoDao() {
        return uIKanrenBunshoInfoDao;
    }

    /**
     * 関連文書Daoを設定します。
     * @param 関連文書Dao
     */
    public void setUIKanrenBunshoInfoDao(
            UIKanrenBunshoInfoDao kanrenBunshoInfoDao) {
        uIKanrenBunshoInfoDao = kanrenBunshoInfoDao;
    }

    /**
     * 文書情報を作成します。
     * @param listUIKanrenBunshoInfo 関連文書情報
     * @return 文書情報
     */
    private List createUIDocSearch(List listUIKanrenBunshoInfo) {
        List listUIDocSearch = new ArrayList();

        for (int i = 0; i < listUIKanrenBunshoInfo.size(); i++) {
            UIKanrenBunshoInfo uiKanrenBunshoInfo = (UIKanrenBunshoInfo) listUIKanrenBunshoInfo
                    .get(i);
            UIDocSearch entity = new  UIDocSearch();
            entity.setInfoShu(uiKanrenBunshoInfo.getRelInfoShu());
            entity.setRegDate(uiKanrenBunshoInfo.getRelRegDate());
            entity.setSeq(uiKanrenBunshoInfo.getRelSeq());
            //通達の場合
            if(INFO_SHU_TUTATU.equals(uiKanrenBunshoInfo.getRelInfoShu())){
                entity.setTitle(uiKanrenBunshoInfo.getBt02Title());
                entity.setFileName(uiKanrenBunshoInfo.getBt02FileName());
            //文書の場合    
            }else if(INFO_SHU_BUNSHO.equals(uiKanrenBunshoInfo.getRelInfoShu())){
                entity.setTitle(uiKanrenBunshoInfo.getBt03Title());
                entity.setFileName(uiKanrenBunshoInfo.getBt03FileName());
            //フォームの場合    
            }else if(INFO_SHU_FORM.equals(uiKanrenBunshoInfo.getRelInfoShu())){
                entity.setTitle(uiKanrenBunshoInfo.getBt04Title());
                entity.setFileName(uiKanrenBunshoInfo.getBt04FileName());
            }
            listUIDocSearch.add(entity);
        }
        return listUIDocSearch;
        
    }
    
    /**
     * 文書情報を作成します。
     * @param listUIKanrenBunshoInfo 関連文書情報
     * @return 文書情報
     */
    private List createUIDocSearch(List listUIKanrenBunshoInfo, BirdUserInfo birdUserInfo, String sysDate) {
        List listUIDocSearch = new ArrayList();

        for (int i = 0; i < listUIKanrenBunshoInfo.size(); i++) {
            UIKanrenBunshoInfo uiKanrenBunshoInfo = (UIKanrenBunshoInfo) listUIKanrenBunshoInfo
                    .get(i);
            UIDocSearch entity = new  UIDocSearch();
            entity.setInfoShu(uiKanrenBunshoInfo.getRelInfoShu());
            entity.setRegDate(uiKanrenBunshoInfo.getRelRegDate());
            entity.setSeq(uiKanrenBunshoInfo.getRelSeq());
            //通達の場合
            if(INFO_SHU_TUTATU.equals(uiKanrenBunshoInfo.getRelInfoShu())){
                entity.setTitle(uiKanrenBunshoInfo.getBt02Title());
                entity.setFileName(uiKanrenBunshoInfo.getBt02FileName());
            //文書の場合    
            }else if(INFO_SHU_BUNSHO.equals(uiKanrenBunshoInfo.getRelInfoShu())){
                entity.setTitle(uiKanrenBunshoInfo.getBt03Title());
                entity.setFileName(uiKanrenBunshoInfo.getBt03FileName());
            //フォームの場合    
            }else if(INFO_SHU_FORM.equals(uiKanrenBunshoInfo.getRelInfoShu())){
                entity.setTitle(uiKanrenBunshoInfo.getBt04Title());
                entity.setFileName(uiKanrenBunshoInfo.getBt04FileName());
            }
     
            if ( entity.getTitle() != null  && entity.getFileName() != null ) {
                if ( checkBunshoAccess(entity,birdUserInfo,sysDate)) {
                    listUIDocSearch.add(entity);    
                }
            }
        }
        return listUIDocSearch;
        
    }
    /**
     * 関連文書のアクセス権限を取得します。
     * @param UIDocSearch
     * @return 権限情報
     */    
    public boolean checkBunshoAccess(UIDocSearch entity, BirdUserInfo birdUserInfo,String sysDate) {
        boolean accessFlg = false;
        String infoShu = entity.getInfoShu();
        String regDate = entity.getRegDate();
        String seq = entity.getSeq();
        MstUser mstUser = birdUserInfo.getMstUser();
        String userId = mstUser.getUser_id();
        String companyCd = mstUser.getRCompanyCd();
        
        List accessList = getTrnDocAccessDao().getDocAccess(infoShu,regDate,seq,companyCd,userId);
        
        if ( accessList.size() == 0 ) {
            accessFlg = false;
        } else {
            // ユーザー対象の店コードを取得
            String totalCd = TYPE_MISE;
            List onerMiseList = getUIViewTenpoInfoDao().getOnerTenpoInfo(companyCd,userId,totalCd);
            List staffMiseList = getUIViewTenpoInfoDao().getStaffTenpoInfo(companyCd,userId,totalCd);
            
            // ユーザー対象の店舗から支部取り込みコードを取得
            totalCd = TYPE_AREA;
            List onerAreaList = getUIViewTenpoInfoDao().getOnerTenpoInfo(companyCd,userId,totalCd);
            List staffAreaList = getUIViewTenpoInfoDao().getStaffTenpoInfo(companyCd,userId,totalCd);
            
            for ( int i = 0; i < accessList.size(); i++ ) {
                TrnDocAccess accessEntity = (TrnDocAccess)accessList.get(i);

                List miseList = new ArrayList();
                List areaList = new ArrayList();
                if ( onerMiseList.size() == 0 && staffMiseList.size() == 0) {
                    accessFlg = true;
                    break;
                } else if ( onerMiseList.size() != 0 ) {
                    miseList = onerMiseList;
                    areaList = onerAreaList;
                } else if ( staffMiseList.size() != 0 ) {
                    miseList = staffMiseList;
                    areaList = staffAreaList;
                }
                
                // 個別設定フラグ有りのとき
                if ( accessEntity.getKobetsuFlg().equals("1")) {
                    String areaDaiCd = accessEntity.getKobetsuCd();
                    String gyotaiKbn = accessEntity.getGyotaiKbn();
                    for ( int k = 0; k < areaList.size(); k++ ) {
                        UIViewTenpoInfo viewEntity = (UIViewTenpoInfo)areaList.get(k);
                        if ( gyotaiKbn.equals( viewEntity.getGyotaiKbn().trim())) {
                            if ( areaDaiCd.equals( viewEntity.getDataCd().trim() ) ) {
                                accessFlg = true;
                                break;
                            }
                        }
                    }
                }
                
                //個店設定フラグが有りのとき
                if ( accessEntity.getMiseFlg().equals("1")) {
                    String miseCd = accessEntity.getMiseCd();
                    if(miseCd == null) {
                    	continue;
                    }
                    String gyotaiKbn = accessEntity.getGyotaiKbn();
                    for ( int k = 0; k < miseList.size(); k++ ) {
                        UIViewTenpoInfo viewEntity = (UIViewTenpoInfo)miseList.get(k);
                        if ( gyotaiKbn.equals( viewEntity.getGyotaiKbn().trim())) {
                            if ( miseCd.equals( viewEntity.getDataCd().trim())) {
                                accessFlg = true;
                                break;
                            }
                        }
                    }
                }
                
                if ( accessEntity.getKobetsuFlg().equals("0") && accessEntity.getMiseFlg().equals("0") ) {
                    accessFlg = true;
                    break;
                }
            }
        }
        return accessFlg; 
        
    }

    public TrnDocAccessDao getTrnDocAccessDao() {
        return trnDocAccessDao;
    }

    public void setTrnDocAccessDao(TrnDocAccessDao trnDocAccessDao) {
        this.trnDocAccessDao = trnDocAccessDao;
    }

    public UIViewTenpoInfoDao getUIViewTenpoInfoDao() {
        return uIViewTenpoInfoDao;
    }

    public void setUIViewTenpoInfoDao(UIViewTenpoInfoDao viewTenpoInfoDao) {
        uIViewTenpoInfoDao = viewTenpoInfoDao;
    }

	/**
	 * @return クラス変数uIviewKanrenBunshoDao を戻します。
	 */
	public UIViewKanrenBunshoDao getUIviewKanrenBunshoDao() {
		return uIviewKanrenBunshoDao;
	}

	/**
	 * @param iviewKanrenBunshoDao を クラス変数uIviewKanrenBunshoDaoへ設定します。
	 */
	public void setUIviewKanrenBunshoDao(UIViewKanrenBunshoDao iviewKanrenBunshoDao) {
		uIviewKanrenBunshoDao = iviewKanrenBunshoDao;
	}


}