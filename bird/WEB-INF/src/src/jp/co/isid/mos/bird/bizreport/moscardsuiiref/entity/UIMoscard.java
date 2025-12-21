/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;

/**
 * Entity[MOSCARD推移情報]
 * 
 * 作成日:2013/05/07
 * @author xkinu
 *
 */
public class UIMoscard extends SuiiRefUIEntity {
	
    public static final String TABLE = "BD35ZNMC";
	
    public static final String chargeKinCancel_COLUMN = "CHARGE_KIN_CANCEL";
    
    public static final String chargeKenCancel_COLUMN = "CHARGE_KEN_CANCEL";
    
    public static final String useKinCancel_COLUMN = "USE_KIN_CANCEL";
    
    public static final String useKenCancel_COLUMN = "USE_KEN_CANCEL";
    
    public static final String bonusVIssue_COLUMN = "BONUS_V_ISSUE";
    
    public static final String bonusVUse_COLUMN = "BONUS_V_USE";
    
    public static final String couponVIssue_COLUMN = "COUPON_V_ISSUE";
    
    public static final String couponVUse_COLUMN = "COUPON_V_USE";
    
    public static final String zandaka_COLUMN = "ZANDAKA";

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

    public static final String netIssueCnt_COLUMN = "NET_ISSUE_CNT";
    
    public static final String netChargeKin_COLUMN = "NET_CHARGE_KIN";
    
    public static final String netChargeKen_COLUMN = "NET_CHARGE_KEN";
    
    public static final String netKessaiKin_COLUMN = "NET_KESSAI_KIN";
    
    public static final String netKessaiKen_COLUMN = "NET_KESSAI_KEN";

    public static final String netZenIssueCnt_COLUMN = "NET_ZEN_ISSUE_CNT";
    
    public static final String netZenChargeKin_COLUMN = "NET_ZEN_CHARGE_KIN";
    
    public static final String netZenChargeKen_COLUMN = "NET_ZEN_CHARGE_KEN";
    
    public static final String netZenKessaiKin_COLUMN = "NET_ZEN_KESSAI_KIN";
    
    public static final String netZenkessaiKen_COLUMN = "NET_ZEN_KESSAI_KEN";
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
     * 発行枚数
     * */
    private BigDecimal issueCnt = new BigDecimal("0");
    /**
     * チャージ金額
     * */
    private BigDecimal chargeKin = new BigDecimal("0");
    /**
     * チャージ件数
     * */
    private BigDecimal chargeKen = new BigDecimal("0");
    /**
     * 決済金額
     * */
    private BigDecimal kessaiKin = new BigDecimal("0");
    /**
     * 決済件数
     * */
    private BigDecimal kessaiKen = new BigDecimal("0");
    /**
     * チャージ単価
     * */
    private BigDecimal chargeTanka = new BigDecimal("0");
    /**
     * 決済単価
     * */
    private BigDecimal kessaiTanka = new BigDecimal("0");
    /**
     * 前年チャージ単価
     * */
    private BigDecimal zenChargeTanka = new BigDecimal("0");
    /**
     * 前年決済単価
     * */
    private BigDecimal zenKessaiTanka = new BigDecimal("0");
    /**
     * NETチャージ単価
     * */
    private BigDecimal netChargeTanka = new BigDecimal("0");
    /**
     * NET決済単価
     * */
    private BigDecimal netKessaiTanka = new BigDecimal("0");
    /**
     * NET前年チャージ単価
     * */
    private BigDecimal netZenChargeTanka = new BigDecimal("0");
    /**
     * NET前年決済単価
     * */
    private BigDecimal netZenKessaiTanka = new BigDecimal("0");
    /**
     * チャージ(売上比)
     * */
    private BigDecimal chargeUriagehi = new BigDecimal("0.00");
    /**
     * チャージ(客数比)
     * */
    private BigDecimal chargeKyakusuhi = new BigDecimal("0.00");
    /**
     * チャージ(単価比)
     * */
    private BigDecimal chargeTankahi = new BigDecimal("0.00");
    /**
     * 決済(売上比)
     * */
    private BigDecimal kessaiUriagehi = new BigDecimal("0.00");
    /**
     * 決済(客数比)
     * */
    private BigDecimal kessaiKyakusuhi = new BigDecimal("0.00");
    /**
     * 決済(単価比)
     * */
    private BigDecimal kessaiTankahi = new BigDecimal("0.00");
    /**
     * 前年発行枚数
     * */
    private BigDecimal zenIssueCnt = new BigDecimal("0");
    /**
     * 前年チャージ金額
     * */
    private BigDecimal zenChargeKin = new BigDecimal("0");
    /**
     * 前年チャージ件数
     * */
    private BigDecimal zenChargeKen = new BigDecimal("0");
    /**
     * 前年決済金額
     * */
    private BigDecimal zenKessaiKin = new BigDecimal("0");
    /**
     * 前年決済件数
     * */
    private BigDecimal zenKessaiKen = new BigDecimal("0");
    /**
     * NET発行枚数
     * */
    private BigDecimal netIssueCnt = new BigDecimal("0");
    /**
     * NETチャージ金額
     * */
    private BigDecimal netChargeKin = new BigDecimal("0");
    /**
     * NETチャージ件数
     * */
    private BigDecimal netChargeKen = new BigDecimal("0");
    /**
     * NET決済金額
     * */
    private BigDecimal netKessaiKin = new BigDecimal("0");
    /**
     * NET決済件数
     * */
    private BigDecimal netKessaiKen = new BigDecimal("0");
    /**
     * NET前年発行枚数
     * */
    private BigDecimal netZenIssueCnt = new BigDecimal("0");
    /**
     * NET前年チャージ金額
     * */
    private BigDecimal netZenChargeKin = new BigDecimal("0");
    /**
     * NET前年チャージ件数
     * */
    private BigDecimal netZenChargeKen = new BigDecimal("0");
    /**
     * NET前年決済金額
     * */
    private BigDecimal netZenKessaiKin = new BigDecimal("0");
    /**
     * NET前年決済件数
     * */
    private BigDecimal netZenKessaiKen = new BigDecimal("0");
    
