package jp.co.isid.mos.bird.analysis.menubeturef.entity;

import java.math.BigDecimal;

public class UIAbcMonth extends UIAbcDay {
    
    public static final String kosuZenGetu_COLUMN = "KOSU_ZEN_GETU";
    
    public static final String kingakuZenGetu_COLUMN = "KINGAKU_ZEN_GETU";
    
    public static final String kingakuKouseiHiZenGetu_COLUMN = "KINGAKU_KOUSEI_HI_ZEN_GETU";
    
    /**
     * 前月販売個数
     */
    private BigDecimal kosuZenGetu = new BigDecimal("0");
    
    /**
     * 前月販売金額
     */
    private BigDecimal kingakuZenGetu = new BigDecimal("0");
    
    /**
     * 前月金額構成比
     */
    private BigDecimal kingakuKouseiHiZenGetu = new BigDecimal("0");
    
    
    /**
     * 前月販売個数を取得します。
     * @return 前月販売個数
     */
    public BigDecimal getKosuZenGetu() {
        return kosuZenGetu;
    }
    /**
     * 前月販売個数を設定します。
     * @param kosuZenGetu 前月販売個数
     */
    public void setKosuZenGetu(BigDecimal kosuZenGetu) {
        this.kosuZenGetu = kosuZenGetu;
    }
    
    /**
     * 前月販売金額を取得します。
     * @return 前月販売金額
     */
    public BigDecimal getKingakuZenGetu() {
        return kingakuZenGetu;
    }
    /**
     * 前月販売金額を設定します。
     * @param kingakuZenGetu 前月販売金額
     */
    public void setKingakuZenGetu(BigDecimal kingakuZenGetu) {
        this.kingakuZenGetu = kingakuZenGetu;
    }
    
    /**
     * 前月金額構成比を取得します。
     * @return 前月金額構成比
     */
    public BigDecimal getKingakuKouseiHiZenGetu() {
        return kingakuKouseiHiZenGetu;
    }
    /**
     * 前月金額構成比を設定します。
     * @param kingakuKouseiHiZenGetu 前月金額構成比
     */
    public void setKingakuKouseiHiZenGetu(BigDecimal kingakuKouseihiZenGetu) {
        this.kingakuKouseiHiZenGetu = kingakuKouseihiZenGetu;
    }
}
