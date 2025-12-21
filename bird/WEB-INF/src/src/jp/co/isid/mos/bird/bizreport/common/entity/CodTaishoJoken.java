package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodTaishoJoken {
    
    public static final String TABLE = "";
    
    public static final String code_COLUMN = "CODE";
    
    public static final String name_COLUMN = "NAME";
    
    /**
     * 対象条件コード
     */
    private String code;
    
    /**
     * 対象条件名
     */
    private String name;
    
    /**
     * 対象条件コードを取得します。
     * @return 対象条件コード
     */
    public String getCode() {
        return code;
    }
    /**
     * 対象条件コードを設定します。
     * @param code 対象条件コード
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 対象条件名を取得します。
     * @return 対象条件名
     */
    public String getName() {
        return name;
    }
    /**
     * 対象条件名を設定します。
     * @param name 対象条件名
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
