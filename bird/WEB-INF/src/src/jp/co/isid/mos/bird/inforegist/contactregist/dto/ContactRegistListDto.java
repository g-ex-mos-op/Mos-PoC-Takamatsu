/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * @author xyuchida
 *
 */
public class ContactRegistListDto implements DownloadDto{

    //選択されたインデックス
    private int selectedIndex;
    //対象日付
    private String targetDate;
    private String categoryId;
    private String inputTargetDate;
    private String inputCategoryId;
    //検索結果リスト
    private List contactList;

    private int currentPageNumber;
    private int count;
    private int maxPageCount;
    //添付ファイルのインデックス
    private int downloadIndex;
    /**
     * @return selectedIndex を戻します。
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }
    /**
     * @param selectedIndex selectedIndex を設定。
     */
    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }
    /**
     * @return targetDate を戻します。
     */
    public String getTargetDate() {
        return targetDate;
    }
    /**
     * @param targetDate targetDate を設定。
     */
    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }
    /**
     * @return categoryId を戻します。
     */
    public String getCategoryId() {
        return categoryId;
    }
    /**
     * @param categoryId categoryId を設定。
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    /**
     * @return inputTargetDate を戻します。
     */
    public String getInputTargetDate() {
        return inputTargetDate;
    }
    /**
     * @param inputTargetDate inputTargetDate を設定。
     */
    public void setInputTargetDate(String inputTargetDate) {
        this.inputTargetDate = inputTargetDate;
    }
    /**
     * @return inputCategoryId を戻します。
     */
    public String getInputCategoryId() {
        return inputCategoryId;
    }
    /**
     * @param inputCategoryId inputCategoryId を設定。
     */
    public void setInputCategoryId(String inputCategoryId) {
        this.inputCategoryId = inputCategoryId;
    }
    /**
     * @return contactList を戻します。
     */
    public List getContactList() {
        return contactList;
    }
    /**
     * @param contactList contactList を設定。
     */
    public void setContactList(List contactList) {
        this.contactList = contactList;
    }

    public int getContactListSize() {
        return contactList.size();
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
     * 添付ファイルのインデックスを取得します。
     * @return 添付ファイルのインデックス 
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    /**
     * 添付ファイルのインデックスを設定します。
     * @param 添付ファイルのインデックス
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }
    /**
     * 条件画面データ初期化処理
     *
     */
    public void clearConditionData() {
    	setCategoryId(null);
    	setTargetDate(null);
    	setInputCategoryId(null);
    	setInputTargetDate(null);
        // 検索結果クリア
        setSelectedIndex(0);
        getContactList().clear();
    }
}
