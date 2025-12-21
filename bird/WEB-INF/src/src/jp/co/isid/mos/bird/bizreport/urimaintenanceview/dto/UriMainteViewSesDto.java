/*
 * 作成日:2007/02/27
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.urimaintenanceview.common.UriMainteViewConstants;

/**
 * 売上修正確認セッションDTO
 * @author xwatanabe
 */
public class UriMainteViewSesDto {

    /** MAX WindowID */
    private int maxWindowId = 0;
    /** MaxSize */
    private int maxSize = 0;

    //----------------------------------------------
    // 共通データ部分(複数Window対応必要なしの項目)
    //----------------------------------------------
    /** ユーザID */
    private String userId;
    /** ユーザタイプ */
    private String userTypeCd;
    /** システム日付 */
    private String sysDate;
    /** 修正日リスト */
    private List syuseDateList;
    /** 会社リスト */
    private List companyList;
    /** 集計区分情報 */
    private List syukeiKbnList;
    /** 会計区分情報 */
    private List kaikeiKbnList;
    /** 集計区分別会計区分情報 */
    private Map kaikeiKbnMasterInfo;
    
    //------------------------------------
    // 複数ウィンドウ対応項目(検索条件)
    //------------------------------------
    /** 会社コード */
    private Map companyCd        = new LinkedHashMap();
    /** 修正日 */
    private Map syuseiDate       = new LinkedHashMap();

    //------------------------------------
    // 複数ウィンドウ対応項目(前回検索条件)
    //------------------------------------
    /** (前回検索条件)会社コード */
    private Map companyCdZen     = new LinkedHashMap();
    /** (前回検索条件)期間 */
    private Map syuseiDateZen    = new LinkedHashMap();


    //------------------------------------
    // 複数ウィンドウ対応項目(結果)
    //------------------------------------
    /** 表示タブインデックス */
    private Map tabIndexMain     = new LinkedHashMap();
    /** 表示サブタブインデックス */
    private Map tabIndexSub1     = new LinkedHashMap();
    /** 表示サブタブインデックス */
    private Map tabIndexSub2     = new LinkedHashMap();
    /** 表示サブタブインデックス */
    private Map tabIndexSub3     = new LinkedHashMap();
    /** 売上修正履歴 */
    private Map uriageSyuseiRirekiList      = new LinkedHashMap();
    /** 売上修正履歴 */
    private Map uriMainteHeader  = new LinkedHashMap();

    //------------------------------------
    // 複数ウィンドウ対応項目(その他)
    //------------------------------------
    /** データ存在フラグ */
    private Map existDataFlg     = new LinkedHashMap();
    /** 再検索フラグ */
    private Map researchFlg      = new LinkedHashMap();


    /////////////////////////////////////////////////////////////////////

