/*
 * 作成日:2007/02/06
 */
package jp.co.isid.mos.bird.storemanage.posniporef.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.storemanage.posniporef.common.PosNipoRefConstants;

/**
 * POS日報DTO(SESSION)
 * @author xwatanabe
 */
public class PosNipoSessionDto implements CsvOutputDto {

	/** windowId */
	private int windowId = 0;
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
    /** アプリ日付 */
    private String appDate;
    /** 制限区分 */
    private boolean limitFlg;
    /** 期間リスト */
    private List kikanList;

    //本部・オーナーユーザ共通
    /** 会社リスト */
    private List companyList;
    
    //オーナーユーザ用
    /** オーナーコードMAP(オーナーユーザ用) */
    private Map onerMap;
    /** 店舗リストMAP */
    private Map miseListMap;

    //店舗ユーザ用
    /** 会社コード(店舗ユーザ用) */
    private String companyCdTenpoUser;
    /** 会社名称(店舗ユーザ用) */
    private String companyNameTenpoUser;
    /** 店舗コード(店舗ユーザ用) */
    private String miseCdTenpoUser;
    /** 店舗名称(店舗ユーザ用) */
    private String miseNameTenpoUser;

    //------------------------------------
    // 複数ウィンドウ対応項目(検索条件)
    //------------------------------------
    /** 会社コード */
    private Map companyCd        = new LinkedHashMap();
    /** 期間 */
    private Map kikan            = new LinkedHashMap();
    /** 店舗コード */
    private Map miseCd           = new LinkedHashMap();
    /** オーナーコード */
    private Map onerCd           = new LinkedHashMap();
    /** 検索区分(店舗・オーナーコード・全店) */
    private Map kbn              = new LinkedHashMap();

    //------------------------------------
    // 複数ウィンドウ対応項目(前回検索条件)
    //------------------------------------
    /** (前回検索条件)会社コード */
    private Map companyCdZen     = new LinkedHashMap();
    /** (前回検索条件)期間 */
    private Map kikanZen         = new LinkedHashMap();
    /** (前回検索条件)店舗コード */
    private Map miseCdZen        = new LinkedHashMap();
    /** (前回検索条件)オーナーコード */
    private Map onerCdZen        = new LinkedHashMap();
    /** (前回検索条件)検索区分(店舗・オーナーコード・全店) */
    private Map kbnZen           = new LinkedHashMap();

    //------------------------------------
    // 複数ウィンドウ対応項目(結果)
    //------------------------------------
    /** 取得店舗数 */
    private Map tenpoCnt         = new LinkedHashMap();
    /** POS日報情報 */
    private Map posNipoList      = new LinkedHashMap();
    /** 表示タブインデックス */
    private Map tabIndex         = new LinkedHashMap();
    /** 表示サブタブインデックス */
    private Map subTabIndex      = new LinkedHashMap();
    /** 表示用：会社名称 */
    private Map dispCompanyName  = new LinkedHashMap();
    /** 表示用：期間(YYYY年MM月) */
    private Map dispKikan        = new LinkedHashMap();
    /** 表示用：店舗名称 */
    private Map dispMiseName     = new LinkedHashMap();
    /** 表示用：取得店舗数 */
    private Map dispTenpoCnt     = new LinkedHashMap();

    //------------------------------------
    // 複数ウィンドウ対応項目(その他)
    //------------------------------------
    /** データ存在フラグ */
    private Map existDataFlg      = new LinkedHashMap();
    /** 再検索フラグ */
    private Map researchFlg      = new LinkedHashMap();
    /** 店リスト */
    private Map miseList         = new LinkedHashMap();


    /////////////////////////////////////////////////////////////////////

