/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIShoBnrSeikyuRireki;

/**
 * 商品分類別請求履歴取得
 * 
 * @author xwatanabe
 */
public interface UIShoBnrSeikyuRirekiDao {

    public static final Class BEAN = UIShoBnrSeikyuRireki.class;
    
    public static final String getOnerSeikyuBnrui_ARGS = "companyCd, urikakeCd, seikyushoId";
    public static final String getMiseSeikyuBnrui_ARGS = "companyCd, urikakeCd, seikyushoId";

    /**
     * オーナー商品分類別請求履歴の取得
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getOnerSeikyuBnrui(String companyCd, String urikakeCd, String seikyushoId);

    /**
     * 店商品分類別請求履歴の取得
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getMiseSeikyuBnrui(String companyCd, String urikakeCd, String seikyushoId);

}
