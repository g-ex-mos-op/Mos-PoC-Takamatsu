/*
 * 作成日: 2006/03/06
 *
 */
package jp.co.isid.mos.bird.sysadmin.help.entity;

/**
 * @author xylee
 *
 */
public class HelpMenuList {
    
	
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
     * メニュー種類区分
     */
    private String menuCheck;
	
    
    /**
	 * @return menuCheck を戻します。
	 */
	public String getMenuCheck() {
		return menuCheck;
	}
	/**
	 * @param menuCheck menuId を設定。
	 */
	public void setMenuCheck(String menuCheck) {
		this.menuCheck = menuCheck;
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
}
