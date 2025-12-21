/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;

/**
 * 連絡情報の更新ロジック
 * @author xyuchida
 */
public interface RenewalRenrakuLogic {

    /**
     * 連絡情報の更新ロジック
     * @param contactRegistDto
     * @param publicTargetDto
     */
    public void execute(ContactRegistDto contactRegistDto, PublicTargetDto publicTargetDto);
}
