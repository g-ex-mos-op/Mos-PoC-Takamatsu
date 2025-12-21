/*
 * 作成日: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIMiseInfo;

/**
 * 店情報
 * 
 * @author xlee
 */
public interface UIMiseInfoDao {

    public static final Class BEAN = UIMiseInfo.class;

    public static final String getMiseInfo_ARGS = "sysDate,onerCd";

    /**
     *店情報取得
     * 
     * @param sysDate システム日付
     * @param onerCd オーナーコード
     * 
     * @return 店情報
     */
    public List getMiseInfo(String sysDate, String onerCd);
}
