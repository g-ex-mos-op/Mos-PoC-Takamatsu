package jp.co.isid.mos.bird.entry.nationalviewlist.entity;

public class CodSv {
    
    public static final String TABLE = "BM52SVCD";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String svCd_COLUMN = "SV_CD";
    
    public static final String svNameKj_COLUMN = "SV_NAME_KJ";
    
    public static final String svNameKna_COLUMN = "SV_NAME_KNA";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String svCd;
    
    /**
     * 支部名称
     */
    private String svNameKj;
    
    /**
     * 支部名称
     */
    private String svNameKna;
    
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
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSvCd() {
        return svCd;
    }
    /**
     * 支部コードを設定します。
     * @param svCd 支部コード
     */
    public void setSvCd(String svCd) {
        this.svCd = svCd;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSvNameKj() {
        return svNameKj;
    }
    /**
     * 支部名称を設定します。
     * @param svNameKj 支部名称
     */
    public void setSvNameKj(String svNameKj) {
        this.svNameKj = svNameKj;
    }
    
    /**
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSvNameKna() {
        return svNameKna;
    }
    /**
     * 支部名称を設定します。
     * @param svNameKna 支部名称
     */
    public void setSvNameKna(String svNameKna) {
        this.svNameKna = svNameKna;
    }
    
}
