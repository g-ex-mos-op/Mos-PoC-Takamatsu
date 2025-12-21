package jp.co.isid.mos.bird.storemanage.misemaintenance.entity;

public class MstGyotai {
    
    public static final String TABLE = "BM25GTAI";
    
    public static final String gyotaiKbn_COLUMN = "GYOTAI_KBN";
    
    public static final String gyotaiKbnName_COLUMN = "GYOTAI_KBN_NAME";
    
    public static final String gyotaiKbnSub_COLUMN = "GYOTAI_KBN_SUB";
    
    public static final String gyotaiKbnSubName_COLUMN = "GYOTAI_KBN_SUB_NAME";
    
    /**
     * 業態区分
     */
    private String gyotaiKbn;
    
    /**
     * 業態区分名称
     */
    private String gyotaiKbnName;
    
    /**
     * サブ業態区分
     */
    private String gyotaiKbnSub;
    
    /**
     * サブ業態区分名称
     */
    private String gyotaiKbnSubName;
    
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
     * 業態区分名称を取得します。
     * @return 業態区分名称
     */
    public String getGyotaiKbnName() {
        return gyotaiKbnName;
    }
    /**
     * 業態区分名称を設定します。
     * @param gyotaiKbnName 業態区分名称
     */
    public void setGyotaiKbnName(String gyotaiKbnName) {
        this.gyotaiKbnName = gyotaiKbnName;
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
     * サブ業態区分名称を取得します。
     * @return サブ業態区分名称
     */
    public String getGyotaiKbnSubName() {
        return gyotaiKbnSubName;
    }
    /**
     * サブ業態区分名称を設定します。
     * @param gyotaiKbnSubName サブ業態区分名称
     */
    public void setGyotaiKbnSubName(String gyotaiKbnSubName) {
        this.gyotaiKbnSubName = gyotaiKbnSubName;
    }
    
}
