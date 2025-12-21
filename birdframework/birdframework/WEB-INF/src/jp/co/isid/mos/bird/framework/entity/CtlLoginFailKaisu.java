package jp.co.isid.mos.bird.framework.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CtlLoginFailKaisu {
    
    public static final String TABLE = "IR05FAIL";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String loginFail_COLUMN = "LOGIN_FAIL";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * ユーザーID
     */
    private String userId;
    
    /**
     * ログイン失敗回数
     */
    private BigDecimal loginFail;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新ＰＧＭ
     */
    private String lastPgm;
    
    /**
     * 更新ＴＭＳＰ
     */
    private Timestamp lastTmsp;
    
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
     * ログイン失敗回数を取得します。
     * @return ログイン失敗回数
     */
    public BigDecimal getLoginFail() {
        return loginFail;
    }
    /**
     * ログイン失敗回数を設定します。
     * @param loginFail ログイン失敗回数
     */
    public void setLoginFail(BigDecimal loginFail) {
        this.loginFail = loginFail;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新ＰＧＭを取得します。
     * @return 更新ＰＧＭ
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新ＰＧＭを設定します。
     * @param lastPgm 更新ＰＧＭ
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新ＴＭＳＰを取得します。
     * @return 更新ＴＭＳＰ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新ＴＭＳＰを設定します。
     * @param lastTmsp 更新ＴＭＳＰ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
