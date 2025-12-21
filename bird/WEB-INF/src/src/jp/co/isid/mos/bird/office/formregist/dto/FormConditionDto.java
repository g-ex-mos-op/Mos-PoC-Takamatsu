package jp.co.isid.mos.bird.office.formregist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstSubCategoryInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;

/**
 * フォーム登録条件画面DTO
 * @author xytamura
 */
public class FormConditionDto implements DownloadDto {

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
    /* サブカテゴリID */
    private String subCateId;
    /* 年月 */
    private String nengetu;
    
    /* 選択カテゴリID */
    private String selectedCateId;
    /* 選択サブカテゴリID */
    private String selectedSubCateId;
    /* 選択中の文書リストインデックス */
    private int selectedListIndex = -1;
    /* ダウンロードアクションを実行したリスト内のIndex */
    private int downloadIndex;
    /* 添付ファイル 選択インデックス */
    private int selectedAttachFileIndex;
    /* 検索時選択年月 */
    private String selectedNengetu;
    
    /* カテゴリ一覧 */
    private List listCategory;
    /* サブカテゴリ一覧 */
    private List listSubCategory;
    //画面表示用サブカテゴリ情報
    private List listTabSubCate = new ArrayList(0);
    /* 年月一覧 */
    private List listNengetu;
    /* フォーム一覧 */
    private List listForm;
    /* 関連文書 */
    private List listKanrenBunsho;
    /* 現在ページ番号 */
    private int currentPageNumber;
    private int count;
    
    /* 条件-->編集遷移フラグ */
    private boolean flgCondToReg = false;
    /* 編集モード （１：新規  ２：更新  ３：削除）*/
    private int regMode;
    
    //関連先として登録している文書
    private List listKanrenSaki;

    
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
     * サブカテゴリ一覧リスト設定処理
     * @param listSubCategory サブカテゴリ一覧リスト
     */
    public void setListSubCategory(List listSubCategory) {
        this.listSubCategory = listSubCategory;
        setListTabSubCate(listSubCategory);
    }

    /**
     * サブカテゴリ一覧リスト設定処理
     * @return サブカテゴリ一覧リスト
     */
    public List getListSubCategory() {
        return listSubCategory;
    }

