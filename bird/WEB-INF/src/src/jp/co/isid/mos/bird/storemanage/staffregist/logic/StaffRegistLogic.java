/*
 * ì¬“ú: 2006/04/08
 *
*/
package jp.co.isid.mos.bird.storemanage.staffregist.logic;

import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistDto;

/**
 * @author xylee
 *
 */
public interface StaffRegistLogic {

	/**
     * @param staffRegistDto
	 * @param userId
	 * @return
	 */
	public void execute(StaffRegistDto staffRegistDto, String userId);
}