/**
 * 
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 作成日:2010/04/22
 * @author xkinu
 *
 */
public class CommonDto {
	/** プルダウン：社員暦 */
	private List listEmployeeYears = new ArrayList(0);
	/** プルダウン：パート暦 */
	private List listParttimerYears = new ArrayList(0);
	/**
	 * プルダウン：社員暦取得処理
	 * 
	 * @return クラス変数listEmployeeYears を戻します。
	 */
	public List getListEmployeeYears() {
		return listEmployeeYears;
	}
	/**
	 * プルダウン：社員暦設定処理
	 * @param listEmployeeYears を クラス変数listEmployeeYearsへ設定します。
	 */
	public void setListEmployeeYears(List listEmployeeYears) {
		this.listEmployeeYears = listEmployeeYears;
	}
	/**
	 * プルダウン：パート暦取得処理
	 * 
	 * @return クラス変数listParttimerYears を戻します。
	 */
	public List getListParttimerYears() {
		return listParttimerYears;
	}
	/**
	 * プルダウン：パート暦設定処理
	 * @param listParttimerYears を クラス変数listParttimerYearsへ設定します。
	 */
	public void setListParttimerYears(List listParttimerYears) {
		this.listParttimerYears = listParttimerYears;
	}
}
