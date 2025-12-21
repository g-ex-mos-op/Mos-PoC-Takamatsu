/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dto;

import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;

/**
 * 機能設定]【売上速報設定】
 * Request保持データDTO
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class RequestDto {
	/** セッションKey */
	private String sesstionKey;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private int windowId;
    /* 対象レポート年月 */
    private String taishoYm;
    /** メイン選択タブ */
    private String selectedTabMain;
    /** サブ選択タブ */
    private String selectedTabSub;
    /** メニュー選択対象メニューインデックス */
    private int selectedSearchMenuIndex;
    private String checkMessage;
    
    /**
	 * @return checkMessage を戻します。
	 */
	public String getCheckMessage() {
		return checkMessage;
	}
	/**
	 * @param checkMessage を クラス変数checkMessageへ設定します。
	 */
	public void setCheckMessage(String checkMessage) {
		this.checkMessage = checkMessage;
	}
	public void clear() {
    	taishoYm = "";
    	selectedTabMain = UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT;
    	selectedTabSub = UrisokuRegistUtil.FRAME_KBN_LEFT;
    	selectedSearchMenuIndex = 0;
    }
	/**
	 * @return taishoYm を戻します。
	 */
	public String getTaishoYm() {
		return taishoYm;
	}
	/**
	 * @param taishoYm taishoYmへ設定します。
	 */
	public void setTaishoYm(String taishoYm) {
		this.taishoYm = taishoYm;
	}
	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * サブ選択タブ取得処理
	 * 
	 * 選択中のメインタブの中の選択中タブの判断値を返します。
	 * 
	 * @return selectedTabSub を戻します。
	 */
	public String getSelectedTabSub() {
		return selectedTabSub;
	}
	/**
	 * サブ選択タブ設定処理
	 * 
	 * 選択中のメインタブの中の選択中タブの判断値を設定します。
	 * 
	 * @param selectedTabSub selectedTabSubへ設定します。
	 */
	public void setSelectedTabSub(String selectedTabSub) {
		this.selectedTabSub = selectedTabSub;
	}
	/**
	 * メイン選択タブ
	 * @return selectedTabMain を戻します。
	 */
	public String getSelectedTabMain() {
		return selectedTabMain;
	}
	/**
	 * @param selectedTabMain selectedTabMainへ設定します。
	 */
	public void setSelectedTabMain(String selectedTabMain) {
		this.selectedTabMain = selectedTabMain;
	}
	/**
	 * @return selectedSearchMenuIndex を戻します。
	 */
	public int getSelectedSearchMenuIndex() {
		return selectedSearchMenuIndex;
	}
	/**
	 * @param selectedSearchMenuIndex を クラス変数selectedSearchMenuIndexへ設定します。
	 */
	public void setSelectedSearchMenuIndex(int selectedSearchMenuIndex) {
		this.selectedSearchMenuIndex = selectedSearchMenuIndex;
	}
	/**
	 * @return sesstionKey を戻します。
	 */
	public String getSesstionKey() {
		return sesstionKey;
	}
	/**
	 * @param sesstionKey を クラス変数sesstionKeyへ設定します。
	 */
	public void setSesstionKey(String sesstionKey) {
		this.sesstionKey = sesstionKey;
	}

}
