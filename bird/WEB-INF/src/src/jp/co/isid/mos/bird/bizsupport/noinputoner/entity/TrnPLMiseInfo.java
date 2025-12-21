package jp.co.isid.mos.bird.bizsupport.noinputoner.entity;

/**
 * P/Lデータエンティティ
 * 
 * @author Aspac
 */
public class TrnPLMiseInfo {
    
    public static final String TABLE = "BM01TENM";
    
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String closeMiseFlg_COLUMN = "CLOSE_MISE_FLG";

    
    /**
     * 企業コード
     */
    private String companyCd;
        
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * クローズ店フラグ
     */
    private String closeMiseFlg;
    
    
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
     * クローズ店フラグを取得します。
     * @return クローズ店フラグ
     */
    public String getCloseMiseFlg() {
        return closeMiseFlg;
    }
    /**
     * クローズ店フラグを設定します。
     * @param closeMiseFlg クローズ店フラグ
     */
    public void setCloseMiseFlg(String closeMiseFlg) {
        this.closeMiseFlg = closeMiseFlg;
    }
    

}
