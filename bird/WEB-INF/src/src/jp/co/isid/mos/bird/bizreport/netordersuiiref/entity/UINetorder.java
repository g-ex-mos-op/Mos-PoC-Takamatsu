/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.netordersuiiref.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.util.NipoRefUtil;

/**
 * Entity[ネット注文売上推移表]
 * 
 * 作成日:
 * @author
 *
 */
public class UINetorder extends SuiiRefUIEntity {
	
    public static final String eigyoDaysNsum_COLUMN        = "EIGYO_DAYS_NSUM";
    public static final String miseCntKbnNsum_COLUMN       = "MISECNT_KBN_NSUM";
    public static final String uriageNsum_COLUMN           = "URIAGE_NSUM";
    public static final String uriageNsumZen_COLUMN 	   = "URIAGE_NSUM_ZEN";
    public static final String kyakusuNsum_COLUMN          = "KYAKUSU_NSUM";
    public static final String kyakusuNsumZen_COLUMN       = "KYAKUSU_NSUM_ZEN";
    public static final String eigyoDaysNtake_COLUMN       = "EIGYO_DAYS_NTAKE";
    public static final String miseCntKbnNtake_COLUMN      = "MISECNT_KBN_NTAKE";
    public static final String uriageNtake_COLUMN      	   = "URIAGE_NTAKE";
    public static final String uriageNtakeZen_COLUMN       = "URIAGE_NTAKE_ZEN";
    public static final String kyakusuNtake_COLUMN         = "KYAKUSU_NTAKE";
    public static final String kyakusuNtakeZen_COLUMN      = "KYAKUSU_NTAKE_ZEN";
    public static final String eigyoDaysNtaku_COLUMN       = "EIGYO_DAYS_NTAKU";;
    public static final String miseCntKbnNtaku_COLUMN      = "MISECNT_KBN_NTAKU";
    public static final String uriageNtaku_COLUMN          = "URIAGE_NTAKU";
    public static final String uriageNtakuZen_COLUMN       = "URIAGE_NTAKU_ZEN";
    public static final String kyakusuNtaku_COLUMN         = "KYAKUSU_NTAKU";
    public static final String kyakusuNtakuZen_COLUMN      = "KYAKUSU_NTAKU_ZEN";
    
    public static final String netUriageNsum_COLUMN        = "NET_URIAGE_NSUM";
    public static final String netUriageNsumZen_COLUMN 	   = "NET_URIAGE_NSUM_ZEN";
    public static final String netKyakusuNsum_COLUMN       = "NET_KYAKUSU_NSUM";
    public static final String netKyakusuNsumZen_COLUMN    = "NET_KYAKUSU_NSUM_ZEN";
    public static final String netUriageNtake_COLUMN       = "NET_URIAGE_NTAKE";
    public static final String netUriageNtakeZen_COLUMN    = "NET_URIAGE_NTAKE_ZEN";
    public static final String netKyakusuNtake_COLUMN      = "NET_KYAKUSU_NTAKE";
    public static final String netKyakusuNtakeZen_COLUMN   = "NET_KYAKUSU_NTAKE_ZEN";
    public static final String netUriageNtaku_COLUMN       = "NET_URIAGE_NTAKU";
    public static final String netUriageNtakuZen_COLUMN    = "NET_URIAGE_NTAKU_ZEN";
    public static final String netKyakusuNtaku_COLUMN      = "NET_KYAKUSU_NTAKU";
    public static final String netKyakusuNtakuZen_COLUMN   = "NET_KYAKUSU_NTAKU_ZEN";
    
    /** ネット注文日数 */
    private BigDecimal eigyoDaysNsum         = new BigDecimal(0);
    
    /** ネット注文実績店舗数 */
    private BigDecimal miseCntKbnNsum        = new BigDecimal(0);
    
    /** ネット注文売上 */
    private BigDecimal uriageNsum        	 = new BigDecimal(0);
    
    /** 前年ネット注文実績 */
    private BigDecimal uriageNsumZen         = new BigDecimal(0);
    
    /** ネット注文件数 */
    private BigDecimal kyakusuNsum           = new BigDecimal(0);
    
    /** 前年ネット注文件数 */
    private BigDecimal kyakusuNsumZen        = new BigDecimal(0);
    
    /** ネットテイク日数 */
    private BigDecimal eigyoDaysNtake        = new BigDecimal(0);
    
    /** ネットテイク実績店舗数 */
    private BigDecimal miseCntKbnNtake       = new BigDecimal(0);
    
    /** ネットテイク売上 */
    private BigDecimal uriageNtake        	 = new BigDecimal(0);
    
    /** 前年ネットテイク実績 */
    private BigDecimal uriageNtakeZen        = new BigDecimal(0);
    
    /** ネットテイク件数 */
    private BigDecimal kyakusuNtake          = new BigDecimal(0);
    
    /** 前年ネットテイク件数 */
    private BigDecimal kyakusuNtakeZen       = new BigDecimal(0);
    
    /** ネット宅配日数 */
    private BigDecimal eigyoDaysNtaku        = new BigDecimal(0);
    
    /** ネット宅配実績店舗数 */
    private BigDecimal miseCntKbnNtaku       = new BigDecimal(0);
    
    /** ネット宅配売上 */
    private BigDecimal uriageNtaku        	 = new BigDecimal(0);
    
    /** 前年ネット宅配実績 */
    private BigDecimal uriageNtakuZen        = new BigDecimal(0);
    
    /** ネット宅配件数 */
    private BigDecimal kyakusuNtaku          = new BigDecimal(0);
    
    /** 前年ネット宅配件数 */
    private BigDecimal kyakusuNtakuZen       = new BigDecimal(0);
    
    /** 前年比対象ネット注文売上 */
    private BigDecimal netUriageNsum         = new BigDecimal(0);
    
    /** 前年比対象前年ネット注文実績 */
    private BigDecimal netUriageNsumZen      = new BigDecimal(0);
    
    /** 前年比対象ネット注文件数 */
    private BigDecimal netKyakusuNsum        = new BigDecimal(0);
    
