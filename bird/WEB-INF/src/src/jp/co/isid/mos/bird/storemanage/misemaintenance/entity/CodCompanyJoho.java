package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

public class CodCompanyJoho {
    
    public static final String TABLE = "BC05KCOM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 管理会社企業名称
     */
    private String companyName;
    
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
     * 管理会社企業名称を取得します。
     * @return 管理会社企業名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 管理会社企業名称を設定します。
     * @param companyName 管理会社企業名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
