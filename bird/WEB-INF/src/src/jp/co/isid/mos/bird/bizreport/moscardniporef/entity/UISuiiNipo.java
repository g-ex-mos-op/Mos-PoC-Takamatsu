package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

public class UISuiiNipo {
    
    public static final String TABLE = "BD35ZNMC";
    
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
    
    public static final String issueCnt_COLUMN = "ISSUE_CNT";
    
    public static final String chargeKin_COLUMN = "CHARGE_KIN";
    
    public static final String chargeKen_COLUMN = "CHARGE_KEN";
    
    public static final String kessaiKin_COLUMN = "KESSAI_KIN";
    
    public static final String kessaiKen_COLUMN = "KESSAI_KEN";
    
    public static final String chargeKinCancel_COLUMN = "CHARGE_KIN_CANCEL";
    
    public static final String chargeKenCancel_COLUMN = "CHARGE_KEN_CANCEL";
    
    public static final String useKinCancel_COLUMN = "USE_KIN_CANCEL";
    
    public static final String useKenCancel_COLUMN = "USE_KEN_CANCEL";
    
    public static final String bonusVIssue_COLUMN = "BONUS_V_ISSUE";
    
    public static final String bonusVUse_COLUMN = "BONUS_V_USE";
    
    public static final String couponVIssue_COLUMN = "COUPON_V_ISSUE";
    
    public static final String couponVUse_COLUMN = "COUPON_V_USE";
    
    public static final String zandaka_COLUMN = "ZANDAKA";
    
    public static final String issueCntZenDoyo_COLUMN = "ISSUE_CNT_ZEN_DOYO";
    
    public static final String chargeKinZenDoyo_COLUMN = "CHARGE_KIN_ZEN_DOYO";
    
    public static final String chargeKenZenDoyo_COLUMN = "CHARGE_KEN_ZEN_DOYO";
    
    public static final String kessaiKinZenDoyo_COLUMN = "KESSAI_KIN_ZEN_DOYO";
    
    public static final String kessaiKenZenDoyo_COLUMN = "KESSAI_KEN_ZEN_DOYO";
    
    public static final String issueCntZenDojitu_COLUMN = "ISSUE_CNT_ZEN_DOJITU";
    
    public static final String chargeKinZenDojitu_COLUMN = "CHARGE_KIN_ZEN_DOJITU";
    
    public static final String chargeKenZenDojitu_COLUMN = "CHARGE_KEN_ZEN_DOJITU";
    
    public static final String kessaiKinZenDojitu_COLUMN = "KESSAI_KIN_ZEN_DOJITU";
    
    public static final String kessaiKenZenDojitu_COLUMN = "KESSAI_KEN_ZEN_DOJITU";
    
    public static final String issueCntRui_COLUMN = "ISSUE_CNT_RUI";
    
    public static final String chargeKinRui_COLUMN = "CHARGE_KIN_RUI";
    
    public static final String chargeKenRui_COLUMN = "CHARGE_KEN_RUI";
    
    public static final String kessaiKinRui_COLUMN = "KESSAI_KIN_RUI";
    
    public static final String kessaiKenRui_COLUMN = "KESSAI_KEN_RUI";
    
    public static final String chargeKinCancelRui_COLUMN = "CHARGE_KIN_CANCEL_RUI";
    
    public static final String chargeKenCancelRui_COLUMN = "CHARGE_KEN_CANCEL_RUI";
    
    public static final String useKinCancelRui_COLUMN = "USE_KIN_CANCEL_RUI";
    
    public static final String useKenCancelRui_COLUMN = "USE_KEN_CANCEL_RUI";
    
    public static final String bonusVIssueRui_COLUMN = "BONUS_V_ISSUE_RUI";
    
    public static final String bonusVUseRui_COLUMN = "BONUS_V_USE_RUI";
    
    public static final String couponVIssueRui_COLUMN = "COUPON_V_ISSUE_RUI";
    
    public static final String couponVUseRui_COLUMN = "COUPON_V_USE_RUI";
    
    public static final String issueCntZenDojituRui_COLUMN = "ISSUE_CNT_ZEN_DOJITU_RUI";
    
    public static final String chargeKinZenDojituRui_COLUMN = "CHARGE_KIN_ZEN_DOJITU_RUI";
    
    public static final String chargeKenZenDojituRui_COLUMN = "CHARGE_KEN_ZEN_DOJITU_RUI";
    
    public static final String kessaiKinZenDojituRui_COLUMN = "KESSAI_KIN_ZEN_DOJITU_RUI";
    
    public static final String kessaiKenZenDojituRui_COLUMN = "KESSAI_KEN_ZEN_DOJITU_RUI";
    
    public static final String issueCntZennenhiRui_COLUMN = "ISSUE_CNT_ZENNENHI_RUI";
    
    public static final String chargeKinUriagehi_COLUMN = "CHARGE_KIN_URIAGEHI";
    
    public static final String chargeKinUriagehiRui_COLUMN = "CHARGE_KIN_URIAGEHI_RUI";
    
    public static final String chargeKinZenuriagehi_COLUMN = "CHARGE_KIN_ZENURIAGEHI";
    
    public static final String chargeKenKyakusuhi_COLUMN = "CHARGE_KEN_KYAKUSUHI";
    
    public static final String chargeKenZenKyakusuhi_COLUMN = "CHARGE_KEN_ZENKYAKUSUHI";
    
    public static final String chargeKenKyakusuhiRui_COLUMN = "CHARGE_KEN_KYAKUSUHI_RUI";
    
