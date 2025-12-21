package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodBlock {
    
    public static final String TABLE = "BC23BLCK";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return blockCd;
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return blockName;
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    
}
