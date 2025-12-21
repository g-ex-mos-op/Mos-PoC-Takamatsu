package jp.co.isid.mos.bird.bill.demanddeposithistory.entity;

import java.math.BigDecimal;

public class UITaxRateWake {

    public static final String TABLE = "BS12SKR4";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String urikakeCd_COLUMN = "URIKAKE_CD";

    public static final String seikyushoId_COLUMN ="SEIKYUSHO_ID";

    public static final String taxkbn1_kazei_COLUMN = "TAXKBN1_KAZEI";

    public static final String taxkbn2_kazei_COLUMN = "TAXKBN2_KAZEI";

    public static final String taxkbn3_kazei_COLUMN = "TAXKBN3_KAZEI";

    public static final String taxkbn4_hizei_COLUMN = "TAXKBN4_HIZEI";

    public static final String taxkbn1_zeiko_COLUMN = "TAXKBN1_ZEIKO";

    public static final String taxkbn2_zeiko_COLUMN = "TAXKBN2_ZEIKO";

    public static final String taxkbn3_zeiko_COLUMN = "TAXKBN3_ZEIKO";
//add 2023/04/27 USI金 begin
    public static final String taxkbn1_zeiko_tax_COLUMN = "TAXKBN1_ZEIKO_TAX";

    public static final String taxkbn2_zeiko_tax_COLUMN = "TAXKBN2_ZEIKO_TAX";

    public static final String taxkbn3_zeiko_tax_COLUMN = "TAXKBN3_ZEIKO_TAX";
//add 2023/04/27 USI金 end
    public static final String taxkbn1_uri_COLUMN = "TAXKBN1_URI";

    public static final String taxkbn2_uri_COLUMN = "TAXKBN2_URI";

    public static final String taxkbn3_uri_COLUMN = "TAXKBN3_URI";

    public static final String taxkbn1_inv_COLUMN = "TAXKBN1_INV";

    public static final String taxkbn2_inv_COLUMN = "TAXKBN2_INV";

    public static final String taxkbn3_inv_COLUMN = "TAXKBN3_INV";

    /**
     * 企業コード
     */
    private String companyCd;

    /**
     * 売掛先コード
     */
    private String urikakeCd;

    /**
     * 請求書ＩＤ
     */
    private String seikyushoId;

    /**
     * 税率区分＝’1’、課税合計額
     */
    private BigDecimal taxkbn1_kazei = new BigDecimal(0);

    /**
     * 税率区分＝’2’、課税合計額
     */
    private BigDecimal taxkbn2_kazei = new BigDecimal(0);

    /**
     * 税率区分＝’3’、課税合計額
     */
    private BigDecimal taxkbn3_kazei = new BigDecimal(0);

    /**
     * 税率区分＝’1’、税込商品合計
     */
    private BigDecimal taxkbn1_zeiko = new BigDecimal(0);

    /**
     * 税率区分＝’2’、税込商品合計
     */
    private BigDecimal taxkbn2_zeiko = new BigDecimal(0);

    /**
     * 税率区分＝’3’、税込商品合計
     */
    private BigDecimal taxkbn3_zeiko = new BigDecimal(0);

//add 2023/04/27 USI金 begin
    /**
     * 税率区分＝’1’、税込の消費税額
     */
    private BigDecimal taxkbn1_zeiko_tax = new BigDecimal(0);

    /**
     * 税率区分＝’2’、税込の消費税額
     */
    private BigDecimal taxkbn2_zeiko_tax = new BigDecimal(0);

    /**
     * 税率区分＝’3’、税込の消費税額
     */
    private BigDecimal taxkbn3_zeiko_tax = new BigDecimal(0);
//add 2023/04/27 USI金 end
    /**
     * 税率区分＝’1’、内税税抜金額
     */
    private BigDecimal taxkbn1_uri = new BigDecimal(0);

    /**
     * 税率区分＝’2’、内税税抜金額
     */
    private BigDecimal taxkbn2_uri = new BigDecimal(0);

    /**
     * 税率区分＝’3’、内税税抜金額
     */
    private BigDecimal taxkbn3_uri = new BigDecimal(0);

    /**
     * 税率区分＝’1’、インボイス消費税額
     */
    private BigDecimal taxkbn1_inv = new BigDecimal(0);

    /**
     * 税率区分＝’2’、インボイス消費税額
     */
    private BigDecimal taxkbn2_inv = new BigDecimal(0);

