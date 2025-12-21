package jp.co.isid.mos.bird.bizsupport.moschickenstorestateconfirm.logic;

import java.util.List;
import java.util.Map;

/**
 * 店別予約状況一覧(モスチキン用画面）
 * 条件項目情報の取得
 * ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface ConditionLogic {

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);
}
