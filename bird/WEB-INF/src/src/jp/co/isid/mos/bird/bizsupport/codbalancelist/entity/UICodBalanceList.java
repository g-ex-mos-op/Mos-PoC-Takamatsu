package jp.co.isid.mos.bird.bizsupport.codbalancelist.entity;

import java.math.BigDecimal;

/**
 * COD残高一覧(UICodBalanceList)
 * 
 * @author xkinu
 *
 */
public class UICodBalanceList {
    
    public static final String TABLE = "BD02CODZ";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String onerName_COLUMN = "ONER_NAME";
    
    public static final String genZan_COLUMN = "GEN_ZAN";
    
    public static final String juchuKin_COLUMN = "JUCHU_KIN";
    
    public static final String hikiZan_COLUMN = "HIKI_ZAN";
    
    public static final String sakiJuchuKin_COLUMN = "SAKI_JUCHU_KIN";
    
    public static final String calcDate_COLUMN = "CALC_DATE";
    
    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * 売掛先名称
     */
    private String onerName;
    
    /**
     * 残高
     */
    private BigDecimal genZan;
    
    /**
     * 翌日受注金額
     */
    private BigDecimal juchuKin;
    
    /**
     * 差引残高
     */
    private BigDecimal hikiZan;
    
    /**
     * 先日付受注金額
     */
    private BigDecimal sakiJuchuKin;
    
    /**
     * 算出日
     */
    private String calcDate;
    
    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    
    /**
     * 売掛先名称を取得します。
     * @return 売掛先名称
     */
    public String getOnerName() {
        return onerName;
    }
    /**
     * 売掛先名称を設定します。
     * @param onerName 売掛先名称
     */
    public void setOnerName(String onerName) {
        this.onerName = onerName;
    }
    
    /**
     * 残高を取得します。
     * @return 残高
     */
    public BigDecimal getGenZan() {
        return genZan;
    }
    /**
     * 残高を設定します。
     * @param genZan 残高
     */
    public void setGenZan(BigDecimal genZan) {
        this.genZan = genZan;
    }
    
    /**
     * 翌日受注金額を取得します。
     * @return 翌日受注金額
     */
    public BigDecimal getJuchuKin() {
        return juchuKin;
    }
    /**
     * 翌日受注金額を設定します。
     * @param juchuKin 翌日受注金額
     */
    public void setJuchuKin(BigDecimal juchuKin) {
        this.juchuKin = juchuKin;
    }
    
    /**
     * 差引残高を取得します。
     * @return 差引残高
     */
    public BigDecimal getHikiZan() {
        return hikiZan;
    }
    /**
     * 差引残高を設定します。
     * @param hikiZan 差引残高
     */
    public void setHikiZan(BigDecimal hikiZan) {
        this.hikiZan = hikiZan;
    }
    
    /**
     * 先日付受注金額を取得します。
     * @return 先日付受注金額
     */
    public BigDecimal getSakiJuchuKin() {
        return sakiJuchuKin;
    }
    /**
     * 先日付受注金額を設定します。
     * @param sakiJuchuKin 先日付受注金額
     */
    public void setSakiJuchuKin(BigDecimal sakiJuchuKin) {
        this.sakiJuchuKin = sakiJuchuKin;
    }
    
    /**
     * 算出日を取得します。
     * @return 算出日
     */
    public String getCalcDate() {
        return calcDate;
    }
    /**
     * 算出日を設定します。
     * @param calcDate 算出日
     */
    public void setCalcDate(String calcDate) {
        this.calcDate = calcDate;
    }
    
}
