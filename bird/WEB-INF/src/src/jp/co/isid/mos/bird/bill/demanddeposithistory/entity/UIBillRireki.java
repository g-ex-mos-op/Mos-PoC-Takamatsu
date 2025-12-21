package jp.co.isid.mos.bird.bill.demanddeposithistory.entity;

import java.math.BigDecimal;

public class UIBillRireki {

    public static final String TABLE = "BS02SEKR";

    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String urikakeCd_COLUMN = "URIKAKE_CD";

    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";

    public static final String hakkoDt_COLUMN = "HAKKO_DT";

    public static final String seikyushoId_COLUMN = "SEIKYUSHO_ID";

    public static final String seikyuZen_COLUMN = "SEIKYU_ZEN";

    public static final String nyukinGaku_COLUMN = "NYUKIN_GAKU";

    public static final String kuriZan_COLUMN = "KURI_ZAN";

    public static final String kazeiGaku_COLUMN = "KAZEI_GAKU";

    public static final String shoTax_COLUMN = "SHO_TAX";

    public static final String handTax_COLUMN = "HAND_TAX";

    public static final String taxKei_COLUMN = "TAX_KEI";

    public static final String hizeiGaku_COLUMN = "HIZEI_GAKU";

    public static final String seikyuKon_COLUMN = "SEIKYU_KON";

    public static final String nyukinRuiseki_COLUMN = "NYUKIN_RUISEKI";

    public static final String seikyuNyukinDt_COLUMN = "SEIKYU_NYUKIN_DT";

    public static final String seikyuNyukinKbn_COLUMN = "";

    public static final String zandaka_COLUMN = "";

    public static final String kaiageGaku_COLUMN = "KAIAGE_GAKU";

//add 2023/04/11 USI金 begin
    public static final String changeDt = "20230630";
//add 2023/04/11 USI金 end
    /**
     * 企業コード
     */
    private String companyCd;

    /**
     * 売掛先コード
     */
    private String urikakeCd;

    /**
     * 売掛先名称
     */
    private String onerNameKj;

    /**
     * 発効日
     */
    private String hakkoDt;

/*add 2023/04/11 USI金 begin*/
    /**
     * 今回お買上額内訳区分
     * 0の場合、変更前の画面：今回お買上額内訳が課税合計額、消費税額、税込非課税額となる画面
     * 1の場合、変更後の画面：今回お買上額内訳が税抜金額合計、消費税額、非課税となる画面
     */
    private String utiwakeKbn;
/*add 2023/04/11 USI金 end*/

    /**
     * 請求書ＩＤ
     */
    private String seikyushoId;

    /**
     * 前回請求額
     */
    private BigDecimal seikyuZen = new BigDecimal(0);

    /**
     * 入金額
     */
    private BigDecimal nyukinGaku = new BigDecimal(0);

    /**
     * 繰越残高
     */
    private BigDecimal kuriZan = new BigDecimal(0);

    /**
     * 課税合計額
     */
    private BigDecimal kazeiGaku = new BigDecimal(0);

    /**
     * 消費税額
     */
    private BigDecimal shoTax = new BigDecimal(0);

    /**
     * 手入力消費税
     */
    private BigDecimal handTax = new BigDecimal(0);

    /**
     * 消費税額合計
     */
    private BigDecimal taxKei = new BigDecimal(0);


    /**
     * 税込・非課税額
     */
    private BigDecimal hizeiGaku = new BigDecimal(0);

    /**
     * 今回御請求額
     */
    private BigDecimal seikyuKon = new BigDecimal(0);

    /**
     * 入金累積額
     */
    private BigDecimal nyukinRuiseki = new BigDecimal(0);

    /**
     * 請求入金日付
     */
    private String seikyuNyukinDt;

    /**
     * 請求入金区分
     */
    private String seikyuNyukinKbn;

    /**
     * 残高
     */
    private BigDecimal zandaka = new BigDecimal(0);

    /**
     * 買上額
     */
    private BigDecimal kaiageGaku = new BigDecimal(0);

    /**
     * 最新フラグ
     * 同じ請求先レコードの中で最新データか否かを保持
     */
    private boolean saisinFlg;