    public static final String chargeTanka_COLUMN = "CHARGETANKA";
    
    public static final String chargeTankaZenDoyo_COLUMN = "CHARGETANKA_ZEN_DOYO";
    
    public static final String chargeTankaTankahi_COLUMN = "CHARGETANKA_TANKAHI";
    
    public static final String chargeTankaZenhi_COLUMN = "CHARGETANKA_ZENHI";
    
    public static final String kessaiKinUriagehi_COLUMN = "KESSAI_KIN_URIAGEHI";
    
    public static final String kessaiKinUriagehiRui_COLUMN = "KESSAI_KIN_URIAGEHI_RUI";
    
    public static final String kessaiKinZenUriagehi_COLUMN = "KESSAI_KIN_ZENURIAGEHI";
    
    public static final String kessaiKenKyakusuhi_COLUMN = "KESSAI_KEN_KYAKUSUHI";
    
    public static final String kessaiKenZenKyakusuhi_COLUMN = "KESSAI_KEN_ZENKYAKUSUHI";
    
    public static final String kessaiKenKyakusuhiRui_COLUMN = "KESSAI_KEN_KYAKUSUHI_RUI";
    
    public static final String kessaiTanka_COLUMN = "KESSAITANKA";
    
    public static final String kessaiTankaZenDoyo_COLUMN = "KESSAITANKA_ZEN_DOYO";
    
    public static final String kessaiTankaTankahi_COLUMN = "KESSAITANKA_TANKAHI";
    
    public static final String kessaiTankaZenhi_COLUMN = "KESSAITANKA_ZENHI";
    
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
     * 発行枚数
     */
    private BigDecimal issueCnt;
    
    /**
     * チャージ金額
     */
    private BigDecimal chargeKin;
    
    /**
     * チャージ件数
     */
    private BigDecimal chargeKen;
    
    /**
     * 決済金額
     */
    private BigDecimal kessaiKin;
    
    /**
     * 決済件数
     */
    private BigDecimal kessaiKen;
    
    /**
     * 前年同曜発行枚数
     */
    private BigDecimal issueCntZenDoyo;
    
    /**
     * 前年同曜チャージ金額
     */
    private BigDecimal chargeKinZenDoyo;
    
    /**
     * 前年同曜チャージ件数
     */
    private BigDecimal chargeKenZenDoyo;
    
    /**
     * 前年同曜決済金額
     */
    private BigDecimal kessaiKinZenDoyo;
    
    /**
     * 前年同曜決済件数
     */
    private BigDecimal kessaiKenZenDoyo;
    
    /**
     * 前年同日発行枚数
     */
    private BigDecimal issueCntZenDojitu;
    
    /**
     * 前年同日チャージ金額
     */
    private BigDecimal chargeKinZenDojitu;
    
    /**
     * 前年同日チャージ件数
     */
    private BigDecimal chargeKenZenDojitu;
    
    /**
     * 前年同日決済金額
     */
    private BigDecimal kessaiKinZenDojitu;
    
    /**
     * 前年同日決済件数
     */
    private BigDecimal kessaiKenZenDojitu;
    
    /**
     * 入金取消金額
     */
    private BigDecimal chargeKinCancel;
    
    /**
     * 入金取消件数
     */
    private BigDecimal chargeKenCancel;
    
    /**
     * 利用取消金額
     */
    private BigDecimal useKinCancel;
    
    /**
     * 利用取消件数
     */
    private BigDecimal useKenCancel;
    
    /**
     * 発行ボーナスバリュー
     */
    private BigDecimal bonusVIssue;
    
    /**
     * 利用ボーナスバリュー
     */
    private BigDecimal bonusVUse;
    
    /**
     * 発行クーポンバリュー
     */
    private BigDecimal couponVIssue;
    
    /**
     * 利用クーポンバリュー
     */
    private BigDecimal couponVUse;
    
    /**
     * 前受残高
     */
    private BigDecimal zandaka;
    
    /**
     * 発行枚数累計
     */
    private BigDecimal issueCntRui;
    
    /**
     * チャージ金額累計
     */
    private BigDecimal chargeKinRui;
    
    /**
     * チャージ件数累計
     */
    private BigDecimal chargeKenRui;
    
    /**
     * 決済金額累計
     */
    private BigDecimal kessaiKinRui;
    
    /**
     * 決済件数累計
     */
    private BigDecimal kessaiKenRui;
    
    /**
     * 入金取消金額累計
     */
    private BigDecimal chargeKinCancelRui;
    
    /**
     * 入金取消件数累計
     */
    private BigDecimal chargeKenCancelRui;
    
    /**
     * 利用取消金額累計
     */
    private BigDecimal useKinCancelRui;
    
    /**
     * 利用取消件数累計
     */
    private BigDecimal useKenCancelRui;
    
    /**
     * 発行ボーナスバリュー累計
     */
    private BigDecimal bonusVIssueRui;
    
    /**
     * 利用ボーナスバリュー累計
     */
    private BigDecimal bonusVUseRui;
    
    /**
     * 発行クーポンバリュー累計
     */
    private BigDecimal couponVIssueRui;
    
    /**
     * 利用クーポンバリュー累計
     */
    private BigDecimal couponVUseRui;
    
    /**
     * 前年同日発行枚数累計
     */
    private BigDecimal issueCntZenDojituRui;
    
    /**
     * 前年同日チャージ金額累計
     */
    private BigDecimal chargeKinZenDojituRui;
    
    /**
     * 前年同日チャージ件数累計
     */
    private BigDecimal chargeKenZenDojituRui;
    
