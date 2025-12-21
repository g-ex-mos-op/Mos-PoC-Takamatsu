package jp.co.isid.mos.bird.entry.mlentry.dto;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.entity.MstOner;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * マスターライセンス受験申込 DTO<br>
 * @author Aspac
 */
public class MlEntryDto {
    
    /* メッセージ */
    //ブラウザチェック
    private static final String 
        MSG_BROWSER_CHECK = "お使いのブラウザでは、一部の機能が正常に動作しない場合がございます。";
    //全スタッフ削除時に確認画面に表示するメッセージ
    private static final String MSG_ALLSTAFF_DELETE = "上記オーナーによるマスターライセンス申込登録情報を削除します。";
    //オーナーユーザー 編集画面遷移時のメッセージ
    private static final String MSG_ONERUSER_ALERT = "登録いただくデータは、マスターライセンス資料作成以外に使用致しません。";
    //スタッフ情報編集ボタン押下時にスタッフが未選択のメッセージ
    private static final String MSG_NOSELECT_STAFF_ALERT = "編集するスタッフを選択して下さい。";
    
    
    private static final String EXAM_NEW_MSG = "新規エントリー";
    private static final String EXAM_MSG     = "前回受験番号：";
    
    public String getExamNewMsg(){
        return EXAM_NEW_MSG;
    }
    public String getExamMsg(){
        return EXAM_MSG;
    }
    public String getNoSelectStaffMsg() {
        return MSG_NOSELECT_STAFF_ALERT;
    }
    public void setExamNewMsg(String str){
    }
    public void setExamMsg(String str){
    }
    public void setNoSelectStaffMsg(String str){
    }
    
    /* 制御用 */
    //画面遷移妥当性チェック用SessionKey
    private String sessionKey;

    /* 条件画面 */
    // 条件：店コード
    private String condMiseCd;
    // 条件：会社コード
    private String condCompanyCd;
    // 条件：店コードのオーナーコード
    private String condOnerCd;
    // 条件：店コードのオーナー名称
    private String condOnerNameKj;
    
    
    
    //エントリーマスタ管理
    private List listEntryMst;
    //エントリー日付管理
    private List listEntryDate;
    //会社一覧
    private List listCompany;
    // エントリーオーナー宛先情報
    private List listEntryOner;
   
    
    //エントリーマスタ管理Emptyチェック
    public boolean isListEntryMstEmpty(){
        for (Iterator ite = this.getListEntryMst().iterator(); ite.hasNext();) {
            UIEntryMst entity = (UIEntryMst) ite.next();
            if ("1".equals(entity.getEditFlg()) || "1".equals(entity.getDisplayFlg())) {
                return false;
            }
        }
        return true;
    }
     
    
    /**
     * マスターライセンスエントリー状況
     * ※スタッフIDをもとにエントリー状況、エントリー履歴情報を保持する。
     */ 
    private List listEntryState;
    
    
    
    /**
     * 更新対象レコードリスト
     * 更新対象のレコードのみエントリー状況リスト(listEntryState)から選抜し保持する。
     */
    private List listEntryStateRegist;
    
    /** 
     * エントリー更新データリスト
     * ※エントリー状況(BT23MLEJ)に更新するエントリーデータを保持する。
     */
    private List listEntry;
    
    /** 
     * 結果状況履歴更新データリスト
     * ※結果状況履歴(BT32MLKR)に更新するエントリーデータを保持する。
     */
    private List listResult;
    
    /** 
     * スタッフ更新データリスト
     * ※加盟店スタッフマスタ(BM12STAF)に更新するエントリーデータを保持する。
     */
    private List listStaff;