    /** 前年比対象前年ネット注文件数 */
    private BigDecimal netKyakusuNsumZen     = new BigDecimal(0);
    
    /** 前年比対象ネットテイク売上 */
    private BigDecimal netUriageNtake        = new BigDecimal(0);
    
    /** 前年比対象前年ネットテイク実績 */
    private BigDecimal netUriageNtakeZen     = new BigDecimal(0);
    
    /** 前年比対象ネットテイク件数 */
    private BigDecimal netKyakusuNtake       = new BigDecimal(0);
    
    /** 前年比対象前年ネットテイク件数 */
    private BigDecimal netKyakusuNtakeZen    = new BigDecimal(0);
    
    /** 前年比対象ネット宅配売上 */
    private BigDecimal netUriageNtaku        = new BigDecimal(0);
    
    /** 前年比対象前年ネット宅配実績 */
    private BigDecimal netUriageNtakuZen     = new BigDecimal(0);
    
    /** 前年比対象ネット宅配件数 */
    private BigDecimal netKyakusuNtaku       = new BigDecimal(0);
    
    /** 前年比対象前年ネット宅配件数 */
    private BigDecimal netKyakusuNtakuZen    = new BigDecimal(0);
    
    //計算項目
    /** 達成率 */
    private BigDecimal tasseiRitu              = new BigDecimal(0);
    
    /** 前年比(ネット注文売上) */
    private BigDecimal nsumUriageZenhi     = new BigDecimal(0.00);
    
    /** 構成比(ネット注文売上) */
    private BigDecimal nsumUriageKouseihi  = new BigDecimal(0.00);
    
    /** ネット注文売上平均 */
    private BigDecimal nsumUriageAvg       = new BigDecimal(0);
    
    /** 前年比(ネット注文件数) */
    private BigDecimal nsumKyakusuZenhi     = new BigDecimal(0);
    
    /** 構成比(ネット注文件数) */
    private BigDecimal nsumKyakusuKouseihi  = new BigDecimal(0);
    
    /** ネット注文件数平均 */
    private BigDecimal nsumKyakusuAvg       = new BigDecimal(0);
    
    /** 客単価(ネット注文) */
    private BigDecimal nsumKyakuTanka      = new BigDecimal(0);
    
    /** 前年客単価(ネット注文) */
    private BigDecimal nsumKyakuTankaZen   = new BigDecimal(0);
    
    /** 前年比(客単価ネット注文) */
    private BigDecimal nsumKyakuTankaZenhi = new BigDecimal(0.00);
    
    /** 前年比(ネットテイク売上) */
    private BigDecimal ntakeUriageZenhi     = new BigDecimal(0.00);
    
    /** 構成比(ネットテイク売上) */
    private BigDecimal ntakeUriageKouseihi  = new BigDecimal(0.00);
    
    /** ネットテイク売上平均 */
    private BigDecimal ntakeUriageAvg       = new BigDecimal(0);
    
    /** 前年比(ネットテイク件数) */
    private BigDecimal ntakeKyakusuZenhi     = new BigDecimal(0);
    
    /** 構成比(ネットテイク件数) */
    private BigDecimal ntakeKyakusuKouseihi  = new BigDecimal(0);
    
    /** ネットテイク件数平均 */
    private BigDecimal ntakeKyakusuAvg       = new BigDecimal(0);
    
    /** 客単価(ネットテイク) */
    private BigDecimal ntakeKyakuTanka      = new BigDecimal(0);
    
    /** 前年客単価(ネットテイク) */
    private BigDecimal ntakeKyakuTankaZen   = new BigDecimal(0);
    
    /** 前年比(客単価ネットテイク) */
    private BigDecimal ntakeKyakuTankaZenhi = new BigDecimal(0.00);
    
    /** 前年比(ネット宅配売上) */
    private BigDecimal ntakuUriageZenhi     = new BigDecimal(0);
    
    /** 構成比(ネット宅配売上) */
    private BigDecimal ntakuUriageKouseihi  = new BigDecimal(0);
    
    /** ネット宅配売上平均 */
    private BigDecimal ntakuUriageAvg       = new BigDecimal(0);
    
    /** 前年比(ネット宅配件数) */
    private BigDecimal ntakuKyakusuZenhi     = new BigDecimal(0.00);
    
    /** 構成比(ネット宅配件数) */
    private BigDecimal ntakuKyakusuKouseihi  = new BigDecimal(0.00);
    
    /** ネット宅配件数平均 */
    private BigDecimal ntakuKyakusuAvg       = new BigDecimal(0);
    
    /** 客単価(ネット宅配) */
    private BigDecimal ntakuKyakuTanka      = new BigDecimal(0);
    
    /** 前年客単価(ネット宅配) */
    private BigDecimal ntakuKyakuTankaZen   = new BigDecimal(0);
    
    /** 前年比(客単価ネット宅配) */
    private BigDecimal ntakuKyakuTankaZenhi = new BigDecimal(0.00);
    
    
    
    /** NET前年比(ネット注文売上) */
    private BigDecimal netNsumUriageZenhi     = new BigDecimal(0.00);
    
    /** NET前年比(ネット注文件数) */
    private BigDecimal netNsumKyakusuZenhi     = new BigDecimal(0.00);
    
    /** NET前年比対象客単価(ネット注文) */
    private BigDecimal netNsumKyakuTanka      = new BigDecimal(0);
    
    /** NET前年比対象前年客単価(ネット注文) */
    private BigDecimal netNsumKyakuTankaZen   = new BigDecimal(0);
    
    /** NET前年比(客単価ネット注文) */
    private BigDecimal netNsumKyakuTankaZenhi = new BigDecimal(0.00);
    
    /** NET前年比(ネットテイク売上) */
    private BigDecimal netNtakeUriageZenhi     = new BigDecimal(0.00);
    
    /** NET前年比(ネットテイク件数) */
    private BigDecimal netNtakeKyakusuZenhi     = new BigDecimal(0.00);
    
