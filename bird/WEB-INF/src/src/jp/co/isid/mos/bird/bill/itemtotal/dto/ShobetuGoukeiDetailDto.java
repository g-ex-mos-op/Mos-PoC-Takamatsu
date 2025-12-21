/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.itemtotal.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品別合計DTO
 * 
 * @author xlee
 */
public class ShobetuGoukeiDetailDto{
	
	/**
     * 新しいウィンドウを開く区分
     */
	private String newWindowFlg;

    /**
     * 明細リスト
     */
    private Map meisaiInfoListMap = new HashMap();    
    
    /**
     * 履歴リスト
     */
    private Map rirekiInfoListMap = new HashMap();  
    
    /**
     * 実商品コード
     */
    private String shoCdJitu;
    
    /**
     * 実商品名
     */
    private String shoCdJituNm;
    
    /**
     * 店名
     */
    private Map tenpoNmMap = new HashMap();
    
	/**
     * ウィンドウID
     */
    private int windowId = 0;
    
    /**
     * 初期化する
     */
	public void clear(){
		setNewWindowFlg(null);
		setShoCdJitu(null);
		setShoCdJituNm(null);
		setTenpoNm(null);
		setMeisaiInfoList(null);
		setRirekiInfoList(null);
	}	
    
    /**
     * 明細情報リストを設定します。
     * @param meisaiInfoList 明細情報
     */
    public void setMeisaiInfoList(List meisaiInfoList) {
       // this.meisaiInfoList = meisaiInfoList;
    	this.meisaiInfoListMap.put(new Integer(getWindowId()), meisaiInfoList);
    }
    
    /**
     * 明細情報リストリストを取得します。
     * @return　明細情報リストリスト
     */
    public List getMeisaiInfoList() {        
        //return meisaiInfoList;
        return (List) meisaiInfoListMap.get(new Integer(getWindowId()));
    }

    /**
     * 履歴情報リストを設定します。
     * @param rirekiInfoList 履歴情報リスト
     */
    public void setRirekiInfoList(List rirekiInfoList) {
        //this.rirekiInfoList = rirekiInfoList;
        this.rirekiInfoListMap.put(new Integer(getWindowId()), rirekiInfoList);
    }
    
    /**
     * 履歴情報リストを取得します。
     * @return　履歴情報リスト
     */
    public List getRirekiInfoList() {        
        //return rirekiInfoList;
        return (List) rirekiInfoListMap.get(new Integer(getWindowId()));
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
     * 新しいウィンドウを開く区分を設定します。
     * @param newWindowFlg 新しいウィンドウを開く区分
     */
    public void setNewWindowFlg(String newWindowFlg) {
        this.newWindowFlg = newWindowFlg;
    }
    
    /**
     * 新しいウィンドウを開く区分を取得します。
     * @return　新しいウィンドウを開く区分
     */
    public String getNewWindowFlg() {
    	return newWindowFlg;
    }
    
    /**
     * 実商品コードを取得します。
     * @return　実商品コード
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
     * 実商品名を取得します
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

    /**
     * 店名を取得します
     */
    public String getTenpoNm() {
        //return tenpoNm;
        return (String) tenpoNmMap.get(new Integer(getWindowId()));
    }
   
    /**
     * 実商品名を設定します
     * @param tenpoNm　店名
     */
    public void setTenpoNm(String tenpoNm) {
    	//this.tenpoNm = tenpoNm;
    	this.tenpoNmMap.put(new Integer(getWindowId()), tenpoNm);
    }
}