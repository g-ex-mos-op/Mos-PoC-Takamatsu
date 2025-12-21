package jp.co.isid.mos.bird.bill.detailbilldownload.entity;

import java.math.BigDecimal;

public class UIBillList {
    
    public static final String TABLE = "BS02SEKR";
    
    public static final String seikyushoId_COLUMN = "SEIKYUSHO_ID";
    
    public static final String kingaku_COLUMN = "KINGAKU";
    
    public static final String nengetu_COLUMN = "NENGETU";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    /**
     * 請求書ID
     */
    private String seikyushoId;
    
    /**
     * 金額
     */
    private BigDecimal kingaku;
    
    /**
     * 年月
     */
    private String nengetu;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 請求書IDを取得します。
     * @return 請求書ID
     */
    public String getSeikyushoId() {
        return seikyushoId;
    }
    /**
     * 請求書IDを設定します。
     * @param seikyushoId 請求書ID
     */
    public void setSeikyushoId(String seikyushoId) {
        this.seikyushoId = seikyushoId;
    }
    
    /**
     * 金額を取得します。
     * @return 金額
     */
    public BigDecimal getKingaku() {
        return kingaku;
    }
    /**
     * 金額を設定します。
     * @param kingaku 金額
     */
    public void setKingaku(BigDecimal kingaku) {
        this.kingaku = kingaku;
    }
    
    /**
     * 年月を取得します。
     * @return 年月
     */
    public String getNengetu() {
        return nengetu;
    }
    /**
     * 年月を設定します。
     * @param nengetu 年月
     */
    public void setNengetu(String nengetu) {
        this.nengetu = nengetu;
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
    
}