    /** NET前年比対象客単価(ネットテイク) */
    private BigDecimal netNtakeKyakuTanka      = new BigDecimal(0);
    
    /** NET前年比対象前年客単価(ネットテイク) */
    private BigDecimal netNtakeKyakuTankaZen   = new BigDecimal(0);
    
    /** NET前年比(客単価ネットテイク) */
    private BigDecimal netNtakeKyakuTankaZenhi = new BigDecimal(0.00);
    
    /** NET前年比(ネット宅配売上) */
    private BigDecimal netNtakuUriageZenhi     = new BigDecimal(0.00);
    
    /** NET前年比(ネット宅配件数) */
    private BigDecimal netNtakuKyakusuZenhi     = new BigDecimal(0.00);
    
    /** NET前年比対象客単価(ネット宅配) */
    private BigDecimal netNtakuKyakuTanka      = new BigDecimal(0);
    
    /** NET前年比対象前年客単価(ネット宅配) */
    private BigDecimal netNtakuKyakuTankaZen   = new BigDecimal(0);
    
    /** NET前年比(客単価ネット宅配) */
    private BigDecimal netNtakuKyakuTankaZenhi = new BigDecimal(0.00);
    
    /** 日次ネット注文、ネットテイク、ネット宅配実績店舗数合計*/
    private BigDecimal miseCntNsumTotal        = new BigDecimal(0);
    
    private BigDecimal miseCntNtakeTotal        = new BigDecimal(0);
    
    private BigDecimal miseCntNtakuTotal        = new BigDecimal(0);
    
	/**
	 * @return eigyoDaysNsum を戻します。
	 */
	public BigDecimal getEigyoDaysNsum() {
		return eigyoDaysNsum;
	}

	/**
	 * @param eigyoDaysNsum 設定する eigyoDaysNsum。
	 */
	public void setEigyoDaysNsum(BigDecimal eigyoDaysNsum) {
		this.eigyoDaysNsum = eigyoDaysNsum;
	}

	/**
	 * @return eigyoDaysNtake を戻します。
	 */
	public BigDecimal getEigyoDaysNtake() {
		return eigyoDaysNtake;
	}

	/**
	 * @param eigyoDaysNtake 設定する eigyoDaysNtake。
	 */
	public void setEigyoDaysNtake(BigDecimal eigyoDaysNtake) {
		this.eigyoDaysNtake = eigyoDaysNtake;
	}

	/**
	 * @return eigyoDaysNtaku を戻します。
	 */
	public BigDecimal getEigyoDaysNtaku() {
		return eigyoDaysNtaku;
	}

	/**
	 * @param eigyoDaysNtaku 設定する eigyoDaysNtaku。
	 */
	public void setEigyoDaysNtaku(BigDecimal eigyoDaysNtaku) {
		this.eigyoDaysNtaku = eigyoDaysNtaku;
	}

	/**
	 * @return kyakusuNsum を戻します。
	 */
	public BigDecimal getKyakusuNsum() {
		return kyakusuNsum;
	}

	/**
	 * @param kyakusuNsum 設定する kyakusuNsum。
	 */
	public void setKyakusuNsum(BigDecimal kyakusuNsum) {
		this.kyakusuNsum = kyakusuNsum;
	}

	/**
	 * @return kyakusuNsumZen を戻します。
	 */
	public BigDecimal getKyakusuNsumZen() {
		return kyakusuNsumZen;
	}

	/**
	 * @param kyakusuNsumZen 設定する kyakusuNsumZen。
	 */
	public void setKyakusuNsumZen(BigDecimal kyakusuNsumZen) {
		this.kyakusuNsumZen = kyakusuNsumZen;
	}

	/**
	 * @return kyakusuNtake を戻します。
	 */
	public BigDecimal getKyakusuNtake() {
		return kyakusuNtake;
	}

	/**
	 * @param kyakusuNtake 設定する kyakusuNtake。
	 */
	public void setKyakusuNtake(BigDecimal kyakusuNtake) {
		this.kyakusuNtake = kyakusuNtake;
	}

	/**
	 * @return kyakusuNtakeZen を戻します。
	 */
	public BigDecimal getKyakusuNtakeZen() {
		return kyakusuNtakeZen;
	}

	/**
	 * @param kyakusuNtakeZen 設定する kyakusuNtakeZen。
	 */
	public void setKyakusuNtakeZen(BigDecimal kyakusuNtakeZen) {
		this.kyakusuNtakeZen = kyakusuNtakeZen;
	}

	/**
	 * @return kyakusuNtaku を戻します。
	 */
	public BigDecimal getKyakusuNtaku() {
		return kyakusuNtaku;
	}

	/**
	 * @param kyakusuNtaku 設定する kyakusuNtaku。
	 */
	public void setKyakusuNtaku(BigDecimal kyakusuNtaku) {
		this.kyakusuNtaku = kyakusuNtaku;
	}

	/**
	 * @return kyakusuNtakuZen を戻します。
	 */
	public BigDecimal getKyakusuNtakuZen() {
		return kyakusuNtakuZen;
	}

	/**
	 * @param kyakusuNtakuZen 設定する kyakusuNtakuZen。
	 */
	public void setKyakusuNtakuZen(BigDecimal kyakusuNtakuZen) {
		this.kyakusuNtakuZen = kyakusuNtakuZen;
	}

	/**
	 * @return netKyakusuNsum を戻します。
	 */
	public BigDecimal getNetKyakusuNsum() {
		return netKyakusuNsum;
	}

	/**
	 * @param netKyakusuNsum 設定する netKyakusuNsum。
	 */
	public void setNetKyakusuNsum(BigDecimal netKyakusuNsum) {
		this.netKyakusuNsum = netKyakusuNsum;
	}

	/**
	 * @return netKyakusuNsumZen を戻します。
	 */
	public BigDecimal getNetKyakusuNsumZen() {
		return netKyakusuNsumZen;
	}

	/**
	 * @param netKyakusuNsumZen 設定する netKyakusuNsumZen。
	 */
	public void setNetKyakusuNsumZen(BigDecimal netKyakusuNsumZen) {
		this.netKyakusuNsumZen = netKyakusuNsumZen;
	}

