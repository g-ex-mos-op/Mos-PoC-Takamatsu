package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity;

import jp.co.isid.mos.bird.common.entity.UILists;

/**
 * タブ別情報エンティティクラス
 * @author xogawa
 *
 */
public class MeisaiTabInfo {

	/**
	 * タブ別ヘッダー情報
	 */
	private UILists uiTabHeader;
	
	/**
	 * タブ別データ情報
	 */
	private UILists uiTabData;

	/**
	 * タブ別ヘッダー情報を取得する
	 * @return uiTabHeader タブ別ヘッダー情報
	 */
	public UILists getUiTabHeader() {
		return uiTabHeader;
	}

	/**
	 * タブ別ヘッダー情報を設定する
	 * @param uiTabHeader タブ別ヘッダー情報
	 */
	public void setUiTabHeader(UILists uiTabHeader) {
		this.uiTabHeader = uiTabHeader;
	}

	/**
	 * タブ別データ情報を取得する
	 * @return uiTabData タブ別データ情報
	 */
	public UILists getUiTabData() {
		return uiTabData;
	}

	/**
	 * タブ別データ情報を設定する
	 * @param uiTabData タブ別データ情報
	 */
	public void setUiTabData(UILists uiTabData) {
		this.uiTabData = uiTabData;
	}

	
	
}
