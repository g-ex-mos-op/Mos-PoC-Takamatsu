/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.documentsearch.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * 関連文書検索処理Dto
 * @author m.onodera
 */
public class DocumentSearchConditionDto implements DownloadDto {
	
	private static final int DEFAULT_CURRENT_PAGE_NUMBER = 1;
	private static final int DEFAULT_MAX_PAGE_COUNT = 30;

    /* タイプ */
    private String infoShu;
    /* カテゴリID */
    private String cateId;
    /* サブカテゴリID */
    private String subCateId;
    /* タイトル */
    private String title;
    /* 対象年月 */
    private String date;

    /* タイプ(SQL検索用) */
    private String selectInfoShu;
    /* カテゴリID(SQL検索用) */
    private String selectCateId;
    /* サブカテゴリID(SQL検索用) */
    private String selectSubCateId;
    /* タイトル(SQL検索用) */
    private String selectTitle;
    /* 対象年月(SQL検索用) */
    private String selectDate;

	private int currentPageNumber;
	private int maxPageCount;
    
    /* タイプ一覧 */
    private List infoShuList;
    /* カテゴリ一覧 */
    private List cateList;
    /* サブカテゴリ一覧 */
    private List subCateList;
    /* 検索結果関連文書リスト */
    private List documentList;
    /* 選択関連文書Tempリスト */
    private List checkList;
    /* saveMap */
    private HashMap saveMap;
    private int downloadIndex;
    /* 全検索結果関連文書リスト */
    private List listAllDocument;
    /**
     * 条件項目：対象年月リスト
     */
    private List targetDateList;
    /**
     * ダウンロードIndexの設定
     * @return getDownloadIndex を戻します。
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    /**
     * ダウンロードIndexの設定
     * @param downloadIndex downloadIndex を設定。
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * タイプの設定
     * @return infoShu を戻します。
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * タイプの設定
     * @param infoShu infoShu を設定。
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }
    /**
     * カテゴリIDの設定
     * @return cateId を戻します。
     */
    public String getCateId() {
        return cateId;
    }
    /**
     * カテゴリIDの設定
     * @param cateId cateId を設定。
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    /**
     * サブカテゴリIDの設定
     * @return subCateId を戻します。
     */
    public String getSubCateId() {
        return subCateId;
    }
    /**
     * サブカテゴリIDの設定
     * @param subCateId subCateId を設定。
     */
    public void setSubCateId(String subCateId) {
        this.subCateId = subCateId;
    }
    /**
     * タイトルの設定
     * @return title を戻します。
     */
    public String getTitle() {
        return title;
    }
    /**
     * タイトルの設定
     * @param title title を設定。
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 対象年月の設定
     * @return date を戻します。
     */
    public String getDate() {
        return date;
    }
    /**
     * 対象年月の設定
     * @param date date を設定。
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * タイプ(SQL検索用)の設定
     * @return selectInfoShu を戻します。
     */
    public String getSelectInfoShu() {
        return selectInfoShu;
    }
    /**
     * タイプ(SQL検索用)の設定
     * @param selectInfoShu selectInfoShu を設定。
     */
    public void setSelectInfoShu(String selectInfoShu) {
        this.selectInfoShu = selectInfoShu;
    }
    /**
     * カテゴリID(SQL検索用)の設定
     * @return selectCateId を戻します。
     */
    public String getSelectCateId() {
        return selectCateId;
    }
    /**
     * カテゴリID(SQL検索用)の設定
     * @param selectCateId selectCateId を設定。
     */
    public void setSelectCateId(String selectCateId) {
        this.selectCateId = selectCateId;
    }
    /**
     * サブカテゴリID(SQL検索用)の設定
     * @return selectSubCateId を戻します。
     */
    public String getSelectSubCateId() {
        return selectSubCateId;
    }
    /**
     * サブカテゴリID(SQL検索用)の設定
     * @param selectSubCateId selectSubCateId を設定。
     */
    public void setSelectSubCateId(String selectSubCateId) {
        this.selectSubCateId = selectSubCateId;
    }
    /**
     * タイトル(SQL検索用)の設定
     * @return title を戻します。
     */
    public String getSelectTitle() {
        return selectTitle;
    }
    /**
     * タイトル(SQL検索用)の設定
     * @param selectTitle selectTitle を設定。
     */
    public void setSelectTitle(String selectTitle) {
        this.selectTitle = selectTitle;
    }
    /**
     * 対象年月(SQL検索用)の設定
     * @return selectDate を戻します。
     */
    public String getSelectDate() {
        return selectDate;
    }
    /**
     * 対象年月(SQL検索用)の設定
     * @param selectDate selectDate を設定。
     */
    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }
    
