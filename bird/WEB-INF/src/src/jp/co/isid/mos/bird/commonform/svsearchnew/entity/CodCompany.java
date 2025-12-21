package jp.co.isid.mos.bird.commonform.svsearchnew.entity;

public class CodCompany {
    
    public static final String TABLE = "BM03USCP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 会社名
     */
    private String companyName;
    
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
     * 会社名を取得します。
     * @return 会社名
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名を設定します。
     * @param companyName 会社名
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
