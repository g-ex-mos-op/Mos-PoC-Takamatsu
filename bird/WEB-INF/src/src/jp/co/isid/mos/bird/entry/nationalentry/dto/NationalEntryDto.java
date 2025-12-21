/*
 * 作成日: 2006/11/21
 */
package jp.co.isid.mos.bird.entry.nationalentry.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntrySelectInfo;

/**
 * 事業方針説明会申込DTO
 *
 * @author xlee
 */
public class NationalEntryDto  {

	/**
	 *　参加者初期値
	 */
    public static final String JOIN_FIRST = "1";

	/**
	 * 処理区分
	 */
    public static final String PROC_KBN_INSERT = "I";
    public static final String PROC_KBN_UPDATE = "U";

    /**
     * 編集区分
     */
    public static final String EDIT_KBN_INSERT = "I";
    public static final String EDIT_KBN_UPDATE = "U";

    /**
     * 画面区分
     */
    public static final String VIEW_KBN_E = "E";
    public static final String VIEW_KBN_V = "V";

    /**
     * マップキー
     * プロセス区分
     * オーナー別エントリー状況
     * エントリーセレクション情報
     * エントリー責任者情報
     * エントリー参加者情報
     */
    public static final String MAPKEY_PROC = "E_PROC";
    public static final String MAPKEY_STATE = "E_STATE";
    public static final String MAPKEY_SELECTION = "E_SELECT";
    public static final String MAPKEY_DUTYINFO = "E_DUTY";
    public static final String MAPKEY_JOININFO = "E_JOIN";

    /**
     * 申込区分：１
     * オプショナル：２
     */
    public static final String SELECTION1 = "1";
    public static final String SELECTION2 = "2";

    /**
     * 入力欄追加可能：0
     * 入力欄追加不可：1
     */
    public static final String INPUT_APPEND_OK = "0";
    public static final String INPUT_APPEND_NG = "1";

    /**
     * フォーム表示：0
     * フォーム非表示：1
     */
    public static final String VIEW_FORM_OK = "0";
    public static final String VIEW_FORM_NG = "1";

    /**
     * 申込データが存在しない区分
     */
    public static final String NO_DATA_ENTRY = "1";

    /**
     * 初期化頁区分
     */
    public static final String PGKBN_END = "E";
    public static final String PGKBN_BACK = "B";

    /**
     * 入力欄追加処理
     * 登録・終了処理
     */
    public static final String BTN_INPUTAPPEND = "APPEND";
    public static final String BTN_DBINSERT = "INSERT";

    /**
     * 初期化区分
     */
    public static final String INITFLG_TRUE = "0";
    public static final String INITFLG_FALSE = "1";

    /**
     * 入力欄追加ボタン
     */
    public static final String BTN_TRUE = "0";
    public static final String BTN_FALSE = "1";

    /** 再検索処理区分 */
    public static final String KBN_RESEARCH_EXEC = "1";

    /** 参加・不参加
     * 参加：１
     * 不参加：０
     */
    public static final String ENTRY_STATE_OK = "1";
    public static final String ENTRY_STATE_NG = "0";


    /**
     * システム日付
     */
    private String sysDate;

    /**
     * ユーザＩＤ
     */
    private String userId;

    /**
     * ユーザタイプ
     */
    private String userType;

    /**
     *　会社コード
     */
    private String condCompanyCd;

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
     *　入力欄追加可能可否フラグ
     */
    private HashMap prmInputAppendFlgMap = new HashMap();

    /**
     *　参加者編集フォーム非表示フラグ
     */
    private HashMap prmFormNonViewFlgMap = new HashMap();

    /**
     *　永年勤続申込ボタン使用フラグ
     */
    private HashMap prmLongServButFlgMap = new HashMap();

	/**
	 * 編集区分：新規登録または更新
	 */
	private Map editKbnMap = new HashMap();

	/**
	 * 画面区分
	 */
	private Map viewKbnMap = new HashMap();

	/**
	 * ボタン名
	 */
	private Map btnKbnMap = new HashMap();

	/**
	 * 戻る
	 */
	private Map backKbnMap = new HashMap();

	/**
	 *　申込終了後、データがない場合
	 */
	private Map noDataFlgMap = new HashMap();

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
	 *　職位リスト保持マップ
	 */
	private Map condListSyokuiMap = new HashMap();

	/**
	 * エラー発生フラグマップ
	 */
	private Map errFlgMap = new HashMap();

