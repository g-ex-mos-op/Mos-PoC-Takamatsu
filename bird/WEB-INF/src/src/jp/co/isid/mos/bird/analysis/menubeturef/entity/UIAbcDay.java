package jp.co.isid.mos.bird.analysis.menubeturef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIMenu;

public class UIAbcDay extends UIMenu{
    
    public static final String TABLE = "BD10NMSL";
        
    public static final String hanbaiTenpoCnt_COLUMN = "HANBAI_TENPO_CNT";
    
    public static final String kosuZenNen_COLUMN = "KOSU_ZEN_NEN";
    
    public static final String kingakuZenNen_COLUMN = "KINGAKU_ZEN_NEN";
    
    public static final String kingakuKouseiHiZenNen_COLUMN = "KINGAKU_KOUSEI_HI_ZEN_NEN";
    
    /**
     * 販売店舗数
     */
    private BigDecimal hanbaiTenpoCnt = new BigDecimal("0");
    
    /**
     * 前年販売個数
     */
    private BigDecimal kosuZenNen = new BigDecimal("0");
    
    /**
     * 前年販売金額
     */
    private BigDecimal kingakuZenNen = new BigDecimal("0");
    
    /**
     * 前年金額構成比
     */
    private BigDecimal kingakuKouseiHiZenNen = new BigDecimal("0");
    
    /**
     * 前年販売個数を取得します。
     * @return 前年販売個数
     */
    public BigDecimal getKosuZenNen() {
        return kosuZenNen;
    }
    /**
     * 前年販売個数を設定します。
     * @param kosuZenNen 前年販売個数
     */
    public void setKosuZenNen(BigDecimal kosuZenNen) {
        this.kosuZenNen = kosuZenNen;
    }
    
    /**
     * 前年販売金額を取得します。
     * @return 前年販売金額
     */
    public BigDecimal getKingakuZenNen() {
        return kingakuZenNen;
    }
    /**
     * 前年販売金額を設定します。
     * @param kingakuZenNen 前年販売金額
     */
    public void setKingakuZenNen(BigDecimal kingakuZenNen) {
        this.kingakuZenNen = kingakuZenNen;
    }
    
    /**
     * 前年金額構成比を取得します。
     * @return 前年金額構成比
     */
    public BigDecimal getKingakuKouseiHiZenNen() {
        return kingakuKouseiHiZenNen;
    }
    /**
     * 前年金額構成比を設定します。
     * @param kingakuKouseiHiZenNen 前年金額構成比
     */
    public void setKingakuKouseiHiZenNen(BigDecimal kingakuKouseiHiZenNen) {
        this.kingakuKouseiHiZenNen = kingakuKouseiHiZenNen;
    }
	/**
	 * @return hanbaiTenpoCnt を戻します。
	 */
	public BigDecimal getHanbaiTenpoCnt() {
		return hanbaiTenpoCnt;
	}
	/**
	 * @param hanbaiTenpoCnt を クラス変数hanbaiTenpoCntへ設定します。
	 */
	public void setHanbaiTenpoCnt(BigDecimal hanbaiTenpoCnt) {
		this.hanbaiTenpoCnt = hanbaiTenpoCnt;
	}
    
}
