package jp.co.isid.mos.bird.inforegist.notificationregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;

/**
 * 通達登録条件画面DTO
 * @author m.onodera
 */
public class ConditionDto implements DownloadDto {

    /** 編集モード：新規 */
    public static final int REG_MODE_INSERT = 1;
    /** 編集モード：編集 */
    public static final int REG_MODE_UPDATE = 2;
    /** 編集モード：削除 */
    public static final int REG_MODE_DELETE = 3;
    
    // 1ページの表示最大件数
    private static final int PAGE_MAX_SIZE = 30;
    
    /* カテゴリID */
    private String _cateId;
    /* 年月 */
    private String _nengetu;
    /* 管理番号 */
    private String _kanriNo;
    
    /* 選択カテゴリID */
    private String _selectedCateId;
    /* 選択中の文書リストインデックス */
    private int _selectedListIndex = -1;
    /* ダウンロードアクションを実行したリスト内のIndex */
    private int downloadIndex;
    /* 検索時選択年月 */
    private String _selectedNengetu;
    /* 検索時管理番号 */
    private String _selectedKanriNo;

    
    /* カテゴリ一覧 */
    private List _listCategory;
    /* 年月一覧 */
    private List _listNengetu;
    /* 通達一覧 */
    private List _listTutatu;
    /* 関連文書 */
    private List listKanrenBunsho;
    /* 現在ページ番号 */
    private int currentPageNumber;
    /* 件数 */
    private int count;
   
    /* 選択Entity */
//    private UITutatuInfo selectedEntity;
    
    /* 条件-->編集遷移フラグ */
    private boolean flgCondToReg = false;
    /* 編集モード （１：新規  ２：更新  ３：削除）*/
    private int regMode;
    
    /**
     * カテゴリ一覧リスト設定処理
     * @param _listCategory _listCategory を設定。
     */
    public void setListCategory(List _listCategory) {
        this._listCategory = _listCategory;
    }
    /**
     * カテゴリ一覧リスト設定処理
     * @return _listCategory を戻します。
     */
    public List getListCategory() {
        return _listCategory;
    }
    /**
     * カテゴリID設定処理
     * @param _cateId _cateId を設定。
     */
    public void setCateId(String _cateId) {
        this._cateId = _cateId;
    }
    /**
     * カテゴリID取得処理
     * @return _cateId を戻します。
     */
    public String getCateId() {
        return _cateId;
    }
    /**
     * 年月一覧リスト設定処理
     * @param _listNengetu _listNengetu を設定。
     */
    public void setListNengetu(List _listNengetu) {
        this._listNengetu = _listNengetu;
    }
    /**
     * 年月一覧リスト取得処理
     * @return _listNengetu を戻します。
     */
    public List getListNengetu() {
        return _listNengetu;
    }
    /**
     * 年月設定処理
     * @param _nengetu _nengetu を設定。
     */
    public void setNengetu(String _nengetu) {
        this._nengetu = _nengetu;
    }
    /**
     * 年月取得処理
     * @return _nengetu を戻します。
     */
    public String getNengetu() {
        return _nengetu;
    }
    /**
     * 管理番号設定処理
     * @param _kanriNo _kanriNo を設定。
     */
    public void setKanriNo(String _kanriNo) {
        this._kanriNo = _kanriNo;
    }
    /**
     * 管理番号取得処理
     * @return _kanriNo を戻します。
     */
    public String getKanriNo() {
        return _kanriNo;
    }
    /**
     * SQL LIKE文用の年月取得処理 
     * @return _nengetu を戻します。
     */
//    public String getNengetuSql() {
//        return _nengetu + "%";
//    }

    
    
    
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
    /**
     * 通達一覧設定処理
     * @param _listTutatu _listTutatu を設定。
     */
    public void setListTutatu(List _listTutatu) {
        this._listTutatu = _listTutatu;
    }
    /**
     * 通達一覧取得処理
     * @return _listTutatu を戻します。
     */
    public List getListTutatu() {
        return _listTutatu;
    }
    /**
     * 通達一覧の件数を取得します。
     * @return int 件数
     */
    public int getListTutatuSize() {
        int size = 0;
        if (getListTutatu() != null) {
            size = getListTutatu().size();
        }
        return size;
    }

    /**
     * @param _selectedListIndex _selectedListIndex を設定。
     */
    public void setSelectedListIndex(int _selectedListIndex) {
        this._selectedListIndex = _selectedListIndex;
    }
    /**
     * @return _selectedListIndex を戻します。
     */
    public int getSelectedListIndex() {
        return _selectedListIndex;
    }
    
    /**
     * カテゴリーID（SQL用指定条件）
     * @param _selectedCateId _selectedCateId を設定。
     */
    public void setSelectedCateId(String _selectedCateId) {
        this._selectedCateId = _selectedCateId;
    }
    /**
     * カテゴリーID（SQL用指定条件）
     * @return _selectedCateId を戻します。
     */
    public String getSelectedCateId() {
        return _selectedCateId;
    }
    /**
     * 対象年月（SQL用指定条件）
     * @param _selectedNengetu _selectedNengetu を設定。
     */
    public void setSelectedNengetu(String _selectedNengetu) {
        this._selectedNengetu = _selectedNengetu;
    }
    /**
     * 対象年月（SQL用指定条件）
     * @return _selectedNengetu を戻します。
     */
    public String getSelectedNengetu() {
        return _selectedNengetu;
    }
    /**
     * 管理番号（SQL用指定条件）
     * @param _selectedKanriNo _selectedKanriNo を設定。
     */
    public void setSelectedKanriNo(String _selectedKanriNo) {
        this._selectedKanriNo = _selectedKanriNo;
    }
    /**
     * 管理番号（SQL用指定条件）
     * @return _selectedKanriNo を戻します。
     */
    public String getSelectedKanriNo() {
        return _selectedKanriNo;
    }    
    /**
     * 編集モード
     * @return regMode を戻します。
     */
    public int getRegMode() {
        return regMode;
    }
    /**
     * 編集モード
     * @param regMode regMode を設定。
     */
    public void setRegMode(int regMode) {
        this.regMode = regMode;
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

    public UITutatuInfo getSelectedEntity() {
        if (getListTutatu() != null && getSelectedListIndex() >= 0) {
            if (getListTutatu().size() >= getSelectedListIndex()) {
                return (UITutatuInfo) getListTutatu().get(getSelectedListIndex());
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
     * クリア処理
     * 　確認画面-->条件画面 遷移用
     *
     */
    public void clearRegistToCond() {
        // 条件画面の文書リスト
        setListTutatu(null);
        // カテゴリーリスト
        setSelectedListIndex(-1);
        setCateId(null);
        setNengetu(null);
        setKanriNo(null);
    }
    /**
     * クリア処理
     * 　条件画面 新規、検索処理実行用
     *
     */
    public void clearCondInsOrSearch() {
        setListTutatu(null);
        setSelectedListIndex(-1);
        //setUiBunshoInfo(null);
    }
}