//  /**
//  * タイプ名称の設定
//  * @return infoShuName を戻します。
//  */
// public String getInfoShuName() {
//     return infoShuName;
// }
// /**
//  * タイプ名称の設定
//  * @param infoShuName infoShuName を設定。
//  */
// public void setInfoShuName(String infoShuName) {
//     this.infoShuName = infoShuName;
// }
//    /**
//     * カテゴリ名称の設定
//     * @return cateId を戻します。
//     */
//    public String getCateName() {
//        return cateId;
//    }
//    /**
//     * カテゴリ名称の設定
//     * @param cateId cateId を設定。
//     */
//    public void setCateName(String cateName) {
//        this.cateName = cateName;
//    }
//    /**
//     * サブカテゴリ名称の設定
//     * @return subCateId を戻します。
//     */
//    public String getSubCateName() {
//        return subCateName;
//    }
//    /**
//     * サブカテゴリ名称の設定
//     * @param subCateId subCateId を設定。
//     */
//    public void setSubCateName(String subCateName) {
//        this.subCateName = subCateName;
//    }
    
    /**
     * タイプ一覧の設定
     * @return infoShuList を戻します。
     */
    public List getInfoShuList() {
        return infoShuList;
    }
    /**
     * タイプ一覧の設定
     * @param categoryList categoryList を設定。
     */
    public void setInfoShuList(List infoShuList) {
        this.infoShuList = infoShuList;
    }
    /**
     * カテゴリ一覧の設定
     * @return categoryList を戻します。
     */
    public List getCateList() {
        return cateList;
    }
    /**
     * カテゴリ一覧の設定
     * @param categoryList categoryList を設定。
     */
    public void setCateList(List cateList) {
        this.cateList = cateList;
    }
    /**
     * サブカテゴリ一覧の設定
     * @return subCateList を戻します。
     */
    public List getSubCateList() {
        return subCateList;
    }
    /**
     * サブカテゴリ一覧の設定
     * @param subCateList subCateList を設定。
     */
    public void setSubCateList(List subCateList) {
        this.subCateList = subCateList;
    }
    /**
     * 関連文書リストサイズの取得
     * @return documentListSize を戻します。
     */
    public int getDocumentListSize() {
    	if(getDocumentList() == null) {
    		return 0;
    	}
        return getDocumentList().size();
    }
    /**
     * 選択関連文書リストの設定
     * @return documentList を戻します。
     */
    public List getDocumentList() {
        return documentList;
    }
    /**
     * 選択関連文書リストの設定
     * @param documentList documentList を設定。
     */
    public void setDocumentList(List documentList) {
        this.documentList = documentList;
    }
    /**
     * 全検索結果関連文書リストから
     * 指定ページ番号の表示用関連文書リスト設定処理
     * 
     * @param currentPageNumber
     */
    public void setDispDocumentList(int currentPageNumber) {
    	List listDispData = new ArrayList();
    	Integer key = new Integer(currentPageNumber);
    	if(getSaveMap().containsKey(key)) {
    		listDispData = (List)getSaveMap().get(key);
        	//表示用関連文書リストの設定
        	setDocumentList(listDispData);
    	}
    	else {
	    	//1.DTO【関連文書検索処理】.表示ページ番号へ引数.currentPageNumberを設定する。
	    	setCurrentPageNumber(currentPageNumber);
	    	int offset = getPageFirstRecordNumber();
	    	int rownoTo = offset + DEFAULT_MAX_PAGE_COUNT;
	    	for (int i=offset; i<getListAllDocument().size(); i++) {
	    		 UIDocSearch entity = (UIDocSearch)getListAllDocument().get(i);
	    		listDispData.add(entity);
	    		if(i>=rownoTo) {
	    			break;
	    		}
	    	}
	    	//表示用関連文書リストの設定
	    	setDocumentList(listDispData);
	    	//関連文書状態保存(開いたページ全てMapに保存)
	        saveState();
    	}
    }
	/**
	 * 関連文書状態保存(開いたページ全てMapに保存)
	 * key  :ページ番号
	 * value:ページ内ListData全件
	 * 
	 * @param roleSearchRoleListDto 関連文書チェック状態保存
	 */
	public void saveState() {
	    String key = Integer.toString(getCurrentPageNumber());
	    Object value = getDocumentList();
	    getSaveMap().put(key, value);
	}

    /**
     * 選択関連文書Tempリストの設定
     * @return checkList を戻します。
     */
    public List getCheckList() {
        if(checkList == null){
            checkList = new ArrayList();
        }
        return checkList;
    }
    /**
     * 選択関連文書Tempリストの設定
     * @param checkList checkList を設定。
     */
    public void setCheckList(List checkList) {
        this.checkList = checkList;
    }
    /**
     * 表示済みページ検索結果の設定
     * @return saveMap を戻します。
     */
    public HashMap getSaveMap() {
        if(saveMap == null){
            saveMap = new HashMap();
        }
        return saveMap;
    }
    /**
     * 表示済みページ検索結果の設定
     * @param saveMap saveMap を設定。
     */
    public void setSaveMap(HashMap saveMap) {
        this.saveMap = saveMap;
    }
    
    /**
     * クリア処理<br>
     */
    public void clear() {
        this.setInfoShu(null);
        this.setCateList(null);
        this.setCateId(null);
        this.setSubCateList(null);
        this.setSubCateId(null);
        this.setTitle(null);
        this.setDate(null);
        clearSearchData();

        this.setSelectInfoShu(null);
        this.setSelectCateId(null);
        this.setSelectSubCateId(null);
        this.setSelectTitle(null);
        this.setSelectDate(null);
    }
    /**
     * 検索取得情報クリア処理
     *
     */
    public void clearSearchData() {
    	//1.DTO【関連文書検索処理】.表示ページ番号へ引数.currentPageNumberを設定する。
    	setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
		// 件数取得
    	this.setListAllDocument(null);
        this.setDocumentList(null);
        this.setSaveMap(null);
        this.setCheckList(null);
    }
