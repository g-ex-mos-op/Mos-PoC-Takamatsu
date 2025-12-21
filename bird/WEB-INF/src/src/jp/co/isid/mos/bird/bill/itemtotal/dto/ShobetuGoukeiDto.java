/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.itemtotal.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;

/**
 * 商品別合計DTO
 * 
 * @author xlee
 */
public class ShobetuGoukeiDto implements PdfOutputDto{
	
	/**
     * ウィンドウID
     */
    private int windowId = 0;
    
    /**
     * 最大ウィンドウID
     */	
    private int maxWindowId = 0;
	
	/**
     * ユーザータイプ
     */
    private Map userTypeCdMap = new HashMap();
    
    //検索条件--
    
    /**
     * オーナーコード
     */   
    private Map condOnerCdMap = new HashMap(); //String condOnerCd;
    
    /**
     * オーナーコード
     */   
    private Map condOnerNmMap = new HashMap();
    
    /**
     * 対象店舗リスト
     */
    private Map condTaishoTenpoListMap = new HashMap();   
    
    /**
     * 対象店舗コード
     */
    private Map condTaishoTenpoCdMap = new HashMap();
    
    /**
     * 対象店舗コード
     */
    private Map condTaishoTenpoNmMap = new HashMap();
    
    /**
     * 対象期間リスト
     */
    private Map condListTaishoKikanMap = new HashMap();
    
    /**
     * 対象期間
     */
    private Map condTaishoKikanCdMap = new HashMap();
    
    /**
     *　会社コード
     */
    private String companyCd;
    
    /**
     *　システム日付
     */    
    private String appDate;
    
    /**
     *　システム日付
     */    
    private String newWindowFlg;
    
    /**
     * 実商品コード
     */
    private String shoCdJitu;
    
    /**
     *　実商品名
     */    
    private String shoCdJituNm;
    
    //--検索条件
    
    /**
     *　分類リスト
     */
    private Map bunruiInfoListMap = new HashMap();
    
    /**
     *　分類リスト
     */
    private Map bunruiInfoSubListMap = new HashMap();
    
    /**
     *　商品別情報リスト
     */
    private Map shohinbetuInfoListMap = new HashMap();
    
    /**
     *　売上高情報
     */
    private Map uriageDakaDogetMap = new HashMap();
    //データ保持リスト --
    
    //　append
    // 2007.01.24　李　検索後、条件を変え、ＰＤＦダウンロードを実行しても
    //　検索した結果のオーナーコード、店舗コード、対象期間が表示されるように
    /**
     * 検索結果：店舗コード
     */
    private Map resultTaishoTenpoCdMap = new HashMap();

    /**
     * 検索結果：対象期間
     */
    private Map resultTaishoKikanCdMap = new HashMap();

    /**
     * 検索結果：オーナーコード
     */   
    private Map resultOnerCdMap = new HashMap();
    
    
    /**
     * 初期化する
     */
	public void clear(){
		setUserTypeCd(null);
		setCondOnerCd(null);
		setCondOnerNm(null);
		setCondTaishoTenpoList(null);
		setCondTaishoTenpoCd(null);
		setCondTaishoTenpoNm(null);
		setCondTaishoKikanList(null);
		setCondTaishoKikanCd(null);
		setBunruiInfoList(null);
		setBunruiInfoSubList(null);
		setShohinbetuInfoList(null);
		setUriageDakaInfo(null);
		setAppDate(null);
		setNewWindowFlg(null);
		//append
		setResultTaishoTenpoCd(null);
		setResultTaishoKikanCd(null);
		setResultOnerCd(null);
	}
	
    /**
     * 初期化する
     */
	public void clearExcute(){
		setCondTaishoTenpoList(null);
		setCondTaishoTenpoCd(null);
		setCondTaishoTenpoNm(null);
		setCondTaishoKikanList(null);
		setCondTaishoKikanCd(null);
		setBunruiInfoList(null);
		setBunruiInfoSubList(null);
		setShohinbetuInfoList(null);
		setUriageDakaInfo(null);
		setNewWindowFlg(null);
		//append
		setResultTaishoTenpoCd(null);
		setResultTaishoKikanCd(null);
		setResultOnerCd(null);
	}

