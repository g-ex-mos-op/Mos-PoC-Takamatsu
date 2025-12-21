/*
 * 作成日: 2006/03/13
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;

/**
 * CSV一括取込ロジック
 * 
 * @author xyuchida
 */
public interface TakePlInfoCsvLogic {

    /**
     * P/LデータCSV取込ロジック
     * @param lumpTakeInDto CSV一括取込DTO
     * @return P/Lデータリスト
     */
    public List execute(LumpTakeInDto lumpTakeInDto);
}
