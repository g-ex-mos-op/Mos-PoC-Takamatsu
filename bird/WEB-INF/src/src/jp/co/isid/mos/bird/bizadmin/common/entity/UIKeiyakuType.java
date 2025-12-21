package jp.co.isid.mos.bird.bizadmin.common.entity;

public class UIKeiyakuType {
    
    public static final String TABLE = "BR59KTRL";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String keiyakuType_COLUMN = "KEIYAKU_TYPE";
    
    public static final String roleCd_COLUMN = "ROLE_CD";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 契約タイプコード
     */
    private String keiyakuType;
    
    /**
     * ロールコード
     */
    private String roleCd;
    
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
     * 契約タイプコードを取得します。
     * @return 契約タイプコード
     */
    public String getKeiyakuType() {
        return keiyakuType;
    }
    /**
     * 契約タイプコードを設定します。
     * @param keiyakuType 契約タイプコード
     */
    public void setKeiyakuType(String keiyakuType) {
        this.keiyakuType = keiyakuType;
    }
    
    /**
     * ロールコードを取得します。
     * @return ロールコード
     */
    public String getRoleCd() {
        return roleCd;
    }
    /**
     * ロールコードを設定します。
     * @param roleCd ロールコード
     */
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    
}
