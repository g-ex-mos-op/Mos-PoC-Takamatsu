package jp.co.isid.mos.bird.framework.dto;

import java.util.List;

//import com.ultraseek.xpa.search.SearchResultList;
import net.n2sm.search.client.api.SearchResultList;

/**
 * FullTextSearch検索処理用DTO
 * @author xnkusama
 */
public interface FullTextSearchDto {

	/**
	 * 検索対象コレクション 取得処理
	 */
	public String getCollectionId();
    /**
     * 検索文字列 取得処理
     * @return String
     */
    public String getSearchWord();
    /**
     * 検索文字列 設定処理
     * @param String searchWord
     */
    public void setSearchWord(final String searchWord);

    /**
     * 検索期間From 取得処理
     * (yyyyMMdd形式で格納)
     * @return
     */
    public String getSearchKikanFrom();
    /**
     * 検索期間From 設定処理
     */
    public void setSearchKikanFrom(final String searchKikanFrom);

    /**
     * 検索期間To 取得処理
     * (yyyyMMdd形式で格納)
     * @return
     */
    public String getSearchKikanTo();
    /**
     * 検索期間To 設定処理
     */
    public void setSearchKikanTo(final String searchKikanTo);

    /**
     * 検索時間From 取得処理
     * (hhmmss形式で格納)
     * @return
     */
    public String getSearchKikanTimeFrom();
    /**
     * 検索時間From 設定処理
     */
    public void setSearchKikanTimeFrom(final String searchKikanTimeFrom);

    /**
     * 検索時間To 取得処理
     * (hhmmss形式で格納)
     * @return
     */
    public String getSearchKikanTimeTo();
    /**
     * 検索時間To 設定処理
     */
    public void setSearchKikanTimeTo(final String searchKikanTimeTo);

    /**
     * 検索結果取得処理
     * @return SearchResultList
     */
    public SearchResultList getSearchResultList();
    /**
     * 検索結果設定処理
     * @param searchResultList
     */
    public void setSearchResultList(final SearchResultList searchResultList);

    /**
     * 検索結果件数 取得処理
     * @return
     */
    public int getResultCount();

    /**
     * FullTextSearch検索 DB上のファイル情報設定処理
     * @param FileAccessInfoEntityを格納しているList
     */
    public void setFileAccessInfoEntity(final List entity);
    /**
     * FullTextSearch検索 DB上のファイル情報取得処理
     * @return FileAccessInfoEntityを格納しているList
     */
    public List getFileAccessInfoEntity();

    /**
     * FullTextSearch検索とDB検索のマッチング結果設定処理
     * @param data
     */
    public void setMatchingData(final List data);
    /**
     * FullTextSearch検索とDB検索のマッチング結果取得処理
     * @return
     */
    public List getMatchingData();
}
