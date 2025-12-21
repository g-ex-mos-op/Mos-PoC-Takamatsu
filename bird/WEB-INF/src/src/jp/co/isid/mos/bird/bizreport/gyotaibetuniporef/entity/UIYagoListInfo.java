package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity;

public class UIYagoListInfo {
    
    public static final String TABLE = "BM42YSET";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String gyotaiKbnSub_COLUMN = "GYOTAI_KBN_SUB";
    
    public static final String yagoCd_COLUMN = "YAGO_CD";
    
    public static final String sortYago_COLUMN = "SORT_YAGO";
    
    public static final String yagoDetailCd_COLUMN = "YAGO_DETAIL_CD";
    
    public static final String sortDetail_COLUMN = "SORT_DETAIL";
    
    public static final String yagoName_COLUMN = "YAGO_NAME";
    
    public static final String yagoDetailName_COLUMN = "YAGO_DETAIL_NAME";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * サブ業態区分
     */
    private String gyotaiKbnSub;
    
    /**
     * 屋号コード
     */
    private String yagoCd;
    
    /**
     * 屋号ソートＮｏ
     */
    private String sortYago;
    
    /**
     * 詳細屋号コード
     */
    private String yagoDetailCd;
    
    /**
     * 詳細屋号ソートＮｏ
     */
    private String sortDetail;
    
    /**
     * 屋号名称
     */
    private String yagoName;
    
    /**
     * 詳細屋号名称
     */
    private String yagoDetailName;
    
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
     * 業態区分を取得します。
     * @return 業態区分
     */
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    /**
     * 業態区分を設定します。
     * @param gyotaiKbn 業態区分
     */
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    
    /**
     * サブ業態区分を取得します。
     * @return サブ業態区分
     */
    public String getGyotaiKbnSub() {
        return gyotaiKbnSub;
    }
    /**
     * サブ業態区分を設定します。
     * @param gyotaiKbnSub サブ業態区分
     */
    public void setGyotaiKbnSub(String gyotaiKbnSub) {
        this.gyotaiKbnSub = gyotaiKbnSub;
    }
    
    /**
     * 屋号コードを取得します。
     * @return 屋号コード
     */
    public String getYagoCd() {
        return yagoCd;
    }
    /**
     * 屋号コードを設定します。
     * @param yagoCd 屋号コード
     */
    public void setYagoCd(String yagoCd) {
        this.yagoCd = yagoCd;
    }
    
    /**
     * 屋号ソートＮｏを取得します。
     * @return 屋号ソートＮｏ
     */
    public String getSortYago() {
        return sortYago;
    }
    /**
     * 屋号ソートＮｏを設定します。
     * @param sortYago 屋号ソートＮｏ
     */
    public void setSortYago(String sortYago) {
        this.sortYago = sortYago;
    }
    
    /**
     * 詳細屋号コードを取得します。
     * @return 詳細屋号コード
     */
    public String getYagoDetailCd() {
        return yagoDetailCd;
    }
    /**
     * 詳細屋号コードを設定します。
     * @param yagoDetailCd 詳細屋号コード
     */
    public void setYagoDetailCd(String yagoDetailCd) {
        this.yagoDetailCd = yagoDetailCd;
    }
    
    /**
     * 詳細屋号ソートＮｏを取得します。
     * @return 詳細屋号ソートＮｏ
     */
    public String getSortDetail() {
        return sortDetail;
    }
    /**
     * 詳細屋号ソートＮｏを設定します。
     * @param sortDetail 詳細屋号ソートＮｏ
     */
    public void setSortDetail(String sortDetail) {
        this.sortDetail = sortDetail;
    }
    
    /**
     * 屋号名称を取得します。
     * @return 屋号名称
     */
    public String getYagoName() {
        return yagoName;
    }
    /**
     * 屋号名称を設定します。
     * @param yagoName 屋号名称
     */
    public void setYagoName(String yagoName) {
        this.yagoName = yagoName;
    }
    
    /**
     * 詳細屋号名称を取得します。
     * @return 詳細屋号名称
     */
    public String getYagoDetailName() {
        return yagoDetailName;
    }
    /**
     * 詳細屋号名称を設定します。
     * @param yagoDetailName 詳細屋号名称
     */
    public void setYagoDetailName(String yagoDetailName) {
        this.yagoDetailName = yagoDetailName;
    }
    
}
