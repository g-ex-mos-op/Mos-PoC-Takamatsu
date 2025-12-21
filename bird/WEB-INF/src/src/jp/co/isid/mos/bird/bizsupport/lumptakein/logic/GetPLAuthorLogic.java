/*
 * 作成日: 2006/03/17
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLAuthorInfo;

/**
 * P/L作成者情報取得ロジック
 * 
 * @author xyuchida
 */
public interface GetPLAuthorLogic {

    /**
     * P/L作成者情報取得
     * @param　onerCd オーナーコード
     * @return P/L作成者情報
     */
    public TrnPLAuthorInfo execute(String onerCd);
}
