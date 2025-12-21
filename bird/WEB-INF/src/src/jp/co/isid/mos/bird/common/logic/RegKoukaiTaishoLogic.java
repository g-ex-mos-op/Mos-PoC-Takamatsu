/*
 * ì¬“ú: 2006/02/01
 *
 */
package jp.co.isid.mos.bird.common.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;

/**
 * @author xyuchida
 *
 */
public interface RegKoukaiTaishoLogic {

    public void execute(PublicTargetDto publicTargetDto, String userId, String programId);
}
