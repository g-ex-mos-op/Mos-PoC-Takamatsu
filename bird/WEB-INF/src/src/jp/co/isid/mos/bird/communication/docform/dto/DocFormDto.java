package jp.co.isid.mos.bird.communication.docform.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.communication.docform.code.SearchField;
import jp.co.isid.mos.bird.communication.docform.common.DocFormCont;
import jp.co.isid.mos.bird.communication.docform.entity.UIViewDocFormInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 文書＆フォームDTO
 * @author xnkusama
 */
public class DocFormDto implements DownloadDto {

    /**************
     *  検索条件  *
     **************/
    // 検索条件：カテゴリー
    private String searchedCateId;
    // 検索条件：全文検索文字列
    private String searchedWord;
    // 検索条件：全文検索文字列
    private String searchedTitle;
    // 検索済みの対象
    private String searchedField;
    // 選択されている情報種別タブ
    private String targetTab;
    // 検索モード（１：カテゴリ検索 ２：タイトル検索）
    private int searchMode = 1;
    
    
    /**************
     *  検索結果  *
     **************/
    // 検索結果：文書
    private List listDocForm = new ArrayList(0);
    // サブカテゴリリスト
    private List listSubCate = new ArrayList(0);
    //画面表示用サブカテゴリ情報
    private List listTabSubCate = new ArrayList(0);
    // 文書データがあるか（true：存在する）
    private boolean existBunshoData = false;
    // フォームデータがあるか（true：存在する）
    private boolean existFormData = false;
    // カテゴリ検索 選択カテゴリ名
    private String viewCateName;
    // 公開対象所属
    private String viewShozoku;
    
    
    /******************
     * ページ制御関連 *
     ******************/
    private int currentPageNumber;
    //private int maxPageCount;
    private int count;


    /************
     * 詳細表示 *
     ************/
    // 詳細表示Entity
    private UIViewDocFormInfo entityViewDetail;
    // 一覧選択Index
    private int indexEntityViewDetail;
    /********************
     * ダウンロード関連 *
     ********************/
    // ダウンロード対象モード（１：文書ファイル　２：添付ファイル　３：関連文書）
    private int downloadMode;
    // メインファイル ダウンロードインデックス
    private int downloadFileIndex;
    // 関連文書ダウンロードIndex
    private int viewSelectedAttachFileIndex;

    /****************
     *    共通      *
     ****************/
    // 検索対象情報種別リスト
    private List listInfoShu;

    /****************
     *  ナビエリア  *
     ****************/
    // ナビエリア 選択情報種別
    private String naviSelectedInfoShu;
    // カテゴリList保持Map
    private Map mapListCategory = new HashMap();

    /**
     * @return viewListFlg を戻します。
     */
    public boolean isViewListFlg() {
        return getListDocForm() != null && getListDocForm().size() > 0;
    }
    /**
     * @return listBunsho を戻します。
     */
    public List getListDocForm() {
        return listDocForm;
    }
    /**
     * @param listBunsho listBunsho を設定。
     */
    public void setListDocForm(List listBunsho) {
        this.listDocForm = listBunsho;
    }

