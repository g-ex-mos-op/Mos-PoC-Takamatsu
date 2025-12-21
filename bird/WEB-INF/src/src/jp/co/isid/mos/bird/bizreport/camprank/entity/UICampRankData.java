package jp.co.isid.mos.bird.bizreport.camprank.entity;

import java.math.BigDecimal;

public class UICampRankData {
    
    public static final String TABLE = "BD03CPML";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String hanbaiKosu_COLUMN = "HANBAI_KOSU";
    
    public static final String hanbaiKin_COLUMN = "HANBAI_KIN";
    
    public static final String kingakuKoseiHi_COLUMN = "KINGAKU_KOSEI_HI";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String zenUriage_COLUMN = "ZEN_URIAGE";
    
    public static final String zennenHi_COLUMN = "ZENNEN_HI";
    
    public static final String rank_COLUMN = "RANK";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 販売個数
     */
    private BigDecimal hanbaiKosu;
    
    /**
     * 販売金額
     */
    private BigDecimal hanbaiKin;
    
    /**
     * 金額構成比
     */
    private BigDecimal kingakuKoseiHi;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 前年売上
     */
    private BigDecimal zenUriage;
    
    /**
     * 前年比
     */
    private BigDecimal zennenHi;
    
    /**
     * 順位
     */
    private BigDecimal rank;
    
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
     * 店舗名称を取得します。
     * @return 店舗名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店舗名称を設定します。
     * @param miseNameKj 店舗名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
    /**
     * 販売個数を取得します。
     * @return 販売個数
     */
    public BigDecimal getHanbaiKosu() {
        return hanbaiKosu;
    }
    /**
     * 販売個数を設定します。
     * @param hanbaiKosu 販売個数
     */
    public void setHanbaiKosu(BigDecimal hanbaiKosu) {
        this.hanbaiKosu = hanbaiKosu;
    }
    
    /**
     * 販売金額を取得します。
     * @return 販売金額
     */
    public BigDecimal getHanbaiKin() {
        return hanbaiKin;
    }
    /**
     * 販売金額を設定します。
     * @param hanbaiKin 販売金額
     */
    public void setHanbaiKin(BigDecimal hanbaiKin) {
        this.hanbaiKin = hanbaiKin;
    }
    
    /**
     * 金額構成比を取得します。
     * @return 金額構成比
     */
    public BigDecimal getKingakuKoseiHi() {
        return kingakuKoseiHi;
    }
    /**
     * 金額構成比を設定します。
     * @param kingakuKoseiHi 金額構成比
     */
    public void setKingakuKoseiHi(BigDecimal kingakuKoseiHi) {
        this.kingakuKoseiHi = kingakuKoseiHi;
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
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getZenUriage() {
        return zenUriage;
    }
    /**
     * 前年売上を設定します。
     * @param zenUriage 前年売上
     */
    public void setZenUriage(BigDecimal zenUriage) {
        this.zenUriage = zenUriage;
    }
    
    /**
     * 前年比を取得します。
     * @return 前年比
     */
    public BigDecimal getZennenHi() {
        return zennenHi;
    }
    /**
     * 前年比を設定します。
     * @param zennenHi 前年比
     */
    public void setZennenHi(BigDecimal zennenHi) {
        this.zennenHi = zennenHi;
    }
    
    /**
     * 順位を取得します。
     * @return 順位
     */
    public BigDecimal getRank() {
        return rank;
    }
    /**
     * 順位を設定します。
     * @param rank 順位
     */
    public void setRank(BigDecimal rank) {
        this.rank = rank;
    }
    
    public String getZennenHiClass() {
        return getZennenHi().compareTo((new BigDecimal("100"))) < 0 ? "body_hiritu_m" : "body_num_n";
    }
}
