package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UriageTaxMeisaiInfo;

/**
 * 売上消費税明細情報を取得Daoクラス
 * 2019/07/16
 * @author USI 張
 */
public interface UriageTaxMeisaiDao {

	public static final Class BEAN = UriageTaxMeisaiInfo.class;

    /**
     * 売上と消費税消費税明細情報を取得時のパラメータ
     */
	public static final String select_ARGS =
		      " companyCd"
		    + ", taishoTenpoCd"
		    + ", taishoYM";

		    /**
		     * 売上消費税明細情報を取得する(月報)
		     * @param companyCd 対象会社コード
		     * @param taishoTenpoCd	 対象店コード
		     * @param taishoYM  対象年月
		     * @return List
		     */
		public List select (
			String companyCd,
		    String taishoTenpoCd,
		    String taishoYM);
}