    /**
     * 検索結果部分クリア
     */
    public void resultClear(int windowId){
        
        setExistDataFlg(windowId, false);
        setDispCompanyName(windowId, "");
        setDispKikan(windowId, "");
        setDispMiseName(windowId, "");
        setDispTenpoCnt(windowId, "");
        setTenpoCnt(windowId, 0);
        setPosNipoList(windowId, null);
        setTabIndex(windowId, PosNipoRefConstants.TAB_INDEX_NIPO);
        setSubTabIndex(windowId, PosNipoRefConstants.SUB_TAB_INDEX_JITU);
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
        return posNipoList.containsKey(key);
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
     * @return アプリ日付を戻します。
     */
    public String getAppDate() {
        return appDate;
    }
    /**
     * @param アプリ日付を設定する。
     */
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    /**
     * 制限区分を戻します。
     */
    public boolean isLimitFlg() {
        return limitFlg;
    }
    /**
     * 制限区分を設定する。
     */
    public void setLimitFlg(boolean limitFlg) {
        this.limitFlg = limitFlg;
    }

    /**
     * 期間リストを取得する。
     */
    public List getKikanList() {
        return kikanList;
    }
    /**
     * 期間リストを設定する。
     */
    public void setKikanList(List list) {
        this.kikanList = list;
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
     * onerMapを戻します。
     */
    public Map getOnerMap() {
        return onerMap;
    }
    /**
     * onerMapを設定する。
     */
    public void setOnerMap(Map onerMap) {
        this.onerMap = onerMap;
    }
    
    /**
     * 店舗リストMAPを戻します。
     */
    public Map getMiseListMap() {
        return miseListMap;
    }
    /**
     * 店舗リストMAPを設定する。
     */
    public void setMiseListMap(Map miseListMap) {
        this.miseListMap = miseListMap;
    }

    /**
     * 会社コード(店舗ユーザ用)を戻します。
     */
    public String getCompanyCdTenpoUser() {
        return companyCdTenpoUser;
    }
    /**
     * 会社コード(店舗ユーザ用)を設定する。
     */
    public void setCompanyCdTenpoUser(String companyCdTenpoUser) {
        this.companyCdTenpoUser = companyCdTenpoUser;
    }


    /**
     * 会社名称(店舗ユーザ用)を戻します。
     */
    public String getCompanyNameTenpoUser() {
        return companyNameTenpoUser;
    }

    /**
     * 会社名称(店舗ユーザ用)を設定する。
     */
    public void setCompanyNameTenpoUser(String companyNameTenpoUser) {
        this.companyNameTenpoUser = companyNameTenpoUser;
    }

    /**
     * 店コード(店舗ユーザ用)を戻します。
     */
    public String getMiseCdTenpoUser() {
        return miseCdTenpoUser;
    }
    /**
     * 店コード(店舗ユーザ用)を設定する。
     */
    public void setMiseCdTenpoUser(String miseCdTenpoUser) {
        this.miseCdTenpoUser = miseCdTenpoUser;
    }

    /**
     * 店舗名称(店舗ユーザ用)を戻します。
     */
    public String getMiseNameTenpoUser() {
        return miseNameTenpoUser;
    }
    /**
     * 店舗名称(店舗ユーザ用)を設定する。
     */
    public void setMiseNameTenpoUser(String miseNameTenpoUser) {
        this.miseNameTenpoUser = miseNameTenpoUser;
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
     * 期間をセットする。
     */
    public void setKikan(int windowId, String str) {
        setData(windowId, str, kikan);
    }
    /**
     * 期間を取得する。
     */
    public String getKikan(int windowId) {
        return (String)getData(windowId, kikan);
    }

    /**
     * 店舗コードをセットする。
     */
    public void setMiseCd(int windowId, String str) {
        setData(windowId, str, miseCd);
    }
    /**
     * 店舗コードを取得する。
     */
    public String getMiseCd(int windowId) {
        return (String)getData(windowId, miseCd);
    }

    /**
     * オーナーコードをセットする。
     */
    public void setOnerCd(int windowId, String list) {
        setData(windowId, list, onerCd);
    }
    /**
     * オーナーコードを取得する。
     */
    public String getOnerCd(int windowId) {
        return (String)getData(windowId, onerCd);
    }

    /**
     * 検索区分をセットする。
     */
    public void setKbn(int windowId, String str) {
        setData(windowId, str, kbn);
    }
    /**
     * 検索区分を取得する。
     */
    public String getKbn(int windowId) {
        return (String)getData(windowId, kbn);
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
    public void setKikanZen(int windowId, String str) {
        setData(windowId, str, kikanZen);
    }
    /**
     * (前回検索条件)期間を取得する。
     */
    public String getKikanZen(int windowId) {
        return (String)getData(windowId, kikanZen);
    }

    /**
     * (前回検索条件)店舗コードをセットする。
     */
    public void setMiseCdZen(int windowId, String str) {
        setData(windowId, str, miseCdZen);
    }
    /**
     * (前回検索条件)店舗コードを取得する。
     */
    public String getMiseCdZen(int windowId) {
        return (String)getData(windowId, miseCdZen);
    }

    /**
     * (前回検索条件)オーナーコードをセットする。
     */
    public void setOnerCdZen(int windowId, String list) {
        setData(windowId, list, onerCdZen);
    }
    /**
     * (前回検索条件)オーナーコードを取得する。
     */
    public String getOnerCdZen(int windowId) {
        return (String)getData(windowId, onerCdZen);
    }

    /**
     * (前回検索条件)検索区分をセットする。
     */
    public void setKbnZen(int windowId, String str) {
        setData(windowId, str, kbnZen);
    }
    /**
     * (前回検索条件)検索区分を取得する。
     */
    public String getKbnZen(int windowId) {
        return (String)getData(windowId, kbnZen);
    }

    ////////////セッター・ゲッター(複数ウィンドウ対応あり・結果部分)//////////////

    /**
     * 取得店舗数をセットする。
     */
    public void setTenpoCnt(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), tenpoCnt);
    }
    /**
     * 取得店舗数を取得する。
     */
    public int getTenpoCnt(int windowId) {
        Integer cnt = (Integer)getData(windowId, tenpoCnt);
        if(cnt == null){
            return 0;
        }
        return cnt.intValue();
    }

    /**
     * POS日報リストをセットする。
     */
    public void setPosNipoList(int windowId, List list) {
        setData(windowId, list, posNipoList);
    }
    /**
     * POS日報リストを取得する。
     */
    public List getPosNipoList(int windowId) {
        return (List)getData(windowId, posNipoList);
    }
    /**
     * POS日報リストサイズを取得する。
     */
    public int getPosNipoListSize(int windowId) {

        List list = (List)posNipoList.get(new Integer(windowId));
        if(list == null){
            return 0;
        }else{
            return list.size();
        }
    }

    /**
     * 表示タブインデックスをセットする。
     */
    public void setTabIndex(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), tabIndex);
    }
    /**
     * 表示タブインデックスを取得する。
     */
    public int getTabIndex(int windowId) {
        Integer cnt = (Integer)getData(windowId, tabIndex);
        if(cnt == null){
            return 1;
        }
        return cnt.intValue();
    }