    /**
     * 発行枚数前年比
     * */
    private BigDecimal issueCntZennenhi = new BigDecimal("0.00");
    /**
     * チャージ金額前年比
     * */
    private BigDecimal chargeKinZennenhi = new BigDecimal("0.00");
    /**
     * チャージ件数前年比
     * */
    private BigDecimal chargeKenZennenhi = new BigDecimal("0.00");
    /**
     * チャージ単価前年比
     * */
    private BigDecimal chargeTankaZennenhi = new BigDecimal("0.00");
    /**
     * 決済金額前年比
     * */
    private BigDecimal kessaiKinZennenhi = new BigDecimal("0.00");
    /**
     * 決済件数前年比
     * */
    private BigDecimal kessaiKenZennenhi = new BigDecimal("0.00");
    /**
     * 決済単価前年比
     * */
    private BigDecimal kessaiTankaZennenhi = new BigDecimal("0.00");
    /**
     * NET発行枚数前年比
     * */
    private BigDecimal netIssueCntZennenhi = new BigDecimal("0.00");
    /**
     * NETチャージ金額前年比
     * */
    private BigDecimal netChargeKinZennenhi = new BigDecimal("0.00");
    /**
     * NETチャージ件数前年比
     * */
    private BigDecimal netChargeKenZennenhi = new BigDecimal("0.00");
    /**
     * NETチャージ単価前年比
     * */
    private BigDecimal netChargeTankaZennenhi = new BigDecimal("0.00");
    /**
     * NET決済金額前年比
     * */
    private BigDecimal netKessaiKinZennenhi = new BigDecimal("0.00");
    /**
     * NET決済件数前年比
     * */
    private BigDecimal netKessaiKenZennenhi = new BigDecimal("0.00");
    /**
     * NET決済単価前年比
     * */
    private BigDecimal netKessaiTankaZennenhi = new BigDecimal("0.00");
   
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
	/**
	 * @return クラス変数chargeKen を戻します。
	 */
	public BigDecimal getChargeKen() {
		return chargeKen;
	}
	/**
	 * @param chargeKen を クラス変数chargeKenへ設定します。
	 */
	public void setChargeKen(BigDecimal chargeKen) {
		this.chargeKen = chargeKen;
	}
	/**
	 * @return クラス変数chargeKin を戻します。
	 */
	public BigDecimal getChargeKin() {
		return chargeKin;
	}
	/**
	 * @param chargeKin を クラス変数chargeKinへ設定します。
	 */
	public void setChargeKin(BigDecimal chargeKin) {
		this.chargeKin = chargeKin;
	}
	/**
	 * @return クラス変数issueCnt を戻します。
	 */
	public BigDecimal getIssueCnt() {
		return issueCnt;
	}
	/**
	 * @param issueCnt を クラス変数issueCntへ設定します。
	 */
	public void setIssueCnt(BigDecimal issueCnt) {
		this.issueCnt = issueCnt;
	}
	/**
	 * @return クラス変数kessaiKen を戻します。
	 */
	public BigDecimal getKessaiKen() {
		return kessaiKen;
	}
	/**
	 * @param kessaiKen を クラス変数kessaiKenへ設定します。
	 */
	public void setKessaiKen(BigDecimal kessaiKen) {
		this.kessaiKen = kessaiKen;
	}
	/**
	 * @return クラス変数kessaiKin を戻します。
	 */
	public BigDecimal getKessaiKin() {
		return kessaiKin;
	}
	/**
	 * @param kessaiKin を クラス変数kessaiKinへ設定します。
	 */
	public void setKessaiKin(BigDecimal kessaiKin) {
		this.kessaiKin = kessaiKin;
	}
	/**
	 * @return クラス変数netChargeKen を戻します。
	 */
	public BigDecimal getNetChargeKen() {
		return netChargeKen;
	}
	/**
	 * @param netChargeKen を クラス変数netChargeKenへ設定します。
	 */
	public void setNetChargeKen(BigDecimal netChargeKen) {
		this.netChargeKen = netChargeKen;
	}
	/**
	 * @return クラス変数netChargeKin を戻します。
	 */
	public BigDecimal getNetChargeKin() {
		return netChargeKin;
	}
	/**
	 * @param netChargeKin を クラス変数netChargeKinへ設定します。
	 */
	public void setNetChargeKin(BigDecimal netChargeKin) {
		this.netChargeKin = netChargeKin;
	}
	/**
	 * @return クラス変数netIssueCnt を戻します。
	 */
	public BigDecimal getNetIssueCnt() {
		return netIssueCnt;
	}
	/**
	 * @param netIssueCnt を クラス変数netIssueCntへ設定します。
	 */
	public void setNetIssueCnt(BigDecimal netIssueCnt) {
		this.netIssueCnt = netIssueCnt;
	}
	/**
	 * @return クラス変数netKessaiKen を戻します。
	 */
	public BigDecimal getNetKessaiKen() {
		return netKessaiKen;
	}
	/**
	 * @param netKessaiKen を クラス変数netKessaiKenへ設定します。
	 */
	public void setNetKessaiKen(BigDecimal netKessaiKen) {
		this.netKessaiKen = netKessaiKen;
	}
	/**
	 * @return クラス変数netKessaiKin を戻します。
	 */
	public BigDecimal getNetKessaiKin() {
		return netKessaiKin;
	}
	/**
	 * @param netKessaiKin を クラス変数netKessaiKinへ設定します。
	 */
	public void setNetKessaiKin(BigDecimal netKessaiKin) {
		this.netKessaiKin = netKessaiKin;
	}
	/**
	 * @return クラス変数netZenChargeKen を戻します。
	 */
	public BigDecimal getNetZenChargeKen() {
		return netZenChargeKen;
	}
	/**
	 * @param netZenChargeKen を クラス変数netZenChargeKenへ設定します。
	 */
	public void setNetZenChargeKen(BigDecimal netZenChargeKen) {
		this.netZenChargeKen = netZenChargeKen;
	}
	/**
	 * @return クラス変数netZenChargeKin を戻します。
	 */
	public BigDecimal getNetZenChargeKin() {
		return netZenChargeKin;
	}
	/**
	 * @param netZenChargeKin を クラス変数netZenChargeKinへ設定します。
	 */
	public void setNetZenChargeKin(BigDecimal netZenChargeKin) {
		this.netZenChargeKin = netZenChargeKin;
	}
	/**
	 * @return クラス変数netZenIssueCnt を戻します。
	 */
	public BigDecimal getNetZenIssueCnt() {
		return netZenIssueCnt;
	}
	/**
	 * @param netZenIssueCnt を クラス変数netZenIssueCntへ設定します。
	 */
	public void setNetZenIssueCnt(BigDecimal netZenIssueCnt) {
		this.netZenIssueCnt = netZenIssueCnt;
	}
	/**
	 * @return クラス変数netZenKessaiKen を戻します。
	 */
	public BigDecimal getNetZenKessaiKen() {
		return netZenKessaiKen;
	}
	/**
	 * @param netZenKessaiKen を クラス変数netZenKessaiKenへ設定します。
	 */
	public void setNetZenKessaiKen(BigDecimal netZenKessaiKen) {
		this.netZenKessaiKen = netZenKessaiKen;
	}
	/**
	 * @return クラス変数netZenKessaiKin を戻します。
	 */
	public BigDecimal getNetZenKessaiKin() {
		return netZenKessaiKin;
	}
	/**
	 * @param netZenKessaiKin を クラス変数netZenKessaiKinへ設定します。
	 */
	public void setNetZenKessaiKin(BigDecimal netZenKessaiKin) {
		this.netZenKessaiKin = netZenKessaiKin;
	}
	/**
	 * @return クラス変数zenChargeKen を戻します。
	 */
	public BigDecimal getZenChargeKen() {
		return zenChargeKen;
	}
	/**
	 * @param zenChargeKen を クラス変数zenChargeKenへ設定します。
	 */
	public void setZenChargeKen(BigDecimal zenChargeKen) {
		this.zenChargeKen = zenChargeKen;
	}
	/**
	 * @return クラス変数zenChargeKin を戻します。
	 */
	public BigDecimal getZenChargeKin() {
		return zenChargeKin;
	}
	/**
	 * @param zenChargeKin を クラス変数zenChargeKinへ設定します。
	 */
	public void setZenChargeKin(BigDecimal zenChargeKin) {
		this.zenChargeKin = zenChargeKin;
	}
	/**
	 * @return クラス変数zenIssueCnt を戻します。
	 */
	public BigDecimal getZenIssueCnt() {
		return zenIssueCnt;
	}
	/**
	 * @param zenIssueCnt を クラス変数zenIssueCntへ設定します。
	 */
	public void setZenIssueCnt(BigDecimal zenIssueCnt) {
		this.zenIssueCnt = zenIssueCnt;
	}
	/**
	 * @return クラス変数zenKessaiKen を戻します。
	 */
	public BigDecimal getZenKessaiKen() {
		return zenKessaiKen;
	}
	/**
	 * @param zenKessaiKen を クラス変数zenKessaiKenへ設定します。
	 */
	public void setZenKessaiKen(BigDecimal zenKessaiKen) {
		this.zenKessaiKen = zenKessaiKen;
	}
	/**
	 * @return クラス変数zenKessaiKin を戻します。
	 */
	public BigDecimal getZenKessaiKin() {
		return zenKessaiKin;
	}
	/**
	 * @param zenKessaiKin を クラス変数zenKessaiKinへ設定します。
	 */
	public void setZenKessaiKin(BigDecimal zenKessaiKin) {
		this.zenKessaiKin = zenKessaiKin;
	}
	/**
	 * @return クラス変数chargeKenZennenhi を戻します。
	 */
	public BigDecimal getChargeKenZennenhi() {
		return chargeKenZennenhi;
	}
	/**
	 * @param chargeKenZennenhi を クラス変数chargeKenZennenhiへ設定します。
	 */
	public void setChargeKenZennenhi(BigDecimal chargeKenZennenhi) {
		this.chargeKenZennenhi = chargeKenZennenhi;
	}
	/**
	 * @return クラス変数chargeKinZennenhi を戻します。
	 */
	public BigDecimal getChargeKinZennenhi() {
		return chargeKinZennenhi;
	}
	/**
	 * @param chargeKinZennenhi を クラス変数chargeKinZennenhiへ設定します。
	 */
	public void setChargeKinZennenhi(BigDecimal chargeKinZennenhi) {
		this.chargeKinZennenhi = chargeKinZennenhi;
	}
	/**
	 * @return クラス変数issueCntZennenhi を戻します。
	 */
	public BigDecimal getIssueCntZennenhi() {
		return issueCntZennenhi;
	}
	/**
	 * @param issueCntZennenhi を クラス変数issueCntZennenhiへ設定します。
	 */
	public void setIssueCntZennenhi(BigDecimal issueCntZennenhi) {
		this.issueCntZennenhi = issueCntZennenhi;
	}
	/**
	 * @return クラス変数kessaiKenZennenhi を戻します。
	 */
	public BigDecimal getKessaiKenZennenhi() {
		return kessaiKenZennenhi;
	}
	/**
	 * @param kessaiKenZennenhi を クラス変数kessaiKenZennenhiへ設定します。
	 */
	public void setKessaiKenZennenhi(BigDecimal kessaiKenZennenhi) {
		this.kessaiKenZennenhi = kessaiKenZennenhi;
	}
	/**
	 * @return クラス変数kessaiKinZennenhi を戻します。
	 */
	public BigDecimal getKessaiKinZennenhi() {
		return kessaiKinZennenhi;
	}
	/**
	 * @param kessaiKinZennenhi を クラス変数kessaiKinZennenhiへ設定します。
	 */
	public void setKessaiKinZennenhi(BigDecimal kessaiKinZennenhi) {
		this.kessaiKinZennenhi = kessaiKinZennenhi;
	}
	/**
	 * @return クラス変数chargeKyakusuhi を戻します。
	 */
	public BigDecimal getChargeKyakusuhi() {
		return chargeKyakusuhi;
	}
	/**
	 * @param chargeKyakusuhi を クラス変数chargeKyakusuhiへ設定します。
	 */
	public void setChargeKyakusuhi(BigDecimal chargeKyakusuhi) {
		this.chargeKyakusuhi = chargeKyakusuhi;
	}
	/**
	 * @return クラス変数chargeTankahi を戻します。
	 */
	public BigDecimal getChargeTankahi() {
		return chargeTankahi;
	}
	/**
	 * @param chargeTankahi を クラス変数chargeTankahiへ設定します。
	 */
	public void setChargeTankahi(BigDecimal chargeTankahi) {
		this.chargeTankahi = chargeTankahi;
	}
	/**
	 * @return クラス変数chargeUriagehi を戻します。
	 */
	public BigDecimal getChargeUriagehi() {
		return chargeUriagehi;
	}
	/**
	 * @param chargeUriagehi を クラス変数chargeUriagehiへ設定します。
	 */
	public void setChargeUriagehi(BigDecimal chargeUriagehi) {
		this.chargeUriagehi = chargeUriagehi;
	}
	/**
	 * @return クラス変数kessaiKyakusuhi を戻します。
	 */
	public BigDecimal getKessaiKyakusuhi() {
		return kessaiKyakusuhi;
	}
	/**
	 * @param kessaiKyakusuhi を クラス変数kessaiKyakusuhiへ設定します。
	 */
	public void setKessaiKyakusuhi(BigDecimal kessaiKyakusuhi) {
		this.kessaiKyakusuhi = kessaiKyakusuhi;
	}
	/**
	 * @return クラス変数kessaiTankahi を戻します。
	 */
	public BigDecimal getKessaiTankahi() {
		return kessaiTankahi;
	}
	/**
	 * @param kessaiTankahi を クラス変数kessaiTankahiへ設定します。
	 */
	public void setKessaiTankahi(BigDecimal kessaiTankahi) {
		this.kessaiTankahi = kessaiTankahi;
	}
	/**
	 * @return クラス変数kessaiUriagehi を戻します。
	 */
	public BigDecimal getKessaiUriagehi() {
		return kessaiUriagehi;
	}
	/**
	 * @param kessaiUriagehi を クラス変数kessaiUriagehiへ設定します。
	 */
	public void setKessaiUriagehi(BigDecimal kessaiUriagehi) {
		this.kessaiUriagehi = kessaiUriagehi;
	}
	/**
	 * @return クラス変数netChargeKenZennenhi を戻します。
	 */
	public BigDecimal getNetChargeKenZennenhi() {
		return netChargeKenZennenhi;
	}
	/**
	 * @param netChargeKenZennenhi を クラス変数netChargeKenZennenhiへ設定します。
	 */
	public void setNetChargeKenZennenhi(BigDecimal netChargeKenZennenhi) {
		this.netChargeKenZennenhi = netChargeKenZennenhi;
	}
	/**
	 * @return クラス変数netChargeKinZennenhi を戻します。
	 */
	public BigDecimal getNetChargeKinZennenhi() {
		return netChargeKinZennenhi;
	}
	/**
	 * @param netChargeKinZennenhi を クラス変数netChargeKinZennenhiへ設定します。
	 */
	public void setNetChargeKinZennenhi(BigDecimal netChargeKinZennenhi) {
		this.netChargeKinZennenhi = netChargeKinZennenhi;
	}
	/**
	 * @return クラス変数netIssueCntZennenhi を戻します。
	 */
	public BigDecimal getNetIssueCntZennenhi() {
		return netIssueCntZennenhi;
	}
	/**
	 * @param netIssueCntZennenhi を クラス変数netIssueCntZennenhiへ設定します。
	 */
	public void setNetIssueCntZennenhi(BigDecimal netIssueCntZennenhi) {
		this.netIssueCntZennenhi = netIssueCntZennenhi;
	}
	/**
	 * @return クラス変数netKessaiKenZennenhi を戻します。
	 */
	public BigDecimal getNetKessaiKenZennenhi() {
		return netKessaiKenZennenhi;
	}
	/**
	 * @param netKessaiKenZennenhi を クラス変数netKessaiKenZennenhiへ設定します。
	 */
	public void setNetKessaiKenZennenhi(BigDecimal netKessaiKenZennenhi) {
		this.netKessaiKenZennenhi = netKessaiKenZennenhi;
	}
	/**
	 * @return クラス変数netKessaiKinZennenhi を戻します。
	 */
	public BigDecimal getNetKessaiKinZennenhi() {
		return netKessaiKinZennenhi;
	}
	/**
	 * @param netKessaiKinZennenhi を クラス変数netKessaiKinZennenhiへ設定します。
	 */
	public void setNetKessaiKinZennenhi(BigDecimal netKessaiKinZennenhi) {
		this.netKessaiKinZennenhi = netKessaiKinZennenhi;
	}
	/**
	 * @return クラス変数chargeTanka を戻します。
	 */
	public BigDecimal getChargeTanka() {
		return chargeTanka;
	}
	/**
	 * @param chargeTanka を クラス変数chargeTankaへ設定します。
	 */
	public void setChargeTanka(BigDecimal chargeTanka) {
		this.chargeTanka = chargeTanka;
	}
	/**
	 * @return クラス変数chargeTankaZennenhi を戻します。
	 */
	public BigDecimal getChargeTankaZennenhi() {
		return chargeTankaZennenhi;
	}
	/**
	 * @param chargeTankaZennenhi を クラス変数chargeTankaZennenhiへ設定します。
	 */
	public void setChargeTankaZennenhi(BigDecimal chargeTankaZennenhi) {
		this.chargeTankaZennenhi = chargeTankaZennenhi;
	}
	/**
	 * @return クラス変数kessaiTanka を戻します。
	 */
	public BigDecimal getKessaiTanka() {
		return kessaiTanka;
	}
	/**
	 * @param kessaiTanka を クラス変数kessaiTankaへ設定します。
	 */
	public void setKessaiTanka(BigDecimal kessaiTanka) {
		this.kessaiTanka = kessaiTanka;
	}
	/**
	 * @return クラス変数kessaiTankaZennenhi を戻します。
	 */
	public BigDecimal getKessaiTankaZennenhi() {
		return kessaiTankaZennenhi;
	}
	/**
	 * @param kessaiTankaZennenhi を クラス変数kessaiTankaZennenhiへ設定します。
	 */
	public void setKessaiTankaZennenhi(BigDecimal kessaiTankaZennenhi) {
		this.kessaiTankaZennenhi = kessaiTankaZennenhi;
	}
	/**
	 * @return クラス変数netChargeTanka を戻します。
	 */
	public BigDecimal getNetChargeTanka() {
		return netChargeTanka;
	}
	/**
	 * @param netChargeTanka を クラス変数netChargeTankaへ設定します。
	 */
	public void setNetChargeTanka(BigDecimal netChargeTanka) {
		this.netChargeTanka = netChargeTanka;
	}
	/**
	 * @return クラス変数netChargeTankaZennenhi を戻します。
	 */
	public BigDecimal getNetChargeTankaZennenhi() {
		return netChargeTankaZennenhi;
	}
	/**
	 * @param netChargeTankaZennenhi を クラス変数netChargeTankaZennenhiへ設定します。
	 */
	public void setNetChargeTankaZennenhi(BigDecimal netChargeTankaZennenhi) {
		this.netChargeTankaZennenhi = netChargeTankaZennenhi;
	}
	/**
	 * @return クラス変数netKessaiTanka を戻します。
	 */
	public BigDecimal getNetKessaiTanka() {
		return netKessaiTanka;
	}
	/**
	 * @param netKessaiTanka を クラス変数netKessaiTankaへ設定します。
	 */
	public void setNetKessaiTanka(BigDecimal netKessaiTanka) {
		this.netKessaiTanka = netKessaiTanka;
	}
	/**
	 * @return クラス変数netKessaiTankaZennenhi を戻します。
	 */
	public BigDecimal getNetKessaiTankaZennenhi() {
		return netKessaiTankaZennenhi;
	}
	/**
	 * @param netKessaiTankaZennenhi を クラス変数netKessaiTankaZennenhiへ設定します。
	 */
	public void setNetKessaiTankaZennenhi(BigDecimal netKessaiTankaZennenhi) {
		this.netKessaiTankaZennenhi = netKessaiTankaZennenhi;
	}
	/**
	 * @return クラス変数netZenChargeTanka を戻します。
	 */
	public BigDecimal getNetZenChargeTanka() {
		return netZenChargeTanka;
	}
	/**
	 * @param netZenChargeTanka を クラス変数netZenChargeTankaへ設定します。
	 */
	public void setNetZenChargeTanka(BigDecimal netZenChargeTanka) {
		this.netZenChargeTanka = netZenChargeTanka;
	}
	/**
	 * @return クラス変数netZenKessaiTanka を戻します。
	 */
	public BigDecimal getNetZenKessaiTanka() {
		return netZenKessaiTanka;
	}
	/**
	 * @param netZenKessaiTanka を クラス変数netZenKessaiTankaへ設定します。
	 */
	public void setNetZenKessaiTanka(BigDecimal netZenKessaiTanka) {
		this.netZenKessaiTanka = netZenKessaiTanka;
	}
	/**
	 * @return クラス変数zenChargeTanka を戻します。
	 */
	public BigDecimal getZenChargeTanka() {
		return zenChargeTanka;
	}
	/**
	 * @param zenChargeTanka を クラス変数zenChargeTankaへ設定します。
	 */
	public void setZenChargeTanka(BigDecimal zenChargeTanka) {
		this.zenChargeTanka = zenChargeTanka;
	}
	/**
	 * @return クラス変数zenKessaiTanka を戻します。
	 */
	public BigDecimal getZenKessaiTanka() {
		return zenKessaiTanka;
	}
	/**
	 * @param zenKessaiTanka を クラス変数zenKessaiTankaへ設定します。
	 */
	public void setZenKessaiTanka(BigDecimal zenKessaiTanka) {
		this.zenKessaiTanka = zenKessaiTanka;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity#getTenkoKbnKj()
	 */
	public String getTenkoKbnKj() {
		// TODO 自動生成されたメソッド・スタブ
		return NipoRefUtil.getTenkoKbnLabel(super.getTenkoKbn());
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity#getTenkoKbnZenKj()
	 */
	public String getTenkoKbnZenKj() {
		// TODO 自動生成されたメソッド・スタブ
		return NipoRefUtil.getTenkoKbnLabel(super.getTenkoKbnZen());
	}
}
