/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.dto;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MstOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 汎用研修マスタ登録用DTO<br>
 * @author itamoto
 */
public class HanyoApplicationDto {
    
    /* メッセージ */
    //ブラウザチェック
    private static final String 
        MSG_BROWSER_CHECK = "お使いのブラウザでは、一部の機能が正常に動作しない場合がございます。";
//  delete start inazawa 2006/01/09 マスタライセンス４次対応
    //申込人数ワーニング
//    private static final String MSG_MOSIKOMI_LIMIT_ALERT = "入力できる人数は最大10人までです。";
// delete end    
    //全スタッフ削除時に確認画面に表示するメッセージ
    private static final String MSG_ALLSTAFF_DELETE = "上記オーナーによる{0}申込登録情報を削除します。";
    //オーナーユーザー 編集画面遷移時のメッセージ
    private static final String MSG_ONERUSER_ALERT = "登録いただくデータは、研修資料作成・送付以外に使用致しません。";
    
    /* 削除以外の研修エントリー状況データの数 */
    private int insupEntryStateNum = 0;
    
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
    // 受講案内送付先
    private List listSofusakiPulldown;
    // 保有店舗(スタッフ用)
    private List listMstMise;
    // オーナー保有店舗
    private List listMstMiseOner;
    // 条件画面 表示対象エントリーキー一覧
    private List listDispEntryList;
    // エントリーオーナー宛先情報（申込責任者）
    private UIEntryOwner uiEntryOwnerMosikomiSekinin;
//    // エントリーオーナー宛先情報（結果報告先）
//    private UIEntryOwner uiEntryOwnerKekkaHokokusaki;
    // オーナー別エントリー状況
    private UIEntryOnerStatus uiEntryOnerStatus;
    // オーナー情報
    private MstOwner mstOner;
    
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

//  delete start inazawa 2006/01/09 マスタライセンス４次対応
    /* 同一オーナー１１人以上申込のアラートフラグ */
//    private boolean flgEdtiAlert = false;
//    private String editAlertMsg = "";
//  delete end

    /* オーナーzユーザー 情報セキュリティアラートフラグ */
    private String dispSecurityInfo;
    
    /* 編集、表示可能なエントリーの数 */
    private int radioCnt = 0;
    /* ユーザー情報 */
    //ユーザータイプ
    private String userTypeCd;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
// add start 2009/01/09 inazawa マスタライセンス４次対応
    /*文言情報*/
    private String notice;
// add end
    private int staffBottonIndex = 0;
    
    private int listEntryCount = 0;
    
    private int afterEntryCount = 0;

