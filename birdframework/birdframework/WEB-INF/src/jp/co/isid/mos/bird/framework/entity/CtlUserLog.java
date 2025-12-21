package jp.co.isid.mos.bird.framework.entity;

import java.sql.Timestamp;


public class CtlUserLog {
    
    public static final String TABLE = "BR81ULGR";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String loginTmsp_COLUMN = "LOGIN_TMSP";
    
    public static final String loginDt_COLUMN = "LOGIN_DT";
    
    public static final String remoteAddr_COLUMN = "REMOTE_ADDR";
    
    public static final String userAgent_COLUMN = "USER_AGENT";
    
    public static final String appId_COLUMN = "APP_ID";
    
    /**
     * ユーザーID
     */
    private String userId;
    
    /**
     * ログイン時間
     */
    private Timestamp loginTmsp;
    
    /**
     * ログイン日
     */
    private String loginDt;
    
    /**
     * リモートIPアドレス
     */
    private String remoteAddr;
    
    /**
     * 端末情報
     */
    private String userAgent;
    
    /**
     * サイト区分
     */
    private String appId;
    
    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * ログイン時間を取得します。
     * @return ログイン時間
     */
    public Timestamp getLoginTmsp() {
        return loginTmsp;
    }
    /**
     * ログイン時間を設定します。
     * @param loginTmsp ログイン時間
     */
    public void setLoginTmsp(Timestamp loginTmsp) {
        this.loginTmsp = loginTmsp;
    }
    
    /**
     * ログイン日を取得します。
     * @return ログイン日
     */
    public String getLoginDt() {
        return loginDt;
    }
    /**
     * ログイン日を設定します。
     * @param loginDt ログイン日
     */
    public void setLoginDt(String loginDt) {
        this.loginDt = loginDt;
    }
    
    /**
     * リモートIPアドレスを取得します。
     * @return リモートIPアドレス
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }
    /**
     * リモートIPアドレスを設定します。
     * @param remoteAddr リモートIPアドレス
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    
    /**
     * 端末情報を取得します。
     * @return 端末情報
     */
    public String getUserAgent() {
        return userAgent;
    }
    /**
     * 端末情報を設定します。
     * @param userAgent 端末情報
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    
    /**
     * サイト区分を取得します。
     * @return サイト区分
     */
    public String getAppId() {
        return appId;
    }
    /**
     * サイト区分を設定します。
     * @param appId サイト区分
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
}