    public int getFirstPageNumber() {
        return 1;
    }
    public int getLastPageNumber() {
        return (getCount() == 0) ? 1 : (getCount() - 1) / getMaxPageCount() + 1;
    }
    public int getPreviousPageNumber() {
        return (isExistPreviousPage()) ? getCurrentPageNumber() - 1 : getCurrentPageNumber();
    }
    public int getNextPageNumber() {
        return (isExistNextPage()) ? getCurrentPageNumber() + 1 : getCurrentPageNumber();
    }
    public int getPageFirstRecordNumber() {
        return (getCurrentPageNumber() - 1) * getMaxPageCount();
    }
    public boolean isFirstPage() {
        return getCurrentPageNumber() == getFirstPageNumber();
    }
    public boolean isLastPage() {
        return getCurrentPageNumber() == getLastPageNumber();
    }
    public boolean isExistPreviousPage() {
        return getCurrentPageNumber() > getFirstPageNumber();
    }
    public boolean isExistNextPage() {
        return getCurrentPageNumber() < getLastPageNumber();
    }
    public int getCurrentPageNumber() {
        return currentPageNumber;
    }
    public void setCurrentPageNumber(int currentPageNumber) {
        if (currentPageNumber >= getFirstPageNumber() && currentPageNumber <= getLastPageNumber()) {
            this.currentPageNumber = currentPageNumber;
        }
    }
    public int getMaxPageCount() {
        return DocFormCont.PAGE_MAX_SIZE;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }
    /**
     * @return listSubCate を戻します。
     */
    public List getListSubCate() {
        return listSubCate;
    }
    /**
     * @param listSubCate listSubCate を設定。
     */
    public void setListSubCate(List listSubCate) {
        this.listSubCate = listSubCate;
        setListTabSubCate(listSubCate);
    }
    /**
     * 画面表示用サブカテゴリータブ情報
     * @param listSubCate
     */
    private void setListTabSubCate(List listSubCate) {
    	if(listSubCate == null) {
    		return;
    	}
    	int ino = 0;
    	listTabSubCate = new ArrayList(0);
    	while (ino<listSubCate.size()) {
    		List listRow = new ArrayList(0);
    		for (int i=0; i<4 && ino<getListSubCate().size(); i++) {
    			listRow.add(getListSubCate().get(ino));
    			ino++;
    		}
    		while(listRow.size()<4) {
    			listRow.add(null);
    		}
    		listTabSubCate.add(listRow);
    	}
    }
    /**
     * 画面表示用サブカテゴリータブ情報
     * 
     * 1インデックスにつき１行分(4個のサブカテゴリ)の
     * 情報を保持しています。
     * @return
     */
    public List getListTabSubCate() {
    	return listTabSubCate;
    }
    public int getSubCateRowCnt() {
    	return getListTabSubCate().size();
    }
    /**
     * @return entityViewDetail を戻します。
     */
    public UIViewDocFormInfo getEntityViewDetail() {
        return entityViewDetail;
    }
    /**
     * @param entityViewDetail entityViewDetail を設定。
     */
    public void setEentityViewDetail(UIViewDocFormInfo entityViewDetail) {
        this.entityViewDetail = entityViewDetail;
    }
    /**
     * @return indexEntityViewDetail を戻します。
     */
    public int getIndexEntityViewDetail() {
        return indexEntityViewDetail;
    }
    /**
     * @param indexEntityViewDetail indexEntityViewDetail を設定。
     */
    public void setIndexEntityViewDetail(int indexEntityViewDetail) {
        this.indexEntityViewDetail = indexEntityViewDetail;
    }
    /**
     * @param entityViewDetail entityViewDetail を設定。
     */
    public void setEntityViewDetail(UIViewDocFormInfo entityViewDetail) {
        this.entityViewDetail = entityViewDetail;
    }
    /**
     * @param downloadMode downloadMode を設定。
     */
    public void setDownloadMode(int downloadMode) {
        this.downloadMode = downloadMode;
    }
    /**
     * @return downloadMode を戻します。
     */
    public int getDownloadMode() {
        return downloadMode;
    }
    /**
     * @return searchMode を戻します。
     */
    public int getSearchMode() {
        return searchMode;
    }
    /**
     * @param searchMode searchMode を設定。
     */
    public void setSearchMode(int searchMode) {
        this.searchMode = searchMode;
    }
    
    public void clearDto() {
        setListDocForm(new ArrayList(0));
        setListSubCate(new ArrayList(0));
        setEntityViewDetail(null);
        setSearchedField(SearchField.FIELD_DOCFORM);
        setTargetTab(SearchField.FIELD_DOCFORM);
        setExistBunshoData(false);
        setExistFormData(false);
    }
    
