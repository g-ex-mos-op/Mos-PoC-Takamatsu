package jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity;

public class CodBlock {
    
    public static final String TABLE = "BC23BLCK";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";

    public static final String changeFlg_COLUMN = "CHANGE_FLG";
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    /**
     * 画面変更フラグ
     */
    private String changeFlg;
    
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
	/**
	 * @return changeFlg を戻します。
	 */
	public String getChangeFlg() {
		return changeFlg;
	}
	/**
	 * @param changeFlg 設定する changeFlg。
	 */
	public void setChangeFlg(String changeFlg) {
		this.changeFlg = changeFlg;
	}
    
}
