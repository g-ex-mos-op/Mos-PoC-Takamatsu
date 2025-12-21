/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集計区分結果情報保持エンティティ
 * 
 * 作成日:2012/07/31
 * @author xkinu
 *
 */
public class SyukeiKbnResultData {
	/** 集計区分コード */
	private String cd;
	/** 集計区分名称 */
	private String name;
	/** タブ別UIListDataHeader情報保持リスト */
	private List listTabResult = new ArrayList(0);
	/** タブ別UIListDataHeader情報保持マップ */
	private Map mapTabResult = new HashMap();
	
	public int getTabCnt() {
		if(listTabResult != null) {
			return listTabResult.size();
		}
		return 0;
	}
	/**
	 * @return クラス変数exist を戻します。
	 */
	public boolean isExist() {
		return 0<getTabCnt();
	}
	/**
	 * @param exist を クラス変数existへ設定します。
	 */
	public void setExist(boolean exist) {
	}
	/**
	 * @return クラス変数cd を戻します。
	 */
	public String getCd() {
		return cd;
	}
	/**
	 * @param cd を クラス変数cdへ設定します。
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}
	/**
	 * @return クラス変数listTabResult を戻します。
	 */
	public List getListTabResult() {
		return listTabResult;
	}
	/**
	 * @param listTabResult を クラス変数listTabResultへ設定します。
	 */
	public void setListTabResult(List listTabResult) {
		this.listTabResult = listTabResult;
	}
	/**
	 * @return クラス変数mapTabResult を戻します。
	 */
	public Map getMapTabResult() {
		return mapTabResult;
	}
	/**
	 * @param mapTabResult を クラス変数mapTabResultへ設定します。
	 */
	public void setMapTabResult(Map mapTabResult) {
		this.mapTabResult = mapTabResult;
	}
	/**
	 * @return クラス変数name を戻します。
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name を クラス変数nameへ設定します。
	 */
	public void setName(String name) {
		this.name = name;
	}
}
