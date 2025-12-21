/*
 * 作成日: 2006/09/19
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * モスチキン販売状況一覧DTO
 * 
 * @author xlee
 */
public class MosChickenSaleStateViewDto  {

	/**
     * ユーザータイプ
     */
    private String userTypeCd;
    
    /**
     * アプリ日付
     */
    private String appDate;
    
    /**
     * ユーザID
     */   
    private String userId;
        
    /**
     * 商品グループリストが格納されているMAP
     */
    private Map condMapShohinGroup;
     
    /**
     *　会社コード
     */
    private String condCompanyCd;
    
	/**
	* アクション区分
	*/
    private String actionKbn;
    
    //-----
    //複数Window対応
    //-----
	/**
     * ウィンドウID
     */
    private int windowId = 0;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    
	/**
	 * オーナーコード保持マップ
	 */	
	private Map condOnerCdMap = new HashMap();
	
	/**
	 * タイトルコード保持マップ
	 */
	private Map condTitleCdMap = new HashMap();
	
	/**
	 * 対象期間開始日保持マップ
	 */
	private Map condTaishoKikanCdFrMap = new HashMap();
	
	/**
	 * 対象期間終了日保持マップ
	 */
	private Map condTaishoKikanCdToMap = new HashMap();
	
	/**
	 * 対象店舗コード保持マップ
	 */
	private Map condTenpoCdMap = new HashMap();
	
	/**
	 * 商品グループコード保持マップ
	 */	
	private Map condShohinGroupCdMap = new HashMap();
	
	/**
	 * 結果タイトル保持マップ
	 */	
	private Map resultTitleListMap = new HashMap();
	
	/**
	 * 結果合計保持マップ
	 */	
	private Map resultSumListMap = new HashMap();
	
	/**
	 * 結果詳細保持マップ
	 */	
	private Map resultDetailListMap = new HashMap();
	
	/**
	 * 全体結果保持マップ
	 */	
	private Map pageListMap = new HashMap();
	
	/**
	 * 頁遷移区分保持マップ
	 */	
	private Map pageKbnMap = new HashMap();
	
	/**
	 * 頁表示日付保持マップ
	 */	
	private Map pageDtMap = new HashMap();
	
	/**
	 * 頁最初日付保持マップ
	 */	
	private Map pageFirstDtMap = new HashMap();
	
	/**
	 * 頁ボタンコントロール値保持マップ
	 */	
	private Map pageBtControlMap = new HashMap();
	
	/**
	 * 対象店舗リスト保持マップ
	 */	
	private Map condListTenpoMap = new HashMap();
	
	 /**
	 * 対象期間リスト
	 */    
	private Map condListTaishoKikanMap = new HashMap();

	/**
	 * 商品グループリスト
	 */    
	private Map condListShohinGroupMap = new HashMap();
	
	/**
	 * タイトルグループリスト
	 */    
	private Map condListTitleMap = new HashMap();
	
    
    /**
     * 初期化する
     */
	public void clear(){
		setUserTypeCd(null);
		setAppDate(null);
		setUserId(null);
		setCondCompanyCd(null);
		setActionKbn(null);
	}
	
    /**
     * 初期化する
     */
	public void allClear(){
		setUserTypeCd(null);
		setAppDate(null);
		setUserId(null);
		setCondOnerCd(null);
		setCondCompanyCd(null);
		setCondListTitle(null);
		setCondTitleCd(null);
		setCondListTaishoKikan(null);
		setCondTaishoKikanCdFr(null);
		setCondTaishoKikanCdTo(null);
		setCondListTenpo(null);
		setCondTenpoCd(null);
		setCondListShohinGroup(null);
		setCondShohinGroupCd(null);
		setActionKbn(null);
		setResultTitleList(null);
		setResultSumList(null);
		setResultDetailList(null);
		setPageList(null);
		setPageKbn(null);
		setPageDt(null);
		setPageFirstDt(null);
	}
	
    /**
     * 初期化する
     */
	public void serchCondClear(){
		//検索条件選択初期化する
		setCondShohinGroupCd(null);
		setCondTenpoCd(null);
		setCondTaishoKikanCdFr(null);
		setCondTaishoKikanCdTo(null);
	}
	
