/*
 * ì¬“ú: 2006/02/27
 *
*/
package jp.co.isid.mos.bird.sysadmin.help.logic;

import jp.co.isid.mos.bird.sysadmin.help.dto.UserMenuDto;

/**
 * @author xylee
 *
 */
public interface SearchMenuLogic {

	/**
	 * 
	 * @param userId
	 * @param userMenuDto
	 * @param viewId
	 */
	public void execute(String userId, UserMenuDto userMenuDto, String viewId);

}