    /**
     * 前年同日決済金額累計
     */
    private BigDecimal kessaiKinZenDojituRui;
    
    /**
     * 前年同日決済件数累計
     */
    private BigDecimal kessaiKenZenDojituRui;
    
    /**
     * 発行枚数（前年対比）
     */
    private BigDecimal issueCntZennenhiRui;
    
    /**
     * チャージ金額（売上比）（当日）
     */
    private BigDecimal chargeKinUriagehi;
    
    /**
     * チャージ金額（売上比）（累計）
     */
    private BigDecimal chargeKinUriagehiRui;
    
    /**
     * チャージ金額（前年対比）
     */
    private BigDecimal chargeKinZenuriagehi;
    
    /**
     * チャージ件数（客数比当日）
     */
    private BigDecimal chargeKenKyakusuhi;
    
    /**
     * チャージ件数前年値（前年対比）
     */
    private BigDecimal chargeKenZenKyakusuhi;
    
    /**
     * チャージ件数（客数比累計）
     */
    private BigDecimal chargeKenKyakusuhiRui;
    
    /**
     * チャージ単価（当日）
     */
    private BigDecimal chargeTanka;
    
    /**
     * チャージ単価（前年同曜日）
     */
    private BigDecimal chargeTankaZenDoyo;
    
    /**
     * チャージ単価（単価比）
     */
    private BigDecimal chargeTankaTankahi;
    
    /**
     * チャージ単価（前年対比）
     */
    private BigDecimal chargeTankaZenhi;
    
    /**
     * 決済金額（売上比）（当日）
     */
    private BigDecimal kessaiKinUriagehi;
    
    /**
     * 決済金額（売上比）（累計）
     */
    private BigDecimal kessaiKinUriagehiRui;
    
    /**
     * 決済金額（前年対比）
     */
    private BigDecimal kessaiKinZenUriagehi;
    
    /**
     * 決済件数（客数比当日）
     */
    private BigDecimal kessaiKenKyakusuhi;
    
    /**
     * 決済件数前年値（前年対比）
     */
    private BigDecimal kessaiKenZenKyakusuhi;
    
    /**
     * 決済件数（客数比累計）
     */
    private BigDecimal kessaiKenKyakusuhiRui;
    
    /**
     * 決済単価（当日）
     */
    private BigDecimal kessaiTanka;
    
    /**
     * 決済単価（前年同曜日）
     */
    private BigDecimal kessaiTankaZenDoyo;
    
    /**
     * 決済単価（単価比）
     */
    private BigDecimal kessaiTankaTankahi;
    
    /**
     * 決済単価（前年対比）
     */
    private BigDecimal kessaiTankaZenhi;
    
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
    
    /**
     * 発行枚数を取得します。
     * @return 発行枚数
     */
    public BigDecimal getIssueCnt() {
        return issueCnt;
    }
    /**
     * 発行枚数を設定します。
     * @param issueCnt 発行枚数
     */
    public void setIssueCnt(BigDecimal issueCnt) {
        this.issueCnt = issueCnt;
    }
    
    /**
     * チャージ金額を取得します。
     * @return チャージ金額
     */
    public BigDecimal getChargeKin() {
        return chargeKin;
    }
    /**
     * チャージ金額を設定します。
     * @param chargeKinAvg チャージ金額
     */
    public void setChargeKin(BigDecimal chargeKin) {
        this.chargeKin = chargeKin;
    }
    
    /**
     * チャージ件数を取得します。
     * @return チャージ件数
     */
    public BigDecimal getChargeKen() {
        return chargeKen;
    }
    /**
     * チャージ件数を設定します。
     * @param chargeKenAvg チャージ件数
     */
    public void setChargeKen(BigDecimal chargeKen) {
        this.chargeKen = chargeKen;
    }
    
    /**
     * 決済金額を取得します。
     * @return 決済金額
     */
    public BigDecimal getKessaiKin() {
        return kessaiKin;
    }
    /**
     * 決済金額を設定します。
     * @param kessaiKinAvg 決済金額
     */
    public void setKessaiKin(BigDecimal kessaiKin) {
        this.kessaiKin = kessaiKin;
    }
    
    /**
     * 決済件数を取得します。
     * @return 決済件数
     */
    public BigDecimal getKessaiKen() {
        return kessaiKen;
    }
    /**
     * 決済件数平均を設定します。
     * @param kessaiKenAvg 決済件数平均
     */
    public void setKessaiKen(BigDecimal kessaiKen) {
        this.kessaiKen = kessaiKen;
    }
    
    /**
     * 前年同曜発行枚数を取得します。
     * @return 前年同曜発行枚数
     */
    public BigDecimal getIssueCntZenDoyo() {
        return issueCntZenDoyo;
    }
    /**
     * 前年同曜発行枚数を設定します。
     * @param issueCntZenDoyo 前年同曜発行枚数
     */
    public void setIssueCntZenDoyo(BigDecimal issueCntZenDoyo) {
        this.issueCntZenDoyo = issueCntZenDoyo;
    }    
    
    /**
     * 前年同曜チャージ金額を取得します。
     * @return 前年同曜発行枚数
     */
    public BigDecimal getChargeKinZenDoyo() {
        return chargeKinZenDoyo;
    }
    /**
     * 前年同曜チャージ金額を設定します。
     * @param chargeKinZenDoyo 前年同曜チャージ金額
     */
    public void setChargeKinZenDoyo(BigDecimal chargeKinZenDoyo) {
        this.chargeKinZenDoyo = chargeKinZenDoyo;
    }
    /**
     * 前年同曜チャージ件数を取得します。
     * @return 前年同曜チャージ件数
     */
    public BigDecimal getChargeKenZenDoyo() {
        return chargeKenZenDoyo;
    }
    /**
     * 前年同曜チャージ件数を設定します。
     * @param chargeKenZenDoyo 前年同曜チャージ件数
     */
    public void setChargeKenZenDoyo(BigDecimal chargeKenZenDoyo) {
        this.chargeKenZenDoyo = chargeKenZenDoyo;
    }
    
