package jp.co.isid.mos.bird.storemanage.mssonerpointref.entity;

import java.math.BigDecimal;

public class UITotalPoint {
    
    public static final String TABLE = "BS04MSPS";
    
    public static final String bnrL_COLUMN = "BNR_L";
    
    public static final String bnrLName_COLUMN = "BNR_L_NAME";
    
    public static final String bnrM_COLUMN = "BNR_M";
    
    public static final String bnrMName_COLUMN = "BNR_M_NAME";
    
    public static final String limitmax_COLUMN = "LIMITMAX";
    
    public static final String limitU100_COLUMN = "LIMIT_U_100";
    
    public static final String zenAvg100_COLUMN = "ZEN_AVG_100";
    
    public static final String sibuAvg100_COLUMN = "SIBU_AVG_100";
    
    public static final String svSibuAvg100_COLUMN = "SV_SIBU_AVG_100";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String companyName_COLUMN = "COMPANY_NAME";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String tokuten100_COLUMN = "TOKUTEN_100";
    
    /**
     * 大分類コード
     */
    private String bnrL;
    
    /**
     * 大分類名称
     */
    private String bnrLName;
    
    /**
     * 中分類コード
     */
    private String bnrM;
    
    /**
     * 中分類名称
     */
    private String bnrMName;
    
    /**
     * 上限値
     */
    private BigDecimal limitmax;
    
    /**
     * 上限値（100点満点換算）
     */
    private BigDecimal limitU100;
    
    /**
     * 全国平均
     */
    private BigDecimal zenAvg100;
    
    /**
     * 支部平均
     */
    private BigDecimal sibuAvg100;
    
    /**
     * SV平均
     */
    private BigDecimal svSibuAvg100;
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 会社名称
     */
    private String companyName;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * オーナーコード
     */
    private String svCd;
    
    /**
     * オーナー名称
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
     * 得点
     */
    private BigDecimal tokuten100;
    
    /**
     * 大分類コードを取得します。
     * @return 大分類コード
     */
    public String getBnrL() {
        return bnrL;
    }
    /**
     * 大分類コードを設定します。
     * @param bnrL 大分類コード
     */
    public void setBnrL(String bnrL) {
        this.bnrL = bnrL;
    }
    
    /**
     * 大分類名称を取得します。
     * @return 大分類名称
     */
    public String getBnrLName() {
        return bnrLName;
    }
    /**
     * 大分類名称を設定します。
     * @param bnrLName 大分類名称
     */
    public void setBnrLName(String bnrLName) {
        this.bnrLName = bnrLName;
    }
    
    /**
     * 中分類コードを取得します。
     * @return 中分類コード
     */
    public String getBnrM() {
        return bnrM;
    }
    /**
     * 中分類コードを設定します。
     * @param bnrM 中分類コード
     */
    public void setBnrM(String bnrM) {
        this.bnrM = bnrM;
    }
    
    /**
     * 中分類名称を取得します。
     * @return 中分類名称
     */
    public String getBnrMName() {
        return bnrMName;
    }
    /**
     * 中分類名称を設定します。
     * @param bnrMName 中分類名称
     */
    public void setBnrMName(String bnrMName) {
        this.bnrMName = bnrMName;
    }
    
    /**
     * 上限値を取得します。
     * @return 上限値
     */
    public BigDecimal getLimitmax() {
        return limitmax;
    }
    /**
     * 上限値を設定します。
     * @param limitmax 上限値
     */
    public void setLimitmax(BigDecimal limitmax) {
        this.limitmax = limitmax;
    }
    
    /**
     * 上限値（100点満点換算）を取得します。
     * @return 上限値（100点満点換算）
     */
    public BigDecimal getLimitU100() {
        return limitU100;
    }
    /**
     * 上限値（100点満点換算）を設定します。
     * @param limitU100 上限値（100点満点換算）
     */
    public void setLimitU100(BigDecimal limitU100) {
        this.limitU100 = limitU100;
    }
    
    /**
     * 全国平均を取得します。
     * @return 全国平均
     */
    public BigDecimal getZenAvg100() {
        return zenAvg100;
    }
    /**
     * 全国平均を設定します。
     * @param zenAvg100 全国平均
     */
    public void setZenAvg100(BigDecimal zenAvg100) {
        this.zenAvg100 = zenAvg100;
    }
    
    /**
     * 支部平均を取得します。
     * @return 支部平均
     */
    public BigDecimal getSibuAvg100() {
        return sibuAvg100;
    }
    /**
     * 支部平均を設定します。
     * @param sibuAvg100 支部平均
     */
    public void setSibuAvg100(BigDecimal sibuAvg100) {
        this.sibuAvg100 = sibuAvg100;
    }
    
    /**
     * SV平均を取得します。
     * @return SV平均
     */
    public BigDecimal getSvSibuAvg100() {
        return svSibuAvg100;
    }
    /**
     * SV平均を設定します。
     * @param svSibuAvg100 SV平均
     */
    public void setSvSibuAvg100(BigDecimal svSibuAvg100) {
        this.svSibuAvg100 = svSibuAvg100;
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
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * オーナーコードを設定します。
     * @param svCd オーナーコード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    
    /**
     * オーナー名称を取得します。
     * @return オーナー名称
     */
    public String getSvNameKj() {
        return svNameKj;
    }
    /**
     * オーナー名称を設定します。
     * @param svNameKj オーナー名称
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
     * 得点を取得します。
     * @return 得点
     */
    public BigDecimal getTokuten100() {
        return tokuten100;
    }
    /**
     * 得点を設定します。
     * @param tokuten100 得点
     */
    public void setTokuten100(BigDecimal tokuten100) {
        this.tokuten100 = tokuten100;
    }
    
}
