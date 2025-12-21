package jp.co.isid.mos.bird.bizsupport.pllumpextract.entity;

public class CodMiseInfo {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String openDt_COLUMN = "OPEN_DT";
    
    /**
     * 
     */
    private String companyCd;
    
    /**
     * 
     */
    private String miseCd;
    
    /**
     * 
     */
    private String miseNameKj;
    
    /**
     * 
     */
    private String openDt;
    
    /**
     * を取得します。
     * @return 
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * を設定します。
     * @param companyCd 
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * を取得します。
     * @return 
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * を設定します。
     * @param miseCd 
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * を取得します。
     * @return 
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * を設定します。
     * @param miseNameKj 
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * を取得します。
     * @return 
     */
    public String getOpenDt() {
        return openDt;
    }
    /**
     * を設定します。
     * @param openDt 
     */
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    
}
