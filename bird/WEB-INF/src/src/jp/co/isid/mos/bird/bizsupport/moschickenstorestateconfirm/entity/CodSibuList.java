package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.entity;

public class CodSibuList {
    
    public static final String TABLE = "BM10GSIB";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    /**
     * 支部コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 支部コードを設定します。
     * @param companyCd 支部コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
}
