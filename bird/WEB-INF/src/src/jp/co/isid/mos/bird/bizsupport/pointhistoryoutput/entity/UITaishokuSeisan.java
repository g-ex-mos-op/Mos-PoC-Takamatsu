/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity;

import java.math.BigDecimal;

/**
 * 退職時精算
 * @author Yuichi Tamura(ISID-AO)
 *
 */
public class UITaishokuSeisan {

    public static final String TABLE = "BD61TSES";

    private static final String nendo_COLUMN = "NENDO";
	private static final String userId_COLUMN = "USER_ID";
	private static final String userNameKj_COLUMN = "USER_NAME_KJ";
	private static final String pointRuikei_COLUMN = "POINT_RUIKEI";
	private static final String kbCompanyCd_COLUMN = "KB_COMPANY_CD";
	private static final String kbCompanyName_COLUMN = "KB_COMPANY_NAME";
	private static final String nyusyaDt_COLUMN = "NYUSYA_DT";
	private static final String taishokuDt_COLUMN = "TAISHOKU_DT";
	private static final String kinzokuYear_COLUMN = "KINZOKU_YEAR";
	private static final String retireCd_COLUMN = "RETIRE_CD";
	private static final String retireName_COLUMN = "RETIRE_NAME";
	private static final String sikyuRate_COLUMN = "SIKYU_RATE";
	private static final String tSeisanPoint_COLUMN = "T_SEISAN_POINT";
	private static final String bikou_COLUMN = "BIKOU";

	//年度
	private String nendo;
	//社員番号
	private String userId;
	//社員名
	private String userNameKj;
	//累計ポイント
	private BigDecimal pointRuikei;
	//会社コード
	private String kbCompanyCd;
	//会社名
	private String kbCompanyName;
	//入社年月日
	private String nyusyaDt;
	//退職年月日
	private String taishokuDt;
	//勤続年数
	private BigDecimal kinzokuYear;
	//退職事由
	private String retireCd;
	//支給率
	private BigDecimal sikyuRate;
	//退職精算ポイント
	private BigDecimal tSeisanPoint;
	//退職事由名
	private String retireName;


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
	 * @return 累計イントを返す
	 */
	public BigDecimal getPointRuikei() {
		return pointRuikei;
	}
	/**
	 * @param pointRuikei 累計ポイントをセットする
	 */
	public void setPointRuikei(BigDecimal pointRuikei) {
		this.pointRuikei = pointRuikei;
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
	/**
	 * @return 入社年月日を返す
	 */
	public String getNyusyaDt() {
		return nyusyaDt;
	}
	/**
	 * @param nyusyaDt 入社年月日をセットする
	 */
	public void setNyusyaDt(String nyusyaDt) {
		this.nyusyaDt = nyusyaDt;
	}
	/**
	 * @return 退職年月日を返す
	 */
	public String getTaishokuDt() {
		return taishokuDt;
	}
	/**
	 * @param taishokuDt 退職年月日をセットする
	 */
	public void setTaishokuDt(String taishokuDt) {
		this.taishokuDt = taishokuDt;
	}
	/**
	 * @return 勤続年数を返す
	 */
	public BigDecimal getKinzokuYear() {
		return kinzokuYear;
	}
	/**
	 * @param kinzokuYear 勤続年数をセットする
	 */
	public void setKinzokuYear(BigDecimal kinzokuYear) {
		this.kinzokuYear = kinzokuYear;
	}
	/**
	 * @return 退職事由を返す
	 */
	public String getRetireCd() {
		return retireCd;
	}
	/**
	 * @param retireCd 退職事由をセットする
	 */
	public void setRetireCd(String retireCd) {
		this.retireCd = retireCd;
	}
	/**
	 * @return 退職事由名を返す
	 */
	public String getRetireName() {
		return retireName;
	}
	/**
	 * @param retireName 退職事由名をセットする
	 */
	public void setRetireName(String retireName) {
		this.retireName = retireName;
	}
	/**
	 * @return 支給率を返す
	 */
	public BigDecimal getSikyuRate() {
		return sikyuRate;
	}
	/**
	 * @param sikyuRate 支給率をセットする
	 */
	public void setSikyuRate(BigDecimal sikyuRate) {
		this.sikyuRate = sikyuRate;
	}
	/**
	 * @return 退職精算ポイントを返す
	 */
	public BigDecimal getTSeisanPoint() {
		return tSeisanPoint;
	}
	/**
	 * @param tSeisanPoint 退職精算ポイントをセットする
	 */
	public void setTSeisanPoint(BigDecimal tSeisanPoint) {
		this.tSeisanPoint = tSeisanPoint;
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
