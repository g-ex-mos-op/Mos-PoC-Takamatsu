/*
 * 作成日: 2006/11/21
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.dto;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事業方針説明会申込DTO
 * 
 * @author xlee
 */
public class ProjectPlanOfferDto  {
	
    public static final String TABNO_DUTY = "0";
    public static final String TABNO_JOIN1 = "1";
    
    public static final String MAP_KEY_UNIQKEY = "UNIQKEY";
    public static final String MAP_KEY_USERID = "USERID";
    public static final String MAP_KEY_DUTY = "DUTY";
    public static final String MAP_KEY_JOIN = "JOIN";
    public static final String MAP_KEY_PROC = "PROC";
    public static final String MAP_KEY_ININ = "ININ";

    public static final String PROC_KBN_INSERT = "I";
    public static final String PROC_KBN_UPDATE = "U";
    public static final String PROC_KBN_DELETE = "D";
    
    public static final String EDIT_KBN_INSERT = "I";
    public static final String EDIT_KBN_UPDATE = "U";
    public static final String EDIT_KBN_CONF_E = "E";
    public static final String EDIT_KBN_CONF_V = "V";
    
    public static final String ATESAKI_KBN = "00";
    
    public static final String REG_KBN_ININ = "ININ";
    public static final String REG_KBN_NOTININ = "NOTININ";
    
    public static final int FORM_SIZE = 4;
    
    public static final String SELMISE_PROC0 = "INIT";
    public static final String SELMISE_PROC1 = "CHGTAB";
    public static final String SELMISE_PROC2 = "CHGMISE";
    
    public static final String VALCHK_MODE_EDIT = "EDITMODE";
    public static final String VALCHK_MODE_ININ = "ININMODE";
    
    /**
     * システム日付
     */
    private String sysDate;
     
    /**
     *　会社コード
     */
    private String condCompanyCd;
    
    /**
     *　注意事項
     */
    private String noticeTxt;
    
    /**
     *　委任状文言
     */
    private String ininNoteTxt;
    
    /**
     *　画面用セッションKey
     */
    private String viewSessionKey;
    
    /**
     *　セッションKey
     */
    private HashMap sessionKeyMap = new HashMap();

	/**
     * ウィンドウID
     */
    private int windowId = 0;
    
    /**
     *　処理区分
     */
    private String[] processKbn;
    
    /**
     * 確認画面用のデータ保持マップ
     */
    private Map prmInfoMap = new HashMap();
    
    /**
     * 削除フラグマップ
     */
    private Map delFlgMap = new HashMap();
    
    /**
     * validater処理するモード
     */
    private Map valChkModeMap = new HashMap();
    
	/**
	 * オーナーコード保持マップ
	 */	
	private Map condOnerCdMap = new HashMap();
	
	/**
	 * 会社コード名保持マップ
	 */
	private Map condEntryCdMap = new HashMap();
	
	/**
	 * 対象期間開始日保持マップ
	 */
	private Map condEntryYearMap = new HashMap();
	
	/**
	 * 対象期間終了日保持マップ
	 */
	private Map condEntryKaiMap = new HashMap();
	
	/**
	 *　店舗リスト保持マップ
	 */
	private Map condListMiseMap = new HashMap();
	
	/**
	 * 店舗を切り替える時、パラメータ
	 */
	private Map prmMiseCdMap = new HashMap();
		
	/**
	 * 店舗を切り替える時、支部名
	 */
	private Map miseNameMap = new HashMap();
	
	/**
	 * 店舗を切り替える時、支部名
	 */
	private Map sibuNameMap = new HashMap();
	
	/**
	 * タブ別店舗コード
	 */
	private Map prmMiseCdListMap = new HashMap();
	
	/**
	 * 店舗を切り替える時、支部名
	 */
	private Map miseNameListMap = new HashMap();
	
	/**
	 * タブ別店舗毎の支部名
	 */
	private Map sibuNameListMap = new HashMap();
	
	/**
	 * 一号店コード
	 */
	private Map firstMiseCdMap = new HashMap();
	
	/**
	 * 一号店コード、支部名
	 */
	private Map firstSibuNameMap = new HashMap();
	
	/**
	 * タブ番号
	 * ０：申込責任者
	 * １：申込参加者１
	 * ２：申込参加者２
	 * ３：申込参加者３
	 */
	private Map prmTabNoMap = new HashMap();
	
