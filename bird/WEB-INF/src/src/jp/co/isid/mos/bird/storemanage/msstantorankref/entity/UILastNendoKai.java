package jp.co.isid.mos.bird.storemanage.msstantorankref.entity;

public class UILastNendoKai {
    
    public static final String TABLE = "BM31MSPM";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String kai_COLUMN = "KAI";
    
    /**
     * 年度
     */
    private String nendo;
    
    /**
     * 回
     */
    private String kai;
    
    /**
     * 年度を取得します。
     * @return 年度
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 年度を設定します。
     * @param nendo 年度
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    
    /**
     * 回を取得します。
     * @return 回
     */
    public String getKai() {
        return kai;
    }
    /**
     * 回を設定します。
     * @param kai 回
     */
    public void setKai(String kai) {
        this.kai = kai;
    }
    
}
