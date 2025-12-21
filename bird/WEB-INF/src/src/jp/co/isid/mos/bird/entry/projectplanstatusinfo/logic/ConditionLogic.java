package jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic;

import java.util.Map;

/**
 * 事業方針説明会申込状況確認
 * 条件項目取得ロジックインターフェース
 * 
 * 対象条件の『支部』選択時の支部情報を取得します。
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
    public Map execute(Map params);
}
