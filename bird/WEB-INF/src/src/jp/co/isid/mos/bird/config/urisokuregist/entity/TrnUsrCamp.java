/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;

import java.sql.Timestamp;

/**
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class TrnUsrCamp {
    public static final String TABLE = "BR66SKCP";
    
    public static final String uriSokuYm_COLUMN = "URI_SOKU_YM";

    public static final String campId_COLUMN = "CAMP_ID";

    public static final String titleFlg_COLUMN = "TITLE_FLG";

    public static final String jissekiFlg_COLUMN = "JISSEKI_FLG";

    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    /**
     * 売上速報レポート対象年月
     */
    private String uriSokuYm;

    /**
     * キャンペーン識別番号
     */
    private String campId;

    /**
     * キャンペーン識別番号
     */
    private String titleFlg;

    /**
     * キャンペーン識別番号
     */
    private String jissekiFlg;

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
	 * @param firstPgm firstPgmへ設定します。
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
	 * @param firstTmsp firstTmspへ設定します。
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
	 * @param firstUser firstUserへ設定します。
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
	 * @param lastPgm lastPgmへ設定します。
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
	 * @param lastTmsp lastTmspへ設定します。
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
	 * @param lastUser lastUserへ設定します。
	 */
	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}

	/**
	 * @return uriSokuYm を戻します。
	 */
	public String getUriSokuYm() {
		return uriSokuYm;
	}

	/**
	 * @param uriSokuYm uriSokuYmへ設定します。
	 */
	public void setUriSokuYm(String uriSokuYm) {
		this.uriSokuYm = uriSokuYm;
	}

	/**
	 * @return campId を戻します。
	 */
	public String getCampId() {
		return campId;
	}

	/**
	 * @param campId campIdへ設定します。
	 */
	public void setCampId(String campId) {
		this.campId = campId;
	}

	/**
	 * @return jissekiFlg を戻します。
	 */
	public String getJissekiFlg() {
		return jissekiFlg;
	}

	/**
	 * @param jissekiFlg jissekiFlgへ設定します。
	 */
	public void setJissekiFlg(String jissekiFlg) {
		this.jissekiFlg = jissekiFlg;
	}

	/**
	 * @return titleFlg を戻します。
	 */
	public String getTitleFlg() {
		return titleFlg;
	}

	/**
	 * @param titleFlg titleFlgへ設定します。
	 */
	public void setTitleFlg(String titleFlg) {
		this.titleFlg = titleFlg;
	}

}
