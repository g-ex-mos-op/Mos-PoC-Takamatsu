/*
 * 作成日: 2006/03/01
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.communication.notificationreference.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.communication.notificationreference.entity.UIViewTutatu;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * @author m.onodera
 * 更新日:2009/06/22 既読リンク文字色変更対応
 * 　　　・クラス変数『ダウンロード対象ファイル名』追加
 * 　　　・クラス変数『ダウンロード対象ファイル名をキーにした対象インデックス保持Map』追加
 */
public class NotificationReferenceDto implements DownloadDto {

    private static final int DEFAULT_CURRENT_PAGE_NUMBER = 1;
    private static final int DEFAULT_MAX_PAGE_COUNT = 30;

    private String pubDate;
    private String pubDateTo;
    private String gyotaiKbn;
    private String title;
    private String searchWord;
    private String inputPubDate;
    private String inputPubDateTo;
    private String inputGyotaiKbn;
    private String inputTitle;
    private String inputSearchWord;
    private String cateId;

    /* 対象年月List */
    private List pubDateList;
    /* 業態List */
    private List gyotaiList;
    /* 通達カテゴリList */
    private List categoryList;
    //画面表示用サブカテゴリ情報
    private List listTabCate = new ArrayList(0);
    /* 通達情報（検索結果）List */
    private List tutatuList;
    /* 関連文書List */
    private List listKanrenBunsho;

    /* 添付ファイル 選択インデックス */
    private String _selectedAttachFileIndex;
    /* ダウンロードアクションを実行したリスト内のIndex */
    private int downloadIndex;

    private int currentPageNumber;
    private int count;
    private int maxPageCount;

    /* 今月の通達 */
    private int tutatuCount;
    
    private List listAllTutatu;
    private Map mapCategoryTutatuList = new HashMap();
    
    /**
     * ダウンロード対象ファイル名
     * 作成日:2009/06/22
     * 既読リンク文字色変更対応
     */
    private String downloadFileName;
    /**
     * ダウンロード対象ファイル名をキーにした対象インデックス保持Map
     * 作成日:2009/06/22
     * 既読リンク文字色変更対応
     */
    private Map mapFileNameIndex = new HashMap();
    
    /**
     * コンストラクタ
     * 
     */
    public NotificationReferenceDto() {
        this(DEFAULT_MAX_PAGE_COUNT);
    }

    /**
     * コンストラクタ
     * 
     * @param maxPageCount ページ表示件数
     */
    public NotificationReferenceDto(int maxPageCount) {
        setMaxPageCount((maxPageCount > 0) ? maxPageCount : DEFAULT_MAX_PAGE_COUNT);
        setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
    }

