/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.dto;

import java.util.List;

import jp.co.isid.mos.bird.commonform.menusearch.util.MenuSearchUtil;

/**
 * [共通]メニュー選択画面
 * Request用DTO
 * 
 * 作成日:2008/08/20
 * 更新日:2008/11/20 複数メニュー選択モード追加
 * @author xkinu
 *
 */
public class RequestDto {
    /** ウィンドウID */
    private int windowId;
    /**
     * [条件項目]メニューコード
     */ 
    private String menuCdFrom;
    /**
     * [条件項目]メニューコード
     */ 
    private String menuCdTo;
    /**
     * [条件項目]メニュー名称
     */ 
    private String menuNameKj;
    /**
     * [条件項目]メニュー分類
     */ 
    private String menuBunrui;
    /**
     * [条件項目]メニュー販売
     */ 
    private String menuHanbai;

    /**
     * [条件項目]メニュー食材区分
     */ 
    private String menuShokuzai;

    /**
     * [条件項目]メニュー販売日
     */ 
    private String menuHanbaiDt;
    /**
     * [条件項目]販売終了メニュー含むか否か
     */ 
    private boolean hanbaiEndIn = false;
    
    /**
     * [条件項目]ソート順
     */ 
    private String sortType;
    /**
     * [条件項目]LIKE時メニュー名称指定位置
     */ 
    private String likePoint;
    
    /**
     * [条件項目]50音検索文字
     */ 
    private String search50Word;
    /** 50音検索key */
    private String search50Key;
    /**
     * 50音検索タブタイプ
     * 0:カナ、1:英数記号
     */
    private String search50TabType = "0";
    /**
     * システム日付
     */
    private String sysDate;
    /**
     * 決定対象メニュー情報
     */
    private List listSearchData = null;
    
    private int selectedIndex=-1;
    
    /** 選択モード */
    private int selectMode = 0;

    /** メニュー選択インデック */
    private String menuSelectIndex;
    
