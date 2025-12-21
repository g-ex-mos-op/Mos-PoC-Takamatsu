package jp.co.isid.mos.bird.communication.notificationreference.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import net.n2sm.search.client.api.SearchResultList;
//import com.ultraseek.xpa.search.SearchResultList;

/**
 * @author m.onodera
 *
 */
public class FullTextSearchTutatuDto implements FullTextSearchDto {

    /** FULLTEXTSEARCH 検索対象コレクション */
    public static final String TARGET_COLLECTION = "brdtutat";

    private String _searchWord;
    private String _searchKikanFrom;
    private String _searchKikanTo;
    private String _searchKikanTimeFrom;
    private String _searchKikanTimeTo;
    private SearchResultList _searchResultList = null;
    private List _fileAccessInfoEntityList = null;
    private List _matchingData = null;
    private String viewID;

    //検索文字列
    public void setSearchWord(final String searchWord) {
        this._searchWord = searchWord;
    }
    public String getSearchWord() {
        if (this._searchWord == null) {
            _searchWord = "";
        }
        return this._searchWord;
    }

    //
    public void setSearchResultList(final SearchResultList searchResultList) {
        this._searchResultList = searchResultList;
    }
    public SearchResultList getSearchResultList() {
        return this._searchResultList;
    }

    /**
     * 検索結果件数 取得処理
     * @return int 結果件数
     */
    public int getResultCount() {
        return getSearchResultList().size();
    }

    /**
     * 検索期間From 取得処理
     * @return
     */
    public String getSearchKikanFrom() {
        if (this._searchKikanFrom == null) {
            this._searchKikanFrom = "";
        }
        return this._searchKikanFrom;
    }
    /**
     * 検索期間From 設定処理
     */
    public void setSearchKikanFrom(final String searchKikanFrom) {
        this._searchKikanFrom = searchKikanFrom;
    }
    /**
     * 検索期間To 取得処理
     * @return
     */
    public String getSearchKikanTo() {
        if (this._searchKikanTo == null) {
            this._searchKikanTo = "";
        }
        return this._searchKikanTo;
    }
    /**
     * 検索期間To 設定処理
     */
    public void setSearchKikanTo(final String searchKikanTo) {
        this._searchKikanTo = searchKikanTo;
    }
    /**
     * 検索時間From 取得処理
     * (hhmmss形式で格納)
     * @return
     */
    public String getSearchKikanTimeFrom() {
        return this._searchKikanTimeFrom;
    }
    /**
     * 検索時間From 設定処理
     */
    public void setSearchKikanTimeFrom(final String searchKikanTimeFrom) {
        this._searchKikanTimeFrom = searchKikanTimeFrom;
    }
    /**
     * 検索時間To 取得処理
     * (hhmmss形式で格納)
     * @return
     */
    public String getSearchKikanTimeTo() {
        return _searchKikanTimeTo;
    }
    /**
     * 検索時間To 設定処理
     */
    public void setSearchKikanTimeTo(final String searchKikanTimeTo) {
        this._searchKikanTimeTo = searchKikanTimeTo;
    }

    /**
     * FullTextSearch検索 DB上のファイル情報設定処理
     * @param entity
     */
    public void setFileAccessInfoEntity(final List entity) {
        this._fileAccessInfoEntityList = entity;
    }
    /**
     * FullTextSearch検索 DB上のファイル情報取得処理
     * @return
     */
    public List getFileAccessInfoEntity() {
        return this._fileAccessInfoEntityList;
    }

    //マッチングデータ
    public void setMatchingData(final List data) {
        this._matchingData = data;
    }
    public List getMatchingData() {
        return this._matchingData;
    }

    //マッチングデータ件数
    public int getMatchingDataCount() {
        int dataCount = 0;
        if (getMatchingData() != null) {
            dataCount = getMatchingData().size();
        }
        return dataCount;
    }

    public String getViewID() {
        return viewID;
    }
    public void setViewID(String viewID) {
        this.viewID = viewID;
    }
    public String getCollectionId() {
    	return TARGET_COLLECTION;
    }
}
