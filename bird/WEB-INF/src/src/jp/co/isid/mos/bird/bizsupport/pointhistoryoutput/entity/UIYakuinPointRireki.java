/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity;

import java.math.BigDecimal;

/**
 * 役員付与ポイント履歴
 * @author Yuichi Tamura(ISID-AO)
 *
 */
public class UIYakuinPointRireki {

    public static final String TABLE = "BD60YPRI";

    private static final String nendo_COLUMN = "NENDO";
	private static final String userId_COLUMN = "USER_ID";
	private static final String userNameKj_COLUMN = "USER_NAME_KJ";
	private static final String point_COLUMN = "POINT";
	private static final String pointShuName_COLUMN = "POINT_SHU_NAME";
	private static final String kbCompanyCd_COLUMN = "KB_COMPANY_CD";
	private static final String kbCompanyName_COLUMN = "KB_COMPANY_NAME";
	private static final String bikou_COLUMN = "BIKOU";

	//年度
	private String nendo;
	//社員番号
	private String userId;
	//社員名
	private String userNameKj;
	//ポイント
	private BigDecimal point;
	//ポイント付与種別名称
	private String pointShuName;
	//会社コード
	private String kbCompanyCd;
	//会社名
	private String kbCompanyName;
	//備考
	private String bikou;

	/**
	 * @return 年度を返す
	 */
	public String getNendo() {
		return nendo;
	}
	/**
	 * @param nendo 年度をセットする
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	/**
	 * @return 社員番号を返す
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId 社員番号をセットする
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return 社員名を返す
	 */
	public String getUserNameKj() {
		return userNameKj;
	}
	/**
	 * @param userNameKj 社員名をセットする
	 */
	public void setUserNameKj(String userNameKj) {
		this.userNameKj = userNameKj;
	}
	/**
	 * @return ポイントを返す
	 */
	public BigDecimal getPoint() {
		return point;
	}
	/**
	 * @param point ポイントをセットする
	 */
	public void setPoint(BigDecimal point) {
		this.point = point;
	}
	/**
	 * @return ポイント付与種別名称を返す
	 */
	public String getPointShuName() {
		return pointShuName;
	}
	/**
	 * @param pointShuName ポイント付与種別名称をセットする
	 */
	public void setPointShuName(String pointShuName) {
		this.pointShuName = pointShuName;
	}
	/**
	 * @return 会社コードを返す
	 */
	public String getKbCompanyCd() {
		return kbCompanyCd;
	}
	/**
	 * @param kbCompanyCd 会社コードをセットする
	 */
	public void setKbCompanyCd(String kbCompanyCd) {
		this.kbCompanyCd = kbCompanyCd;
	}
	/**
	 * @return 会社名称を返す
	 */
	public String getKbCompanyName() {
		return kbCompanyName;
	}
	/**
	 * @param kbCompanyName 会社名称をセットする
	 */
	public void setKbCompanyName(String kbCompanyName) {
		this.kbCompanyName = kbCompanyName;
	}
	/**
	 * @return 備考を返す
	 */
	public String getBikou() {
		return bikou;
	}
	/**
	 * @param bikou 備考をセットする
	 */
	public void setBikou(String bikou) {
		this.bikou = bikou;
	}




}
