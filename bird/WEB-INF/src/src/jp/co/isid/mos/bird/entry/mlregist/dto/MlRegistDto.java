/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryMaster;

/**
 * マスタライセンスマスタ登録DTO
 * 
 * @author xyuchida
 */
public class MlRegistDto {

    // エントリーリスト
    private List mlEntryList;

    // エントリーマスタ管理Entity
    private UIEntryMaster uiEntryMaster;

    // エントリー日付管理Entity
    private UIEntryDate uiEntryDateOpen;
    private UIEntryDate uiEntryDateDisplayOner;
    private UIEntryDate uiEntryDateDisplayHead;
    private UIEntryDate uiEntryDateApplyOner;
    private UIEntryDate uiEntryDateApplyHead;
    private UIEntryDate uiEntryDateRegist;
    private UIEntryDate uiEntryDateResultOner;
    private UIEntryDate uiEntryDateResultHead;

    // エントリー受験地管理Entitiyリスト
    private List uiEntryPlaceList;

    // エントリーコード
    private String entryCd;
    // 申込開始日
    private String applyFromDt;

    // 編集モード = 1:insert 2:update 3:delete
    private int editMode;
    // 選択ラジオボタンインデックス
    private int selectIndex;

    // ユーザID
    private String userId;
    // システム日付
    private String sysDate;

    // ページング機能
    private int currentPageNumber;
    private int count;
    private int maxPageCount;


    public List getMlEntryList() {
        return mlEntryList;
    }
    public void setMlEntryList(List mlEntryList) {
        this.mlEntryList = mlEntryList;
    }

    public UIEntryMaster getUiEntryMaster() {
        return uiEntryMaster;
    }
    public void setUiEntryMaster(UIEntryMaster uiEntryMaster) {
        this.uiEntryMaster = uiEntryMaster;
    }

    public UIEntryDate getUiEntryDateOpen() {
        return uiEntryDateOpen;
    }
    public void setUiEntryDateOpen(UIEntryDate uiEntryDateOpen) {
        this.uiEntryDateOpen = uiEntryDateOpen;
    }
    public UIEntryDate getUiEntryDateDisplayOner() {
        return uiEntryDateDisplayOner;
    }
    public void setUiEntryDateDisplayOner(UIEntryDate uiEntryDateDisplayOner) {
        this.uiEntryDateDisplayOner = uiEntryDateDisplayOner;
    }
    public UIEntryDate getUiEntryDateDisplayHead() {
        return uiEntryDateDisplayHead;
    }
    public void setUiEntryDateDisplayHead(UIEntryDate uiEntryDateDisplayHead) {
        this.uiEntryDateDisplayHead = uiEntryDateDisplayHead;
    }
    public UIEntryDate getUiEntryDateApplyOner() {
        return uiEntryDateApplyOner;
    }
    public void setUiEntryDateApplyOner(UIEntryDate uiEntryDateApplyOner) {
        this.uiEntryDateApplyOner = uiEntryDateApplyOner;
    }
    public UIEntryDate getUiEntryDateApplyHead() {
        return uiEntryDateApplyHead;
    }
    public void setUiEntryDateApplyHead(UIEntryDate uiEntryDateApplyHead) {
        this.uiEntryDateApplyHead = uiEntryDateApplyHead;
    }
    public UIEntryDate getUiEntryDateRegist() {
        return uiEntryDateRegist;
    }
    public void setUiEntryDateRegist(UIEntryDate uiEntryDateRegist) {
        this.uiEntryDateRegist = uiEntryDateRegist;
    }
    public UIEntryDate getUiEntryDateResultOner() {
        return uiEntryDateResultOner;
    }
    public void setUiEntryDateResultOner(UIEntryDate uiEntryDateResultOner) {
        this.uiEntryDateResultOner = uiEntryDateResultOner;
    }
    public UIEntryDate getUiEntryDateResultHead() {
        return uiEntryDateResultHead;
    }
    public void setUiEntryDateResultHead(UIEntryDate uiEntryDateResultHead) {
        this.uiEntryDateResultHead = uiEntryDateResultHead;
    }

    public List getUiEntryPlaceList() {
        return uiEntryPlaceList;
    }
    public void setUiEntryPlaceList(List uiEntryPlaceList) {
        this.uiEntryPlaceList = uiEntryPlaceList;
    }

    public String getEntryCd() {
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getApplyFromDt() {
        return applyFromDt;
    }
    public void setApplyFromDt(String applyFromDt) {
        this.applyFromDt = applyFromDt;
    }

    public int getEditMode() {
        return editMode;
    }
    public void setEditMode(int editMode) {
        this.editMode = editMode;
    }
    public int getSelectIndex() {
        return selectIndex;
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
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

    public boolean isEmptyMlEntryList() {
        return getMlEntryList() == null || getMlEntryList().isEmpty();
    }
    public boolean isEditableItem() {
        // 新規モード、または申込期間前は変更可能
        return getEditMode() == MlRegistConstants.EDIT_MODE_INSERT
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
