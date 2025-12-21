package jp.co.isid.mos.bird.config.campaignmasterregist.entity;

public class MstMenu {
    
    public static final String TABLE = "PC10SMNU";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    
    /**
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuNameKj() {
        return menuNameKj;
    }
    /**
     * メニュー名称を設定します。
     * @param menuNameKj メニュー名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
    }
    
}
