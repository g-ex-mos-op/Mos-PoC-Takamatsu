package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodKikanSitei {
    
    public static final String TABLE = "";
    
    public static final String code_COLUMN = "CODE";
    
    public static final String name_COLUMN = "NAME";
    /**
     * 期間指定コード
     */
    private String code;
    
    /**
     * 期間指定名
     */
    private String name;
    
    /**
     * 期間指定コードを取得します。
     * @return 期間指定コード
     */
    public String getCode() {
        return code;
    }
    /**
     * 期間指定コードを設定します。
     * @param code 期間指定コード
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 期間指定名を取得します。
     * @return 期間指定名
     */
    public String getName() {
        return name;
    }
    /**
     * 期間指定名を設定します。
     * @param name 期間指定名
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
