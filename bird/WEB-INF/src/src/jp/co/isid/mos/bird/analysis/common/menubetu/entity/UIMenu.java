package jp.co.isid.mos.bird.analysis.common.menubetu.entity;

import java.math.BigDecimal;

public abstract class UIMenu extends UIAnalysis {
    
    public static final String menuBunrui_COLUMN = "MENU_BUNRUI";
    
    public static final String mbunruiNameKj_COLUMN = "MBUNRUI_NAME_KJ";
    
    public static final String sumMenuCd_COLUMN = "SUM_MENU_CD";
    
    public static final String sumMenuNameKj_COLUMN = "SUM_MENU_NAME_KJ";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    public static final String tanka_COLUMN = "TANKA";
    
    public static final String kosu_COLUMN = "KOSU";
    
    public static final String kingaku_COLUMN = "KINGAKU";
    
    public static final String kingakuKouseiHi_COLUMN = "KINGAKU_KOUSEI_HI";

     
    /**
     * メニュー分類コード
     */
    private String menuBunrui;
    
    /**
     * メニュー分類名称（漢字）
     */
    private String mbunruiNameKj;
    
    /**
     * 集約メニューコード
     */
    private String sumMenuCd;
    
    /**
     * 集約メニュー名称
     */
    private String sumMenuNameKj;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    
    /**
     * 指定期日単価
     */
    private BigDecimal tanka = new BigDecimal("0");
    
    /**
     * 指定期日販売個数
     */
    private BigDecimal kosu = new BigDecimal("0");
    
    /**
     * 指定期日販売金額
     */
    private BigDecimal kingaku = new BigDecimal("0");
    
    /**
     * 指定期日金額構成比
     */
    private BigDecimal kingakuKouseiHi = new BigDecimal("0");
    
    /**
     * メニュー分類コードを取得します。
     * @return メニュー分類コード
     */
    public String getMenuBunrui() {
        return menuBunrui;
    }
    /**
     * メニュー分類コードを設定します。
     * @param menuBunrui メニュー分類コード
     */
    public void setMenuBunrui(String menuBunrui) {
        this.menuBunrui = menuBunrui;
    }
    
    /**
     * メニュー分類名称（漢字）を取得します。
     * @return メニュー分類名称（漢字）
     */
    public String getMbunruiNameKj() {
        return mbunruiNameKj;
    }
    /**
     * メニュー分類名称（漢字）を設定します。
     * @param mbunruiNameKj メニュー分類名称（漢字）
     */
    public void setMbunruiNameKj(String mbunruiNameKj) {
        this.mbunruiNameKj = mbunruiNameKj;
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
     * 指定期日単価を取得します。
     * @return 指定期日単価
     */
    public BigDecimal getTanka() {
        return tanka;
    }
    /**
     * 指定期日単価を設定します。
     * @param tanka 指定期日単価
     */
    public void setTanka(BigDecimal tanka) {
        this.tanka = tanka;
    }
    
    /**
     * 指定期日販売個数を取得します。
     * @return 指定期日販売個数
     */
    public BigDecimal getKosu() {
        return kosu;
    }
    /**
     * 指定期日販売個数を設定します。
     * @param kosu 指定期日販売個数
     */
    public void setKosu(BigDecimal kosu) {
        this.kosu = kosu;
    }
    
    /**
     * 指定期日販売金額を取得します。
     * @return 指定期日販売金額
     */
    public BigDecimal getKingaku() {
        return kingaku;
    }
    /**
     * 指定期日販売金額を設定します。
     * @param kingaku 指定期日販売金額
     */
    public void setKingaku(BigDecimal kingaku) {
        this.kingaku = kingaku;
    }
    
    /**
     * 指定期日金額構成比を取得します。
     * @return 指定期日金額構成比
     */
    public BigDecimal getKingakuKouseiHi() {
        return kingakuKouseiHi;
    }
    /**
     * 指定期日金額構成比を設定します。
     * @param kingakuKouseiHi 指定期日金額構成比
     */
    public void setKingakuKouseiHi(BigDecimal kingakuKouseiHi) {
        this.kingakuKouseiHi = kingakuKouseiHi;
    }
}
