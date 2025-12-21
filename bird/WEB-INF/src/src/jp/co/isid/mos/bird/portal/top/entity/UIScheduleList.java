/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.entity;

import java.util.List;

/**
 * スケジュールエンティティ
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class UIScheduleList {
    public static final String TABLE = "BR70SCDL";
    private String scdlDate;
    private List title;
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
	 * @return title を戻します。
	 */
	public List getTitle() {
		return title;
	}
	/**
	 * @param title を クラス変数titleへ設定します。
	 */
	public void setTitle(List title) {
		this.title = title;
	}
}
