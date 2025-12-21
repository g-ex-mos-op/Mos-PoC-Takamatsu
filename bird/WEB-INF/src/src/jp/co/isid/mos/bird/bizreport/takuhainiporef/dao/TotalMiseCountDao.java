package jp.co.isid.mos.bird.bizreport.takuhainiporef.dao;

import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.TotalMiseCount;

/**
 * 宅配対象店舗数情報Daoクラス
 *
 * @author xjung
 */
public interface TotalMiseCountDao {

	/** 宅配対象店舗数エンティティクラス */
	public static final Class BEAN = TotalMiseCount.class;

    /**
     * 宅配対象店舗数(月報)取得時のパラメータ【本部用】
     */
	public static final String selectCountGepo_ARGS =
		  "companyCd"
		+ ", userId"
		+ ", limitFlg"
		+ ", tenpoShu"
		+ ", taishoTenpo"
		+ ", shukeiKbn"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", kikanTo"
		+ ", dataShu";

    /**
     * 宅配対象店舗数(日報)取得時のパラメータ【本部用】
     */
	public static final String selectCountNipo_ARGS =
		  "companyCd"
		+ ", userId"
		+ ", limitFlg"
		+ ", tenpoShu"
		+ ", taishoTenpo"
		+ ", shukeiKbn"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", kikanTo"
		+ ", dataShu";

    /**
     * 宅配対象店舗数(月報)取得時のパラメータ【オーナー用】
     */
	public static final String selectOnerCountGepo_ARGS =
		  "companyCd"
		+ ", onerCd"
		+ ", kikanFrom";

    /**
     * 宅配対象店舗数(日報)取得時のパラメータ【オーナー用】
     */
	public static final String selectOnerCountNipo_ARGS =
		  "companyCd"
		+ ", onerCd"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", kikanTo";

	/**
	 * 宅配対象店舗数を取得する(月報)【本部用】
	 * @param companyCd	会社コード
	 * @param userId		ユーザーID
	 * @param limitFlg		制限区分
	 * @param tenpoShu		店舗種別
	 * @param taishoTenpo	対象店舗
	 * @param shukeiKbn	集計区分
	 * @param taishoKikan	対象期間
	 * @param kikanFrom	期間FROM
	 * @param kikanTo		期間TO
	 * @param dataShu		前年データ種別
	 * @return int 		対象店舗数
	 */
	public int selectCountGepo (
		String companyCd,
		String userId,
		boolean limitFlg,
		String tenpoShu,
		String taishoTenpo, 
		String shukeiKbn,
		String taishoKikan,
		String kikanFrom,
		String kikanTo,
		String dataShu);

	/**
	 * 宅配対象店舗数を取得する(日報)【本部用】
	 * @param companyCd	会社コード
	 * @param userId		ユーザーID
	 * @param limitFlg		制限区分
	 * @param tenpoShu		店舗種別
	 * @param taishoTenpo	対象店舗
	 * @param shukeiKbn	集計区分
	 * @param taishoKikan	対象期間
	 * @param kikanFrom	期間FROM
	 * @param kikanTo		期間TO
	 * @param dataShu		前年データ種別
	 * @return int 		対象店舗数
	 */
	public int selectCountNipo (
		String companyCd,
		String userId,
		boolean limitFlg,
		String tenpoShu,
		String taishoTenpo, 
		String shukeiKbn,
		String taishoKikan,
		String kikanFrom,
		String kikanTo,
		String dataShu);

	/**
	 * 宅配対象店舗数を取得する(月報)【オーナー用】
	 * @param companyCd	会社コード
	 * @param onerCd		オーナーコード
	 * @param kikanFrom	期間FROM
	 * @return int 		対象店舗数
	 */
	public int selectOnerCountGepo (
		String companyCd,
		String onerCd,
		String kikanFrom);

	/**
	 * 宅配対象店舗数を取得する(日報)【オーナー用】
	 * @param companyCd	会社コード
	 * @param onerCd		オーナーコード
	 * @param taishoKikan	対象期間
	 * @param kikanFrom	期間FROM
	 * @param kikanTo		期間TO
	 * @return int 		対象店舗数
	 */
	public int selectOnerCountNipo (
		String companyCd,
		String onerCd,
		String taishoKikan,
		String kikanFrom,
		String kikanTo);
}