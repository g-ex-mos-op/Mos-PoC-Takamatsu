package jp.co.isid.mos.bird.inforegist.documentregist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstSubCategoryInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;

/**
 * 文書登録条件画面DTO
 * @author xnkusama
 */
public class DocumentRegistConditionDto implements DownloadDto {

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
//    /* 添付ファイル 選択インデックス */
//    private int _selectedAttachFileIndex;
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
    /* 文書一覧 */
    private List listBunsho;
    /* 関連文書 */
    private List listKanrenBunsho;
    /* 現在ページ番号 */
    private int currentPageNumber;
    private int count;
   
    
    /* 条件-->編集遷移フラグ */
    private boolean flgCondToReg = false;
    /* 編集モード （１：新規  ２：更新  ３：削除）*/
    private int regMode;
    
    
// 2006/03/06 xkhata タブ切替対策
    private String firstNengetsu;
    private String firstCateId;
    
    // 順位設定画面から遷移を判別するフラグ
    private boolean flgFromZyuni = false;
    
    //関連先として登録している文書
    private List listKanrenSaki;

    
    public void setFirstNengetsu(String firstNengetu) {
    	this.firstNengetsu = firstNengetu;
    }
    public String getFirstNengetsu() {
    	return this.firstNengetsu;
    }
    
    public void setFirstCateId(String firstCateId) {
    	this.firstCateId = firstCateId;
    }
    public String getFirstCateId() {
    	return this.firstCateId;
    }
//
    /**
     * カテゴリ一覧リスト設定処理
     * @param _listCategory _listCategory を設定。
     */
    public void setListCategory(List listCategory) {
        this.listCategory = listCategory;
    }

    /**
     * カテゴリ一覧リスト設定処理
     * @return _listCategory を戻します。
     */
    public List getListCategory() {
        return listCategory;
    }

    /**
     * サブカテゴリ一覧リスト設定処理
     * @return _listSubCategory を戻します。
     */
    public List getListSubCategory() {
        return listSubCategory;
    }

    /**
     * サブカテゴリ一覧リスト設定処理
     * @param _listSubCategory _listSubCategory を設定。
     */
    public void setListSubCategory(List listSubCategory) {
        this.listSubCategory = listSubCategory;
        setListTabSubCate(listSubCategory);
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
     * 1インデックスにつき１行分(5個のサブカテゴリ)の
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
     * カテゴリID設定処理
     * @param _cateId _cateId を設定。
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    /**
     * カテゴリID取得処理
     * @return _cateId を戻します。
     */
    public String getCateId() {
        return cateId;
    }

    /**
     * サブカテゴリID設定処理
     * @param _cateId _cateId を設定。
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

//    /**
//     * SQL LIKE文用の年月取得処理 
//     * @return _nengetu を戻します。
//     */
//    public String getNengetuSql() {
//        return _nengetu + "%";
//    }

    /**
     * 文書一覧設定処理
     * @param _listBunsho _listBunsho を設定。
     */
    public void setListBunsho(List listBunsho) {
        this.listBunsho = listBunsho;
    }

    /**
     * 文書一覧取得処理
     * @return _listBunsho を戻します。
     */
    public List getListBunsho() {
        return listBunsho;
    }
    
    /**
     * 文書一覧の件数を取得します。
     * @return int 件数
     */
    public int getListBunshoSize() {
        int size = 0;
        if (getListBunsho() != null) {
            size = getListBunsho().size();
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
    
//    /**
//     * @param _selectedAttachFileIndex _selectedAttachFileIndex を設定。
//     */
//    public void setSelectedAttachFileIndex(int _selectedAttachFileIndex) {
//        this._selectedAttachFileIndex = _selectedAttachFileIndex;
//    }

    /**
     * @return _selectedAttachFileIndex を戻します。
     */
    public int getSelectedAttachFileIndex() {
        //return _selectedAttachFileIndex;
        UIBunshoInfo entity = (UIBunshoInfo) getListBunsho().get(getDownloadIndex());
        return entity.getDownloadAttachIndex();
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

    public UIBunshoInfo getSelectedEntity() {
        if (getListBunsho() != null && getSelectedListIndex() >= 0) {
            if (getListBunsho().size() >= getSelectedListIndex()) {
                return (UIBunshoInfo) getListBunsho().get(getSelectedListIndex());
            }
        }
        return null;
    }
//    /**
//     * @param selectedEntity selectedEntity を設定。
//     */
//    public void setSelectedEntity(UIBunshoInfo selectedEntity) {
//        this.selectedEntity = selectedEntity;
//    }

//    /**
//     * @return selectedEntity を戻します。
//     */
//    public UIBunshoInfo getSelectedEntity() {
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
     * @return regMode を戻します。
     */
    public int getRegMode() {
        return regMode;
    }
    /**
     * @param regMode regMode を設定。
     */
    public void setRegMode(int regMode) {
        this.regMode = regMode;
    }
    
    /**
     * @return listKanrenBunsho を戻します。
     */
    public List getListKanrenBunsho() {
        return listKanrenBunsho;
    }
    /**
     * @param listKanrenBunsho listKanrenBunsho を設定。
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
     * @param downloadIndex downloadIndex を設定。
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * @return downloadIndex を戻します。
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    
    /**
     * クリア処理
     * 　編集画面-->条件画面 遷移用
     *
     */
    public void clearRegistToCond() {
        // 条件画面の文書リスト
        setListBunsho(null);
        setSelectedListIndex(-1);
        setCateId(null);
        setNengetu(null);
        setListSubCategory(null);
        setListBunsho(null);
        setListCategory(null);
        setListSubCategory(null);
    }
    
    /**
     * クリア処理
     * 　条件画面 新規、検索処理実行用
     *
     */
    public void clearCondInsOrSearch() {
        setListBunsho(null);
        setSelectedListIndex(-1);
        setListSubCategory(null);
        setFlgCondToReg(true);
    }
	public boolean isFlgFromZyuni() {
		return flgFromZyuni;
	}
	public void setFlgFromZyuni(boolean flgFromZyuni) {
		this.flgFromZyuni = flgFromZyuni;
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