    /**
     * 請求情報か否(入金情報)か
     * true･･･請求情報、false･･･入金情報
     */
    private boolean isSeikyuInfo;

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
     * 売掛先名称を取得します。
     * @return 売掛先名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * 売掛先名称を設定します。
     * @param onerNameKj 売掛先名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }

    /**
     * 発効日を取得します。
     * @return 発効日
     */
    public String getHakkoDt() {
        return hakkoDt;
    }
    /**
     * 発効日を設定します。
     * @param hakkoDt 発効日
     */
    public void setHakkoDt(String hakkoDt) {
        this.hakkoDt = hakkoDt;
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
     * @param seikyushoId 請求書ＩＤ
     */
    public void setSeikyushoId(String seikyushoId) {
        this.seikyushoId = seikyushoId;
    }

    /**
     * 前回請求額を取得します。
     * @return 前回請求額
     */
    public BigDecimal getSeikyuZen() {
        return seikyuZen;
    }
    /**
     * 前回請求額を設定します。
     * @param seikyuZen 前回請求額
     */
    public void setSeikyuZen(BigDecimal seikyuZen) {
        this.seikyuZen = seikyuZen;
    }

    /**
     * 入金額を取得します。
     * @return 入金額
     */
    public BigDecimal getNyukinGaku() {
        return nyukinGaku;
    }
    /**
     * 入金額を設定します。
     * @param nyukinGaku 入金額
     */
    public void setNyukinGaku(BigDecimal nyukinGaku) {
        this.nyukinGaku = nyukinGaku;
    }

    /**
     * 繰越残高を取得します。
     * @return 繰越残高
     */
    public BigDecimal getKuriZan() {
        return kuriZan;
    }
    /**
     * 繰越残高を設定します。
     * @param kuriZan 繰越残高
     */
    public void setKuriZan(BigDecimal kuriZan) {
        this.kuriZan = kuriZan;
    }

    /**
     * 課税合計額を取得します。
     * @return 課税合計額
     */
    public BigDecimal getKazeiGaku() {
        return kazeiGaku;
    }
    /**
     * 課税合計額を設定します。
     * @param kazeiGaku 課税合計額
     */
    public void setKazeiGaku(BigDecimal kazeiGaku) {
        this.kazeiGaku = kazeiGaku;
    }

    /**
     * 消費税額を取得します。
     * @return 消費税額
     */
    public BigDecimal getShoTax() {
        return shoTax;
    }
    /**
     * 消費税額を設定します。
     * @param shoTax 消費税額
     */
    public void setShoTax(BigDecimal shoTax) {
        this.shoTax = shoTax;
    }

    /**
     * 手入力消費税を取得します。
     * @return 手入力消費税
     */
    public BigDecimal getHandTax() {
        return handTax;
    }
    /**
     * 手入力消費税を設定します。
     * @param handTax 手入力消費税
     */
    public void setHandTax(BigDecimal handTax) {
        this.handTax = handTax;
    }

    /**
     * 消費税額合計を取得します。
     * @return 消費税額合計
     */
    public BigDecimal getTaxKei() {
        return taxKei;
    }

    /**
     * 消費税額合計を設定します。
     * @param handTax 消費税額合計
     */
    public void setTaxKei(BigDecimal taxKei) {
        this.taxKei = taxKei;
    }

    /**
     * 税込・非課税額を取得します。
     * @return 税込・非課税額
     */
    public BigDecimal getHizeiGaku() {
        return hizeiGaku;
    }
    /**
     * 税込・非課税額を設定します。
     * @param hizeiGaku 税込・非課税額
     */
    public void setHizeiGaku(BigDecimal hizeiGaku) {
        this.hizeiGaku = hizeiGaku;
    }

    /**
     * 今回御請求額を取得します。
     * @return 今回御請求額
     */
    public BigDecimal getSeikyuKon() {
        return seikyuKon;
    }
    /**
     * 今回御請求額を設定します。
     * @param seikyuKon 今回御請求額
     */
    public void setSeikyuKon(BigDecimal seikyuKon) {
        this.seikyuKon = seikyuKon;
    }

    /**
     * 入金累積額を取得します。
     * @return 入金累積額
     */
    public BigDecimal getNyukinRuiseki() {
        return nyukinRuiseki;
    }
    /**
     * 入金累積額を設定します。
     * @param nyukinRuiseki 入金累積額
     */
    public void setNyukinRuiseki(BigDecimal nyukinRuiseki) {
        this.nyukinRuiseki = nyukinRuiseki;
    }

    /**
     * 請求入金日付を取得します。
     * @return 請求入金日付
     */
    public String getSeikyuNyukinDt() {
        return seikyuNyukinDt;
    }
    /**
     * 請求入金日付を設定します。
     * @param seikyuNyukinDt 請求入金日付
     */
    public void setSeikyuNyukinDt(String seikyuNyukinDt) {
        this.seikyuNyukinDt = seikyuNyukinDt;
    }

    /**
     * 請求入金区分を取得します。
     * @return 請求入金区分
     */
    public String getSeikyuNyukinKbn() {
        return seikyuNyukinKbn;
    }
    /**
     * 請求入金区分を設定します。
     * @param seikyuNyukinKbn 請求入金区分
     */
    public void setSeikyuNyukinKbn(String seikyuNyukinKbn) {
        this.seikyuNyukinKbn = seikyuNyukinKbn;
    }

    /**
     * 残高を取得します。
     * @return 残高
     */
    public BigDecimal getZandaka() {
        return zandaka;
    }
    /**
     * 残高を設定します。
     * @param zandaka 残高
     */
    public void setZandaka(BigDecimal zandaka) {
        this.zandaka = zandaka;
    }

    /**
     * 買上額を取得します。
     * @return 買上額
     */
    public BigDecimal getKaiageGaku() {
        return kaiageGaku;
    }
    /**
     * 買上額を設定します。
     * @param kaiageGaku 買上額
     */
    public void setKaiageGaku(BigDecimal kaiageGaku) {
        this.kaiageGaku = kaiageGaku;
    }

    /**
     * 最新フラグを取得します。
     * @return 最新フラグ
     */
    public boolean getSaisinFlg() {
        return saisinFlg;
    }
    /**
     * 最新フラグを設定します。
     * @param saisinFlg 最新フラグ
     */
    public void setSaisinFlg(boolean saisinFlg) {
        this.saisinFlg = saisinFlg;
    }

    /**
     * 請求情報か否(入金情報)かを取得します。
     * @return true･･･請求情報、false･･･入金情報
     */
    public boolean getIsSeikyuInfo() {
        return isSeikyuInfo;
    }
    /**
     * 請求情報か否(入金情報)かを設定します。
     * @param true･･･請求情報、false･･･入金情報
     */
    public void setIsSeikyuInfo(boolean boo) {
        this.isSeikyuInfo = boo;
    }

/*add 2023/04/11 USI金 begin*/
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
/*add 2023/04/11 USI金 end*/
}
