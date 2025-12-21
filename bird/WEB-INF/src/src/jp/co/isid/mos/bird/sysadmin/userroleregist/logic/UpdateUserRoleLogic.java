/*
 * ì¬“ú: 2006/02/21
 *
*/
package jp.co.isid.mos.bird.sysadmin.userroleregist.logic;

import java.util.List;

/**
 * @author xylee
 *
 */
public interface UpdateUserRoleLogic {

	/**
	 * @param userId
	 * @return
	 */
	public void execute(String userId, String firstUser, List userRole);

}