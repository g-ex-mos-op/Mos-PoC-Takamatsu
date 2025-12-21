package jp.co.isid.mos.bird.togouser.common.dto;

import java.util.List;

public class TogoUserMenuDto {
    
    /**
     * メニューID
     */
    private String menu_id;
    
    /**
     * メニュー名称
     */
    private String menu_name;

    /**
     * サブメニューID
     */
    private String sub_menu_id;
    
    /**
     * サブメニュー名称
     */
    private String sub_menu_name;

    /**
     * 画面ID
     */
    private String view_id;

    /**
     * パッケージ名称
     */
    private String pckg_name;
    
    
    /**
     * 
     */
    private String init_view_id;
    
    /**
     * パラメータ
     */
    private String param;
    
    /**
     * ソート番号
     */
    private String sort_seq;

    private List menulist;
    
    public String getInitViewId() {
        return init_view_id;
    }

    public void setInitViewId(String initviewid) {
        this.init_view_id = initviewid;
    }

    public String getMenuId() {
        return menu_id;
    }

    public void setMenuId(String menuid) {
        this.menu_id = menuid;
    }

    public String getMenuName() {
        return menu_name;
    }

    public void setMenuName(String menuname) {
        this.menu_name = menuname;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getPckgName() {
        return pckg_name;
    }

    public void setPckgName(String pckgname) {
        this.pckg_name = pckgname;
    }

    public String getSortSeq() {
        return sort_seq;
    }

    public void setSortSeq(String sortseq) {
        this.sort_seq = sortseq;
    }

    public String getSubMenuId() {
        return sub_menu_id;
    }

    public void setSubMenuId(String submenuid) {
        this.sub_menu_id = submenuid;
    }

    public String getSubMenuName() {
        return sub_menu_name;
    }

    public void setSubMenuName(String submenuname) {
        this.sub_menu_name = submenuname;
    }

    public String getViewId() {
        return view_id;
    }

    public void setViewId(String viewid) {
        this.view_id = viewid;
    }

    public List getMenulist() {
        return menulist;
    }

    public void setMenulist(List menulist) {
        this.menulist = menulist;
    }
    
}
