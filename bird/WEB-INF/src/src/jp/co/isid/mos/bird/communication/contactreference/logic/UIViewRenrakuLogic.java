/*
 * çÏê¨ì˙: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.logic;

import java.util.List;

import jp.co.isid.mos.bird.communication.contactreference.dto.ContactReferenceDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * @author xyuchida
 *
 */
public interface UIViewRenrakuLogic {

    /**
     * 
     * @param birdUserInfo
     * @param contactReference
     * @param sysDate
     * @return
     */
    public List execute(BirdUserInfo birdUserInfo, ContactReferenceDto contactReference, String sysDate);
    
}
