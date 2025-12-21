package jp.co.isid.mos.bird.bizreport.common.camp.entity;

public class CodCampMenu {
    
    public static final String TABLE = "BM61CPMN";
    
    public static final String code_COLUMN = "MENU_CD";
    
    public static final String name_COLUMN = "MENU_NAME_KJ";
    
    /**
     * メニューコード
     */
    private String code;
    
    /**
     * メニュー名称
     */
    private String name;
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getCode() {
        return code;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setCode(String menuCd) {
        this.code = menuCd;
    }
    
    /**
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getName() {
        return name;
    }
    /**
     * メニュー名称を設定します。
     * @param menuNameKj メニュー名称
     */
    public void setName(String menuNameKj) {
        this.name = menuNameKj;
    }
    
}
