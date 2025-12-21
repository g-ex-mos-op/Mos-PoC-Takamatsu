package jp.co.isid.mos.bird.office.formdownload.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;
import jp.co.isid.mos.bird.office.formdownload.dao.FormDownLoadDao;
import jp.co.isid.mos.bird.office.formdownload.dao.UIFormDownLoadDao;
import jp.co.isid.mos.bird.office.formdownload.entity.FormDownLoad;
import jp.co.isid.mos.bird.office.formdownload.entity.UIFormDownLoad;
import jp.co.isid.mos.bird.office.formdownload.logic.GetFormInfoLogic;

/**
 * フォームダウンロード情報の取得ロジック
 * @author xytamura
 */
public class GetFormInfoLogicImpl implements GetFormInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF002L01";

    private static final int PAGE_MAX_SIZE = 30;


    /**
     * フォーム情報DAO
     */
    private UIFormDownLoadDao uIFormDownLoadDao;

    /**
     * 公開対象絞込みロジック
     */
    private GetInfoAccessControlLogic getInfoAccessControlLogic;

    /**
     * 関連文書検索
     */
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    
    private FormDownLoadDao formDownLoadDao;
    
    //ソート順：カテゴリID 昇順、サブカテゴリーID 昇順、ソートSEQ 昇順
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((FormDownLoad) obj1).getCateSortKey() 
                        + ((FormDownLoad) obj1).getCateId()
                        + ((FormDownLoad) obj1).getSubCateSortKey()
                        + ((FormDownLoad) obj1).getSubCateId()
                        + ((FormDownLoad) obj1).getSortSeq();
            
            String val2 = ((FormDownLoad) obj2).getCateSortKey()
                        + ((FormDownLoad) obj2).getCateId()
                        + ((FormDownLoad) obj2).getSubCateSortKey()
                        + ((FormDownLoad) obj2).getSubCateId()
                        + ((FormDownLoad) obj2).getSortSeq();
            
            return val1.compareTo(val2);
        }
    }

    
    /**
     * フォームダウンロード情報を取得する
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String title タイトル
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @param int pageNo ページ番号
     * @return Map TOTAL_COUNT:指定サブカテゴリの件数 SUB_CATE_LIST：サブカテゴリのリスト
     *         DEF_SUB_CATE：デフォルトサブカテゴリID BUNSHO_LIST：表示用データ
     * @exception ApplicationException
     */
    public Map execute(String infoShu, String cateId, String subCateId,
            String title, String sysDate, BirdUserInfo birdUserInfo, int pageNo)
            throws ApplicationException {
        Map mapRet = new HashMap();
        String defSubCate = subCateId;
        // 事前条件
        validate(infoShu, cateId, title, sysDate, birdUserInfo, pageNo);

        // フォーム一覧取得
        List listForm;
        if (!isNull(cateId)) {
            // カテゴリIDによる検索
            listForm = getFormDownLoadDao().getViewForm(infoShu, cateId,
                    subCateId, null, sysDate, birdUserInfo.getUserID());
        } else {
            // タイトルによる検索
            listForm = getFormDownLoadDao().getViewForm(infoShu, null,
                    null, "%" + title + "%", sysDate, birdUserInfo.getUserID());
        }

        // 件数確認
        if (listForm == null || listForm.size() == 0) {
            throw new NoResultException();
        }

        // 公開対象絞込み処理
        List listFormKokai = getGetInfoAccessControlLogic().execute(infoShu,
                birdUserInfo, listForm);
        if (listFormKokai == null || listFormKokai.size() == 0) {
            throw new NoResultException();
        }

        // サブカテゴリが指定されていない場合、以下の情報を作成
        //  サブカテゴリの一覧、デフォルトサブカテゴリ
        if (!isNull(cateId) && isNull(subCateId)) {
            List listSubCate = makeSubCateList(listForm);
            mapRet.put("SUB_CATE_LIST", listSubCate);
            defSubCate = ((FormDownLoad) listSubCate.get(0)).getSubCateId();
            mapRet.put("DEF_SUB_CATE", defSubCate);

            // デフォルトサブカテゴリを使用し、再度検索
            listForm = getFormDownLoadDao().getViewForm(infoShu, cateId,
                    defSubCate, null, sysDate, birdUserInfo.getUserID());
            
            listFormKokai = getGetInfoAccessControlLogic().execute(infoShu,
                    birdUserInfo, listForm);
        }

        // 件数
        mapRet.put("TOTAL_COUNT", new Integer(listFormKokai.size()));
        if (listFormKokai.size() > PAGE_MAX_SIZE) {
            int endIndex = pageNo * PAGE_MAX_SIZE <= listFormKokai.size() ? pageNo
                    * PAGE_MAX_SIZE
                    : listFormKokai.size();
            listFormKokai = listFormKokai.subList((pageNo - 1)
                    * PAGE_MAX_SIZE, endIndex);
        }
        //ソート番号で並び替え
        Collections.sort(listFormKokai, new SortComparator());

        //検索用Entityから表示用Entityに変換
        for (int i = 0; i < listFormKokai.size(); i++) {
            listFormKokai.set(i, doCastEntity((FormDownLoad) listFormKokai.get(i)));
        }
        
        //関連文書を設定
//        setKanrenBunsho(listFormKokai, infoShu);
        setKanrenBunsho(listFormKokai,infoShu,birdUserInfo,sysDate);
		
        // 表示用データ
        mapRet.put("FORM_LIST", listFormKokai);

        // 画面表示用のデータにする
        return mapRet;
    }

    private UIFormDownLoad doCastEntity(FormDownLoad entity) {
        UIFormDownLoad uiEntity = new UIFormDownLoad();

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
    private void validate(String infoShu, String cateId, String title,
            String sysDate, BirdUserInfo birdUserInfo, int pageNo)
            throws ApplicationException {
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(
                DateVerifier.DATE_TYPE_YM);

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

        // タイトルに「%」、「％」が入力されていないか
        if (!isNull(title)) {
            MetaCharVerifier verifier = new MetaCharVerifier();
            if (!verifier.validate(title)) {
                throw new InvalidInputException("タイトル");
            }
        }
    }

    // サブカテゴリリスト作成
    private List makeSubCateList(List listForm) {
        List listSubCate = new ArrayList();
        String subCate = "";
        for (Iterator e = listForm.iterator(); e.hasNext();) {
            FormDownLoad entity = (FormDownLoad) e.next();
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
     * フォーム情報DAOを取得します。
     * @return フォーム情報DAO
     */
    public UIFormDownLoadDao getUIFormDownLoadDao() {
        return uIFormDownLoadDao;
    }

    /**
     * フォーム情報DAOを設定します。
     * @param formDownLoadDao フォーム情報DAO
     */
    public void setUIFormDownLoadDao(UIFormDownLoadDao uIFormDownLoadDao) {
        this.uIFormDownLoadDao = uIFormDownLoadDao;
    }

    /**
     * 公開対象絞込みロジックを取得します。
     * @return 公開対象絞込みロジック
     */
    public GetInfoAccessControlLogic getGetInfoAccessControlLogic() {
        return getInfoAccessControlLogic;
    }

    /**
     * 公開対象絞込みロジックを設定します。
     * @param 公開対象絞込みロジック
     */
    public void setGetInfoAccessControlLogic(
            GetInfoAccessControlLogic getInfoAccessControlLogic) {
        this.getInfoAccessControlLogic = getInfoAccessControlLogic;
    }
    
//    /**
//     * 関連文書を設定します。
//     * @param listBunshoKokai 関連文書
//     * @param infoShu 情報種別
//     * @deprecated
//     */
//    private void setKanrenBunsho(List listBunshoKokai, String infoShu){
//        for(int i = 0; i < listBunshoKokai.size(); i++){
//            UIFormDownLoad entity = (UIFormDownLoad)listBunshoKokai.get(i);
//            // 関連文書情報取得
//            List listKanren = getGetKanrenBunshoInfoLogic().execute(infoShu, entity.getRegDate(), entity.getSeq());
//            entity.setListKenrenFile(listKanren);
//        }
//    }

    /**
     * 関連文書を設定します。
     * @param listBunshoKokai 関連文書
     * @param infoShu 情報種別
     */
    private void setKanrenBunsho(List listBunshoKokai, String infoShu, BirdUserInfo birdUserInfo, String sysDate){
        for(int i = 0; i < listBunshoKokai.size(); i++){
            UIFormDownLoad entity = (UIFormDownLoad)listBunshoKokai.get(i);
            // 関連文書情報取得
            List listKanren = getGetKanrenBunshoInfoLogic().execute(infoShu, entity.getRegDate(), entity.getSeq(),birdUserInfo, sysDate);
            entity.setListKenrenFile(listKanren);
            entity = null;
            listKanren = null;
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

    public FormDownLoadDao getFormDownLoadDao() {
        return formDownLoadDao;
    }

    public void setFormDownLoadDao(FormDownLoadDao formDownLoadDao) {
        this.formDownLoadDao = formDownLoadDao;
    }

    
}