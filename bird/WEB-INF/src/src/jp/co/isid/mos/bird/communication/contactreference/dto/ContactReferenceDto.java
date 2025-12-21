/*
 * 作成日: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 連絡文書ＤＴＯ
 * @author xyuchida
 *
 */
public class ContactReferenceDto {

    private static final int DEFAULT_CURRENT_PAGE_NUMBER = 1;
    private static final int DEFAULT_MAX_PAGE_COUNT = 30;

    private String pubDate;
    private String gyotaiKbn;
    private String title;
    private String inputPubDate;
    private String inputGyotaiKbn;
    private String inputTitle;
    private String cateId;

    private List pubDateList;
    private List gyotaiList;
    private List categoryList;
    //画面表示用サブカテゴリ情報
    private List listTabCate = new ArrayList(0);
    private List contactList;

    private int currentPageNumber;
    private int count;
    private int maxPageCount;
    //今月の連絡の件数
    private int kongetuRenrakuCount;

    /**
     * コンストラクタ
     * 
     */
    public ContactReferenceDto() {
        this(DEFAULT_MAX_PAGE_COUNT);
    }

    /**
     * コンストラクタ
     * 
     * @param maxPageCount ページ表示件数
     */
    public ContactReferenceDto(int maxPageCount) {
        setMaxPageCount((maxPageCount > 0) ? maxPageCount : DEFAULT_MAX_PAGE_COUNT);
        setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
    }

    public String getPubDate() {
        return pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getGyotaiKbn() {
        return gyotaiKbn;
    }
    public void setGyotaiKbn(String gyotaiKbn) {
        this.gyotaiKbn = gyotaiKbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getInputPubDate() {
        return inputPubDate;
    }
    public void setInputPubDate(String inputPubDate) {
        this.inputPubDate = inputPubDate;
    }
    public String getInputGyotaiKbn() {
        return inputGyotaiKbn;
    }
    public void setInputGyotaiKbn(String inputGyotaiKbn) {
        this.inputGyotaiKbn = inputGyotaiKbn;
    }
    public String getInputTitle() {
        return inputTitle;
    }
    public void setInputTitle(String inputTitle) {
        this.inputTitle = inputTitle;
    }
    public String getCateId() {
        if("".equals(cateId)){
            return null;
        }
        return cateId;
    }
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public List getPubDateList() {
        return pubDateList;
    }
    public void setPubDateList(List pubDateList) {
        this.pubDateList = pubDateList;
    }
    public List getGyotaiList() {
        return gyotaiList;
    }
    public void setGyotaiList(List gyotaiList) {
        this.gyotaiList = gyotaiList;
    }
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
    public List getContactList() {
        return contactList;
    }
    public void setContactList(List contactList) {
        this.contactList = contactList;
    }

    public boolean isEmptyCategoryList() {
        return (getCategoryList() == null) ? true : getCategoryList().isEmpty();
    }
    public boolean isEmptyContactList() {
        return (getContactList() == null) ? true : getContactList().isEmpty();
    }

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
    public List getDirectLinkList() {
        List directLinkList = new ArrayList();
        for (int i = getFirstPageNumber(), n = getLastPageNumber(); i <= n; i++) {
            directLinkList.add(new Integer(i));
        }
        return directLinkList;
    }

    /**
     * 今月の連絡の件数を取得します。
     * @return 今月の連絡の件数
     */
    public int getKongetuRenrakuCount() {
        return kongetuRenrakuCount;
    }

    /**
     * 今月の連絡を設定します。
     * @param 今月の連絡の件数
     */
    public void setKongetuRenrakuCount(int kongetuRenrakuCount) {
        this.kongetuRenrakuCount = kongetuRenrakuCount;
    }
    

}
