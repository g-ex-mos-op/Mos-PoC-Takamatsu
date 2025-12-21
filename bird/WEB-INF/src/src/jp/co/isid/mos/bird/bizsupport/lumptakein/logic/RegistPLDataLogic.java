/*
 * 作成日: 2006/03/20
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

/**
 * P/Lデータ登録ロジック
 * 
 * @author xyuchida
 */
public interface RegistPLDataLogic {

    /**
     * P/Lデータ登録
     * @param userId ユーザID
     * @param trnPLInfo P/Lデータエンティティ
     */
    public void execute(String userId, String sysDate, LumpTakeInDto lumpTakeInDto, TrnPLInfo trnPLInfo);
}
