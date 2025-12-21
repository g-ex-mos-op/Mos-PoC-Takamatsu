package jp.co.isid.mos.bird.framework.dto;

import java.util.List;


/**
 * ユーザリストDTO
 * @author xnlee
 */
public class BirdUserListDto {

	
    /* ユーザLIST */
    private List userList;
	
	/**
	 * @return userList を戻します。
	 */
	public List getUserList() {
		return userList;
	}
	/**
	 * @param userList userList を設定。
	 */
	public void setUserList(List userList) {
		this.userList = userList;
	}

}
