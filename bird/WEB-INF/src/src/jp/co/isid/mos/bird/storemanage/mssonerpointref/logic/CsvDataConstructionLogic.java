package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic;

import java.util.List;
import java.util.Map;


/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * CSVデータ構築ロジック インターフェース
 * @author xkinu
 *
 */
public interface CsvDataConstructionLogic {
    /**
     * CSVデータ構築 実行処理
     * @param param
     * @return
     * @throws Exception
     */
    public List execute(Map param) throws Exception;

}
