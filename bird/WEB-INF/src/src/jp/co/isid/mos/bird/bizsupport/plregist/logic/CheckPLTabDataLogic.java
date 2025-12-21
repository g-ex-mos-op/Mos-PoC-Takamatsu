/*
 * 作成日: 2006/3/22
 */
package jp.co.isid.mos.bird.bizsupport.plregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.TrnPLInfoDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;

/**
 * タブ内容チェックロジックインターフェース
 * @author itamoto
 */
public interface CheckPLTabDataLogic {

    /**
     * タブ内容の整合性チェックを行う。
     * @param trnPLInfo
     * @param trnZenPLInfo
     * @param plRegistDto
     * @param trnPLInfoDto
     */
    public List execute(TrnPLInfo trnPLInfo, TrnZenPLInfo trnZenPLInfo,
			PlRegistDto plRegistDto, TrnPLInfoDto trnPLInfoDto);
}
