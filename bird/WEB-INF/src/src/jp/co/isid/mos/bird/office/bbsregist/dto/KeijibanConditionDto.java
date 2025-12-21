/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;

/**
 * 掲示板登録条件画面DTO
 * @author xytamura
 */
public class KeijibanConditionDto implements DownloadDto {

    /** 編集モード：新規 */
    public static final int REG_MODE_INSERT = 1;
    /** 編集モード：編集 */
    public static final int REG_MODE_UPDATE = 2;
    /** 編集モード：削除 */
    public static final int REG_MODE_DELETE = 3;
    
    // 1ページの表示最大件数
    private static final int PAGE_MAX_SIZE = 30;
    
    /* カテゴリID */
    private String cateId;
    /* 検索文字 */
    private String keyWord;
    /* タイトルフラグ */
    private boolean titleFlg = true;
    /* メッセージフラグ */ 
    private boolean msgFlg = true;

    /* 選択カテゴリID */
    private String selectedCateId;
    /* 選択中の文書リストインデックス */
    private int selectedListIndex = -1;
    /* ダウンロードアクションを実行したリスト内のIndex */
    private int downloadIndex;
    /* 添付ファイル 選択インデックス */
    private int selectedAttachFileIndex;
    
    /* カテゴリ一覧 */
    private List listCategory;
    /* 掲示板情報一覧 */
    private List listKeijiban;
    /* 現在ページ番号 */
    private int currentPageNumber;
    private int count;
   
    
    /* 条件-->編集遷移フラグ */
    private boolean flgCondToReg = false;
    

    public static final String FLG_ON = "1";
    public static final String FLG_OFF = "0";
    
    public static final String ALL_BIZ_CATE_NAME = "業務系すべて";
    
    /* 情報種別 */
    public static String INFO_SHU = "06";

    /* ビューID */
    public static final String VIEWID_CONDITION = "BOF003V01";
    public static final String VIEWID_REF = "BOF003V02";
    public static final String VIEWID_EDIT = "BOF003V03";
    
    //キーワード検索モード
    private boolean keywordSearchMode;
    
    /**
     * カテゴリ一覧リスト設定処理
     * @param  カテゴリ一覧
     */
    public void setListCategory(List listCategory) {
        this.listCategory = listCategory;
    }

    /**
     * カテゴリ一覧リスト設定処理
     * @return サブカテゴリ一覧リスト
     */
    public List getListCategory() {
        return listCategory;
    }

    /**
     * カテゴリ一覧の件数を取得します。
     * @return int 件数
     */
    public int getListCategorySize() {
        int size = 0;
        if (getListCategory() != null) {
            size = getListCategory().size();
        }
        return size;
    }


    /**
     * カテゴリID設定処理
     * @param cateId cateId を設定。
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    /**
     * カテゴリID取得処理
     * @return 「業務系全て」の場合はnullをリターン 
     */
    public String getCateId() {
        if("".equals(cateId)){
            return null;
        }
        return cateId;
    }
    
    
    /**
     * 検索文字を取得します。
     * @return 検索文字 
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * 検索文字を設定します。
     * @param 検索文字
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * メッセージフラグを取得します。
     * @return メッセージフラグ 
     */
    public boolean getMsgFlg() {
        return msgFlg;
    }

    /**
     * メッセージフラグを設定します。
     * @param メッセージフラグ
     */
    public void setMsgFlg(boolean msgFlg) {
        this.msgFlg = msgFlg;
    }

    /**
     * タイトルフラグを取得します。
     * @return タイトルフラグ 
     */
    public boolean getTitleFlg() {
        return titleFlg;
    }

    /**
     * タイトルフラグを設定します。
     * @param タイトルフラグ
     */
    public void setTitleFlg(boolean titleFlg) {
        this.titleFlg = titleFlg;
    }



    /**
     * 掲示板一覧設定処理
     * @param 掲示板一覧を設定。
     */
    public void setListKeijiban(List listKeijiban) {
        this.listKeijiban = listKeijiban;
    }

    /**
     * 掲示板一覧取得処理
     * @return 掲示板一覧を戻します。
     */
    public List getListKeijiban() {
        return listKeijiban;
    }
    
    /**
     * 掲示板一覧の件数を取得します。
     * @return int 件数
     */
    public int getListKeijibanSize() {
        int size = 0;
        if (getListKeijiban() != null) {
            size = getListKeijiban().size();
        }
        return size;
    }


    /**
     * @param _selectedListIndex _selectedListIndex を設定。
     */
    public void setSelectedListIndex(int selectedListIndex) {
        this.selectedListIndex = selectedListIndex;
    }

    /**
     * @return _selectedListIndex を戻します。
     */
    public int getSelectedListIndex() {
        return selectedListIndex;
    }
    
    /**
     * @param _selectedAttachFileIndex _selectedAttachFileIndex を設定。
     */
    public void setSelectedAttachFileIndex(int selectedAttachFileIndex) {
        this.selectedAttachFileIndex = selectedAttachFileIndex;
    }

    /**
     * @return _selectedAttachFileIndex を戻します。
     */
    public int getSelectedAttachFileIndex() {
        return selectedAttachFileIndex;
    }


    /**
     * @param _selectedCateId _selectedCateId を設定。
     */
    public void setSelectedCateId(String selectedCateId) {
        this.selectedCateId = selectedCateId;
    }

    /**
     * @return _selectedCateId を戻します。
     */
    public String getSelectedCateId() {
        return selectedCateId;
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

    public UIKeijiban getSelectedEntity() {
        if (getListKeijiban() != null && getSelectedListIndex() >= 0) {
            if (getListKeijiban().size() >= getSelectedListIndex()) {
                return (UIKeijiban) getListKeijiban().get(getSelectedListIndex());
            }
        }
        return null;
    }

    /**
     * @param flgCondToReg flgCondToReg を設定。
     */
    public void setFlgCondToReg(boolean flgCondToReg) {
        this.flgCondToReg = flgCondToReg;
    }

    /**
     * @return flgCondToReg を戻します。
     */
    public boolean isFlgCondToReg() {
        return flgCondToReg;
    }

    /**
     * 選択されたダウンロードファイルのインデックスを設定します。
     * @param downloadIndex 選択されたダウンロードファイルのインデックス
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * 選択されたダウンロードファイルのインデックスを取得します。
     * @return 選択されたダウンロードファイルのインデックス
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    
    /**
     * クリア処理
     */
    public void clearData() {
        // 条件画面の文書リスト
        setListKeijiban(null);
        setSelectedListIndex(-1);
        setCateId(null);
//        setNengetu(null);
//        setListCategory(null);
    }
    
    /**
     * クリア処理
     * 　条件画面 新規、検索処理実行用
     *
     */
    public void clearCondInsOrSearch() {
        setListKeijiban(null);
        setSelectedListIndex(-1);
        setFlgCondToReg(true);
    }

    /**
     * キーワード検索モードを取得します。
     * @return キーワード検索モード 
     */
    public boolean isKeywordSearchMode() {
        return keywordSearchMode;
    }

    /**
     * キーワード検索モードを設定します。
     * @param キーワード検索モード
     */
    public void setKeywordSearchMode(boolean keywordSearchMode) {
        this.keywordSearchMode = keywordSearchMode;
    }
    
    
}
