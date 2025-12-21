/*
 * 作成日: 2006/02/27
 *
 */
package jp.co.isid.mos.bird.sysadmin.help.entity;

/**
 * @author xylee
 *
 */
public class UIBirdMenu {
    
	public static final String TABLE = "BR02BMNU";
    
    public static final String menuId_COLUMN       = "MENU_ID";
    
    public static final String menuName_COLUMN     = "MENU_NAME";
    
    public static final String subMenuId_COLUMN    = "SUB_MENU_ID";
    
    public static final String subMenuName_COLUMN  = "SUB_MENU_NAME";
    
    public static final String viewId_COLUMN       = "VIEW_ID";
    
    public static final String sortSeq_COLUMN      = "SORT_SEQ";

    public static final String enableFlg_COLUMN    = "ENABLE_FLG";

    public static final String extraFlg_COLUMN     = "EXTRA_FLG";

    public static final String customizeFlg_COLUMN = "CUSTOMIZE_FLG";

    /**
     * メニューＩＤ
     */
    private String menuId;
    
    /**
     * メニュー名
     */
    private String menuName;
    
    /**
     * サブメニューＩＤ
     */
    private String subMenuId;
    
    /**
     * サブメニュー名
     */
    private String subMenuName;
    
    /**
     * viewId
     */
    private String viewId;
    
    /**
     * ソート順
     */
    private String sortSeq;
    
    /**
     * パイル検査
     */
    private boolean fileCheck;
	
    /**
     * 使用可能フラグ(初期フラグ)
     */
    private String enableFlg;

    /**
     * 上限拡張フラグ
     */
    private String extraFlg;

    /**
     * 個別設定フラグ
     */
    private String customizeFlg;

    
    
    /**
	 * @return fileCheck を戻します。
	 */
	public boolean getFileCheck() {
		return fileCheck;
	}
	/**
	 * @paramfileCheck を設定。
	 */
	public void setFileCheck(boolean fileCheck) {
		this.fileCheck = fileCheck;
	}
    /**
	 * @return menuId を戻します。
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId menuId を設定。
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
	 * @param menuName menuName を設定。
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return sortSeq を戻します。
	 */
	public String getSortSeq() {
		return sortSeq;
	}
	/**
	 * @param sortSeq sortSeq を設定。
	 */
	public void setSortSeq(String sortSeq) {
		this.sortSeq = sortSeq;
	}
	/**
	 * @return subMenuId を戻します。
	 */
	public String getSubMenuId() {
		return subMenuId;
	}
	/**
	 * @param subMenuId subMenuId を設定。
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
	 * @param subMenuName subMenuName を設定。
	 */
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}
	/**
	 * @param viewId viewId を設定。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
    /**
     * @return customizeFlg を戻します。
     */
    public String getCustomizeFlg() {
        return customizeFlg;
    }
    /**
     * @param customizeFlg 設定する customizeFlg。
     */
    public void setCustomizeFlg(String customizeFlg) {
        this.customizeFlg = customizeFlg;
    }
    /**
     * @return enableFlg を戻します。
     */
    public String getEnableFlg() {
        return enableFlg;
    }
    /**
     * @param enableFlg 設定する enableFlg。
     */
    public void setEnableFlg(String enableFlg) {
        this.enableFlg = enableFlg;
    }
    /**
     * @return extraFlg を戻します。
     */
    public String getExtraFlg() {
        return extraFlg;
    }
    /**
     * @param extraFlg 設定する extraFlg。
     */
    public void setExtraFlg(String extraFlg) {
        this.extraFlg = extraFlg;
    }
}