    /**
     * 前年同曜決済金額を取得します。
     * @return 前年同曜決済金額
     */
    public BigDecimal getKessaiKinZenDoyo() {
        return kessaiKinZenDoyo;
    }
    /**
     * 前年同曜決済金額を設定します。
     * @param kessaiKinZenDoyo 前年同曜決済金額
     */
    public void setKessaiKinZenDoyo(BigDecimal kessaiKinZenDoyo) {
        this.kessaiKinZenDoyo = kessaiKinZenDoyo;
    }
    
    /**
     * 前年同曜決済件数額を取得します。
     * @return 前年同曜決済件数
     */
    public BigDecimal getKessaiKenZenDoyo() {
        return kessaiKenZenDoyo;
    }
    /**
     * 前年同曜決済件数を設定します。
     * @param kessaiKenZenDoyo 前年同曜決済件数
     */
    public void setKessaiKenZenDoyo(BigDecimal kessaiKenZenDoyo) {
        this.kessaiKenZenDoyo = kessaiKenZenDoyo;
    }
    
    /**
     * 前年同日発行枚数を取得します。
     * @return 前年同日発行枚数
     */
    public BigDecimal getIssueCntZenDojitu() {
        return issueCntZenDojitu;
    }
    /**
     * 前年同日発行枚数を設定します。
     * @param issueCntZenDojitu 前年同日発行枚数
     */
    public void setIssueCntZenDojitu(BigDecimal issueCntZenDojitu) {
        this.issueCntZenDojitu = issueCntZenDojitu;
    }
    
    /**
     *  前年同日チャージ金額を取得します。
     * @return  前年同日チャージ金額
     */
    public BigDecimal getChargeKinZenDojituu() {
        return chargeKinZenDojitu;
    }
    /**
     *  前年同日チャージ金額を設定します。
     * @param chargeKinZenDojitu  前年同日チャージ金額
     */
    public void setChargeKinZenDojitu(BigDecimal chargeKinZenDojitu) {
        this.chargeKinZenDojitu = chargeKinZenDojitu;
    }
    /**
     *  前年同日チャージ件数を取得します。
     * @return  前年同日チャージ件数
     */
    public BigDecimal getChargeKenZenDojitu() {
        return chargeKenZenDojitu;
    }
    /**
     *  前年同日チャージ件数を設定します。
     * @param chargeKenZenDojitu  前年同日チャージ件数
     */
    public void setChargeKenZenDojitu(BigDecimal chargeKenZenDojitu) {
        this.chargeKenZenDojitu = chargeKenZenDojitu;
    }
    
    /**
     *  前年同日決済金額を取得します。
     * @return 前年同日決済金額
     */
    public BigDecimal getKessaiKinZenDojitu() {
        return kessaiKinZenDojitu;
    }
    /**
     *  前年同日決済金額を設定します。
     * @param kessaiKinZenDojitu  前年同日決済金額
     */
    public void setKessaiKinZenDojitu(BigDecimal kessaiKinZenDojitu) {
        this.kessaiKinZenDojitu = kessaiKinZenDojitu;
    }
    
    /**
     *  前年同日決済件数を取得します。
     * @return 前年同日決済件数
     */
    public BigDecimal getKessaiKenZenDojitu() {
        return kessaiKenZenDojitu;
    }
    /**
     *  前年同日決済件数を設定します。
     * @param kessaiKenZenDojitu  前年同日決済件数
     */
    public void setKessaiKenZenDojitu(BigDecimal kessaiKenZenDojitu) {
        this.kessaiKenZenDojitu = kessaiKenZenDojitu;
    }
    
    /**
     *  入金取消金額を取得します。
     * @return 入金取消金額
     */
    public BigDecimal getChargeKinCancel() {
        return chargeKinCancel;
    }
    /**
     *  入金取消金額を設定します。
     * @param chargeKinCancel  入金取消金額
     */
    public void setChargeKinCancel(BigDecimal chargeKinCancel) {
        this.chargeKinCancel = chargeKinCancel;
    }
    
    /**
     *  入金取消金額を取得します。
     * @return 入金取消金額
     */
    public BigDecimal getChargeKenCancel() {
        return chargeKenCancel;
    }
    /**
     *  入金取消金額を設定します。
     * @param chargeKenCancel  入金取消金額
     */
    public void setChargeKenCancel(BigDecimal chargeKenCancel) {
        this.chargeKenCancel = chargeKenCancel;
    }
    
    /**
     *  利用取消金額を取得します。
     * @return 利用取消金額
     */
    public BigDecimal getUseKinCancel() {
        return useKinCancel;
    }
    /**
     *  利用取消金額を設定します。
     * @param useKinCancel  利用取消金額
     */
    public void setUseKinCancel(BigDecimal useKinCancel) {
        this.useKinCancel = useKinCancel;
    }
    
    /**
     *  利用取消件数を取得します。
     * @return 利用取消件数
     */
    public BigDecimal getUseKenCancel() {
        return useKenCancel;
    }
    /**
     *  利用取消件数を設定します。
     * @param useKenCancel  利用取消件数
     */
    public void setUseKenCancel(BigDecimal useKenCancel) {
        this.useKenCancel = useKenCancel;
    }
    
