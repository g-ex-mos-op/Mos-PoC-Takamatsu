/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic;

import java.util.List;

/**
 * P/Lデータ登録状況取得ロジック
 * 
 * @author xyuchida
 */
public interface GetPLInfoStatusLogic {

    /**
     * P/Lデータ登録状況取得ロジック
     * @param plType P/Lの種類
     * @param onerCd オーナーコード
     * @return P/Lデータリスト
     */
    public List execute(String plYm, String onerCd);
}
