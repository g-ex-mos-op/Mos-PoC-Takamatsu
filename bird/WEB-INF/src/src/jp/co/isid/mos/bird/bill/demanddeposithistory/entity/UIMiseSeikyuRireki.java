package jp.co.isid.mos.bird.bill.demanddeposithistory.entity;

import java.math.BigDecimal;

public class UIMiseSeikyuRireki {
    
    public static final String TABLE = "BS01URSR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String urikakeYm_COLUMN = "URIKAKE_YM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String seikyuId_COLUMN = "SEIKYU_ID";
    
    public static final String seikyuKbn_COLUMN = "SEIKYU_KBN";
    
    public static final String uriKin_COLUMN = "URI_KIN";
    
    public static final String uriAka_COLUMN = "URI_AKA";
    
    public static final String uriTei_COLUMN = "URI_TEI";
    
    public static final String taxnKin_COLUMN = "TAXN_KIN";
    
    public static final String taxyKin_COLUMN = "TAXY_KIN";
    
    public static final String sotoTax_COLUMN = "SOTO_TAX";
    
    public static final String handTax_COLUMN = "HAND_TAX";
    
    public static final String komiKin_COLUMN = "KOMI_KIN";
    
    public static final String uchiTax_COLUMN = "UCHI_TAX";
    
    public static final String kousenKin_COLUMN = "KOUSEN_KIN";
    
    public static final String royalKin_COLUMN = "ROYAL_KIN";
    
    public static final String urizanAll_COLUMN = "URIZAN_ALL";
    
    public static final String nyuYoDt_COLUMN = "NYU_YO_DT";
    
    public static final String seikyuDt_COLUMN = "SEIKYU_DT";
    
    public static final String seikyushoId_COLUMN = "SEIKYUSHO_ID";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * 売掛残高年月
     */
    private String urikakeYm;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * 請求ＩＤ
     */
    private String seikyuId;
    
    /**
     * 請求区分
     */
    private String seikyuKbn;
    
    /**
     * 通常売上金額合計
     */
    private String uriKin;
    
    /**
     * 赤伝売上合計
     */
    private String uriAka;
    
    /**
     * 訂正売上合計
     */
    private BigDecimal uriTei = new BigDecimal(0);
    
    /**
     * 非課税対象商品額
     */
    private BigDecimal taxnKin = new BigDecimal(0);
    
    /**
     * 課税対象商品額
     */
    private BigDecimal taxyKin = new BigDecimal(0);
    
    /**
     * 外税消費税
     */
    private BigDecimal sotoTax = new BigDecimal(0);
    
    /**
     * 手入力消費税
     */
    private BigDecimal handTax = new BigDecimal(0);
    
    /**
     * 税込商品額
     */
    private BigDecimal komiKin = new BigDecimal(0);
    
    /**
     * 内税（算出）消費税
     */
    private BigDecimal uchiTax = new BigDecimal(0);
    
    /**
     * 広告宣伝費金額
     */
    private BigDecimal kousenKin = new BigDecimal(0);
    
    /**
     * ロイヤルティ金額
     */
    private BigDecimal royalKin = new BigDecimal(0);
    
    /**
     * 売掛残高合計
     */
    private BigDecimal urizanAll = new BigDecimal(0);
    
    /**
     * 入金予定日
     */
    private String nyuYoDt;
    
    /**
     * 請求書締め日
     */
    private String seikyuDt;
    
    /**
     * 請求書ＩＤ
     */
    private String seikyushoId;

