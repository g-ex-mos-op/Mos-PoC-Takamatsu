/**
 * 
 */
package jp.co.isid.mos.bird.framework.logic;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * ŠO•”ƒVƒXƒeƒ€(•“o˜^Œn)o—ÍƒƒO“o˜^ˆ—
 * 
 * ì¬“ú:2009/01/13
 * @author xkinu
 *
 */
public interface RegOutputLogLogic {
	/**
	 * Àsˆ—
	 * 
	 * @param request
	 * @param birdUserInfo
	 * @param linkId
	 * @return
	 */
	public int execute(final HttpServletRequest request, final BirdUserInfo birdUserInfo, final String linkId);
}
