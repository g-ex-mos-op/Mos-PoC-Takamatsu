package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.NebikiInfo;

/**
 * 値引情報取得Dao
 * @author xsong
 *
 */
public interface NebikiInfoDao {

	/** 値引情報エンティティクラス */
	public static final Class BEAN = NebikiInfo.class;
	
	/** 値引情報取得時のパラメータ*/
	public static final String selectNebiki_ARGS =
        "userType"
        + ", userId"
        + ", limitKbn"
		+ ", onerCd"
		+ ", companyCd"
		+ ", miseCd"
		+ ", kikanFrom"
		+ ", kikanTo";
	
	
	/**
	 * 値引情報を取得する
     * @param userType  ユーザタイプ
     * @param userId    ユーザID
     * @param limitKbn  制限フラグ 
	 * @param companyCd 会社コード
	 * @param onerCd	 オーナーコード
	 * @param miseCd	 店コード
	 * @param kikanFrom 期間FROM
	 * @param kikanTo	 期間TO
	 * @return int 	 対象店舗数
	 */
	public List selectNebiki( String userType,
	        String userId,
	        boolean limitKbn,
			String onerCd,
			String companyCd,
			String miseCd,
			String kikanFrom,
			String kikanTo);
}
