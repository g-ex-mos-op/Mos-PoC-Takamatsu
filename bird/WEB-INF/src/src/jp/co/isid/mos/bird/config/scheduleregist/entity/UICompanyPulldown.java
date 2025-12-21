package jp.co.isid.mos.bird.config.scheduleregist.entity;

public class UICompanyPulldown {
    
    public static final String TABLE = "BC06COMM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
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
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
