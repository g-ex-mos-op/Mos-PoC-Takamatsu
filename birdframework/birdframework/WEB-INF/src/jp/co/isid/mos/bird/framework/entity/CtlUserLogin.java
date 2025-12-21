package jp.co.isid.mos.bird.framework.entity;

import java.sql.Timestamp;

public class CtlUserLogin {
    
    public static final String TABLE = "BR43ULOG";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String currentLoginTmsp_COLUMN = "CURRENT_LOGIN_TMSP";
    
    public static final String lastLoginTmsp_COLUMN = "LAST_LOGIN_TMSP";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * 今回ログイン時間
     */
    private Timestamp currentLoginTmsp;
    
    /**
     * 前回ログイン時間
     */
    private Timestamp lastLoginTmsp;
    
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
     * 今回ログイン時間を取得します。
     * @return 今回ログイン時間
     */
    public Timestamp getCurrentLoginTmsp() {
        return currentLoginTmsp;
    }
    /**
     * 今回ログイン時間を設定します。
     * @param currentLoginTmsp 今回ログイン時間
     */
    public void setCurrentLoginTmsp(Timestamp currentLoginTmsp) {
        this.currentLoginTmsp = currentLoginTmsp;
    }
    
    /**
     * 前回ログイン時間を取得します。
     * @return 前回ログイン時間
     */
    public Timestamp getLastLoginTmsp() {
        return lastLoginTmsp;
    }
    /**
     * 前回ログイン時間を設定します。
     * @param lastLoginTmsp 前回ログイン時間
     */
    public void setLastLoginTmsp(Timestamp lastLoginTmsp) {
        this.lastLoginTmsp = lastLoginTmsp;
    }
    
}
