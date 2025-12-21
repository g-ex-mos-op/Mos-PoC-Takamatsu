package jp.co.isid.mos.bird.togouser.filterregist.entity;

public class CodBumonName {
    
    public static final String TABLE = "BC08CBMN";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    /**
     * 部門コード
     */
    private String bumonCd;
    
    /**
     * 部門名
     */
    private String bumonName;
    
    /**
     * 部門コードを取得します。
     * @return 部門コード
     */
    public String getBumonCd() {
        return bumonCd;
    }
    /**
     * 部門コードを設定します。
     * @param bumonCd 部門コード
     */
    public void setBumonCd(String bumonCd) {
        this.bumonCd = bumonCd;
    }
    
    /**
     * 部門名を取得します。
     * @return 部門名
     */
    public String getBumonName() {
        return bumonName;
    }
    /**
     * 部門名を設定します。
     * @param bumonName 部門名
     */
    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }
    
}
