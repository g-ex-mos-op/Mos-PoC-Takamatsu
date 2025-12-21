/*
 * ì¬“ú: 2006/03/02
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic;

import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * @author xyuchida
 *
 */
public interface GetRenrakuCountLogic {
	/**
	 * ŒŸõ˜A—î•ñŒ”æ“¾ˆ—
	 * 
	 * @param pubDate
	 * @param cateId
	 * @param mstUser
	 * @return
	 */
    public int execute(String pubDate, String cateId, MstUser mstUser);
}
