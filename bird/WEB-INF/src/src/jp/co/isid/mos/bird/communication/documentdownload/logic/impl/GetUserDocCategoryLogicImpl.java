package jp.co.isid.mos.bird.communication.documentdownload.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.communication.documentdownload.dao.UICategoryListDao;
import jp.co.isid.mos.bird.communication.documentdownload.entity.UICateList;
import jp.co.isid.mos.bird.communication.documentdownload.logic.GetUserDocCategoryLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * ログインユーザのカテゴリー取得ロジック
 * @author xytamura
 */
public class GetUserDocCategoryLogicImpl implements GetUserDocCategoryLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BCM003L02";

    // 文書DAO 
    private UICategoryListDao documentdownloadUICategoryListDao;

    //公開対象絞込み処理ロジック
    private GetInfoAccessControlLogic getInfoAccessControlLogic;

    // カテゴリーID順にソート
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
     * @see jp.co.isid.mos.bird.communication.documentdownload.logic.impl.GetUserCategoryLogic#execute(java.lang.String, java.lang.String, jp.co.isid.mos.bird.framework.control.BirdUserInfo)
     */
    public List execute(String infoShu, String sysDate,
            BirdUserInfo birdUserInfo) throws ApplicationException {

        // 事前条件
        validate(infoShu, sysDate, birdUserInfo);

        // 文書取得
        List listBunsho = getDocumentdownloadUICategoryListDao().getCategory(infoShu,
                sysDate, birdUserInfo.getUserID());

        // 件数確認
        if (listBunsho == null || listBunsho.size() == 0) {
            throw new NoResultException();
        }

        // 公開対象絞込み処理
        List listBunshoKokai = getGetInfoAccessControlLogic().execute(infoShu,
                birdUserInfo, listBunsho);
        if (listBunshoKokai == null || listBunshoKokai.size() == 0) {
            throw new NoResultException();
        }

        List listCate = summaryCategory(listBunshoKokai, infoShu);

        // 画面表示用のデータにする
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
    private void validate(String infoShu, String sysDate,
            BirdUserInfo birdUserInfo) throws ApplicationException {
        // 日付バリデーター
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

        // BIRDユーザー情報
        if (birdUserInfo == null) {
            throw new NotNullException("ユーザー情報");
        }

    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * 文書DAOを取得します。
     * @return 文書DAO
     */
    public UICategoryListDao getDocumentdownloadUICategoryListDao() {
        return documentdownloadUICategoryListDao;
    }

    /**
     * 文書DAOを設定します。
     * @param 文書DAO
     */
    public void setDocumentdownloadUICategoryListDao(UICategoryListDao documentdownloadUICategoryListDao) {
        this.documentdownloadUICategoryListDao = documentdownloadUICategoryListDao;
    }

    /**
     * 公開対象絞込み処理ロジックを取得します。
     * @return 公開対象絞込み処理ロジック
     */
    public GetInfoAccessControlLogic getGetInfoAccessControlLogic() {
        return getInfoAccessControlLogic;
    }

    /**
     * 公開対象絞込み処理ロジックを取得します。
     * @param 公開対象絞込み処理ロジック
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
    private List summaryCategory(List listBunshoKokai, String infoShu) {
        Map cateCheckMap = new HashMap();
        List mstCategoryLsit = new ArrayList();
        for (int i = 0; i < listBunshoKokai.size(); i++) {
            UICateList entity = (UICateList) listBunshoKokai.get(i);
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

}
