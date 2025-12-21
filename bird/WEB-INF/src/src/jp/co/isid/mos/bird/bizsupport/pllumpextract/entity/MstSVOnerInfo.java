package jp.co.isid.mos.bird.bizsupport.pllumpextract.entity;

public class MstSVOnerInfo {
    
    public static final String TABLE = "KM32SVCD";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    
    public static final String svNameKna_COLUMN = "SV_NAME_KNA";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String managerCd_COLUMN = "MANAGER_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String onerNameKj_COLUMN = "ONER_NAME_KJ";
    
    /**
     * SVコード
     */
    private String svCd;
    
    /**
     * SV名称(漢字)
     */
    private String svNameKj;
    
    /**
     * SV名称(カナ)
     */
    private String svNameKna;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * SVランク
     */
    private String managerCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * オーナー名称
     */
    private String onerNameKj;
    
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
     * SV名称(漢字)を取得します。
     * @return SV名称(漢字)
     */
    public String getSvNameKj() {
        return svNameKj;
    }
    /**
     * SV名称(漢字)を設定します。
     * @param svNameKj SV名称(漢字)
     */
    public void setSvNameKj(String svNameKj) {
        this.svNameKj = svNameKj;
    }
    
    /**
     * SV名称(カナ)を取得します。
     * @return SV名称(カナ)
     */
    public String getSvNameKna() {
        return svNameKna;
    }
    /**
     * SV名称(カナ)を設定します。
     * @param svNameKna SV名称(カナ)
     */
    public void setSvNameKna(String svNameKna) {
        this.svNameKna = svNameKna;
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
     * SVランクを取得します。
     * @return SVランク
     */
    public String getManagerCd() {
        return managerCd;
    }
    /**
     * SVランクを設定します。
     * @param managerCd SVランク
     */
    public void setManagerCd(String managerCd) {
        this.managerCd = managerCd;
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
    
}
