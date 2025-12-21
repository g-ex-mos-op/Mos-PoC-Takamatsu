package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodTaishoKikan {
    
    public static final String TABLE = "";
    
    public static final String code_COLUMN = "CODE";
    
    public static final String name_COLUMN = "NAME";
    /**
     * 対象期間コード
     */
    private String code;
    
    /**
     * 対象期間名
     */
    private String name;
    
    /**
     * 対象期間コードを取得します。
     * @return 対象期間コード
     */
    public String getCode() {
        return code;
    }
    /**
     * 対象期間コードを設定します。
     * @param code 対象期間コード
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 対象期間名を取得します。
     * @return 対象期間名
     */
    public String getName() {
        return name;
    }
    /**
     * 対象期間名を設定します。
     * @param name 対象期間名
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