	/**
	 * @return netKyakusuNtake を戻します。
	 */
	public BigDecimal getNetKyakusuNtake() {
		return netKyakusuNtake;
	}

	/**
	 * @param netKyakusuNtake 設定する netKyakusuNtake。
	 */
	public void setNetKyakusuNtake(BigDecimal netKyakusuNtake) {
		this.netKyakusuNtake = netKyakusuNtake;
	}

	/**
	 * @return netKyakusuNtakeZen を戻します。
	 */
	public BigDecimal getNetKyakusuNtakeZen() {
		return netKyakusuNtakeZen;
	}

	/**
	 * @param netKyakusuNtakeZen 設定する netKyakusuNtakeZen。
	 */
	public void setNetKyakusuNtakeZen(BigDecimal netKyakusuNtakeZen) {
		this.netKyakusuNtakeZen = netKyakusuNtakeZen;
	}

	/**
	 * @return netKyakusuNtaku を戻します。
	 */
	public BigDecimal getNetKyakusuNtaku() {
		return netKyakusuNtaku;
	}

	/**
	 * @param netKyakusuNtaku 設定する netKyakusuNtaku。
	 */
	public void setNetKyakusuNtaku(BigDecimal netKyakusuNtaku) {
		this.netKyakusuNtaku = netKyakusuNtaku;
	}

	/**
	 * @return netKyakusuNtakuZen を戻します。
	 */
	public BigDecimal getNetKyakusuNtakuZen() {
		return netKyakusuNtakuZen;
	}

	/**
	 * @param netKyakusuNtakuZen 設定する netKyakusuNtakuZen。
	 */
	public void setNetKyakusuNtakuZen(BigDecimal netKyakusuNtakuZen) {
		this.netKyakusuNtakuZen = netKyakusuNtakuZen;
	}

	/**
	 * @return netNsumKyakusuZenhi を戻します。
	 */
	public BigDecimal getNetNsumKyakusuZenhi() {
		return netNsumKyakusuZenhi;
	}

	/**
	 * @param netNsumKyakusuZenhi 設定する netNsumKyakusuZenhi。
	 */
	public void setNetNsumKyakusuZenhi(BigDecimal netNsumKyakusuZenhi) {
		this.netNsumKyakusuZenhi = netNsumKyakusuZenhi;
	}

	/**
	 * @return netNsumKyakuTanka を戻します。
	 */
	public BigDecimal getNetNsumKyakuTanka() {
		return netNsumKyakuTanka;
	}

	/**
	 * @param netNsumKyakuTanka 設定する netNsumKyakuTanka。
	 */
	public void setNetNsumKyakuTanka(BigDecimal netNsumKyakuTanka) {
		this.netNsumKyakuTanka = netNsumKyakuTanka;
	}

	/**
	 * @return netNsumKyakuTankaZen を戻します。
	 */
	public BigDecimal getNetNsumKyakuTankaZen() {
		return netNsumKyakuTankaZen;
	}

	/**
	 * @param netNsumKyakuTankaZen 設定する netNsumKyakuTankaZen。
	 */
	public void setNetNsumKyakuTankaZen(BigDecimal netNsumKyakuTankaZen) {
		this.netNsumKyakuTankaZen = netNsumKyakuTankaZen;
	}

	/**
	 * @return netNsumKyakuTankaZenhi を戻します。
	 */
	public BigDecimal getNetNsumKyakuTankaZenhi() {
		return netNsumKyakuTankaZenhi;
	}

	/**
	 * @param netNsumKyakuTankaZenhi 設定する netNsumKyakuTankaZenhi。
	 */
	public void setNetNsumKyakuTankaZenhi(BigDecimal netNsumKyakuTankaZenhi) {
		this.netNsumKyakuTankaZenhi = netNsumKyakuTankaZenhi;
	}

	/**
	 * @return netNsumUriageZenhi を戻します。
	 */
	public BigDecimal getNetNsumUriageZenhi() {
		return netNsumUriageZenhi;
	}

	/**
	 * @param netNsumUriageZenhi 設定する netNsumUriageZenhi。
	 */
	public void setNetNsumUriageZenhi(BigDecimal netNsumUriageZenhi) {
		this.netNsumUriageZenhi = netNsumUriageZenhi;
	}

	/**
	 * @return netNtakeKyakusuZenhi を戻します。
	 */
	public BigDecimal getNetNtakeKyakusuZenhi() {
		return netNtakeKyakusuZenhi;
	}

	/**
	 * @param netNtakeKyakusuZenhi 設定する netNtakeKyakusuZenhi。
	 */
	public void setNetNtakeKyakusuZenhi(BigDecimal netNtakeKyakusuZenhi) {
		this.netNtakeKyakusuZenhi = netNtakeKyakusuZenhi;
	}

	/**
	 * @return netNtakeKyakuTanka を戻します。
	 */
	public BigDecimal getNetNtakeKyakuTanka() {
		return netNtakeKyakuTanka;
	}

	/**
	 * @param netNtakeKyakuTanka 設定する netNtakeKyakuTanka。
	 */
	public void setNetNtakeKyakuTanka(BigDecimal netNtakeKyakuTanka) {
		this.netNtakeKyakuTanka = netNtakeKyakuTanka;
	}

	/**
	 * @return netNtakeKyakuTankaZen を戻します。
	 */
	public BigDecimal getNetNtakeKyakuTankaZen() {
		return netNtakeKyakuTankaZen;
	}

	/**
	 * @param netNtakeKyakuTankaZen 設定する netNtakeKyakuTankaZen。
	 */
	public void setNetNtakeKyakuTankaZen(BigDecimal netNtakeKyakuTankaZen) {
		this.netNtakeKyakuTankaZen = netNtakeKyakuTankaZen;
	}

	/**
	 * @return netNtakeKyakuTankaZenhi を戻します。
	 */
	public BigDecimal getNetNtakeKyakuTankaZenhi() {
		return netNtakeKyakuTankaZenhi;
	}

