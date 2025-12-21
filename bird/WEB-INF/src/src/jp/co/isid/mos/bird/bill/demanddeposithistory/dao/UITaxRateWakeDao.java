/*
 * 作成日: 2023/02/06
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UITaxRateWake;

/**
 * 税率別の内訳
 *
 * @author USI範
 */
public interface UITaxRateWakeDao {

    public static final Class BEAN =  UITaxRateWake.class;

    public static final String getTenPercent_ARGS = "companyCd, urikakeCd, seikyushoId";
    public static final String getGennZei_ARGS = "companyCd, urikakeCd, seikyushoId";
    public static final String getKeikaSoti_ARGS = "companyCd, urikakeCd, seikyushoId";
    public static final String getHiKazei_ARGS = "companyCd, urikakeCd, seikyushoId";

    /**
     * 税率区分１の内訳
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getTenPercent(String companyCd, String urikakeCd, String seikyushoId);

    /**
     * 税率区分2の内訳
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getGennZei(String companyCd, String urikakeCd, String seikyushoId);

    /**
     * 税率区分3の内訳
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getKeikaSoti(String companyCd, String urikakeCd, String seikyushoId);

    /**
     * 税率区分4の内訳
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getHiKazei(String companyCd, String urikakeCd, String seikyushoId);

}