    /**
     * ダウンロードファイルのプルダウンのインデックスを取得します。
     * @return ダウンロードファイルのプルダウンのインデックス 
     */
    public int getDownloadFileIndex() {
        return downloadFileIndex;
    }
    /**
     * ダウンロードファイルのプルダウンのインデックスを設定します。
     * @param downloadFileIndex ダウンロードファイルのプルダウンのインデックス
     */
    public void setDownloadFileIndex(int downloadFileIndex) {
        this.downloadFileIndex = downloadFileIndex;
    }
    public String getViewCateName() {
        return viewCateName;
    }
    public void setViewCateName(String viewCateName) {
        this.viewCateName = viewCateName;
    }
    public int getViewSelectedAttachFileIndex() {
        return viewSelectedAttachFileIndex;
    }
    public void setViewSelectedAttachFileIndex(int viewSelectedAttachFileIndex) {
        this.viewSelectedAttachFileIndex = viewSelectedAttachFileIndex;
    }
    public String getViewShozoku() {
        return viewShozoku;
    }
    public void setViewShozoku(String viewShozoku) {
        this.viewShozoku = viewShozoku;
    }
    public String getNaviSelectedInfoShu() {
        return naviSelectedInfoShu;
    }
    public void setNaviSelectedInfoShu(String naviSelectedInfoShu) {
        this.naviSelectedInfoShu = naviSelectedInfoShu;
    }
    public List getListInfoShu() {
        return listInfoShu;
    }
    public void setListInfoShu(List listInfoShu) {
        this.listInfoShu = listInfoShu;
    }
    /**
     * 文書カテゴリ一覧List
     * @return
     */
    public List getListBunshoCategory() {
        return getListCategory(DocFormCont.INFO_SHU_BUNSHO);
    }
    /**
     * フォームカテゴリ一覧List
     * @return
     */
    public List getListFormCategory() {
        return getListCategory(DocFormCont.INFO_SHU_FORM);
    }
    public void setListBunshoCategory(List listBunshoCategory) {
    }
    public void setListFormCategory(List listFormCategory) {
    }
    public String getSearchedField() {
        return searchedField;
    }
    public boolean isExistBunshoData() {
        return existBunshoData;
    }
    public void setExistBunshoData(boolean existBunshoData) {
        this.existBunshoData = existBunshoData;
    }
    public boolean isExistFormData() {
        return existFormData;
    }
    public void setExistFormData(boolean existFormData) {
        this.existFormData = existFormData;
    }
	/**
	 * @return searchedTitle を戻します。
	 */
	public String getSearchedTitle() {
		return searchedTitle;
	}
	/**
	 * @param searchedTitle を クラス変数searchedTitleへ設定します。
	 */
	public void setSearchedTitle(String searchedTitle) {
		this.searchedTitle = searchedTitle;
	}
	/**
	 * @return searchedWord を戻します。
	 */
	public String getSearchedWord() {
		return searchedWord;
	}
	/**
	 * @param searchedWord を クラス変数searchedWordへ設定します。
	 */
	public void setSearchedWord(String searchedWord) {
		this.searchedWord = searchedWord;
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
	 * @param searchedField を クラス変数searchedFieldへ設定します。
	 */
	public void setSearchedField(String searchedField) {
		this.searchedField = searchedField;
	}
	/**
	 * @return searchedCateId を戻します。
	 */
	public String getSearchedCateId() {
		return searchedCateId;
	}
	/**
	 * @param searchedCateId を クラス変数searchedCateIdへ設定します。
	 */
	public void setSearchedCateId(String searchedCateId) {
		this.searchedCateId = searchedCateId;
	}
	/**
	 * 指定情報種別カテゴリーリスト設定処理
	 * @param infoShu
	 * @param listCategory
	 */
	public void setListCategory(String infoShu, List listCategory) {
		mapListCategory.put(infoShu, listCategory);
	}
	/**
	 * 指定情報種別カテゴリーリスト取得処理
	 * 
	 * @param infoShu
	 * @return
	 */
	public List getListCategory(String infoShu) {
		if(mapListCategory.containsKey(infoShu)
				&& mapListCategory.get(infoShu) != null) 
		{
			return (List)mapListCategory.get(infoShu);
		}
		return new ArrayList(0);
	}
}