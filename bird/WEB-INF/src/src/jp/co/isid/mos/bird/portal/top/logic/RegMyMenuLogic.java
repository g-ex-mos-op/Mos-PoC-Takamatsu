/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.logic;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;

/**
 * çÏê¨ì˙:2008/12/18
 * @author xkinu
 *
 */
public interface RegMyMenuLogic {
	/**
	 * 
	 * @return
	 */
	public List add(BirdUserInfo birdUserInfo, PortalTopDto portalTopDto);
	/**
	 * 
	 * @return
	 */
	public List delete(BirdUserInfo birdUserInfo, PortalTopDto portalTopDto);
	
}