	/**
	 * @param netNtakeKyakuTankaZenhi 設定する netNtakeKyakuTankaZenhi。
	 */
	public void setNetNtakeKyakuTankaZenhi(BigDecimal netNtakeKyakuTankaZenhi) {
		this.netNtakeKyakuTankaZenhi = netNtakeKyakuTankaZenhi;
	}

	/**
	 * @return netNtakeUriageZenhi を戻します。
	 */
	public BigDecimal getNetNtakeUriageZenhi() {
		return netNtakeUriageZenhi;
	}

	/**
	 * @param netNtakeUriageZenhi 設定する netNtakeUriageZenhi。
	 */
	public void setNetNtakeUriageZenhi(BigDecimal netNtakeUriageZenhi) {
		this.netNtakeUriageZenhi = netNtakeUriageZenhi;
	}

	/**
	 * @return netNtakuKyakusuZenhi を戻します。
	 */
	public BigDecimal getNetNtakuKyakusuZenhi() {
		return netNtakuKyakusuZenhi;
	}

	/**
	 * @param netNtakuKyakusuZenhi 設定する netNtakuKyakusuZenhi。
	 */
	public void setNetNtakuKyakusuZenhi(BigDecimal netNtakuKyakusuZenhi) {
		this.netNtakuKyakusuZenhi = netNtakuKyakusuZenhi;
	}

	/**
	 * @return netNtakuKyakuTanka を戻します。
	 */
	public BigDecimal getNetNtakuKyakuTanka() {
		return netNtakuKyakuTanka;
	}

	/**
	 * @param netNtakuKyakuTanka 設定する netNtakuKyakuTanka。
	 */
	public void setNetNtakuKyakuTanka(BigDecimal netNtakuKyakuTanka) {
		this.netNtakuKyakuTanka = netNtakuKyakuTanka;
	}

	/**
	 * @return netNtakuKyakuTankaZen を戻します。
	 */
	public BigDecimal getNetNtakuKyakuTankaZen() {
		return netNtakuKyakuTankaZen;
	}

	/**
	 * @param netNtakuKyakuTankaZen 設定する netNtakuKyakuTankaZen。
	 */
	public void setNetNtakuKyakuTankaZen(BigDecimal netNtakuKyakuTankaZen) {
		this.netNtakuKyakuTankaZen = netNtakuKyakuTankaZen;
	}

	/**
	 * @return netNtakuKyakuTankaZenhi を戻します。
	 */
	public BigDecimal getNetNtakuKyakuTankaZenhi() {
		return netNtakuKyakuTankaZenhi;
	}

	/**
	 * @param netNtakuKyakuTankaZenhi 設定する netNtakuKyakuTankaZenhi。
	 */
	public void setNetNtakuKyakuTankaZenhi(BigDecimal netNtakuKyakuTankaZenhi) {
		this.netNtakuKyakuTankaZenhi = netNtakuKyakuTankaZenhi;
	}

	/**
	 * @return netNtakuUriageZenhi を戻します。
	 */
	public BigDecimal getNetNtakuUriageZenhi() {
		return netNtakuUriageZenhi;
	}

	/**
	 * @param netNtakuUriageZenhi 設定する netNtakuUriageZenhi。
	 */
	public void setNetNtakuUriageZenhi(BigDecimal netNtakuUriageZenhi) {
		this.netNtakuUriageZenhi = netNtakuUriageZenhi;
	}

	/**
	 * @return netUriageNsum を戻します。
	 */
	public BigDecimal getNetUriageNsum() {
		return netUriageNsum;
	}

	/**
	 * @param netUriageNsum 設定する netUriageNsum。
	 */
	public void setNetUriageNsum(BigDecimal netUriageNsum) {
		this.netUriageNsum = netUriageNsum;
	}

	/**
	 * @return netUriageNsumZen を戻します。
	 */
	public BigDecimal getNetUriageNsumZen() {
		return netUriageNsumZen;
	}

	/**
	 * @param netUriageNsumZen 設定する netUriageNsumZen。
	 */
	public void setNetUriageNsumZen(BigDecimal netUriageNsumZen) {
		this.netUriageNsumZen = netUriageNsumZen;
	}

	/**
	 * @return netUriageNtake を戻します。
	 */
	public BigDecimal getNetUriageNtake() {
		return netUriageNtake;
	}

	/**
	 * @param netUriageNtake 設定する netUriageNtake。
	 */
	public void setNetUriageNtake(BigDecimal netUriageNtake) {
		this.netUriageNtake = netUriageNtake;
	}

	/**
	 * @return netUriageNtakeZen を戻します。
	 */
	public BigDecimal getNetUriageNtakeZen() {
		return netUriageNtakeZen;
	}

	/**
	 * @param netUriageNtakeZen 設定する netUriageNtakeZen。
	 */
	public void setNetUriageNtakeZen(BigDecimal netUriageNtakeZen) {
		this.netUriageNtakeZen = netUriageNtakeZen;
	}

	/**
	 * @return netUriageNtaku を戻します。
	 */
	public BigDecimal getNetUriageNtaku() {
		return netUriageNtaku;
	}

	/**
	 * @param netUriageNtaku 設定する netUriageNtaku。
	 */
	public void setNetUriageNtaku(BigDecimal netUriageNtaku) {
		this.netUriageNtaku = netUriageNtaku;
	}

	/**
	 * @return netUriageNtakuZen を戻します。
	 */
	public BigDecimal getNetUriageNtakuZen() {
		return netUriageNtakuZen;
	}

	/**
	 * @param netUriageNtakuZen 設定する netUriageNtakuZen。
	 */
	public void setNetUriageNtakuZen(BigDecimal netUriageNtakuZen) {
		this.netUriageNtakuZen = netUriageNtakuZen;
	}

	/**
	 * @return nsumKyakusuAvg を戻します。
	 */
	public BigDecimal getNsumKyakusuAvg() {
		return nsumKyakusuAvg;
	}