	private Map prmPrevTabNoMap = new HashMap();
	
	/**
	 * 編集区分
	 */
	private Map prmEditKbnMap = new HashMap();
	
	/**
	 * 委任状区分
	 */
	private Map prmIninKbnMap = new HashMap();
	
	/**
	 * 委任状区分
	 */
	private Map prmConfirmKbnMap = new HashMap();
	
	/**
	 * 処理画面表示情報
	 */
	private Map initInfoListMap = new HashMap();
	
	/**
	 * エラー発生フラグマップ
	 */
	private Map errFlgMap = new HashMap();

	
	/////////////////////////////////////////////
	//申込責任者
	/////////////////////////////////////////////
	/**
	 * 会社名
	 */
	private Map prmDutyOnerNameMap = new HashMap();
	
	/**
	 * 連絡先名
	 */
	private Map prmDutySoufuNameMap = new HashMap();

	/**
	 * 電話番号名
	 */
	private Map prmDutyTelMap = new HashMap();

	/**
	 * 申込責任者
	 */
	private Map prmDutyNameMap = new HashMap();
	
	/**
	 * 役職
	 */
	private Map prmDutyJobTypeMap = new HashMap();
		
	/**
	 * 住所ー郵便番号
	 */
	private Map prmDutyZipMap = new HashMap();
	
	/**
	 * 住所１
	 */
	private Map prmDutyAddress1Map = new HashMap();
	
	/**
	 * 住所２
	 */
	private Map prmDutyAddress2Map = new HashMap();
	
	/**
	 * 住所２
	 */
	private Map prmDutyAddress3Map = new HashMap();
	
	/////////////////////////////////////////////
	//申込参加者
	/////////////////////////////////////////////
	/**
	 * 参加者マップ
	 */
	private Map prmJoinPersonListMap = new HashMap();
	
	/**
	 * 参加者マップ
	 */
	private Map prmJoinKanaCntMap = new HashMap();
	
	/////////////////////////////////////////////
	//委任状
	/////////////////////////////////////////////
	/**
	 * 委任状店舗コード
	 */
	private Map prmIninMiseCdMap = new HashMap();
	
	/**
	 * 委任状店舗名
	 */
	private Map prmIninMiseNmMap = new HashMap();
	
	/**
	 * 委任状氏
	 */
	private Map prmIninFNameMap = new HashMap();
	
	/**
	 * 委任状名
	 */
	private Map prmIninLNameMap = new HashMap();
	
	/**
	 * 店舗数
	 */
	private Map prmIninMiseTotalMap = new HashMap();
	
	/**
	 *　登録ユーザ
	 */
	private Map prmIninFirstUserMap	 = new HashMap();
	
	/**
	 *　登録PG
	 *
	 */
	private Map prmIninFirstPgmMap = new HashMap();
	
	/**
	 *　登録時間
	 */
	private Map prmIninFirstTmspMap = new HashMap();
	
	/**
	 *　変更ユーザ
	 */
	private Map prmIninLastUserMap = new HashMap();
	
	/**
	 *　変更PG
	 */
	private Map prmIninLastPgmMap = new HashMap();
	
	/**
	 *　変更時間
	 */
	private Map prmIninLastTmspMap = new HashMap();
		
