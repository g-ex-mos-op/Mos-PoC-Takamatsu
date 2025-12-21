/*
 * 作成日: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIOnerCd;

/**
 * オーナーコード情報
 * 
 * @author xlee
 */
public interface UIOnerCdDao {

    public static final Class BEAN = UIOnerCd.class;

    public static final String getOnerCd_ARGS = "USER_ID";

    /**
     * オーナコード取得
     * @param userId ユーザーID
     * @return オーナーコード
     */
    public UIOnerCd getOnerCd(String userId);
}
