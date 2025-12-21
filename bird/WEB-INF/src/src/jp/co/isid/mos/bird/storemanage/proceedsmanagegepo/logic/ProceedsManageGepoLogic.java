package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic;

import java.util.Map;

/**
 * 売上金管理月報取得ロジック インターフェース
 *
 * @author xjung
 */
public interface ProceedsManageGepoLogic {
    /**
     * 売上金管理月報情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ 
	 * @param onerCd	 オーナーコード
     * @param companyCd   会社コード
     * @param miseCd      店コード
     * @param taishoYM    対象年月
     * @param syukeiKbnList 集計区分情報リスト
     * @return Map 売上金管理月報情報
	 */
    public Map execute (
        String userType,
        String userId,
        boolean limitKbn,
        String onerCd,
        String companyCd,
        String miseCd,
        String taishoYM);
}