    /**
     * エントリー対象(可能)となるスタッフのリスト
     */
    private List ListEntryEnableStaff;
    
    
    // 保有店舗(スタッフ用)
    private List listMstMise;
    // オーナー保有店舗
    private List listMstMiseOner;
    // 送付先一覧
    private List listSofusakiPulldown;
    // エントリーオーナー宛先情報（結果報告先）
    private UIEntryOner uiEntryOnerKekkaHokoku;
    // エントリーオーナー宛先情報（担当者）
    private UIEntryOner uiEntryOnerTanto;
    // オーナー別エントリー状況
    private UIEntryOnerStatus uiEntryOnerStatus;
    // オーナー情報
    private MstOner mstOner;
    // 受験希望地一覧
    private List listPlace;
    
    //---条件画面レコード選択ラジオボタン
    private int selectIndex;
    //---新規スタッフ追加ボタンのインデックス
    private int addStaffIndex = 0;
//add start MLレグレーションテスト後修正 inazawa
    //---スタッフ編集ボタン押下判断
    private boolean editStaffFlg;
    //---スタッフ追加ボタン押下判断
    private boolean addStaffFlg;
// add end    
    
    // 編集対象のスタッフID
    private String editStaffId;
    
    /* 編集画面初期処理判定フラグ */
    private boolean initFlg;
    
    
    /* KEY情報：エントリーコード */
    private String entryCd;
    /* KEY情報：エントリー年 */
    private String entryYear;
    /* KEY情報：エントリー回*/
    private String entryKai;

    
    /* 実更新データ数 */
    private int insupEntryStateNum = 0;
    
    /* エントリー可能なスタッフ数 */
    private int staffCnt = 0;
    
    /* スタッフ選択実行 */
    private String selectedStaff = "";
    
    /* オーナーユーザー 情報セキュリティアラートフラグ */
    private String dispSecurityInfo;
    
    
    /* ユーザー情報 */
    //ユーザータイプ
    private String userTypeCd;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;

    /**
	 * 編集画面初期処理判定フラグの設定
	 * @return initFlg を戻します。
	 */
	public boolean isInitFlg() {
		return initFlg;
	}
	/**
	 * 編集画面初期処理判定フラグの設定
	 * @param initFlg initFlg を設定。
	 */
	public void setInitFlg(boolean initFlg) {
		this.initFlg = initFlg;
	}

	/**
	 * エントリーコードの設定
	 * @return entryCd を戻します。
	 */
	public String getEntryCd() {
		return entryCd;
	}
	/**
	 * エントリーコードの設定
	 * @param entryCd entryCd を設定。
	 */
	public void setEntryCd(String entryCd) {
		this.entryCd = entryCd;
	}

	/**
	 * 日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * 日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

    public String getSysDate() {
        return getBirdDateInfo().getSysDate();
    }
    public List getListEntryMst() {
        return listEntryMst;
    }
    public void setListEntryMst(List listEntryMst) {
        this.listEntryMst = listEntryMst;
    }
    public int getSelectIndex() {
        return selectIndex;
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    public String getSessionKey() {
        return sessionKey;
    }
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * エントリー一覧で選択されたエントリーのUIEntryMstを取得
     * @return
     */
    public UIEntryMst getSelectEntryMst() {
        if (getListEntryMst() == null) {
            return null;
        }
        return (UIEntryMst) getListEntryMst().get(getSelectIndex());
    }
    
    
    public String getEntryKai() {
        return entryKai;
    }
    public void setEntryKai(String entryKasi) {
        this.entryKai = entryKasi;
    }
    public String getEntryYear() {
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    public String getCondCompanyCd() {
        return condCompanyCd;
    }

    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }

    public String getCondMiseCd() {
        return condMiseCd;
    }

    public void setCondMiseCd(String condMiseCd) {
        this.condMiseCd = condMiseCd;
    }

