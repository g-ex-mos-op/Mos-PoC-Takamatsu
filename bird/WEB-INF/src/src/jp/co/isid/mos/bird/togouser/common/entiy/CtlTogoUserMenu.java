package jp.co.isid.mos.bird.togouser.common.entiy;

public class CtlTogoUserMenu {
    
    public static final String TABLE = "IR06TMNU";
    
    public static final String menuId_COLUMN = "MENU_ID";
    
    public static final String menuName_COLUMN = "MENU_NAME";
    
    public static final String subMenuId_COLUMN = "SUB_MENU_ID";
    
    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";
    
    public static final String viewId_COLUMN = "VIEW_ID";
    
    public static final String pckgName_COLUMN = "PCKG_NAME";
    
    public static final String initViewId_COLUMN = "INIT_VIEW_ID";
    
    public static final String param_COLUMN = "PARAM";
    
    public static final String sortSeq_COLUMN = "SORT_SEQ";
    
    /**
     * メニューID
     */
    private String menuId;
    
    /**
     * メニュー名
     */
    private String menuName;
    
    /**
     * サブメニューID
     */
    private String subMenuId;
    
    /**
     * サブメニュー名
     */
    private String subMenuName;
    
    /**
     * ビューID
     */
    private String viewId;
    
    /**
     * パッケージ名
     */
    private String pckgName;
    
    /**
     * 初期ビューID
     */
    private String initViewId;
    
    /**
     * パラメータ
     */
    private String param;
    
    /**
     * ソート順
     */
    private String sortSeq;
    
    /**
     * メニューIDを取得します。
     * @return メニューID
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * メニューIDを設定します。
     * @param menuId メニューID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * メニュー名を取得します。
     * @return メニュー名
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * メニュー名を設定します。
     * @param menuName メニュー名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    /**
     * サブメニューIDを取得します。
     * @return サブメニューID
     */
    public String getSubMenuId() {
        return subMenuId;
    }
    /**
     * サブメニューIDを設定します。
     * @param subMenuId サブメニューID
     */
    public void setSubMenuId(String subMenuId) {
        this.subMenuId = subMenuId;
    }
    
    /**
     * サブメニュー名を取得します。
     * @return サブメニュー名
     */
    public String getSubMenuName() {
        return subMenuName;
    }
    /**
     * サブメニュー名を設定します。
     * @param subMenuName サブメニュー名
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
    
    /**
     * ビューIDを取得します。
     * @return ビューID
     */
    public String getViewId() {
        return viewId;
    }
    /**
     * ビューIDを設定します。
     * @param viewId ビューID
     */
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }
    
    /**
     * パッケージ名を取得します。
     * @return パッケージ名
     */
    public String getPckgName() {
        return pckgName;
    }
    /**
     * パッケージ名を設定します。
     * @param pckgName パッケージ名
     */
    public void setPckgName(String pckgName) {
        this.pckgName = pckgName;
    }
    
    /**
     * 初期ビューIDを取得します。
     * @return 初期ビューID
     */
    public String getInitViewId() {
        return initViewId;
    }
    /**
     * 初期ビューIDを設定します。
     * @param initViewId 初期ビューID
     */
    public void setInitViewId(String initViewId) {
        this.initViewId = initViewId;
    }
    
    /**
     * パラメータを取得します。
     * @return パラメータ
     */
    public String getParam() {
        return param;
    }
    /**
     * パラメータを設定します。
     * @param param パラメータ
     */
    public void setParam(String param) {
        this.param = param;
    }
    
    /**
     * ソート順を取得します。
     * @return ソート順
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * ソート順を設定します。
     * @param sortSeq ソート順
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }
    
}
