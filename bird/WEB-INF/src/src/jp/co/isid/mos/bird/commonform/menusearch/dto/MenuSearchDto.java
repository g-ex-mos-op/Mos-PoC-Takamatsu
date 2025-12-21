/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.dto;

import java.util.List;

import jp.co.isid.mos.bird.commonform.menusearch.entity.UIMenuInfo;
import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;

/**
 * [共通]メニュー選択画面
 * 呼び出し処理用DTO
 * 
 * 使用注意：呼び出す際には、以下３点の設定が必要<br>
 *   1. 初期化 initialize();<br>
 * 　2. リクエスト元(呼び出し元)VIEW_IDを設定(検索画面から元画面に戻る際のアクションの戻り値の設定)<br>
 * 　3. リクエスト元(呼び出し元)ウィンドウIDを設定<br>
 * 
 * 画面VIEWIDはjp.co.isid.mos.bird.common.util.CommonUtilクラスに
 * 定数としてVIEW_ID_SEARCH_MENUを設定してあります。
 * 
 * 作成日:2008/08/20
 * 更新日:2008/11/20 複数メニュー選択モード追加
 * @author xkinu
 *
 */
public class MenuSearchDto {
    /** 
     * アクション区分
     * （-1:非稼動 0:初期値 1:決定 2:戻る）
     */
    private int actionKind = MenuSearchUtil.ACTION_KIND_NULL;

    /** リクエスト元(呼び出し元)VIEW_ID */
    private String requesterViewId;
    /** リクエスト元(呼び出し元)ウィンドウID */
    private int requesterWindowId;
    /** 選択モード　0:単一メニュー選択（デフォルト）　1:複数メニュー選択 */
    private int selectMode = 0;
    /** 選択メニューList（複数選択モードのみ）*/
    private List listSelectMenu;
    
    /**
     * 決定対象メニュー情報
     */
    private UIMenuInfo selectedMenuInfo;
	/**
	 * アクション区分取得処理
	 * 
	 * @return actionKind を戻します。
	 */
	public int getActionKind() {
		return actionKind;
	}
	/**
	 * アクション区分設定処理
	 * 
	 * @param actionKind を actionKindへ設定します。
	 */
	public void setActionKind(int actionKind) {
		this.actionKind = actionKind;
	}
	/**
	 * リクエスト元(呼び出し元)VIEW_ID取得処理
	 * 
	 * @return requesterViewId を戻します。
	 */
	public String getRequesterViewId() {
		return requesterViewId;
	}
	/**
	 * リクエスト元(呼び出し元)VIEW_ID設定処理
	 * @param viewId　を requesterViewIdへ設定します。
	 */
	public void setRequesterViewId(String viewId) {
		this.requesterViewId = viewId;
	}
	/**
	 * リクエスト元(呼び出し元)ウィンドウID取得処理
	 * 
	 * @return requesterWindowId を戻します。
	 */
	public int getRequesterWindowId() {
		return requesterWindowId;
	}
	/**
	 * リクエスト元(呼び出し元)ウィンドウID設定処理
	 * 
	 * @param windowId を requesterWindowIdへ設定します。
	 */
	public void setRequesterWindowId(int windowId) {
		this.requesterWindowId = windowId;
	}
    /**
     * 初期化処理<br>
     */
    public void initialize() {
    	//アクション区分を「初期化」に設定します。
        setActionKind(MenuSearchUtil.ACTION_KIND_INIT);
        setRequesterViewId(null);
        setSelectedMenuInfo(null);
        setSelectMode(MenuSearchUtil.SELECT_MODE_SINGLE);
        setListSelectMenu(null);
    }
	/**
	 * @return menuCd を戻します。
	 */
	public String getMenuCd() {
		return getSelectedMenuInfo().getMenuCd();
	}
	/**
	 * @return menuName を戻します。
	 */
	public String getMenuNameKj() {
		return getSelectedMenuInfo().getMenuNameKj();
	}
	/**
	 * 決定対象メニュー情報(エンティティ社内用メニューマスタ)取得処理
	 * @return selectedMenuInfo を戻します。
	 */
	public UIMenuInfo getSelectedMenuInfo() {
		if(selectedMenuInfo != null) {
			return selectedMenuInfo;
		}
		return new UIMenuInfo();
	}
	/**
	 * 決定対象メニュー情報(エンティティ社内用メニューマスタ)設定処理
	 * @param selectedMenuInfo を クラス変数selectedMenuInfoへ設定します。
	 */
	public void setSelectedMenuInfo(UIMenuInfo selectedMenuInfo) {
		this.selectedMenuInfo = selectedMenuInfo;
	}
    public int getSelectMode() {
        return selectMode;
    }
    public void setSelectMode(int selectMode) {
        this.selectMode = selectMode;
    }
    public List getListSelectMenu() {
        return listSelectMenu;
    }
    public void setListSelectMenu(List listSelectMenu) {
        this.listSelectMenu = listSelectMenu;
    }
}
