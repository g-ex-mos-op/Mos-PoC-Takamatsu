package jp.co.isid.mos.bird.storemanage.msstantorankref.entity;

public class CodKai {
    
    public static final String TABLE = "";
    
    public static final String kai_COLUMN = "KAI";
    
    public static final String kaiName_COLUMN = "KAI_NAME";
    
    /**
     * 回値
     */
    private String kai;
    
    /**
     * 回名称
     */
    private String kaiName;
    
    /**
     * 回値を取得します。
     * @return 回値
     */
    public String getKai() {
        return kai;
    }
    /**
     * 回値を設定します。
     * @param kai 回値
     */
    public void setKai(String kai) {
        this.kai = kai;
    }
    
    /**
     * 回名称を取得します。
     * @return 回名称
     */
    public String getKaiName() {
        return kaiName;
    }
    /**
     * 回名称を設定します。
     * @param kaiName 回名称
     */
    public void setKaiName(String kaiName) {
        this.kaiName = kaiName;
    }
    
}
