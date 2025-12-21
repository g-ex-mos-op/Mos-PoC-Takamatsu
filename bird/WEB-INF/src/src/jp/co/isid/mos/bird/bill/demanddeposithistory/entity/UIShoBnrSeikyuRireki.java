package jp.co.isid.mos.bird.bill.demanddeposithistory.entity;

import java.math.BigDecimal;

public class UIShoBnrSeikyuRireki {
    
    public static final String TABLE = "BS03USSR";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String seikyuBnrui_COLUMN = "SEIKYU_BNRUI";
    
    public static final String seBnrName_COLUMN = "SE_BNR_NAME";
    
    public static final String sotoKin_COLUMN = "SOTO_KIN";
    
    public static final String utiKin_COLUMN = "UTI_KIN";
    
    public static final String uriKin_COLUMN = "URI_KIN";
    
    public static final String sotoTax_COLUMN = "SOTO_TAX";
    
    public static final String uchiTax_COLUMN = "UCHI_TAX";
    
    public static final String staxUri_COLUMN = "STAX_URI";
    
    public static final String seikyuBnruiKigo_COLUMN = "";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 売掛先コード
     */
    private String onerCd;
    
    /**
     * 売掛先名称
     */
    private String onerNameKj;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 請求書用分類
     */
    private String seikyuBnrui;
    
    /**
     * 請求書分類名称
     */
    private String seBnrName;
    
    /**
     * 外税金額
     */
    private BigDecimal sotoKin = new BigDecimal(0);
    
    /**
     * 内税金額
     */
    private BigDecimal utiKin = new BigDecimal(0);
    
    /**
     * 売上金額
     */
    private BigDecimal uriKin = new BigDecimal(0);
    
    /**
     * 外税消費税
     */
    private BigDecimal sotoTax = new BigDecimal(0);
    
    /**
     * 算出内税消費税
     */
    private BigDecimal uchiTax = new BigDecimal(0);
    
    /**
     * 消費税区分（売上）
     */
    private String staxUri;
    
    /**
     * 請求書分類記号
     */
    private String seikyuBnruiKigo;
 
    /**
     * 請求書用分類記号
     */
    private String seikyuBnruiSort;
    
    
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
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param onerCd 売掛先コード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 請求書用分類を取得します。
     * @return 請求書用分類
     */
    public String getSeikyuBnrui() {
        return seikyuBnrui;
    }
    /**
     * 請求書用分類を設定します。
     * @param seikyuBnrui 請求書用分類
     */
    public void setSeikyuBnrui(String seikyuBnrui) {
        this.seikyuBnrui = seikyuBnrui;
    }
    
    /**
     * 請求書分類名称を取得します。
     * @return 請求書分類名称
     */
    public String getSeBnrName() {
        return seBnrName;
    }
    /**
     * 請求書分類名称を設定します。
     * @param seBnrName 請求書分類名称
     */
    public void setSeBnrName(String seBnrName) {
        this.seBnrName = seBnrName;
    }
    
    /**
     * 外税金額を取得します。
     * @return 外税金額
     */
    public BigDecimal getSotoKin() {
        return sotoKin;
    }
    /**
     * 外税金額を設定します。
     * @param sotoKin 外税金額
     */
    public void setSotoKin(BigDecimal sotoKin) {
        this.sotoKin = sotoKin;
    }
    
    /**
     * 内税金額を取得します。
     * @return 内税金額
     */
    public BigDecimal getUtiKin() {
        return utiKin;
    }
    /**
     * 内税金額を設定します。
     * @param utiKin 内税金額
     */
    public void setUtiKin(BigDecimal utiKin) {
        this.utiKin = utiKin;
    }
    
    /**
     * 売上金額を取得します。
     * @return 売上金額
     */
    public BigDecimal getUriKin() {
        return uriKin;
    }
    /**
     * 売上金額を設定します。
     * @param uriKin 売上金額
     */
    public void setUriKin(BigDecimal uriKin) {
        this.uriKin = uriKin;
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
     * 算出内税消費税を取得します。
     * @return 算出内税消費税
     */
    public BigDecimal getUchiTax() {
        return uchiTax;
    }
    /**
     * 算出内税消費税を設定します。
     * @param uchiTax 算出内税消費税
     */
    public void setUchiTax(BigDecimal uchiTax) {
        this.uchiTax = uchiTax;
    }
    
    /**
     * 消費税区分（売上）を取得します。
     * @return 消費税区分（売上）
     */
    public String getStaxUri() {
        return staxUri;
    }
    /**
     * 消費税区分（売上）を設定します。
     * @param staxUri 消費税区分（売上）
     */
    public void setStaxUri(String staxUri) {
        this.staxUri = staxUri;
    }
    
    /**
     * 請求書分類記号を取得します。
     * @return 請求書分類記号
     */
    public String getSeikyuBnruiKigo() {
        return seikyuBnruiKigo;
    }
    /**
     * 請求書分類記号を設定します。
     * @param seikyuBnruiKigo 請求書分類記号
     */
    public void setSeikyuBnruiKigo(String seikyuBnruiKigo) {
        this.seikyuBnruiKigo = seikyuBnruiKigo;
    }

    /**
     * 請求書用分類ソートキーを取得します。
     * @return 請求書用分類ソートキー
     */
    public String getSeikyuBnruiSort() {
        return seikyuBnruiSort;
    }
    /**
     * 請求書用分類ソートキーを設定します。
     * @param seikyuBnruiKigo 請求書用分類ソートキー
     */
    public void setSeikyuBnruiSort(String seikyuBnruiSort) {
        this.seikyuBnruiSort = seikyuBnruiSort;
    }
    
}
