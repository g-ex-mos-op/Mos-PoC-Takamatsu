package jp.co.isid.mos.bird.communication.docform.entity;


public class UIViewShozokuInfo {
    
    public static final String TABLE = "BT13IASZ";
    
    public static final String shozokuKbn_COLUMN = "SHOZOKU_KBN";
    
    public static final String shozokuName_COLUMN = "SHOZOKU_NAME";
    
    /**
     * 所属区分
     */
    private String shozokuKbn;
    
    /**
     * 所属名称
     */
    private String shozokuName;
    
    /**
     * 所属区分を取得します。
     * @return 所属区分
     */
    public String getShozokuKbn() {
        return shozokuKbn;
    }
    /**
     * 所属区分を設定します。
     * @param shozokuKbn 所属区分
     */
    public void setShozokuKbn(String shozokuKbn) {
        this.shozokuKbn = shozokuKbn;
    }
    
    /**
     * 所属名称を取得します。
     * @return 所属名称
     */
    public String getShozokuName() {
        return shozokuName;
    }
    /**
     * 所属名称を設定します。
     * @param shozokuName 所属名称
     */
    public void setShozokuName(String shozokuName) {
        this.shozokuName = shozokuName;
    }
    
}
