/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;

import java.sql.Timestamp;

/**
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public class UIUser {


    public static final String TABLE = "BR01USER";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    public static final String bumonName_COLUMN = "BMN_NAME";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String pswdupdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String lastPswd_COLUMN = "LAST_PSWD";
    
    public static final String sekyuFlg_COLUMN = "SEKYU_FLG";
    
    public static final String mailAdd_COLUMN = "MAIL_ADD";
    
    public static final String appliedDt_COLUMN = "APPLIED_DT";
    
    public static final String seikyuDt_COLUMN = "SEKYU_DT";
    
    public static final String seikyuUpdtDt_COLUMN = "SEKYU_UPDT_DT";
    
    public static final String limitKbn_COLUMN = "LIMIT_KBN";
    
    public static final String stopFlg_COLUMN = "STOP_FLG";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
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
     * ユーザ名称(カナ)
     */
	private String userNameKana;
	
	/**
     * ユーザタイプコード
     */
	private String usertypeCd;
	
	/**
     * 部門コード
     */
	private String bumonCd;
	/**
     * 部門名
     */
	private String bumonName;
	
	/**
     * パスワード
     */
	private byte[] userPswd;
	
	/**
     * パスワード更新日
     */
	private String pswdupdtDt;
	
	/**
     * 前回パスワード
     */
	private byte[] lastPswd;
	
	/**
     * 請求フラグ
     */
	private String sekyuFlg;
	
	/**
     * メールアドレス
     */
	private String mailAdd;
	
	/**
     * 申込日
     */
	private String appliedDt;
	
	/**
     * 請求開始日
     */
	private String seikyuDt;
	
	/**
     * 請求変更予定日
     */
	private String seikyuUpdtDt;
	
	/**
     * 制限区分
     */
	private String limitKbn;
	
	/**
     * 使用停止フラグ
     */
	private String stopFlg;
	
	/**
     * 登録ユーザー
     */
	private String firstUser;
	
	/**
     * 登録プログラム
     */
	private String firstPgm;
	
	/**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
	private Timestamp firstTmsp;
	
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
	 * @return appliedDt を戻します。
	 */
	public String getAppliedDt() {
		return appliedDt==null?"":appliedDt;
	}
	/**
	 * @param appliedDt appliedDt を設定。
	 */
	public void setAppliedDt(String appliedDt) {
		this.appliedDt = appliedDt;
	}
	/**
	 * @return bumonCd を戻します。
	 */
	public String getBumonCd() {
		return bumonCd==null?"":bumonCd;
	}
	/**
	 * @param bumonCd bumonCd を設定。
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
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
	/**
	 * @return lastPgm を戻します。
	 */
	public String getLastPgm() {
		return lastPgm;
	}
	/**
	 * @param lastPgm lastPgm を設定。
	 */
	public void setLastPgm(String lastPgm) {
		this.lastPgm = lastPgm;
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
	 * @return lastUser を戻します。
	 */
	public String getLastUser() {
		return lastUser;
	}
	/**
	 * @param lastUser lastUser を設定。
	 */
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	/**
	 * @return limitKbn を戻します。
	 */
	public String getLimitKbn() {
		return limitKbn==null?"":limitKbn;
	}
	/**
	 * @param limitKbn limitKbn を設定。
	 */
	public void setLimitKbn(String limitKbn) {
		this.limitKbn = limitKbn;
	}
	/**
	 * @return mailAdd を戻します。
	 */
	public String getMailAdd() {
		return mailAdd==null?"":mailAdd;
	}
	/**
	 * @param mailAdd mailAdd を設定。
	 */
	public void setMailAdd(String mailAdd) {

		this.mailAdd = mailAdd;
	}
	/**
	 * @return pswdupdtDt を戻します。
	 */
	public String getPswdupdtDt() {
		return pswdupdtDt;
	}
	/**
	 * @param pswdupdtDt pswdupdtDt を設定。
	 */
	public void setPswdupdtDt(String pswdupdtDt) {
		this.pswdupdtDt = pswdupdtDt;
	}
	/**
	 * @return seikyuDt を戻します。
	 */
	public String getSeikyuDt() {
		return seikyuDt==null?"":seikyuDt;
	}
	/**
	 * @param seikyuDt seikyuDt を設定。
	 */
	public void setSeikyuDt(String seikyuDt) {
		this.seikyuDt = seikyuDt;
	}
	/**
	 * @return seikyuUpdtDt を戻します。
	 */
	public String getSeikyuUpdtDt() {
		return seikyuUpdtDt==null?"":seikyuUpdtDt;
	}
	/**
	 * @param seikyuUpdtDt seikyuUpdtDt を設定。
	 */
	public void setSeikyuUpdtDt(String seikyuUpdtDt) {
		this.seikyuUpdtDt = seikyuUpdtDt;
	}
	/**
	 * @return sekyuFlg を戻します。
	 */
	public String getSekyuFlg() {
		return sekyuFlg==null?"":sekyuFlg;
	}
	/**
	 * @param sekyuFlg sekyuFlg を設定。
	 */
	public void setSekyuFlg(String sekyuFlg) {
		this.sekyuFlg = sekyuFlg;
	}
	/**
	 * @return stopFlg を戻します。
	 */
	public String getStopFlg() {
		return stopFlg==null?"":stopFlg;
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
	 * @return userNameKana を戻します。
	 */
	public String getUserNameKana() {
		if(userNameKana == null){
            return "";
        }
        return userNameKana.trim();
	}
	/**
	 * @param userNameKana userNameKana を設定。
	 */
	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
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
	/**
	 * @return usertypeCd を戻します。
	 */
	public String getUsertypeCd() {
		return usertypeCd;
	}
	/**
	 * @param usertypeCd usertypeCd を設定。
	 */
	public void setUsertypeCd(String usertypeCd) {
		this.usertypeCd = usertypeCd;
	}
	/**
	 * @return bumonName を戻します。
	 */
	public String getBumonName() {
		return bumonName;
	}
	/**
	 * @param bumonName bumonName を設定。
	 */
	public void setBumonName(String bumonName) {
		this.bumonName = bumonName;
	}
    /**
     * @return firstTmsp を戻します。
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * @param firstTmsp 設定する firstTmsp。
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    /**
     * @return lastTmsp を戻します。
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * @param lastTmsp 設定する lastTmsp。
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
}
