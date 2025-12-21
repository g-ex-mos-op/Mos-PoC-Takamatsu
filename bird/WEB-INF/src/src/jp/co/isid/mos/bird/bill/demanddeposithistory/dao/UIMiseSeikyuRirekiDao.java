/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIMiseSeikyuRireki;

/**
 * 店別請求履歴情報取得
 * 
 * @author xwatanabe
 */
public interface UIMiseSeikyuRirekiDao {

    public static final Class BEAN = UIMiseSeikyuRireki.class;
    
    public static final String getMiseSeikyu_ARGS = "companyCd, urikakeCd, seikyushoId";

    /**
     * 店別請求情報の取得
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getMiseSeikyu(String companyCd, String urikakeCd, String seikyushoId);

}
