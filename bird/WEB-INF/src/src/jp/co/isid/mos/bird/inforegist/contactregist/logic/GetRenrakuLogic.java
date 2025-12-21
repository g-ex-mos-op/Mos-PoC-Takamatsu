/*
 * ì¬“ú: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * @author xyuchida
 *
 */
public interface GetRenrakuLogic {
	/**
	 * ŒŸõ˜A—î•ñæ“¾ˆ—
	 * 
	 * @param pubDate
	 * @param cateId
	 * @param mstUser
	 * @param limit
	 * @param offset
	 * @return
	 */
    public List execute(String pubDate, String cateId, MstUser mstUser, int limit, int offset);
}
