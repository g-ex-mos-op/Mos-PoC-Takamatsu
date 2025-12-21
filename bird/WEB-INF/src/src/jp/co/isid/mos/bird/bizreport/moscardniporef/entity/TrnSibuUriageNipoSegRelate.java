package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

public class TrnSibuUriageNipoSegRelate {
    
    public static final String TABLE = "BD35ZNMC";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String segmentType_COLUMN = "SEGMENT_TYPE";
    
    public static final String segmentName_COLUMN = "SEGMENT_NAME";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String openKbn_COLUMN = "OPEN_KBN";
    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String onerYosan_COLUMN = "ONER_YOSAN";
    
    public static final String issueCnt_COLUMN = "ISSUE_CNT";
    
    public static final String chargeKin_COLUMN = "CHARGE_KIN";
    
    public static final String chargeKen_COLUMN = "CHARGE_KEN";
    
    public static final String kessaiKin_COLUMN = "KESSAI_KIN";
    
    public static final String kessaiKen_COLUMN = "KESSAI_KEN";

    public static final String issueCntZen_COLUMN = "ISSUE_CNT_ZEN";
    
    public static final String chargeKinZen_COLUMN = "CHARGE_KIN_ZEN";
    
    public static final String chargeKenZen_COLUMN = "CHARGE_KEN_ZEN";
    
    public static final String kessaiKinZen_COLUMN = "KESSAI_KIN_ZEN";
    
    public static final String kessaiKenZen_COLUMN = "KESSAI_KEN_ZEN";
    
    public static final String sibuYosan_COLUMN = "SIBU_YOSAN";
    
    public static final String miseCount_COLUMN = "MISE_COUNT";
    
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
     * セグメントタイプ
     */
    private String segmentType;
    
    /**
     * セグメント名称
     */
    private String segmentName;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
      
    /**
     * 当年OPEN区分
     */
    private BigDecimal openKbn;
    
    /**
     * 前年OPEN区分
     */
    private BigDecimal openKbnZen;
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen;
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen;
    
    /**
     * オーナー予算
     */
    private BigDecimal onerYosan;
    
    /**
     * 事業計画予算
     */
    private BigDecimal uriYosan;
    
    /**
     * 予算対象店舗数
     * 作成日：2007/06/05 ADD xkinu クローズ店予算表示対応
     */
    private BigDecimal yosanMiseCnt = new BigDecimal("0");
    
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
     * 前年発行枚数
     */
    private BigDecimal issueCntZen;
    
    /**
     * 前年チャージ金額
     */
    private BigDecimal chargeKinZen;
    
    /**
     * 前年チャージ件数
     */
    private BigDecimal chargeKenZen;
    
    /**
     * 前年決済金額
     */
    private BigDecimal kessaiKinZen;
    
    /**
     * 前年決済件数
     */
    private BigDecimal kessaiKenZen;
    
    /**
     * 予算
     */
    private BigDecimal sibuYosan;
    
    /**
     * 予算対象店舗
     */
    private BigDecimal miseCount;
    
    /**
     * 入金取消金額
     * */
    private BigDecimal chargeKinCancel;
    /**
     * 入金取消件数
     * */
    private BigDecimal chargeKenCancel;
    /**
     * 利用取消金額
     * */
    private BigDecimal useKinCancel;
    /**
     * 利用取消件数
     * */
    private BigDecimal useKenCancel;
    /**
     * 発行ボーナスバリュー
     * */
    private BigDecimal bonusVIssue;
    /**
     * 利用ボーナスバリュー
     * */
    private BigDecimal bonusVUse;
    /**
     * 発行クーポンバリュー
     * */
    private BigDecimal couponVIssue;
    
    /**
     * 利用クーポンバリュー
     * */
    private BigDecimal couponVUse;
    
