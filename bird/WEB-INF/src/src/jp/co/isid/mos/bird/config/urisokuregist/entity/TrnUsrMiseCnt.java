/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 売上触法レポート用店舗数エンティティ
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class TrnUsrMiseCnt {
    public static final String TABLE = "BR69USRT";
    
    public static final String taishoYm_COLUMN = "TAISHO_YM";
    
    public static final String miseCntAll_COLUMN = "MISE_CNT_ALL";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    /**
     * 対象年月
     */
    private String taishoYm;
    
    /**
     * 店舗数
     */
    private BigDecimal miseCntAll;
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
	 * @return miseCntAll を戻します。
	 */
	public BigDecimal getMiseCntAll() {
		return miseCntAll;
	}

	/**
	 * @param miseCntAll miseCntAllへ設定します。
	 */
	public void setMiseCntAll(BigDecimal miseCntAll) {
		this.miseCntAll = miseCntAll;
	}

	/**
	 * @return taishoYm を戻します。
	 */
	public String getTaishoYm() {
		return taishoYm;
	}

	/**
	 * @param taishoYm taishoYmへ設定します。
	 */
	public void setTaishoYm(String taishoYm) {
		this.taishoYm = taishoYm;
	}

}
