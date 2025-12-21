package jp.co.isid.mos.bird.bizreport.common.suiiref.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;


/**
 * 推移表共通DTO【結果情報】クラス
 * 
 * 作成日:2013/04/26
 * @author xkinu
 *
 */
public class SuiiRefResultDto {

    /** 換算済判断フラグ 2013/05追加 */
    private boolean kansanFlg = false;
    /**
     * 該当データ存在判断フラグ
     */
    private boolean existData = false;
    /**
     * Map[タブ情報]
     * タブコードをキーに各[タブ情報]が格納されています。
     */
    private Map mapMonthTab = new HashMap();
    /**
     * List[[タブ情報]]
     */
    private List listUITabData = new ArrayList(0);
    
    /**
     * List[[フォーカスタブ検索結果]]
     */
    private List listFocusTabResult = new ArrayList(0);
    /**
     * フォーカスタブ区分
     */
    private String focusTab = "";
    /**
     * 注訳文言
     */
    private String tabMsg = "";

    /**
     * 検索結果情報クリア
     */
    public void clear() {
        setExistData(false);
        setListUITabData(new ArrayList(0));
        setKansanFlg(false);
    }
	/**
	 * 該当データ存在判断フラグ
	 * @return existData を戻します。
	 */
	public boolean isExistData() {
		return existData;
	}

	/**
	 * 該当データ存在判断フラグ
	 * @param existData 設定する existData。
	 */
	public void setExistData(boolean existDataFlg) {
		this.existData = existDataFlg;
	}
	/**
	 * 換算済判断フラグ
	 * @param kansanFlg を クラス変数kansanFlgへ設定します。
	 */
	public void setKansanFlg(boolean kansanFlg) {
		this.kansanFlg = kansanFlg;
	}

	/**
	 * 換算済判断フラグ
	 * @return クラス変数kansanFlg を戻します。
	 */
	public boolean isKansanFlg() {
		return kansanFlg;
	}

	/**
	 * @return クラス変数mapMonthTab を戻します。
	 */
	public Map getMapMonthTab() {
		return mapMonthTab;
	}

	/**
	 * @param mapMonthTab を クラス変数mapMonthTabへ設定します。
	 */
	public void setMapMonthTab(Map mapMonthTab) {
		this.mapMonthTab = mapMonthTab;
	}
	/**
	 * フォーカス対象UITabData[タブ情報]取得処理
	 * @param tabCode
	 * @return
	 */
	public UITabData getUITabData(String tabCode) {
		if (mapMonthTab.containsKey(tabCode)) {
			return (UITabData)mapMonthTab.get(tabCode);
		}
		return null;
	}
	public void setUITabData(String tabCode, UITabData tabData) {
		mapMonthTab.put(tabCode, tabData);
	}
	/**
	 * @return クラス変数listTabData を戻します。
	 */
	public List getListUITabData() {
		return listUITabData;
	}

	/**
	 * @param listUITabData を クラス変数listTabDataへ設定します。
	 */
	public void setListUITabData(List listTabData) {
		this.listUITabData = listTabData;
	}
	/**
	 * 検索結果有無判断処理
	 * 
	 * @return
	 */
	public boolean isListResultEmpty() {
		return listUITabData == null || listUITabData.isEmpty();			
	}

	/**
	 * フォーカスタブ
	 * @return クラス変数focusTab を戻します。
	 */
	public String getFocusTab() {
		return focusTab;
	}

	/**
	 * フォーカスタブ
	 * @param focusTab を クラス変数focusTabへ設定します。
	 */
	public void setFocusTab(String focusTab) {
		this.focusTab = focusTab;
	}
	/**
	 * UITabData[フォーカスタブ]
	 * @return
	 */
	public UITabData getFocusUITabData() {
		return getUITabData(getFocusTab());
	}

	/**
	 * 注訳文言
     * タブエリア表示メッセージ取得処理
     * 
     * 1.指定月が当月（システム日付を含む月）の場合は、「12ヶ月合計と平均には当月は含みません」と固定メッセージを表示する。
     * 2.指定月が前月以前の場合は、「12ヶ月合計と平均には前年同月は含みません」と固定メッセージを表示する。
	 * @return クラス変数tabMsg を戻します。
	 */
	public String getTabMsg() {
		return tabMsg;
	}

	/**
	 * 注訳文言
	 * @param tabMsg を クラス変数tabMsgへ設定します。
	 */
	public void setTabMsg(String tabMsg) {
		this.tabMsg = tabMsg;
	}
	/**
	 * List[[フォーカスタブ検索結果]]
	 * @return クラス変数listFocusTabResult を戻します。
	 */
	public List getListFocusTabResult() {
		return listFocusTabResult;
	}
	/**
	 * List[[フォーカスタブ検索結果]]
	 * @param listFocusTabResult を クラス変数listFocusTabResultへ設定します。
	 */
	public void setListFocusTabResult(List listFocusTabResult) {
		this.listFocusTabResult = listFocusTabResult;
	}
}