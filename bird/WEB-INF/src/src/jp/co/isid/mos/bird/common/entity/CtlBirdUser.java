package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

public class CtlBirdUser {
    
    public static final String TABLE = "BR01USER";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String pswdUpdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String lastPswd_COLUMN = "LAST_PSWD";
    
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
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
    private byte[] userPswd;
    
    /**
     * パスワード更新日
     */
    private String pswdUpdtDt;
    
    /**
     * 前回パスワード
     */
    private byte[] lastPswd;
    
    /**
     * ユーザータイプ
     */
    private String usertypeCd;
    
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
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店区分
     */
    private String MiseKbn;
    
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
    public byte[] getUserPswd() {
        return userPswd;
    }
    /**
     * パスワードを設定します。
     * @param userPswd パスワード
     */
    public void setUserPswd(byte[] userPswd) {
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
     * @param pswdupdtDt パスワード更新日
     */
    public void setPswdUpdtDt(String pswdUpdtDt) {
        this.pswdUpdtDt = pswdUpdtDt;
    }
    
    /**
     * 前回パスワードを取得します。
     * @return 前回パスワード
     */
    public byte[] getLastPswd() {
        return lastPswd;
    }
    /**
     * 前回パスワードを設定します。
     * @param lastPswd 前回パスワード
     */
    public void setLastPswd(byte[] lastPswd) {
        this.lastPswd = lastPswd;
    }
    
    /**
     * ユーザータイプを取得します。
     * @return ユーザータイプ
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * ユーザータイプを設定します。
     * @param usertypeCd ユーザータイプ
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
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
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店区分を取得します。
     * @return 店区分
     */
    public String getMiseKbn() {
        return MiseKbn;
    }
    /**
     * 店区分を設定します。
     * @param MiseKbn 店区分
     */
    public void setMiseKbn(String MiseKbn) {
        this.MiseKbn = MiseKbn;
    }
    
}