    /**
     *  発行ボーナスバリューを取得します。
     * @return 発行ボーナスバリュー
     */
    public BigDecimal getBonusVIssue() {
        return bonusVIssue;
    }
    /**
     *  発行ボーナスバリューを設定します。
     * @param bonusVIssue  発行ボーナスバリュー
     */
    public void setBonusVIssue(BigDecimal bonusVIssue) {
        this.bonusVIssue = bonusVIssue;
    }
    
    /**
     *  利用ボーナスバリューを取得します。
     * @return 利用ボーナスバリュー
     */
    public BigDecimal getBonusVUse() {
        return bonusVUse;
    }
    /**
     *  利用ボーナスバリューを設定します。
     * @param bonusVUse  利用ボーナスバリュー
     */
    public void setBonusVUse(BigDecimal bonusVUse) {
        this.bonusVUse = bonusVUse;
    }
    
    /**
     *  発行クーポンバリューを取得します。
     * @return 発行クーポンバリュー
     */
    public BigDecimal getCouponVIssue() {
        return couponVIssue;
    }
    /**
     *  発行クーポンバリューを設定します。
     * @param couponVIssue  発行クーポンバリュー
     */
    public void setCouponVIssue(BigDecimal couponVIssue) {
        this.couponVIssue = couponVIssue;
    }
    
    /**
     *  利用クーポンバリューを取得します。
     * @return 利用クーポンバリュー
     */
    public BigDecimal getCouponVUse() {
        return couponVUse;
    }
    /**
     *  利用クーポンバリューを設定します。
     * @param couponVUse  利用クーポンバリュー
     */
    public void setCouponVUse(BigDecimal couponVUse) {
        this.couponVUse = couponVUse;
    }
    
    /**
     *  前受残高を取得します。
     * @return 前受残高
     */
    public BigDecimal getZandaka() {
        return zandaka;
    }
    /**
     *  前受残高を設定します。
     * @param zandaka  前受残高
     */
    public void setZandaka(BigDecimal zandaka) {
        this.zandaka = zandaka;
    }
    /**
     *  発行枚数累計を取得します。
     * @return 発行枚数累計
     */
    public BigDecimal getIssueCntRui() {
        return issueCntRui;
    }
    /**
     *  発行枚数累計を設定します。
     * @param issueCntRui  発行枚数累計
     */
    public void setIssueCntRui(BigDecimal issueCntRui) {
        this.issueCntRui = issueCntRui;
    }
    
    /**
     *  チャージ金額累計を取得します。
     * @return チャージ金額累計
     */
    public BigDecimal getChargeKinRui() {
        return chargeKinRui;
    }
    /**
     *  チャージ金額累計を設定します。
     * @param chargeKinRui  チャージ金額累計
     */
    public void setChargeKinRui(BigDecimal chargeKinRui) {
        this.chargeKinRui = chargeKinRui;
    }
    
    /**
     *  チャージ金額累計を取得します。
     * @return チャージ金額累計
     */
    public BigDecimal getChargeKenRui() {
        return chargeKenRui;
    }
    /**
     *  チャージ金額累計を設定します。
     * @param chargeKenRui  チャージ金額累計
     */
    public void setChargeKenRui(BigDecimal chargeKenRui) {
        this.chargeKenRui = chargeKenRui;
    }
    
    /**
     *  決済金額累計を取得します。
     * @return 決済金額累計
     */
    public BigDecimal getKessaiKinRui() {
        return kessaiKinRui;
    }
    /**
     *  決済金額累計を設定します。
     * @param kessaiKinRui  決済金額累計
     */
    public void setKessaiKinRui(BigDecimal kessaiKinRui) {
        this.kessaiKinRui = kessaiKinRui;
    }
    /**
     *  決済件数累計を取得します。
     * @return 決済件数累計
     */
    public BigDecimal getKessaiKenRui() {
        return kessaiKenRui;
    }
    /**
     *  決済件数累計を設定します。
     * @param kessaiKenRui  決済件数累計
     */
    public void setKessaiKenRui(BigDecimal kessaiKenRui) {
        this.kessaiKenRui = kessaiKenRui;
    }
    
    /**
     *  入金取消金額累計を取得します。
     * @return 入金取消金額累計
     */
    public BigDecimal getChargeKinCancelRui() {
        return chargeKinCancelRui;
    }
    /**
     *  入金取消金額累計を設定します。
     * @param chargeKinCancelRui  入金取消金額累計
     */
    public void setChargeKinCancelRui(BigDecimal chargeKinCancelRui) {
        this.chargeKinCancelRui = chargeKinCancelRui;
    }
    
    /**
     *  入金取消件数累計を取得します。
     * @return 入金取消件数累計
     */
    public BigDecimal getChargeKenCancelRui() {
        return chargeKenCancelRui;
    }
    /**
     *  入金取消件数累計を設定します。
     * @param chargeKenCancelRui  入金取消件数累計
     */
    public void setChargeKenCancelRui(BigDecimal chargeKenCancelRui) {
        this.chargeKenCancelRui = chargeKenCancelRui;
    }
    
    /**
     *  利用取消金額累計を取得します。
     * @return 利用取消金額累計
     */
    public BigDecimal getUseKinCancelRui() {
        return useKinCancelRui;
    }
    /**
     *  利用取消金額累計を設定します。
     * @param useKinCancelRui  利用取消金額累計
     */
    public void setUseKinCancelRui(BigDecimal useKinCancelRui) {
        this.useKinCancelRui = useKinCancelRui;
    }
    
