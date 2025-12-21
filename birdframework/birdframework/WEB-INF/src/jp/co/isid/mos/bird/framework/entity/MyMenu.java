/**
 * 
 */
package jp.co.isid.mos.bird.framework.entity;

/**
 * マイメニュー情報
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class MyMenu extends TrnMyMenu {
    public static final String menuId_COLUMN = "MENU_ID";

    public static final String menuName_COLUMN = "MENU_NAME";

    public static final String subMenuId_COLUMN = "SUB_MENU_ID";
    
    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";

    public static final String screenId_COLUMN = "SCREEN_ID";
    
    public static final String param_COLUMN = "PARAM";

    public static final String menuDispKbn_COLUMN = "MENU_DISP_KBN";
    /**
     * メニューID
     */
    private String menuId;
    
    /**
     * メニュー名称
     */
    private String menuName;
    
    /**
     * サブメニューID
     */
    private String subMenuId;
    
    /**
     * サブメニュー名称
     */
    private String subMenuName;

    /**
     * 画面ID
     */
    private String screenId;
    
    /**
     * パラメータ
     * ※外部リンクの場合リンクIDが設定されています。
     */
    private String param;

    /**
     * メニュー表示区分
     * 0:非表示、1:表示(デフォルト)、2:表示(外部システムリンク)、3:非表示(外部システムリンク)
     */
    private String menuDispKbn;
	/**
	 * @return menuDispKbn を戻します。
	 */
	public String getMenuDispKbn() {
		return menuDispKbn;
	}

	/**
	 * @param menuDispKbn を クラス変数menuDispKbnへ設定します。
	 */
	public void setMenuDispKbn(String menuDispKbn) {
		this.menuDispKbn = menuDispKbn;
	}

	/**
	 * @return param を戻します。
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param を クラス変数paramへ設定します。
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return menuId を戻します。
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId を クラス変数menuIdへ設定します。
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return menuName を戻します。
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName を クラス変数menuNameへ設定します。
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return subMenuId を戻します。
	 */
	public String getSubMenuId() {
		return subMenuId;
	}

	/**
	 * @param subMenuId を クラス変数subMenuIdへ設定します。
	 */
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}

	/**
	 * @return subMenuName を戻します。
	 */
	public String getSubMenuName() {
		return subMenuName;
	}

	/**
	 * @param subMenuName を クラス変数subMenuNameへ設定します。
	 */
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	/**
	 * @return screenId を戻します。
	 */
	public String getScreenId() {
		return screenId;
	}

	/**
	 * @param screenId を クラス変数screenIdへ設定します。
	 */
	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

}