    /**
     * 初期化する
     */
	public void clear(){
		setSysDate(null);
        setViewSessionKey(null);
        setSessioniKey(null);
        setCondListMise(null);
        setPrmMiseCd(null);
        setPrmMiseCdList(null);
        setMiseName(null);
        setMiseNameList(null);        
        setSibuName(null);
        setSibuNameList(null);
        setNoticeTxt(null);
        setIninNoteTxt(null);
        setProcessKbn(null);
        setPrmInfo(null);
        setDelFlg(null);
        setPrmTabNo(null);
        setPrmPrevTabNo(null);
        setPrmEditKbn(null);
        setPrmIninKbn(null);
        setInitInfoList(null);
        setPrmDutyOnerName(null);
        setPrmDutySoufuName(null);
        setPrmDutyTel(null);
        setPrmDutyName(null);
        setPrmDutyJobType(null);
        setPrmDutyZip(null);
        setPrmDutyAddress1(null);
        setPrmDutyAddress2(null);
        setPrmDutyAddress3(null);
        setPrmIninMiseCd(null);
        setPrmIninMiseNm(null);
        setPrmIninFName(null);
        setPrmIninLName(null);
        setPrmIninMiseTotal(null);
        setPrmIninFirstUser(null);
        setPrmIninFirstPgm(null);
        setPrmIninFirstTmsp(null);
        setPrmIninLastUser(null);
        setPrmIninLastPgm(null);
        setPrmIninLastTmsp(null);
        setErrFlg(null);
        setPrmJoinKanaCnt(null);
        setValChkMode(null);
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
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCondCompanyCd() {
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
     * viewSessionKey取得
     */
    public String getViewSessionKey() {
        return this.viewSessionKey;
    }
    
    /**
     * viewSessionKey取得
     * @param viewSessionKey
     */
    public void setViewSessionKey( String viewSessionKey ) {
        this.viewSessionKey = viewSessionKey;
    }

    /**
     * sessionKey取得
     */
    public String getSessionKey() {
    	return (String) sessionKeyMap.get(new Integer(getWindowId()));
    }
    
    /**
     * sessionKey取得
     * @param sessionKey
     */
    public void setSessioniKey( String sessionKey ) {
    	this.sessionKeyMap.put(new Integer(getWindowId()), sessionKey);
    }
    
    /**
     * 店舗リストを取得します。
     * @return 店舗リスト
     */
    public List getCondListMise() {
    	return  (List) condListMiseMap.get(new Integer(getWindowId()));
    }

    /**
     * 店舗リストをを設定します。
     * @param condListMise 店舗リスト
     */
    public void setCondListMise(List condListMise) {
    	this.condListMiseMap.put(new Integer(getWindowId()), condListMise);
    }
    
    /**
     * 店舗リストのサイズを設定します。
     * @param  店舗リストのサイズ
     */   
	public int getMiseListSize(){
		if(getCondListMise() == null){
			return 0;
		}else{
			return getCondListMise().size();
		}
	}

    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String getPrmMiseCd() {
    	return  (String) prmMiseCdMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setPrmMiseCd(String prmMiseCd) {
    	this.prmMiseCdMap.put(new Integer(getWindowId()), prmMiseCd);
    }
	
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String [] getPrmMiseCdList() {
    	return  (String []) prmMiseCdListMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setPrmMiseCdList(String [] prmMiseCdList) {
    	this.prmMiseCdListMap.put(new Integer(getWindowId()), prmMiseCdList);
    }
    
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String getMiseName() {
    	return  (String) miseNameMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setMiseName(String miseName) {
    	this.miseNameMap.put(new Integer(getWindowId()), miseName);
    }
    
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String [] getMiseNameList() {
    	return  (String []) miseNameListMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setMiseNameList(String [] miseNameList) {
    	this.miseNameListMap.put(new Integer(getWindowId()), miseNameList);
    }
    
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String getSibuName() {
    	return  (String) sibuNameMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setSibuName(String sibuName) {
    	this.sibuNameMap.put(new Integer(getWindowId()), sibuName);
    }
    
    /**
     * パラメータ店舗コードを取得します。
     * @return パラメータ店舗コード
     */
    public String [] getSibuNameList() {
    	return  (String []) sibuNameListMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setSibuNameList(String [] sibuNameList) {
    	this.sibuNameListMap.put(new Integer(getWindowId()), sibuNameList);
    }

    /**
     * 一号店コードを取得します。
     * @return パラメータ店舗コード
     */
    public String getFirstMiseCd() {
    	return  (String) firstMiseCdMap.get(new Integer(getWindowId()));
    }

    /**
     * パラメータ店舗コードをを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setFirstMiseCd(String firstMiseCd) {
    	this.firstMiseCdMap.put(new Integer(getWindowId()), firstMiseCd);
    }

	
    /**
     * 一号店の支部名を取得します。
     * @return パラメータ店舗コード
     */
    public String getFirstSibuName() {
    	return  (String) firstSibuNameMap.get(new Integer(getWindowId()));
    }

    /**
     * 一号店の支部名をを設定します。
     * @param prmMiseCd パラメータ店舗コード
     */
    public void setFirstSibuName(String firstSibuName) {
    	this.firstSibuNameMap.put(new Integer(getWindowId()), firstSibuName);
    }
    
    
    /**
     * 注意事項文言を取得
     */
    public String getNoticeTxt() {
        return this.noticeTxt;
    }
    
    /**
     *  注意事項文言を設定
     * @param noticeTxt
     */
    public void setNoticeTxt( String noticeTxt ) {
        this.noticeTxt = noticeTxt;
    }
    
    /**
     * 委任状文言を取得
     */
    public String getIninNoteTxt() {
        return this.ininNoteTxt;
    }
    
    /**
     * 委任状文言を設定
     * @param ininNoteTxt
     */
    public void setIninNoteTxt( String ininNoteTxt ) {
        this.ininNoteTxt = ininNoteTxt;
    }
    
    /**
     * 処理区分を取得
     */
    public String [] getProcessKbn() {
        return this.processKbn;
    }
    
    /**
     * 処理区分を設定
     * @param processKbn
     */
    public void setProcessKbn( String [] processKbn ) {
        this.processKbn = processKbn;
    }
    
    /**
     * 
     * @return　
     */
    public Map getPrmInfo() {
        return (Map) prmInfoMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 
     * @param 
     */
    public void setPrmInfo(Map prmInfo) {
    	this.prmInfoMap.put(new Integer(getWindowId()), prmInfo);
    }
    
    /**
     * 
     * @return　
     */
    public String getDelFlg() {
        return (String) delFlgMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 
     * @param 
     */
    public void setDelFlg(String delFlg) {
    	this.delFlgMap.put(new Integer(getWindowId()), delFlg);
    }
    
    /**
     * 
     * @return　
     */
    public String getValChkMode() {
        return (String) valChkModeMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 
     * @param 
     */
    public void setValChkMode(String valChkMode) {
    	this.valChkModeMap.put(new Integer(getWindowId()), valChkMode);
    }
    
    
    
    /**
     * 
     * @return　
     */
    public String getCondOnerCd() {
        return (String) condOnerCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 
     * @param 
     */
    public void setCondOnerCd(String condOnerCd) {
    	this.condOnerCdMap.put(new Integer(getWindowId()), condOnerCd);
    }

    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getCondEntryCd() {
    	return (String) condEntryCdMap.get(new Integer(getWindowId()));
    }

    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setCondEntryCd(String condEntryCd) {
    	this.condEntryCdMap.put(new Integer(getWindowId()), condEntryCd);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getCondEntryYear() {
    	return (String) condEntryYearMap.get(new Integer(getWindowId()));
    }

    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setCondEntryYear(String condEntryYear) {
    	this.condEntryYearMap.put(new Integer(getWindowId()), condEntryYear);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getCondEntryKai() {
    	return (String) condEntryKaiMap.get(new Integer(getWindowId()));
    }

    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setCondEntryKai(String condEntryKai) {
    	this.condEntryKaiMap.put(new Integer(getWindowId()), condEntryKai);
    }

    /**
     * タブ番号を設定します。
     * @param prmTabNo  タブ番号
     */
    public void setPrmTabNo(String prmTabNo) {
    	this.prmTabNoMap.put(new Integer(getWindowId()), prmTabNo);
    }
    
    /**
     *  タブ番号を取得します。
     * @return タブ番号
     */
    public String getPrmTabNo() {
    	return (String) prmTabNoMap.get(new Integer(getWindowId()));
    }
    
    /**
     * タブ番号を設定します。
     * @param prmTabNo  タブ番号
     */
    public void setPrmPrevTabNo(String prmPrevTabNo) {
    	this.prmPrevTabNoMap.put(new Integer(getWindowId()), prmPrevTabNo);
    }
    
    /**
     *  タブ番号を取得します。
     * @return タブ番号
     */
    public String getPrmPrevTabNo() {
    	return (String) prmPrevTabNoMap.get(new Integer(getWindowId()));
    }
    
    /**
     *　画面の編集区分、新規登録または更新
     * @param prmEditKbn 編集区分
     */
    public void setPrmEditKbn(String prmEditKbn) {
    	this.prmEditKbnMap.put(new Integer(getWindowId()), prmEditKbn);
    }
    
    /**
     *  画面の編集区分、新規登録または更新を取得します。
     * @return 編集区分
     */
    public String getPrmEditKbn() {
    	return (String) prmEditKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * タブ番号を設定します。
     * @param prmTabNo  タブ番号
     */
    public void setPrmIninKbn(String prmIninKbn) {
    	this.prmIninKbnMap.put(new Integer(getWindowId()), prmIninKbn);
    }
    
    /**
     *  タブ番号を取得します。
     * @return タブ番号
     */
    public String getPrmIninKbn() {
	    	return (String) prmIninKbnMap.get(new Integer(getWindowId()));
    }
    
    /**
     * タブ番号を設定します。
     * @param prmTabNo  タブ番号
     */
    public void setPrmConfirmKbn(String prmConfirmKbn) {
    	this.prmConfirmKbnMap.put(new Integer(getWindowId()), prmConfirmKbn);
    }
    
    /**
     *  タブ番号を取得します。
     * @return タブ番号
     */
    public String getPrmConfirmKbn() {
	    	return (String) prmConfirmKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * タブ番号を設定します。
     * @param prmTabNo  タブ番号
     */
    public void setInitInfoList(List initInfoList) {
    	this.initInfoListMap.put(new Integer(getWindowId()), initInfoList);
    }
    
    /**
     *  タブ番号を取得します。
     * @return タブ番号
     */
    public List getInitInfoList() {
    	return (List) initInfoListMap.get(new Integer(getWindowId()));
    }
    
    ////////////////////////////////////
    //申込責任者
    ///////////////////////////////////
    
    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyOnerName(String prmDutyOnerName) {
    	this.prmDutyOnerNameMap.put(new Integer(getWindowId()), prmDutyOnerName);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyOnerName() {
    	return (String) prmDutyOnerNameMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutySoufuName(String prmDutySoufuName) {
    	this.prmDutySoufuNameMap.put(new Integer(getWindowId()), prmDutySoufuName);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutySoufuName() {
    	return (String) prmDutySoufuNameMap.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyTel(String prmDutyTel) {
    	this.prmDutyTelMap.put(new Integer(getWindowId()), prmDutyTel);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyTel() {
    	return (String) prmDutyTelMap.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyName(String prmDutyName) {
    	this.prmDutyNameMap.put(new Integer(getWindowId()), prmDutyName);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyName() {
    	return (String) prmDutyNameMap.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyJobType(String prmDutyJobType) {
    	this.prmDutyJobTypeMap.put(new Integer(getWindowId()), prmDutyJobType);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyJobType() {
    	return (String) prmDutyJobTypeMap.get(new Integer(getWindowId()));
    }
    
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyZip(String prmDutyZip) {
    	this.prmDutyZipMap.put(new Integer(getWindowId()), prmDutyZip);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyZip() {
    	return (String) prmDutyZipMap.get(new Integer(getWindowId()));
    }
    
    
    /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyAddress1(String prmDutyAddress1) {
    	this.prmDutyAddress1Map.put(new Integer(getWindowId()), prmDutyAddress1);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyAddress1() {
    	return (String) prmDutyAddress1Map.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyAddress2(String prmDutyAddress2) {
    	this.prmDutyAddress2Map.put(new Integer(getWindowId()), prmDutyAddress2);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyAddress2() {
    	return (String) prmDutyAddress2Map.get(new Integer(getWindowId()));
    }
    
        /**
     * 商品グループリストをを設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyAddress3(String prmDutyAddress3) {
    	this.prmDutyAddress3Map.put(new Integer(getWindowId()), prmDutyAddress3);
    }
    
    /**
     * 商品グループリストを取得します。
     * @return 商品グループリスト
     */
    public String getPrmDutyAddress3() {
    	return (String) prmDutyAddress3Map.get(new Integer(getWindowId()));
    }

    ////////////////////////////////
    //申込参加者
    ////////////////////////////////
    /**
     * 申込参加者１，２，３リストをを設定します。
     * @param JoinPersonList 申込参加者１，２，３リスト
     */
    public void setPrmJoinPersonList(List prmJoinPersonList) {
    	this.prmJoinPersonListMap.put(new Integer(getWindowId()), prmJoinPersonList);
    }
    
    /**
     * 申込参加者１，２，３リストを取得します。
     * @return 申込参加者１，２，３リスト
     */
    public List getPrmJoinPersonList() {
    	return (List) prmJoinPersonListMap.get(new Integer(getWindowId()));
    }
    /**
     * 参加者カナ数を設定します。
     * @param prmJoinKanaCnt 参加者カナ数
     */
    public void setPrmJoinKanaCnt(String prmJoinKanaCnt) {
    	this.prmJoinKanaCntMap.put(new Integer(getWindowId()), prmJoinKanaCnt);
    }
    
    /**
     * 参加者カナ数を取得します。
     * @return 参加者カナ数
     */
    public String getPrmJoinKanaCnt() {
    	return (String) prmJoinKanaCntMap.get(new Integer(getWindowId()));
    }
    
    //////////////////////////////////////////
    //　委任状
    //////////////////////////////////////////
    
    /**
     * 委任状店舗コードをを設定します。
     * @param prmIninMiseCd 委任状店舗コード
     */
    public void setPrmIninMiseCd(String prmIninMiseCd) {
    	this.prmIninMiseCdMap.put(new Integer(getWindowId()), prmIninMiseCd);
    }
    
    /**
     * 委任状店舗コードを取得します。
     * @return 委任状店舗コード
     */
    public String getPrmIninMiseCd() {
    	return (String) prmIninMiseCdMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 委任状店舗コードをを設定します。
     * @param prmIninMiseCd 委任状店舗コード
     */
    public void setPrmIninMiseNm(String prmIninMiseNm) {
    	this.prmIninMiseNmMap.put(new Integer(getWindowId()), prmIninMiseNm);
    }
    
    /**
     * 委任状店舗コードを取得します。
     * @return 委任状店舗コード
     */
    public String getPrmIninMiseNm() {
    	return (String) prmIninMiseNmMap.get(new Integer(getWindowId()));
    }
    
     /**
     * 委任状ー氏をを設定します。
     * @param prmIninFName 委任状ー氏
     */
    public void setPrmIninFName(String prmIninFName) {
    	this.prmIninFNameMap.put(new Integer(getWindowId()), prmIninFName);
    }
    
    /**
     * 委任状ー氏を取得します。
     * @return 委任状ー氏
     */
    public String getPrmIninFName() {
    	return (String) prmIninFNameMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 委任状ー名を設定します。
     * @param prmIninLName 委任状ー名
     */
    public void setPrmIninLName(String prmIninLName) {
    	this.prmIninLNameMap.put(new Integer(getWindowId()), prmIninLName);
    }
    
    /**
     * 委任状ー名を取得します。
     * @return 委任状ー名
     */
    public String getPrmIninLName() {
    	return (String) prmIninLNameMap.get(new Integer(getWindowId()));
    }
    	
    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninMiseTotal(String prmIninMiseTotal) {
    	this.prmIninMiseTotalMap.put(new Integer(getWindowId()), prmIninMiseTotal);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getPrmIninMiseTotal() {
    	return (String) prmIninMiseTotalMap.get(new Integer(getWindowId()));
    }

    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninFirstUser(String prmIninFirstUser) {
    	this.prmIninFirstUserMap.put(new Integer(getWindowId()), prmIninFirstUser);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getPrmIninFirstUser() {
    	return (String) prmIninFirstUserMap.get(new Integer(getWindowId()));
    }

    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninFirstPgm(String prmIninFirstPgm) {
    	this.prmIninFirstPgmMap.put(new Integer(getWindowId()), prmIninFirstPgm);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getPrmIninFirstPgm() {
    	return (String) prmIninFirstPgmMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninFirstTmsp(Timestamp prmIninFirstTmsp) {
    	this.prmIninFirstTmspMap.put(new Integer(getWindowId()), prmIninFirstTmsp);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public Timestamp getPrmIninFirstTmsp() {
    	return (Timestamp) prmIninFirstTmspMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninLastUser(String prmIninLastUser) {
    	this.prmIninLastUserMap.put(new Integer(getWindowId()), prmIninLastUser);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getPrmIninLastUser() {
    	return (String) prmIninLastUserMap.get(new Integer(getWindowId()));
    }

    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninLastPgm(String prmIninLastPgm) {
    	this.prmIninLastPgmMap.put(new Integer(getWindowId()), prmIninLastPgm);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getPrmIninLastPgm() {
    	return (String) prmIninLastPgmMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setPrmIninLastTmsp(Timestamp prmIninLastTmsp) {
    	this.prmIninLastTmspMap.put(new Integer(getWindowId()), prmIninLastTmsp);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public Timestamp getPrmIninLastTmsp() {
    	return (Timestamp) prmIninLastTmspMap.get(new Integer(getWindowId()));
    }
    
    /**
     * 店舗数を設定します。
     * @param prmIninMiseTotal 店舗数
     */
    public void setErrFlg(String errFlg) {
    	this.errFlgMap.put(new Integer(getWindowId()), errFlg);
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public String getErrFlg() {
    	return (String) errFlgMap.get(new Integer(getWindowId()));
    }
    
}
