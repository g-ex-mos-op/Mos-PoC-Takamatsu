package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity;

import java.math.BigDecimal;

public class TrnSibuUriageNipoSegRelate {
    
    public static final String TABLE = "BT60ZNIP";
    
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
    
    public static final String uriYosan_COLUMN = "URI_YOSAN";
    
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
    
}