	/**
	 * 初期化フラグ
	 */
	private Map initFlgMap = new HashMap();

	/**
	 * セレクション情報：申込区分
	 */
	private Map selectionEntryKbn = new HashMap();

	/**
	 * セレクション情報：オプショナル
	 */
	private Map selectionOption = new HashMap();

	/////////////////////////////////////////////
	//申込責任者
	/////////////////////////////////////////////
	/**
	 * 会社名
	 */
	private Map prmDutyOnerNameMap = new HashMap();

	/**
	 * オーナー別エントリー状況
	 */
	private Map prmDutyEntryStateMap = new HashMap();

	/**
	 *　排他処理日付
	 */
	private Map prmDutyLastTmspMap = new HashMap();

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

	/////////////////////////////////////////////
	//申込参加者
	/////////////////////////////////////////////
	/**
	 * 参加者マップ
	 */
	private Map prmJoinListMap = new HashMap();

	/**
	 * 参加者マップ
	 */
	private Map prmSeqNoMap = new HashMap();

    /**
     * 入館者件数
     */
    private Map prmNowSeqNoMap = new HashMap();

    /**
     * 初期化する
     */
	public void clear(String pgKbn){
		setSysDate(null);
		setUserId(null);
		setUserType(null);
		if(!pgKbn.equals(PGKBN_BACK)) {
			setViewSessionKey(null);
			setSessioniKey(null);
			setCondCompanyCd(null);
	        setCondOnerCd(null);
	        setCondEntryCd(null);
	        setCondEntryYear(null);
	        setCondEntryKai(null);
		}
        setCondListMise(null);
        setBtnKbn(null);
        setEditKbn(null);
        setViewKbn(null);
        setPrmDutyOnerName(null);
        setPrmDutyEntryState(null);
        setPrmDutyLastTmsp(null);
        setPrmDutySoufuName(null);
        setPrmDutyTel(null);
        setPrmDutyName(null);
        setPrmDutyJobType(null);
        setPrmJoinList(null);
        setPrmSeqNo(null);
        setErrFlg(null);
        setInitFlg(null);
        setSelectionOptionInfo(null);
        setSelectionEntryKbn(null);
        setPrmInputAppendFlg(null);
        setPrmFormNonViewFlg(null);
        setPrmLongServButFlg(null);
        setNoDataFlg(null);
        setNowSeqNo(null);
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
     * ユーザＩＤを取得します。
     * @return ユーザＩＤ
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザＩＤをを設定します。
     * @param userId ユーザＩＤ
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }

    /**
     * ユーザタイプを取得します。
     * @return ユーザタイプ
     */
    public String getUserType() {
        return userType;
    }

