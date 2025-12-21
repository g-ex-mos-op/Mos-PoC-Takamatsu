/*
 * 作成日: 2005/12/27
 */
package jp.co.isid.mos.bird.communication.documentdownload.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.documentdownload.entity.ViewBunshoInfo;

/**
 * 文書情報取得
 * @author itamoto
 */
public interface ViewBunshoInfoDao {

    public static final Class BEAN = ViewBunshoInfo.class;

    public static final String getViewCategoryBunsho_ARGS = "infoShu, cateId, subCateId, sysDate, userId";

    public static final String getViewTitleBunsho_ARGS = "infoShu, title, sysDate, userId";

    /**
     * カテゴリ検索による照会文書の取得
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String subCateId サブカテゴリID
     * @param String sysDate 日付
     * @param String userId ユーザーID
     * @return List
     */
    public List getViewCategoryBunsho(String infoShu, String cateId,
            String subCateId, String sysDate, String userId);

    /**
     * タイトル検索による照会文書の取得
     * @param String infoShu 情報種別
     * @param String cateId タイトルID
     * @param String sysDate 日付
     * @param String userId ユーザーID
     * @return List
     */
    public List getViewTitleBunsho(String infoShu, String title,
            String sysDate, String userId);

}