package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

import java.sql.Timestamp;

public class TrnStaffID {
    
    public static final String TABLE = "BR37STCT";
    
    public static final String noCounter_COLUMN = "NO_COUNTER";
    
    public static final String cntShoki_COLUMN = "CNT_SHOKI";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    /**
     * スタッフIDカウンタ
     */
    private String noCounter;
    
    /**
     * スタッフIDカウンタ初期値
     */
    private String cntShoki;
    
    /**
     * 更新ユーザー
     */
    private String lastUser;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * スタッフIDカウンタを取得します。
     * @return スタッフIDカウンタ
     */
    public String getNoCounter() {
        return convString(noCounter);
    }
    /**
     * スタッフIDカウンタを設定します。
     * @param noCounter スタッフIDカウンタ
     */
    public void setNoCounter(String noCounter) {
        this.noCounter = noCounter;
    }
    
    /**
     * スタッフIDカウンタ初期値を取得します。
     * @return スタッフIDカウンタ初期値
     */
    public String getCntShoki() {
        return convString(cntShoki);
    }
    /**
     * スタッフIDカウンタ初期値を設定します。
     * @param cntShoki スタッフIDカウンタ初期値
     */
    public void setCntShoki(String cntShoki) {
        this.cntShoki = cntShoki;
    }
    
    /**
     * 更新ユーザーを取得します。
     * @return 更新ユーザー
     */
    public String getLastUser() {
        return convString(lastUser);
    }
    /**
     * 更新ユーザーを設定します。
     * @param lastUser 更新ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return convString(lastPgm);
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
	/**
	 * @return firstPgm を戻します。
	 */
	public String getFirstPgm() {
		return firstPgm;
	}
	/**
	 * @param firstPgm firstPgm を設定。
	 */
	public void setFirstPgm(String firstPgm) {
		this.firstPgm = firstPgm;
	}
	/**
	 * @return firstTmsp を戻します。
	 */
	public Timestamp getFirstTmsp() {
		return firstTmsp;
	}
	/**
	 * @param firstTmsp firstTmsp を設定。
	 */
	public void setFirstTmsp(Timestamp firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	/**
	 * @return firstUser を戻します。
	 */
	public String getFirstUser() {
		return firstUser;
	}
	/**
	 * @param firstUser firstUser を設定。
	 */
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
}
