package jp.co.isid.mos.bird.storemanage.msstantorankref.entity;

public class CodNendo {
    
    public static final String TABLE = "";
    
    public static final String nendo_COLUMN = "NENDO";
    
    public static final String nendoName_COLUMN = "NENDO_NAME";
    
    /**
     * 年度値
     */
    private String nendo;
    
    /**
     * 年度名称
     */
    private String nendoName;
    
    /**
     * 年度値を取得します。
     * @return 年度値
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * 年度値を設定します。
     * @param nendo 年度値
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    
    /**
     * 年度名称を取得します。
     * @return 年度名称
     */
    public String getNendoName() {
        return nendoName;
    }
    /**
     * 年度名称を設定します。
     * @param nendoName 年度名称
     */
    public void setNendoName(String nendoName) {
        this.nendoName = nendoName;
    }
    
}
