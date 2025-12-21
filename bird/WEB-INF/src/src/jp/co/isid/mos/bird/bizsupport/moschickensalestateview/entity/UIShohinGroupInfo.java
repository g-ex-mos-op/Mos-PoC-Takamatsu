package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity;

public class UIShohinGroupInfo {
    
    public static final String TABLE = "BM40CMGP";
    
    public static final String menuGroup_COLUMN = "MENU_GROUP";
    
    public static final String menuGroupNm_COLUMN = "MENU_GROUP_NM";
    
    /**
     * コード
     */
    private String menuGroup;
    
    /**
     * 名称
     */
    private String menuGroupNm;
    
    /**
     * コードを取得します。
     * @return コード
     */
    public String getMenuGroup() {
        return menuGroup;
    }
    /**
     * コードを設定します。
     * @param menuGroup コード
     */
    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }
    
    /**
     * 名称を取得します。
     * @return 名称
     */
    public String getMenuGroupNm() {
        return menuGroupNm;
    }
    /**
     * 名称を設定します。
     * @param menuGroupNm 名称
     */
    public void setMenuGroupNm(String menuGroupNm) {
        this.menuGroupNm = menuGroupNm;
    }
    
}
