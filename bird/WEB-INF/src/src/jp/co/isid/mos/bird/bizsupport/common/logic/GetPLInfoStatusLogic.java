package jp.co.isid.mos.bird.bizsupport.common.logic;

import java.util.List;

/**
 * P/Lデータ登録状況取得ロジック
 * 
 * @author Aspac
 */
public interface GetPLInfoStatusLogic {

    /**
     * P/Lデータ登録状況取得ロジック
     * @param plType P/Lの種類
     * @param onerCd オーナーコード]
     * @param closeMiseFlg true:店コードを含まない　false:含む
     * @return P/Lデータリスト
     */
    public List execute(String plYm, String onerCd, boolean closeMiseFlg);
}
