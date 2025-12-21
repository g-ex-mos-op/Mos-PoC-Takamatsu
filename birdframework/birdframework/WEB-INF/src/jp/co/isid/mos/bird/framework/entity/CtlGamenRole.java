package jp.co.isid.mos.bird.framework.entity;

public class CtlGamenRole {
    
    public static final String TABLE = "BR11GMRL";
    
    public static final String gamenId_COLUMN = "GAMEN_ID";
    
    public static final String bunruiCd_COLUMN = "BUNRUI_CD";
    
    public static final String seteiKbn_COLUMN = "SETEI_KBN";
    
    public static final String roleCd_COLUMN1 = "ROLE_CD";
    
    /**
     * 画面ID
     */
    private String gamenId;
    
    /**
     * 分類CD
     */
    private String bunruiCd;
    
    /**
     * 設定区分
     */
    private String seteiKbn;
    
    /**
     * ロールCD
     */
    private String roleCd;
    
    
    /**
     * 画面IDを取得します。
     * @return 画面ID
     */
    public String getGamenId() {
        return gamenId;
    }
    /**
     * 画面IDを設定します。
     * @param onlId 画面ID
     */
    public void setGamenId(String gamenId) {
        this.gamenId = gamenId;
    }
    
    /**
     * 分類CDを取得します。
     * @return 分類CD
     */
    public String getBunruiCd() {
        return bunruiCd;
    }
    /**
     * 分類CDを設定します。
     * @param bunruiCd 分類CD
     */
    public void setBunruiCd(String bunruiCd) {
        this.bunruiCd = bunruiCd;
    }
    
    /**
     * 設定区分を取得します。
     * @return 設定区分
     */
    public String getSeteiKbn() {
        return seteiKbn;
    }
    /**
     * 設定区分を設定します。
     * @param seteiKbn 設定区分
     */
    public void setSeteiKbn(String seteiKbn) {
        this.seteiKbn = seteiKbn;
    }
    
    /**
     * ロールCDを取得します。
     * @return ロールCD
     */
    public String getRoleCd() {
        return roleCd;
    }
    /**
     * ロールCDを設定します。
     * @param roleCd ロールCD
     */
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
}
