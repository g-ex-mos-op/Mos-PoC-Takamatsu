/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;

/**
 * @author xkinu
 *
 */
public class UITabSearchData extends UILists {
	/**
	 * 照会ヘッダー部の対象期間
	 * 
	 * 対象日：yyyy/mm/dd
	 * 期間合計：yyyy/mm/dd～yyyy/mm/dd
	 */
	private String taishoKikan;
	
	/**
	 * 照会ヘッダー部の対象店舗数
	 */
	private BigDecimal taishoTenpoCnt = new BigDecimal("-1");
	
	/**
	 * タブ共通項目個数
	 */
	private int commonKoumokuSize = 0;
	/**
	 * 項目個数
	 */
	private int koumokuSize = 0;
	/**
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan() {
		return taishoKikan;
	}

	/**
	 * @param taishoKikan 設定する taishoKikan。
	 */
	public void setTaishoKikan(String taishoKikan) {
		this.taishoKikan = taishoKikan;
	}

	/**
	 * @return taishoTenpoCnt を戻します。
	 */
	public BigDecimal getTaishoTenpoCnt() {
		return taishoTenpoCnt;
	}

	/**
	 * @param taishoTenpoCnt 設定する taishoTenpoCnt。
	 */
	public void setTaishoTenpoCnt(BigDecimal taishoTenpoCnt) {
		this.taishoTenpoCnt = taishoTenpoCnt;
	}

	/**
	 * @return commonKoumokuSize を戻します。
	 */
	public int getCommonKoumokuSize() {
		return commonKoumokuSize;
	}

	/**
	 * @param commonKoumokuSize 設定する commonKoumokuSize。
	 */
	public void setCommonKoumokuSize(int commonKoumokuSize) {
		this.commonKoumokuSize = commonKoumokuSize;
	}

	/**
	 * @return koumokuSize を戻します。
	 */
	public int getKoumokuSize() {
		return koumokuSize;
	}

	/**
	 * @param koumokuSize 設定する koumokuSize。
	 */
	public void setKoumokuSize(int koumokuSize) {
		this.koumokuSize = koumokuSize;
	}
	/**
	 * 全項目個数
	 * @return
	 */
	public int getAllKoumokuSize() {
		return getCommonKoumokuSize()+getKoumokuSize();
	}
	public boolean isSearchDataExist() {
		if(getListData() == null || getListData().size() < 1) {
			return false;
		}
		return true;
	}
}
