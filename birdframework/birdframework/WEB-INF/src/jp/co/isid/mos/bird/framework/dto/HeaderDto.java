/*
 * 作成日: 2006/02/01
 *
 */
package jp.co.isid.mos.bird.framework.dto;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.framework.util.formatter.TimestampFormatter;

/**
 * ヘッダー情報を保持します。
 * @author xytamura
 *
 */
public class HeaderDto {
    /**
     * ユーザＩＤ
     */
    private String userId;
    
    /**
     * ユーザ名称
     */
    private String userNameKj;
    
    /**
     * 最終ログイン時間
     */
    private Timestamp lastLogin;
    
    private String addMyMenuMsg = "メニューのトップ（条件を設定する画面）が登録されます。";
        
    /**
     * 最終ログイン時間を取得します。
     * @return 最終ログイン時間
     */
    public Timestamp getLastLogin() {
        return lastLogin;
    }
    
    /**
     * 最終ログイン時間を設定します。
     * @param 最終ログイン時間
     */
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    /**
     * ユーザＩＤを取得します。
     * @return userid ユーザＩＤ
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * ユーザＩＤを設定します。
     * @param userid userid ユーザＩＤ
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
     * ログイン時間(表示用)を取得します。
     * @return　ログイン時間(表示用)
     */    
    public String getLoginTime(){
        TimestampFormatter f = new TimestampFormatter(TimestampFormatter.ALL);
        return f.format(getLastLogin(), true);
    }

    public String getAddMyMenuMsg() {
        return addMyMenuMsg;
    }

    public void setAddMyMenuMsg(String addMyMenuMsg) {
        this.addMyMenuMsg = addMyMenuMsg;
    }
}
