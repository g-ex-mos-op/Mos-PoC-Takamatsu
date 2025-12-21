package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

public class CodInsentiveCd {
    
    public static final String TABLE = "BC17INSE";
    
    public static final String insentiveCd_COLUMN = "INSENTIVE_CD";
    
    public static final String insentiveName_COLUMN = "INSENTIVE_NAME";
    
    /**
     * インセンティブCD
     */
    private String insentiveCd;
    
    /**
     * インセンティブ名称
     */
    private String insentiveName;
    
    /**
     * インセンティブCDを取得します。
     * @return インセンティブCD
     */
    public String getInsentiveCd() {
        return insentiveCd;
    }
    /**
     * インセンティブCDを設定します。
     * @param insentiveCd インセンティブCD
     */
    public void setInsentiveCd(String insentiveCd) {
        this.insentiveCd = insentiveCd;
    }
    
    /**
     * インセンティブ名称を取得します。
     * @return インセンティブ名称
     */
    public String getInsentiveName() {
        return insentiveName;
    }
    /**
     * インセンティブ名称を設定します。
     * @param insentiveName インセンティブ名称
     */
    public void setInsentiveName(String insentiveName) {
        this.insentiveName = insentiveName;
    }
    
}
