package jp.co.isid.mos.bird.framework.entity;

public class CtlUserMenu {
    
    public static final String TABLE = "BR02BMNU";
    
    public static final String menuId_COLUMN    = "MENU_ID";
    
    public static final String subMenuId_COLUMN = "SUB_MENU_ID";

    public static final String param_COLUMN     = "PARAM";

    public static final String enableFlg_COLUMN     = "ENABLE_FLG";         //20061206ユーザデフォルト対応

    public static final String extraFlg_COLUMN      = "EXTRA_FLG";          //20061206ユーザデフォルト対応

    public static final String customizeFlg_COLUMN  = "CUSTOMIZE_FLG";      //20061206ユーザデフォルト対応

    /** メニュー */
    private String menuId;
    
    /** サブメニューＩＤ */
    private String subMenuId;
    
    /** パラメータ */
    private String param;

    
    //以下、ユーザロールデフォルト設定対応(2006/12/06)
    /** 使用可能フラグ(BR05ACTR) */
    private String enableFlg;
    
    /** 上限拡張フラグ(BR05ACTR) */
    private String extraFlg;

    /** 個別設定フラグ(BR54USAC) */
    private String customizeFlg;

    
    
    //////////////////////以下、セッター・ゲッター///////////////////
    
    /**
     * メニューを取得します。
     * @return メニュー
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * メニューを設定します。
     * @param menuId メニュー
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
    
    /**
     * パラメータを取得します。
     * @return パラメータ
     */
    public String getParam() {
        if(param == null){
            return "";
        }
        return param.trim();
    }
    
    /**
     * パラメータを設定します。
     * @param param パラメータ
     */
    public void setParam(String param) {
        this.param = param;
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
