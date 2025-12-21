package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity;

import java.math.BigDecimal;

public class UISuiiNipo {
    
    public static final String TABLE = "BT60ZNIP";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String yosan_COLUMN = "YOSAN";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    
    public static final String zenDoyoDt_COLUMN = "ZEN_DOYO_DT";
    
    public static final String uriageZenDoyo_COLUMN = "URIAGE_ZEN_DOYO";
    
    public static final String kyakusuZenDoyo_COLUMN = "KYAKUSU_ZEN_DOYO";
    
    public static final String tenkoKbnZenDoyo_COLUMN = "TENKO_KBN_ZEN_DOYO";
    
    public static final String uriageZenDojitu_COLUMN = "URIAGE_ZEN_DOJITU";
    
    public static final String kyakusuZenDojitu_COLUMN = "KYAKUSU_ZEN_DOJITU";
    
    public static final String uriageRui_COLUMN = "URIAGE_RUI";
    
    public static final String yosanRui_COLUMN = "YOSAN_RUI";
    
    public static final String kyakusuRui_COLUMN = "KYAKUSU_RUI";
    
    public static final String uriageZenDojituRui_COLUMN = "URIAGE_ZEN_DOJITU_RUI";
    
    public static final String kyakusuZenDojituRui_COLUMN = "KYAKUSU_ZEN_DOJITU_RUI";
    
    public static final String yosanhi_COLUMN = "YOSANHI";
    
    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String kyakutankaZenDoyo_COLUMN = "KYAKUTANKA_ZEN_DOYO";
    
    public static final String kyakutankaZenDoyonenhi_COLUMN = "KYAKUTANKA_ZEN_DOYONENHI";
    
    public static final String tenkoKbnKj_COLUMN = "TENKO_KBN_KJ";
    
    public static final String tenkoKbnZenDoyoKj_COLUMN = "TENKO_KBN_ZEN_DOYO_KJ";
    
    public static final String yosanhiRui_COLUMN = "YOSANHI_RUI";
    
    public static final String uriageZennenhiRui_COLUMN = "URIAGE_ZENNENHI_RUI";
    
    public static final String kyakusuZennenhiRui_COLUMN = "KYAKUSU_ZENNENHI_RUI";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 売上
     */
    private String uriage;
    
    /**
     * オーナー予算
     */
    private BigDecimal yosan;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 天候区分
     */
    private BigDecimal tenkoKbn;
    
    /**
     * 前年同曜日付
     */
    private String zenDoyoDt;
    
    /**
     * 前年同曜売上高
     */
    private BigDecimal uriageZenDoyo;
    
    /**
     * 前年同曜客数
     */
    private BigDecimal kyakusuZenDoyo;
    
    /**
     * 前年同曜天候
     */
    private BigDecimal tenkoKbnZenDoyo;
    
    /**
     * 前年同日売上高
     */
    private BigDecimal uriageZenDojitu;
    
    /**
     * 前年同日客数
     */
    private BigDecimal kyakusuZenDojitu;
    
    /**
     * 当年売上累計
     */
    private BigDecimal uriageRui;
    
    /**
     * オーナー予算累計
     */
    private BigDecimal yosanRui;
    
    /**
     * 当年客数累計
     */
    private BigDecimal kyakusuRui;
    
    /**
     * 前年同日売上高累計
     */
    private BigDecimal uriageZenDojituRui;
    
    /**
     * 前年同日客数累計
     */
    private BigDecimal kyakusuZenDojituRui;
    
    /**
     * 予算比（予算達成率）
     */
    private BigDecimal yosanhi;
    
    /**
     * 客単価
     */
    private BigDecimal kyakutanka;
    
    /**
     * 前年同曜客単価
     */
    private BigDecimal kyakutankaZenDoyo;
    
    /**
     * 客単価前年比
     */
    private BigDecimal kyakutankaZenDoyonenhi;
    
    /**
     * 天候区分名称
     */
    private String tenkoKbnKj;
    
    /**
     * 前年同曜天候区分名称
     */
    private String tenkoKbnZenDoyoKj;
    
    /**
     * 累計の予算比※売上高（累計）／売上高予算比（累計）
     */
    private BigDecimal yosanhiRui;
    
    /**
     * 累計の売上前年比※売上高（累計）／売上高前年値（売上高累計）
     */
    private BigDecimal uriageZennenhiRui;
    
    /**
     * 累計の客数前年比※客数（累計）／客数前年値（累計）
     */
    private BigDecimal kyakusuZennenhiRui;
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public String getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(String uriage) {
        this.uriage = uriage;
    }
    
