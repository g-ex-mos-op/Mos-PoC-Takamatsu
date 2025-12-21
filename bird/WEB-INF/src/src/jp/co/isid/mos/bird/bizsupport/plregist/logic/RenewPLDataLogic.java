/*
 * 作成日: 2006/3/20
 */
package jp.co.isid.mos.bird.bizsupport.plregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;

/**
 * PLデータ更新ロジックインターフェース
 * @author itamoto
 */
public interface RenewPLDataLogic {

    /**
     * PLデータを更新する
     * @param trnPLInfo
     * @param plRegistDto
     */
    public List execute(TrnPLInfo trnPLInfo, PlRegistDto plRegistDto);
}
