/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.entity;

/**
 * 日次カレンダー情報エンティティ
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public class UICalenderDayInfo {
    public static final String TABLE = "BR23DDSE";
    
    public static final String eigyoDtCOLUMN = "EIGYO_DT";
    public static final String dogetuDt_COLUMN = "DOGETU_DT";
    public static final String dojituDt_COLUMN  = "DOJITU_DT";
    public static final String doyoDt_COLUMN = "DOYO_DT";
    /** 対象年月日 */
    private String eigyoDt;
    /** 対象前年同月(前年同月営業日補正)年月日 */
    private String dogetuDt;
    /** 対象前年同日年月日 */
    private String dojituDt;
    /** 対象前年同曜日年月日 */
    private String doyoDt;
	/**
	 * @return dogetuDt を戻します。
	 */
	public String getDogetuDt() {
		return dogetuDt;
	}
	/**
	 * @param dogetuDt を クラス変数dogetuDtへ設定します。
	 */
	public void setDogetuDt(String dogetuDt) {
		this.dogetuDt = dogetuDt;
	}
	/**
	 * @return dojituDt を戻します。
	 */
	public String getDojituDt() {
		return dojituDt;
	}
	/**
	 * @param dojituDt を クラス変数dojituDtへ設定します。
	 */
	public void setDojituDt(String dojituDt) {
		this.dojituDt = dojituDt;
	}
	/**
	 * @return doyoDt を戻します。
	 */
	public String getDoyoDt() {
		return doyoDt;
	}
	/**
	 * @param doyoDt を クラス変数doyoDtへ設定します。
	 */
	public void setDoyoDt(String doyoDt) {
		this.doyoDt = doyoDt;
	}
	/**
	 * @return eigyoDt を戻します。
	 */
	public String getEigyoDt() {
		return eigyoDt;
	}
	/**
	 * @param eigyoDt を クラス変数eigyoDtへ設定します。
	 */
	public void setEigyoDt(String eigyoDt) {
		this.eigyoDt = eigyoDt;
	}

}
