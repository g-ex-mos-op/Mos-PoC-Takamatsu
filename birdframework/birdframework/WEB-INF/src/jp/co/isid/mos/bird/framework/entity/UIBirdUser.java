package jp.co.isid.mos.bird.framework.entity;

public class UIBirdUser {
    
    public static final String TABLE = "BR01USER";
    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String userNameKj_COLUMN = "USER_NAME_KJ";
    
    public static final String userNameKana_COLUMN = "USER_NAME_KANA";
    
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String bumonCd_COLUMN = "BUMON_CD";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * ユーザNAME
     */
    private String userNameKj;
    
    /**
     * ユーザNAMEカナ
     */
    private String userNameKana;
    
    /**
     * ユーザTYPEコード
     */
    private String usertypeCd;
    
    /**
     * 部門コード
     */
    private String bumonCd;

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
		return userNameKana;
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
}