//---------------------------
	/**
	 * コンストラクタ
	 * 
	 */
	public DocumentSearchConditionDto() {
		this(DEFAULT_MAX_PAGE_COUNT);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param maxPageCount ページ表示件数
	 */
	public DocumentSearchConditionDto(int maxPageCount) {
		setMaxPageCount((maxPageCount > 0) ? maxPageCount : DEFAULT_MAX_PAGE_COUNT);
		setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
	}

	/**
	 * 現在のページ番号取得処理
	 * @return
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	/**
	 * 現在のページ番号設定処理
	 * 
	 * @param currentPageNumber
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getCount() {
		if(getListAllDocument() == null) {
			return 0;
		}
		return getListAllDocument().size();
	}

	public int getMaxPageCount() {
		return maxPageCount;
	}
	public void setMaxPageCount(int maxPageCount) {
		if (maxPageCount > 0) {
			this.maxPageCount = maxPageCount;
		}
	}

	//先頭ページ番号    : 常に1。
	public int getFirstPageNumber() {
		return 1;
	}
	//最終ページ番号    : (全件数 - 1) / 表示件数 + 1。全0件、1件表示の時に注意。
	public int getLastPageNumber() {
		int page = 0;
		if(getCount() > 0) {
			//除算
			page = getCount() / getMaxPageCount();
			if(0 < (getCount() % getMaxPageCount())) {
				page++;
			}
		}
		return page;
	}
	//前のページ番号    : 現在ページ - 1。先頭ページの場合、同じページ番号。
	public int getPreviousPageNumber() {
		return (isExistPreviousPage()) ? getCurrentPageNumber() - 1 : getCurrentPageNumber();
	}
	//次のページ番号    : 現在ページ + 1。最終ページの場合、同じページ番号。
	public int getNextPageNumber() {
		return (isExistNextPage()) ? getCurrentPageNumber() + 1 : getCurrentPageNumber();
	}
	//ページ先頭件数    : offsetに使いたい。(現在ページ - 1) * 件数。0～で取れる。
	public int getPageFirstRecordNumber() {
		return (getCurrentPageNumber() - 1) * getMaxPageCount();
	}
	//先頭ページ？      : 現在ページ == 先頭ページ
	public boolean isFirstPage() {
		return getCurrentPageNumber() == getFirstPageNumber();
	}
	//最終ページ？      : 現在ページ == 最終ページ
	public boolean isLastPage() {
		return getCurrentPageNumber() == getLastPageNumber();
	}
	//前のページ有？    : 現在ページ > 先頭ページ
	public boolean isExistPreviousPage() {
		return getCurrentPageNumber() > getFirstPageNumber();
	}
	//次のページ有？    : 現在ページ < 最終ページ
	public boolean isExistNextPage() {
		return getCurrentPageNumber() < getLastPageNumber();
	}
	//ページ指定
	public List getDirectLinkList() {
		List directLinkList = new ArrayList();
		for (int i = getFirstPageNumber(), n = getLastPageNumber(); i <= n; i++) {
			directLinkList.add(new Integer(i));
		}
		return directLinkList;
	}
	/**
	 * 条件フィールドの値をSQL検索用変数にセットする。
	 *
	 */
	public void settingSearchJokenData() {
        setSelectInfoShu(getInfoShu());
        setSelectCateId(getCateId());
        setSelectSubCateId(getSubCateId());
        setSelectTitle(getTitle());
        setSelectDate(getDate());

	}
    /**
     * サブカテゴリー存在判断フラグ
     * 
     * @return
     */
    public boolean isExistListSubCate() {
    	if(getSubCateList() != null && getSubCateList().size() > 0) {
    		return true;
    	}
    	return false;
    }
	/**
	 * @return targetDateList を戻します。
	 */
	public List getTargetDateList() {
		return targetDateList;
	}
	/**
	 * @param targetDateList 設定する targetDateList。
	 */
	public void setTargetDateList(List targetDateList) {
		this.targetDateList = targetDateList;
	}
	/**
	 * @return listAllDocument を戻します。
	 */
	public List getListAllDocument() {
		return listAllDocument;
	}
	/**
	 * @param listAllDocument 設定する listAllDocument。
	 */
	public void setListAllDocument(List listAllDocument) {
		this.listAllDocument = listAllDocument;
	}
    
}
