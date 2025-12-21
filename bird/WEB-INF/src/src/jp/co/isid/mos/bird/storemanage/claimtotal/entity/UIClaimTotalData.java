package jp.co.isid.mos.bird.storemanage.claimtotal.entity;

import java.math.BigDecimal;

public class UIClaimTotalData {
    
    public static final String TABLE = "BD05VICE";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String jusinDt_COLUMN = "JUSIN_DT";
    
    public static final String bnrM_COLUMN = "BNR_M";
    
    public static final String claimCount_COLUMN = "CLAIM_COUNT";
    
    public static final String typeCd_COLUMN = "TYPE_CD";
    
    public static final String rowType_COLUMN = "ROW_TYPE";
    
    public static final String hyojitaishoName_COLUMN = "HYOJITAISHO_NAME";
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 年月
     */
    private String jusinDt;
    
    /**
     * 中分類
     */
    private String bnrM;
    
    /**
     * 件数
     */
    private BigDecimal claimCount;
    
    /**
     * タイプコード
     */
    private String typeCd;
    
    /**
     * 列タイプ
     */
    private String rowType;
    
    /**
     * 表示対象名
     */
    private String hyojitaishoName;
    
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
     * 中分類を取得します。
     * @return 中分類
     */
    public String getBnrM() {
        return bnrM;
    }
    /**
     * 中分類を設定します。
     * @param bnrM 中分類
     */
    public void setBnrM(String bnrM) {
        this.bnrM = bnrM;
    }
    
    /**
     * 件数を取得します。
     * @return 件数
     */
    public BigDecimal getClaimCount() {
        return claimCount;
    }
    /**
     * 件数を設定します。
     * @param claimCount 件数
     */
    public void setClaimCount(BigDecimal claimCount) {
        this.claimCount = claimCount;
    }
    
    /**
     * タイプコードを取得します。
     * @return タイプコード
     */
    public String getTypeCd() {
        return typeCd;
    }
    /**
     * タイプコードを設定します。
     * @param typeCd タイプコード
     */
    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }
    
    /**
     * 列タイプを取得します。
     * @return 列タイプ
     */
    public String getRowType() {
        return rowType;
    }
    /**
     * 列タイプを設定します。
     * @param rowType 列タイプ
     */
    public void setRowType(String rowType) {
        this.rowType = rowType;
    }
    public String getJusinDt() {
        return jusinDt;
    }
    public void setJusinDt(String jusinDt) {
        this.jusinDt = jusinDt;
    }
    public String getHyojitaishoName() {
        return hyojitaishoName;
    }
    public void setHyojitaishoName(String hyojitaishoName) {
        this.hyojitaishoName = hyojitaishoName;
    }
    
}
