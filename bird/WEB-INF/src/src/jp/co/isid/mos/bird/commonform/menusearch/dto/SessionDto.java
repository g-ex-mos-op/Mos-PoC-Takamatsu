/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;

/**
 * [共通]メニュー選択画面
 * Session用DTO
 * 
 * 作成日:2008/08/20
 * 更新日:2008/11/20 複数メニュー選択モード追加
 * @author xkinu
 *
 */
public class SessionDto {
    /* 現行セッションKey */
    private String nowSessionKey;
    /* セッションKey保持Map */
    private Map sessionKey = new HashMap();
    
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /**
     * 最大データ保持件数
     */
    private int maxSize=5;
    /** リクエスト元(呼び出し元)VIEW_ID */
    private Map requesterViewId = new HashMap();
    /** リクエスト元(呼び出し元)ウィンドウID */
    private Map requesterWindowId = new HashMap();
    
    /**
     * [条件項目]プルダウン用メニュー分類リスト
     */ 
    private List listMenuBunrui = new ArrayList(0);
    /**
     * [条件項目]開始メニューコード
     */ 
    private Map menuCdFrom = new HashMap();
    /**
     * [条件項目]終了メニューコード
     */ 
    private Map menuCdTo = new HashMap();
    /**
     * [条件項目]メニュー名称
     */ 
    private Map menuNameKj = new HashMap();
    /**
     * [条件項目]メニュー分類
     */ 
    private Map menuBunrui = new HashMap();
    /**
     * [条件項目]メニュー販売
     */ 
    private Map menuHanbai = new HashMap();

    /**
     * [条件項目]メニュー食材区分
     */ 
    private Map menuShokuzai = new HashMap();

    /**
     * [条件項目]メニュー販売日
     */ 
    private Map menuHanbaiDt = new HashMap();
    
    /**
     * [条件項目]販売終了メニュー含むか否か
     */ 
    private Map hanbaiEndIn = new HashMap();
    /**
     * [条件項目]ソート順
     */ 
    private Map sortType = new HashMap();
    /**
     * [条件項目]LIKE時メニュー名称指定位置
     */ 
    private Map likePoint = new HashMap();
    
    /* 50音検索key */
    private Map indexSearchKey = new HashMap();
    
    /**
     * 検索結果
     */
    private Map listSearchData = new LinkedHashMap();
    
    /**
     * 選択モード
     */
    private Map selectMode = new HashMap();
    
    
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public int updateWindowid() {
       return createWindowId();
    }
	/**
	 * @return menuBunrui を戻します。
	 */
	public String getMenuBunrui(int windowId) {
		return (String)menuBunrui.get(new Integer(windowId));
	}

	/**
	 * @param menuBunrui を クラス変数menuBunruiへ設定します。
	 */
	public void setMenuBunrui(int windowId, String menuBunrui) {
		this.menuBunrui.put(new Integer(windowId), menuBunrui);
	}

	/**
	 * @return menuCd を戻します。
	 */
	public String getMenuCdFrom(int windowId) {
		return (String)menuCdFrom.get(new Integer(windowId));
	}

	/**
	 * @param menuCd を クラス変数menuCdへ設定します。
	 */
	public void setMenuCdFrom(int windowId, String menuCd) {
		this.menuCdFrom.put(new Integer(windowId), menuCd);
	}
	/**
	 * @return menuCd を戻します。
	 */
	public String getMenuCdTo(int windowId) {
		return (String)menuCdTo.get(new Integer(windowId));
	}

	/**
	 * @param menuCd を クラス変数menuCdへ設定します。
	 */
	public void setMenuCdTo(int windowId, String menuCd) {
		this.menuCdTo.put(new Integer(windowId), menuCd);
	}

	/**
	 * @return menuHanbai を戻します。
	 */
	public String getMenuHanbai(int windowId) {
		return (String)menuHanbai.get(new Integer(windowId));
	}

	/**
	 * @param menuHanbai を クラス変数menuHanbaiへ設定します。
	 */
	public void setMenuHanbai(int windowId, String menuHanbai) {
		this.menuHanbai.put(new Integer(windowId), menuHanbai);
	}