    /**
     *  利用取消件数累計を取得します。
     * @return 利用取消件数累計
     */
    public BigDecimal getUseKenCancelRui() {
        return useKenCancelRui;
    }
    /**
     *  利用取消件数累計を設定します。
     * @param useKenCancelRui  利用取消件数累計
     */
    public void setUseKenCancelRui(BigDecimal useKenCancelRui) {
        this.useKenCancelRui = useKenCancelRui;
    }
    
    /**
     *  発行ボーナスバリュー累計を取得します。
     * @return 発行ボーナスバリュー累計
     */
    public BigDecimal getBonusVIssueRui() {
        return bonusVIssueRui;
    }
    /**
     *  発行ボーナスバリュー累計を設定します。
     * @param bonusVIssueRui  発行ボーナスバリュー累計
     */
    public void setBonusVIssueRui(BigDecimal bonusVIssueRui) {
        this.bonusVIssueRui = bonusVIssueRui;
    }
    
    /**
     *  利用ボーナスバリュー累計を取得します。
     * @return 利用ボーナスバリュー累計
     */
    public BigDecimal getBonusVUseRui() {
        return bonusVUseRui;
    }
    /**
     *  利用ボーナスバリュー累計を設定します。
     * @param bonusVUseRui  利用ボーナスバリュー累計
     */
    public void setBonusVUseRui(BigDecimal bonusVUseRui) {
        this.bonusVUseRui = bonusVUseRui;
    }
    
    /**
     *  発行クーポンバリュー累計を取得します。
     * @return 発行クーポンバリュー累計
     */
    public BigDecimal getCouponVIssueRui() {
        return couponVIssueRui;
    }
    /**
     *  発行クーポンバリュー累計を設定します。
     * @param couponVIssueRui  発行クーポンバリュー累計
     */
    public void setCouponVIssueRui(BigDecimal couponVIssueRui) {
        this.couponVIssueRui = couponVIssueRui;
    }
    
    /**
     *  利用クーポンバリュー累計を取得します。
     * @return 利用クーポンバリュー累計
     */
    public BigDecimal getCouponVUseRui() {
        return couponVUseRui;
    }
    /**
     *  利用クーポンバリュー累計を設定します。
     * @param couponVUseRui  利用クーポンバリュー累計
     */
    public void setCouponVUseRui(BigDecimal couponVUseRui) {
        this.couponVUseRui = couponVUseRui;
    }
    
    /**
     *  前年同日発行枚数累計を取得します。
     * @return 前年同日発行枚数累計
     */
    public BigDecimal getIssueCntZenDojituRui() {
        return issueCntZenDojituRui;
    }
    /**
     *  前年同日発行枚数累計を設定します。
     * @param issueCntZenDojituRui  前年同日発行枚数累計
     */
    public void setIssueCntZenDojituRui(BigDecimal issueCntZenDojituRui) {
        this.issueCntZenDojituRui = issueCntZenDojituRui;
    }
    
    /**
     *  前年同日チャージ金額累計を取得します。
     * @return 前年同日チャージ金額累計
     */
    public BigDecimal getChargeKinZenDojituRui() {
        return chargeKinZenDojituRui;
    }
    /**
     *  前年同日チャージ金額累計を設定します。
     * @param chargeKinZenDojituRui  前年同日チャージ金額累計
     */
    public void setChargeKinZenDojituRui(BigDecimal chargeKinZenDojituRui) {
        this.chargeKinZenDojituRui = chargeKinZenDojituRui;
    }
    
    /**
     *  前年同日チャージ件数累計を取得します。
     * @return  前年同日チャージ件数累計
     */
    public BigDecimal getChargeKenZenDojituRui() {
        return chargeKenZenDojituRui;
    }
    /**
     *  前年同日チャージ件数累計を設定します。
     * @param chargeKenZenDojituRui  前年同日チャージ件数累計
     */
    public void setChargeKenZenDojituRui(BigDecimal chargeKenZenDojituRui) {
        this.chargeKenZenDojituRui = chargeKenZenDojituRui;
    }
    
    /**
     *  前年同日決済金額累計を取得します。
     * @return 前年同日決済金額累計
     */
    public BigDecimal getKessaiKinZenDojituRui() {
        return kessaiKinZenDojituRui;
    }
    /**
     *  前年同日決済金額累計を設定します。
     * @param kessaiKinZenDojituRui  前年同日決済金額累計
     */
    public void setKessaiKinZenDojituRui(BigDecimal kessaiKinZenDojituRui) {
        this.kessaiKinZenDojituRui = kessaiKinZenDojituRui;
    }
    /**
     *  前年同日決済件数累計を取得します。
     * @return 前年同日決済件数累計
     */
    public BigDecimal getKessaiKenZenDojituRui() {
        return kessaiKenZenDojituRui;
    }
    /**
     *  前年同日決済件数累計を設定します。
     * @param kessaiKenZenDojituRui  前年同日決済件数累計
     */
    public void setKessaiKenZenDojituRui(BigDecimal kessaiKenZenDojituRui) {
        this.kessaiKenZenDojituRui = kessaiKenZenDojituRui;
    }
    /**
     *  発行枚数（前年対比）を取得します。
     * @return 発行枚数（前年対比）
     */
    public BigDecimal getIssueCntZennenhiRui() {
        return issueCntZennenhiRui;
    }
    /**
     *  発行枚数（前年対比）を設定します。
     * @param issueCntZennenhiRui  発行枚数（前年対比）
     */
    public void setIssueCntZennenhiRui(BigDecimal issueCntZennenhiRui) {
        this.issueCntZennenhiRui = issueCntZennenhiRui;
    }
    
