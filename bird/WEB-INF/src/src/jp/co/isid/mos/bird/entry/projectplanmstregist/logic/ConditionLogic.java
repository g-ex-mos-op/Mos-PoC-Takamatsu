package jp.co.isid.mos.bird.entry.projectplanmstregist.logic;

import java.util.List;
import java.util.Map;

/**
 * 事業方針説明会マスタ登録
 * 条件項目取得ロジックインターフェース
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
