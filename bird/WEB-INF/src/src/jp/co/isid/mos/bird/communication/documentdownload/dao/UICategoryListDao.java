/*
 * 作成日: 2008/02/18
 */
package jp.co.isid.mos.bird.communication.documentdownload.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.documentdownload.entity.UICateList;


/**
 * フォーム情報
 * @author xytamura
 */
public interface UICategoryListDao {

    public static final Class BEAN = UICateList.class;

    public static final String getCategory_ARGS = "infoShu, sysDate, userId";

    /**
     * カテゴリの取得
     * @param String infoShu 情報種別
     * @param String sysDate 日付
     * @param String userId ユーザーID
     * @return List
     */
    public List getCategory(String infoShu, String sysDate, String userId);
}