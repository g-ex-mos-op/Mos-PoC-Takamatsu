package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodTenpoShubetu {
    
    public static final String TABLE = "";
    
    public static final String code_COLUMN = "CODE";
    
    public static final String name_COLUMN = "NAME";
    
    /**
     * 店舗種別コード
     */
    private String code;
    
    /**
     * 店舗種別名
     */
    private String name;
    
    /**
     * 店舗種別コードを取得します。
     * @return 店舗種別コード
     */
    public String getCode() {
        return code;
    }
    /**
     * 店舗種別コードを設定します。
     * @param code 店舗種別コード
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 店舗種別名を取得します。
     * @return 店舗種別名
     */
    public String getName() {
        return name;
    }
    /**
     * 店舗種別名を設定します。
     * @param name 店舗種別名
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
