package jp.co.isid.mos.bird.communication.documentdownload.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.communication.common.util.CommuniCationUtil;
import jp.co.isid.mos.bird.communication.documentdownload.dao.ViewBunshoInfoDao;
import jp.co.isid.mos.bird.communication.documentdownload.entity.UIViewBunshoInfo;
import jp.co.isid.mos.bird.communication.documentdownload.entity.ViewBunshoInfo;
import jp.co.isid.mos.bird.communication.documentdownload.logic.GetDocInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * 照会文書の取得ロジック 
 * @author xnkusama
 */
public class GetDocInfoLogicImpl implements GetDocInfoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BCM003L01";

    private static final int PAGE_MAX_SIZE = 30;
    
    /*【DAO】*/
    private ViewBunshoInfoDao documentdownloadViewBunshoInfoDao;
    //【ロジック】
    // 公開対象絞込み処理
    private GetInfoAccessControlLogic getInfoAccessControlLogic;   
    //関連文書検索
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    
    //ソート順：カテゴリID 昇順、サブカテゴリーID 昇順、ソートSEQ 昇順
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((UIViewBunshoInfo) obj1).getCateSortKey()
                        + ((UIViewBunshoInfo) obj1).getCateId()
                        + ((UIViewBunshoInfo) obj1).getSubCateSortKey()
                        + ((UIViewBunshoInfo) obj1).getSubCateId()
                        + ((UIViewBunshoInfo) obj1).getSortSeq();
            
            String val2 = ((UIViewBunshoInfo) obj2).getCateSortKey()
                        + ((UIViewBunshoInfo) obj2).getCateId()
                        + ((UIViewBunshoInfo) obj2).getSubCateSortKey()
                        + ((UIViewBunshoInfo) obj2).getSubCateId()
                        + ((UIViewBunshoInfo) obj2).getSortSeq();
            
            return val1.compareTo(val2);
        }
    }

    
    /**
     * 照会対象の文書を取得する
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String title タイトル
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @param int pageNo ページ番号
     * @return Map  TOTAL_COUNT:指定サブカテゴリの件数
     * 　　　　　　　SUB_CATE_LIST：サブカテゴリのリスト
     * 　　　　　　　DEF_SUB_CATE：デフォルトサブカテゴリID
     * 　　　　　　　BUNSHO_LIST：表示用データ
     * @exception ApplicationException
     */
    public Map execute(String infoShu, 
                         String cateId,
                         String subCateId,
                         String title,
                         String sysDate,
                         BirdUserInfo birdUserInfo,
                         int pageNo) throws ApplicationException
    {
        Map mapRet = new HashMap();
        String defSubCate = subCateId;
        
        // 事前条件
        validate(infoShu, cateId, title, sysDate, birdUserInfo, pageNo);
        
        // 文書一覧取得
        List listBunsho;
        if (!isNull(cateId)) {
            // カテゴリIDによる検索
            listBunsho = getDocumentdownloadViewBunshoInfoDao().getViewCategoryBunsho(infoShu, cateId, subCateId, sysDate, birdUserInfo.getUserID());
        }
        else {
            // タイトルによる検索
            listBunsho = getDocumentdownloadViewBunshoInfoDao().getViewTitleBunsho(infoShu, "%" + title + "%", sysDate, birdUserInfo.getUserID());
        }
        // 件数確認
        if (listBunsho == null || listBunsho.size() == 0) {
            throw new NoResultException();
        }
        
        // 公開対象絞込み処理
        List listBunshoKokai = getGetInfoAccessControlLogic().execute(infoShu, birdUserInfo, listBunsho);
        if (listBunshoKokai == null || listBunshoKokai.size() == 0) {
            throw new NoResultException();
        }
        // サブカテゴリが指定されていない場合、以下の情報を作成
        //  サブカテゴリの一覧、デフォルトサブカテゴリ
        if (!isNull(cateId) && isNull(subCateId)) {
            List listSubCate = makeSubCateList(listBunsho);
            mapRet.put("SUB_CATE_LIST", listSubCate);
            defSubCate = ((ViewBunshoInfo) listSubCate.get(0)).getSubCateId();
            mapRet.put("DEF_SUB_CATE", defSubCate);
            
            // デフォルトサブカテゴリを使用し、再度検索
            listBunsho = getDocumentdownloadViewBunshoInfoDao().getViewCategoryBunsho(infoShu, cateId, defSubCate, sysDate, birdUserInfo.getUserID());
            listBunshoKokai = getGetInfoAccessControlLogic().execute(infoShu, birdUserInfo, listBunsho);
        }
        listBunsho.clear();
        // 件数
        mapRet.put("TOTAL_COUNT", new Integer(listBunshoKokai.size()));
        if (listBunshoKokai.size() > PAGE_MAX_SIZE) {
            int endIndex = pageNo * PAGE_MAX_SIZE <= listBunshoKokai.size() ? pageNo * PAGE_MAX_SIZE : listBunshoKokai.size();
            listBunshoKokai = listBunshoKokai.subList((pageNo - 1) * PAGE_MAX_SIZE, endIndex); 
        }
        //検索用Entityから表示用Entityに変換
        for (int i = 0; i < listBunshoKokai.size(); i++) {
            listBunshoKokai.set(i, doCastEntity((ViewBunshoInfo) listBunshoKokai.get(i)));
        }

        //関連文書を設定
        //setKanrenBunsho(listBunshoKokai, infoShu);
        setKanrenBunsho(listBunshoKokai, infoShu ,birdUserInfo, sysDate);
        //ソート
        Collections.sort(listBunshoKokai, new SortComparator());
        
        // 表示用データ
        mapRet.put("BUNSHO_LIST", listBunshoKokai);
        
        // 画面表示用のデータにする
        return mapRet;
    }
    
    private UIViewBunshoInfo doCastEntity(ViewBunshoInfo entity) {
        UIViewBunshoInfo uiEntity = new UIViewBunshoInfo();

        uiEntity.setAttachFl1(entity.getAttachFl1());
        uiEntity.setAttachFl2(entity.getAttachFl2());
        uiEntity.setAttachFl3(entity.getAttachFl3());
        uiEntity.setAttachName1(entity.getAttachName1());
        uiEntity.setAttachName2(entity.getAttachName2());
        uiEntity.setAttachName3(entity.getAttachName3());
        uiEntity.setCateId(entity.getCateId());
        uiEntity.setCateName(entity.getCateName());
        uiEntity.setCateSortKey(entity.getCateSortKey());
        uiEntity.setDiscription(entity.getDiscription());
        uiEntity.setFileName(entity.getFileName());
        uiEntity.setGyotaiKbn(entity.getGyotaiKbn());
        uiEntity.setKobetsuFlg(entity.getKobetsuFlg());
        uiEntity.setLimitKbn(entity.getLimitKbn());
        uiEntity.setLimitKbnName(entity.getLimitKbnName());
        uiEntity.setMiseFlg(entity.getMiseFlg());
        uiEntity.setPubDateFrom(entity.getPubDateFrom());
        uiEntity.setPubDateTo(entity.getPubDateTo());
        uiEntity.setPubOrgName(entity.getPubOrgName());
        uiEntity.setPubUser(entity.getPubUser());
        uiEntity.setPugOrg(entity.getPugOrg());
        uiEntity.setRegDate(entity.getRegDate());
        uiEntity.setSakujoFlg(entity.getSakujoFlg());
        uiEntity.setSeq(entity.getSeq());
        uiEntity.setSortSeq(entity.getSortSeq());
        uiEntity.setSubCateId(entity.getSubCateId());
        uiEntity.setSubCateName(entity.getSubCateName());
        uiEntity.setSubCateSortKey(entity.getSubCateSortKey());
        uiEntity.setTitle(entity.getTitle());
        uiEntity.setUserNameKj(entity.getUserNameKj());
        entity = null;
        return uiEntity;
    }
    
    /**
     * 事前条件
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String title タイトル
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @param int pageNo ページ番号
     * @throws ApplicationException
     */
    private void validate(String infoShu, 
                            String cateId,
                            String title,
                            String sysDate,
                            BirdUserInfo birdUserInfo,
                            int pageNo) throws ApplicationException
    {
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(DateVerifier.DATE_TYPE_YM);
        
        // 情報種別
        if (isNull(infoShu)) {
            throw new NotNullException("情報種別");
        }
        // カテゴリID 、 タイトルのどちらかは必須
        if (isNull(cateId) && isNull(title)) {
            throw new NotNullException("カテゴリまたはタイトル");
        }

        // システム日付
        if (isNull(sysDate)) {
            throw new InvalidInputException("日付");
        }
        dateValidator.validate(sysDate);
        
        //BIRDユーザー情報
        if (birdUserInfo == null) {
            throw new NotNullException("ユーザー情報");
        }

        // ページ
        if (pageNo == 0) {
            throw new NotNullException("ページ");
        }
    }
    // サブカテゴリリスト作成
    private List makeSubCateList(List listBunsho) {
        List listSubCate = new ArrayList();
        String subCate = "";
        for (Iterator e = listBunsho.iterator(); e.hasNext();) {
            ViewBunshoInfo entity = (ViewBunshoInfo) e.next();
            if (!subCate.equals(entity.getSubCateId())) {
                listSubCate.add(entity);
                subCate = entity.getSubCateId();
            }
        }
        
        return listSubCate;
    }
    // 
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * @return uiViewBunshoInfoDao を戻します。
     */
    public ViewBunshoInfoDao getDocumentdownloadViewBunshoInfoDao() {
        return documentdownloadViewBunshoInfoDao;
    }
    /**
     * @param uiViewBunshoInfoDao uiViewBunshoInfoDao を設定。
     */
    public void setDocumentdownloadViewBunshoInfoDao(ViewBunshoInfoDao documentdownloadViewBunshoInfoDao) {
        this.documentdownloadViewBunshoInfoDao = documentdownloadViewBunshoInfoDao;
    }
    /**
     * @return getInfoAccessControlLogic を戻します。
     */
    public GetInfoAccessControlLogic getGetInfoAccessControlLogic() {
        return getInfoAccessControlLogic;
    }
    /**
     * @param getInfoAccessControlLogic getInfoAccessControlLogic を設定。
     */
    public void setGetInfoAccessControlLogic(
            GetInfoAccessControlLogic getInfoAccessControlLogic) {
        this.getInfoAccessControlLogic = getInfoAccessControlLogic;
    }
    
    /**
     * 関連文書を設定します。
     * @param listBunshoKokai 関連文書
     * @param infoShu 情報種別
     */
    private void setKanrenBunsho(List listBunshoKokai, String infoShu, BirdUserInfo birdUserInfo, String sysDate){
        String publicTutatuDate = CommuniCationUtil.getTutatuPubDate(sysDate);
    	for(int i = 0; i < listBunshoKokai.size(); i++){
            UIViewBunshoInfo entity = (UIViewBunshoInfo)listBunshoKokai.get(i);
            // 関連文書情報取得
            List listKanren = getGetKanrenBunshoInfoLogic().execute(
            		null,infoShu, entity.getRegDate(), entity.getSeq()
            		, sysDate, publicTutatuDate);
            entity.setListKenrenFile(listKanren);
        }
    }

    /**
     * 関連文書検索ロジックを取得します。
     * @return 関連文書検索ロジック
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }

    /**
     * 関連文書検索ロジックを設定します。
     * @param 関連文書検索ロジック
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }
    
    
}
