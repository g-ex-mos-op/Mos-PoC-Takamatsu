package jp.co.isid.mos.bird.office.formdownload.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.office.formdownload.entity.UIFormDownLoad;

/**
 * フォームダウンロードDTO
 * @author xytamura
 */
public class FormDownloadDto implements DownloadDto {

    
    // 1ページの表示最大件数
    private static final int PAGE_MAX_SIZE = 30;

//    // 現在表示ページ番号
//    private int nowPageNo = 0;
//    // 検索済みフラグ (true:検索済み)
//    private boolean viewListFlg;
    // 検索条件：タイトル
    private String condTitle;
    // 検索条件：カテゴリID
    private String condCateId;
    // 検索条件：サブカテゴリID
    private String condSubCateId;
    // 検索結果：フォーム一覧
    private List listForm;
    // 選択中のサブカテゴリ
    private String selectedSubCateId;
    // サブカテゴリリスト
    private List listSubCate;
    /* ページ制御関連 */
    private int currentPageNumber;
    private int count;

    // 詳細表示Entity
    private UIFormDownLoad entityUIFormDownLoad;
    // 一覧選択Index
    private int indexEntityViewDetail;
    // 添付ファイル 選択インデックス
    private int selectedAttachFileIndex;
    // ダウンロード対象モード（１：フォームファイル　２：添付ファイル　３：関連文書）
    private int downloadMode;
    // 検索モード（１：カテゴリ検索 ２：タイトル検索）
    private int searchMode = 1;
    /* ナビエリア */
    // カテゴリ一覧
    private List listNaviCate;
    //ダウンロードファイルのプルダウンのインデックス
    private int downloadFileIndex;

// add start xkhata 
    
    private String viewCateName;
    
    private int selectIndex;
    
    private int viewSelectAttachFileIndex;
    
    private int viewDownloadFileIndex;
    
    private String viewShozoku;
//add end
    /**
     * @return condTitle を戻します。
     */
    public String getCondTitle() {
        return condTitle;
    }
    /**
     * @param condTitle condTitle を設定。
     */
    public void setCondTitle(String condTitle) {
        this.condTitle = condTitle;
    }
    /**
     * @return viewListFlg を戻します。
     */
    public boolean isViewListFlg() {
        return getListForm() != null && getListForm().size() > 0;
//        return viewListFlg;
    }
//    /**
//     * @param viewListFlg viewListFlg を設定。
//     */
//    public void setViewListFlg(boolean viewListFlg) {
//        this.viewListFlg = viewListFlg;
//    }
    /**
     * @return listNaviCate を戻します。
     */
    public List getListNaviCate() {
        return listNaviCate;
    }
    /**
     * @param listNaviCate listNaviCate を設定。
     */
    public void setListNaviCate(List listNaviCate) {
        this.listNaviCate = listNaviCate;
    }
    
    public void clear() {
//        nowPageNo = 0;
        condTitle = "";
    }

    /**
     * @return listBunsho を戻します。
     */
    public List getListForm() {
        return listForm;
    }
    /**
     * @param listBunsho listBunsho を設定。
     */
    public void setListForm(List listForm) {
        this.listForm = listForm;
    }
    /**
     * @return condCateId を戻します。
     */
    public String getCondCateId() {
        return condCateId;
    }
    /**
     * @param condCateId condCateId を設定。
     */
    public void setCondCateId(String condCateId) {
        this.condCateId = condCateId;
    }
    /**
     * @return selectedSubCateId を戻します。
     */
    public String getSelectedSubCateId() {
        return selectedSubCateId;
    }
    /**
     * @param selectedSubCateId selectedSubCateId を設定。
     */
    public void setSelectedSubCateId(String selectedSubCateId) {
        this.selectedSubCateId = selectedSubCateId;
    }
    /**
     * @return condSubCateId を戻します。
     */
    public String getCondSubCateId() {
        return condSubCateId;
    }
    /**
     * @param condSubCateId condSubCateId を設定。
     */
    public void setCondSubCateId(String condSubCateId) {
        this.condSubCateId = condSubCateId;
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
        return PAGE_MAX_SIZE;
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
    }
    /**
     * @return entityViewDetail を戻します。
     */
    public UIFormDownLoad getEntityViewDetail() {
        return entityUIFormDownLoad;
    }
    /**
     * @param entityViewDetail entityViewDetail を設定。
     */
    public void setEntityViewDetail(UIFormDownLoad entityUIFormDownLoad) {
        this.entityUIFormDownLoad = entityUIFormDownLoad;
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
        setCurrentPageNumber(1);
        setCondSubCateId(null);
        setListForm(null);
        setListSubCate(null);
        setEntityViewDetail(null);
    }
    
    public void clearCondDto() {
        setCondTitle(null);
        setCondCateId(null);
        setCondSubCateId(null);
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
    public int getSelectIndex() {
        return selectIndex;
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    public int getSelectedAttachFileIndex() {
        return selectedAttachFileIndex;
    }
    public void setSelectedAttachFileIndex(int selectedAttachFileIndex) {
        this.selectedAttachFileIndex = selectedAttachFileIndex;
    }
    public int getViewSelectAttachFileIndex() {
        return viewSelectAttachFileIndex;
    }
    public void setViewSelectAttachFileIndex(int viewSelectAttachFileIndex) {
        this.viewSelectAttachFileIndex = viewSelectAttachFileIndex;
    }
    public int getViewDownloadFileIndex() {
        return viewDownloadFileIndex;
    }
    public void setViewDownloadFileIndex(int viewDownloadFileIndex) {
        this.viewDownloadFileIndex = viewDownloadFileIndex;
    }
    public String getViewShozoku() {
        return viewShozoku;
    }
    public void setViewShozoku(String viewShozoku) {
        this.viewShozoku = viewShozoku;
    }

}