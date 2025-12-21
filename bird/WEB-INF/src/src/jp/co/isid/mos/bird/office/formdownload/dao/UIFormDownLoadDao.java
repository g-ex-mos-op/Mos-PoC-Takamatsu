/*
 * 作成日: 2005/12/27
 */
package jp.co.isid.mos.bird.office.formdownload.dao;

import java.util.List;

import jp.co.isid.mos.bird.office.formdownload.entity.UIFormDownLoad;

/**
 * フォーム情報
 * @author xytamura
 */
public interface UIFormDownLoadDao {

    public static final Class BEAN = UIFormDownLoad.class;

    public static final String getViewForm_ARGS = "infoShu, cateId, subCateId, title, sysDate, userId";

    public static final String getCategory_ARGS = "infoShu, sysDate, userId";

    /**
     * フォーム情報の取得
     * @param infoShu 情報種別
     * @param cateId カテゴリID
     * @param subCateId サブカテゴリID
     * @param title タイトル
     * @param sysDate 日付
     * @param userId ユーザーID
     * @return
     */
    public List getViewForm(String infoShu, String cateId, String subCateId,
            String title, String sysDate, String userId);

    /**
     * カテゴリー情報の取得
     * @param infoShu 情報種別
     * @param sysDate 日付
     * @param userId ユーザーID
     * @return
     */
    public List getCategory(String infoShu, String sysDate, String userId);

}
