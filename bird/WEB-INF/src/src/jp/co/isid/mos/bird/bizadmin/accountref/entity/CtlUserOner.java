/*
 * 作成日: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.entity;

/**
 * @author 慮
 */
public class CtlUserOner {
	
	public static final String TABLE = "BM06UONR";
	
	public static final String userId_COLUMN = "USER_ID";
	
	public static final String companyCd_COLUMN = "COMPANY_CD";
	
	public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
	
	public static final String onerCd_COLUMN = "ONER_CD";
	
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
	 * 管理会社企業コード
	 */
	private String companyCd;
	
	/**
	 * オーナーコード名
	 */
	private String onerNameKj;

	/**
	 * オーナーコード
	 */
	private String onerCd;
	
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
	private String firstTmsp;
	
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
	private String lastTmsp;


	/**
	 * onerNameKj　オーナーコード名を取得します。
	 * @return onerNameKj オーナーコード名
	 */
	public String getOnerNameKj() {
		return onerNameKj;
	}
	/**
	 * onerNameKj　オーナーコード名を設定します。
	 * @param onerNameKj オーナーコード名
	 */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}
	/**
	 * companyCd　管理会社企業コードを取得します。
	 * @return companyCd 管理会社企業コード
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * companyCd　管理会社企業コードを設定します。
	 * @param companyCd 管理会社企業コード
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * firstPgm　登録プログラムを取得します。
	 * @return firstPgm 登録プログラム
	 */
	public String getFirstPgm() {
		return firstPgm;
	}
	/**
	 * firstPgm　登録プログラムを設定します。
	 * @param firstPgm 登録プログラム
	 */
	public void setFirstPgm(String firstPgm) {
		this.firstPgm = firstPgm;
	}
	/**
	 * firstTmsp　登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
	 * @return firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
	 */
	public String getFirstTmsp() {
		return firstTmsp;
	}
	/**
	 * firstTmsp　登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
	 * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
	 */
	public void setFirstTmsp(String firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	/**
	 * firstUser　登録ユーザーを取得します。
	 * @return firstUser 登録ユーザー
	 */
	public String getFirstUser() {
		return firstUser;
	}
	/**
	 * firstUser　登録ユーザーを設定します。
	 * @param firstUser 登録ユーザー
	 */
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	/**
	 * lastPgm　修正プログラムを取得します。
	 * @return lastPgm 修正プログラム
	 */
	public String getLastPgm() {
		return lastPgm;
	}
	/**
	 * lastPgm　修正プログラムを設定します。
	 * @param lastPgm 修正プログラム
	 */
	public void setLastPgm(String lastPgm) {
		this.lastPgm = lastPgm;
	}
	/**
	 * lastTmsp　修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
	 * @return lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
	 */
	public String getLastTmsp() {
		return lastTmsp;
	}
	/**
	 * lastTmsp　修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
	 * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
	 */
	public void setLastTmsp(String lastTmsp) {
		this.lastTmsp = lastTmsp;
	}
	/**
	 * lastUser　修正ユーザーを取得します。
	 * @return lastUser 修正ユーザー
	 */
	public String getLastUser() {
		return lastUser;
	}
	/**
	 * lastUser　修正ユーザーを設定します。
	 * @param lastUser 修正ユーザー
	 */
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	/**
	 * onerCd　オーナーコードを取得します。
	 * @return onerCd オーナーコード
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * onerCd　オーナーコードを設定します。
	 * @param onerCd オーナーコード
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
	 * userId　ユーザIDを取得します。
	 * @return userId ユーザID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId　ユーザIDを設定します。
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
