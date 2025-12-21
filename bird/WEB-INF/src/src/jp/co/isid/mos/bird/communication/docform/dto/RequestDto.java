package jp.co.isid.mos.bird.communication.docform.dto;

import java.util.List;

import jp.co.isid.mos.bird.communication.docform.code.SearchField;


/**
 * 文書＆フォームDTO
 * @author xkinu
 */
public class RequestDto {

    /**************
     *  検索条件  *
     **************/
    /*
     * 検索対象：対象(範囲)
     * 対象プルダウンの選択された値
     */
    private String cateSearchField= SearchField.FIELD_DOC;
    /*
     * 検索対象：対象(範囲)
     * 対象プルダウンの選択された値
     */
    private String buttonSearchField= SearchField.FIELD_DOCFORM;
    /*
     * 検索対象：対象(範囲)
     * 対象プルダウンの選択された値
     */
    private String searchField= SearchField.FIELD_DOCFORM;
    // 検索条件：全文検索文字列
    private String searchWord;
    // 検索条件：タイトル
    private String searchTitle;
    // 検索条件：カテゴリID
    private String searchCateId;
    /* 検索条件：対象タブ
     * 情報種別又はサブカテゴリIDが設定されます。*/
    private String targetTab;
    /* 
     * ポータルリンク指定サブカテゴリ
     *  結果画面でサブカテゴリを選択した時とは、処理の流れが異なるため
     *  対象タブプロパティとは別で
     */
    private String portalLinkSubCateId;
    // 検索対象情報種別
    private int targetInfoShu;
    // 検索モード（１：カテゴリ検索 ２：タイトル検索）
    private int searchMode = 1;

    private int currentPageNumber;

	/**
	 * @return currentPageNumber を戻します。
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * @param currentPageNumber を クラス変数currentPageNumberへ設定します。
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	/**
	 * @return searchCateId を戻します。
	 */
	public String getSearchCateId() {
		return searchCateId;
	}

	/**
	 * @param searchCateId を クラス変数searchCateIdへ設定します。
	 */
	public void setSearchCateId(String searchCateId) {
		this.searchCateId = searchCateId;
	}

	/**
	 * @return searchMode を戻します。
	 */
	public int getSearchMode() {
		return searchMode;
	}

	/**
	 * @param searchMode を クラス変数searchModeへ設定します。
	 */
	public void setSearchMode(int searchMode) {
		this.searchMode = searchMode;
	}

	/**
	 * @return searchTitle を戻します。
	 */
	public String getSearchTitle() {
		return searchTitle;
	}

	/**
	 * @param searchTitle を クラス変数searchTitleへ設定します。
	 */
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	/**
	 * 対象(範囲)取得処理
	 * 
	 * @return searchField を戻します。
	 * {文書・フォーム：1、文書：03、フォーム：04}
	 */
	public String getSearchField() {
		return searchField;
	}

	/**
	 * 対象(範囲)取得処理
	 * @param searchField を クラス変数searchFieldへ設定します。
	 * {文書・フォーム：1、文書：03、フォーム：04}
	 */
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	/**
	 * @return searchWord を戻します。
	 */
	public String getSearchWord() {
		return searchWord;
	}

	/**
	 * @param searchWord を クラス変数searchWordへ設定します。
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	/**
	 * @return targetInfoShu を戻します。
	 */
	public int getTargetInfoShu() {
		return targetInfoShu;
	}

	/**
	 * @param targetInfoShu を クラス変数targetInfoShuへ設定します。
	 */
	public void setTargetInfoShu(int targetInfoShu) {
		this.targetInfoShu = targetInfoShu;
	}

	/**
	 * @return targetTab を戻します。
	 */
	public String getTargetTab() {
		return targetTab;
	}

	/**
	 * @param targetTab を クラス変数targetTabへ設定します。
	 */
	public void setTargetTab(String targetTab) {
		this.targetTab = targetTab;
	}
	/**
	 * 対象プルダウンリスト取得処理
	 * 
	 * @return
	 */
	public List getListSearchField() {
		return SearchField.getPullDownList();
	}

	/**
	 * @return buttonSearchField を戻します。
	 */
	public String getButtonSearchField() {
		return buttonSearchField;
	}

	/**
	 * @param buttonSearchField を クラス変数buttonSearchFieldへ設定します。
	 */
	public void setButtonSearchField(String buttonSearchField) {
		this.buttonSearchField = buttonSearchField;
	}

	/**
	 * @return cateSearchField を戻します。
	 */
	public String getCateSearchField() {
		return cateSearchField;
	}

	/**
	 * @param cateSearchField を クラス変数cateSearchFieldへ設定します。
	 */
	public void setCateSearchField(String cateSearchField) {
		this.cateSearchField = cateSearchField;
	}
	/**
	 * カテゴリーフィールドの検索対象が”文書”か否かの判断処理
	 * @return
	 */
	public boolean isCateSearchFieldDoc() {
		return SearchField.FIELD_DOC.equals(getCateSearchField());
	}
	public List getListCateSearchField() {
		return SearchField.getPullDownListCate();
	}

    public String getPortalLinkSubCateId() {
        return portalLinkSubCateId;
    }

    public void setPortalLinkSubCateId(String portalLinkSubCateId) {
        this.portalLinkSubCateId = portalLinkSubCateId;
    }
}