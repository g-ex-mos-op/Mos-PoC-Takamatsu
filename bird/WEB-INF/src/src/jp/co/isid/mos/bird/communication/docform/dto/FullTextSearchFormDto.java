/**
 *
 */
package jp.co.isid.mos.bird.communication.docform.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import net.n2sm.search.client.api.SearchResultList;

/**
 * フォーム用FullTextSearch検索DTO
 *
 * 作成日:2008/12/05
 * @author xkinu
 *
 */
public class FullTextSearchFormDto implements FullTextSearchDto {
    /** FULLTEXTSEARCH 検索対象コレクション */
    public static final String TARGET_COLLECTION = "brdform";
    private String searchWord;
    private String searchKikanFrom;
    private String searchKikanTo;
    private String searchKikanTimeFrom;
    private String searchKikanTimeTo;
    private SearchResultList searchResultList = null;
    private List fileAccessInfoEntity = null;
    private List matchingData = null;

	/*
	 * 検索対象コレクションID取得処理
	 *
	 * (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getCollectionId()
	 */
	public String getCollectionId() {
		// TODO 自動生成されたメソッド・スタブ
		return TARGET_COLLECTION;
	}

	/**
	 * @return fileAccessInfoEntity を戻します。
	 */
	public List getFileAccessInfoEntity() {
		return fileAccessInfoEntity;
	}

	/**
	 * @param fileAccessInfoEntity を クラス変数fileAccessInfoEntityへ設定します。
	 */
	public void setFileAccessInfoEntity(List fileAccessInfoEntity) {
		this.fileAccessInfoEntity = fileAccessInfoEntity;
	}

	/**
	 * @return matchingData を戻します。
	 */
	public List getMatchingData() {
		return matchingData;
	}

	/**
	 * @param matchingData を クラス変数matchingDataへ設定します。
	 */
	public void setMatchingData(List matchingData) {
		this.matchingData = matchingData;
	}

	/**
	 * @return searchKikanFrom を戻します。
	 */
	public String getSearchKikanFrom() {
		return searchKikanFrom;
	}

	/**
	 * @param searchKikanFrom を クラス変数searchKikanFromへ設定します。
	 */
	public void setSearchKikanFrom(String searchKikanFrom) {
		this.searchKikanFrom = searchKikanFrom;
	}

	/**
	 * @return searchKikanTimeFrom を戻します。
	 */
	public String getSearchKikanTimeFrom() {
		return searchKikanTimeFrom;
	}

	/**
	 * @param searchKikanTimeFrom を クラス変数searchKikanTimeFromへ設定します。
	 */
	public void setSearchKikanTimeFrom(String searchKikanTimeFrom) {
		this.searchKikanTimeFrom = searchKikanTimeFrom;
	}

	/**
	 * @return searchKikanTimeTo を戻します。
	 */
	public String getSearchKikanTimeTo() {
		return searchKikanTimeTo;
	}

	/**
	 * @param searchKikanTimeTo を クラス変数searchKikanTimeToへ設定します。
	 */
	public void setSearchKikanTimeTo(String searchKikanTimeTo) {
		this.searchKikanTimeTo = searchKikanTimeTo;
	}

	/**
	 * @return searchKikanTo を戻します。
	 */
	public String getSearchKikanTo() {
		return searchKikanTo;
	}

	/**
	 * @param searchKikanTo を クラス変数searchKikanToへ設定します。
	 */
	public void setSearchKikanTo(String searchKikanTo) {
		this.searchKikanTo = searchKikanTo;
	}

	/**
	 * @return searchResultList を戻します。
	 */
	public SearchResultList getSearchResultList() {
		return searchResultList;
	}

	/**
	 * @param searchResultList を クラス変数searchResultListへ設定します。
	 */
	public void setSearchResultList(SearchResultList searchResultList) {
		this.searchResultList = searchResultList;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getResultCount()
	 */
	public int getResultCount() {
		// TODO 自動生成されたメソッド・スタブ
		return getSearchResultList().size();
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
}