	/**
	 * @param nsumKyakusuAvg 設定する nsumKyakusuAvg。
	 */
	public void setNsumKyakusuAvg(BigDecimal nsumKyakusuAvg) {
		this.nsumKyakusuAvg = nsumKyakusuAvg;
	}

	/**
	 * @return nsumKyakusuKouseihi を戻します。
	 */
	public BigDecimal getNsumKyakusuKouseihi() {
		return nsumKyakusuKouseihi;
	}

	/**
	 * @param nsumKyakusuKouseihi 設定する nsumKyakusuKouseihi。
	 */
	public void setNsumKyakusuKouseihi(BigDecimal nsumKyakusuKouseihi) {
		this.nsumKyakusuKouseihi = nsumKyakusuKouseihi;
	}

	/**
	 * @return nsumKyakusuZenhi を戻します。
	 */
	public BigDecimal getNsumKyakusuZenhi() {
		return nsumKyakusuZenhi;
	}

	/**
	 * @param nsumKyakusuZenhi 設定する nsumKyakusuZenhi。
	 */
	public void setNsumKyakusuZenhi(BigDecimal nsumKyakusuZenhi) {
		this.nsumKyakusuZenhi = nsumKyakusuZenhi;
	}

	/**
	 * @return nsumKyakuTanka を戻します。
	 */
	public BigDecimal getNsumKyakuTanka() {
		return nsumKyakuTanka;
	}

	/**
	 * @param nsumKyakuTanka 設定する nsumKyakuTanka。
	 */
	public void setNsumKyakuTanka(BigDecimal nsumKyakuTanka) {
		this.nsumKyakuTanka = nsumKyakuTanka;
	}

	/**
	 * @return nsumKyakuTankaZen を戻します。
	 */
	public BigDecimal getNsumKyakuTankaZen() {
		return nsumKyakuTankaZen;
	}

	/**
	 * @param nsumKyakuTankaZen 設定する nsumKyakuTankaZen。
	 */
	public void setNsumKyakuTankaZen(BigDecimal nsumKyakuTankaZen) {
		this.nsumKyakuTankaZen = nsumKyakuTankaZen;
	}

	/**
	 * @return nsumKyakuTankaZenhi を戻します。
	 */
	public BigDecimal getNsumKyakuTankaZenhi() {
		return nsumKyakuTankaZenhi;
	}

	/**
	 * @param nsumKyakuTankaZenhi 設定する nsumKyakuTankaZenhi。
	 */
	public void setNsumKyakuTankaZenhi(BigDecimal nsumKyakuTankaZenhi) {
		this.nsumKyakuTankaZenhi = nsumKyakuTankaZenhi;
	}

	/**
	 * @return nsumUriageAvg を戻します。
	 */
	public BigDecimal getNsumUriageAvg() {
		return nsumUriageAvg;
	}

	/**
	 * @param nsumUriageAvg 設定する nsumUriageAvg。
	 */
	public void setNsumUriageAvg(BigDecimal nsumUriageAvg) {
		this.nsumUriageAvg = nsumUriageAvg;
	}

	/**
	 * @return nsumUriageKouseihi を戻します。
	 */
	public BigDecimal getNsumUriageKouseihi() {
		return nsumUriageKouseihi;
	}

	/**
	 * @param nsumUriageKouseihi 設定する nsumUriageKouseihi。
	 */
	public void setNsumUriageKouseihi(BigDecimal nsumUriageKouseihi) {
		this.nsumUriageKouseihi = nsumUriageKouseihi;
	}

	/**
	 * @return nsumUriageZenhi を戻します。
	 */
	public BigDecimal getNsumUriageZenhi() {
		return nsumUriageZenhi;
	}

	/**
	 * @param nsumUriageZenhi 設定する nsumUriageZenhi。
	 */
	public void setNsumUriageZenhi(BigDecimal nsumUriageZenhi) {
		this.nsumUriageZenhi = nsumUriageZenhi;
	}

	/**
	 * @return ntakeKyakusuAvg を戻します。
	 */
	public BigDecimal getNtakeKyakusuAvg() {
		return ntakeKyakusuAvg;
	}

	/**
	 * @param ntakeKyakusuAvg 設定する ntakeKyakusuAvg。
	 */
	public void setNtakeKyakusuAvg(BigDecimal ntakeKyakusuAvg) {
		this.ntakeKyakusuAvg = ntakeKyakusuAvg;
	}

	/**
	 * @return ntakeKyakusuKouseihi を戻します。
	 */
	public BigDecimal getNtakeKyakusuKouseihi() {
		return ntakeKyakusuKouseihi;
	}

	/**
	 * @param ntakeKyakusuKouseihi 設定する ntakeKyakusuKouseihi。
	 */
	public void setNtakeKyakusuKouseihi(BigDecimal ntakeKyakusuKouseihi) {
		this.ntakeKyakusuKouseihi = ntakeKyakusuKouseihi;
	}

	/**
	 * @return ntakeKyakusuZenhi を戻します。
	 */
	public BigDecimal getNtakeKyakusuZenhi() {
		return ntakeKyakusuZenhi;
	}

	/**
	 * @param ntakeKyakusuZenhi 設定する ntakeKyakusuZenhi。
	 */
	public void setNtakeKyakusuZenhi(BigDecimal ntakeKyakusuZenhi) {
		this.ntakeKyakusuZenhi = ntakeKyakusuZenhi;
	}

	/**
	 * @return ntakeKyakuTanka を戻します。
	 */
	public BigDecimal getNtakeKyakuTanka() {
		return ntakeKyakuTanka;
	}

	/**
	 * @param ntakeKyakuTanka 設定する ntakeKyakuTanka。
	 */
	public void setNtakeKyakuTanka(BigDecimal ntakeKyakuTanka) {
		this.ntakeKyakuTanka = ntakeKyakuTanka;
	}

	/**
	 * @return ntakeKyakuTankaZen を戻します。
	 */
	public BigDecimal getNtakeKyakuTankaZen() {
		return ntakeKyakuTankaZen;
	}

