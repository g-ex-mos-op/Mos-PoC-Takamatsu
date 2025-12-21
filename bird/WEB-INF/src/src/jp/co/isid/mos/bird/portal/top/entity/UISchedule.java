/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.entity;

/**
 * スケジュールエンティティ
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class UISchedule {
    public static final String TABLE = "BR70SCDL";
    
    public static final String taishoCd_COLUMN = "TAISHO_CD";
    
    public static final String compayCd_COLUMN = "COMPANY_CD";

    public static final String scdlDate_COLUMN = "SCDL_DATE";
    
    public static final String dateIndex_COLUMN = "DATE_INDEX";
    public static final String scdlId_COLUMN = "SCDL_ID";
    
    public static final String title_COLUMN = "TITLE";

    private String taishoCd;
    private String compayCd;
    private String scdlDate;
    private String dateIndex;
    private String scdlId;
    private String title;
	/**
	 * @return compayCd を戻します。
	 */
	public String getCompayCd() {
		return compayCd;
	}
	/**
	 * @param compayCd を クラス変数compayCdへ設定します。
	 */
	public void setCompayCd(String compayCd) {
		this.compayCd = compayCd;
	}
	/**
	 * @return scdlDate を戻します。
	 */
	public String getScdlDate() {
		return scdlDate;
	}
	/**
	 * @param scdlDate を クラス変数scdlDateへ設定します。
	 */
	public void setScdlDate(String scdlDate) {
		this.scdlDate = scdlDate;
	}
	/**
	 * @return scdlId を戻します。
	 */
	public String getScdlId() {
		return scdlId;
	}
	/**
	 * @param scdlId を クラス変数scdlIdへ設定します。
	 */
	public void setScdlId(String scdlId) {
		this.scdlId = scdlId;
	}
	/**
	 * @return taishoCd を戻します。
	 */
	public String getTaishoCd() {
		return taishoCd;
	}
	/**
	 * @param taishoCd を クラス変数taishoCdへ設定します。
	 */
	public void setTaishoCd(String taishoCd) {
		this.taishoCd = taishoCd;
	}
	/**
	 * @return title を戻します。
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title を クラス変数titleへ設定します。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return dateIndex を戻します。
	 */
	public String getDateIndex() {
		return dateIndex;
	}
	/**
	 * @param dateIndex を クラス変数dateIndexへ設定します。
	 */
	public void setDateIndex(String dateIndex) {
		this.dateIndex = dateIndex;
	}
}
