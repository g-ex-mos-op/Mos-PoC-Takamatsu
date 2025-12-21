/*
 * 作成日: 2006/10/20
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;

/**
 * 時間帯別予約状況確認表DTO
 * 
 * @author xlee
 */
public class MosChickenReserveChkBytimeDto implements PdfOutputDto, CsvOutputDto{

	/**
     * ユーザータイプ
     */
    private String userTypeCd;
    
    /**
     * システム日付
     */
    private String sysDate;

    /**
     * オーナーコード
     */   
    private String condOnerCd;
    
    /**
     * ユーザID
     */   
    private String userId;

    /**
     * 時間単位リスト
     */    
    private List condListTimeUnit;
    
    /**
     *　会社コード
     */
    private String condCompanyCd;
    
	/**
	* アクション区分
	*/
    private String actionKbn;
    
    /**
	* 期間中、該当タイトルが存在しない場合,存在しない場合true
	*/
    private boolean errFlg = false;
    
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
	 *　時間単位保持マップ
	 */
	private String condTimeUnitCd;
    
	/**
	 * タイトルコード保持マップ
	 */
	String condTitleCd;
	
	/**
	 * 対象期間開始日保持マップ
	 */
	String condTaishoDt;
	
	
	/**
	 * 店舗コード保持マップ
	 */
	String condTenpoCd;
	
	/**
	 * 店舗コード保持マップ
	 */
	String condTenpoNm;
	
	/**
	 *　店舗リスト保持マップ
	 */
	private Map condListTenpoMap = new HashMap();

	/**
	 *　店舗リスト保持マップ
	 */
	private Map condListTaishoDtMap = new HashMap();
	
	/**
	 *　タイトルリスト保持マップ
	 */
	private Map condListTitleMap = new HashMap();
	
	
    /**
     * 初期化する
     */
	public void clear(){
		setUserTypeCd(null);
		setSysDate(null);
		setUserId(null);
		setCondCompanyCd(null);
		setActionKbn(null);
		setErrFlg(false);
		setCondOnerCd(null);
		setCondTitleCd(null);
		setCondTaishoDt(null);
		setCondTenpoCd(null);
		setCondTenpoNm(null);
	}
	
	public void allClear() {
		clear();
		setCondListTitle(null);
		setCondListTaishoDt(null);
		setCondListTenpo(null);
	}
	
	/**
	 * タイトル切り替えた場合、検索条件をクリアします。
	 */
	public void serchCondClear(){
		//検索条件選択初期化する
		setCondTenpoCd(null);
		setCondTaishoDt(null);
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
     * システム日付を取得します。
     * @return システム日付
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * システム日付をを設定します。
     * @param sysDate システム日付
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
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
    	return condTitleCd;
    }
    
    /**
     *  選択されたタイトルのコードを設定します。
     * @param condTaishoKikanCd 対象期間
     */
    public void setCondTitleCd(String condTitleCd) {
    	this.condTitleCd = condTitleCd;
    }

    /**
     * 対象期間リストを取得します。
     * @return ユーザータイプ
     */
    public List getCondListTaishoDt() {
    	return (List) condListTaishoDtMap.get(new Integer(getWindowId()));
    }

    /**
     * 対象期間リストをを設定します。
     * @param condListTaishoKikan 対象期間リスト
     */
    public void setCondListTaishoDt(List condListTaishoDt) {
    	this.condListTaishoDtMap.put(new Integer(getWindowId()), condListTaishoDt);
    }
    
    /**
     * 対象期間リストのサイズを設定します。
     * @param  対象期間リストのサイズ
     */   
	public int getTaishoDtListSize(){
		if(getCondListTaishoDt() == null){
			return 0;
		}else{
			return getCondListTaishoDt().size();
		}
	}
    
    /**
     * 選択された対象期間終了日を取得します。
     * @return　選択された対象期間終了日
     */
    public String getCondTaishoDt() {
    	return condTaishoDt;
    }
    
    /**
     * 選択された対象期間終了日を設定します。
     * @param condTaishoKikanCd 対象期間終了日
     */
    public void setCondTaishoDt(String condTaishoDt) {
    	this.condTaishoDt = condTaishoDt;
    }
    
    /**
     * 店舗リストを取得します。
     * @return 店舗リスト
     */
    public List getCondListTenpo() {
    	return  (List) condListTenpoMap.get(new Integer(getWindowId()));
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
     * 時間単位リストを取得します。
     * @return 時間単位リスト
     */
    public List getCondListTimeUnit() {
        return condListTimeUnit;
    }

    /**
     * 時間単位リストをを設定します。
     * @param condListTimeUnit 時間単位リスト
     */
    public void setCondListTimeUnit(List condListTimeUnit) {
        this.condListTimeUnit = condListTimeUnit;
    }

    /**
     * 選択された時間単位を取得します。
     * @return　選択された時間単位
     */
    public String getCondTimeUnitCd() {
    	return condTimeUnitCd;
    }
    
    /**
     * 選択された時間単位を設定します。
     * @param condTimeUnitCd 時間単位
     */
    public void setCondTimeUnitCd(String condTimeUnitCd) {
    	this.condTimeUnitCd = condTimeUnitCd;
    }
	
    /**
     * 選択された店舗コードを設定します。
     * @return 選択された店舗コード
     */
    public String getCondTenpoCd() {
    	return condTenpoCd;
    }

    /**
     * 選択された店舗コードをを設定します。
     * @param condTenpoCd 選択された店舗コード
     */
    public void setCondTenpoCd(String condTenpoCd) {
    	this.condTenpoCd = condTenpoCd;
    }
    
    /**
     * 選択された店舗コードを設定します。
     * @return 選択された店舗コード
     */
    public String getCondTenpoNm() {
    	return condTenpoNm;
    }

    /**
     * 選択された店舗コードをを設定します。
     * @param condTenpoCd 選択された店舗コード
     */
    public void setCondTenpoNm(String condTenpoNm) {
    	this.condTenpoNm = condTenpoNm;
    }
  
    /**
     * オーナーコードを取得します
     * @return オーナーコード
     */
    public String getCondOnerCd() {
        return condOnerCd;
    }

    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setCondOnerCd(String condOnerCd) {
        this.condOnerCd = condOnerCd;
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
     * タイトル存在可否を取得します
     * @return タイトル存在可否
     */
    public boolean getErrFlg() {
        return errFlg;
    }

    /**
     * タイトル存在可否を設定します。
     * @param actionKbn タイトル存在可否
     */
    public void setErrFlg(boolean errFlg) {
        this.errFlg = errFlg;
    }
    
    /**
     * PDFファイル名
     * @return PDFファイル名
     */
	public String getFileName() {
		return null;
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
