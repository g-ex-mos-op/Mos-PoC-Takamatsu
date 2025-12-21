package jp.co.isid.mos.bird.office.formdownload.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.office.formdownload.dao.UICategoryListDao;
import jp.co.isid.mos.bird.office.formdownload.entity.UICateList;
import jp.co.isid.mos.bird.office.formdownload.logic.GetUserFormCategoryLogic;

/**
 * カテゴリーの取得ロジック
 * @author xytamura
 */
public class GetUserFormCategoryLogicImpl implements GetUserFormCategoryLogic  {

    /* ロジックID */
    public static final String LOGIC_ID = "BOF002L02";

    /**
     * フォーム情報DAO
     */
    private UICategoryListDao formdownloadCategoryListDao;

    /**
     * 公開対象絞込みロジック
     */
    private GetInfoAccessControlLogic getInfoAccessControlLogic;

    
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((MstCategoryInfo) obj1).getSortKey();
            String val2 = ((MstCategoryInfo) obj2).getSortKey();
            
            return val1.compareTo(val2);
        }
    }

    
    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.office.formdownload.logic.impl.GetUserCategoryLogic#execute(java.lang.String, java.lang.String, jp.co.isid.mos.bird.framework.control.BirdUserInfo)
     */
    public List execute(String infoShu, 
            String sysDate, BirdUserInfo birdUserInfo)
            throws ApplicationException {

        // 事前条件
        validate(infoShu, sysDate, birdUserInfo);

        // フォーム一覧取得
        List listForm = getFormdownloadCategoryListDao().getCategory(infoShu, sysDate, birdUserInfo.getUserID());
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
        List listCate = summaryCategory(listFormKokai, infoShu);


        return listCate;
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
            String sysDate, BirdUserInfo birdUserInfo)
            throws ApplicationException {
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(
                DateVerifier.DATE_TYPE_YM);

        // 情報種別
        if (isNull(infoShu)) {
            throw new NotNullException("情報種別");
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

    }


    // 
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
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
    
    
    /**
     * カテゴリーのサマリー処理を行う。
     * @param listBunshoKokai 文書情報
     * @param infoShu 情報種別
     * @return カテゴリー
     */
    private List summaryCategory(List listFormKokai, String infoShu) {
        Map cateCheckMap = new HashMap();
        List mstCategoryLsit = new ArrayList();
        for (int i = 0; i < listFormKokai.size(); i++) {
            UICateList entity = (UICateList) listFormKokai.get(i);
            if (!cateCheckMap.containsKey(entity.getCateId())) {
                MstCategoryInfo mstCategoryInfo = new MstCategoryInfo();
                mstCategoryInfo.setCateId(entity.getCateId());
                mstCategoryInfo.setCateName(entity.getCateName());
                mstCategoryInfo.setInfoShu(infoShu);
                mstCategoryInfo.setSortKey(entity.getCateSortKey());

                mstCategoryLsit.add(mstCategoryInfo);
                cateCheckMap.put(entity.getCateId(), "");
            }
        }
        // カテゴリーIDで並び替え
        Collections.sort(mstCategoryLsit, new SortComparator());

        return mstCategoryLsit;

    }

    public UICategoryListDao getFormdownloadCategoryListDao() {
        return formdownloadCategoryListDao;
    }

    public void setFormdownloadCategoryListDao(
            UICategoryListDao formdownloadCategoryListDao) {
        this.formdownloadCategoryListDao = formdownloadCategoryListDao;
    }


    
}