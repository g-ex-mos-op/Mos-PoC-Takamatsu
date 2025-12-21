package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

public class TrnUriageInfo {
    
    public static final String TABLE = "BD35ZNMC";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String openKbn_COLUMN = "OPEN_KBN";
    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String zenKyakusu_COLUMN = "KYAKUSU_ZEN";
    
    public static final String onerYosan_COLUMN = "ONER_YOSAN";
    
    public static final String uriYosan_COLUMN = "URI_YOSAN";

    public static final String issueCnt_COLUMN = "ISSUE_CNT";
    
    public static final String chargeKin_COLUMN = "CHARGE_KIN";
    
    public static final String chargeKen_COLUMN = "CHARGE_KEN";
    
    public static final String kessaiKin_COLUMN = "KESSAI_KIN";
    
    public static final String kessaiKen_COLUMN = "KESSAI_KEN";
    
    public static final String zenIssueCnt_COLUMN = "ZEN_ISSUE_CNT";
    
    public static final String zenChargeKin_COLUMN = "ZEN_CHARGE_KIN";
    
    public static final String zenChargeKen_COLUMN = "ZEN_CHARGE_KEN";
    
    public static final String zenKessaiKin_COLUMN = "ZEN_KESSAI_KIN";
    
    public static final String zenkessaiKen_COLUMN = "ZEN_KESSAI_KEN";
    
    public static final String sibuYosan_COLUMN = "SIBU_YOSAN";
    
    public static final String miseCount_COLUMN = "MISE_CNT";
    
    public static final String chargeKinCancel_COLUMN = "CHARGE_KIN_CANCEL";
    
    public static final String chargeKenCancel__COLUMN = "CHARGE_KEN_CANCEL";
    
    public static final String useKinCancel_COLUMN = "USE_KIN_CANCEL";
    
    public static final String useKenCancel_COLUMN = "USE_KEN_CANCEL";
    
    public static final String bonusVIssue_COLUMN = "BONUS_V_ISSUE";
    
    public static final String bonusVUse_COLUMN = "BONUS_V_USE";
    
    public static final String couponVIssue_COLUMN = "COUPON_V_ISSUE";
    
    public static final String couponVUse_COLUMN = "COUPON_V_USE";
    
    public static final String zandaka_COLUMN = "ZANDAKA";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 売上
     */
    private BigDecimal uriage = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 当年OPEN区分
     */
    private BigDecimal openKbn = new BigDecimal("0");
    
    /**
     * 前年OPEN区分
     */
    private BigDecimal openKbnZen = new BigDecimal("0");
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    
    /**
     * オーナー予算
     */
    private BigDecimal onerYosan = new BigDecimal("0");
    
    /**
     * 事業計画予算
     */
    private BigDecimal uriYosan = new BigDecimal("0");

    /**
     * 発行枚数
     */
    private BigDecimal issueCnt = new BigDecimal("0");
    
    /**
     * 入金額
     */
    private BigDecimal chargeKin = new BigDecimal("0");
    
    /**
     * 入金件数
     */
    private BigDecimal chargeKen = new BigDecimal("0");
    
    /**
     * 決済金額
     */
    private BigDecimal kessaiKin = new BigDecimal("0");
    
    /**
     * 決済件数
     */
    private BigDecimal kessaiKen = new BigDecimal("0");
    
    /**
     * 前年同月発行枚数
     */
    private BigDecimal zenIssueCnt = new BigDecimal("0");
    
    /**
     * 前年同月チャージ金額
     */
    private BigDecimal zenChargeKin = new BigDecimal("0");    
    
    /**
     * 前年同月チャージ件数
     */
    private BigDecimal zenChargeKen = new BigDecimal("0");
    
    /**
     * 前年同月決済金額
     */
    private BigDecimal zenKessaiKin = new BigDecimal("0");    
    
    /**
     * 前年同月決済件数
     */
    private BigDecimal zenkessaiKen = new BigDecimal("0");
    
    /**
     * 予算
     */
    private BigDecimal sibuYosan = new BigDecimal("0");
    
    /**
     * 予算対象店舗
     */
    private BigDecimal miseCount = new BigDecimal("0");
    
    /**
     * 入金取消金額
     * */
    private BigDecimal chargeKinCancel = new BigDecimal("0");
    /**
     * 入金取消件数
     * */
    private BigDecimal chargeKenCancel = new BigDecimal("0");
    /**
     * 利用取消金額
     * */
    private BigDecimal useKinCancel = new BigDecimal("0");
    /**
     * 利用取消件数
     * */
    private BigDecimal useKenCancel = new BigDecimal("0");
    /**
     * 発行ボーナスバリュー
     * */
    private BigDecimal bonusVIssue = new BigDecimal("0");
    /**
     * 利用ボーナスバリュー
     * */
    private BigDecimal bonusVUse = new BigDecimal("0");
    /**
     * 発行クーポンバリュー
     * */
    private BigDecimal couponVIssue = new BigDecimal("0");
    
