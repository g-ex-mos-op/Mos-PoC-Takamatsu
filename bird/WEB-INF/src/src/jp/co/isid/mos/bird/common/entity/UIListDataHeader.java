/**
 * 
 */
package jp.co.isid.mos.bird.common.entity;

import java.util.List;

/**
 * ヘッダーリスト付情報
 * 
 * @author xkinu
 *
 */
public class UIListDataHeader extends UILists {
	private List listHeader;
	/**
	 * @param key
	 * @param keyName
	 */
	public UIListDataHeader() {
		super();
	}
	/**
	 * @param key
	 * @param keyName
	 */
	public UIListDataHeader(String key, String keyName) {
		super(key, keyName);
	}
	/**
	 * @return クラス変数listHeader を戻します。
	 */
	public List getListHeader() {
		return listHeader;
	}
	/**
	 * @param listHeader を クラス変数listHeaderへ設定します。
	 */
	public void setListHeader(List listHeader) {
		this.listHeader = listHeader;
	}
	public int getListHeaderCnt() {
		if(listHeader != null) {
			return listHeader.size();
		}
		return 0;
	}
}
