/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoregist.dto;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryNotice;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 汎用研修マスタ登録用DTO<br>
 * @author itamoto
 */
public class HanyoRegistDto {
    
    /* 制御用 */
    //画面遷移妥当性チェック用SessionKey
    private String sessionKey;
    //編集モード
    private String editMode;
    /* 条件画面 */
    //---Entity
    //エントリーマスタ管理
    private List listEntryMst;
    //エントリー日付管理
    private List listEntryDate;
    // コースリスト
    private List courseList;
    //---レコード選択ラジオボタン
    private int selectIndex;
    // 申込開始日
    private String applyFromDt;

    /* 編集画面初期処理判定フラグ */
    private boolean initFlg;
    /* 条件画面初期化処理判定フラグ */
    private boolean condClearFlg;
    
    /* 編集画面 */
    // エントリーマスタ管理
    private UIEntryMst uiEntryMst;
    // エントリー日付管理 開催日
    private UIEntryDate uiEntryDateKaisai;
    // エントリー日付管理 本部 表示
    private UIEntryDate uiEntryDateHonbuHyoji;
    // エントリー日付管理 本部 登録
    private UIEntryDate uiEntryDateHonbuToroku;
    // エントリー日付管理 オーナー 表示
    private UIEntryDate uiEntryDateOnerHyoji;
    // エントリー日付管理 オーナー 登録
    private UIEntryDate uiEntryDateOnerToroku;
    // エントリー日付管理 結果登録
    private UIEntryDate uiEntryDateKekka;
    // 注意事項
    private UIEntryCourse uiEntryCourse;
// add start inazawa 20070109 マスタライセンス４次対応
    // エントリー文言情報
    private UIEntryNotice uIEntryNotice;
    //エントリー文言
//    private String notice;
// add end    
    /* KEY情報：エントリーコード */
    private String entryCd;
    /* KEY情報：エントリー年 */
    private String entryYear;
    /* KEY情報：エントリー回*/
    private String entryKai;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;