    /**
     * オーナー予算を取得します。
     * @return オーナー予算
     */
    public BigDecimal getYosan() {
        return yosan;
    }
    /**
     * オーナー予算を設定します。
     * @param yosan オーナー予算
     */
    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu) {
        this.kyakusu = kyakusu;
    }
    
    /**
     * 天候区分を取得します。
     * @return 天候区分
     */
    public BigDecimal getTenkoKbn() {
        return tenkoKbn;
    }
    /**
     * 天候区分を設定します。
     * @param tenkoKbn 天候区分
     */
    public void setTenkoKbn(BigDecimal tenkoKbn) {
        this.tenkoKbn = tenkoKbn;
    }
    
    /**
     * 前年同曜日付を取得します。
     * @return 前年同曜日付
     */
    public String getZenDoyoDt() {
        return zenDoyoDt;
    }
    /**
     * 前年同曜日付を設定します。
     * @param zenDoyoDt 前年同曜日付
     */
    public void setZenDoyoDt(String zenDoyoDt) {
        this.zenDoyoDt = zenDoyoDt;
    }
    
    /**
     * 前年同曜売上高を取得します。
     * @return 前年同曜売上高
     */
    public BigDecimal getUriageZenDoyo() {
        return uriageZenDoyo;
    }
    /**
     * 前年同曜売上高を設定します。
     * @param uriageZenDoyo 前年同曜売上高
     */
    public void setUriageZenDoyo(BigDecimal uriageZenDoyo) {
        this.uriageZenDoyo = uriageZenDoyo;
    }
    
    /**
     * 前年同曜客数を取得します。
     * @return 前年同曜客数
     */
    public BigDecimal getKyakusuZenDoyo() {
        return kyakusuZenDoyo;
    }
    /**
     * 前年同曜客数を設定します。
     * @param kyakusuZenDoyo 前年同曜客数
     */
    public void setKyakusuZenDoyo(BigDecimal kyakusuZenDoyo) {
        this.kyakusuZenDoyo = kyakusuZenDoyo;
    }
    
    /**
     * 前年同曜天候を取得します。
     * @return 前年同曜天候
     */
    public BigDecimal getTenkoKbnZenDoyo() {
        return tenkoKbnZenDoyo;
    }
    /**
     * 前年同曜天候を設定します。
     * @param tenkoKbnZenDoyo 前年同曜天候
     */
    public void setTenkoKbnZenDoyo(BigDecimal tenkoKbnZenDoyo) {
        this.tenkoKbnZenDoyo = tenkoKbnZenDoyo;
    }
    
    /**
     * 前年同日売上高を取得します。
     * @return 前年同日売上高
     */
    public BigDecimal getUriageZenDojitu() {
        return uriageZenDojitu;
    }
    /**
     * 前年同日売上高を設定します。
     * @param uriageZenDojitu 前年同日売上高
     */
    public void setUriageZenDojitu(BigDecimal uriageZenDojitu) {
        this.uriageZenDojitu = uriageZenDojitu;
    }
    
    /**
     * 前年同日客数を取得します。
     * @return 前年同日客数
     */
    public BigDecimal getKyakusuZenDojitu() {
        return kyakusuZenDojitu;
    }
    /**
     * 前年同日客数を設定します。
     * @param kyakusuZenDojitu 前年同日客数
     */
    public void setKyakusuZenDojitu(BigDecimal kyakusuZenDojitu) {
        this.kyakusuZenDojitu = kyakusuZenDojitu;
    }
    
    /**
     * 当年売上累計を取得します。
     * @return 当年売上累計
     */
    public BigDecimal getUriageRui() {
        return uriageRui;
    }
    /**
     * 当年売上累計を設定します。
     * @param uriageRui 当年売上累計
     */
    public void setUriageRui(BigDecimal uriageRui) {
        this.uriageRui = uriageRui;
    }
    
    /**
     * オーナー予算累計を取得します。
     * @return オーナー予算累計
     */
    public BigDecimal getYosanRui() {
        return yosanRui;
    }
    /**
     * オーナー予算累計を設定します。
     * @param yosanRui オーナー予算累計
     */
    public void setYosanRui(BigDecimal yosanRui) {
        this.yosanRui = yosanRui;
    }
    
    /**
     * 当年客数累計を取得します。
     * @return 当年客数累計
     */
    public BigDecimal getKyakusuRui() {
        return kyakusuRui;
    }
    /**
     * 当年客数累計を設定します。
     * @param kyakusuRui 当年客数累計
     */
    public void setKyakusuRui(BigDecimal kyakusuRui) {
        this.kyakusuRui = kyakusuRui;
    }
    
    /**
     * 前年同日売上高累計を取得します。
     * @return 前年同日売上高累計
     */
    public BigDecimal getUriageZenDojituRui() {
        return uriageZenDojituRui;
    }
    /**
     * 前年同日売上高累計を設定します。
     * @param uriageZenDojituRui 前年同日売上高累計
     */
    public void setUriageZenDojituRui(BigDecimal uriageZenDojituRui) {
        this.uriageZenDojituRui = uriageZenDojituRui;
    }
    
    /**
     * 前年同日客数累計を取得します。
     * @return 前年同日客数累計
     */
    public BigDecimal getKyakusuZenDojituRui() {
        return kyakusuZenDojituRui;
    }
    /**
     * 前年同日客数累計を設定します。
     * @param kyakusuZenDojituRui 前年同日客数累計
     */
    public void setKyakusuZenDojituRui(BigDecimal kyakusuZenDojituRui) {
        this.kyakusuZenDojituRui = kyakusuZenDojituRui;
    }
    
    /**
     * 予算比（予算達成率）を取得します。
     * @return 予算比（予算達成率）
     */
    public BigDecimal getYosanhi() {
        return yosanhi;
    }
    /**
     * 予算比（予算達成率）を設定します。
     * @param yosanhi 予算比（予算達成率）
     */
    public void setYosanhi(BigDecimal yosanhi) {
        this.yosanhi = yosanhi;
    }
    
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakutanka() {
        return kyakutanka;
    }
    /**
     * 客単価を設定します。
     * @param kyakutanka 客単価
     */
    public void setKyakutanka(BigDecimal kyakutanka) {
        this.kyakutanka = kyakutanka;
    }
    
    /**
     * 前年同曜客単価を取得します。
     * @return 前年同曜客単価
     */
    public BigDecimal getKyakutankaZenDoyo() {
        return kyakutankaZenDoyo;
    }
    /**
     * 前年同曜客単価を設定します。
     * @param kyakutankaZenDoyo 前年同曜客単価
     */
    public void setKyakutankaZenDoyo(BigDecimal kyakutankaZenDoyo) {
        this.kyakutankaZenDoyo = kyakutankaZenDoyo;
    }
    
    /**
     * 客単価前年比を取得します。
     * @return 客単価前年比
     */
    public BigDecimal getKyakutankaZenDoyonenhi() {
        return kyakutankaZenDoyonenhi;
    }
    /**
     * 客単価前年比を設定します。
     * @param kyakutankaZenDoyonenhi 客単価前年比
     */
    public void setKyakutankaZenDoyonenhi(BigDecimal kyakutankaZenDoyonenhi) {
        this.kyakutankaZenDoyonenhi = kyakutankaZenDoyonenhi;
    }
    
    /**
     * 天候区分名称を取得します。
     * @return 天候区分名称
     */
    public String getTenkoKbnKj() {
        return tenkoKbnKj;
    }
    /**
     * 天候区分名称を設定します。
     * @param tenkoKbnKj 天候区分名称
     */
    public void setTenkoKbnKj(String tenkoKbnKj) {
        this.tenkoKbnKj = tenkoKbnKj;
    }
    
    /**
     * 前年同曜天候区分名称を取得します。
     * @return 前年同曜天候区分名称
     */
    public String getTenkoKbnZenDoyoKj() {
        return tenkoKbnZenDoyoKj;
    }
    /**
     * 前年同曜天候区分名称を設定します。
     * @param tenkoKbnZenDoyoKj 前年同曜天候区分名称
     */
    public void setTenkoKbnZenDoyoKj(String tenkoKbnZenDoyoKj) {
        this.tenkoKbnZenDoyoKj = tenkoKbnZenDoyoKj;
    }
    
    /**
     * 累計の予算比※売上高（累計）／売上高予算比（累計）を取得します。
     * @return 累計の予算比※売上高（累計）／売上高予算比（累計）
     */
    public BigDecimal getYosanhiRui() {
        return yosanhiRui;
    }
    /**
     * 累計の予算比※売上高（累計）／売上高予算比（累計）を設定します。
     * @param yosanhiRui 累計の予算比※売上高（累計）／売上高予算比（累計）
     */
    public void setYosanhiRui(BigDecimal yosanhiRui) {
        this.yosanhiRui = yosanhiRui;
    }
    
    /**
     * 累計の売上前年比※売上高（累計）／売上高前年値（売上高累計）を取得します。
     * @return 累計の売上前年比※売上高（累計）／売上高前年値（売上高累計）
     */
    public BigDecimal getUriageZennenhiRui() {
        return uriageZennenhiRui;
    }
    /**
     * 累計の売上前年比※売上高（累計）／売上高前年値（売上高累計）を設定します。
     * @param uriageZennenhiRui 累計の売上前年比※売上高（累計）／売上高前年値（売上高累計）
     */
    public void setUriageZennenhiRui(BigDecimal uriageZennenhiRui) {
        this.uriageZennenhiRui = uriageZennenhiRui;
    }
    
    /**
     * 累計の客数前年比※客数（累計）／客数前年値（累計）を取得します。
     * @return 累計の客数前年比※客数（累計）／客数前年値（累計）
     */
    public BigDecimal getKyakusuZennenhiRui() {
        return kyakusuZennenhiRui;
    }
    /**
     * 累計の客数前年比※客数（累計）／客数前年値（累計）を設定します。
     * @param kyakusuZennenhiRui 累計の客数前年比※客数（累計）／客数前年値（累計）
     */
    public void setKyakusuZennenhiRui(BigDecimal kyakusuZennenhiRui) {
        this.kyakusuZennenhiRui = kyakusuZennenhiRui;
    }
    
}
