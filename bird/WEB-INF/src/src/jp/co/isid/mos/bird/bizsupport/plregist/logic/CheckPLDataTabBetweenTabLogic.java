/*
 * 作成日: 2006/3/22
 */
package jp.co.isid.mos.bird.bizsupport.plregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;

/**
 * タブ間の整合性チェックロジックインターフェース
 * @author itamoto
 */
public interface CheckPLDataTabBetweenTabLogic {

    /**
     * タブ間の整合性チェックを行う。
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto
     */
    public List execute(TrnPLInfo trnPLInfo, TrnZenPLInfo trnZenPLInfo,
			PlRegistDto plRegistDto);
}