    /**
     * 表示サブタブインデックスをセットする。
     */
    public void setSubTabIndex(int windowId, int cnt) {
        setData(windowId, new Integer(cnt), subTabIndex);
    }
    /**
     * 表示サブタブインデックスを取得する。
     */
    public int getSubTabIndex(int windowId) {
        Integer cnt = (Integer)getData(windowId, subTabIndex);
        if(cnt == null){
            return 0;
        }
        return cnt.intValue();
    }

    /**
     * 表示用：会社名称をセットする。
     */
    public void setDispCompanyName(int windowId, String str) {
        setData(windowId, str, dispCompanyName);
    }
    /**
     * 表示用：会社名称を取得する。
     */
    public String getDispCompanyName(int windowId) {
        return (String)getData(windowId, dispCompanyName);
    }

    /**
     * 表示用：期間(YYYY年MM月)をセットする。
     */
    public void setDispKikan(int windowId, String str) {
        setData(windowId, str, dispKikan);
    }
    /**
     * 表示用：期間(YYYY年MM月)を取得する。
     */
    public String getDispKikan(int windowId) {
        return (String)getData(windowId, dispKikan);
    }

    /**
     * 表示用：店舗名称をセットする。
     */
    public void setDispMiseName(int windowId, String str) {
        setData(windowId, str, dispMiseName);
    }
    /**
     * 表示用：店舗名称を取得する。
     */
    public String getDispMiseName(int windowId) {
        return (String)getData(windowId, dispMiseName);
    }

    /**
     * 表示用：取得店舗数をセットする。
     */
    public void setDispTenpoCnt(int windowId, String str) {
        setData(windowId, str, dispTenpoCnt);
    }
    /**
     * 表示用：取得店舗数を取得する。
     */
    public String getDispTenpoCnt(int windowId) {
        return (String)getData(windowId, dispTenpoCnt);
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
    
    /**
     * 店リストをセットする。
     */
    public void setMiseList(int windowId, List list) {
        setData(windowId, list, miseList);
    }
    /**
     * 店リストを取得する。
     */
    public List getMiseList(int windowId) {
        return (List)getData(windowId, miseList);
    }

	public int getWindowId() {
		return windowId;
	}

	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

}