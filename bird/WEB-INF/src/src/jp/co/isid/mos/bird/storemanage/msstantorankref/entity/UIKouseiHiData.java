package jp.co.isid.mos.bird.storemanage.msstantorankref.entity;

import java.math.BigDecimal;

public class UIKouseiHiData {
    
    public static final String TABLE = "BM04MSPS";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String kai_COLUMN = "KAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
        
    public static final String allMisecnt_COLUMN = "ALL_MISECNT";
    
    public static final String misecnt1_COLUMN = "MISECNT1";
    
    public static final String misecnt2_COLUMN = "MISECNT2";
    
    public static final String misecnt3_COLUMN = "MISECNT3";
    
    public static final String misecnt4_COLUMN = "MISECNT4";
    
    public static final String kouseihi1_COLUMN = "KOUSEIHI1";
    
    public static final String kouseihi2_COLUMN = "KOUSEIHI2";
    
    public static final String kouseihi3_COLUMN = "KOUSEIHI3";
    
    public static final String kouseihi4_COLUMN = "KOUSEIHI4";
    
    /**
     * 年度
     */
    private String nendo;
    
    /**
     * 回
     */
    private String kai;
    
    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * 全店舗数
     */
    private BigDecimal allMisecnt;
    
    /**
     * 店舗数
     */
    private BigDecimal misecnt1;
    
    /**
     * 店舗数
     */
    private BigDecimal misecnt2;
    
    /**
     * 店舗数
     */
    private BigDecimal misecnt3;
    
    /**
     * 店舗数
     */
    private BigDecimal misecnt4;
    
    /**
     * 構成比
     */
    private BigDecimal kouseihi1;
    
    /**
     * 構成比
     */
    private BigDecimal kouseihi2;
    
    /**
     * 構成比
     */
    private BigDecimal kouseihi3;
    
    /**
     * 構成比
     */
    private BigDecimal kouseihi4;
    
    /**
     * 年度を取得します。
     * @return 年度
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 年度を設定します。
     * @param nendo 年度
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    
    /**
     * 回を取得します。
     * @return 回
     */
    public String getKai() {
        return kai;
    }
    /**
     * 回を設定します。
     * @param kai 回
     */
    public void setKai(String kai) {
        this.kai = kai;
    }
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
        
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getAllMisecnt() {
        return allMisecnt;
    }
    /**
     * 店舗数を設定します。
     * @param allMisecnt 店舗数
     */
    public void setAllMisecnt(BigDecimal allMisecnt) {
        this.allMisecnt = allMisecnt;
    }
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getMisecnt1() {
        return misecnt1;
    }
    /**
     * 店舗数を設定します。
     * @param misecnt1 店舗数
     */
    public void setMisecnt1(BigDecimal misecnt1) {
        this.misecnt1 = misecnt1;
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getMisecnt2() {
        return misecnt2;
    }
    /**
     * 店舗数を設定します。
     * @param misecnt2 店舗数
     */
    public void setMisecnt2(BigDecimal misecnt2) {
        this.misecnt2 = misecnt2;
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getMisecnt3() {
        return misecnt3;
    }
    /**
     * 店舗数を設定します。
     * @param misecnt3 店舗数
     */
    public void setMisecnt3(BigDecimal misecnt3) {
        this.misecnt3 = misecnt3;
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getMisecnt4() {
        return misecnt4;
    }
    /**
     * 店舗数を設定します。
     * @param misecnt4 店舗数
     */
    public void setMisecnt4(BigDecimal misecnt4) {
        this.misecnt4 = misecnt4;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseihi1() {
        return kouseihi1;
    }
    /**
     * 構成比を設定します。
     * @param kouseihi1 構成比
     */
    public void setKouseihi1(BigDecimal kouseihi1) {
        this.kouseihi1 = kouseihi1;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseihi2() {
        return kouseihi2;
    }
    /**
     * 構成比を設定します。
     * @param kouseihi2 構成比
     */
    public void setKouseihi2(BigDecimal kouseihi2) {
        this.kouseihi2 = kouseihi2;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseihi3() {
        return kouseihi3;
    }
    /**
     * 構成比を設定します。
     * @param kouseihi3 構成比
     */
    public void setKouseihi3(BigDecimal kouseihi3) {
        this.kouseihi3 = kouseihi3;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseihi4() {
        return kouseihi4;
    }
    /**
     * 構成比を設定します。
     * @param kouseihi4 構成比
     */
    public void setKouseihi4(BigDecimal kouseihi4) {
        this.kouseihi4 = kouseihi4;
    }
    
}
