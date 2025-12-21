/*
 * 作成日: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIUriageDakaInfo;

/**
 * 売上高情報取得DAO
 * 
 * @author xlee
 */
public interface UIUriageDakaInfoDao {

    public static final Class BEAN = UIUriageDakaInfo.class;

    public static final String getUriagedakaInfo_ARGS = "miseCd, eigyoDt";

    /**
     * 売上高情報を取得します
     * 
     * @param miseCd 店コード
     * @param eigyoDt 売掛年月
     * @return 売上高情報　BigDecimal
     */
    public BigDecimal getUriagedakaInfo(String miseCd, String eigyoDt);
}
