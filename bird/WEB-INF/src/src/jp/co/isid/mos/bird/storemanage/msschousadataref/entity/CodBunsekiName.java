package jp.co.isid.mos.bird.storemanage.msschousadataref.entity;

public class CodBunsekiName {
    
    public static final String TABLE = "BC25MSPS";
    
    public static final String BunsekiKbn_COLUMN = "BUNSEKI_KBN";
    
    public static final String BunsekiName_COLUMN = "BUNSEKI_NAME";
    
    /**
     * 分析区分
     */
    private String BunsekiKbn;
    
    /**
     * 分析ネーム
     */
    private String BunsekiName;
    
    /**
     * 分析区分を取得します。
     * @return 分析区分
     */
    public String getBunsekiKbn() {
        return BunsekiKbn;
    }
    /**
     * 分析区分を設定します。
     * @param BunsekiKbn 分析区分
     */
    public void setBunsekiKbn(String BunsekiKbn) {
        this.BunsekiKbn = BunsekiKbn;
    }
    
    /**
     * 分析ネームを取得します。
     * @return 分析ネーム
     */
    public String getBunsekiName() {
        return BunsekiName;
    }
    /**
     * 分析ネームを設定します。
     * @param BunsekiName 分析ネーム
     */
    public void setBunsekiName(String BunsekiName) {
        this.BunsekiName = BunsekiName;
    }
    
}
