/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1st;
import jp.co.isid.mos.bird.storeinfo.eiseiref.entity.TrnBd33shtb;

/**
 * DTO【検索結果】
 * 
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class ResultDto extends RequestDto {
	/** データ存在フラグ */
	private boolean isExistsData = false;
	/** List[[検索結果]] */
	private List listData = new ArrayList(0);
    /**
     * ダウンロードファイル種類
     * 
     * 値:jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1stのコード
     */
    private String tabKey = "";
    
    /**
     * エンティティ：TrnBd33shtb[PDF店舗衛生情報店情報]
     */
    private TrnBd33shtb bd33shtbMiseInfo;
	/**
	 * コンストラクター
	 */
	public ResultDto() {
		super();
	}
	/**
	 * コンストラクター
	 * @param requestDto
	 */
	public ResultDto(RequestDto requestDto) {
		super.setCompanyCd(requestDto.getCompanyCd());
		super.setMiseCd(requestDto.getMiseCd());
		super.setNendo(requestDto.getNendo());
	}
	/**
	 * クリア処理
	 *
	 */
	public void reset() {
		setTabKey("");
		setExistsData(false);
		setListData(new ArrayList(0));
		setBd33shtbMiseInfo(null);
	}
	/**
     * 値:jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1stのコード
	 * @return クラス変数tabKey を戻します。
	 */
	public String getTabKey() {
		return tabKey;
	}
	/**
	 * ヘッダー情報取得
	 * @return
	 */
	public TrnBd33shtb getHeaderData() {
		List listHeader = Tab1st.getUILists(Tab1st.TAB_2, getListData()).getListData();
		if(listHeader.isEmpty()){
			return bd33shtbMiseInfo;
		}
		return (TrnBd33shtb) listHeader.get(0);
	}
	/**
	 * @param tabKey を クラス変数tabKeyへ設定します。
	 */
	public void setTabKey(String tabKey) {
		this.tabKey = tabKey;
	}
	/**
	 * @return クラス変数listData を戻します。
	 */
	public List getListData() {
		return listData;
	}
	/**
	 * @param list を クラス変数listDataへ設定します。
	 */
	public void setListData(List list) {
		this.listData = list;
	}
	/**
	 * @return クラス変数isExistsData を戻します。
	 */
	public boolean isExistsData() {
		return isExistsData;
	}
	/**
	 * @param isExistsData を クラス変数isExistsDataへ設定します。
	 */
	public void setExistsData(boolean isExistsData) {
		this.isExistsData = isExistsData;
	}
	/**
	 * TrnBd33shtb[PDF店舗衛生情報店情報]
	 * @return クラス変数bd33shtbMiseInfo を戻します。
	 */
	public TrnBd33shtb getBd33shtbMiseInfo() {
		return bd33shtbMiseInfo;
	}
	/**
	 * TrnBd33shtb[PDF店舗衛生情報店情報]
	 * @param entity を クラス変数bd33shtbMiseInfoへ設定します。
	 */
	public void setBd33shtbMiseInfo(TrnBd33shtb entity) {
		this.bd33shtbMiseInfo = entity;
	}

}
