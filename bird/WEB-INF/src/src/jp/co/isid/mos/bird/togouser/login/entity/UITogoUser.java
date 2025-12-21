package jp.co.isid.mos.bird.togouser.login.entity;


public class UITogoUser {
    
    public static final String TABLE = "VIR51USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String pswdUpdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String stopFlg_COLUMN = "STOP_FLG";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ユーザ名称
     */
    private String userNameKj;
    
    /**
     * パスワード
     */
    private String userPswd;
    
    /**
     * パスワード更新日
     */
    private String pswdUpdtDt;
    
    /**
     * 使用停止フラグ
     */
    private String stopFlg;
    
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
     * ユーザ名称を取得します。
     * @return ユーザ名称
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * ユーザ名称を設定します。
     * @param userNameKj ユーザ名称
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    
    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public String getUserPswd() {
        return userPswd;
    }
    /**
     * パスワードを設定します。
     * @param userPswd パスワード
     */
    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }
    
    /**
     * パスワード更新日を取得します。
     * @return パスワード更新日
     */
    public String getPswdUpdtDt() {
        return pswdUpdtDt;
    }
    /**
     * パスワード更新日を設定します。
     * @param pswdUpdtDt パスワード更新日
     */
    public void setPswdUpdtDt(String pswdUpdtDt) {
        this.pswdUpdtDt = pswdUpdtDt;
    }
    
    /**
     * 使用停止フラグを取得します。
     * @return 使用停止フラグ
     */
    public String getStopFlg() {
        return stopFlg;
    }
    /**
     * 使用停止フラグを設定します。
     * @param stopFlg 使用停止フラグ
     */
    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
    }
    
}
