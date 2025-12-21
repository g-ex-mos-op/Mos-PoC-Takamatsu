package jp.co.isid.mos.bird.bizsupport.lumptakein.entity;

import java.math.BigDecimal;

/**
 * P/Lデータ上下限値エンティティ
 * 
 * @author xyuchida
 */
public class TrnPLLimit {
    
    public static final String TABLE = "BT19PLLM";
    
    public static final String columnName_COLUMN = "COLUMN_NAME";
    
    public static final String limitFlg_COLUMN = "LIMIT_FLG";
    
    public static final String limit_COLUMN = "LIMIT";
    
    /**
     * 対象カラム名称
     */
    private String columnName;
    
    /**
     * 上下限フラグ
     */
    private String limitFlg;
    
    /**
     * リミット
     */
    private BigDecimal limit;
    
    /**
     * 対象カラム名称を取得します。
     * @return 対象カラム名称
     */
    public String getColumnName() {
        return columnName;
    }
    /**
     * 対象カラム名称を設定します。
     * @param columnName 対象カラム名称
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    /**
     * 上下限フラグを取得します。
     * @return 上下限フラグ
     */
    public String getLimitFlg() {
        return limitFlg;
    }
    /**
     * 上下限フラグを設定します。
     * @param limitFlg 上下限フラグ
     */
    public void setLimitFlg(String limitFlg) {
        this.limitFlg = limitFlg;
    }
    
    /**
     * リミットを取得します。
     * @return リミット
     */
    public BigDecimal getLimit() {
        return limit;
    }
    /**
     * リミットを設定します。
     * @param limit リミット
     */
    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
    
}
