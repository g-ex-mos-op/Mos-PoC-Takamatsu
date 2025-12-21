/*
 * 作成日: 2006/03/15
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;

/**
 * P/Lデータチェックロジック
 * 
 * @author xyuchida
 */
public interface CheckPLDataLogic {

    /**
     * P/Lデータチェック
     * @param P/Lデータ
     */
    public void execute(TrnPLInfo trnPLInfo);
}
