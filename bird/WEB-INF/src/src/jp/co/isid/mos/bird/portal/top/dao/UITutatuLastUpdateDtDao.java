/*
 * 作成日: 2005/2/1
 */
package jp.co.isid.mos.bird.portal.top.dao;

import jp.co.isid.mos.bird.portal.top.entity.UITutatuLastUpdateDt;

/**
 * 通達最新更新日取得
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public interface UITutatuLastUpdateDtDao {

    public static final Class BEAN = UITutatuLastUpdateDt.class;
    public static final String select_ARGS = "sysDate, userId";

    /**
     * 通達最新更新日検索処理
     * 
     * @param sysDate
     * @param userId
     * @return
     */
    public String select(String sysDate, String userId);
}
