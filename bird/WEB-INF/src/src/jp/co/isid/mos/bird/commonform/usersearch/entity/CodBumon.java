package jp.co.isid.mos.bird.commonform.usersearch.entity;

public class CodBumon {
    
    public static final String TABLE = "BC08CBMN";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    public static final String bumonCd_COLUMN = "BUMON_CD";
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    /**会社コード*/
    private String companyCd;
    /**部門コード*/
    private String bumonCd;
    /**部門名*/
    private String bumonName;

    
    /**
     * 会社コードを取得します。
     * @return 部門コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param company 部門コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
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
