/*
 * ì¬“ú: 2006/03/03
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.dto;

/**
 * İ’è‹æ•ªDto
 * @author lee
 */
public class SeteiKubunDto {

	private String seteiKubun;

	private String userId;
	
	public void setSeteiKubun(final String seteiKubun) {
		this.seteiKubun = seteiKubun;
	}
	
	public String getSeteiKubun() {
		return this.seteiKubun;
	}
	
	public void setUserId(final String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}
}