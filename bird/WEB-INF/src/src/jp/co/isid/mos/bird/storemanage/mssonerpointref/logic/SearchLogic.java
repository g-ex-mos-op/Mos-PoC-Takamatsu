package jp.co.isid.mos.bird.storemanage.mssonerpointref.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;


/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 検索情報生成ロジック インターフェース
 * @author xkinu
 *
 */
public interface SearchLogic {
    /**
     * 照会データ検索処理実行
     * @param Map 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(Map param) throws Exception;

}
