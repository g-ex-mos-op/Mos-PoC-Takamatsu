/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportref.dto;

import java.util.List;

/**
 * セッション情報保持ＤＴＯ
 * 
 * 作成日:2010/10/25
 * @author xkinu
 *
 */
public class SessionDto {
	/**
	 * List[[対象条件]]
	 * 各インデックスにCODE【対象条件】が格納されています。
	 */
	private List listTaishoJoken;
	/**
	 * List[[表示対象]]
	 * 各インデックスにENTITY【リスト情報】が格納されています。
	 */
	private List listHyojiTaisho;
	/**
	 * 表示対象リスト取得処理
	 * @return　List[[表示対象]]
	 */
	public List getListHyojiTaisho() {
		return listHyojiTaisho;
	}
	/**
	 * 表示対象リスト設定処理
	 * @param listHyojiTaisho　List[[表示対象]]
	 */
	public void setListHyojiTaisho(List listHyojiTaisho) {
		this.listHyojiTaisho = listHyojiTaisho;
	}
	/**
	 * 対象条件リスト取得処理
	 * @return List[[対象条件]]
	 */
	public List getListTaishoJoken() {
		return listTaishoJoken;
	}
	/**
	 * 対象条件リスト設定処理
	 * @param listTaishoJoken　List[[対象条件]]
	 */
	public void setListTaishoJoken(List listTaishoJoken) {
		this.listTaishoJoken = listTaishoJoken;
	}
	public void clear() {
        setListTaishoJoken(null); //対象条件リスト
		setListHyojiTaisho(null); //表示対象リスト

	}
}