    /**
     * 検索結果部分クリア
     */
    public void resultClear(int windowId){
        
        setExistDataFlg(windowId, false);
        setUriageSyuseiRirekiList(windowId, null);
        setTabIndexMain(windowId, UriMainteViewConstants.TAB_INDEX_URIAGE);
        setTabIndexSub1(windowId, UriMainteViewConstants.TAB_INDEX_SUB_MAEUKE);
        setTabIndexSub2(windowId, UriMainteViewConstants.TAB_INDEX_SUB_HANBAI);
        setTabIndexSub3(windowId, UriMainteViewConstants.TAB_INDEX_SUB_NEBIKI);
    }

    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        maxWindowId++;
        return maxWindowId;
    }

    /**
     * 指定したウィンドウIDのデータが存在するか返却します。
     */
    public boolean containsWindowId(int windowId) {
        Integer key = new Integer(windowId);
        return uriageSyuseiRirekiList.containsKey(key);
    }

    /**
     * maxSizeを取得する。
     */
    public int getMaxSize() {
        return maxSize;
    }
    /**
     * maxSizeを設定する。
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 複数ウィンドウ対応項目のデータセット
     * @param windowId
     * @param setData  セットしたいデータ
     * @param mapObj   セット対象のマップ
     */
    private void setData(int windowId, Object setData, Map mapObj){

        // 現在ウインドウIDのデータを保持している場合
        if (mapObj.containsKey(new Integer(windowId))) {
            // 順番を入れ替える為、前回データを削除する
            mapObj.remove(new Integer(windowId));
        // 最大数を超えた場合
        } else if (mapObj.size() > getMaxSize()) {
            // 最古データを削除
            mapObj.remove(mapObj.keySet().toArray()[0]);
        }
        // リスト設定
        mapObj.put(new Integer(windowId), setData);
    }
    /**
     * 複数ウィンドウ対応項目のデータゲット
     */
    private Object getData(int windowId, Map mapObj){
        Object obj = mapObj.get(new Integer(windowId));
        return obj;
    }

    ////////////セッター・ゲッター(複数ウィンドウ対応なし)//////////////

    /**
     * @return ユーザID を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param ユーザIDを設定する。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return ユーザタイプを戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    /**
     * @param ユーザタイプを設定する。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    /**
     * @return システム日付を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * @param システム日付を設定する。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * 修正日リストを取得する。
     */
    public List getSyuseDateList() {
        return syuseDateList;
    }
    /**
     * 修正日リストを設定する。
     */
    public void setSyuseDateList(List list) {
        this.syuseDateList = list;
    }

    /**
     * 会社リストを取得する。
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 会社リストを設定する
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    /**
     * 集計区分情報を戻します。
     * @return syukeiKbnList 集計区分情報
     */
    public List getSyukeiKbnList() {
        return syukeiKbnList;
    }

    /**
     * 集計区分情報を設定します。
     * @param syukeiKbnList 集計区分情報
     */
    public void setSyukeiKbnList(List syukeiKbnList) {
        this.syukeiKbnList = syukeiKbnList;
    }

    /**
     * 会計区分情報を戻します。
     * @return kaikeiKbnList 会計区分情報
     */
    public List getKaikeiKbnList() {
        return kaikeiKbnList;
    }

    /**
     * 会計区分情報を設定します。
     * @param kaikeiKbnList 会計区分情報
     */
    public void setKaikeiKbnList(List kaikeiKbnList) {
        this.kaikeiKbnList = kaikeiKbnList;
    }

    /**
     * 集計区分別会計区分情報を戻します。
     * @return kaikeiKbnMasterInfo 集計区分別会計区分情報
     */
    public Map getKaikeiKbnMasterInfo() {
        return kaikeiKbnMasterInfo;
    }

    /**
     * 集計区分別会計区分情報を設定します。
     * @param kaikeiKbnMasterInfo 集計区分別会計区分情報
     */
    public void setKaikeiKbnMasterInfo(Map kaikeiKbnMasterInfo) {
        this.kaikeiKbnMasterInfo = kaikeiKbnMasterInfo;
    }
    ////////////セッター・ゲッター(複数ウィンドウ対応あり・検索条件)//////////////

    /**
     * 会社コードをセットする。
     */
    public void setCompanyCd(int windowId, String str) {
        setData(windowId, str, companyCd);
    }
    /**
     * 会社コードを取得する。
     */
    public String getCompanyCd(int windowId) {
        return (String)getData(windowId, companyCd);
    }

    /**
     * 修正日をセットする。
     */
    public void setSyuseiDate(int windowId, String str) {
        setData(windowId, str, syuseiDate);
    }
    /**
     * 修正日を取得する。
     */
    public String getSyuseiDate(int windowId) {
        return (String)getData(windowId, syuseiDate);
    }


    ////////////セッター・ゲッター(複数ウィンドウ対応あり・前回検索条件)//////////////

    /**
     * (前回検索条件)会社コードをセットする。
     */
    public void setCompanyCdZen(int windowId, String str) {
        setData(windowId, str, companyCdZen);
    }
    /**
     * (前回検索条件)会社コードを取得する。
     */
    public String getCompanyCdZen(int windowId) {
        return (String)getData(windowId, companyCdZen);
    }

    /**
     * (前回検索条件)期間をセットする。
     */
    public void setSyuseiDateZen(int windowId, String str) {
        setData(windowId, str, syuseiDateZen);
    }
    /**
     * (前回検索条件)期間を取得する。
     */
    public String getSyuseiDateZen(int windowId) {
        return (String)getData(windowId, syuseiDateZen);
    }


    ////////////セッター・ゲッター(複数ウィンドウ対応あり・結果部分)//////////////

    /**
     * 売上修正履歴リストをセットする。
     */
    public void setUriageSyuseiRirekiList(int windowId, List list) {
        setData(windowId, list, uriageSyuseiRirekiList);
    }
    /**
     * 売上修正履歴リストを取得する。
     */
    public List getUriageSyuseiRirekiList(int windowId) {
        return (List)getData(windowId, uriageSyuseiRirekiList);
    }
    /**
     * 売上修正履歴リストサイズを取得する。
     */
    public int getUriageSyuseiRirekiListSize(int windowId) {

        List list = (List)uriageSyuseiRirekiList.get(new Integer(windowId));
        if(list == null){
            return 0;
        }else{
            return list.size();
        }
    }

    /**
     * 表示タブインデックス(メイン)をセットする。
     */
    public void setTabIndexMain(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), tabIndexMain);
    }
    /**
     * 表示タブインデックス(メイン)を取得する。
     */
    public int getTabIndexMain(int windowId) {
        Integer cnt = (Integer)getData(windowId, tabIndexMain);
        if(cnt == null){
            return 1;
        }
        return cnt.intValue();
    }

    /**
     * サブタブインデックス１をセットする。
     */
    public void setTabIndexSub1(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), tabIndexSub1);
    }
    /**
     * サブタブインデックス１を取得する。
     */
    public int getTabIndexSub1(int windowId) {
        Integer cnt = (Integer)getData(windowId, tabIndexSub1);
        if(cnt == null){
            return 0;
        }
        return cnt.intValue();
    }

    /**
     * サブタブインデックス２をセットする。
     */
    public void setTabIndexSub2(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), tabIndexSub2);
    }
    /**
     * サブタブインデックス２を取得する。
     */
    public int getTabIndexSub2(int windowId) {
        Integer cnt = (Integer)getData(windowId, tabIndexSub2);
        if(cnt == null){
            return 0;
        }
        return cnt.intValue();
    }

    /**
     * サブタブインデックス３をセットする。
     */
    public void setTabIndexSub3(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), tabIndexSub3);
    }
    /**
     * サブタブインデックス３を取得する。
     */
    public int getTabIndexSub3(int windowId) {
        Integer cnt = (Integer)getData(windowId, tabIndexSub3);
        if(cnt == null){
            return 0;
        }
        return cnt.intValue();
    }

    /**
     * ヘッダー項目をセットする。
     */
    public void setHeader(int windowId, UriMainteHeader header) {
        setData(windowId, header, uriMainteHeader);
    }
    /**
     * ヘッダー項目を取得する。
     */
    public UriMainteHeader getHeader(int windowId) {
        return (UriMainteHeader)getData(windowId, uriMainteHeader);
    }
    
    ////////////セッター・ゲッター(複数ウィンドウ対応あり・その他)//////////////

    /**
     * データ存在フラグをセットする。
     */
    public void setExistDataFlg(int windowId, boolean boo) {
        setData(windowId, new Boolean(boo), existDataFlg);
    }
    /**
     * データ存在フラグを取得する。
     */
    public boolean getExistDataFlg(int windowId) {
        Boolean boo = (Boolean)getData(windowId, existDataFlg);
        if(boo == null){
            return false;
        }
        return boo.booleanValue();
    }

    /**
     * 再検索フラグをセットする。
     */
    public void setResearchFlg(int windowId, boolean boo) {
        setData(windowId, new Boolean(boo), researchFlg);
    }
    /**
     * 再検索フラグを取得する。
     */
    public boolean isResearchFlg(int windowId) {
        Boolean boo = (Boolean)getData(windowId, researchFlg);
        if(boo == null){
            return false;
        }
        return boo.booleanValue();
    }
}