/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.dto;

import java.util.List;

/**
 * @author xylee
 *
 */
public class RoleUserInfoDto {

	/**
     * ユーザロールリスト
     */
	private List userRoleList;
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
     * ユーザロールリストを取得します。
     * 
     * @return ユーザロールリスト
     */
	public List getUserRoleList() {
		return userRoleList;
	}

    /**
     * ユーザロールリストを設定します。
     * 
     * @param userRoleList ユーザロールリスト
     */
	public void setUserRoleList(List userRoleList) {
		this.userRoleList = userRoleList;
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
}