    /**
     * サブカテゴリ一覧の件数を取得します。
     * @return int 件数
     */
    public int getListSubCategorySize() {
        int size = 0;
        if (getListSubCategory() != null) {
            size = getListSubCategory().size();
        }
        return size;
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
    		for (int i=0; i<5 && ino<getListSubCategory().size(); i++) {
    			listRow.add(getListSubCategory().get(ino));
    			ino++;
    		}
    		while(listRow.size()<5) {
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
     * カテゴリID設定処理
     * @param cateId cateId を設定。
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    /**
     * カテゴリID取得処理
     * @return cateId を戻します。
     */
    public String getCateId() {
        return cateId;
    }

    /**
     * サブカテゴリID設定処理
     * @param cateId cateId を設定。
     */
    public void setSubCateId(String subCateId) {
        this.subCateId = subCateId;
    }

    /**
     * サブカテゴリID取得処理
     * @return _subCateId を戻します。
     */
    public String getSubCateId() {
        return subCateId;
    }

    /**
     * 年月一覧リスト設定処理
     * @param _listNengetu _listNengetu を設定。
     */
    public void setListNengetu(List listNengetu) {
        this.listNengetu = listNengetu;
    }

    /**
     * 年月一覧リスト取得処理
     * @return _listNengetu を戻します。
     */
    public List getListNengetu() {
        return listNengetu;
    }

    /**
     * 年月設定処理
     * @param _nengetu _nengetu を設定。
     */
    public void setNengetu(String nengetu) {
        this.nengetu = nengetu;
    }

    /**
     * 年月取得処理
     * @return _nengetu を戻します。
     */
    public String getNengetu() {
        return nengetu;
    }

    /**
     * SQL LIKE文用の年月取得処理 
     * @return _nengetu を戻します。
     */
    public String getNengetuSql() {
        return nengetu + "%";
    }

    /**
     * フォーム一覧設定処理
     * @param _listForm _listBunsho を設定。
     */
    public void setListForm(List listForm) {
        this.listForm = listForm;
    }

    /**
     * フォーム一覧取得処理
     * @return _listBunsho を戻します。
     */
    public List getListForm() {
        return listForm;
    }
    
    /**
     * フォーム一覧の件数を取得します。
     * @return int 件数
     */
    public int getListFormSize() {
        int size = 0;
        if (getListForm() != null) {
            size = getListForm().size();
        }
        return size;
    }

    /**
     * 選択中サブカテゴリID設定処理
     * @param _selectedSubCateId _selectedSubCateId を設定。
     */
    public void setSelectedSubCateId(String selectedSubCateId) {
        this.selectedSubCateId = selectedSubCateId;
    }

    /**
     * 選択中サブカテゴリID取得処理
     * @return _selectedSubCateId を戻します。
     */
    public String getSelectedSubCateId() {
        String subCateId = selectedSubCateId;
        if (subCateId == null) {
            if (getListSubCategory() != null && getListSubCategory().size() > 0) {
                MstSubCategoryInfo mstSubCategory = (MstSubCategoryInfo) getListSubCategory().get(0); 
                subCateId = mstSubCategory.getSubCateId();
            }
        }
        return subCateId;
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
     * @param _selectedNengetu _selectedNengetu を設定。
     */
    public void setSelectedNengetu(String selectedNengetu) {
        this.selectedNengetu = selectedNengetu;
    }

    /**
     * @return _selectedNengetu を戻します。
     */
    public String getSelectedNengetu() {
        return selectedNengetu;
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

    public UIFormInfo getSelectedEntity() {
        if (getListForm() != null && getSelectedListIndex() >= 0) {
            if (getListForm().size() >= getSelectedListIndex()) {
                return (UIFormInfo) getListForm().get(getSelectedListIndex());
            }
        }
        return null;
    }
//    /**
//     * @param selectedEntity selectedEntity を設定。
//     */
//    public void setSelectedEntity(UIFormInfo selectedEntity) {
//        this.selectedEntity = selectedEntity;
//    }

//    /**
//     * @return selectedEntity を戻します。
//     */
//    public UIFormInfo getSelectedEntity() {
//        return selectedEntity;
//    }

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
     * 登録モードを取得します。
     * @return 登録モード
     */
    public int getRegMode() {
        return regMode;
    }
    
    /**
     * 登録モードを設定します。
     * @param regMode 登録モード
     */
    public void setRegMode(int regMode) {
        this.regMode = regMode;
    }
    
    /**
     * 関連文書を取得します。
     * @return 関連文書
     */
    public List getListKanrenBunsho() {
        return listKanrenBunsho;
    }
    
    /**
     * 関連文書を設定します。
     * @param listKanrenBunsho 関連文書
     */
    public void setListKanrenBunsho(List listKanrenBunsho) {
        this.listKanrenBunsho = listKanrenBunsho;
    }

    /**
     * 編集モード名称
     * @return String
     */
    public String getRegModeName() {
        String name = "";
        if (getRegMode() == 1) {
            name = "新規";
        }
        else if (getRegMode() == 2) {
            name = "編集";
        }
        else if (getRegMode() == 3) {
            name = "削除";
        }
        return name;
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
        setListForm(null);
        setSelectedListIndex(-1);
        setCateId(null);
        setNengetu(null);
        setListSubCategory(null);
        setListCategory(null);
        setListSubCategory(null);
    }
    
    /**
     * クリア処理
     * 　条件画面 新規、検索処理実行用
     *
     */
    public void clearCondInsOrSearch() {
        setListForm(null);
        setSelectedListIndex(-1);
        setListSubCategory(null);
        setFlgCondToReg(true);
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getTitle(){
        if(getRegMode() == REG_MODE_INSERT){
            return "フォーム登録";

        }
        return "フォーム登録　編集";
    }

    /**
     * /関連先として登録している文書を取得します。
     * @return /関連先として登録している文書 
     */
    public List getListKanrenSaki() {
        return listKanrenSaki;
    }
    /**
     * /関連先として登録している文書を設定します。
     * @param /関連先として登録している文書
     */
    public void setListKanrenSaki(List listKanrenSaki) {
        this.listKanrenSaki = listKanrenSaki;
    }
    
    /**
     * 関連先として登録している文書の件数を取得します。
     * @return 件数
     */
    public int getListKanrenSakiSize(){
        if(getListKanrenSaki() != null){
            return getListKanrenSaki().size();
        }
        return 0;
    }

}