    /**
     * エントリー名称取得
     */
    public String getEntryName() {
        String name = "";
        if (getEntryCd() != null) {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            List listEntry = (List) container.getComponent("entryListHanyoApplication");
            for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
                EntryDto dto = (EntryDto) ite.next();
                if (getEntryCd().equals(dto.getKey())) {
                    name = dto.getName();
                    break;
                }
            }
        }
        return name;
    }
    
    /**
     * 申込日取得
     */
    public String getEntryDate() {
        String entryDate = "";
        
        if (getInsupEntryStateNum() > 0) {
            Timestamp entryTitestamp = ((UIEntryState)getListEntryState().get(0)).getLastTmsp();
            if (entryTitestamp != null) {
                // 年月日の取得
                entryDate = entryTitestamp.toString().substring(0,10).replaceAll("-","");
            }
        }
        
        return entryDate;
    }
    
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

    public UIEntryOwner getUiEntryOwnerMosikomiSekinin() {
        return uiEntryOwnerMosikomiSekinin;
    }

    public void setUiEntryOwnerMosikomiSekinin(UIEntryOwner uiEntryOwner) {
        this.uiEntryOwnerMosikomiSekinin = uiEntryOwner;
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

//    public UIEntryOwner getUiEntryOwnerKekkaHokokusaki() {
//        return uiEntryOwnerKekkaHokokusaki;
//    }
//
//    public void setUiEntryOwnerKekkaHokokusaki(UIEntryOwner uiEntryOwnerKekkaHokoku) {
//        this.uiEntryOwnerKekkaHokokusaki = uiEntryOwnerKekkaHokoku;
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

    public List getListSofusakiPulldown() {
        return listSofusakiPulldown;
    }

    public void setListSofusakiPulldown(List listSofusaki) {
        this.listSofusakiPulldown = listSofusaki;
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

//  delete start inazawa 2006/01/09 マスタライセンス４次対応
//    public boolean isFlgEdtiAlert() {
//        return flgEdtiAlert;
//    }
//
//    public void setFlgEdtiAlert(boolean flgEdtiAlert) {
//        this.flgEdtiAlert = flgEdtiAlert;
//    }

//    public String getEditAlertMsg() {
//        return editAlertMsg;
//    }
//
//    public void setEditAlertMsg(String editAlertMsg) {
//        this.editAlertMsg = editAlertMsg;
//    }
//  delete end

    /**
     * IE以外のブラウザ使用時のメッセージ
     */
    public String getMsgBrowserCheck() {
        if (HanyoApplicationCommon.USER_TYPE_CD_ONER.equals(getUserTypeCd())) {
            return MSG_BROWSER_CHECK;
        }
        return "";
    }
    public void setMsgBrowserCheck(String msg) {
    }
//  delete start inazawa 2006/01/09 マスタライセンス４次対応
    /**
     * 申込人数ワーニングMSG（11人以上）
     */
//    public String getMsgMosikomiLimitAlert() {
//        return MSG_MOSIKOMI_LIMIT_ALERT;
//    }
// delete end    
    public void setMsgMosikomiLimitAlert(String msg) {
    }
    /**
     * 全スタッフ削除時に確認画面に表示するメッセージ
     */
    public String getMsgAllStaffDelete() {
        String param = "";
        if (HanyoApplicationCommon.ENTRYCD_SHUTTYO.equals(getEntryCd())) {
            param = HanyoApplicationCommon.ENTRY_NAME_SHUTTYO;
        } else if (HanyoApplicationCommon.ENTRYCD_KOUSIN.equals(getEntryCd())) {
            param = HanyoApplicationCommon.ENTRY_NAME_KOUSIN;
        }
        return MessageFormat.format(MSG_ALLSTAFF_DELETE, new Object[] { param });
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

    public MstOwner getMstOner() {
        return mstOner;
    }

    public void setMstOner(MstOwner mstOner) {
        this.mstOner = mstOner;
    }

    public int getInsupEntryStateNum() {
        return insupEntryStateNum;
    }

    public void setInsupEntryStateNum(int insupEntryStateNum) {
        this.insupEntryStateNum = insupEntryStateNum;
    }

    public List getListDispEntryList() {
        return listDispEntryList;
    }

    public void setListDispEntryList(List listDispEntryList) {
        this.listDispEntryList = listDispEntryList;
    }

    public String getDispSecurityInfo() {
        return dispSecurityInfo;
    }

    public void setDispSecurityInfo(String dispSecurityInfo) {
        this.dispSecurityInfo = dispSecurityInfo;
    }

    /**
     * 編集、表示可能なエントリーの数を取得
     * @return
     */
    public int getRadioCnt() {
        return radioCnt;
    }
    /**
     * 編集、表示可能なエントリーの数を設定
     * @param radioCnt
     */
    public void setRadioCnt(int radioCnt) {
        this.radioCnt = radioCnt;
    }
//  add start 2009/01/09 inazawa マスタライセンス４次対応
    /**
     * 文言情報の取得
     * @return
     */
    public String getNotice() {
        return notice;
    }
    /**
     * 文言情報の設定
     * @param notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }
//  add start 2009/01/09 inazawa マスタライセンス４次対応
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
    /**
     * 申請者件数の取得
     * @return
     */
    public int getListEntryCount() {
        return getListEntryState().size();
    }
    /**
     * 申請者件数の設定
     * 
     */
    public void setListEntryCount(int listEntryCount) {
        this.listEntryCount = listEntryCount;
    }
    /**
     * 申請者件数の取得（入力欄追加実行前の件数）
     * @return
     */
    public int getEntryAfterCount() {
        return afterEntryCount;
    }
    /**
     * 申請者件数の設定（入力欄追加実行前の件数）
     * 
     */
    public void setEntryAfterCount(int afterEntryCount) {
        this.afterEntryCount = afterEntryCount;
    }

}