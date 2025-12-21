package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

public class CtlUserKakoPW {
    
    public static final String TABLE = "IR52PASS";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String userId_COLUMN = "USER_ID";
    
    public static final String pswdUpdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String lastPswd_COLUMN = "LAST_PSWD";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String seqNo_COLUMN = "SEQ_NO";
    
    /**
     * 社員番号
     */
    private String userId;
    
    /**
     * パスワード更新日
     */
    private String pswdUpdtDt;
    
    /**
     * パスワード
     */
    private byte[] userPswd;
    
    /**
     * 前回パスワード
     */
    private byte[] lastPswd;
    
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
     * パスワードSEQ_NO
     */
    private int seqNo;
    
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
    public int getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
    
}
