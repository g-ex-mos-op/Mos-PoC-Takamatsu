package jp.co.isid.mos.bird.config.campaignmasterregist.entity;


public class UICampMenu {
    
    public static final String TABLE = "BM61CPMN";
    
    public static final String campId_COLUMN = "CAMP_ID";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    public static final String convValue_COLUMN = "CONV_VALUE";
    
    public static final String sumMenuCd_COLUMN = "SUM_MENU_CD";
    
    public static final String sumMenuNameKj_COLUMN = "SUM_MENU_NAME_KJ";
    
    /**
     * キャンペーン識別番号
     */
    private String campId;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    
    /**
     * 換算値
     */
    private String convValue;
    
    /**
     * 集約メニューコード
     */
    private String sumMenuCd;
    
    /**
     * 集約メニュー名称
     */
    private String sumMenuNameKj;

    
    /**
     * キャンペーン識別番号を取得します。
     * @return キャンペーン識別番号
     */
    public String getCampId() {
        return campId;
    }
    /**
     * キャンペーン識別番号を設定します。
     * @param campId キャンペーン識別番号
     */
    public void setCampId(String campId) {
        this.campId = campId;
    }
    
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
    
    /**
     * 換算値を取得します。
     * @return 換算値
     */
    public String getConvValue() {
        return convValue;
    }
    /**
     * 換算値を設定します。
     * @param convValue 換算値
     */
    public void setConvValue(String convValue) {
        this.convValue = convValue;
    }
    
    /**
     * 集約メニューコードを取得します。
     * @return 集約メニューコード
     */
    public String getSumMenuCd() {
        return sumMenuCd;
    }
    /**
     * 集約メニューコードを設定します。
     * @param sumMenuCd 集約メニューコード
     */
    public void setSumMenuCd(String sumMenuCd) {
        this.sumMenuCd = sumMenuCd;
    }
    
    /**
     * 集約メニュー名称を取得します。
     * @return 集約メニュー名称
     */
    public String getSumMenuNameKj() {
        return sumMenuNameKj;
    }
    /**
     * 集約メニュー名称を設定します。
     * @param sumMenuNameKj 集約メニュー名称
     */
    public void setSumMenuNameKj(String sumMenuNameKj) {
        this.sumMenuNameKj = sumMenuNameKj;
    }
    
}
