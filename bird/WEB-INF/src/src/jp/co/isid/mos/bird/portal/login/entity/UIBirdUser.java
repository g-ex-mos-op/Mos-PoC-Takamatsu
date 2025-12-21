package jp.co.isid.mos.bird.portal.login.entity;

import java.sql.Timestamp;

/**
 * BIRDユーザーEntity
 * @author xnkusama
 */
public class UIBirdUser {
    
    public static final String TABLE = "BR01USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String pswdUpdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String lastPswd_COLUMN = "LAST_PSWD";
    
    public static final String stopFlg_COLUMN = "STOP_FLG";

    public static final String lastUser_COLUMN = "LAST_USER";

    public static final String lastPgm_COLUMN = "LAST_PGM";

    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ユーザー名称漢字
     */
    private String userNameKj;
    
    /**
     * 部門コード
     */
    private String bumonCd;
    
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
     * 使用停止フラグ
     */
    private String stopFlg;

    /**
     * 最終更新ユーザー
     */
    private String lastUser;

    /**
     * 最終更新プログラム
     */
    private String lastPgm;

    /**
     * 最終更新日
     */
    private Timestamp lastTmsp;

    /**
     * @return bumonCd を戻します。
     */
    public String getBumonCd() {
        return bumonCd;
    }
    /**
     * @param bumonCd bumonCd を設定。
     */
    public void setBumonCd(String bumonCd) {
        this.bumonCd = bumonCd;
    }
    /**
     * @return lastPswd を戻します。
     */
    public byte[] getLastPswd() {
        return lastPswd;
    }
    /**
     * @param lastPswd lastPswd を設定。
     */
    public void setLastPswd(byte[] lastPswd) {
        this.lastPswd = lastPswd;
    }
    /**
     * @return pswdUpdtDt を戻します。
     */
    public String getPswdUpdtDt() {
        return pswdUpdtDt;
    }
    /**
     * @param pswdUpdtDt pswdUpdtDt を設定。
     */
    public void setPswdUpdtDt(String pswdUpdtDt) {
        this.pswdUpdtDt = pswdUpdtDt;
    }
    /**
     * @return stopFlg を戻します。
     */
    public String getStopFlg() {
        return stopFlg;
    }
    /**
     * @param stopFlg stopFlg を設定。
     */
    public void setStopFlg(String stopFlg) {
        this.stopFlg = stopFlg;
    }
    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return userNameKj を戻します。
     */
    public String getUserNameKj() {
        return userNameKj;
    }
    /**
     * @param userNameKj userNameKj を設定。
     */
    public void setUserNameKj(String userNameKj) {
        this.userNameKj = userNameKj;
    }
    /**
     * @return userPswd を戻します。
     */
    public byte[] getUserPswd() {
        return userPswd;
    }
    /**
     * @param userPswd userPswd を設定。
     */
    public void setUserPswd(byte[] userPswd) {
        this.userPswd = userPswd;
    }
	public String getLastPgm() {
		return lastPgm;
	}
	public void setLastPgm(String lastPgm) {
		this.lastPgm = lastPgm;
	}
	public Timestamp getLastTmsp() {
		return lastTmsp;
	}
	public void setLastTmsp(Timestamp lastTmsp) {
		this.lastTmsp = lastTmsp;
	}
	public String getLastUser() {
		return lastUser;
	}
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
}
