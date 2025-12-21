package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic;

import java.util.Map;

/**
 * 売上金管理月報の条件部情報取得ロジック インターフェース
 *
 * @author xjung
 */
public interface ProceedsConditionLogic {

    /**
     * 条件部情報を取得する
     * @param userId	ユーザID
     * @param appDate	アプリ日付
     * @return Map     条件部情報
     */
    public Map execute (String userId, String appDate);
}