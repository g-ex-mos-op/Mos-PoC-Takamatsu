/*
 * 作成日: 2006/05/30
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;

/**
 * ベーシック研修マスタ登録DTO
 * 
 * @author xyuchida
 */
public class BasicRegistDto {

    /**
     * 編集モード
     *  = 1:insert 2:update 3:delete
     */
    private int editMode;

    /**
     * エントリーリスト
     */
    private List basicEntryList;
    /**
     * 選択ラジオボタンインデックス
     */
    private int selectIndex;

    /**
     * コースリスト
     */
    private List courseList;
    /**
     * 選択コースコード
     */
    private String courseCd;

    /**
     * 申込開始日
     */
    private String applyFromDt;

    /**
     * ユーザID
     */
    private String userId;
    /**
     * システム日付
     */
    private String sysDate;

    /**
     * エントリーマスタ管理Entity
     */
    private UIEntryMst uiEntryMst;
    /**
     * エントリー日付管理Entity(ベーシック研修)
     */
    private UIEntryDate uiEntryDateBasic;
    /**
     * エントリー日付管理Entity(臨店実習コース)
     */
    private UIEntryDate uiEntryDateVisit;
    /**
     * エントリー日付管理Entity(本部：表示)
     */
    private UIEntryDate uiEntryDateDisplayHead;
    /**
     * エントリー日付管理Entity(オーナー：表示)
     */
    private UIEntryDate uiEntryDateDisplayOner;
    /**
     * エントリー日付管理Entity(本部：申込)
     */
    private UIEntryDate uiEntryDateApplyHead;
    /**
     * エントリー日付管理Entity(オーナー：申込)
     */
    private UIEntryDate uiEntryDateApplyOner;
    /**
     * エントリー日付管理Entity(結果)
     */
    private UIEntryDate uiEntryDateResult;
    /**
     * エントリーコース管理Entity
     */
    private UIEntryCourse uiEntryCourse;

    /**
     * 現在ページNo
     */
    private int currentPageNumber;
    /**
     * 全件数
     */
    private int count;
    /**
     * 1ページあたりの表示件数
     */
    private int maxPageCount;

    public int getEditMode() {
        return editMode;
    }
    public void setEditMode(int editMode) {
        this.editMode = editMode;
    }

    public List getBasicEntryList() {
        return basicEntryList;
    }
    public void setBasicEntryList(List basicEntryList) {
        this.basicEntryList = basicEntryList;
    }
    public int getSelectIndex() {
        return selectIndex;
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public List getCourseList() {
        return courseList;
    }
    public void setCourseList(List courseList) {
        this.courseList = courseList;
    }
    public String getCourseCd() {
        return courseCd;
    }
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    public String getApplyFromDt() {
        return applyFromDt;
    }
    public void setApplyFromDt(String applyFromDt) {
        this.applyFromDt = applyFromDt;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSysDate() {
        return sysDate;
    }
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public UIEntryMst getUiEntryMst() {
        return uiEntryMst;
    }
    public void setUiEntryMst(UIEntryMst uiEntryMst) {
        this.uiEntryMst = uiEntryMst;
    }
    public UIEntryDate getUiEntryDateBasic() {
        return uiEntryDateBasic;
    }
    public void setUiEntryDateBasic(UIEntryDate uiEntryDateBasic) {
        this.uiEntryDateBasic = uiEntryDateBasic;
    }
    public UIEntryDate getUiEntryDateVisit() {
        return uiEntryDateVisit;
    }
    public void setUiEntryDateVisit(UIEntryDate uiEntryDateVisit) {
        this.uiEntryDateVisit = uiEntryDateVisit;
    }
    public UIEntryDate getUiEntryDateDisplayHead() {
        return uiEntryDateDisplayHead;
    }
    public void setUiEntryDateDisplayHead(UIEntryDate uiEntryDateDisplayHead) {
        this.uiEntryDateDisplayHead = uiEntryDateDisplayHead;
    }
    public UIEntryDate getUiEntryDateDisplayOner() {
        return uiEntryDateDisplayOner;
    }
    public void setUiEntryDateDisplayOner(UIEntryDate uiEntryDateDisplayOner) {
        this.uiEntryDateDisplayOner = uiEntryDateDisplayOner;
    }
    public UIEntryDate getUiEntryDateApplyHead() {
        return uiEntryDateApplyHead;
    }
    public void setUiEntryDateApplyHead(UIEntryDate uiEntryDateApplyHead) {
        this.uiEntryDateApplyHead = uiEntryDateApplyHead;
    }
    public UIEntryDate getUiEntryDateApplyOner() {
        return uiEntryDateApplyOner;
    }
    public void setUiEntryDateApplyOner(UIEntryDate uiEntryDateApplyOner) {
        this.uiEntryDateApplyOner = uiEntryDateApplyOner;
    }
    public UIEntryDate getUiEntryDateResult() {
        return uiEntryDateResult;
    }
    public void setUiEntryDateResult(UIEntryDate uiEntryDateResult) {
        this.uiEntryDateResult = uiEntryDateResult;
    }
    public UIEntryCourse getUiEntryCourse() {
        return uiEntryCourse;
    }
    public void setUiEntryCourse(UIEntryCourse uiEntryCourse) {
        this.uiEntryCourse = uiEntryCourse;
    }

    public boolean isEmptyBasicEntryList() {
        return getBasicEntryList() == null || getBasicEntryList().isEmpty();
    }
    public boolean isEditableItem() {
        // 新規モード、または申込期間前は変更可能
        return getEditMode() == BasicRegistConstants.EDIT_MODE_INSERT
                || (getApplyFromDt() == null
                || getSysDate().compareTo(getApplyFromDt()) < 0);
    }

    /** ページング機能関連メソッド */
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
}
