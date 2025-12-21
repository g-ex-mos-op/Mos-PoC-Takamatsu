/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity;

import java.math.BigDecimal;

/**
 * 社員付与ポイント履歴
 * @author Yuichi Tamura(ISID-AO)
 *
 */
public class UIHuyoPointRireki {

    public static final String TABLE = "BD59HPRI";

    private static final String nendo_COLUMN = "NENDO";
	private static final String userId_COLUMN = "USER_ID";
	private static final String userNameKj_COLUMN = "USER_NAME_KJ";
	private static final String point_COLUMN = "POINT";
	private static final String pointShu_COLUMN = "POINT_SHU";
	private static final String pointShuName_COLUMN = "POINT_SHU_NAME";
	private static final String kbCompanyCd_COLUMN = "KB_COMPANY_CD";
	private static final String kbCompanyName_COLUMN = "KB_COMPANY_NAME";
	private static final String rankCd_COLUMN = "RANK_CD";
	private static final String rankName_COLUMN = "RANK_NAME";
	private static final String nyusyaDt_COLUMN = "NYUSYA_DT";
	private static final String taishokuDt_COLUMN = "TAISHOKU_DT";
	private static final String kinzokuYear_COLUMN = "KINZOKU_YEAR";
	private static final String retireCd_COLUMN = "RETIRE_CD";
	private static final String retireName_COLUMN = "RETIRE_NAME";
	private static final String kaigaiFlg_COLUMN = "KAIGAI_FLG";
	private static final String bikou_COLUMN = "BIKOU";

	//年度
	private String nendo;
	//社員番号
	private String userId;
	//社員名
	private String userNameKj;
	//ポイント
	private BigDecimal point;
	//ポイント付与種別
	private String pointShu;
	//ポイント付与種別名称
	private String pointShuName;
	//会社コード
	private String kbCompanyCd;
	//会社名
	private String kbCompanyName;
	//等級
	private String rankCd;
	//等級名
	private String rankName;
	//入社年月日
	private String nyusyaDt;
	//退職年月日
	private String taishokuDt;
	//勤続年数
	private BigDecimal kinzokuYear;
	//退職事由
	private String retireCd;
	//退職事由名
	private String retireName;
	//海外赴任中フラグ
	private String kaigaiFlg;
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
	 * @return ポイント付与種別を返す
	 */
	public String getPointShu() {
		return pointShu;
	}
	/**
	 * @param pointShu ポイント付与種別をセットする
	 */
	public void setPointShu(String pointShu) {
		this.pointShu = pointShu;
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
	 * @return 等級を返す
	 */
	public String getRankCd() {
		return rankCd;
	}
	/**
	 * @param rankCd 等級をセットする
	 */
	public void setRankCd(String rankCd) {
		this.rankCd = rankCd;
	}
	/**
	 * @return 等級名を返す
	 */
	public String getRankName() {
		return rankName;
	}
	/**
	 * @param rankName 等級名をセットする
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
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
	 * @return 海外赴任中フラグを返す
	 */
	public String getKaigaiFlg() {
		return kaigaiFlg;
	}
	/**
	 * @param kaigaiFlg 海外赴任中フラグをセットする
	 */
	public void setKaigaiFlg(String kaigaiFlg) {
		this.kaigaiFlg = kaigaiFlg;
	}

	/**
	 * @return 表示用海外赴任中フラグを返す
	 */
	public String getKaigaiFlgName() {
		if("1".equals(getKaigaiFlg())){
			return "海外赴任中";
		}
		return "";
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
