package jp.co.isid.mos.bird.analysis.kakouriage.entity;

import java.math.BigDecimal;

public class TrnKakoUriage {
    
    public static final String TABLE = "BD19PAST";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String miseCnt_COLUMN = "MISE_CNT";
    
    /**
     * 営業月
     */
    private String eigyoDt;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 年度
     */
    private String nendo;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 店舗数
     */
    private BigDecimal miseCnt;
    
    /**
     * 営業月を取得します。
     * @return 営業月
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業月を設定します。
     * @param eigyoDt 営業月
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
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
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getMiseCnt() {
        return miseCnt;
    }
    /**
     * 店舗数を設定します。
     * @param miseCnt 店舗数
     */
    public void setMiseCnt(BigDecimal miseCnt) {
        this.miseCnt = miseCnt;
    }
    
}
