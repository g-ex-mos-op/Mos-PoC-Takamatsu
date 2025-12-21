/*
 * 作成日: 2006/11/21
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事業方針説明会申込DTO
 * 
 * @author xlee
 */
public class ProjectPlanOfferBakDto  {
	/**
     * ウィンドウID
     */
    private int windowId = 0;
	/**
	 * 会社名
	 */
	private Map bakDutyOnerNameMap = new HashMap();
	
	/**
	 * 連絡先名
	 */
	private Map bakDutySoufuNameMap = new HashMap();

	/**
	 * 電話番号名
	 */
	private Map bakDutyTelMap = new HashMap();

	/**
	 * 申込責任者
	 */
	private Map bakDutyNameMap = new HashMap();
	
	/**
	 * 役職
	 */
	private Map bakDutyJobTypeMap = new HashMap();
		
	/**
	 * 住所ー郵便番号
	 */
	private Map bakDutyZipMap = new HashMap();
	
	/**
	 * 住所１
	 */
	private Map bakDutyAddress1Map = new HashMap();
	
	/**
	 * 住所２
	 */
	private Map bakDutyAddress2Map = new HashMap();
	
	/**
	 * 住所２
	 */
	private Map bakDutyAddress3Map = new HashMap();
	
	/////////////////////////////////////////////
	//申込参加者
	/////////////////////////////////////////////
	
	/**
	 * 
	 */
	private Map bakMiseCdListMap = new HashMap();

	/**
	 * 
	 */
	private Map bakMiseNameListMap = new HashMap();
	
	/**
	 * 
	 */
	private Map bakSibuNameListMap = new HashMap();
	
	/**
	 * 参加者マップ
	 */
	private Map bakJoinPersonListMap = new HashMap();
	
	/////////////////////////////////////////////
	//委任状
	/////////////////////////////////////////////
	/**
	 * 委任状店舗コード
	 */
	private Map bakIninMiseCdMap = new HashMap();
	
	/**
	 * 委任状氏
	 */
	private Map bakIninFNameMap = new HashMap();
	
	/**
	 * 委任状名
	 */
	private Map bakIninLNameMap = new HashMap();
	
	/**
	 *　店舗数
	 */
	private Map bakIninMiseTotalMap = new HashMap();
	
