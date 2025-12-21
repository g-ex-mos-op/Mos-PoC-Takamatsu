package jp.co.isid.mos.bird.framework.dto;

/**
 * 例外情報用共通DTO
 * 
 * @author xkinu(ASPAC)
 */
public class ErrHtmlElementDto {
    /* HTML要素名称 */
    private String htmlElementName;
    /* HTML要素名称 */
    private String htmlTabName;
    /* HTML要素インデックス */
    private int htmlElementIndex;
    /* 例外発生判断フラグ */
    private boolean exceptinFlg = false;
	
	/**
	 * HTML要素名称取得処理
	 * 
	 * @return htmlElementName を戻します。
	 */
	public String getHtmlElementName() {
		return this.htmlElementName;
	}
	
	/**
	 * 例外発生判断値取得処理
	 * 
	 * @return exceptinFlg true 例外発生、false 例外無し
	 */
	public boolean isException() {
		return this.exceptinFlg;
	}
	/**
	 * HTML要素情報設定処理
	 * 
	 * @param name
	 */
	public void setHtmlElementName(final String name) {
		this.htmlElementName = name;
	}
	/**
	 * HTML要素インデックス情報設定処理
	 * 
	 * @param index　インデックス
	 */
	public void setHtmlElementIndex(final int index) {
		this.htmlElementIndex = index;
	}
	/**
	 * HTML要素インデックス取得処理
	 * 
	 * @return htmlElementIndex を戻します。
	 */
	public int getHtmlElementIndex() {
		return this.htmlElementIndex;
	}
	/**
	 * 例外発生判断フラグ設定処理
	 * 
	 * @param name
	 * @param index
	 */
	public void setExceptionFlg(final boolean flg) {
		this.exceptinFlg = flg;
	}
    /**
     * クリア処理
     */
    public void clear() {
        setHtmlElementName("");
        setHtmlElementIndex(0);
        setExceptionFlg(false);
    }
	/**
	 * HTMLタブ名称情報設定処理
	 * 
	 * @param name
	 * @param index
	 */
	public void setHtmlTabName(final String name) {
		this.htmlTabName = name;
	}
	/**
	 * HTMLタブ名称情報設定処理
	 * @return String HTMLタブ名称
	 */
	public String getHtmlTabName() {
		return this.htmlTabName;
	}

}
