package jp.co.isid.mos.bird.bill.buyinglistview.entity;

public class UISeikyuPDFInfo {
    
    public static final String TABLE = "BS02SEKR";
    
    public static final String seikyuDt_COLUMN = "SEIKYU_DT";
    
    public static final String seikyushoId_COLUMN = "SEIKYUSHO_ID";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String pdfName_COLUMN = "PDF_NAME";
    
    public static final String hakkoDt_COLUMN = "HAKKO_DT";
    
    /**
     * 請求日付
     */
    private String seikyuDt;
    
    /**
     * 請求書ＩＤ
     */
    private String seikyushoId;
    
    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * 売掛先名称
     */
    private String onerNameKj;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * PDF名称
     */
    private String pdfName;
    
    /**
     * 発行日
     */
    private String hakkoDt;
    
    /**
     * 請求日付を取得します。
     * @return 請求日付
     */
    public String getSeikyuDt() {
        return seikyuDt;
    }
    /**
     * 請求日付を設定します。
     * @param seikyuDt 請求日付
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
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * PDF名称を取得します。
     * @return PDF名称
     */
    public String getPdfName() {
        return pdfName;
    }
    /**
     * PDF名称を設定します。
     * @param pdfName PDF名称
     */
    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }
    
    /**
     * 発行日を取得します。
     * @return 発行日
     */
    public String getHakkoDt() {
        return hakkoDt;
    }
    /**
     * 発行日を設定します。
     * @param hakkoDt 発行日
     */
    public void setHakkoDt(String hakkoDt) {
        this.hakkoDt = hakkoDt;
    }
}
