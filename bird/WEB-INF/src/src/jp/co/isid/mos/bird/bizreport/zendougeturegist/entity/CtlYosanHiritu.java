package jp.co.isid.mos.bird.bizreport.zendougeturegist.entity;

public class CtlYosanHiritu {
    
    public static final String TABLE = "BS06NIPH";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 営業月
     */
    private String eigyoDt;
    
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
     * 営業月を取得します。
     * @return 営業月
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業月を設定します。
     * @param eigyoDt 営業月
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
}
