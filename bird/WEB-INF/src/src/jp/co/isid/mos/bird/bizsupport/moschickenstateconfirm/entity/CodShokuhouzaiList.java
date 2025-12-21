package jp.co.isid.mos.bird.bizsupport.moschickenstateconfirm.entity;

public class CodShokuhouzaiList {
    
    public static final String TABLE = "BM41CMNU";
    
    public static final String ckanriNo_COLUMN = "CKANRI_NO";
    
    public static final String shokuCd_COLUMN = "SHOKU_CD";
    
    public static final String shokuNameKna_COLUMN = "SHOKU_NAME_KNA";
    
    /**
     * 管理番号
     */
    private String ckanriNo;
    
    /**
     * 食包材コード
     */
    private String shokuCd;
    
    /**
     * 食包材名称
     */
    private String shokuNameKna;
    
    /**
     * 管理番号を取得します。
     * @return 管理番号
     */
    public String getCkanriNo() {
        return ckanriNo;
    }
    /**
     * 管理番号を設定します。
     * @param ckanriNo 管理番号
     */
    public void setCkanriNo(String ckanriNo) {
        this.ckanriNo = ckanriNo;
    }
    
    /**
     * 食包材コードを取得します。
     * @return 食包材コード
     */
    public String getShokuCd() {
        return shokuCd;
    }
    /**
     * 食包材コードを設定します。
     * @param shokuCd 食包材コード
     */
    public void setShokuCd(String shokuCd) {
        this.shokuCd = shokuCd;
    }
    
    /**
     * 食包材名称を取得します。
     * @return 食包材名称
     */
    public String getShokuNameKna() {
        return shokuNameKna;
    }
    /**
     * 食包材名称を設定します。
     * @param shokuNameKna 食包材名称
     */
    public void setShokuNameKna(String shokuNameKna) {
        this.shokuNameKna = shokuNameKna;
    }
    
}
