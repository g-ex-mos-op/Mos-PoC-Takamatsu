/*
 * 作成日: 2007/01/31
 */
package jp.co.isid.mos.bird.sysadmin.userregist.entity;

/**
 * @author xamaruyama
 */

public class CodRoleType {
    
    public static final String TABLE = "BR61MKRL";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String roleCd_COLUMN = "ROLE_CD";
    
    public static final String description_COLUMN = "DESCRIPTION";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * 説明
     */
    private String description;
    
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
    
    /**
     * 説明を取得します。
     * @return 説明
     */
    public String getDescription() {
        return description;
    }
    /**
     * 説明を設定します。
     * @param description 説明
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
