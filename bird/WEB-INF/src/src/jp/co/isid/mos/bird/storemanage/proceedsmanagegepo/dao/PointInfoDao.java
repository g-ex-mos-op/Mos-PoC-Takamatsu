package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.PointInfo;

/**
 * dポイント、株主優待モスポイント情報Daoクラス
 *
 * @author USI 張
 */

public interface PointInfoDao {
	/** dポイント、株主優待モスポイント情報エンティティクラス */
	public static final Class BEAN = PointInfo.class;

	/** dポイント、株主優待モスポイント情報取得時のパラメータ*/
	public static final String select_ARGS =
        "userType"
        + ", userId"
        + ", limitKbn"
        + ", onerCd"
		+ ", companyCd"
		+ ", miseCd"
		+ ", taishoYM";

	/**
	 * dポイント、株主優待モスポイント情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ
     * @param onerCd	 オーナーコード
	 * @param companyCd 会社コード
	 * @param miseCd    店コード
	 * @param taishoYM  対象期間
	 * @return List     dポイント、株主優待モスポイント情報リスト
	 */
	public List select( String userType,
	        String userId,
	        boolean limitKbn,
	        String onerCd,
			String companyCd,
			String miseCd,
			String taishoYM);
}
