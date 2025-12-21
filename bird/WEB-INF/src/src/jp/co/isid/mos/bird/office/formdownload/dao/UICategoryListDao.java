/*
 * 作成日: 2008/02/18
 */
package jp.co.isid.mos.bird.office.formdownload.dao;

import java.util.List;

import jp.co.isid.mos.bird.office.formdownload.entity.UICateList;

/**
 * フォーム情報
 * @author xytamura
 */
public interface UICategoryListDao {

    public static final Class BEAN = UICateList.class;

    public static final String getCategory_ARGS = "infoShu, sysDate, userId";

    /**
     * カテゴリー情報の取得
     * @param infoShu 情報種別
     * @param sysDate 日付
     * @param userId ユーザーID
     * @return
     */
    public List getCategory(String infoShu, String sysDate, String userId);

}