package jp.co.isid.mos.bird.entry.nationalviewlist.entity;

import jp.co.isid.mos.bird.common.util.CommonUtil;

public class UINatiViewEntrySelectInfo {
    
    public static final String TABLE = "BR53ENTS";
    
    public static final String selectionKbn_COLUMN = "SELECTION_KBN";
    
    public static final String selectionIndex_COLUMN = "SELECTION_INDEX";
    
    public static final String selectionName_COLUMN = "SELECTION_NAME";
    
    /**
     * セレクション区分
     */
    private String selectionKbn;
    
    /**
     * セレクションインデックス
     */
    private String selectionIndex;
    
    /**
     * セレクション名称
     */
    private String selectionName;
    /**
     * 非表示フラグ
     */
    private boolean disabled = false;
    /**
     * セレクション区分を取得します。
     * @return セレクション区分
     */
    public String getSelectionKbn() {
        return selectionKbn;
    }
    /**
     * セレクション区分を設定します。
     * @param selectionKbn セレクション区分
     */
    public void setSelectionKbn(String selectionKbn) {
        this.selectionKbn = selectionKbn;
    }
    
    /**
     * セレクションインデックスを取得します。
     * @return セレクションインデックス
     */
    public String getSelectionIndex() {
        return selectionIndex;
    }
    /**
     * セレクションインデックスを設定します。
     * @param selectionIndex セレクションインデックス
     */
    public void setSelectionIndex(String selectionIndex) {
        this.selectionIndex = selectionIndex;
    }
    
    /**
     * セレクション名称を取得します。
     * @return セレクション名称
     */
    public String getSelectionName() {
        return selectionName;
    }
    /**
     * セレクション名称を設定します。
     * @param selectionName セレクション名称
     */
    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }
    /**
     * 空判断処理
     * 
     * @return ture:登録有り false:登録無し
     */
    public boolean isEmptySelection() {
    	return CommonUtil.isNull(selectionName);
    }
    public void setEmptySelection(boolean is) {
    	
    }
	/**
	 * @return クラス変数disabled を戻します。
	 */
	public boolean isDisabled() {
		return disabled;
	}
	/**
	 * @param disabled を クラス変数disabledへ設定します。
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
