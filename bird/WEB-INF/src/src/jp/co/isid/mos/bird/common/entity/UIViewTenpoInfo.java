package jp.co.isid.mos.bird.common.entity;

public class UIViewTenpoInfo {
    
    public static final String TABLE = "";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String dataCd_COLUMN = "DATA_CD";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * データコード
     */
    private String dataCd;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * データコードを取得します。
     * @return データコード
     */
    public String getDataCd() {
        return dataCd;
    }
    /**
     * データコードを設定します。
     * @param dataCd データコード
     */
    public void setDataCd(String dataCd) {
        this.dataCd = dataCd;
    }
    
    /**
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
}
