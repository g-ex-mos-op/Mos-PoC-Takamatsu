/*
 * 作成日: 2005/12/27
 */
package jp.co.isid.mos.bird.portal.top.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.top.entity.UIOsirase;

/**
 * お知らせ情報取得
 * @author itamoto
 */
public interface UIOsiraseDao {

    public static final Class BEAN = UIOsirase.class;
    public static final String select_ARGS = "USER_ID, SYS_DATE";
    
    /**
     * 検索条件よりお知らせ情報を取得
     * @param userId     ユーザID
     * @param sysDate    システム日付
     * @return List      検索結果
     */
    public List select(String UserId, String sysDate);
}