	/**
	 * @return selectedIndex を戻します。
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}
	/**
	 * @param selectedIndex を クラス変数selectedIndexへ設定します。
	 */
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	/**
	 * @return menuCdFrom を戻します。
	 */
	public String getMenuCdFrom() {
		return menuCdFrom;
	}
	/**
	 * @param menuCdFrom menuCdへ設定します。
	 */
	public void setMenuCdFrom(String menuCd) {
		this.menuCdFrom = menuCd;
	}
	/**
	 * @return menuNameKj を戻します。
	 */
	public String getMenuNameKj() {
		return menuNameKj;
	}
	/**
	 * @param menuNameKj を クラス変数menuNameへ設定します。
	 */
	public void setMenuNameKj(String menuName) {
		this.menuNameKj = menuName;
	}
	/**
	 * @return menuBunrui を戻します。
	 */
	public String getMenuBunrui() {
		return menuBunrui;
	}
	/**
	 * @param menuBunrui を クラス変数menuBunruiへ設定します。
	 */
	public void setMenuBunrui(String menuBunrui) {
		this.menuBunrui = menuBunrui;
	}
	/**
	 * @return menuHanbai を戻します。
	 */
	public String getMenuHanbai() {
		return menuHanbai;
	}
	/**
	 * @param menuHanbai を クラス変数menuHanbaiへ設定します。
	 */
	public void setMenuHanbai(String menuHanbai) {
		this.menuHanbai = menuHanbai;
	}
	/**
	 * @return menuHanbaiDt を戻します。
	 */
	public String getMenuHanbaiDt() {
		return menuHanbaiDt;
	}
	/**
	 * @param menuHanbaiDt を クラス変数menuHanbaiDtへ設定します。
	 */
	public void setMenuHanbaiDt(String menuHanbaiDt) {
		this.menuHanbaiDt = menuHanbaiDt;
	}
	/**
	 * @return menuShokuzai を戻します。
	 */
	public String getMenuShokuzai() {
		return menuShokuzai;
	}
	/**
	 * @param menuShokuzai を クラス変数menuShokuzaiへ設定します。
	 */
	public void setMenuShokuzai(String menuShokuzai) {
		this.menuShokuzai = menuShokuzai;
	}
	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}
	/**
	 * @param listSearchData を クラス変数listSearchDataへ設定します。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}
	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * クリア処理
	 *
	 */
	public void clear() {
		setMenuCdFrom(null);
		setMenuCdTo(null);
		setMenuNameKj(null);
		setMenuHanbai(null);
		setMenuBunrui(null);
		setMenuShokuzai(null);
		setMenuHanbaiDt(null);
		setHanbaiEndIn(false);
		setListSearchData(null);
	}
	/**
	 * @return menuCdTo を戻します。
	 */
	public String getMenuCdTo() {
		return menuCdTo;
	}
	/**
	 * @param menuCdTo を クラス変数menuCdToへ設定します。
	 */
	public void setMenuCdTo(String menuCdTo) {
		this.menuCdTo = menuCdTo;
	}
	/**
	 * @return sysDate を戻します。
	 */
	public String getSysDate() {
		return sysDate;
	}
	/**
	 * @param sysDate を クラス変数sysDateへ設定します。
	 */
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	/**
	 * @return hanbaiEndIn を戻します。
	 */
	public boolean isHanbaiEndIn() {
		return hanbaiEndIn;
	}
	/**
	 * @param hanbaiEndIn を クラス変数hanbaiEndInへ設定します。
	 */
	public void setHanbaiEndIn(boolean hanbaiEndIn) {
		this.hanbaiEndIn = hanbaiEndIn;
	}
	/**
	 * @return sortType を戻します。
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType を クラス変数sortTypeへ設定します。
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	/**
	 * @return likePoint を戻します。
	 */
	public String getLikePoint() {
		return likePoint;
	}
	/**
	 * @param likePoint を クラス変数likePointへ設定します。
	 */
	public void setLikePoint(String likePoint) {
		this.likePoint = likePoint;
	}
	/**
	 * 50音数字文字列リスト取得処理
	 * 
	 * @return
	 */
	public List getListNum() {
		return MenuSearchUtil.getListNum();
	}
	/**
	 * 50音カタカナ文字列リスト取得処理
	 * 
	 * @return
	 */
	public List getListKana() {
		return MenuSearchUtil.getListKana();
	}
	/**
	 * 50音ローマ字文字列リスト取得処理
	 * 
	 * @return
	 */
	public List getListRoma() {
		return MenuSearchUtil.getListRoma();
	}
	/**
	 * 50音記号文字列リスト取得処理
	 * 
	 * @return
	 */
	public List getListKigo() {
		return MenuSearchUtil.getListKigo();
	}
	/**
	 * 50音検索key取得処理
	 * @return search50Key を戻します。
	 */
	public String getSearch50Key() {
		return search50Key;
	}
	/**
	 * 50音検索key設定処理
	 * @param search50Key を クラス変数search50Keyへ設定します。
	 */
	public void setSearch50Key(String search50Key) {
		this.search50Key = search50Key;
	}
	/**
	 * 50音検索タブタイプ取得処理
	 * @return search50TabType を戻します。
	 */
	public String getSearch50TabType() {
		return search50TabType;
	}
	/**
	 * 50音検索タブタイプ設定処理
	 * @param search50TabType を クラス変数search50TabTypeへ設定します。
	 */
	public void setSearch50TabType(String search50TabType) {
		this.search50TabType = search50TabType;
	}
	/**
	 * 50音検索頭文字取得処理
	 * @return search50Word を戻します。
	 */
	public String getSearch50Word() {
		return search50Word;
	}
	/**
	 * 50音検索頭文字設定処理
	 * @param search50Word を クラス変数search50Wordへ設定します。
	 */
	public void setSearch50Word(String search50Word) {
		this.search50Word = search50Word;
	}
    public int getSelectMode() {
        return selectMode;
    }
    public void setSelectMode(int selectMode) {
        this.selectMode = selectMode;
    }
    public String getMenuSelectIndex() {
        return menuSelectIndex;
    }
    public void setMenuSelectIndex(String menuSelectIndex) {
        this.menuSelectIndex = menuSelectIndex;
    }
}