	/**
	 * @param ntakeKyakuTankaZen 設定する ntakeKyakuTankaZen。
	 */
	public void setNtakeKyakuTankaZen(BigDecimal ntakeKyakuTankaZen) {
		this.ntakeKyakuTankaZen = ntakeKyakuTankaZen;
	}

	/**
	 * @return ntakeKyakuTankaZenhi を戻します。
	 */
	public BigDecimal getNtakeKyakuTankaZenhi() {
		return ntakeKyakuTankaZenhi;
	}

	/**
	 * @param ntakeKyakuTankaZenhi 設定する ntakeKyakuTankaZenhi。
	 */
	public void setNtakeKyakuTankaZenhi(BigDecimal ntakeKyakuTankaZenhi) {
		this.ntakeKyakuTankaZenhi = ntakeKyakuTankaZenhi;
	}

	/**
	 * @return ntakeUriageAvg を戻します。
	 */
	public BigDecimal getNtakeUriageAvg() {
		return ntakeUriageAvg;
	}

	/**
	 * @param ntakeUriageAvg 設定する ntakeUriageAvg。
	 */
	public void setNtakeUriageAvg(BigDecimal ntakeUriageAvg) {
		this.ntakeUriageAvg = ntakeUriageAvg;
	}

	/**
	 * @return ntakeUriageKouseihi を戻します。
	 */
	public BigDecimal getNtakeUriageKouseihi() {
		return ntakeUriageKouseihi;
	}

	/**
	 * @param ntakeUriageKouseihi 設定する ntakeUriageKouseihi。
	 */
	public void setNtakeUriageKouseihi(BigDecimal ntakeUriageKouseihi) {
		this.ntakeUriageKouseihi = ntakeUriageKouseihi;
	}

	/**
	 * @return ntakeUriageZenhi を戻します。
	 */
	public BigDecimal getNtakeUriageZenhi() {
		return ntakeUriageZenhi;
	}

	/**
	 * @param ntakeUriageZenhi 設定する ntakeUriageZenhi。
	 */
	public void setNtakeUriageZenhi(BigDecimal ntakeUriageZenhi) {
		this.ntakeUriageZenhi = ntakeUriageZenhi;
	}

	/**
	 * @return ntakuKyakusuAvg を戻します。
	 */
	public BigDecimal getNtakuKyakusuAvg() {
		return ntakuKyakusuAvg;
	}

	/**
	 * @param ntakuKyakusuAvg 設定する ntakuKyakusuAvg。
	 */
	public void setNtakuKyakusuAvg(BigDecimal ntakuKyakusuAvg) {
		this.ntakuKyakusuAvg = ntakuKyakusuAvg;
	}

	/**
	 * @return ntakuKyakusuKouseihi を戻します。
	 */
	public BigDecimal getNtakuKyakusuKouseihi() {
		return ntakuKyakusuKouseihi;
	}

	/**
	 * @param ntakuKyakusuKouseihi 設定する ntakuKyakusuKouseihi。
	 */
	public void setNtakuKyakusuKouseihi(BigDecimal ntakuKyakusuKouseihi) {
		this.ntakuKyakusuKouseihi = ntakuKyakusuKouseihi;
	}

	/**
	 * @return ntakuKyakusuZenhi を戻します。
	 */
	public BigDecimal getNtakuKyakusuZenhi() {
		return ntakuKyakusuZenhi;
	}

	/**
	 * @param ntakuKyakusuZenhi 設定する ntakuKyakusuZenhi。
	 */
	public void setNtakuKyakusuZenhi(BigDecimal ntakuKyakusuZenhi) {
		this.ntakuKyakusuZenhi = ntakuKyakusuZenhi;
	}

	/**
	 * @return ntakuKyakuTanka を戻します。
	 */
	public BigDecimal getNtakuKyakuTanka() {
		return ntakuKyakuTanka;
	}

	/**
	 * @param ntakuKyakuTanka 設定する ntakuKyakuTanka。
	 */
	public void setNtakuKyakuTanka(BigDecimal ntakuKyakuTanka) {
		this.ntakuKyakuTanka = ntakuKyakuTanka;
	}

	/**
	 * @return ntakuKyakuTankaZen を戻します。
	 */
	public BigDecimal getNtakuKyakuTankaZen() {
		return ntakuKyakuTankaZen;
	}

	/**
	 * @param ntakuKyakuTankaZen 設定する ntakuKyakuTankaZen。
	 */
	public void setNtakuKyakuTankaZen(BigDecimal ntakuKyakuTankaZen) {
		this.ntakuKyakuTankaZen = ntakuKyakuTankaZen;
	}

	/**
	 * @return ntakuKyakuTankaZenhi を戻します。
	 */
	public BigDecimal getNtakuKyakuTankaZenhi() {
		return ntakuKyakuTankaZenhi;
	}

	/**
	 * @param ntakuKyakuTankaZenhi 設定する ntakuKyakuTankaZenhi。
	 */
	public void setNtakuKyakuTankaZenhi(BigDecimal ntakuKyakuTankaZenhi) {
		this.ntakuKyakuTankaZenhi = ntakuKyakuTankaZenhi;
	}

	/**
	 * @return ntakuUriageAvg を戻します。
	 */
	public BigDecimal getNtakuUriageAvg() {
		return ntakuUriageAvg;
	}

	/**
	 * @param ntakuUriageAvg 設定する ntakuUriageAvg。
	 */
	public void setNtakuUriageAvg(BigDecimal ntakuUriageAvg) {
		this.ntakuUriageAvg = ntakuUriageAvg;
	}

	/**
	 * @return ntakuUriageKouseihi を戻します。
	 */
	public BigDecimal getNtakuUriageKouseihi() {
		return ntakuUriageKouseihi;
	}

	/**
	 * @param ntakuUriageKouseihi 設定する ntakuUriageKouseihi。
	 */
	public void setNtakuUriageKouseihi(BigDecimal ntakuUriageKouseihi) {
		this.ntakuUriageKouseihi = ntakuUriageKouseihi;
	}

