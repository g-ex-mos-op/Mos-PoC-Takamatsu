/*
 * 作成日: 2008/11/10
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.entity;

import java.sql.Timestamp;

/**
 * @author K.Nihonyanagi
 *
 */
public class UIUserCompany {


	public static final String TABLE="BM03USCP";

    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

	public static final String userId_COLUMN = "USER_ID";

    public static final String zokuseiKbn_COLUMN = "ZOKUSEI_KBN";

	public static final String rCompanyName_COLUMN = "R_COMPANY_NAME";

	public static final String rCompanyCd_COLUMN = "R_COMPANY_CD";

	public static final String firstUser_COLUMN = "FIRST_USER";

	public static final String firstPgm_COLUMN = "FIRST_PGM";

	public static final String firstTmsp_COLUMN = "FIRST_TMSP";

	public static final String lastUser_COLUMN = "LAST_USER";

	public static final String lastPgm_COLUMN = "LAST_PGM";

	public static final String lastTmsp_COLUMN = "LAST_TMSP";

	/**
     * ユーザID
     */
	private String userId;

	/**
     * 企業名
     */
	private String rCompanyName;
	/**
     * 企業コード
     */
	private String rCompanyCd;
	/**
     *属性区分
	 */
     private String zokuseiKbn;
	/**
     * 登録ユーザー
     */
	private String firstUser;

	/**
     * 登録プログラム
     */
	private String firstPgm;

	/**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
	private Timestamp firstTmsp;

	/**
     * 修正ユーザー
     */
	private String lastUser;

	/**
     * 修正プログラム
     */
	private String lastPgm;

	/**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
	private Timestamp lastTmsp;


	/**
	 * @return firstPgm を戻します。
	 */
	public String getFirstPgm() {
		return firstPgm;
	}
	/**
	 * @param firstPgm firstPgm を設定。
	 */
	public void setFirstPgm(String firstPgm) {
		this.firstPgm = firstPgm;
	}
	/**
	 * @return firstTmsp を戻します。
	 */
	public Timestamp getFirstTmsp() {
		return firstTmsp;
	}
	/**
	 * @param firstTmsp firstTmsp を設定。
	 */
	public void setFirstTmsp(Timestamp firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	/**
	 * @return firstUser を戻します。
	 */
	public String getFirstUser() {
		return firstUser;
	}
	/**
	 * @param firstUser firstUser を設定。
	 */
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	/**
	 * @return lastPgm を戻します。
	 */
	public String getLastPgm() {
		return lastPgm;
	}
	/**
	 * @param lastPgm lastPgm を設定。
	 */
	public void setLastPgm(String lastPgm) {
		this.lastPgm = lastPgm;
	}
	/**
	 * @return lastTmsp を戻します。
	 */
	public Timestamp getLastTmsp() {
		return lastTmsp;
	}
	/**
	 * @param lastTmsp lastTmsp を設定。
	 */
	public void setLastTmsp(Timestamp lastTmsp) {
		this.lastTmsp = lastTmsp;
	}
	/**
	 * @return lastUser を戻します。
	 */
	public String getLastUser() {
		return lastUser;
	}
	/**
	 * @param lastUser lastUser を設定。
	 */
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	/**
	 * @return rCompanyCd を戻します。
	 */
	public String getRCompanyCd() {
		return rCompanyCd;
	}
	/**
	 * @param companyCd rCompanyCd を設定。
	 */
	public void setRCompanyCd(String companyCd) {
		rCompanyCd = companyCd;
	}
	/**
	 * @return userId を戻します。
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId userId を設定。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return rCompanyName を戻します。
	 */
	public String getRCompanyName() {
		return rCompanyName;
	}
	/**
	 * @param companyName rCompanyName を設定。
	 */
	public void setRCompanyName(String companyName) {
		rCompanyName = companyName;
	}
    /**
     * zokuseiKbnを戻す
     * @return zokuseiKbn
     */
    public String getZokuseiKbn() {
        return zokuseiKbn;
    }
    /**
     * zokuseiKbnを設定
     * @param zokuseiKbn
     */
    public void setZokuseiKbn(String zokuseiKbn) {
        this.zokuseiKbn = zokuseiKbn;
    }
}
