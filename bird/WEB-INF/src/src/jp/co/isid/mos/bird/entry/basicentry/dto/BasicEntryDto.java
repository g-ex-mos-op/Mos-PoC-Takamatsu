/*
 * 作成日: 2006/06/16
 */
package jp.co.isid.mos.bird.entry.basicentry.dto;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.entity.MstOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * ベーシック研修参加申込 DTO<br>
 * @author kusama
 */
public class BasicEntryDto {
    
    /* メッセージ */
    //ブラウザチェック
    private static final String 
        MSG_BROWSER_CHECK = "お使いのブラウザでは、一部の機能が正常に動作しない場合がございます。";
    //申込人数ワーニング
    private static final String MSG_MOSIKOMI_LIMIT_ALERT = "入力できる人数は最大10人までです。";
    //全スタッフ削除時に確認画面に表示するメッセージ
    private static final String MSG_ALLSTAFF_DELETE = "上記オーナーによるベーシック研修申込登録情報を削除します。";
    //オーナーユーザー 編集画面遷移時のメッセージ
    private static final String MSG_ONERUSER_ALERT = "登録いただくデータは、研修資料作成・送付以外に使用致しません。";
    
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
    
    //---Entity
    // 条件画面 ベーシック研修一覧
    private List listBasicListDataInfo;
    
    
    //エントリーマスタ管理
    private List listEntryMst;
    //エントリー日付管理
    private List listEntryDate;
    //会社一覧
    private List listCompany;
    // エントリーオーナー宛先情報
    private List listEntryOner;
    // 研修エントリー状況
    private List listEntryState;
    // スタッフ一覧
    private List listStaff;
    // 保有店舗(スタッフ用)
    private List listMstMise;
    // オーナー保有店舗
    private List listMstMiseOner;
    // 送付先一覧
    private List listSofusakiPulldown;
    // エントリーオーナー宛先情報（申込責任者）
    private UIEntryOner uiEntryOnerMosikomiSekinin;
    // エントリーオーナー宛先情報（結果報告先）
//    private UIEntryOner uiEntryOnerKekkaHokokusaki;
    // オーナー別エントリー状況
    private UIEntryOnerStatus uiEntryOnerStatus;
    // オーナー情報
    private MstOner mstOner;
    
    //---条件画面レコード選択ラジオボタン
    private int selectIndex;
    //---新規スタッフ追加ボタンのインデックス
    private int addStaffIndex = 0;
    
    /* 編集画面初期処理判定フラグ */
    private boolean initFlg;
    /* 条件画面初期化処理判定フラグ */
    private boolean condClearFlg;
    
    
    /* KEY情報：エントリーコード */
    private String entryCd;
    /* KEY情報：エントリー年 */
    private String entryYear;
    /* KEY情報：エントリー回*/
    private String entryKai;

    /* 同一オーナー１１人以上申込のアラートフラグ */
    private boolean flgEdtiAlert = false;
    private String editAlertMsg = "";
    
    /* 削除以外の研修エントリー状況データの数 */
    private int insupEntryStateNum = 0;
    
    /* オーナーzユーザー 情報セキュリティアラートフラグ */
    private String dispSecurityInfo;
    
    
    /* ユーザー情報 */
    //ユーザータイプ
    private String userTypeCd;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;

    private int nowEntryCount = 0;
    
    private int listEntryCount = 0;
    
    private int staffBottonIndex = 0;
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
    public List getListEntryDate() {
        return listEntryDate;
    }
    public void setListEntryDate(List listEntryDate) {
        this.listEntryDate = listEntryDate;
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
        if (getListBasicListDataInfo() == null) {
            return null;
        }
        return (UIEntryMst) getListBasicListDataInfo().get(getSelectIndex());
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
    
    public boolean isCondClearFlg() {
        return condClearFlg;
    }

    public void setCondClearFlg(boolean condClearFlg) {
        this.condClearFlg = condClearFlg;
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

    public UIEntryOner getUiEntryOnerMosikomiSekinin() {
        return uiEntryOnerMosikomiSekinin;
    }

    public void setUiEntryOnerMosikomiSekinin(UIEntryOner uiEntryOner) {
        this.uiEntryOnerMosikomiSekinin = uiEntryOner;
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

//    public UIEntryOner getUiEntryOnerKekkaHokokusaki() {
//        return uiEntryOnerKekkaHokokusaki;
//    }
//
//    public void setUiEntryOnerKekkaHokokusaki(UIEntryOner uiEntryOnerKekkaHokoku) {
//        this.uiEntryOnerKekkaHokokusaki = uiEntryOnerKekkaHokoku;
//    }

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

    public List getListStaff() {
        return listStaff;
    }

    public void setListStaff(List listStaff) {
        this.listStaff = listStaff;
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

    public boolean isFlgEdtiAlert() {
        return flgEdtiAlert;
    }

    public void setFlgEdtiAlert(boolean flgEdtiAlert) {
        this.flgEdtiAlert = flgEdtiAlert;
    }

    public String getEditAlertMsg() {
        return editAlertMsg;
    }

    public void setEditAlertMsg(String editAlertMsg) {
        this.editAlertMsg = editAlertMsg;
    }
    public List getListBasicListDataInfo() {
        return listBasicListDataInfo;
    }
    public void setListBasicListDataInfo(List listBasicListDataInfo) {
        this.listBasicListDataInfo = listBasicListDataInfo;
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
        if (BasicEntryCommon.USER_TYPE_CD_ONER.equals(getUserTypeCd())) {
            return MSG_BROWSER_CHECK;
        }
        return "";
    }
    public void setMsgBrowserCheck(String msg) {
    }
    /**
     * 申込人数ワーニングMSG（11人以上）
     */
    public String getMsgMosikomiLimitAlert() {
        return MSG_MOSIKOMI_LIMIT_ALERT;
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
        if (BasicEntryCommon.USER_TYPE_CD_ONER.equals(getUserTypeCd())) {
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
    /**
     * ベーシック研修者件数の取得
     * @return
     */
    public int getListEntryCount() {
        listEntryCount = getListEntryState().size();
        return listEntryCount;
    }
    /**
     * 申請者件数の設定
     * 
     */
    public void setListEntryCount(int listEntryCount) {
        this.listEntryCount = listEntryCount;
    }
    /**
     * ベーシック研修者の取得（入力欄追加実行前の件数）
     * @return
     */
    public int getNowEntryCount() {
        return nowEntryCount;
    }
    /**
     * ベーシック研修者の設定（入力欄追加実行前の件数）
     * 
     */
    public void setNowEntryCount(int nowEntryCount) {
        this.nowEntryCount = nowEntryCount;
    }
    /**
     * 選択されたスタッフボタンの取得
     * @return
     */
    public int getStaffBottonIndex() {
        return staffBottonIndex;
    }
    /**
     * 選択されたスタッフボタンの設定
     * @param staffBottonIndex
     */
    public void setStaffBottonIndex(int staffBottonIndex) {
        this.staffBottonIndex = staffBottonIndex;
    }
}