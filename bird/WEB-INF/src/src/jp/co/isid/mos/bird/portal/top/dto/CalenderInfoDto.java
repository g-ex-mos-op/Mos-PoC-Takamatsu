/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * カレンダー情報画面用データ保持DTO
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public class CalenderInfoDto {
	/** 対象年月 */
	private String targetMonth;
	/** 対象年月の日時データ保持リスト*/
	private List listDayInfo = new ArrayList(0);
	
	/** 対象年月データ保持リスト*/
	private List listMonthInfo = new ArrayList(0);
	/**対象年月日が照会範囲の開始年月か否かの判断値*/
	boolean targetMonthFrom = false;
	/**対象年月日が照会範囲の終了年月か否かの判断値*/
	boolean targetMonthTo = false;

	/**
	 * @return listDayInfo を戻します。
	 */
	public List getListDayInfo() {
		return listDayInfo;
	}

	/**
	 * @param listDayInfo を クラス変数listDayInfoへ設定します。
	 */
	public void setListDayInfo(List listDayInfo) {
		this.listDayInfo = listDayInfo;
	}

	/**
	 * @return targetMonth を戻します。
	 */
	public String getTargetMonth() {
		return targetMonth;
	}

	/**
	 * @param targetMonth を クラス変数targetMonthへ設定します。
	 */
	public void setTargetMonth(String targetMonth) {
		this.targetMonth = targetMonth;
	}

	/**
	 * @return listMonthInfo を戻します。
	 */
	public List getListMonthInfo() {
		return listMonthInfo;
	}

	/**
	 * @param listMonthInfo を クラス変数listMonthInfoへ設定します。
	 */
	public void setListMonthInfo(List listMonthInfo) {
		this.listMonthInfo = listMonthInfo;
	}

	/**
	 * @param targetMonthFrom を クラス変数targetMonthFromへ設定します。
	 */
	public void setTargetMonthFrom(boolean targetMonthFrom) {
		this.targetMonthFrom = targetMonthFrom;
	}

	/**
	 * @param targetMonthTo を クラス変数targetMonthToへ設定します。
	 */
	public void setTargetMonthTo(boolean targetMonthTo) {
		this.targetMonthTo = targetMonthTo;
	}

	/**
	 * 対象年月日が照会範囲の開始年月か否かの判断
	 * @return targetMonthFrom を戻します。
	 */
	public boolean isTargetMonthFrom() {
		return targetMonthFrom;
	}

	/**
	 * 対象年月照会範囲の開始年月か否かの判断
	 * @return targetMonthTo を戻します。
	 */
	public boolean isTargetMonthTo() {
		return targetMonthTo;
	}
}
