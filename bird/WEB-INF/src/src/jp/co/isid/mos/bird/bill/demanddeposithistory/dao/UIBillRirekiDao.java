/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIBillRireki;

/**
 * 請求書履歴取得
 * 
 * @author xwatanabe
 */
public interface UIBillRirekiDao {

    public static final Class BEAN = UIBillRireki.class;
    
    public static final String getBillList_ARGS    = "companyCd, onerCd, startYm, endYm";
    public static final String getUtiwakeBill_ARGS = "companyCd, urikakeCd, seikyushoId";

    /**
     * 請求書履歴の取得
     * @param String companyCd  会社コード
     * @param String onerCd     オーナーコード
     * @param String startYm    表示開始年月
     * @param String endYm      表示終了年月
     * @return List
     */
    public List getBillList(String companyCd, String onerCd, String startYm, String endYm);

    /**
     * 内訳画面請求書履歴の取得
     * @param String companyCd    会社コード
     * @param String urikakeCd    売掛先コード
     * @param String seikyushoId  請求書ID
     * @return List
     */
    public List getUtiwakeBill(String companyCd, String urikakeCd, String seikyushoId);

}
