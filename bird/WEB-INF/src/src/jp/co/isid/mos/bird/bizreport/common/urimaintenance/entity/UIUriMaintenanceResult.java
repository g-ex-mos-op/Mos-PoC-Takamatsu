/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity;

import jp.co.isid.mos.bird.common.entity.UIListDataHeader;

/**
 * 売上修正系結果保持
 * 
 * 作成日:2012/07/31
 * @author xkinu
 *
 */
public class UIUriMaintenanceResult extends UIListDataHeader {
	private UIMeisaiReviseInf reviseTotal;
	/**
	 * コンストラクター
	 */
	public UIUriMaintenanceResult() {
		super();
	}
	/**
	 * コンストラクター
	 * @param key
	 * @param keyName
	 */
	public UIUriMaintenanceResult(String key, String keyName){
		super(key, keyName);
	}
	/**
	 * @return クラス変数reviseTotal を戻します。
	 */
	public UIMeisaiReviseInf getReviseTotal() {
		return reviseTotal;
	}

	/**
	 * @param reviseTotal を クラス変数reviseTotalへ設定します。
	 */
	public void setReviseTotal(UIMeisaiReviseInf reviseTotal) {
		this.reviseTotal = reviseTotal;
	}
}