    /**
     * ユーザタイプをを設定します。
     * @param userType ユーザタイプ
     */
    public void setUserType(String userType) {
    	this.userType = userType;
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
     * 職位情報を取得します。
     * @return 職位情報リスト
     */
    public List getCondListSyokui() {
    	return  (List) condListSyokuiMap.get(new Integer(getWindowId()));
    }

    /**
     * 職位情報リストをを設定します。
     * @param condListMise 職位情報リスト
     */
    public void setCondListSyokui(List condListSyokui) {
    	this.condListSyokuiMap.put(new Integer(getWindowId()), condListSyokui);
    }

    /**
     * 職位名を取得します。
     * @param 職位コード
     */
	public String getSyokuiName(String jobTypeCd){
	  	//空白Ｏｐｔｉｏｎの生成
		List pullDownList = new ArrayList();
		String jobType = null;
		if (jobTypeCd == null || "".equals(jobTypeCd)) {
			return jobType;
		}
		pullDownList = getCondListSyokui();
		if (pullDownList == null) {
			return jobTypeCd;
		}
		for (int i=0;i<pullDownList.size();i++) {
			SelectItem selectItem = (SelectItem)pullDownList.get(i);
			if (jobTypeCd.equals(selectItem.getValue())){
				jobType = selectItem.getLabel();
				break;
			}
		}
		return jobType;
	}

    /**
     * 職位コードを取得します。
     * @param 職位名
     */
	public String getSyokuiCd(String jobTypeName){
	  	//空白Ｏｐｔｉｏｎの生成
		List pullDownList = new ArrayList();
		String jobTypeCd = null;
		if (jobTypeName == null || "".equals(jobTypeName)) {
			return jobTypeCd;
		}
		pullDownList = getCondListSyokui();
		if (pullDownList == null) {
			return jobTypeCd;
		}
		for (int i=0;i<pullDownList.size();i++) {
			SelectItem selectItem = (SelectItem)pullDownList.get(i);
			if (jobTypeName.equals(selectItem.getLabel())){
				jobTypeCd = selectItem.getValue().toString();
				break;
			}
		}
		return jobTypeCd;
	}

	/**
     * オーナーコードを取得します。
     * @return　オーナーコード
     */
    public String getCondOnerCd() {
        return (String) condOnerCdMap.get(new Integer(getWindowId()));
    }

    /**
     * オーナーコードを設定します。
     * @param condOnerCd　オーナーコード
     */
    public void setCondOnerCd(String condOnerCd) {
    	this.condOnerCdMap.put(new Integer(getWindowId()), condOnerCd);
    }

    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getCondEntryCd() {
    	return (String) condEntryCdMap.get(new Integer(getWindowId()));
    }

    /**
     * エントリーコードを設定します。
     * @param condEntryCd エントリーコード
     */
    public void setCondEntryCd(String condEntryCd) {
    	this.condEntryCdMap.put(new Integer(getWindowId()), condEntryCd);
    }

    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getCondEntryYear() {
    	return (String) condEntryYearMap.get(new Integer(getWindowId()));
    }

    /**
     * エントリー年を設定します。
     * @param condEntryYear エントリー年
     */
    public void setCondEntryYear(String condEntryYear) {
    	this.condEntryYearMap.put(new Integer(getWindowId()), condEntryYear);
    }

    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getCondEntryKai() {
    	return (String) condEntryKaiMap.get(new Integer(getWindowId()));
    }

    /**
     * エントリー回を設定します。
     * @param condEntryKai　エントリー回
     */
    public void setCondEntryKai(String condEntryKai) {
    	this.condEntryKaiMap.put(new Integer(getWindowId()), condEntryKai);
    }

    /**
     *　入力欄追加可能可否フラグを設定します。
     * @param prmInputAppendFlg 入力欄追加可能可否フラグ
     */
    public void setPrmInputAppendFlg(String prmInputAppendFlg) {
    	this.prmInputAppendFlgMap.put(new Integer(getWindowId()), prmInputAppendFlg);
    }

    /**
     *  入力欄追加可能可否フラグを取得します。
     * @return 入力欄追加可能可否フラグ
     */
    public String getPrmInputAppendFlg() {
    	return (String) prmInputAppendFlgMap.get(new Integer(getWindowId()));
    }

    /**
     *　参加者編集フォーム非表示フラグを設定します。
     * @param prmFormNonViewFlg 参加者編集フォーム非表示フラグ
     */
    public void setPrmFormNonViewFlg(String prmFormNonViewFlg) {
    	this.prmFormNonViewFlgMap.put(new Integer(getWindowId()), prmFormNonViewFlg);
    }

    /**
     *  参加者編集フォーム非表示フラグを取得します。
     * @return 参加者編集フォーム非表示フラグ
     */
    public String getPrmFormNonViewFlg() {
    	return (String) prmFormNonViewFlgMap.get(new Integer(getWindowId()));
    }

    /**
     *　参加者編集フォーム非表示フラグを設定します。
     * @param prmFormNonViewFlg 参加者編集フォーム非表示フラグ
     */
    public void setPrmLongServButFlg(String prmLongServButFlg) {
    	this.prmLongServButFlgMap.put(new Integer(getWindowId()), prmLongServButFlg);
    }

    /**
     *  参加者編集フォーム非表示フラグを取得します。
     * @return 参加者編集フォーム非表示フラグ
     */
    public String getPrmLongServButFlg() {
    	return (String) prmLongServButFlgMap.get(new Integer(getWindowId()));
    }

    /**
     *　編集区分、新規登録または更新
     * @param prmEditKbn 編集区分
     */
    public void setEditKbn(String editKbn) {
    	this.editKbnMap.put(new Integer(getWindowId()), editKbn);
    }

    /**
     *  編集区分、新規登録または更新を取得します。
     * @return 編集区分
     */
    public String getEditKbn() {
    	return (String) editKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * 画面区分、編集または確認
     * @param prmViewKbn 画面区分
     */
    public void setViewKbn(String viewKbn) {
    	this.viewKbnMap.put(new Integer(getWindowId()), viewKbn);
    }

    /**
     *  画面区分、編集または確認
     * @return 画面区分
     */
    public String getViewKbn() {
		return (String) viewKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * ボタン名
     * @param btnKbn ボタン名
     */
    public void setBtnKbn(String btnKbn) {
    	this.btnKbnMap.put(new Integer(getWindowId()), btnKbn);
    }

    /**
     * ボタン名
     * @return　ボタン名
     */
    public String getBtnKbn() {
		return (String) btnKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * 戻る区分
     * @param btnKbn 戻る区分
     */
    public void setBackKbn(String backKbn) {
    	this.backKbnMap.put(new Integer(getWindowId()), backKbn);
    }

    /**
     * 戻る区分
     * @return　戻る区分
     */
    public String getBackKbn() {
		return (String) backKbnMap.get(new Integer(getWindowId()));
    }

    /**
     * 申込期間終了後、申込内容がない場合
     * @param noData
     */
    public void setNoDataFlg(String noDataFlg) {
    	this.noDataFlgMap.put(new Integer(getWindowId()), noDataFlg);
    }

    /**
     *　申込期間終了後、申込内容がない場合
     * @return
     */
    public String getNoDataFlg() {
		return (String) noDataFlgMap.get(new Integer(getWindowId()));
    }

    ////////////////////////////////////
    //申込責任者
    ///////////////////////////////////

    /**
     * 責任者：オーナー名称を設定します。
     * @param prmDutyOnerName 責任者オーナー名称
     */
    public void setPrmDutyOnerName(String prmDutyOnerName) {
    	this.prmDutyOnerNameMap.put(new Integer(getWindowId()), prmDutyOnerName);
    }

    /**
     *責任者：オーナー名称を取得します。
     * @return 責任者オーナー名称
     */
    public String getPrmDutyOnerName() {
    	return (String) prmDutyOnerNameMap.get(new Integer(getWindowId()));
    }

    /**
     * オーナー別エントリー状況を設定します。
     * @param prmDutyEntryState オーナー別エントリー状況
     */
    public void setPrmDutyEntryState(String prmDutyEntryState) {
    	this.prmDutyEntryStateMap.put(new Integer(getWindowId()), prmDutyEntryState);
    }

    /**
     * オーナー別エントリー状況を取得します。
     * @return オーナー別エントリー状況
     */
    public String getPrmDutyEntryState() {
    	return (String) prmDutyEntryStateMap.get(new Integer(getWindowId()));
    }

    /**
     * 排他処理日付を設定します。
     * @param prmDutyEntryState　排他処理日付
     */
    public void setPrmDutyLastTmsp(Timestamp prmDutyLastTmsp) {
    	this.prmDutyLastTmspMap.put(new Integer(getWindowId()), prmDutyLastTmsp);
    }

    /**
     * 排他処理日付を取得します。
     * @return 排他処理日付
     */
    public Timestamp getPrmDutyLastTmsp() {
    	return (Timestamp) prmDutyLastTmspMap.get(new Integer(getWindowId()));
    }

    /**
     * 責任者：責任者名称を設定します。
     * @param prmDutySoufuName　責任者：責任者名称
     */
    public void setPrmDutySoufuName(String prmDutySoufuName) {
    	this.prmDutySoufuNameMap.put(new Integer(getWindowId()), prmDutySoufuName);
    }

    /**
     * 責任者：責任者名称を取得します。
     * @return 責任者：責任者名称
     */
    public String getPrmDutySoufuName() {
    	return (String) prmDutySoufuNameMap.get(new Integer(getWindowId()));
    }

    /**
     * 責任者：電話番号を設定します。
     * @param prmDutyTel 責任者：電話番号
     */
    public void setPrmDutyTel(String prmDutyTel) {
    	this.prmDutyTelMap.put(new Integer(getWindowId()), prmDutyTel);
    }

    /**
     * 責任者：電話番号を取得します。
     * @return 責任者：電話番号
     */
    public String getPrmDutyTel() {
    	return (String) prmDutyTelMap.get(new Integer(getWindowId()));
    }

    /**
     * 責任者：担当者名称を設定します。
     * @param condListShohinGroup 商品グループリスト
     */
    public void setPrmDutyName(String prmDutyName) {
    	this.prmDutyNameMap.put(new Integer(getWindowId()), prmDutyName);
    }

    /**
     * 責任者：担当者名称を取得します。
     * @return 責任者：担当者名称
     */
    public String getPrmDutyName() {
    	return (String) prmDutyNameMap.get(new Integer(getWindowId()));
    }

    /**
     * 責任者：役職を設定します。
     * @param prmDutyJobType 責任者：役職
     */
    public void setPrmDutyJobType(String prmDutyJobType) {
    	this.prmDutyJobTypeMap.put(new Integer(getWindowId()), prmDutyJobType);
    }

    /**
     * 責任者：役職を取得します。
     * @return責任者：役職
     */
    public String getPrmDutyJobType() {
    	return (String) prmDutyJobTypeMap.get(new Integer(getWindowId()));
    }

    ////////////////////////////////
    //申込参加者
    ////////////////////////////////
    /**
     * 申込参加者リストを設定します。
     * @param JoinPersonList 申込参加者リスト
     */
    public void setPrmJoinList(List prmJoinList) {
    	this.prmJoinListMap.put(new Integer(getWindowId()), prmJoinList);
    }

    /**
     * 申込参加者リストを取得します。
     * @return 申込参加者リスト
     */
    public List getPrmJoinList() {
    	return (List) prmJoinListMap.get(new Integer(getWindowId()));
    }

    /**
     * ソート番号を設定します。
     * @param prmSeqNo ソート番号
     */
    public void setPrmSeqNo(String prmSeqNo) {
    	this.prmSeqNoMap.put(new Integer(getWindowId()), prmSeqNo);
    }

    /**
     * ソート番号を取得します。
     * @return ソート番号
     */
    public String getPrmSeqNo() {
    	return (String) prmSeqNoMap.get(new Integer(getWindowId()));
    }

    /**
     * エラーフラグを設定します。
     * @param errFlg エラーフラグ
     */
    public void setErrFlg(String errFlg) {
    	this.errFlgMap.put(new Integer(getWindowId()), errFlg);
    }

    /**
     * エラーフラグを取得します。
     * @return エラーフラグ
     */
    public String getErrFlg() {
    	return (String) errFlgMap.get(new Integer(getWindowId()));
    }

    /**
     * エラーフラグを設定します。
     * @param errFlg エラーフラグ
     */
    public void setInitFlg(String initFlg) {
    	this.initFlgMap.put(new Integer(getWindowId()), initFlg);
    }

    /**
     * エラーフラグを取得します。
     * @return エラーフラグ
     */
    public String getInitFlg() {
    	return (String) initFlgMap.get(new Integer(getWindowId()));
    }

    /**
     * セレクション情報：申込区分を設定します。
     * @param selectionEntryKbn セレクション情報：申込区分
     */
    public void setSelectionEntryKbn(List selectionEntryKbn) {
    	this.selectionEntryKbn.put(new Integer(getWindowId()), selectionEntryKbn);
    }

    /**
     * セレクション情報：申込区分マップを取得します。
     * @return セレクション情報：申込区分
     */
    public List getSelectionEntryKbn() {
    	return (List) selectionEntryKbn.get(new Integer(getWindowId()));
    }

    /**
     * セレクション情報：オプショナルを設定します。
     * @param selectionOption セレクション情報：オプショナル
     */
    public void setSelectionOptionInfo(List selectionOptionInfo) {
    	this.selectionOption.put(new Integer(getWindowId()), selectionOptionInfo);
    }

    /**
     * セレクション情報：オプショナルを取得します。
     * @return  セレクション情報：オプショナル
     */
    public List getSelectionOptionInfo() {
    	return (List) selectionOption.get(new Integer(getWindowId()));
    }

    /**
     * 現在の申請者件数を設定します。
     * @param prmSeqNo ソート番号
     */
    public void setNowSeqNo(String nowSeqNoMap) {
        this.prmNowSeqNoMap.put(new Integer(getWindowId()), nowSeqNoMap);
    }

    /**
     * 現在の申請者件数を取得します。
     * @return ソート番号
     */
    public String getNowSeqNo() {
        return (String) prmNowSeqNoMap.get(new Integer(getWindowId()));
    }
    /**
     * オプショナル（宿泊関連）有無判断処理
     * @return
     */
    public boolean isEmptyOptionalStay() {
    	if(getSelectionOptionInfo() != null
    			&& !getSelectionOptionInfo().isEmpty()
    			&& getSelectionOptionInfo().size() >= 30)
    	{
    		for(int i=0; i<30; i++) {
    			UINatiEntrySelectInfo optional = (UINatiEntrySelectInfo)getSelectionOptionInfo().get(i);
    			if(!optional.isEmptySelection()) {
    	    		return false;
    			}
    		}
    	}
    	return true;
    }
}
