package jp.co.isid.mos.bird.storemanage.msstantorankref.entity;

import java.math.BigDecimal;

public class UIRankingData {
    
    public static final String TABLE = "BM04MSPS";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String kai_COLUMN = "KAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String rank_COLUMN = "RANK";
    
    public static final String spcFlg_COLUMN = "SPC_FLG";
    
    public static final String zenRank_COLUMN = "ZEN_RANK";
    
    public static final String hyoukaData_COLUMN = "HYOUKA_DATA";
    
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
     * 会社名称
     */
    private String companyName;
    
    /**
     * SVコード
     */
    private String svCd;
    
    /**
     * SV名称
     */
    private String svNameKj;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 店舗コード
     */
    private String miseCd;
    
    /**
     * 店舗名称
     */
    private String miseNameKj;
    
    /**
     * ランキング
     */
    private BigDecimal rank;
    
    /**
     * 特殊店フラグ
     */
    private String spcFlg;
    
    /**
     * 全国順位
     */
    private BigDecimal zenRank;
    
    /**
     * 総合ポイント
     */
    private BigDecimal hyoukaData;
    
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
     * 会社名称を取得します。
     * @return 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * SVコードを取得します。
     * @return SVコード
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * SVコードを設定します。
     * @param svCd SVコード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    
    /**
     * SV名称を取得します。
     * @return SV名称
     */
    public String getSvNameKj() {
        return svNameKj;
    }
    /**
     * SV名称を設定します。
     * @param svNameKj SV名称
     */
    public void setSvNameKj(String svNameKj) {
        this.svNameKj = svNameKj;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param onerNameKj オーナー名称
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店舗コードを設定します。
     * @param miseCd 店舗コード
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
     * ランキングを取得します。
     * @return ランキング
     */
    public BigDecimal getRank() {
        return rank;
    }
    /**
     * ランキングを設定します。
     * @param rank ランキング
     */
    public void setRank(BigDecimal rank) {
        this.rank = rank;
    }
    
    /**
     * 特殊店フラグを取得します。
     * @return 特殊店フラグ
     */
    public String getSpcFlg() {
        return spcFlg;
    }
    /**
     * 特殊店フラグを設定します。
     * @param spcFlg 特殊店フラグ
     */
    public void setSpcFlg(String spcFlg) {
        this.spcFlg = spcFlg;
    }
    
    /**
     * 全国順位を取得します。
     * @return 全国順位
     */
    public BigDecimal getZenRank() {
        return zenRank;
    }
    /**
     * 全国順位を設定します。
     * @param zenRank 全国順位
     */
    public void setZenRank(BigDecimal zenRank) {
        this.zenRank = zenRank;
    }
    
    /**
     * 総合ポイントを取得します。
     * @return 総合ポイント
     */
    public BigDecimal getHyoukaData() {
        return hyoukaData;
    }
    /**
     * 総合ポイントを設定します。
     * @param hyoukaData 総合ポイント
     */
    public void setHyoukaData(BigDecimal hyoukaData) {
        this.hyoukaData = hyoukaData;
    }
    
}
