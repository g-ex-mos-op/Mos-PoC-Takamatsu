package jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity;

public class MstOnerInfo {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerKbn_COLUMN = "ONER_KBN";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー区分
     */
    private String onerKbn;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * オーナー区分を取得します。
     * @return オーナー区分
     */
    public String getOnerKbn() {
        return onerKbn;
    }
    /**
     * オーナー区分を設定します。
     * @param onerKbn オーナー区分
     */
    public void setOnerKbn(String onerKbn) {
        this.onerKbn = onerKbn;
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
