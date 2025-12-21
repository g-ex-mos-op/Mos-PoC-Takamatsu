package jp.co.isid.mos.bird.commonform.usersearch.entity;
/**
 * 
 * @author inazawa
 *
 */
public class CodRole {
    
    public static final String TABLE = "BR03ROLE";
    
    public static final String roleCD_COLUMN = "ROLE_CD";
    
    public static final String roleName_COLUMN = "ROLE_NAME";
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * ロール名
     */
    private String roleName;
    
    /**
     * ロールコードを取得します。
     * @return ロールコード
     */
    public String getroleCd() {
        return roleCd;
    }
    /**
     * ロールコードを設定します。
     * @param roleCd ロールコード
     */
    public void setroleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    
    /**
     * ロール名を取得します。
     * @return ロール名
     */
    public String getroleName() {
        return roleName;
    }
    /**
     * ロール名を設定します。
     * @param companyName ロール名
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
