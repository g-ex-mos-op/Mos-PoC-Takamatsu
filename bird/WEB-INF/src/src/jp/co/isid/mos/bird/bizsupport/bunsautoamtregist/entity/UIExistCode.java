package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity;

public class UIExistCode {
    
    public static final String TABLE = "ＢC05KCOM";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String targetCd_COLUMN = "TARGET_CD";
    
    public static final String targetNameKj_COLUMN = "TARGET_NAME_KJ";
    
    public static final String targetNameKna_COLUMN = "TARGET_NAME_KNA";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 対象コード
     */
    private String targetCd;
    
    /**
     * 対象名称
     */
    private String targetNameKj;
    
    /**
     * 対象名称
     */
    private String targetNameKna;
    
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
     * 対象コードを取得します。
     * @return 対象コード
     */
    public String getTargetCd() {
        return targetCd;
    }
    /**
     * 対象コードを設定します。
     * @param targetCd 対象コード
     */
    public void setTargetCd(String targetCd) {
        this.targetCd = targetCd;
    }
    
    /**
     * 対象名称を取得します。
     * @return 対象名称
     */
    public String getTargetNameKj() {
        return targetNameKj;
    }
    /**
     * 対象名称を設定します。
     * @param targetNameKj 対象名称
     */
    public void setTargetNameKj(String targetNameKj) {
        this.targetNameKj = targetNameKj;
    }
    
    /**
     * 対象名称を取得します。
     * @return 対象名称
     */
    public String getTargetNameKna() {
        return targetNameKna;
    }
    /**
     * 対象名称を設定します。
     * @param targetNameKna 対象名称
     */
    public void setTargetNameKna(String targetNameKna) {
        this.targetNameKna = targetNameKna;
    }
    
}
