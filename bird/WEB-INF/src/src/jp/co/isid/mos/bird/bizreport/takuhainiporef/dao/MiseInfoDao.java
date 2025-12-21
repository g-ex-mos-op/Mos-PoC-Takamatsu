package jp.co.isid.mos.bird.bizreport.takuhainiporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.MiseInfo;

/**
 * 店舗別宅配売上情報Daoクラス
 *
 * @author xjung
 */
public interface MiseInfoDao {

    /**
     * 店舗別宅配売上情報エンティティクラス
     */
	public static final Class BEAN = MiseInfo.class;

    /**
     * 店舗別宅配売上情報(月報)取得時のパラメータ【本部用】
     */
	public static final String selectMiseGepoInfo_ARGS =
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
		+ ", linkKbnCd"
		+ ", sibuCd"
		+ ", takuKbnCdList"
        + ", svCd";

    /**
     * 店舗別宅配売上情報(日報)取得時のパラメータ【本部用】
     */
	public static final String selectMiseNipoInfo_ARGS =
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
		+ ", linkKbnCd"
		+ ", sibuCd"
		+ ", takuKbnCdList"
        + ", svCd";

    /**
     * 店舗別宅配売上情報(月報)取得時のパラメータ【オーナー用】
     */
	public static final String selectOnerMiseGepoInfo_ARGS =
		  "companyCd"
		+ ", onerCd"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", dataShu";

    /**
     * 店舗別宅配売上情報(日報)取得時のパラメータ【オーナー用】
     */
	public static final String selectOnerMiseNipoInfo_ARGS =
		  "companyCd"
		+ ", onerCd"
		+ ", taishoKikan"
		+ ", kikanFrom"
		+ ", kikanTo"
		+ ", dataShu";

	/**
	 * 店舗別宅配売上情報を取得する(月報)【本部用】
	 * @param companyCd     会社コード
	 * @param userId		 ユーザーID
	 * @param limitFlg		 制限区分
	 * @param tenpoShu		 店舗種別
	 * @param taishoTenpo	 対象店舗
	 * @param shukeiKbn 	 集計区分
	 * @param taishoKikan	 対象期間
	 * @param kikanFrom	 対象期間From
	 * @param kikanTo		 対象期間To
	 * @param dataShu		 前年データ種別
	 * @param linkKbnCd	 リンク区分コード
	 * @param sibuCd		 支部コード
	 * @param takuKbnCdList 宅配明細コードリスト
     * @param svCd          SVコード 2008/12/09追加 SV対応
     * 
	 * @return List 		 店舗別宅配売上List
	 */
	public List selectMiseGepoInfo(
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
		String linkKbnCd,
		String sibuCd,
		List takuKbnCdList, 
        String svCd);

	/**
	 * 店舗別宅配売上情報を取得する(日報)【本部用】
     * @param companyCd     会社コード
     * @param userId        ユーザーID
     * @param limitFlg      制限区分
     * @param tenpoShu      店舗種別
     * @param taishoTenpo   対象店舗
     * @param shukeiKbn     集計区分
     * @param taishoKikan   対象期間
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     * @param dataShu       前年データ種別
     * @param linkKbnCd     リンク区分コード
     * @param sibuCd        支部コード
     * @param takuKbnCdList 宅配明細コードリスト
     * @param svCd          SVコード 2008/12/09追加 SV対応
     * 
     * @return List         店舗別宅配売上List
	 */
	public List selectMiseNipoInfo(
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
		String linkKbnCd,
		String sibuCd,
		List takuKbnCdList, 
        String svCd);
  
	/**
	 * 店舗別宅配売上情報を取得する(月報)【オーナー用】
	 * @param companyCd	会社コード
	 * @param onerCd		オーナーコード
	 * @param taishoKikan	対象期間
	 * @param kikanFrom	対象期間From
	 * @param dataShu		前年データ種別
	 * @return List 		店舗別宅配売上List
	 */
	public List selectOnerMiseGepoInfo(
		String companyCd,
		String onerCd,
		String taishoKikan,
		String kikanFrom,
		String dataShu);

	/**
	 * 店舗別宅配売上情報を取得する(日報)【オーナー用】
	 * @param companyCd	会社コード
	 * @param onerCd		オーナーコード
	 * @param taishoKikan	対象期間
	 * @param kikanFrom	対象期間From
	 * @param kikanTo		対象期間To
	 * @param dataShu		前年データ種別
	 * @return List 		店舗宅配売上List
	*/
  public List selectOnerMiseNipoInfo(
	  String companyCd,
	  String onerCd,
	  String taishoKikan,
	  String kikanFrom,
	  String kikanTo,
	  String dataShu);
}