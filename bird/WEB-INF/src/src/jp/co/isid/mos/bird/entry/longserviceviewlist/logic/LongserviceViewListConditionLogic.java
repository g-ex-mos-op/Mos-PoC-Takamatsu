/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.logic;

import java.util.Map;

/**
 * 永年勤続申込状況確認 ロジックインターフェース
 * 
 * 対象条件の『支部』選択時の支部情報を取得します。
 * @author xamaruyama
 *
 */
public interface LongserviceViewListConditionLogic {
    
    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public Map execute(Map params);
}
