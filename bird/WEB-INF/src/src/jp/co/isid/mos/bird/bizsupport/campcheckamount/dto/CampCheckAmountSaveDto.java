package jp.co.isid.mos.bird.bizsupport.campcheckamount.dto;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * キャンペーン設定数量確認DTO
 * 
 * @author xlee
 */
public class CampCheckAmountSaveDto implements CsvOutputDto{
    
    /**
     * 対象キャンペーン
     */
    private Map tmpTaishoCmpNo = new HashMap();
    
    /**
     * 対象条件
     */
    private Map tmpTaishoCond = new HashMap();    

    /**
     * 表示対象：店コード
     */   
    private Map tmpTaishoMiseCd = new HashMap();
    
    /**
     * 表示対象：オーナーコード
     */   
    private Map tmpTaishoOnerCd = new HashMap();
    
    /**
     * 表示対象：支部コード
     */   
    private Map tmpTaishoSibuCd = new HashMap();
    
    /**
     * 表示対象：ブロックコード
     */   
    private Map tmpTaishoBlockCd = new HashMap();
    
    /*
     *　表示用 
     */
    /**
     * 対象キャンペーン
     */
    private Map viewTaishoCmpNo = new HashMap();
    
    /**
     * 対象条件
     */
    private Map viewTaishoCond = new HashMap();

    /**
     * 表示対象：店コード
     */   
    private Map viewTaishoMiseCd = new HashMap();
    
    /**
     * 表示対象：店コード
     */   
    private Map viewTaishoOnerCd = new HashMap();    
    
    /**
     * 表示対象：支部コード
     */   
    private Map viewTaishoSibuCd = new HashMap();
    
    /**
     * 表示対象：ブロックコード
     */   
    private Map viewTaishoBlockCd = new HashMap();    
    
    /**
     * 実行フラグ
     */    
    private Map tmpExecFlg = new HashMap();
    
    /**
     * タブ区分
     */    
    private Map tmpTabKbn = new HashMap();
    
    /**
     * タブ表示フラグ
     */    
    private Map tmpTabViewFlg = new HashMap();
    
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
     * 初期化する
     */
	public void clear(){
		setTmpTaishoCmpNo(null);
		setTmpTaishoCond(null);
		setTmpTaishoMiseCd(null);
		setTmpTaishoOnerCd(null);
		setTmpTaishoSibuCd(null);
		setTmpTaishoBlockCd(null);
		/* 表示用 */
		setViewTaishoCmpNo(null);
		setViewTaishoCond(null);
		setViewTaishoMiseCd(null);
		setViewTaishoOnerCd(null);
		setViewTaishoSibuCd(null);
		setViewTaishoBlockCd(null);
		
		setTmpExecFlg(null);
		setTmpTabKbn(null);
		setTmpTabViewFlg(null);
	}
	
    /**
     * 対象キャンペーンを取得します。
     * @return 対象キャンペーン
     */
    public String getTmpTaishoCmpNo() {
		return (String) tmpTaishoCmpNo.get(new Integer(getWindowId()));
    }

    /**
     * 対象キャンペーンを設定します。
     * @param taishoCmp 対象キャンペーン
     */
    public void setTmpTaishoCmpNo(String tmpTaishoCmpNo) {
    	this.tmpTaishoCmpNo.put(new Integer(getWindowId()), tmpTaishoCmpNo);
    }
    
    /**
     * 対象条件を取得します。
     * @return 対象条件
     */
    public String getTmpTaishoCond() {
		return (String) tmpTaishoCond.get(new Integer(getWindowId()));
    }

    /**
     * 対象条件を設定します。
     * @param taishoCond 対象条件
     */
    public void setTmpTaishoCond(String tmpTaishoCond) {
        this.tmpTaishoCond.put(new Integer(getWindowId()), tmpTaishoCond);
    }
    
