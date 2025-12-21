package jp.co.isid.mos.bird.bizreport.campkako.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.camp.entity.Suii;

/**
 * キャンペーン過去売上（推移）
 * 
 * @author xnkusama
 *
 */
public class UISuii extends Suii {
    
    public static final String TABLE = "BD04CPST";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuName_COLUMN = "MENU_NAME";
    
    public static final String kazuKei_COLUMN = "KAZU_KEI";
    
    public static final String menuUriage_COLUMN = "MENU_URIAGE";
    
    public static final String kouseiHi_COLUMN = "KOUSEI_HI";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String uriageZennenhi_COLUMN = "URIAGE_ZENNENHI";
    
    public static final String eigyoDaysZen_COLUMN = "EIGYO_DAYS_ZEN";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String kyakusuZennenhi_COLUMN = "KYAKUSU_ZENNENHI";
    
    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String kyakutankaZen_COLUMN = "KYAKUTANKA_ZEN";
    
    public static final String kyakutankaZennenhi_COLUMN = "KYAKUTANKA_ZENNENHI";
    
    public static final String netUriage_COLUMN = "NET_URIAGE";
    
    public static final String netUriageZen_COLUMN = "NET_URIAGE_ZEN";
    
    public static final String netUriageZennenhi_COLUMN = "NET_URIAGE_ZENNENHI";
    
    public static final String netKyakusu_COLUMN = "NET_KYAKUSU";
    
    public static final String netKyakusuZen_COLUMN = "NET_KYAKUSU_ZEN";
    
    public static final String netKyakusuZennenhi_COLUMN = "NET_KYAKUSU_ZENNENHI";
    
    public static final String netKyakutanka_COLUMN = "NET_KYAKUTANKA";
    
    public static final String netKyakutankaZen_COLUMN = "NET_KYAKUTANKA_ZEN";
    
    public static final String netKyakutankaZennenhi_COLUMN = "NET_KYAKUTANKA_ZENNENHI";
    
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    
    public static final String tenkoKbnZen_COLUMN = "TENKO_KBN_ZEN";
    
    public static final String miseNameKj_COLUM = "MISE_NAME_KJ";
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名称
     */
    private String menuName;
    
    /**
     * 金額構成比
     */
    private BigDecimal kouseiHi;
    
    /**
     * 営業日数
     */
    private BigDecimal eigyoDays;
    
    /**
     * 前年営業日数
     */
    private BigDecimal eigyoDaysZen;
    
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
    public String getMenuName() {
        return menuName;
    }
    /**
     * メニュー名称を設定します。
     * @param menuName メニュー名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    /**
     * 金額構成比を取得します。
     * @return 金額構成比
     */
    public BigDecimal getKouseiHi() {
        return kouseiHi;
    }
    /**
     * 金額構成比を設定します。
     * @param kouseiHi 金額構成比
     */
    public void setKouseiHi(BigDecimal kouseiHi) {
        this.kouseiHi = kouseiHi;
    }
    
    /**
     * 営業日数を取得します。
     * @return 営業日数
     */
    public BigDecimal getEigyoDays() {
        return eigyoDays;
    }
    /**
     * 営業日数を設定します。
     * @param eigyoDays 営業日数
     */
    public void setEigyoDays(BigDecimal eigyoDays) {
        this.eigyoDays = eigyoDays;
    }
    
    /**
     * 前年営業日数を取得します。
     * @return 前年営業日数
     */
    public BigDecimal getEigyoDaysZen() {
        return eigyoDaysZen;
    }
    /**
     * 前年営業日数を設定します。
     * @param eigyoDaysZen 前年営業日数
     */
    public void setEigyoDaysZen(BigDecimal eigyoDaysZen) {
        this.eigyoDaysZen = eigyoDaysZen;
    }
    
    public String getKingakuKoseiHiCssClass() {
        return null;
    }
    public String getKyakusuZennenHiCssClass() {
        return null;
    }
    public String getKyakutankaZennenHiCssClass() {
        return null;
    }
    public String getNetKyakusuZennenHiCssClass() {
        return null;
    }
    public String getNetKyakutankaZennenHiCssClass() {
        return null;
    }
    public String getNetUriageZennenHiCssClass() {
        return null;
    }
    public String getUriageZennenHiCssClass() {
        return null;
    }
}
