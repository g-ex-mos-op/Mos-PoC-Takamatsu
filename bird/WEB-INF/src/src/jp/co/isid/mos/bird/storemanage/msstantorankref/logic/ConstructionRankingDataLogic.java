package jp.co.isid.mos.bird.storemanage.msstantorankref.logic;

import java.util.List;
import java.util.Map;


/**
 * ミステリーショッパーズ 担当店ランキング
 * データ構築ロジック インターフェース
 * @author xkinu
 *
 */
public interface ConstructionRankingDataLogic {
    /**
     * CSVデータ構築 実行処理
     * @param param
     * @return
     * @throws Exception
     */
    public List execute(Map param) throws Exception;

}