    /**
     * 税込・非課税額
     */
    private BigDecimal zeikomiHikazei = new BigDecimal(0);

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
     * 売掛残高年月を取得します。
     * @return 売掛残高年月
     */
    public String getUrikakeYm() {
        return urikakeYm;
    }
    /**
     * 売掛残高年月を設定します。
     * @param urikakeYm 売掛残高年月
     */
    public void setUrikakeYm(String urikakeYm) {
        this.urikakeYm = urikakeYm;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 請求ＩＤを取得します。
     * @return 請求ＩＤ
     */
    public String getSeikyuId() {
        return seikyuId;
    }
    /**
     * 請求ＩＤを設定します。
     * @param seikyuId 請求ＩＤ
     */
    public void setSeikyuId(String seikyuId) {
        this.seikyuId = seikyuId;
    }
    
    /**
     * 請求区分を取得します。
     * @return 請求区分
     */
    public String getSeikyuKbn() {
        return seikyuKbn;
    }
    /**
     * 請求区分を設定します。
     * @param seikyuKbn 請求区分
     */
    public void setSeikyuKbn(String seikyuKbn) {
        this.seikyuKbn = seikyuKbn;
    }
    
    /**
     * 通常売上金額合計を取得します。
     * @return 通常売上金額合計
     */
    public String getUriKin() {
        return uriKin;
    }
    /**
     * 通常売上金額合計を設定します。
     * @param uriKin 通常売上金額合計
     */
    public void setUriKin(String uriKin) {
        this.uriKin = uriKin;
    }
    
    /**
     * 赤伝売上合計を取得します。
     * @return 赤伝売上合計
     */
    public String getUriAka() {
        return uriAka;
    }
    /**
     * 赤伝売上合計を設定します。
     * @param uriAka 赤伝売上合計
     */
    public void setUriAka(String uriAka) {
        this.uriAka = uriAka;
    }
    
    /**
     * 訂正売上合計を取得します。
     * @return 訂正売上合計
     */
    public BigDecimal getUriTei() {
        return uriTei;
    }
    /**
     * 訂正売上合計を設定します。
     * @param uriTei 訂正売上合計
     */
    public void setUriTei(BigDecimal uriTei) {
        this.uriTei = uriTei;
    }
    
    /**
     * 非課税対象商品額を取得します。
     * @return 非課税対象商品額
     */
    public BigDecimal getTaxnKin() {
        return taxnKin;
    }
    /**
     * 非課税対象商品額を設定します。
     * @param taxnKin 非課税対象商品額
     */
    public void setTaxnKin(BigDecimal taxnKin) {
        this.taxnKin = taxnKin;
    }
    
    /**
     * 課税対象商品額を取得します。
     * @return 課税対象商品額
     */
    public BigDecimal getTaxyKin() {
        return taxyKin;
    }
    /**
     * 課税対象商品額を設定します。
     * @param taxyKin 課税対象商品額
     */
    public void setTaxyKin(BigDecimal taxyKin) {
        this.taxyKin = taxyKin;
    }
    
    /**
     * 外税消費税を取得します。
     * @return 外税消費税
     */
    public BigDecimal getSotoTax() {
        return sotoTax;
    }
    /**
     * 外税消費税を設定します。
     * @param sotoTax 外税消費税
     */
    public void setSotoTax(BigDecimal sotoTax) {
        this.sotoTax = sotoTax;
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
     * 税込商品額を取得します。
     * @return 税込商品額
     */
    public BigDecimal getKomiKin() {
        return komiKin;
    }
    /**
     * 税込商品額を設定します。
     * @param komiKin 税込商品額
     */
    public void setKomiKin(BigDecimal komiKin) {
        this.komiKin = komiKin;
    }
    
    /**
     * 内税（算出）消費税を取得します。
     * @return 内税（算出）消費税
     */
    public BigDecimal getUchiTax() {
        return uchiTax;
    }
    /**
     * 内税（算出）消費税を設定します。
     * @param uchiTax 内税（算出）消費税
     */
    public void setUchiTax(BigDecimal uchiTax) {
        this.uchiTax = uchiTax;
    }
    
    /**
     * 広告宣伝費金額を取得します。
     * @return 広告宣伝費金額
     */
    public BigDecimal getKousenKin() {
        return kousenKin;
    }
    /**
     * 広告宣伝費金額を設定します。
     * @param kousenKin 広告宣伝費金額
     */
    public void setKousenKin(BigDecimal kousenKin) {
        this.kousenKin = kousenKin;
    }
    
    /**
     * ロイヤルティ金額を取得します。
     * @return ロイヤルティ金額
     */
    public BigDecimal getRoyalKin() {
        return royalKin;
    }
    /**
     * ロイヤルティ金額を設定します。
     * @param royalKin ロイヤルティ金額
     */
    public void setRoyalKin(BigDecimal royalKin) {
        this.royalKin = royalKin;
    }
    
    /**
     * 売掛残高合計を取得します。
     * @return 売掛残高合計
     */
    public BigDecimal getUrizanAll() {
        return urizanAll;
    }
    /**
     * 売掛残高合計を設定します。
     * @param urizanAll 売掛残高合計
     */
    public void setUrizanAll(BigDecimal urizanAll) {
        this.urizanAll = urizanAll;
    }
    
    /**
     * 入金予定日を取得します。
     * @return 入金予定日
     */
    public String getNyuYoDt() {
        return nyuYoDt;
    }
    /**
     * 入金予定日を設定します。
     * @param nyuYoDt 入金予定日
     */
    public void setNyuYoDt(String nyuYoDt) {
        this.nyuYoDt = nyuYoDt;
    }
    
    /**
     * 請求書締め日を取得します。
     * @return 請求書締め日
     */
    public String getSeikyuDt() {
        return seikyuDt;
    }
    /**
     * 請求書締め日を設定します。
     * @param seikyuDt 請求書締め日
     */
    public void setSeikyuDt(String seikyuDt) {
        this.seikyuDt = seikyuDt;
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
     * 税込・非課税額を取得します。
     * @return 税込・非課税額
     */
    public BigDecimal getZeikomiHikazei() {
        return zeikomiHikazei;
    }
    /**
     * 税込・非課税額を設定します。
     * @param zeikomiHikazei 税込・非課税額
     */
    public void setZeikomiHikazei(BigDecimal zeikomiHikazei) {
        this.zeikomiHikazei = zeikomiHikazei;
    }

}