	/**
     * 初期化する
     */
	public void clear(){
		setBakDutyOnerName(null);
		setBakDutySoufuName(null);
		setBakDutyTel(null);
		setBakDutyName(null);
		setBakDutyJobType(null);
		setBakDutyZip(null);
		setBakDutyAddress1(null);
		setBakDutyAddress2(null);
		setBakDutyAddress3(null);
		setBakJoinPersonList(null);
		setBakIninMiseCd(null);
		setBakIninFName(null);
		setBakIninLName(null);
		setBakIninMiseTotal(null);
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
    
    //-------------------------------------------------------------------
    //保持用
    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyOnerName(String bakDutyOnerName) {
    	this.bakDutyOnerNameMap.put(new Integer(getWindowId()), bakDutyOnerName);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyOnerName() {
    	return (String) bakDutyOnerNameMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutySoufuName(String bakDutySoufuName) {
    	this.bakDutySoufuNameMap.put(new Integer(getWindowId()), bakDutySoufuName);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutySofuName() {
    	return (String) bakDutySoufuNameMap.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyTel(String bakDutyTel) {
    	this.bakDutyTelMap.put(new Integer(getWindowId()), bakDutyTel);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyTel() {
    	return (String) bakDutyTelMap.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyName(String bakDutyName) {
    	this.bakDutyNameMap.put(new Integer(getWindowId()), bakDutyName);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyName() {
    	return (String) bakDutyNameMap.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyJobType(String bakDutyJobType) {
    	this.bakDutyJobTypeMap.put(new Integer(getWindowId()), bakDutyJobType);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyJobType() {
    	return (String) bakDutyJobTypeMap.get(new Integer(getWindowId()));
    }
    
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyZip(String bakDutyZip) {
    	this.bakDutyZipMap.put(new Integer(getWindowId()), bakDutyZip);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyZip() {
    	return (String) bakDutyZipMap.get(new Integer(getWindowId()));
    }
    
    
    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyAddress1(String bakDutyAddress1) {
    	this.bakDutyAddress1Map.put(new Integer(getWindowId()), bakDutyAddress1);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyAddress1() {
    	return (String) bakDutyAddress1Map.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyAddress2(String bakDutyAddress2) {
    	this.bakDutyAddress2Map.put(new Integer(getWindowId()), bakDutyAddress2);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyAddress2() {
    	return (String) bakDutyAddress2Map.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setBakDutyAddress3(String bakDutyAddress3) {
    	this.bakDutyAddress3Map.put(new Integer(getWindowId()), bakDutyAddress3);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getBakDutyAddress3() {
    	return (String) bakDutyAddress3Map.get(new Integer(getWindowId()));
    }
    
    ////////////////////////////////
    //申込参加者
    ////////////////////////////////
    
	
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String [] getBakMiseCdList() {
    	return  (String []) bakMiseCdListMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param BakMiseCd パラメータ店舗コード
     */
    public void setBakMiseCdList(String [] bakMiseCdList) {
    	this.bakMiseCdListMap.put(new Integer(getWindowId()), bakMiseCdList);
    }
    
    
    
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String [] getBakMiseNameList() {
    	return  (String []) bakMiseNameListMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param BakMiseCd パラメータ店舗コード
     */
    public void setBakMiseNameList(String [] bakMiseNameList) {
    	this.bakMiseNameListMap.put(new Integer(getWindowId()), bakMiseNameList);
    }
    
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String [] getBakSibuNameList() {
    	return  (String []) bakSibuNameListMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param BakMiseCd パラメータ店舗コード
     */
    public void setBakSibuNameList(String [] bakSibuNameList) {
    	this.bakSibuNameListMap.put(new Integer(getWindowId()), bakSibuNameList);
    }
    
    /**
     * 申込参加者１，２，３リストをを設定します。
     * @param JoinPersonList 申込参加者１，２，３リスト
     */
    public void setBakJoinPersonList(List bakJoinPersonList) {
    	this.bakJoinPersonListMap.put(new Integer(getWindowId()), bakJoinPersonList);
    }
    
    /**
     * 申込参加者１，２，３リストを取得します。
     * @return 申込参加者１，２，３リスト
     */
    public List getBakJoinPersonList() {
    	return (List) bakJoinPersonListMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 店舗リストのサイズを設定します。
     * @param  店舗リストのサイズ
     */   
	public int getBakJoinPersonListSize(){
		if(getBakJoinPersonList() == null){
			return 0;
		}else{
			return getBakJoinPersonList().size();
		}
	}
    
    //////////////////////////////////////////
    //　委任状
    //////////////////////////////////////////
    
    /**
     * 委任状店舗コードをを設定します。
     * @param bakIninMiseCd 委任状店舗コード
     */
    public void setBakIninMiseCd(String bakIninMiseCd) {
    	this.bakIninMiseCdMap.put(new Integer(getWindowId()), bakIninMiseCd);
    }
    
    /**
     * 委任状店舗コードを取得します。
     * @return 委任状店舗コード
     */
    public String getBakIninMiseCd() {
    	return (String) bakIninMiseCdMap.get(new Integer(getWindowId()));
    }
    
     /**
     * 委任状ー氏をを設定します。
     * @param bakIninFName 委任状ー氏
     */
    public void setBakIninFName(String bakIninFName) {
    	this.bakIninFNameMap.put(new Integer(getWindowId()), bakIninFName);
    }
    
    /**
     * 委任状ー氏を取得します。
     * @return 委任状ー氏
     */
    public String getBakIninFName() {
    	return (String) bakIninFNameMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 委任状ー名を設定します。
     * @param bakIninLName 委任状ー名
     */
    public void setBakIninLName(String bakIninLName) {
    	this.bakIninLNameMap.put(new Integer(getWindowId()), bakIninLName);
    }
    
    /**
     * 委任状ー名を取得します。
     * @return 委任状ー名
     */
    public String getBakIninLName() {
    	return (String) bakIninLNameMap.get(new Integer(getWindowId()));
    }
    	
    /**
     * 店舗数を設定します。
     * @param bakIninMiseTotal 店舗数
     */
    public void setBakIninMiseTotal(String bakIninMiseTotal) {
    	this.bakIninMiseTotalMap.put(new Integer(getWindowId()), bakIninMiseTotal);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getBakIninMiseTotal() {
    	return (String) bakIninMiseTotalMap.get(new Integer(getWindowId()));
    }
}
