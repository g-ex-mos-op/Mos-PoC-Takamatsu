/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.entity;

/**
 * 月次カレンダー情報エンティティ
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public class UICalenderMonthInfo {
    public static final String TABLE = "BD14ACAL";
    
    public static final String eigyoYM_COLUMN = "EIGYO_YM";
    public static final String weekCnt_COLUMN = "WEEK_CNT";
    public static final String satCnt_COLUMN  = "SAT_CNT";
    public static final String holiCnt_COLUMN = "HOLI_CNT";
    /** 対象月 */
    private String eigyoYM;
    /** 対象月 */
    private String weekCnt;
    /** 対象月 */
    private String satCnt;
    /** 対象月 */
    private String holiCnt;
	/**
	 * @return eigyoYM を戻します。
	 */
	public String getEigyoYM() {
		return eigyoYM;
	}
	/**
	 * @param eigyoYM を クラス変数eigyoYMへ設定します。
	 */
	public void setEigyoYM(String eigyoYM) {
		this.eigyoYM = eigyoYM;
	}
	/**
	 * @return holiCnt を戻します。
	 */
	public String getHoliCnt() {
		return holiCnt;
	}
	/**
	 * @param holiCnt を クラス変数holiCntへ設定します。
	 */
	public void setHoliCnt(String holiCnt) {
		this.holiCnt = holiCnt;
	}
	/**
	 * @return satCnt を戻します。
	 */
	public String getSatCnt() {
		return satCnt;
	}
	/**
	 * @param satCnt を クラス変数satCntへ設定します。
	 */
	public void setSatCnt(String satCnt) {
		this.satCnt = satCnt;
	}
	/**
	 * @return weekCnt を戻します。
	 */
	public String getWeekCnt() {
		return weekCnt;
	}
	/**
	 * @param weekCnt を クラス変数weekCntへ設定します。
	 */
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	
}
