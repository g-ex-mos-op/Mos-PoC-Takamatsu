package jp.co.isid.mos.bird.bizreport.netorderreport.logic;

import java.util.Map;

/**
 * ネット注文帳票の条件部情報取得
 * @author zzw
 *
 */
public interface ConditionLogic {
    /**
     * 条件部情報を取得する
     * @param userType	ユーザタイプ
     * @param userId	ユーザID
     * @param appDate	アプリ日付
     * @return Map     条件部情報
     */
    public Map execute (String userType, String userId, String appDate);
}
