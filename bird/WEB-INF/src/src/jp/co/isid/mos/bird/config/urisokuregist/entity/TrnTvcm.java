/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.entity;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.common.util.CommonUtil;

/**
 * TVCM放映日エンティティ
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class TrnTvcm {
    public static final String TABLE = "BR65TVCM";
    
    public static final String tvcmDt_COLUMN = "TVCM_DT";
   
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";


    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    /**
     * TVCM放映日
     */
    private String tvcmDt;
    
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
     * 選択フラグ
     */
    private boolean selectFlg = true;
    

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
	 * @return tvcmDt を戻します。
	 */
	public String getTvcmDt() {
		return tvcmDt;
	}

	/**
	 * @param tvcmDt tvcmDtへ設定します。
	 */
	public void setTvcmDt(String tvcmDt) {
		this.tvcmDt = tvcmDt;
	}
	/**
	 * @return select を戻します。
	 */
	public boolean isSelectFlg() {
		return selectFlg;
	}

	/**
	 * @param flg selectへ設定します。
	 */
	public void setSelectFlg(boolean flg) {
		this.selectFlg = flg;
	}

	/**
	 * 放映日DD取得処理
	 * 
	 * @return
	 */
	public String getTvcmDay() {
		if(!CommonUtil.isNull(getTvcmDt())) {
			return getTvcmDt().substring(6);
		}
		return null;
	}
}
