package jp.co.isid.mos.bird.bizadmin.accountedit.entity;

public class CodCompany {
    
    public static final String TABLE = "BC02COMP";
    
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
     * 会社名+企業名
     */
    private String companyHyouji;
    
    /**
     * 管理会社コード
     */
    private String kanriCd;
    
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
    public String getCompanyHyouji() {
        return companyHyouji;
    }
    public void setCompanyHyouji(String companyHyouji) {
        this.companyHyouji = companyHyouji;
    }
    
//  2006/10/31 追加 xayumi -----------------------------------------
    /**
     * 管理会社コードを取得します。
     * @return 管理会社コード
     */
    public String getKanriCd() {
        return kanriCd;
    }
    /**
     * 管理会社コードを設定します。
     * @param companyCd 管理会社コード
     */
    public void setKanriCd(String kanriCd) {
        this.kanriCd = kanriCd;
    }
    
}
