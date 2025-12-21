/*
 * ì¬“ú: 2006/01/31
 *
 */
package jp.co.isid.mos.bird.common.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;

/**
 * @author xyuchida
 *
 */
public interface GetKoukaiTaishoLogic {

    public PublicTargetDto execute(String infoShu, String regDate, String seq);
}