	/**
	 * @return menuHanbaiDt を戻します。
	 */
	public String getMenuHanbaiDt(int windowId) {
		return (String)menuHanbaiDt.get(new Integer(windowId));
	}

	/**
	 * @param menuHanbaiDt を クラス変数menuHanbaiDtへ設定します。
	 */
	public void setMenuHanbaiDt(int windowId, String menuHanbaiDt) {
		this.menuHanbaiDt.put(new Integer(windowId), menuHanbaiDt);
	}

	/**
	 * @return menuNameKj を戻します。
	 */
	public String getMenuNameKj(int windowId) {
		return (String)menuNameKj.get(new Integer(windowId));
	}

	/**
	 * @param menuNameKj を クラス変数menuNameへ設定します。
	 */
	public void setMenuNameKj(int windowId, String menuName) {
		this.menuNameKj.put(new Integer(windowId), menuName);
	}

	/**
	 * @return menuShokuzai を戻します。
	 */
	public String getMenuShokuzai(int windowId) {
		return (String)menuShokuzai.get(new Integer(windowId));
	}

	/**
	 * @param menuShokuzai を クラス変数menuShokuzaiへ設定します。
	 */
	public void setMenuShokuzai(int windowId, String menuShokuzai) {
		this.menuShokuzai.put(new Integer(windowId), menuShokuzai);
	}
	/**
	 * 販売終了メニュー含むか否かの判断値取得処理
	 * 
	 * @return isIn を戻します。
	 */
	public boolean isHanbaiEndIn(int windowId) {
		return ((Boolean)hanbaiEndIn.get(new Integer(windowId))).booleanValue();
	}

	/**
	 * 販売終了メニュー含むか否かの判断値取得処理
	 * 
	 * @param isIn を クラス変数hanbaiEndInへ設定します。
	 */
	public void setHanbaiEndIn(int windowId, boolean isIn) {
		this.hanbaiEndIn.put(new Integer(windowId), new Boolean(isIn));
	}


	/**
	 * リクエスト元(呼び出し元)VIEW_ID取得処理
	 * 
	 * @return requesterViewId を戻します。
	 */
	public String getRequesterViewId(int windowId) {
		return (String)requesterViewId.get(new Integer(windowId));
	}

	/**
	 * リクエスト元(呼び出し元)VIEW_ID設定処理
	 * 
	 * @param requesterViewId を クラス変数requesterViewIdへ設定します。
	 */
	public void setRequesterViewId(int windowId, String requesterViewId) {
		this.requesterViewId.put(new Integer(windowId), requesterViewId);
	}

	/**
	 * リクエスト元(呼び出し元)ウィンドウID取得処理
	 * 
	 * @return requesterWindowId を戻します。
	 */
	public int getRequesterWindowId(int windowId) {
		return ((Integer)requesterWindowId.get(new Integer(windowId))).intValue();
	}

	/**
	 * リクエスト元(呼び出し元)ウィンドウID設定処理
	 * 
	 * @param requesterWindowId を クラス変数requesterWindowIdへ設定します。
	 */
	public void setRequesterWindowId(int windowId, int requesterWindowId) {
		this.requesterWindowId.put(new Integer(windowId), new Integer(requesterWindowId));
	}
	/**
	 * 50音検索key取得処理
	 * @return indexSearchKey を戻します。
	 */
	public String getIndexSearchKey(int windowId) {
		return (String)indexSearchKey.get(new Integer(windowId));
	}
	/**
	 * 50音検索key設定
	 * @param indexSearchKey を クラス変数indexSearchKeyへ設定します。
	 */
	public void setIndexSearchKey(int windowId, String indexSearchKey) {
		this.indexSearchKey.put(new Integer(windowId), indexSearchKey);
	}
	/**
	 * @return sortType を戻します。
	 */
	public String getSortType(int windowId) {
		return (String)sortType.get(new Integer(windowId));
	}
	/**
	 * @param sortType を クラス変数sortTypeへ設定します。
	 */
	public void setSortType(int windowId, String sortType) {
		this.sortType.put(new Integer(windowId), sortType);
	}
	/**
	 * @return likePoint を戻します。
	 */
	public String getLikePoint(int windowId) {
		return (String)likePoint.get(new Integer(windowId));
	}