    /**
     * 税率区分＝’3’、インボイス消費税額
     */
    private BigDecimal taxkbn3_inv = new BigDecimal(0);
    /**
     * 税率区分＝’4’、非課税額
     */
    private BigDecimal taxkbn4_hizei = new BigDecimal(0);

/*add 2023/04/13 USI金 begin*/
    /**
     * 今回お買上額内訳区分
     * 0の場合、変更前の画面：今回お買上額内訳が課税合計額、消費税額、税込非課税額となる画面
     * 1の場合、変更後の画面：今回お買上額内訳が税抜金額合計、消費税額、非課税となる画面
     */
    private String utiwakeKbn;
/*add 2023/04/13 USI金 end*/

    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }

    /**
     * 請求書ＩＤを取得します。
     * @return 請求書ＩＤ
     */
    public String getSeikyushoId() {
        return seikyushoId;
    }
    /**
     * 請求書ＩＤを設定します。
     * @param urikakeCd 請求書ＩＤ
     */
    public void setSeikyushoId(String seikyushoId) {
        this.seikyushoId = seikyushoId;
    }

    /**
     * 税率区分＝’1’、課税合計額を取得します。
     * @return 税率区分＝’1’、課税合計額
     */
    public BigDecimal gettaxkbn1_kazei() {
        return taxkbn1_kazei;
    }
    /**
     * 税率区分＝’1’、課税合計額を設定します。
     * @param companyCd 税率区分＝’1’、課税合計額
     */
    public void settaxkbn1_kazei(BigDecimal taxkbn1_kazei) {
        this.taxkbn1_kazei = taxkbn1_kazei;
    }

    /**
     * 税率区分＝’2’、課税合計額を取得します。
     * @return 税率区分＝’2’、課税合計額
     */
    public BigDecimal gettaxkbn2_kazei() {
        return taxkbn2_kazei;
    }
    /**
     * 税率区分＝’2’、課税合計額を設定します。
     * @param companyCd 税率区分＝’2’、課税合計額
     */
    public void settaxkbn2_kazei(BigDecimal taxkbn2_kazei) {
        this.taxkbn2_kazei = taxkbn2_kazei;
    }

    /**
     * 税率区分＝’3’、課税合計額を取得します。
     * @return 税率区分＝’3’、課税合計額
     */
    public BigDecimal gettaxkbn3_kazei() {
        return taxkbn3_kazei;
    }
    /**
     * 税率区分＝’3’、課税合計額を設定します。
     * @param companyCd 税率区分＝’3’、課税合計額
     */
    public void settaxkbn3_kazei(BigDecimal taxkbn3_kazei) {
        this.taxkbn3_kazei = taxkbn3_kazei;
    }

    /**
     * 税率区分＝’4’、非課税額を取得します。
     * @return 税率区分＝’4’、非課税額
     */
    public BigDecimal gettaxkbn4_hizei() {
        return taxkbn4_hizei;
    }
    /**
     * 税率区分＝’4’、非課税額を設定します。
     * @param companyCd 税率区分＝’4’、非課税額
     */
    public void settaxkbn4_hizei(BigDecimal taxkbn4_hizei) {
        this.taxkbn4_hizei = taxkbn4_hizei;
    }

    /**
     * 税率区分＝’1’、税込商品合計を取得します。
     * @return 税率区分＝’1’、税込商品合計
     */
    public BigDecimal gettaxkbn1_zeiko() {
        return taxkbn1_zeiko;
    }
    /**
     * 税率区分＝’1’、税込商品合計を設定します。
     * @param companyCd 税率区分＝’1’、税込商品合計
     */
    public void settaxkbn1_zeiko(BigDecimal taxkbn1_zeiko) {
        this.taxkbn1_zeiko = taxkbn1_zeiko;
    }

    /**
     * 税率区分＝’2’、税込商品合計を取得します。
     * @return 税率区分＝’2’、税込商品合計
     */
    public BigDecimal gettaxkbn2_zeiko() {
        return taxkbn2_zeiko;
    }
    /**
     * 税率区分＝’2’、税込商品合計を設定します。
     * @param companyCd 税率区分＝’2’、税込商品合計
     */
    public void settaxkbn2_zeiko(BigDecimal taxkbn2_zeiko) {
        this.taxkbn2_zeiko = taxkbn2_zeiko;
    }

    /**
     * 税率区分＝’3’、税込商品合計を取得します。
     * @return 税率区分＝’3’、税込商品合計
     */
    public BigDecimal gettaxkbn3_zeiko() {
        return taxkbn3_zeiko;
    }
    /**
     * 税率区分＝’3’、税込商品合計を設定します。
     * @param companyCd 税率区分＝’3’、税込商品合計
     */
    public void settaxkbn3_zeiko(BigDecimal taxkbn3_zeiko) {
        this.taxkbn3_zeiko = taxkbn3_zeiko;
    }

 //add 2023/04/27 USI金 begin
    /**
     * 税率区分＝’1’、税込の消費税額を取得します。
     * @return 税率区分＝’1’、税込の消費税額
     */
    public BigDecimal gettaxkbn1_zeiko_tax() {
        return taxkbn1_zeiko_tax;
    }
    /**
     * 税率区分＝’1’、税込の消費税額を設定します。
     * @param companyCd 税率区分＝’1’、税込の消費税額
     */
    public void settaxkbn1_zeiko_tax(BigDecimal taxkbn1_zeiko_tax) {
        this.taxkbn1_zeiko_tax = taxkbn1_zeiko_tax;
    }

    /**
     * 税率区分＝’2’、税込の消費税額を取得します。
     * @return 税率区分＝’2’、税込の消費税額
     */
    public BigDecimal gettaxkbn2_zeiko_tax() {
        return taxkbn2_zeiko_tax;
    }
    /**
     * 税率区分＝’2’、税込の消費税額を設定します。
     * @param companyCd 税率区分＝’2’、税込の消費税額
     */
    public void settaxkbn2_zeiko_tax(BigDecimal taxkbn2_zeiko_tax) {
        this.taxkbn2_zeiko_tax = taxkbn2_zeiko_tax;
    }

    /**
     * 税率区分＝’3’、税込の消費税額を取得します。
     * @return 税率区分＝’3’、税込の消費税額
     */
    public BigDecimal gettaxkbn3_zeiko_tax() {
        return taxkbn3_zeiko_tax;
    }
    /**
     * 税率区分＝’3’、税込の消費税額を設定します。
     * @param companyCd 税率区分＝’3’、税込の消費税額
     */
    public void settaxkbn3_zeiko_tax(BigDecimal taxkbn3_zeiko_tax) {
        this.taxkbn3_zeiko_tax = taxkbn3_zeiko_tax;
    }
 //add 2023/04/27 USI金 end
    /**
     * 税率区分＝’1’、内税税抜金額を取得します。
     * @return 税率区分＝’1’、内税税抜金額
     */
    public BigDecimal gettaxkbn1_uri() {
        return taxkbn1_uri;
    }
    /**
     * 税率区分＝’1’、内税税抜金額を設定します。
     * @param companyCd 税率区分＝’1’、内税税抜金額
     */
    public void settaxkbn1_uri(BigDecimal taxkbn1_uri) {
        this.taxkbn1_uri = taxkbn1_uri;
    }

    /**
     * 税率区分＝’2’、内税税抜金額を取得します。
     * @return 税率区分＝’2’、内税税抜金額
     */
    public BigDecimal gettaxkbn2_uri() {
        return taxkbn2_uri;
    }
    /**
     * 税率区分＝’2’、内税税抜金額を設定します。
     * @param companyCd 税率区分＝’2’、内税税抜金額
     */
    public void settaxkbn2_uri(BigDecimal taxkbn2_uri) {
        this.taxkbn2_uri = taxkbn2_uri;
    }

    /**
     * 税率区分＝’3’、内税税抜金額を取得します。
     * @return 税率区分＝’3’、内税税抜金額
     */
    public BigDecimal gettaxkbn3_uri() {
        return taxkbn3_uri;
    }
    /**
     * 税率区分＝’3’、内税税抜金額を設定します。
     * @param companyCd 税率区分＝’3’、内税税抜金額
     */
    public void settaxkbn3_uri(BigDecimal taxkbn3_uri) {
        this.taxkbn3_uri = taxkbn3_uri;
    }

    /**
     * 税率区分＝’1’、インボイス消費税額を取得します。
     * @return 税率区分＝’1’、インボイス消費税額
     */
    public BigDecimal gettaxkbn1_inv() {
        return taxkbn1_inv;
    }
    /**
     * 税率区分＝’1’、インボイス消費税額を設定します。
     * @param companyCd 税率区分＝’1’、インボイス消費税額
     */
    public void settaxkbn1_inv(BigDecimal taxkbn1_inv) {
        this.taxkbn1_inv = taxkbn1_inv;
    }

    /**
     * 税率区分＝’2’、インボイス消費税額を取得します。
     * @return 税率区分＝’2’、インボイス消費税額
     */
    public BigDecimal gettaxkbn2_inv() {
        return taxkbn2_inv;
    }
    /**
     * 税率区分＝’2’、インボイス消費税額を設定します。
     * @param companyCd 税率区分＝’2’、インボイス消費税額
     */
    public void settaxkbn2_inv(BigDecimal taxkbn2_inv) {
        this.taxkbn2_inv = taxkbn2_inv;
    }

    /**
     * 税率区分＝’3’、インボイス消費税額を取得します。
     * @return 税率区分＝’3’、インボイス消費税額
     */
    public BigDecimal gettaxkbn3_inv() {
        return taxkbn3_inv;
    }
    /**
     * 税率区分＝’3’、インボイス消費税額を設定します。
     * @param companyCd 税率区分＝’3’、インボイス消費税額
     */
    public void settaxkbn3_inv(BigDecimal taxkbn3_inv) {
        this.taxkbn3_inv = taxkbn3_inv;
    }

/*add 2023/04/13 USI金 begin*/
    /**
     * 今回お買上額内訳区分を取得します。
     * @return 0･･･変更前の画面：今回お買上額内訳が課税合計額、消費税額、税込非課税額となる画面
     *              1･･･変更後の画面：今回お買上額内訳が税抜金額合計、消費税額、非課税となる画面
     */
    public String getUtiwakeKbn() {
        return utiwakeKbn;
    }
    /**
     *今回お買上額内訳区分を設定します。
     * @return 0･･･変更前の画面：今回お買上額内訳が課税合計額、消費税額、税込非課税額となる画面
     *              1･･･変更後の画面：今回お買上額内訳が税抜金額合計、消費税額、非課税となる画面
     */
    public void setUtiwakeKbn(String utiwakeKbn) {
        this.utiwakeKbn = utiwakeKbn;
    }
/*add 2023/04/13 USI金 end*/
}
