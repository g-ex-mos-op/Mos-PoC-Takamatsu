package jp.co.isid.mos.bird.framework.entity;

public class CtlDisableMenu {
    
    public static final String TABLE = "BR06USAC";
    
    public static final String menuId_COLUMN = "MENU_ID";
    
    public static final String subMenuId_COLUMN = "SUB_MENU_ID";
    
    /**
     * 親メニューＩＤ
     */
    private String menuId;
    
    /**
     * サブメニューＩＤ
     */
    private String subMenuId;
    
    /**
     * 親メニューＩＤを取得します。
     * @return 親メニューＩＤ
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * 親メニューＩＤを設定します。
     * @param menuId 親メニューＩＤ
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * サブメニューＩＤを取得します。
     * @return サブメニューＩＤ
     */
    public String getSubMenuId() {
        return subMenuId;
    }
    /**
     * サブメニューＩＤを設定します。
     * @param subMenuId サブメニューＩＤ
     */
    public void setSubMenuId(String subMenuId) {
        this.subMenuId = subMenuId;
    }
    
}