    /**
     *  チャージ金額（売上比）（当日）を取得します。
     * @return チャージ金額（売上比）（当日）
     */
    public BigDecimal getChargeKinUriagehi() {
        return chargeKinUriagehi;
    }
    /**
     *  チャージ金額（売上比）（当日）を設定します。
     * @param chargeKinUriagehi  チャージ金額（売上比）（当日）
     */
    public void setChargeKinUriagehi(BigDecimal chargeKinUriagehi) {
        this.chargeKinUriagehi = chargeKinUriagehi;
    }
    
    /**
     *  チャージ金額（売上比）（累計）を取得します。
     * @return チャージ金額（売上比）（累計）
     */
    public BigDecimal getChargeKinUriagehiRui() {
        return chargeKinUriagehiRui;
    }
    /**
     *  チャージ金額（売上比）（累計）を設定します。
     * @param chargeKinUriagehiRui  チャージ金額（売上比）（累計）
     */
    public void setChargeKinUriagehiRui(BigDecimal chargeKinUriagehiRui) {
        this.chargeKinUriagehiRui = chargeKinUriagehiRui;
    }
    
    /**
     *  チャージ金額（前年対比）を取得します。
     * @return チャージ金額（前年対比）
     */
    public BigDecimal getChargeKinZenuriagehi() {
        return chargeKinZenuriagehi;
    }
    /**
     *  チャージ金額（前年対比））を設定します。
     * @param chargeKinZenuriagehi  チャージ金額（前年対比）
     */
    public void setChargeKinZenuriagehi(BigDecimal chargeKinZenuriagehi) {
        this.chargeKinZenuriagehi = chargeKinZenuriagehi;
    }
    
    /**
     *  チャージ件数（客数比当日）を取得します。
     * @return チャージ件数（客数比当日）
     */
    public BigDecimal getChargeKenKyakusuhi() {
        return chargeKenKyakusuhi;
    }
    /**
     *  チャージ件数（客数比当日）を設定します。
     * @param chargeKenKyakusuhi  チャージ件数（客数比当日）
     */
    public void setChargeKenKyakusuhi(BigDecimal chargeKenKyakusuhi) {
        this.chargeKenKyakusuhi = chargeKenKyakusuhi;
    }
    
    /**
     *  チャージ件数前年値（前年対比）を取得します。
     * @return チャージ件数前年値（前年対比）
     */
    public BigDecimal getChargeKenZenKyakusuhi() {
        return chargeKenZenKyakusuhi;
    }
    /**
     *  チャージ件数前年値（前年対比）を設定します。
     * @param chargeKenZenKyakusuhi  チャージ件数前年値（前年対比）
     */
    public void setChargeKenZenKyakusuhi(BigDecimal chargeKenZenKyakusuhi) {
        this.chargeKenZenKyakusuhi = chargeKenZenKyakusuhi;
    }
    
    /**
     *  チャージ件数（客数比累計）を取得します。
     * @return チャージ件数（客数比累計）
     */
    public BigDecimal getChargeKenKyakusuhiRui() {
        return chargeKenKyakusuhiRui;
    }
    /**
     *  チャージ件数（客数比累計）を設定します。
     * @param chargeKenKyakusuhiRui  チャージ件数（客数比累計）
     */
    public void setChargeKenKyakusuhiRui(BigDecimal chargeKenKyakusuhiRui) {
        this.chargeKenKyakusuhiRui = chargeKenKyakusuhiRui;
    }
    
    /**
     *  チャージ単価（当日）を取得します。
     * @return チャージ単価（当日）
     */
    public BigDecimal getChargeTanka() {
        return chargeTanka;
    }
    /**
     *  チャージ単価（当日）を設定します。
     * @param chargeTanka  チャージ単価（当日）
     */
    public void setChargeTanka(BigDecimal chargeTanka) {
        this.chargeTanka = chargeTanka;
    }
    
    /**
     *  チャージ単価（前年同曜日）を取得します。
     * @return チャージ単価（前年同曜日）
     */
    public BigDecimal getChargeTankaZenDoyo() {
        return chargeTankaZenDoyo;
    }
    /**
     *  チャージ単価（前年同曜日）を設定します。
     * @param chargeTankaZenDoyo  チャージ単価（前年同曜日）
     */
    public void setChargeTankaZenDoyo(BigDecimal chargeTankaZenDoyo) {
        this.chargeTankaZenDoyo = chargeTankaZenDoyo;
    }
    
    /**
     *  チャージ単価（単価比）を取得します。
     * @return チャージ単価（単価比）
     */
    public BigDecimal getChargeTankaTankahi() {
        return chargeTankaTankahi;
    }
    /**
     *  チャージ単価（単価比）を設定します。
     * @param chargeTankaTankahi  チャージ単価（単価比）
     */
    public void setChargeTankaTankahi(BigDecimal chargeTankaTankahi) {
        this.chargeTankaTankahi = chargeTankaTankahi;
    }
    
    /**
     *  チャージ単価（前年対比）を取得します。
     * @return チャージ単価（前年対比）
     */
    public BigDecimal getChargeTankaZenhi() {
        return chargeTankaZenhi;
    }
    /**
     *  チャージ単価（前年対比）を設定します。
     * @param chargeTankaZenhi  チャージ単価（前年対比）
     */
    public void setChargeTankaZenhi(BigDecimal chargeTankaZenhi) {
        this.chargeTankaZenhi = chargeTankaZenhi;
    }
    