    /**
     * ユーザータイプを取得します。
     * @return ユーザータイプ
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }

    /**
     * ユーザータイプをを設定します。
     * @param userTypeCd ユーザータイプ
     */
    public void setUserTypeCd(String userTypeCd) {
    	this.userTypeCd = userTypeCd;
    }
    
    /**
     * アプリ日付を取得します。
     * @return アプリ日付
     */
    public String getAppDate() {
        return appDate;
    }

    /**
     * アプリ日付をを設定します。
     * @param appDate アプリ日付
     */
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }
    
    /**
     * ユーザIDを取得します。
     * @return  ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDをを設定します。
     * @param condUserId ユーザID
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }
    
    /**
     * タイトルリストを取得します。
     * @return　タイトルプルダウン用Listリスト
     */
    public List getCondListTitle() {
        return (List) condListTitleMap.get(new Integer(getWindowId()));
    }
    
    /**
     * タイトルリストを設定します。
     * @param condSeikyuSakiList お買上明細
     */
    public void setCondListTitle(List condListTitle) {
    	this.condListTitleMap.put(new Integer(getWindowId()), condListTitle);
    }

    /**
     * タイトルのサイズを設定します。
     * @param  タイトルのサイズ
     */   
	public int getTitleListSize(){
		if(getCondListTitle() == null){
			return 0;
		}else{
			return getCondListTitle().size();
		}
	}
    
    /**
     * 選択されたタイトルのコードを取得します。
     * @return　選択された対象期間
     */
    public String getCondTitleCd() {
        
        return (String) condTitleCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     *  選択されたタイトルのコードを設定します。
     * @param condTaishoKikanCd 対象期間
     */
    public void setCondTitleCd(String condTitleCd) {
    	this.condTitleCdMap.put(new Integer(getWindowId()), condTitleCd);
    }
    
    //--各タイトル毎の保持用
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public List getCondListShohinGroup() {
    	return (List) condListShohinGroupMap.get(new Integer(getWindowId()));
    }

    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setCondListShohinGroup(List condListShohinGroup) {
    	this.condListShohinGroupMap.put(new Integer(getWindowId()), condListShohinGroup);
    }
    
    /**
     * 検索結果全体リストのサイズを設定します。
     * @param 検索結果全体リストのサイズ
     */   
	public int getShohinGroupListSize(){ 
		if(getCondListShohinGroup() == null){
			return 0;
		}else{
			return getCondListShohinGroup().size();
		}
	}
    
    /**
     * 商品グループリストのコードを取得します。
     * @return ユーザータイプ
     */
    public String getCondShohinGroupCd() {
        return (String) condShohinGroupCdMap.get(new Integer(getWindowId()));
    }

    /**
     *  商品グループリストのコードをを設定します。
     * @param condShohinGroupCd  商品グループリストのコード
     */
    public void setCondShohinGroupCd(String condShohinGroupCd) {
    	this.condShohinGroupCdMap.put(new Integer(getWindowId()), condShohinGroupCd);
    }
    
    /**
     * 対象期間リストを取得します。
     * @return 対象期間リスト
     */
    public List getCondListTaishoKikan() {
    	return (List) condListTaishoKikanMap.get(new Integer(getWindowId()));
    }

    /**
     * 対象期間リストをを設定します。
     * @param condListTaishoKikan 対象期間リスト
     */
    public void setCondListTaishoKikan(List condListTaishoKikan) {
    	this.condListTaishoKikanMap.put(new Integer(getWindowId()), condListTaishoKikan);
    }
    
    /**
     * 選択された対象期間終了日を取得します。
     * @return　選択された対象期間終了日
     */
    public String getCondTaishoKikanCdFr() {
    	return (String) condTaishoKikanCdFrMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 選択された対象期間終了日を設定します。
     * @param condTaishoKikanCd 対象期間終了日
     */
    public void setCondTaishoKikanCdFr(String condTaishoKikanCdFr) {
    	this.condTaishoKikanCdFrMap.put(new Integer(getWindowId()), condTaishoKikanCdFr);
    }
    
    /**
     * 選択された対象期間開始日を取得します。
     * @return 選択された対象期間開始日
     */
    public String getCondTaishoKikanCdTo() {
    	return (String) condTaishoKikanCdToMap.get(new Integer(getWindowId()));
    }

    /**
     * 選択された対象期間開始日を設定します。
     * @param condTaishoKikanCdTo 選択された対象期間開始日
     */
    public void setCondTaishoKikanCdTo(String condTaishoKikanCdTo) {
    	this.condTaishoKikanCdToMap.put(new Integer(getWindowId()), condTaishoKikanCdTo);
    }
    
    /**
     * 店舗リストを取得します。
     * @return 店舗リスト
     */
    public List getCondListTenpo() {
    	return (List) condListTenpoMap.get(new Integer(getWindowId()));
    }

    /**
     * 店舗リストをを設定します。
     * @param condListTenpo 店舗リスト
     */
    public void setCondListTenpo(List condListTenpo) {
    	this.condListTenpoMap.put(new Integer(getWindowId()), condListTenpo);
    }
    
    /**
     * 店舗リストのサイズを設定します。
     * @param  店舗リストのサイズ
     */   
	public int getTenpoListSize(){
		if(getCondListTenpo() == null){
			return 0;
		}else{
			return getCondListTenpo().size();
		}
	}
    
    /**
     * 検索結果全体の内容を保持したリストを取得を設定します。
     * @return 検索結果全体リスト
     */
    public List getResultTitleList() {
        return (List) resultTitleListMap.get(new Integer(getWindowId())); 
    }

    /**
     *検索結果全体リストをを設定します。
     * @param resultList 検索結果全体リスト
     */
    public void setResultTitleList(List resultTitleList) {
        this.resultTitleListMap.put(new Integer(getWindowId()), resultTitleList);
    }

    /**
     * 検索結果全体の内容を保持したリストを取得を設定します。
     * @return 検索結果全体リスト
     */
    public List getResultSumList() {
        return (List) resultSumListMap.get(new Integer(getWindowId())); 
    }

    /**
     *検索結果全体リストをを設定します。
     * @param resultList 検索結果全体リスト
     */
    public void setResultSumList(List resultSumList) {
    	this.resultSumListMap.put(new Integer(getWindowId()), resultSumList);
    }
    
    /**
     * 検索結果全体の内容を保持したリストを取得を設定します。
     * @return 検索結果全体リスト
     */
    public List getResultDetailList() {
        return (List) resultDetailListMap.get(new Integer(getWindowId())); 
    }

    /**
     *検索結果全体リストをを設定します。
     * @param resultList 検索結果全体リスト
     */
    public void setResultDetailList(List resultDetailList) {
    	this.resultDetailListMap.put(new Integer(getWindowId()), resultDetailList);
    }

    
    /**
     * 検索結果全体リストのサイズを設定します。
     * @param 検索結果全体リストのサイズ
     */   
	public int getResultDetailListSize(){ 
		if(getResultDetailList() == null){
			return 0;
		}else{
			return getResultDetailList().size();
		}
	}
	
	 /**
     * 全体リストページ内容を保持したリストを取得します。
     * @return　画面に表示するページ内容を保持したリスト
     */
    public List getPageList() {
        return (List) pageListMap.get(new Integer(getWindowId())); 
    }

    /**
     * 全体リストページ内容を保持したリストをを設定します。
     * @param pageList 画面に表示するページ内容を保持したリスト
     */
    public void setPageList(List pageList) {
        this.pageListMap.put(new Integer(getWindowId()), pageList);
    }
    
    /**
     * 検索結果全体リストのサイズを設定します。
     * @param 検索結果全体リストのサイズ
     */   
	public int getPageListSize(){ 
		if(getPageList() == null){
			return 0;
		}else{
			return getPageList().size();
		}
	}
	
    /**
     * 選択された店舗コードを設定します。
     * @return 選択された店舗コード
     */
    public String getCondTenpoCd() {
        return (String) condTenpoCdMap.get(new Integer(getWindowId())); 
    }

    /**
     * 選択された店舗コードをを設定します。
     * @param condTenpoCd 選択された店舗コード
     */
    public void setCondTenpoCd(String condTenpoCd) {
    	this.condTenpoCdMap.put(new Integer(getWindowId()), condTenpoCd);
    }
  
    /**
     * 商品グループを設定します。
     * @return ユーザータイプ
     */
    public Map getCondMapShohinGroup() {
    	return condMapShohinGroup;
    }

    /**
     * 商品グループをを設定します。
     * @param condMapShohinGroup 商品グループ
     */
    public void setCondMapShohinGroup(Map condMapShohinGroup) {
    	this.condMapShohinGroup = condMapShohinGroup;
    }
    
    /**
     * オーナーコードを取得します
     * @return オーナーコード
     */
    public String getCondOnerCd() {
        return  (String) condOnerCdMap.get(new Integer(getWindowId()));
    }

    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setCondOnerCd(String condOnerCd) {
        this.condOnerCdMap.put(new Integer(getWindowId()), condOnerCd);
    }
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCondCompanyCd() {
		//企業コード固定 (株式会社モスフードサービス)
    	condCompanyCd = "00";
		return condCompanyCd;
    }

    /**
     * 会社コードを設定します。
     * @param condCompanyCd 会社コード
     */
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }
    
    /**
     * アクション区分を取得します
     * @return アクション区分
     */
    public String getActionKbn() {
        return actionKbn;
    }

    /**
     * アクション区分を設定します。
     * @param actionKbn アクション区分
     */
    public void setActionKbn(String actionKbn) {
        this.actionKbn = actionKbn;
    }
    
    /**
     * ページ処理区分を取得します
     * @return ページ処理区分
     */
    public String getPageKbn() {
        return (String) pageKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * ページ処理区分を設定します。
     * @param pageKbn ページ処理区分
     */
    public void setPageKbn(String pageKbn) {
    	 this.pageKbnMap.put(new Integer(getWindowId()), pageKbn);
    }
    
    /**
     * ページボタンの表示可否情報を取得します
     * @return ページボタンの表示可否情報
     */
    public boolean [] getPageBtControl() {
        return (boolean[])pageBtControlMap.get(new Integer(getWindowId()));
    }

    /**
     * ページボタンの表示可否情報を設定します。
     * @param pageBtControl ページボタンの表示可否情報
     */
    public void setPageBtControl(boolean [] pageBtControl) {
        this.pageBtControlMap.put(new Integer(getWindowId()), pageBtControl);
    }
    
    /**
     * ページ処理日付区分を取得します
     * @return ページ処理区分
     */
    public String getPageDt() {
        return (String)pageDtMap.get(new Integer(getWindowId()));
    }

    /**
     * ページ処理日付を設定します。
     * @param pageKbn ページ処理区分
     */
    public void setPageDt(String pageDt) {
        this.pageDtMap.put(new Integer(getWindowId()), pageDt);
    }
    
    /**
     * ページに一番最初に表示される日付を取得します
     * @return ページに一番最初に表示される日付
     */
    public String getPageFirstDt() {
        return (String)pageFirstDtMap.get(new Integer(getWindowId()));
    }
    
    /**
     * ページに一番最初に表示される日付を設定します。
     * @param pageFirstDt ページに一番最初に表示される日付
     */
    public void setPageFirstDt(String pageFirstDt) {
    	this.pageFirstDtMap.put(new Integer(getWindowId()), pageFirstDt);
    }
    
    //-----
    //複数Window対応
    //-----
    /**
     * ウィンドウIDを取得します。
     * @return　ウィンドウID
     */
    public int getWindowId() {
        return windowId;
    }
    
    /**
     * ウィンドウIDを設定します。
     * @param windowId　ウィンドウID
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    /**
     * 最大ウィンドウIDを取得します。
     * @return 最大ウィンドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    
    /**
     * 最大ウィンドウIDを設定します。
     * @param maxWindowId　最大ウィンドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    
    // ウィンドウID生成
    /**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
    }    
}
