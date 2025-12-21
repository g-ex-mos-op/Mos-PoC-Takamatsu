package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

public class CtlTogoUserRireki {
    
    public static final String TABLE = "IR51USER";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String userId_COLUMN = "USER_ID";
    
    public static final String hatsureiDt_COLUMN = "HATSUREI_DT";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String pswdUpdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String lastPswd_COLUMN = "LAST_PSWD";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 社員番号
     */
    private String userId;
    
    /**
     * 発令日
     */
    private String hatsureiDt;
    
    /**
     * 氏名
     */
    private String userNameKj;
    
    /**
     * パスワード
     */
    private String userPswd;
    
    /**
     * パスワード変更日
     */
    private String pswdUpdtDt;
    
    /**
     * 前回パスワード
     */
    private String lastPswd;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    /**
     * 社員番号を取得します。
     * @return 社員番号
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 社員番号を設定します。
     * @param userId 社員番号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 発令日を取得します。
     * @return 発令日
     */
    public String getHatsureiDt() {
        return hatsureiDt;
    }
    /**
     * 発令日を設定します。
     * @param hatsureiDt 発令日
     */
    public void setHatsureiDt(String hatsureiDt) {
        this.hatsureiDt = hatsureiDt;
    }
    
    /**
     * 氏名を取得します。
     * @return 氏名
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * 氏名を設定します。
     * @param userNameKj 氏名
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
     * パスワード変更日を取得します。
     * @return パスワード変更日
     */
    public String getPswdUpdtDt() {
        return pswdUpdtDt;
    }
    /**
     * パスワード変更日を設定します。
     * @param pswdUpdtDt パスワード変更日
     */
    public void setPswdUpdtDt(String pswdUpdtDt) {
        this.pswdUpdtDt = pswdUpdtDt;
    }
    
    /**
     * 前回パスワードを取得します。
     * @return 前回パスワード
     */
    public String getLastPswd() {
        return lastPswd;
    }
    /**
     * 前回パスワードを設定します。
     * @param lastPswd 前回パスワード
     */
    public void setLastPswd(String lastPswd) {
        this.lastPswd = lastPswd;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
