/**
 * 
 */
package jp.co.isid.mos.bird.common.entity;

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
	 * @param key
	 * @param keyName
	 */
	public UILists() {
		super();
	}
	/**
	 * @param key
	 * @param keyName
	 */
	public UILists(String key, String keyName) {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
		this.key = key;
		this.keyName = keyName;
	}

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
	/**
	 * データ件数取得処理
	 * @return
	 */
	public int getListDataCnt() {
		if(listData != null) {
			return listData.size();
		}
		return 0;
	}
}
