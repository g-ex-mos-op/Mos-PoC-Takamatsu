/**
 * 
 */
package jp.co.isid.mos.bird.framework.entity;

import java.sql.Timestamp;

/**
 * 外部システムログ
 * 
 * 作成日:2009/01/13
 * @author xkinu
 *
 */
public class TrnOutSystemLog {
    public static final String TABLE = "BR71SLOG";
	
    public static final String linkId_COLUMN = "LINK_ID";
    
    public static final String userId_COLUMN = "USER_ID";
	
    public static final String rmtHost_COLUMN = "RMT_HOST";
	
    public static final String userAgnt_COLUMN = "USER_AGNT";
	
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";

    /**
     * リンクID
     */
    private String linkId;
    /**
     * ユーザID
     */
    private String userId;
    /**
     * リモートホスト
     */
    private String rmtHost;
    /**
     * ユーザエージェント
     */
    private String userAgnt;
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
	/**
	 * @return firstTmsp を戻します。
	 */
	public Timestamp getFirstTmsp() {
		return firstTmsp;
	}
	/**
	 * @param firstTmsp を クラス変数firstTmspへ設定します。
	 */
	public void setFirstTmsp(Timestamp firstTmsp) {
		this.firstTmsp = firstTmsp;
	}
	/**
	 * @return linkId を戻します。
	 */
	public String getLinkId() {
		return linkId;
	}
	/**
	 * @param linkId を クラス変数linkIdへ設定します。
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	/**
	 * @return rmtHost を戻します。
	 */
	public String getRmtHost() {
		return rmtHost;
	}
	/**
	 * @param rmtHost を クラス変数rmtHostへ設定します。
	 */
	public void setRmtHost(String rmtHost) {
		this.rmtHost = rmtHost;
	}
	/**
	 * @return userId を戻します。
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId を クラス変数userIdへ設定します。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return userAgnt を戻します。
	 */
	public String getUserAgnt() {
		return userAgnt;
	}
	/**
	 * @param userAgnt を クラス変数userAgntへ設定します。
	 */
	public void setUserAgnt(String userAgnt) {
		this.userAgnt = userAgnt;
	}
}