    //SQL検索用　対象年月
    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    //SQL検索用　対象年月To
    public String getPubDateTo() {
        return pubDateTo;
    }
    public void setPubDateTo(String pubDateTo) {
        this.pubDateTo = pubDateTo;
    }
    //SQL検索用　業態区分
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    //SQL検索用　タイトル
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    //SQL検索用　全文検索用項目
    public String getSearchWord() {
        return searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    //対象年月
    public String getInputPubDate() {
        return inputPubDate;
    }
    public void setInputPubDate(String inputPubDate) {
        this.inputPubDate = inputPubDate;
    }
    //対象年月To
    public String getInputPubDateTo() {
        return inputPubDateTo;
    }
    public void setInputPubDateTo(String inputPubDateTo) {
        this.inputPubDateTo = inputPubDateTo;
    }
    //業態区分
    public String getInputGyotaiKbn() {
        return inputGyotaiKbn;
    }
    public void setInputGyotaiKbn(String inputGyotaiKbn) {
        this.inputGyotaiKbn = inputGyotaiKbn;
    }
    //タイトル
    public String getInputTitle() {
        return inputTitle;
    }
    public void setInputTitle(String inputTitle) {
        this.inputTitle = inputTitle;
    }
    //全文検索用項目
    public String getInputSearchWord() {
        return inputSearchWord;
    }
    public void setInputSearchWord(String inputSearchWord) {
        this.inputSearchWord = inputSearchWord;
    }
    //カテゴリーID
    public String getCateId() {
        if("".equals(cateId)){
            return null;
        }
        return cateId;
    }
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    //対象年月プルダウン用List
    public List getPubDateList() {
        return pubDateList;
    }
    public void setPubDateList(List pubDateList) {
        this.pubDateList = pubDateList;
    }
    //業態プルダウン用List
    public List getGyotaiList() {
        return gyotaiList;
    }
    public void setGyotaiList(List gyotaiList) {
        this.gyotaiList = gyotaiList;
    }
    //カテゴリタブ切り替え用List
    public List getCategoryList() {
        return categoryList;
    }
    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
        setListTabCate(categoryList);
    }
    /**
     * 画面表示用カテゴリータブ情報
     * @param listCate
     */
    private void setListTabCate(List listCate) {
    	if(listCate == null) {
    		return;
    	}
    	int ino = 0;
    	listTabCate = new ArrayList(0);
    	while (ino<listCate.size()) {
    		List listRow = new ArrayList(0);
    		for (int i=0; i<5 && ino<getCategoryList().size(); i++) {
    			listRow.add(getCategoryList().get(ino));
    			ino++;
    		}
    		while(listRow.size()<5) {
    			listRow.add(null);
    		}
    		listTabCate.add(listRow);
    	}
    }
    /**
     * 画面表示用サブカテゴリータブ情報
     * 
     * 1インデックスにつき１行分(4個のサブカテゴリ)の
     * 情報を保持しています。
     * @return
     */
    public List getListTabCate() {
    	return listTabCate;
    }
    public int getCateRowCnt() {
    	return getListTabCate().size();
    }
    //通達情報List
    public List getTutatuList() {
        return tutatuList;
    }
    public void setTutatuList(List tutatuList) {
        this.tutatuList = tutatuList;
    }
    public boolean isEmptyCategoryList() {
        return (getCategoryList() == null) ? true : getCategoryList().isEmpty();
    }
    
