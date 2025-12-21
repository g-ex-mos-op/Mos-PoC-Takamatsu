/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.entity;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.config.zenurikakuteiregist.util.ZenurikakuteiRegistUtil;

/**
 * 売上速報レポート確定状況
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class TrnUsrStatus {
    public static final String TABLE = "BR68DATS";
    
    public static final String uriSokuYm_COLUMN = "URI_SOKU_YM";
    public static final String statusFlg_COLUMN = "STATUS_FLG";
    public static final String kakuteiFlg_COLUMN = "KAKUTEI_FLG";
   
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";


    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    /**
     * 売上速報レポート年月
     */
    private String uriSokuYm;
    
    /**
     * ステータス
     */
    private String statusFlg;
    
    /**
     * 確定フラグ
     */
    private String kakuteiFlg;
    
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
     * 編集可能判断フラグ
     */
    private boolean registFlg = false;

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
	 * @return kakuteiFlg を戻します。
	 */
	public String getKakuteiFlg() {
		return kakuteiFlg;
	}

	/**
	 * @param kakuteiFlg kakuteiFlgへ設定します。
	 */
	public void setKakuteiFlg(String kakuteiFlg) {
		this.kakuteiFlg = kakuteiFlg;
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
	 * 編集可能判断フラグ取得処理
	 * 
	 * @return registFlg を戻します。
	 */
	public boolean isRegistFlg() {
		return registFlg;
	}

	/**
	 * 編集可能判断フラグ設定処理
	 * 
	 * @param registFlg registFlgへ設定します。
	 */
	public void setRegistFlg(boolean registFlg) {
		this.registFlg = registFlg;
	}

	/**
	 * @return statusFlg を戻します。
	 */
	public String getStatusFlg() {
		return statusFlg;
	}

	/**
	 * @param statusFlg statusへ設定します。
	 */
	public void setStatusFlg(String status) {
		this.statusFlg = status;
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
	 * ステータス名称取得処理
	 * @return
	 */
	public String getStatusName () {
		return ZenurikakuteiRegistUtil.getStatusName(getStatusFlg());
	}
}
