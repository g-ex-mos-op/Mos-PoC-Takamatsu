package jp.co.isid.mos.bird.framework.entity;

import java.io.Serializable;


public class CtlSessionBridge implements Serializable {
    
    public static final long serialVersionUID = 1l;
    
    public static final String TABLE = "BR07SBIF";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String sessionKey_COLUMN = "SESSION_KEY";
    
    public static final String loginTmsp_COLUMN = "LOGIN_TMSP";
    
    private String userId;
    private String sessionKey;
    private String loginTmsp;
    
    /**
     * ユーザーＩＤを取得します。
     * @return ユーザーＩＤ
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーＩＤを設定します。
     * @param userId ユーザーＩＤ
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * セッションキーを取得します。
     * @return セッションキー
     */
    public String getSessionKey() {
        return sessionKey;
    }
    /**
     * セッションキーを設定します。
     * @param String セッションキー
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
    
    /**
     * 作成タイムスタンプを取得します。
     * @return 作成タイムスタンプ
     */
    public String getCreateTmsp() {
        return loginTmsp;
    }
    /**
     * 作成タイムスタンプを設定します。
     * @param subMenuId 作成タイムスタンプ
     */
    public void setLoginTmsp(String loginTmsp) {
        this.loginTmsp = loginTmsp;
    }
}
