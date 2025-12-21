/*
 * 作成日: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.entity;

/**
 * @author 慮
 *
 */
public class UIViewUser {
	
    public static final String TABLE = "BR01USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String bumonName_COLUMN = "BUMON_NAME";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    public static final String userPswd_COLUMN = "USER_PSWD";
    
    public static final String pswdupdtDt_COLUMN = "PSWD_UPDT_DT";
    
    public static final String lastPswd_COLUMN = "LAST_PSWD";
    
    public static final String sekyuFlg_COLUMN = "SEKYU_FLG";
    
    public static final String mailAdd_COLUMN = "MAIL_ADD";
    
    public static final String apploedDt_COLUMN = "APPLIED_DT";
    
    public static final String seikyuDt_COLUMN = "SEKYU_DT";
    
    public static final String seikyuUpdtDt_COLUMN = "SEKYU_UPDT_DT";
    
    public static final String limitKbn_COLUMN = "LIMIT_KBN";
    
    public static final String stopFlg_COLUMN = "STOP_FLG";
    
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
     * 部門コード名
     */
	private String bumonName;
	
	/**
     * パスワード
     */
	private String userPswd;
	
	/**
     * パスワード更新日
     */
	private String pswdupdtDt;
	
	/**
     * 前回パスワード
     */
	private String lastPswd;
	
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
	private String apploedDt;
	
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
	 * apploedDt 申込日を取得します
	 * @return apploedDt 申込日
	 */
	public String getApploedDt() {
		return apploedDt;
	}
	/**
	 * apploedDt 申込日を設定します。
	 * @param apploedDt 申込日
	 */
	public void setApploedDt(String apploedDt) {
		this.apploedDt = apploedDt;
	}
	/**
	 * bumonCd　部門コードを取得します。　
	 * @return bumonCd 部門コード
	 */
	public String getBumonCd() {
		return bumonCd;
	}
	/**
	 * apploedDt apploedDt を設定します。
	 * @param apploedDt 申込日
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}
	/**
	 * bumonName　部門コード名を取得します。　
	 * @return bumonName 部門コード名
	 */
	public String getBumonName() {
		return bumonName;
	}
	/**
	 * bumonName 部門コード名を設定します。
	 * @param bumonName 部門コード名
	 */
	public void setBumonName(String bumonName) {
		this.bumonName = bumonName;
	}
	/**
	 * lastPswd　前回パスワードを取得します。　
	 * @return lastPswd 前回パスワード
	 */
	public String getLastPswd() {
		return lastPswd;
	}
	/**
	 * lastPswd 前回パスワードを設定します。
	 * @param lastPswd 前回パスワード
	 */
	public void setLastPswd(String lastPswd) {
		this.lastPswd = lastPswd;
	}
	/**
	 * limitKbn　制限区分を取得します。　
	 * @return limitKbn 制限区分
	 */
	public String getLimitKbn() {
		return limitKbn;
	}
	/**
	 * limitKbn 制限区分を設定します。
	 * @param limitKbn 制限区分
	 */
	public void setLimitKbn(String limitKbn) {
		this.limitKbn = limitKbn;
	}
	/**
	 * mailAdd　メールアドレスを取得します。　
	 * @return mailAdd メールアドレス
	 */
	public String getMailAdd() {
		return mailAdd;
	}
	/**
	 * mailAdd メールアドレスを設定します。
	 * @param mailAdd メールアドレス
	 */
	public void setMailAdd(String mailAdd) {
		this.mailAdd = mailAdd;
	}
	/**
	 * pswdupdtDt　パスワード更新日を取得します。　
	 * @return pswdupdtDt パスワード更新日
	 */
	public String getPswdupdtDt() {
		return pswdupdtDt;
	}
	/**
	 * pswdupdtDt パスワード更新日を設定します。
	 * @param pswdupdtDt パスワード更新日
	 */
	public void setPswdupdtDt(String pswdupdtDt) {
		this.pswdupdtDt = pswdupdtDt;
	}
	/**
	 * seikyuDt　請求開始日を取得します。　
	 * @return seikyuDt 請求開始日
	 */
	public String getSeikyuDt() {
		return seikyuDt;
	}
	/**
	 * seikyuDt 請求開始日を設定します。
	 * @param seikyuDt 請求開始日
	 */
	public void setSeikyuDt(String seikyuDt) {
		this.seikyuDt = seikyuDt;
	}
	/**
	 * seikyuUpdtDt　請求変更予定日を取得します。　
	 * @return seikyuUpdtDt 請求変更予定日
	 */
	public String getSeikyuUpdtDt() {
		return seikyuUpdtDt;
	}
	/**
	 * seikyuUpdtDt 請求変更予定日を設定します。
	 * @param seikyuUpdtDt 請求変更予定日
	 */
	public void setSeikyuUpdtDt(String seikyuUpdtDt) {
		this.seikyuUpdtDt = seikyuUpdtDt;
	}
	/**
	 * sekyuFlg　請求フラグを取得します。　
	 * @return sekyuFlg 請求フラグ
	 */
	public String getSekyuFlg() {
		return sekyuFlg;
	}
	/**
	 * sekyuFlg 請求フラグを設定します。
	 * @param sekyuFlg 請求フラグ
	 */
	public void setSekyuFlg(String sekyuFlg) {
		this.sekyuFlg = sekyuFlg;
	}
	/**
	 * stopFlg　使用停止フラグを取得します。　
	 * @return stopFlg 使用停止フラグ
	 */
	public String getStopFlg() {
		return stopFlg;
	}
	/**
	 * stopFlg 使用停止フラグを設定します。
	 * @param stopFlg 使用停止フラグ
	 */
	public void setStopFlg(String stopFlg) {
		this.stopFlg = stopFlg;
	}
	/**
	 * userId　ユーザIDを取得します。　
	 * @return userId ユーザID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId ユーザIDを設定します。
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * userNameKana　ユーザ名称(カナ)を取得します。　
	 * @return userNameKana ユーザ名称(カナ)
	 */
	public String getUserNameKana() {
		return userNameKana;
	}
	/**
	 * userNameKana ユーザ名称(カナ)を設定します。
	 * @param userNameKana ユーザ名称(カナ)
	 */
	public void setUserNameKana(String userNameKana) {
		this.userNameKana = userNameKana;
	}
	/**
	 * userNameKj　ユーザ名称を取得します。　
	 * @return userNameKj ユーザ名称
	 */
	public String getUserNameKj() {
		return userNameKj;
	}
	/**
	 * userNameKj ユーザ名称を設定します。
	 * @param userNameKj ユーザ名称
	 */
	public void setUserNameKj(String userNameKj) {
		this.userNameKj = userNameKj;
	}
	/**
	 * userPswd　パスワードを取得します。　
	 * @return userPswd パスワード
	 */
	public String getUserPswd() {
		return userPswd;
	}
	/**
	 * userPswd パスワードを設定します。
	 * @param userPswd パスワード
	 */
	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}
	/**
	 * usertypeCd　ユーザタイプを取得します。　
	 * @return usertypeCd ユーザタイプ
	 */
	public String getUsertypeCd() {
		return usertypeCd;
	}
	/**
	 * usertypeCd ユーザタイプを設定します。
	 * @param usertypeCd ユーザタイプ
	 */
	public void setUsertypeCd(String usertypeCd) {
		this.usertypeCd = usertypeCd;
	}
}
