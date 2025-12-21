/**
 *
 */
package jp.co.isid.mos.bird.communication.docform.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import net.n2sm.search.client.api.SearchResultList;

//import com.ultraseek.xpa.search.SearchResultList;

/**
 * 文書用FullTextSearch検索DTO
 *
 * 作成日:2008/12/05
 * @author xkinu
 *
 */
public class FullTextSearchDocDto implements FullTextSearchDto {
    /** FULLTEXTSEARCH 検索対象コレクション */
    public static final String TARGET_COLLECTION = "brdbunsh";
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

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getSearchWord()
	 */
	public String getSearchWord() {
		// TODO 自動生成されたメソッド・スタブ
		return searchWord;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#setSearchWord(java.lang.String)
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getSearchKikanFrom()
	 */
	public String getSearchKikanFrom() {
		// TODO 自動生成されたメソッド・スタブ
		return searchKikanFrom;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#setSearchKikanFrom(java.lang.String)
	 */
	public void setSearchKikanFrom(String kikanFrom) {
		this.searchKikanFrom = kikanFrom;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getSearchKikanTo()
	 */
	public String getSearchKikanTo() {
		// TODO 自動生成されたメソッド・スタブ
		return searchKikanTo;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#setSearchKikanTo(java.lang.String)
	 */
	public void setSearchKikanTo(String kikanTo) {
		this.searchKikanTo = kikanTo;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getSearchKikanTimeFrom()
	 */
	public String getSearchKikanTimeFrom() {
		// TODO 自動生成されたメソッド・スタブ
		return searchKikanTimeFrom;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#setSearchKikanTimeFrom(java.lang.String)
	 */
	public void setSearchKikanTimeFrom(String timeFrom) {
		this.searchKikanTimeFrom = timeFrom;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getSearchKikanTimeTo()
	 */
	public String getSearchKikanTimeTo() {
		// TODO 自動生成されたメソッド・スタブ
		return searchKikanTimeTo;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#setSearchKikanTimeTo(java.lang.String)
	 */
	public void setSearchKikanTimeTo(String timeTo) {
		this.searchKikanTimeTo = timeTo;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getSearchResultList()
	 */
	public SearchResultList getSearchResultList() {
		return searchResultList;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#setSearchResultList(com.ultraseek.xpa.search.SearchResultList)
	 */
	public void setSearchResultList(SearchResultList list) {
		this.searchResultList = list;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.dto.FullTextSearchDto#getResultCount()
	 */
	public int getResultCount() {
		// TODO 自動生成されたメソッド・スタブ
		return getSearchResultList().size();
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

}
