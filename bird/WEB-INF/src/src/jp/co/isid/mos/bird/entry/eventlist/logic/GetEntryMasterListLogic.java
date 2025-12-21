/**
 * 
 */
package jp.co.isid.mos.bird.entry.eventlist.logic;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public interface GetEntryMasterListLogic {

    public List execute(List entryCdList, String bunrui, String usertypeCd, String sysDate);
}