    public boolean isEmptyTutatuList() {
        return (getTutatuList() == null) ? true : getTutatuList().isEmpty();
    }

    
    /**
     * ダウンロードindex
     * @param downloadIndex downloadIndex を設定。
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }
    /**
     * ダウンロードindex
     * @return downloadIndex を戻します。
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    /**
     * @param _selectedAttachFileIndex _selectedAttachFileIndex を設定。
     */
    public void setSelectedAttachFileIndex(String _selectedAttachFileIndex) {
        this._selectedAttachFileIndex = _selectedAttachFileIndex;
    }
    /**
     * @return _selectedAttachFileIndex を戻します。
     */
    public String getSelectedAttachFileIndex() {
        return _selectedAttachFileIndex;
    }
    
    
    /**
     * 関連文書リスト
     * @return listKanrenBunsho を戻します。
     */
    public List getListKanrenBunsho() {
        return listKanrenBunsho;
    }
    /**
     * 関連文書リスト
     * @param listKanrenBunsho listKanrenBunsho を設定。
     */
    public void setListKanrenBunsho(List listKanrenBunsho) {
        this.listKanrenBunsho = listKanrenBunsho;
    }
    
    
//ページ切り替え
    public int getCurrentPageNumber() {
        return currentPageNumber;
    }
    public void setCurrentPageNumber(int currentPageNumber) {
        if (currentPageNumber >= getFirstPageNumber() && currentPageNumber <= getLastPageNumber()) {
            this.currentPageNumber = currentPageNumber;
        }
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }
    public int getMaxPageCount() {
        return maxPageCount;
    }
    public void setMaxPageCount(int maxPageCount) {
        if (maxPageCount > 0) {
            this.maxPageCount = maxPageCount;
        }
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
        return ((getCurrentPageNumber() - 1) * getMaxPageCount());
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
    public List getDirectLinkList() {
        List directLinkList = new ArrayList();
        for (int i = getFirstPageNumber(), n = getLastPageNumber(); i <= n; i++) {
            directLinkList.add(new Integer(i));
        }
        return directLinkList;
    }
    
    /**
     * 今月の通達の件数を取得します。
     * @return 今月の通達の件数
     */
    public int getTutatuCount() {
        return tutatuCount;
    }
    /**
     * 今月の通達の件数を設定します。
     * @param tutatuCount 今月の通達の件数
     */
    public void setTutatuCount(int tutatuCount) {
        this.tutatuCount = tutatuCount;
    }

	/**
	 * @return listAllTutatu を戻します。
	 */
	public List getListAllTutatu() {
		return listAllTutatu;
	}

	/**
	 * @param listAllTutatu 設定する listAllTutatu。
	 */
	public void setListAllTutatu(List listAllTutatu) {
		this.listAllTutatu = listAllTutatu;
		setMapCategoryTutatuList(null);
		mapCategoryTutatuList = new HashMap();
		//『全て』の情報を"All"をKeyにMapへ保持する。
		mapCategoryTutatuList.put("All", listAllTutatu);
		//上記以外のタブのカテゴリー別通達一覧をカテゴリＩＤをKeyにMapへ保持する。
		for(Iterator itC = categoryList.iterator(); itC.hasNext();) {
			MstCategoryInfo eCategory = (MstCategoryInfo) itC.next();
			String cateId = eCategory.getCateId();
			if(cateId == null) {
				continue;
			}
			List listCategoryTutatu = new ArrayList();
			for(Iterator itT = listAllTutatu.iterator(); itT.hasNext();) {
	            UIViewTutatu eTutatu = (UIViewTutatu) itT.next();
	            if(!cateId.equals(eTutatu.getCateId())) {
	        		continue;
	        	}
	            listCategoryTutatu.add(eTutatu);
	    	}//end of for(Iterator itT = listAllTutatu.iterator(); itT.hasNext();)
			getMapCategoryTutatuList().put(eCategory.getCateId(), listCategoryTutatu);
    	}//end of for(Iterator itC = categoryList.iterator(); itC.hasNext();)
	}
	/**
	 * 表示対象タブの通達情報設定処
	 * 
	 * 更新日:2009/06/22 xkinu 既読リンク文字色変更対応
	 * 
	 */
	public void settingListTutatu() {
		List listTutatu = new ArrayList();
        //既読リンク文字色変更対応用
		Map mapFNIndex = new HashMap();
		String nowCateId = "All";
		if(getCateId() != null) {
			nowCateId = getCateId();
		}
		List listCategoryTutatu = (List)getMapCategoryTutatuList().get(nowCateId);
		setCount(listCategoryTutatu.size());
		int endIndex = getPageFirstRecordNumber()+getMaxPageCount()-1;
		for(int i=getPageFirstRecordNumber(); i<listCategoryTutatu.size(); i++) {
            UIViewTutatu uiViewTutatu = (UIViewTutatu) listCategoryTutatu.get(i);
            listTutatu.add(uiViewTutatu);
            //既読リンク文字色変更対応
            mapFNIndex.put(uiViewTutatu.getFileName(), String.valueOf(listTutatu.size()-1));
            if(i==endIndex) {
            	break;
            }
    	}//end of for (int i=0; i<tutatuList.size(); i++)
		setTutatuList(listTutatu);
        //既読リンク文字色変更対応
		setMapFileNameIndex(mapFNIndex);
	}

	/**
	 * @return mapCategoryTutatuList を戻します。
	 */
	public Map getMapCategoryTutatuList() {
		return mapCategoryTutatuList;
	}

	/**
	 * @param mapCategoryTutatuList 設定する mapCategoryTutatuList。
	 */
	public void setMapCategoryTutatuList(Map mapCategoryTutatuList) {
		this.mapCategoryTutatuList = mapCategoryTutatuList;
	}

	/**
	 * @return downloadFileName を戻します。
	 */
	public String getDownloadFileName() {
		return downloadFileName;
	}

	/**
	 * @param downloadFileName を クラス変数downloadFileNameへ設定します。
	 */
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	/**
	 * @return mapFileNameIndex を戻します。
	 */
	public Map getMapFileNameIndex() {
		return mapFileNameIndex;
	}

	/**
	 * @param mapFileNameIndex を クラス変数mapFileNameIndexへ設定します。
	 */
	public void setMapFileNameIndex(Map mapFileNameIndex) {
		this.mapFileNameIndex = mapFileNameIndex;
	}
}
