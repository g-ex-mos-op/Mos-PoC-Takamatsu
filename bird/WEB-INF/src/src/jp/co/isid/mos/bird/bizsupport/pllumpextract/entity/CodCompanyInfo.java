package jp.co.isid.mos.bird.bizsupport.pllumpextract.entity;

public class CodCompanyInfo {
    
    public static final String TABLE = "BC06COMM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 企業コード
     */
    private String rCompanyCd;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getRCompanyCd() {
        return rCompanyCd;
    }
    /**
     * 企業コードを設定します。
     * @param rCompanyCd 企業コード
     */
    public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd = rCompanyCd;
    }
    
}
