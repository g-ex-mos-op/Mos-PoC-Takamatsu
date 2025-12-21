/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.anshinreport.dao;

import jp.co.isid.mos.bird.common.entity.MstOner;

/**
 * オーナーマスタDao
 * 
 * 作成日:2012/01/24
 * @author xkinu
 *
 */
public interface MstOnerHonbuDao {
	/** エンティティクラス */
	public static final Class BEAN = MstOner.class;
	/** 検索：パラメータ */
	public static final String select_ARGS = "userId, userTypeCd, limitFlg, companyCd, onerCd";
	/**
	 * 検索処理
	 * 
	 * @param userId
	 * @param userTypeCd
	 * @param limitFlg
	 * @param companyCd
	 * @param onerCd
	 * @return オーナー情報
	 */
	public MstOner select(
			String userId, String userTypeCd, boolean limitFlg
			, String companyCd, String onerCd);
}
