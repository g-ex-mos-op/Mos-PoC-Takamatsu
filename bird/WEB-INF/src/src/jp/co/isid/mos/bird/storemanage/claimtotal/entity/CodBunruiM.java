package jp.co.isid.mos.bird.storemanage.claimtotal.entity;

public class CodBunruiM {
    
    public static final String TABLE = "BC30CBRM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String bnrM_COLUMN = "BNR_M";
    
    public static final String bnrMName_COLUMN = "BNR_M_NAME";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 中分類
     */
    private String bnrM;
    
    /**
     * 中分類名称
     */
    private String bnrMName;
    
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
     * 中分類を取得します。
     * @return 中分類
     */
    public String getBnrM() {
        return bnrM;
    }
    /**
     * 中分類を設定します。
     * @param bnrM 中分類
     */
    public void setBnrM(String bnrM) {
        this.bnrM = bnrM;
    }
    
    /**
     * 中分類名称を取得します。
     * @return 中分類名称
     */
    public String getBnrMName() {
        return bnrMName;
    }
    /**
     * 中分類名称を設定します。
     * @param bnrMName 中分類名称
     */
    public void setBnrMName(String bnrMName) {
        this.bnrMName = bnrMName;
    }
    
}