	/**
	 * @return ntakuUriageZenhi を戻します。
	 */
	public BigDecimal getNtakuUriageZenhi() {
		return ntakuUriageZenhi;
	}

	/**
	 * @param ntakuUriageZenhi 設定する ntakuUriageZenhi。
	 */
	public void setNtakuUriageZenhi(BigDecimal ntakuUriageZenhi) {
		this.ntakuUriageZenhi = ntakuUriageZenhi;
	}

	/**
	 * @return tasseiRitu を戻します。
	 */
	public BigDecimal getTasseiRitu() {
		return tasseiRitu;
	}

	/**
	 * @param tasseiRitu 設定する tasseiRitu。
	 */
	public void setTasseiRitu(BigDecimal tasseiRitu) {
		this.tasseiRitu = tasseiRitu;
	}

	/**
	 * @return miseCntKbnNsum を戻します。
	 */
	public BigDecimal getMiseCntKbnNsum() {
		return miseCntKbnNsum;
	}

	/**
	 * @param miseCntKbnNsum 設定する miseCntKbnNsum。
	 */
	public void setMiseCntKbnNsum(BigDecimal miseCntKbnNsum) {
		this.miseCntKbnNsum = miseCntKbnNsum;
	}

	/**
	 * @return miseCntKbnNtake を戻します。
	 */
	public BigDecimal getMiseCntKbnNtake() {
		return miseCntKbnNtake;
	}

	/**
	 * @param miseCntKbnNtake 設定する miseCntKbnNtake。
	 */
	public void setMiseCntKbnNtake(BigDecimal miseCntKbnNtake) {
		this.miseCntKbnNtake = miseCntKbnNtake;
	}

	/**
	 * @return miseCntKbnNtaku を戻します。
	 */
	public BigDecimal getMiseCntKbnNtaku() {
		return miseCntKbnNtaku;
	}

	/**
	 * @param miseCntKbnNtaku 設定する miseCntKbnNtaku。
	 */
	public void setMiseCntKbnNtaku(BigDecimal miseCntKbnNtaku) {
		this.miseCntKbnNtaku = miseCntKbnNtaku;
	}

	/**
	 * @return uriageNsum を戻します。
	 */
	public BigDecimal getUriageNsum() {
		return uriageNsum;
	}

	/**
	 * @param uriageNsum 設定する uriageNsum。
	 */
	public void setUriageNsum(BigDecimal uriageNsum) {
		this.uriageNsum = uriageNsum;
	}

	/**
	 * @return uriageNsumZen を戻します。
	 */
	public BigDecimal getUriageNsumZen() {
		return uriageNsumZen;
	}

	/**
	 * @param uriageNsumZen 設定する uriageNsumZen。
	 */
	public void setUriageNsumZen(BigDecimal uriageNsumZen) {
		this.uriageNsumZen = uriageNsumZen;
	}

	/**
	 * @return uriageNtake を戻します。
	 */
	public BigDecimal getUriageNtake() {
		return uriageNtake;
	}

	/**
	 * @param uriageNtake 設定する uriageNtake。
	 */
	public void setUriageNtake(BigDecimal uriageNtake) {
		this.uriageNtake = uriageNtake;
	}

	/**
	 * @return uriageNtakeZen を戻します。
	 */
	public BigDecimal getUriageNtakeZen() {
		return uriageNtakeZen;
	}

	/**
	 * @param uriageNtakeZen 設定する uriageNtakeZen。
	 */
	public void setUriageNtakeZen(BigDecimal uriageNtakeZen) {
		this.uriageNtakeZen = uriageNtakeZen;
	}

	/**
	 * @return uriageNtaku を戻します。
	 */
	public BigDecimal getUriageNtaku() {
		return uriageNtaku;
	}

	/**
	 * @param uriageNtaku 設定する uriageNtaku。
	 */
	public void setUriageNtaku(BigDecimal uriageNtaku) {
		this.uriageNtaku = uriageNtaku;
	}

	/**
	 * @return uriageNtakuZen を戻します。
	 */
	public BigDecimal getUriageNtakuZen() {
		return uriageNtakuZen;
	}

	/**
	 * @param uriageNtakuZen 設定する uriageNtakuZen。
	 */
	public void setUriageNtakuZen(BigDecimal uriageNtakuZen) {
		this.uriageNtakuZen = uriageNtakuZen;
	}
	
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity#getTenkoKbnKj()
	 */
	public String getTenkoKbnKj() {
		return NipoRefUtil.getTenkoKbnLabel(super.getTenkoKbn());
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity#getTenkoKbnZenKj()
	 */
	public String getTenkoKbnZenKj() {
		return NipoRefUtil.getTenkoKbnLabel(super.getTenkoKbnZen());
	}

	/**
	 * @return miseCntNsumTotal を戻します。
	 */
	public BigDecimal getMiseCntNsumTotal() {
		return miseCntNsumTotal;
	}

	/**
	 * @param miseCntNsumTotal 設定する miseCntNsumTotal。
	 */
	public void setMiseCntNsumTotal(BigDecimal miseCntNsumTotal) {
		this.miseCntNsumTotal = miseCntNsumTotal;
	}

	/**
	 * @return miseCntNtakeTotal を戻します。
	 */
	public BigDecimal getMiseCntNtakeTotal() {
		return miseCntNtakeTotal;
	}

	/**
	 * @param miseCntNtakeTotal 設定する miseCntNtakeTotal。
	 */
	public void setMiseCntNtakeTotal(BigDecimal miseCntNtakeTotal) {
		this.miseCntNtakeTotal = miseCntNtakeTotal;
	}

	/**
	 * @return miseCntNtakuTotal を戻します。
	 */
	public BigDecimal getMiseCntNtakuTotal() {
		return miseCntNtakuTotal;
	}

	/**
	 * @param miseCntNtakuTotal 設定する miseCntNtakuTotal。
	 */
	public void setMiseCntNtakuTotal(BigDecimal miseCntNtakuTotal) {
		this.miseCntNtakuTotal = miseCntNtakuTotal;
	}
}