    /**
     * エントリー名称取得
     */
    public String getEntryName() {
        String name = "";
        if (getEntryCd() != null) {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            List listEntry = (List) container.getComponent("entryList");
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
     * 編集モード時の編集可／不可フラグ
     * 　対象項目：開催日From、本部：受付開始、本部：表示開始、オーナー：受付開始、オーナー：表示開始
     * @return
     */
    public boolean isEditKomoku() {
        boolean editFlg = true;
        if (HanyoRegistCommon.EDIT_MODE_UPDATE.equals(getEditMode())) {
            try {
                String sysDate = getBirdDateInfo().getSysDate();
                String sysDatePlusOne = DateManager.getNextDate(sysDate, 1);
                if ((sysDatePlusOne.compareTo(getUiEntryDateOnerToroku().getFromDt()) >= 0
                        && sysDate.compareTo(getUiEntryDateOnerToroku().getToDt()) <= 0) ||
                     (sysDatePlusOne.compareTo(getUiEntryDateHonbuToroku().getFromDt()) >= 0
                        && sysDate.compareTo(getUiEntryDateHonbuToroku().getToDt()) <= 0))
                {
                    editFlg = false;
                }
            }
            catch (Exception ex) {
                throw new FtlSystemException("日付算出", null, ex);
            }
        }
        return editFlg;
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

	/**
	 * 保持情報のクリア
	 */
	public void clear() {
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
    public List getCourseList() {
        return courseList;
    }
    public void setCourseList(List courseList) {
        this.courseList = courseList;
    }
    public int getSelectIndex() {
        return selectIndex;
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    public String getApplyFromDt() {
        return applyFromDt;
    }
    public void setApplyFromDt(String applyFromDt) {
        this.applyFromDt = applyFromDt;
    }
    public String getSessionKey() {
        return sessionKey;
    }
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
    public UIEntryDate getUiEntryDateHonbuHyoji() {
        return uiEntryDateHonbuHyoji;
    }
    public void setUiEntryDateHonbuHyoji(UIEntryDate uiEntryDateHonbuHyoji) {
        this.uiEntryDateHonbuHyoji = uiEntryDateHonbuHyoji;
    }
    public UIEntryDate getUiEntryDateHonbuToroku() {
        return uiEntryDateHonbuToroku;
    }
    public void setUiEntryDateHonbuToroku(UIEntryDate uiEntryDateHonbuToroku) {
        this.uiEntryDateHonbuToroku = uiEntryDateHonbuToroku;
    }
    public UIEntryDate getUiEntryDateKaisai() {
        return uiEntryDateKaisai;
    }
    public void setUiEntryDateKaisai(UIEntryDate uiEntryDateKaisai) {
        this.uiEntryDateKaisai = uiEntryDateKaisai;
    }
    public UIEntryDate getUiEntryDateOnerHyoji() {
        return uiEntryDateOnerHyoji;
    }
    public void setUiEntryDateOnerHyoji(UIEntryDate uiEntryDateOnerHyoji) {
        this.uiEntryDateOnerHyoji = uiEntryDateOnerHyoji;
    }
    public UIEntryDate getUiEntryDateOnerToroku() {
        return uiEntryDateOnerToroku;
    }
    public void setUiEntryDateOnerToroku(UIEntryDate uiEntryDateOnerToroku) {
        this.uiEntryDateOnerToroku = uiEntryDateOnerToroku;
    }
    public UIEntryMst getUiEntryMst() {
        return uiEntryMst;
    }
    public void setUiEntryMst(UIEntryMst uiEntryMst) {
        this.uiEntryMst = uiEntryMst;
    }
    public String getEditMode() {
        return editMode;
    }
    public void setEditMode(String editMode) {
        this.editMode = editMode;
    }
    public String getEditModeName() {
        String modeName = "";
        if (HanyoRegistCommon.EDIT_MODE_INSERT.equals(getEditMode())) {
            modeName = "新規";
        }
        else if (HanyoRegistCommon.EDIT_MODE_UPDATE.equals(getEditMode())) {
            modeName = "編集";
        }
        else if (HanyoRegistCommon.EDIT_MODE_DELETE.equals(getEditMode())) {
            modeName = "削除";
        }
        return modeName;
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
    public boolean isModeNew() {
        return HanyoRegistCommon.EDIT_MODE_INSERT.equals(getEditMode()) ? true : false;
    }
    public boolean isModeDelete() {
        return HanyoRegistCommon.EDIT_MODE_DELETE.equals(getEditMode()) ? true : false;
    }
    /* isModeDeleteをhiddenタグで使用する為の対処 */
    public void setModeDelete(boolean mode) {
    }
    public String getDeleteConfirmMsg() {
        return "この研修を削除します。現在までの申込の方のデータダウンロードは終了していますか？\n"
              + "まだダウンロードが終了していない場合はキャンセルボタンを押してください。\n"
              + "申込状況確認画面から該当研修を選択し、データをダウンロードしてください。";
    }
    public void setDeleteConfirmMsg(String msg) {
    }
    
    public UIEntryDate getUiEntryDateKekka() {
        return uiEntryDateKekka;
    }

    public void setUiEntryDateKekka(UIEntryDate uiEntryDateKekka) {
        this.uiEntryDateKekka = uiEntryDateKekka;
    }

    public UIEntryCourse getUiEntryCourse() {
        return uiEntryCourse;
    }

    public void setUiEntryCourse(UIEntryCourse uiEntryCourse) {
        this.uiEntryCourse = uiEntryCourse;
    }

    public boolean isCondClearFlg() {
        return condClearFlg;
    } 

    public void setCondClearFlg(boolean condClearFlg) {
        this.condClearFlg = condClearFlg;
    }

    public boolean isEditableKaisaiFromDt() {
        // 新規モード、または申込期間前は変更可能
        return getEditMode().equals(HanyoRegistCommon.EDIT_MODE_INSERT)
                || (getApplyFromDt() == null
                || getBirdDateInfo().getSysDate().compareTo(getApplyFromDt()) < 0);
    }

//  add start inazawa 20070109 マスタライセンス４次対応
    public UIEntryNotice getUIEntryNotice() {
        return uIEntryNotice;
    }

    public void setUIEntryNotice(UIEntryNotice uIEntryNotice) {
        this.uIEntryNotice = uIEntryNotice;
    }
    
//    public String getNotice() {
//        return notice;
//    }
//
//    public void setNotice(String notice) {
//        this.notice = notice;
//    }
//  add end
}