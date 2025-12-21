package jp.co.isid.mos.bird.framework.entity;

public class CtlUserRole {
    
    public static final String TABLE = "BR04USRL";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String roleCd_COLUMN = "ROLE_CD";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
