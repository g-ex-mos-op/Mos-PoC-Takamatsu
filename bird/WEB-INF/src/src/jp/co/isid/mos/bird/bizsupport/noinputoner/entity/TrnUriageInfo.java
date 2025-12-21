package jp.co.isid.mos.bird.bizsupport.noinputoner.entity;

import java.math.BigDecimal;

/**
 * 売上情報エンティティ
 * 
 * @author Aspac
 */
public class TrnUriageInfo {
    
    public static final String TABLE = "BT61ZNDM";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    
    /**
     * 営業年月
     */
    private String eigyoDt;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 売上高
     */
    private BigDecimal uriage;
    
    /**
     * 営業年月を取得します。
     * @return 営業年月
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業年月を設定します。
     * @param eigyoDt 営業年月
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
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
     * 売上高を取得します。
     * @return 売上高
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上高を設定します。
     * @param uriage 売上高
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    
}