	/**
	 * @param likePoint を クラス変数likePointへ設定します。
	 */
	public void setLikePoint(int windowId, String likePoint) {
		this.likePoint.put(new Integer(windowId), likePoint);
	}

	/**
	 * @return maxSize を戻します。
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize 設定する maxSize。
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	/**
	 * @return maxWindowId を戻します。
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * @param maxWindowId 設定する maxWindowId。
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}

	/**
	 * @return nowSessionKey を戻します。
	 */
	public String getNowSessionKey() {
		return nowSessionKey;
	}

	/**
	 * @param nowSessionKey 設定する nowSessionKey。
	 */
	public void setNowSessionKey(String nowSessionKey) {
		this.nowSessionKey = nowSessionKey;
	}

	/**
	 * セッションKey保持MapからセッションKeyの取得
	 * @return sessionKey を戻します。
	 */
	public String getSessionKey(int windowId) {
		return (String)sessionKey.get(new Integer(windowId));
	}
    /**
     * セッションKey保持MapへセッションKeyの設定
     * @param sessionKey sessionKey を設定。
     */
    public void setSessionKey(int windowId, String sessionKey) {
        this.sessionKey.put(new Integer(windowId), sessionKey);
    }
	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData(int windowId) {
		return (List)listSearchData.get(new Integer(windowId));
	}

	/**
	 * 検索結果設定処理
	 * 
	 * @param windowId
	 * @param listSearchData
	 */
	public void setListSearchData(int windowId, List listSearchData) {
        // 現在ウインドウIDのデータを保持している場合
        if (this.listSearchData.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            this.listSearchData.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (this.listSearchData.size() >= getMaxSize()) {
            // 最古データを削除
            this.listSearchData.remove(this.listSearchData.keySet().toArray()[0]);
        }       	
    	this.listSearchData.put(new Integer(windowId), listSearchData);
	}
	/**
	 * 検索済み検索条件値保持処理
	 * 
	 * @param requestDto
	 */
	public void setSearchedData(RequestDto requestDto) {
		int windowId = requestDto.getWindowId();
		setMenuCdFrom(windowId, requestDto.getMenuCdFrom());
		setMenuCdTo(windowId, requestDto.getMenuCdTo());
		setMenuNameKj(windowId, requestDto.getMenuNameKj());
		setMenuBunrui(windowId, requestDto.getMenuBunrui());
		setMenuHanbai(windowId, requestDto.getMenuHanbai());
		setMenuShokuzai(windowId, requestDto.getMenuShokuzai());
		setMenuHanbaiDt(windowId, requestDto.getMenuHanbaiDt());
		setHanbaiEndIn(windowId, requestDto.isHanbaiEndIn());
		setSortType(windowId, requestDto.getSortType());
		setLikePoint(windowId, requestDto.getLikePoint());
		setListSearchData(windowId, requestDto.getListSearchData());
	}

	/**
	 * @return listMenuBunrui を戻します。
	 */
	public List getListMenuBunrui() {
		return listMenuBunrui;
	}

	/**
	 * @param listMenuBunrui を クラス変数listMenuBunruiへ設定します。
	 */
	public void setListMenuBunrui(List listMenuBunrui) {
		this.listMenuBunrui = listMenuBunrui;
	}

    public int getSelectMode(int windowId) {
        if (selectMode.get(new Integer(windowId)) == null) {
            return MenuSearchUtil.SELECT_MODE_SINGLE;
        }
        return ((Integer) selectMode.get(new Integer(windowId))).intValue();
    }

    public void setSelectMode(int windowId, int selectMode) {
        this.selectMode.put(new Integer(windowId), new Integer(selectMode));
    }

}