    /**
     * 表示対象：店コードを取得します。
     * @return 表示対象：店コード
     */
    public String getTmpTaishoMiseCd() {
		return (String) tmpTaishoMiseCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：店コードを設定します。
     * @param taishoMiseCd 表示対象：店コード
     */
    public void setTmpTaishoMiseCd(String tmpTaishoMiseCd) {
    	this.tmpTaishoMiseCd.put(new Integer(getWindowId()), tmpTaishoMiseCd);
    }
    
    /**
     * 表示対象：店コードを取得します。
     * @return 表示対象：店コード
     */
    public String getTmpTaishoOnerCd() {
		return (String) tmpTaishoOnerCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：店コードを設定します。
     * @param taishoMiseCd 表示対象：店コード
     */
    public void setTmpTaishoOnerCd(String tmpTaishoOnerCd) {
    	this.tmpTaishoOnerCd.put(new Integer(getWindowId()), tmpTaishoOnerCd);
    }
    
    /**
     * 表示対象：支部コードを取得します。
     * @return 表示対象：支部コード
     */
    public String getTmpTaishoSibuCd() {
		return (String) tmpTaishoSibuCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：支部コードを設定します。
     * @param taishoMiseCd 表示対象：支部コード
     */
    public void setTmpTaishoSibuCd(String tmpTaishoSibuCd) {
    	this.tmpTaishoSibuCd.put(new Integer(getWindowId()), tmpTaishoSibuCd);
    }
    
    /**
     * 表示対象：ブロックコードを取得します。
     * @return 表示対象：ブロックコード
     */
    public String getTmpTaishoBlockCd() {
		return (String) tmpTaishoBlockCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：ブロックコードを設定します。
     * @param taishoBlockCd 表示対象：ブロックコード
     */
    public void setTmpTaishoBlockCd(String tmpTaishoBlockCd) {
        this.tmpTaishoBlockCd.put(new Integer(getWindowId()), tmpTaishoBlockCd);
    }
    
    /* 表示用 */
    /**
     * 対象キャンペーンを取得します。
     * @return 対象キャンペーン
     */
    public String getViewTaishoCmpNo() {
		return (String) viewTaishoCmpNo.get(new Integer(getWindowId()));
    }

    /**
     * 対象キャンペーンを設定します。
     * @param taishoCmp 対象キャンペーン
     */
    public void setViewTaishoCmpNo(String viewTaishoCmpNo) {
    	this.viewTaishoCmpNo.put(new Integer(getWindowId()), viewTaishoCmpNo);
    }
    
    /**
     * 対象条件を取得します。
     * @return 対象条件
     */
    public String getViewTaishoCond() {
		return (String) viewTaishoCond.get(new Integer(getWindowId()));
    }

    /**
     * 対象条件を設定します。
     * @param taishoCond 対象条件
     */
    public void setViewTaishoCond(String viewTaishoCond) {
        this.viewTaishoCond.put(new Integer(getWindowId()), viewTaishoCond);
    }
    
    /**
     * 表示対象：店コードを取得します。
     * @return 表示対象：店コード
     */
    public String getViewTaishoMiseCd() {
		return (String) viewTaishoMiseCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：店コードを設定します。
     * @param taishoMiseCd 表示対象：店コード
     */
    public void setViewTaishoMiseCd(String viewTaishoMiseCd) {
    	this.viewTaishoMiseCd.put(new Integer(getWindowId()), viewTaishoMiseCd);
    }
    
    /**
     * 表示対象：店コードを取得します。
     * @return 表示対象：店コード
     */
    public String getViewTaishoOnerCd() {
		return (String) viewTaishoOnerCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：店コードを設定します。
     * @param taishoMiseCd 表示対象：店コード
     */
    public void setViewTaishoOnerCd(String viewTaishoOnerCd) {
    	this.viewTaishoOnerCd.put(new Integer(getWindowId()), viewTaishoOnerCd);
    }
    
    /**
     * 表示対象：支部コードを取得します。
     * @return 表示対象：支部コード
     */
    public String getViewTaishoSibuCd() {
		return (String) viewTaishoSibuCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：支部コードを設定します。
     * @param taishoMiseCd 表示対象：支部コード
     */
    public void setViewTaishoSibuCd(String viewTaishoSibuCd) {
    	this.viewTaishoSibuCd.put(new Integer(getWindowId()), viewTaishoSibuCd);
    }
    
    /**
     * 表示対象：ブロックコードを取得します。
     * @return 表示対象：ブロックコード
     */
    public String getViewTaishoBlockCd() {
		return (String) viewTaishoBlockCd.get(new Integer(getWindowId()));
    }

    /**
     * 表示対象：ブロックコードを設定します。
     * @param taishoBlockCd 表示対象：ブロックコード
     */
    public void setViewTaishoBlockCd(String viewTaishoBlockCd) {
        this.viewTaishoBlockCd.put(new Integer(getWindowId()), viewTaishoBlockCd);
    }
    
    /**
     * 実行フラグを取得します。
     * @return 実行フラグ
     */
    public String getTmpExecFlg() {
		return (String) tmpExecFlg.get(new Integer(getWindowId()));
    }

    /**
     * 実行フラグを設定します。
     * @param execFlg  実行フラグ
     */
    public void setTmpExecFlg(String tmpExecFlg) {
        this.tmpExecFlg.put(new Integer(getWindowId()), tmpExecFlg);
    }
    
    /**
     * タブ区分を取得します。
     * @return タブ区分
     */
    public String getTmpTabKbn() {
    	return (String) tmpTabKbn.get(new Integer(getWindowId()));
    }

    /**
     * タブ区分を設定します。
     * @param tabKbn  タブ区分
     */
    public void setTmpTabKbn(String tmpTabKbn) {
    	 this.tmpTabKbn.put(new Integer(getWindowId()), tmpTabKbn);
    }
    
    /**
     * タブ表示フラグを取得します。
     * @return タブ表示フラグ
     */
    public String getTmpTabViewFlg() {
    	return (String) tmpTabViewFlg.get(new Integer(getWindowId()));
    }

    /**
     * タブ表示フラグを設定します。
     * @param tmpTabViewFlg  タブ表示フラグ
     */
    public void setTmpTabViewFlg(String tmpTabViewFlg) {
    	 this.tmpTabViewFlg.put(new Integer(getWindowId()), tmpTabViewFlg);
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