	/**
     * 初期化する
     */
	public void clearSearchExcute(){
		setBunruiInfoList(null);
		setBunruiInfoSubList(null);
		setShohinbetuInfoList(null);
		setUriageDakaInfo(null);
		setNewWindowFlg(null);
	}
    
    /**
     * 検索条件：対象店舗リストを設定します。
     * @param condListTaishoTenpo 対象条件プルダウン用Listリスト
     */
    public void setCondTaishoTenpoList(List condTaishoTenpoList) {
        this.condTaishoTenpoListMap.put(new Integer(getWindowId()), condTaishoTenpoList);
        
    }
    
    /**
     * 検索条件：対象店舗リストを取得します。
     * @return　対象条件プルダウン用Listリスト
     */
    public List getCondTaishoTenpoList() { 
        return (List) condTaishoTenpoListMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 選択された対象店舗コードを設定します。
     * @param condTaishoKikanCd 対象期間
     */
    public void setCondTaishoTenpoCd(String condTaishoTenpoCd) {
    	this.condTaishoTenpoCdMap.put(new Integer(getWindowId()), condTaishoTenpoCd);
    }
    
    /**
     * 選択された対象店舗コードを取得します。
     * @return　選択された対象店舗コード
     */
    public String getCondTaishoTenpoCd() {
    	return (String) condTaishoTenpoCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 選択された対象店舗コードを設定します。
     * @param condTaishoKikanCd 対象期間
     */
    public void setCondTaishoTenpoNm(String condTaishoTenpoNm) {
        this.condTaishoTenpoNmMap.put(new Integer(getWindowId()), condTaishoTenpoNm);
    }
    
    /**
     * 選択された対象店舗コードを取得します。
     * @return　選択された対象店舗コード
     */
    public String getCondTaishoTenpoNm() {
    	return (String) condTaishoTenpoNmMap.get(new Integer(getWindowId()));
    }
	
    /**
     * 検索条件：対象期間リストを設定します。
     * @param condListTaishoKikan 対象期間リスト
     */
    public void setCondTaishoKikanList(List condListTaishoKikan) {
        this.condListTaishoKikanMap.put(new Integer(getWindowId()), condListTaishoKikan);
    }
    
    /**
     * 検索条件：対象店舗リストを取得します。
     * @return　対象条件プルダウン用Listリスト
     */
    public List getCondTaishoKikanList() {
    	return (List) condListTaishoKikanMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 選択された対象期間を設定します。
     * @param condTaishoKikanCd 対象期間
     */
    public void setCondTaishoKikanCd(String condTaishoKikanCd) {
        this.condTaishoKikanCdMap.put(new Integer(getWindowId()), condTaishoKikanCd);
    }
    
    /**
     * 選択された対象期間を取得します。
     * @return　選択された対象期間
     */
    public String getCondTaishoKikanCd() {
    	return (String) condTaishoKikanCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 商品別合計が保持されたリストののサイズを設定します。
     * @param お買上明細のサイズ
     */   
	public int getCondTaishoTenpoListSize(){
		if(getCondTaishoTenpoList() == null){
			return 0;
		}else{
			return getCondTaishoTenpoList().size();
		}
	}
	
    /**
     * 請求書分類情報が保持されたリストののサイズを設定します。
     * @param お買上明細のサイズ
     */  
	public int getBunruiInfoListSize(){
		if(getBunruiInfoList() == null){
			return 0;
		}else{
			return getBunruiInfoList().size();
		}
	}
    
    /**
     * 商品別合計が保持されたリストののサイズを設定します。
     * @param お買上明細のサイズ
     */   
	public int getShohinbetuInfoListSize(){
		if(getShohinbetuInfoList() == null){
			return 0;
		}else{
			return getShohinbetuInfoList().size();
		}
	}
	
//    /**
//     * 商品別合計が保持されたリストののサイズを設定します。
//     * @param お買上明細のサイズ
//     */   
//	public int getShobetuGoukeiDataListSize(){
//		if(shobetuGoukeiDataList == null){
//			return 0;
//		}else{
//			return shobetuGoukeiDataList.size();
//		}
//	}

	/**
     * ユーザータイプを設定します。
     * @return ユーザータイプ
     */
    public String getUserTypeCd() {
        //return userTypeCd;
    	return (String) userTypeCdMap.get(new Integer(getWindowId()));
    }

    /**
     * ユーザータイプをを設定します。
     * @param userTypeCd ユーザータイプ
     */
    public void setUserTypeCd(String userTypeCd) {
        //this.userTypeCd = userTypeCd;
        this.userTypeCdMap.put(new Integer(getWindowId()), userTypeCd);
    }
    
    /**
     * 検索条件：オーナーコードを取得します。
     * @return オーナーコード
     */
	public String getCondOnerCd() {
        //return condOnerCd;
		return (String) condOnerCdMap.get(new Integer(getWindowId()));
	}
	
	/**
     *  検索条件：オーナーコードを設定します。
     * @param 　condOnerCd オーナーコード
     */
	public void setCondOnerCd(String condOnerCd) {
        //this.condOnerCd = condOnerCd;
        this.condOnerCdMap.put(new Integer(getWindowId()), condOnerCd);
	}
	
    /**
     * 検索条件：オーナーコードを取得します。
     * @return オーナーコード
     */
	public String getCondOnerNm() {
       //return condOnerNm;
		return (String) condOnerNmMap.get(new Integer(getWindowId()));
	}
	
	/**
     *  検索条件：オーナーコードを設定します。
     * @param 　condOnerCd オーナーコード
     */
	public void setCondOnerNm(String condOnerNm) {
        //this.condOnerNm = condOnerNm;
        this.condOnerNmMap.put(new Integer(getWindowId()), condOnerNm);
	}
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
		//企業コード固定 ?(株式会社モスフードサービス)
    	companyCd = "00";
		return companyCd;
    }

    /**
     * 会社コードを設定します。
     * @param condCompanyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

//    /**
//     * 商品別合計のデータリストを取得します。
//     * @return お買上明細リスト
//     */
//    public List getShobetuGoukeiDataList() {
//        return shobetuGoukeiDataList;
//    }
//
//    /**
//     * 商品別合計のデータリストを設定します
//     * @param shobetuGoukeiDataList　商品別合計リスト
//     */
//    public void setShobetuGoukeiDataList(List shobetuGoukeiDataList) {
//        this.shobetuGoukeiDataList = shobetuGoukeiDataList;
//    }

    /**
     * 分類情報を取得します。
     * @return 分類リスト
     */
    public List getBunruiInfoList() {
        //return bunruiInfoList;
    	return (List) bunruiInfoListMap.get(new Integer(getWindowId()));
        
    }

    /**
     * 分類を設定します。
     * @param bunruiInfoList　分類リスト
     */
    public void setBunruiInfoList(List bunruiInfoList) {
        //this.bunruiInfoList = bunruiInfoList;
        this.bunruiInfoListMap.put(new Integer(getWindowId()), bunruiInfoList);
    }
    
    /**
     * 請求書分類情報を取得します。
     * @return 請求書分類リスト
     */
    public List getBunruiInfoSubList() {
        //return bunruiInfoSubList;
    	return (List) bunruiInfoSubListMap.get(new Integer(getWindowId()));
    }

    /**
     * 請求書分類を設定します。
     * @param bunruiInfoList　請求書分類リスト
     */
    public void setBunruiInfoSubList(List bunruiInfoSubList) {
        //this.bunruiInfoSubList = bunruiInfoSubList;
    	this.bunruiInfoSubListMap.put(new Integer(getWindowId()), bunruiInfoSubList);
    }
    
    /**
     * 商品別情報を取得します。
     * @return 商品別情報リスト
     */
	public List getShohinbetuInfoList() {
        //return shohinbetuInfoList;
		return (List) shohinbetuInfoListMap.get(new Integer(getWindowId()));
	}
	
	/**
     * 商品別情報を取得します。
     * @param shohinbetuInfoList　商品別情報リス
     */
	public void setShohinbetuInfoList(List shohinbetuInfoList) {
        //this.shohinbetuInfoList = shohinbetuInfoList;
        this.shohinbetuInfoListMap.put(new Integer(getWindowId()), shohinbetuInfoList);
	}
	
    /**
     * 売上高情報を取得します。
     * @return 売上高情報
     */
	public BigDecimal getUriageDakaInfo() {
        //return uriageDakaDoget;
		return (BigDecimal) uriageDakaDogetMap.get(new Integer(getWindowId()));
	}
	
	/**
     *  検索条件：売上高情報を設定します。
     * @param 　uriagedakaInoList 売上高情報
     */
	public void setUriageDakaInfo(BigDecimal uriageDakaDoget) {
        //this.uriageDakaDoget = uriageDakaDoget;
        this.uriageDakaDogetMap.put(new Integer(getWindowId()), uriageDakaDoget);
	}
	
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
    
    // ウィンドウID生成
    /**
     * ウィンドウIDを生成します。
     */
    public String getAppDate() {
        return appDate;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public void setAppDate(String appDate) {
    	this.appDate = appDate;
    }
    
    // ウィンドウID生成
    /**
     * ウィンドウIDを生成します。
     */
    public String getNewWindowFlg() {
        return newWindowFlg;
    }
    
    /**
     * ウィンドウIDを更新します。
     */
    public void setNewWindowFlg(String newWindowFlg) {
    	this.newWindowFlg = newWindowFlg;
    }

    // ウィンドウID生成
    /**
     * 実商品コードを取得します。
     */
    public String getShoCdJitu() {
        return shoCdJitu;
    }
    
    /**
     * 実商品コードを設定します。
     * @param shoCdJitu 実商品コード
     */
    public void setShoCdJitu(String shoCdJitu) {
    	this.shoCdJitu = shoCdJitu;
    }

    /**
     * 実商品名を取得します。
     */
    public String getShoCdJituNm() {
        return shoCdJituNm;
    }
    
    /**
     * 実商品名を設定します。
     * @param shoCdJituNm 実商品名
     */
    public void setShoCdJituNm(String shoCdJituNm) {
    	this.shoCdJituNm = shoCdJituNm;
    }

	public String getFileName() {
		return null;
	}
	
	//append 2007.01.24　李
    /**
     * 検索結果：対象店舗コードを設定します。
     * @param condListTaishoTenpo 対象条件プルダウン用Listリスト
     */
    public void setResultTaishoTenpoCd(String resultTaishoTenpoCd) {
        this.resultTaishoTenpoCdMap.put(new Integer(getWindowId()), resultTaishoTenpoCd);
    }
    
    /**
     * 検索結果：対象店舗コードを取得します。
     * @return　検索結果：対象店舗コード
     */
    public String getResultTaishoTenpoCd() { 
        return (String) resultTaishoTenpoCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 検索結果：対象オーナーコードを設定します。
     * @param resultOnerCd 検索結果：対象オーナーコード
     */
    public void setResultOnerCd(String resultOnerCd) {
        this.resultOnerCdMap.put(new Integer(getWindowId()), resultOnerCd);
        
    }
    
    /**
     * 検索結果：対象オーナーコードを取得します。
     * @return　検索結果：対象オーナーコード
     */
    public String getResultOnerCd() { 
        return (String) resultOnerCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 検索結果：対象オーナーコードを設定します。
     * @param resultOnerCd 検索結果：対象オーナーコード
     */
    public void setResultTaishoKikanCd(String resultTaishoKikanCd) {
        this.resultTaishoKikanCdMap.put(new Integer(getWindowId()), resultTaishoKikanCd);
    }
    
    /**
     * 検索結果：対象オーナーコードを取得します。
     * @return　検索結果：対象オーナーコード
     */
    public String getResultTaishoKikanCd() { 
        return (String) resultTaishoKikanCdMap.get(new Integer(getWindowId()));
    }
}