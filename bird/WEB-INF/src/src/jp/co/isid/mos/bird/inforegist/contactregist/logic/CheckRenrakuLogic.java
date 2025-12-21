/*
 * çÏê¨ì˙: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;

/**
 * @author xyuchida
 *
 */
public interface CheckRenrakuLogic {

    public void execute(ContactRegistDto contactRegistDto, PublicTargetDto publicTargetDto);
}
