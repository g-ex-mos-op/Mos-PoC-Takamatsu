package jp.co.isid.mos.bird.bizreport.takuhainiporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.SibuInfo;

/**
 * 支部別宅配売上情報Daoクラス
 * 
 * @author xjung
 */
public interface SibuInfoDao {

    /**
     * 支部別宅配売上情報エンティティクラス
     */
    public static final Class BEAN = SibuInfo.class;

    /**
     * 支部別宅配売上情報(月報)取得時のパラメータ
     */
    public static final String selectSibuGepoInfo_ARGS =
		  "companyCd"
		+ ", userId"
		+ ", limitFlg"
		+ ", tenpoShu"
		+ ", taishoTenpo"
		+ ", shukeiKbn"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", kikanTo"
		+ ", dataShu"
		+ ", sibuCdList"
		+ ", dispType";

    /**
     * 支部別宅配売上情報(日報)取得時のパラメータ
     */
	public static final String selectSibuNipoInfo_ARGS =
		  "companyCd"
		+ ", userId"
		+ ", limitFlg"
		+ ", tenpoShu"
		+ ", taishoTenpo"
		+ ", shukeiKbn"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", kikanTo"
		+ ", dataShu"
		+ ", sibuCdList"
		+ ", dispType";

    /**
     * 支部別宅配売上情報を取得する(月報)
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
	 * @param sibuCdList	支部コードリスト
	 * @return List 		支部別宅配売上List
	 */
    public List selectSibuGepoInfo(
    	String companyCd,
		String userId,
		boolean limitFlg,
		String tenpoShu,
		String taishoTenpo, 
		String shukeiKbn,
		String taishoKikan,
		String kikanFrom,
		String kikanTo,
		String dataShu,
		List sibuCdList
		, String dispType);

	/**
	 * 支部別宅配売上情報を取得する(日報)
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
	 * @param sibuCdList	支部コードリスト
	 * @return List 		支部別宅配売上List
	 */
    public List selectSibuNipoInfo(
		String companyCd,
		String userId,
		boolean limitFlg,
		String tenpoShu,
		String taishoTenpo, 
		String shukeiKbn,
		String taishoKikan,
		String kikanFrom,
		String kikanTo,
		String dataShu,
		List sibuCdList
		, String dispType);
}