package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity;

public class UIOnerInfo {
    
    public static final String TABLE = "BM11ONER";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    public static final String onerNameKna_COLUMN = "ONER_NAME_KNA";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称（漢字）
     */
    private String onerNameKj;
    
    /**
     * オーナー名称（カナ）
     */
    private String onerNameKna;
    
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
     * オーナー名称（漢字）を取得します。
     * @return オーナー名称（漢字）
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナー名称（漢字）を設定します。
     * @param onerNameKj オーナー名称（漢字）
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    
    /**
     * オーナー名称（カナ）を取得します。
     * @return オーナー名称（カナ）
     */
    public String getOnerNameKna() {
        return onerNameKna;
    }
    /**
     * オーナー名称（カナ）を設定します。
     * @param onerNameKna オーナー名称（カナ）
     */
    public void setOnerNameKna(String onerNameKna) {
        this.onerNameKna = onerNameKna;
    }
    
}
