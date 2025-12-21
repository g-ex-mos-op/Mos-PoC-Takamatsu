package jp.co.isid.mos.bird.bizreport.common.logic;

import java.util.Map;

/**
 * 総合営業日報の条件部情報取得共通ロジック インターフェース
 *
 * @author xjung
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