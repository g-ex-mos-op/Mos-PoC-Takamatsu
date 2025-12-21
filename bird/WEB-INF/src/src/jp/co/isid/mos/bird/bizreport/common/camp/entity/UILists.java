/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.entity;

import java.util.List;

/**
 * リスト情報
 * 
 * @author xkinu
 *
 */
public class UILists {
	private String key;
	private String keyName;
	private List listData;
	/**
	 * @return key を戻します。
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key 設定する key。
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return listData を戻します。
	 */
	public List getListData() {
		return listData;
	}

	/**
	 * @param list 設定する listData。
	 */
	public void setListData(List list) {
		this.listData = list;
	}

	/**
	 * @return keyName を戻します。
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName 設定する keyName。
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
}
