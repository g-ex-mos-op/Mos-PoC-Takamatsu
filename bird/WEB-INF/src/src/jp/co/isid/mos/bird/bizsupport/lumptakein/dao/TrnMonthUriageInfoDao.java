/*
 * 作成日: 2006/03/15
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.dao;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnMonthUriageInfo;

/**
 * 売上情報DAO
 * 
 * @author xyuchida
 */
public interface TrnMonthUriageInfoDao {

    public static final Class BEAN = TrnMonthUriageInfo.class;

    public static final String getMonthUriage_ARGS = "eigyoDt, miseCd";

    /**
     * 売上情報取得
     * @param eigyoDt 営業年月
     * @param miseCd 店コード
     * @return 売上高
     */
    public BigDecimal getMonthUriage(String eigyoDt, String miseCd);
}