    /**
     * 前受残高
     * */
    private BigDecimal zandaka;
    
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
     * セグメントタイプを取得します。
     * @return セグメントタイプ
     */
    public String getSegmentType() {
        return segmentType;
    }
    /**
     * セグメントタイプを設定します。
     * @param segmentType セグメントタイプ
     */
    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }
    
    /**
     * セグメント名称を取得します。
     * @return セグメント名称
     */
    public String getSegmentName() {
        return segmentName;
    }
    /**
     * セグメント名称を設定します。
     * @param segmentName セグメント名称
     */
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
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
	 * 予算対象店舗数取得処理
	 * 
	 * @return yosanMiseCnt を戻します。
	 */
	public BigDecimal getYosanMiseCnt() {
		return yosanMiseCnt;
	}
	/**
	 * 予算対象店舗数設定処理
	 * 
	 * @param yosanMiseCnt 設定する yosanMiseCnt。
	 */
	public void setYosanMiseCnt(BigDecimal yosanMiseCnt) {
		this.yosanMiseCnt = yosanMiseCnt;
	}
    
    
    
    
    
    /**
     * 発行枚数取得処理
     * 
     * @return issueCnt を戻します。
     */
    public BigDecimal getIssueCnt() {
        return issueCnt;
    }
    /**
     * 発行枚数設定処理
     * 
     * @param issueCnt 設定する issueCnt。
     */
    public void setIssueCnt(BigDecimal issueCnt) {
        this.issueCnt = issueCnt;
    }
    /**
     *  チャージ金額取得処理
     * 
     * @return issueCnt を戻します。
     */
    public BigDecimal getChargeKin() {
        return chargeKin;
    }
    /**
     *  チャージ金額設定処理
     * 
     * @param issueCnt 設定する issueCnt。
     */
    public void setChargeKin(BigDecimal chargeKin) {
        this.chargeKin = chargeKin;
    }
    /**
     *  チャージ金額取得処理
     * 
     * @return chargeKen を戻します。
     */
    public BigDecimal getChargeKen() {
        return chargeKen;
    }
    /**
     *  チャージ金額設定処理
     * 
     * @param chargeKen 設定する chargeKen。
     */
    public void setChargeKen(BigDecimal chargeKen) {
        this.chargeKen = chargeKen;
    }   
    /**
     *  決済金額取得処理
     * 
     * @return kessaiKin を戻します。
     */
    public BigDecimal getKessaiKin() {
        return kessaiKin;
    }
    /**
     *  決済金額設定処理
     * 
     * @param kessaiKin 設定する kessaiKin。
     */
    public void setKessaiKin(BigDecimal kessaiKin) {
        this.kessaiKin = kessaiKin;
    }   
    /**
     *  決済件数取得処理
     * 
     * @return kessaiKen を戻します。
     */
    public BigDecimal getKessaiKen() {
        return kessaiKen;
    }
    /**
     *  決済件数設定処理
     * 
     * @param kessaiKen 設定する kessaiKen。
     */
    public void setKessaiKen(BigDecimal kessaiKen) {
        this.kessaiKen = kessaiKen;
    }   
    /**
     *  前年発行枚数取得処理
     * 
     * @return issueCntZen を戻します。
     */
    public BigDecimal getIssueCntZen() {
        return issueCntZen;
    }
    /**
     *  前年発行枚数設定処理
     * 
     * @param issueCntZen 設定する issueCntZen。
     */
    public void setIssueCntZen(BigDecimal issueCntZen) {
        this.issueCntZen = issueCntZen;
    }
    /**
     *  前年チャージ金額取得処理
     * 
     * @return chargeKenZen を戻します。
     */
    public BigDecimal getChargeKinZen() {
        return chargeKinZen;
    }
    /**
     *  前年チャージ金額設定処理
     * 
     * @param chargeKinZen 設定する chargeKinZen。
     */
    public void setChargeKinZen(BigDecimal chargeKinZen) {
        this.chargeKinZen = chargeKinZen;
    }
    /**
     *  前年チャージ件数取得処理
     * 
     * @return chargeKenZen を戻します。
     */
    public BigDecimal getChargeKenZen() {
        return chargeKenZen;
    }
    /**
     *  前年チャージ件数設定処理
     * 
     * @param chargeKenZen 設定する chargeKenZen。
     */
    public void setChargeKenZen(BigDecimal chargeKenZen) {
        this.chargeKenZen = chargeKenZen;
    }
    /**
     *  前年チャージ件数取得処理
     * 
     * @return kessaiKinZen を戻します。
     */
    public BigDecimal getKessaiKinZen() {
        return kessaiKinZen;
    }
    /**
     *  前年決済金額設定処理
     * 
     * @param kessaiKinZen 設定する kessaiKinZen。
     */
    public void setKessaiKinZen(BigDecimal kessaiKinZen) {
        this.kessaiKinZen = kessaiKinZen;
    }   
    /**
     *  前年決済件数取得処理
     * 
     * @return kessaiKenZen を戻します。
     */
    public BigDecimal getkessaiKenZen() {
        return kessaiKenZen;
    }
    /**
     *  前年決済金額設定処理
     * 
     * @param kessaiKinZen 設定する kessaiKinZen。
     */
    public void setKessaiKenZen(BigDecimal kessaiKenZen) {
        this.kessaiKenZen = kessaiKenZen;
    }   

    /**
     *  予算
     * 
     * @return yosan を戻します。
     */
    public BigDecimal getSibuYosan() {
        return sibuYosan;
    }
    /**
     *  予算
     * 
     * @param yosan 設定する yosan。
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
     * @param sibuYosan 予算
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
