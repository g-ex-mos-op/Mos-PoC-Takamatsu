package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic;

import java.util.List;

public interface PointLogic {

	/**
     * dポイント、株主優待モスポイント情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ
     * @param onerCd	 オーナーコード
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param taishoYM  対象年月
     * @param list 売上金管理月報情報リスト
     * @return list dポイント、株主優待モスポイント情報リスト
	 */
    public List execute (
        String userType,
        String userId,
        boolean limitKbn,
        String onerCd,
        String companyCd,
        String miseCd,
        String taishoYM, List list);
}
