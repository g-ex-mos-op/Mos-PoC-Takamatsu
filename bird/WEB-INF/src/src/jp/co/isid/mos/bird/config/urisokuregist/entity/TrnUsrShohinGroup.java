/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;

import java.sql.Timestamp;

/**
 * 売上速報商品グループエンティティ
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class TrnUsrShohinGroup {
    public static final String TABLE = "BR67SGRP";
    
    public static final String uriSokuYm_COLUMN = "URI_SOKU_YM";
    
    public static final String frameKbn_COLUMN = "FRAME_KBN";
    
    public static final String seqNo_COLUMN = "SEQ_NO";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String dataKbn_COLUMN = "DATA_KBN";
    
    public static final String dispWord_COLUMN = "DISP_WORD";
    
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
     * フレーム区分
     * 01:左枠 02:中央枠 03:右枠
     */
    private String frameKbn;
    
    /**
     * 行番号(2桁)
     */
    private String seqNo;
    
    /**
     * メニューコード
     */
    private String menuCd = "";
    
    /**
     * データ区分
     * 0:コメント/空白, 1:メニュー
     */
    private String dataKbn = "";
    
    /**
     * ユーザ指定文字列(30byte)
     * 
     * 商品メニュー項目に出力される文字列
     */
    private String dispWord = "";
    
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
	 * @return dataKbn を戻します。
	 */
	public String getDataKbn() {
		return dataKbn;
	}

	/**
	 * @param dataKbn dataKbnへ設定します。
	 */
	public void setDataKbn(String dataKbn) {
		this.dataKbn = dataKbn;
	}

	/**
	 * @return dispWord を戻します。
	 */
	public String getDispWord() {
		return dispWord;
	}

	/**
	 * @param dispWord dispWordへ設定します。
	 */
	public void setDispWord(String dispWord) {
		this.dispWord =dispWord;
	}

	/**
	 * @return frameKbn を戻します。
	 */
	public String getFrameKbn() {
		return frameKbn;
	}

	/**
	 * @param frameKbn frameKbnへ設定します。
	 */
	public void setFrameKbn(String frameKbn) {
		this.frameKbn = frameKbn;
	}

	/**
	 * @return menuCd を戻します。
	 */
	public String getMenuCd() {
		return menuCd;
	}

	/**
	 * @param menuCd menuCdへ設定します。
	 */
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}

	/**
	 * @return seqNo を戻します。
	 */
	public String getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo seqNoへ設定します。
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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

}