    /**
     * 利用クーポンバリュー
     * */
    private BigDecimal couponVUse = new BigDecimal("0");
    
    /**
     * 前受残高
     * */
    private BigDecimal zandaka = new BigDecimal("0");
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
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
     * 当年OPEN区分を取得します。
     * @return 当年OPEN区分
     */
    public BigDecimal getOpenKbn() {
        return openKbn;
    }
    /**
     * 当年OPEN区分を設定します。
     * @param openKbn 当年OPEN区分
     */
    public void setOpenKbn(BigDecimal openKbn) {
        this.openKbn = openKbn;
    }
    
    /**
     * 前年OPEN区分を取得します。
     * @return 前年OPEN区分
     */
    public BigDecimal getOpenKbnZen() {
        return openKbnZen;
    }
    /**
     * 前年OPEN区分を設定します。
     * @param openKbnZen 前年OPEN区分
     */
    public void setOpenKbnZen(BigDecimal openKbnZen) {
        this.openKbnZen = openKbnZen;
    }
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上を設定します。
     * @param uriageZen 前年売上
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
    }
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen() {
        return kyakusuZen;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen) {
        this.kyakusuZen = kyakusuZen;
    }
    
    /**
     * オーナー予算を取得します。
     * @return オーナー予算
     */
    public BigDecimal getOnerYosan() {
        return onerYosan;
    }
    /**
     * オーナー予算を設定します。
     * @param onerYosan オーナー予算
     */
    public void setOnerYosan(BigDecimal onerYosan) {
        this.onerYosan = onerYosan;
    }
    
    /**
     * 事業計画予算を取得します。
     * @return 事業計画予算
     */
    public BigDecimal getUriYosan() {
        return uriYosan;
    }
    /**
     * 事業計画予算を設定します。
     * @param uriYosan 事業計画予算
     */
    public void setUriYosan(BigDecimal uriYosan) {
        this.uriYosan = uriYosan;
    }
    
    /**
     * 発行枚数を取得します。
     * @return issueCnt 発行枚数
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
     * 入金額を取得します。
     * @return 入金額
     */
    public BigDecimal getChargeKin() {
        return chargeKin;
    }
    /**
     * 入金額を設定します。
     * @param chargeKin 入金額
     */
    public void setChargeKin(BigDecimal chargeKin) {
        this.chargeKin = chargeKin;
    }
    
    /**
     * 入金件数を取得します。
     * @return 入金件数
     */
    public BigDecimal getChargeKen() {
        return chargeKen;
    }
    /**
     * 入金件数を設定します。
     * @param chargeKen 入金件数
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
     * @param chargeKen 決済金額
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
     * 決済件数を設定します。
     * @param chargeKen 決済件数
     */
    public void setKessaiKen(BigDecimal kessaiKen) {
        this.kessaiKen = kessaiKen;
    }
    
    /**
     * 前年同月発行枚数を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenIssueCnt() {
        return zenIssueCnt;
    }
    /**
     * 前年同月発行枚数を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenIssueCnt(BigDecimal zenIssueCnt) {
        this.zenIssueCnt = zenIssueCnt;
    }
    
    /**
     * 前年同月チャージ金額を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenChargeKin() {
        return zenChargeKin;
    }
    /**
     * 前年同月チャージ金額を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenChargeKin(BigDecimal zenChargeKin) {
        this.zenChargeKin = zenChargeKin;
    }
    
    /**
     * 前年同月チャージ件数を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenChargeKen() {
        return zenChargeKen;
    }
    /**
     * 前年同月チャージ件数を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenChargeKen(BigDecimal zenChargeKen) {
        this.zenChargeKen = zenChargeKen;
    }
    
    /**
     * 前年同月決済金額を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenKessaiKin() {
        return zenKessaiKin;
    }
    /**
     * 前年同月決済金額を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenKessaiKin(BigDecimal zenKessaiKin) {
        this.zenKessaiKin = zenKessaiKin;
    }
    
    /**
     * 前年同月決済件数を取得します。
     * @return 前年同月発行枚数
     */
    public BigDecimal getZenKessaiKen() {
        return zenkessaiKen;
    }
    /**
     * 前年同月決済件数を設定します。
     * @param chargeKen 前年同月発行枚数
     */
    public void setZenKessaiKen(BigDecimal zenkessaiKen) {
        this.zenkessaiKen = zenkessaiKen;
    }
    
    /**
     * 予算を取得します。
     * @return 予算
     */
    public BigDecimal getSibuYosan() {
        return sibuYosan;
    }
    /**
     * 予算を設定します。
     * @param sibuYosan 予算
     */
    public void setSibuYosan(BigDecimal sibuYosan) {
        this.sibuYosan = sibuYosan;
    }
    
    /**
     * 予算対象店舗を取得します。
     * @return 予算
     */
    public BigDecimal getMiseCount() {
        return miseCount;
    }
    /**
     * 予算対象店舗を設定します。
     * @param miseCount 予算
     */
    public void setMiseCount(BigDecimal miseCount) {
        this.miseCount = miseCount;
    }
    
    /**
     * 入金取消金額を取得します。
     * @return 入金取消金額
     */
    public BigDecimal getChargeKinCancel() {
        return chargeKinCancel;
    }
    /**
     * 入金取消金額を設定します。
     * @param chargeKinCancel 入金取消金額
     */
    public void setChargeKinCancel(BigDecimal chargeKinCancel) {
        this.chargeKinCancel = chargeKinCancel;
    }
    
    /**
     * 入金取消件数を取得します。
     * @return 入金取消件数
     */
    public BigDecimal getChargeKenCancel() {
        return chargeKenCancel;
    }
    /**
     * 入金取消件数を設定します。
     * @param chargeKenCancel 入金取消件数
     */
    public void setChargeKenCancel(BigDecimal chargeKenCancel) {
        this.chargeKenCancel = chargeKenCancel;
    }
    
    /**
     * 利用取消金額を取得します。
     * @return 利用取消金額
     */
    public BigDecimal getUseKinCancel() {
        return useKinCancel;
    }
    /**
     * 利用取消金額を設定します。
     * @param useKinCancel 利用取消金額
     */
    public void setUseKinCancel(BigDecimal useKinCancel) {
        this.useKinCancel = useKinCancel;
    }

    /**
     * 利用取消件数を取得します。
     * @return 利用取消件数
     */
    public BigDecimal getUseKenCancel() {
        return useKenCancel;
    }
    /**
     * 利用取消件数を設定します。
     * @param useKinCancel 利用取消件数
     */
    public void setUseKenCancel(BigDecimal useKenCancel) {
        this.useKenCancel = useKenCancel;
    }
    
    /**
     * 発行ボーナスバリューを取得します。
     * @return 発行ボーナスバリュー
     */
    public BigDecimal getBonusVIssue() {
        return bonusVIssue;
    }
    /**
     * 発行ボーナスバリューを設定します。
     * @param bonusVIssue 発行ボーナスバリュー
     */
    public void setBonusVIssue(BigDecimal bonusVIssue) {
        this.bonusVIssue = bonusVIssue;
    }
    
    /**
     * 利用ボーナスバリューを取得します。
     * @return 利用ボーナスバリュー
     */
    public BigDecimal getBonusVUse() {
        return bonusVUse;
    }
    
    /**
     * 利用ボーナスバリューを設定します。
     * @param bonusVUse 利用ボーナスバリュー
     */
    public void setBonusVUse(BigDecimal bonusVUse) {
        this.bonusVUse = bonusVUse;
    }
    
    /**
     * 発行クーポンバリューを取得します。
     * @return 発行クーポンバリュー
     */
    public BigDecimal getCouponVIssue() {
        return couponVIssue;
    }
    /**
     * 発行クーポンバリューを設定します。
     * @param couponVIssue 発行クーポンバリュー
     */
    public void setCouponVIssue(BigDecimal couponVIssue) {
        this.couponVIssue = couponVIssue;
    }
    
    /**
     * 利用クーポンバリューを取得します。
     * @return 利用クーポンバリュー
     */
    public BigDecimal getCouponVUse() {
        return couponVUse;
    }
    /**
     * 利用クーポンバリューを設定します。
     * @param couponVUse 利用クーポンバリュー
     */
    public void setCouponVUse(BigDecimal couponVUse) {
        this.couponVUse = couponVUse;
    }
    /**
     * 前受残高を取得します。
     * @return 前受残高
     */
    public BigDecimal getZandaka() {
        return zandaka;
    }
    /**
     * 前受残高を設定します。
     * @param zandaka 前受残高
     */
    public void setZandaka(BigDecimal zandaka) {
        this.zandaka = zandaka;
    }
}