    /**
     *  決済金額（売上比）（当日）を取得します。
     * @return チャージ単価（前年対比）
     */
    public BigDecimal getKessaiKinUriagehi() {
        return kessaiKinUriagehi;
    }
    /**
     *  決済金額（売上比）（当日）を設定します。
     * @param kessaiKinUriagehi  決済金額（売上比）（当日）
     */
    public void setKessaiKinUriagehi(BigDecimal kessaiKinUriagehi) {
        this.kessaiKinUriagehi = kessaiKinUriagehi;
    }
    
    /**
     *  決済金額（売上比）（累計）を取得します。
     * @return 決済金額（売上比）（累計）
     */
    public BigDecimal getKessaiKinUriagehiRui() {
        return kessaiKinUriagehiRui;
    }
    /**
     *  決済金額（売上比）（累計）を設定します。
     * @param kessaiKinUriagehiRui  決済金額（売上比）（累計）
     */
    public void setKessaiKinUriagehiRui(BigDecimal kessaiKinUriagehiRui) {
        this.kessaiKinUriagehiRui = kessaiKinUriagehiRui;
    }
    
    /**
     *  決済金額（前年対比）を取得します。
     * @return 決済金額（前年対比）
     */
    public BigDecimal getKessaiKinZenUriagehi() {
        return kessaiKinZenUriagehi;
    }
    /**
     *  決済金額（前年対比）を設定します。
     * @param kessaiKinZenUriagehi 決済金額（前年対比）
     */
    public void setKessaiKinZenUriagehi(BigDecimal kessaiKinZenUriagehi) {
        this.kessaiKinZenUriagehi = kessaiKinZenUriagehi;
    }
    
    /**
     *  決済件数（客数比当日）を取得します。
     * @return 決済件数（客数比当日）
     */
    public BigDecimal getKessaiKenKyakusuhi() {
        return kessaiKenKyakusuhi;
    }
    /**
     *  決済件数（客数比当日）を設定します。
     * @param kessaiKenKyakusuhi 決済件数（客数比当日）
     */
    public void setKessaiKenKyakusuhi(BigDecimal kessaiKenKyakusuhi) {
        this.kessaiKenKyakusuhi = kessaiKenKyakusuhi;
    }
    
    /**
     *  決済件数前年値（前年対比）を取得します。
     * @return 決済件数前年値（前年対比）
     */
    public BigDecimal getKessaiKenZenKyakusuhi() {
        return kessaiKenZenKyakusuhi;
    }
    /**
     *  決済件数前年値（前年対比）を設定します。
     * @param kessaiKenZenKyakusuhi 決済件数前年値（前年対比）
     */
    public void setKessaiKenZenKyakusuhi(BigDecimal kessaiKenZenKyakusuhi) {
        this.kessaiKenZenKyakusuhi = kessaiKenZenKyakusuhi;
    }
    
    /**
     *  決済件数（客数比累計）を取得します。
     * @return 決済件数（客数比累計）
     */
    public BigDecimal getKessaiKenKyakusuhiRui() {
        return kessaiKenKyakusuhiRui;
    }
    /**
     *  決済件数（客数比累計）を設定します。
     * @param kessaiKenKyakusuhiRui 決済件数（客数比累計）
     */
    public void setKessaiKenKyakusuhiRui(BigDecimal kessaiKenKyakusuhiRui) {
        this.kessaiKenKyakusuhiRui = kessaiKenKyakusuhiRui;
    }
    
    /**
     *  決済単価（当日）を取得します。
     * @return 決済単価（当日）
     */
    public BigDecimal getKessaiTanka() {
        return kessaiTanka;
    }
    /**
     *  決済単価（当日）を設定します。
     * @param kessaiTanka 決済単価（当日）
     */
    public void setKessaiTanka(BigDecimal kessaiTanka) {
        this.kessaiTanka = kessaiTanka;
    }
    
    /**
     *  決済単価（前年同曜日）を取得します。
     * @return 決済単価（前年同曜日）
     */
    public BigDecimal getKessaiTankaZenDoyo() {
        return kessaiTankaZenDoyo;
    }
    /**
     *  決済単価（前年同曜日）を設定します。
     * @param kessaiTankaZenDoyo 決済単価（前年同曜日）
     */
    public void setKessaiTankaZenDoyo(BigDecimal kessaiTankaZenDoyo) {
        this.kessaiTankaZenDoyo = kessaiTankaZenDoyo;
    }
    
    /**
     *  決済単価（単価比）を取得します。
     * @return 決済単価（単価比）
     */
    public BigDecimal getKessaiTankaTankahi() {
        return kessaiTankaTankahi;
    }
    /**
     *  決済単価（単価比）を設定します。
     * @param kessaiTankaTankahi 決済単価（単価比）
     */
    public void setKessaiTankaTankahi(BigDecimal kessaiTankaTankahi) {
        this.kessaiTankaTankahi = kessaiTankaTankahi;
    }
    
    /**
     *  決済単価（前年対比）を取得します。
     * @return 決済単価（前年対比）
     */
    public BigDecimal getKessaiTankaZenhi() {
        return kessaiTankaZenhi;
    }
    /**
     *  決済単価（前年対比）を設定します。
     * @param kessaiTankaZenhi 決済単価（前年対比）
     */
    public void setKessaiTankaZenhi(BigDecimal kessaiTankaZenhi) {
        this.kessaiTankaZenhi = kessaiTankaZenhi;
    }
}
