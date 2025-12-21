package jp.co.isid.mos.bird.togouser.userregist.entity;

public class TogoUserList {
    
    public static final String TABLE = "BR01USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userName_COLUMN = "USER_NAME_KJ";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ユーザ名
     */
    private String userNameKj;

    /**
     * 部門コード
     */
    private String bumonCd;
    
    /**
     * 部門名称
     */
    private String bumonName;

    public String getBumonCd() {
        return bumonCd;
    }

    public String getBumonName() {
        return bumonName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserNameKj() {
        return userNameKj;
    }

    public void setBumonCd(String bumonCd) {
        this.bumonCd = bumonCd;
    }

    public void setBumonName(String bumonName) {
        this.bumonName = bumonName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }

        
}
