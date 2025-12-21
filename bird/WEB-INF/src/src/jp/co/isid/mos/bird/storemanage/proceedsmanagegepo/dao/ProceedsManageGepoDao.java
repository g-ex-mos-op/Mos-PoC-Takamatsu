package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.ProceedsManageGepoInfo;

/**
 * 売上金管理月報情報Daoクラス
 *
 * @author xjung
 */
public interface ProceedsManageGepoDao {

	/** 売上金管理月報情報エンティティクラス */
	public static final Class BEAN = ProceedsManageGepoInfo.class;

    /**
     * 売上金管理月報情報取得時のパラメータ
     */
	public static final String selectGepo_ARGS =
          "userType"
        + ", userId"
        + ", limitKbn"
		+ ", onerCd"
		+ ", companyCd"
		+ ", miseCd"
		+ ", kikanFrom"
		+ ", kikanTo";

	/**
	 * 売上金管理月報情報を取得する(月報)
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
	public List selectGepo (
        String userType,
        String userId,
        boolean limitKbn,
		String onerCd,
		String companyCd,
		String miseCd,
		String kikanFrom,
		String kikanTo);
}