    public List getListCompany() {
        return listCompany;
    }

    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }


    public List getListEntryState() {
        return listEntryState;
    }

    public void setListEntryState(List listEntryState) {
        this.listEntryState = listEntryState;
    }

    public UIEntryOnerStatus getUiEntryOnerStatus() {
        return uiEntryOnerStatus;
    }

    public void setUiEntryOnerStatus(UIEntryOnerStatus uiEntryOnerStatus) {
        this.uiEntryOnerStatus = uiEntryOnerStatus;
    }

    public List getListEntryOner() {
        return listEntryOner;
    }

    public void setListEntryOner(List listEntryOner) {
        this.listEntryOner = listEntryOner;
    }

    public String getCondOnerCd() {
        return condOnerCd;
    }

    public void setCondOnerCd(String condOnerCd) {
        this.condOnerCd = condOnerCd;
    }


    /**
     * 編集画面で「新規スタッフ」、「スタッフ編集」ボタン押下時の
     * スタッフリストプルダウンで選択されているIndex
     * @return
     */
    public int getAddStaffIndex() {
        return addStaffIndex;
    }

    public void setAddStaffIndex(int addStaffIndex) {
        this.addStaffIndex = addStaffIndex;
    }

    public String getCondOnerNameKj() {
        return condOnerNameKj;
    }

    public void setCondOnerNameKj(String condOnerName) {
        this.condOnerNameKj = condOnerName;
    }

    public List getListMstMise() {
        return listMstMise;
    }

    public void setListMstMise(List listMstMise) {
        this.listMstMise = listMstMise;
    }

    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    public List getListMstMiseOner() {
        return listMstMiseOner;
    }

    public void setListMstMiseOner(List listMstMiseOner) {
        this.listMstMiseOner = listMstMiseOner;
    }


    
    public List getListSofusakiPulldown() {
        return listSofusakiPulldown;
    }
    public void setListSofusakiPulldown(List listSofusakiPulldown) {
        this.listSofusakiPulldown = listSofusakiPulldown;
    }
    
    /**
     * IE以外のブラウザ使用時のメッセージ
     */
    public String getMsgBrowserCheck() {
        if (MlEntryCommon.USER_TYPE_CD_ONER.equals(getUserTypeCd())) {
            return MSG_BROWSER_CHECK;
        }
        return "";
    }
    public void setMsgBrowserCheck(String msg) {
    }
    public void setMsgMosikomiLimitAlert(String msg) {
    }
    /**
     * 全スタッフ削除時に確認画面に表示するメッセージ
     */
    public String getMsgAllStaffDelete() {
        return MSG_ALLSTAFF_DELETE;
    }
    public void setMsgAllStaffDelete(String msg) {
    }
    /**
     * オーナーユーザー 編集画面遷移時のメッセージ
     */
    public String getMsgOnerUserAlert() {
        if (MlEntryCommon.USER_TYPE_CD_ONER.equals(getUserTypeCd())) {
            return MSG_ONERUSER_ALERT;
        }
        return "";
    }
    public void setMsgOnerUserAlert(String msg) {
    }
    public MstOner getMstOner() {
        return mstOner;
    }
    public void setMstOner(MstOner mstOner) {
        this.mstOner = mstOner;
    }
    public int getInsupEntryStateNum() {
        return insupEntryStateNum;
    }
    public void setInsupEntryStateNum(int insupEntryStateNum) {
        this.insupEntryStateNum = insupEntryStateNum;
    }
    public String getDispSecurityInfo() {
        return dispSecurityInfo;
    }
    public void setDispSecurityInfo(String dispSecurityInfo) {
        this.dispSecurityInfo = dispSecurityInfo;
    }
    public List getListPlace() {
        return listPlace;
    }
    public void setListPlace(List listPlace) {
        this.listPlace = listPlace;
    }
    public UIEntryOner getUiEntryOnerKekkaHokoku() {
        return uiEntryOnerKekkaHokoku;
    }
    public void setUiEntryOnerKekkaHokoku(UIEntryOner uiEntryOnerKekkaHokoku) {
        this.uiEntryOnerKekkaHokoku = uiEntryOnerKekkaHokoku;
    }
    public UIEntryOner getUiEntryOnerTanto() {
        return uiEntryOnerTanto;
    }
    public void setUiEntryOnerTanto(UIEntryOner uiEntryOnerTanto) {
        this.uiEntryOnerTanto = uiEntryOnerTanto;
    }
    public List getListEntryDate() {
        return listEntryDate;
    }
    public void setListEntryDate(List listEntryDate) {
        this.listEntryDate = listEntryDate;
    }
    
    
    public List getListEntry() {
        return listEntry;
    }
    public void setListEntry(List listEntry) {
        this.listEntry = listEntry;
    }
    public List getListResult() {
        return listResult;
    }
    public void setListResult(List listResult) {
        this.listResult = listResult;
    }
    public List getListStaff() {
        return listStaff;
    }
    public void setListStaff(List listStaff) {
        this.listStaff = listStaff;
    }
    public List getListEntryStateRegist() {
        return listEntryStateRegist;
    }
    public void setListEntryStateRegist(List listEntryStateRegist) {
        this.listEntryStateRegist = listEntryStateRegist;
    }
    public int getStaffCnt() {
        return staffCnt;
    }
    public void setStaffCnt(int staffCnt) {
        this.staffCnt = staffCnt;
    }
    public String getSelectedStaff() {
        return selectedStaff;
    }
    public void setSelectedStaff(String selectedStaff) {
        this.selectedStaff = selectedStaff;
    }
    public String getEditStaffId() {
        return editStaffId;
    }
    public void setEditStaffId(String editStaffId) {
        this.editStaffId = editStaffId;
    }
    public List getListEntryEnableStaff() {
        return ListEntryEnableStaff;
    }
    public void setListEntryEnableStaff(List listEntryEnableStaff) {
        ListEntryEnableStaff = listEntryEnableStaff;
    }
    public int getListEntryStateRegistSize() {
        if (listEntryStateRegist != null) {
            return listEntryStateRegist.size();
        }
        else {
            return 0;
        }
    }
    public void setListEntryStateRegistSize(int listEntryStateRegistSize) {
    }

    /**
     * Dtoを初期状態にする
     */
    public void clear(){
        
        setAddStaffIndex(0);
        setDispSecurityInfo(null);
        
        setEditStaffId("");
        setEntryCd("");
        setEntryKai("");
        setEntryYear("");
        setExamMsg("");
        setExamNewMsg("");
        
        setSelectedStaff("");
        setInitFlg(false);
        setInsupEntryStateNum(0);
        setListCompany(null);
        setListEntry(null);
        setListEntryDate(null);
        setListEntryEnableStaff(null);
        setListEntryMst(null);
        setListEntryOner(null);
        setListEntryState(null);
        setListEntryStateRegist(null);
        setListEntryStateRegistSize(0);
        setListMstMise(null);
        setListMstMiseOner(null);
        setListPlace(null);
        setListResult(null);
        setListSofusakiPulldown(null);
//add start MLレグレーションテスト後修正 inazawa
        staffFlgClear();
    }
    
    public void allClear(){
        
        setCondCompanyCd("");
        setCondMiseCd("");
        setCondOnerCd("");
        setCondOnerNameKj("");
        setSelectIndex(0);
        setBirdDateInfo(null);
        setBirdUserInfo(null);
        
        clear();
    }
//  add start MLレグレーションテスト後修正 inazawa
    public boolean isEditStaffFlg() {
        return editStaffFlg;
    }
    public void setEditStaffFlg(boolean editStaffFlg) {
        this.editStaffFlg = editStaffFlg;
    }
    public boolean isAddStaffFlg() {
        return addStaffFlg;
    }
    public void setAddStaffFlg(boolean addStaffFlg) {
        this.addStaffFlg = addStaffFlg;
    }
    public void staffFlgClear() {
        setAddStaffFlg(false);
        setEditStaffFlg(false);
    }
    
}