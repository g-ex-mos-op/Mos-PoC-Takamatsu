package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.kaikei.entity.TrnAridakaMeisai;

/**
 * 会計区分別在高Daoクラス
 *
 * @author xjung
 */
public interface TrnAridakaMeisaiDao {

	/** 会計区分別在高情報エンティティクラス */
	public static final Class BEAN = TrnAridakaMeisai.class;

    /**
     * 会計区分別在高情報取得時のパラメータ
     */
	public static final String select_ARGS =
           "sysDate"
        + ", userId"
        + ", userType"
		+ ", companyCd"
		+ ", miseCd"
		+ ", taishoYM"
        + ", limitKbn";

	/**
	 * 会計区分別在高情報を取得する(月報)
     * @param sysDate    システム日付
     * @param userId    ユーザID
     * @param userType  ユーザタイプ
	 * @param companyCd 会社コード
	 * @param miseCd	 店コード
	 * @param taishoYM  対象年月
     * @param limitKbn  制限フラグ 
	 * @return List 	 会計区分別在高情報
	 */
	public List select (
        String sysDate,
        String userId,
        String userType,
		String companyCd,
		String miseCd,
		String taishoYM,
        boolean limitKbn);
}