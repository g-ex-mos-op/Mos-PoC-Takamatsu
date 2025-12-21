package jp.co.isid.mos.bird.commonform.common.entity;

public class CodSibu {
    
    public static final String TABLE = "BM10GSIB";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";

    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    
    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名
     */
    private String sibuName;

    /**
     * 会社コードを取得します。
     * @return 支部コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param company 支部コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    /**
     * 支部コードを取得します。q
     * @return 支部コード
     */
    public String getSibuCd() {
        if(sibuCd == null){
            return "";
        }
        return sibuCd.trim();
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }

    /**
     * 支部名を取得します。
     * @return 支部名
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名を設定します。
     * @param sibuName 支部名
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
    
}
