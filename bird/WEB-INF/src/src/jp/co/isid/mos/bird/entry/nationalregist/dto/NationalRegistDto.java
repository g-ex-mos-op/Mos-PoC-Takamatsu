package jp.co.isid.mos.bird.entry.nationalregist.dto;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.nationalregist.common.NationalRegistConstants;
import jp.co.isid.mos.bird.entry.nationalregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.nationalregist.entity.UIEntrySelection;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 全国大会マスタ登録情報DTO
 *
 * @author xjung
 */
public class NationalRegistDto extends CommonDto {
    /** セッション関連情報管理クラス */
    private MakeSessionKey mkSession = new MakeSessionKey();

    /** 編集モード */
    private int editMode;

    /**
     * 選択ラジオボタンインデックス
     */
    private int selectIndex;

    /** 日付サイズ */
    private int dateSize;

    /** 申込区分サイズ */
    private int entryKbnSize;

    /** オプショナルサイズ*/
    private int optionalSize;

    /** 全国大会マスタ情報 */
    private UIEntryMst uiEntryMst;

    /** 全国大会マスタ情報リスト */
    private List mstInfoList;

    /** 全国大会日付情報リスト */
    private List dateInfoList;

    /** 申込区分リスト */
    private List entryKbnList;

    /** オプショナルリスト */
    private List optionalList;

    /**
     * セッション関連情報を取得する
     * @return セッション関連情報
     */
    public MakeSessionKey getMkSession() {
        return mkSession;
    }

    /**
     * セッション関連情報を設定する
     * @param mkSession セッション関連情報
     */
    public void setMkSession(MakeSessionKey mkSession) {
        this.mkSession = mkSession;
    }

    /**
     * 編集モードを取得する
     * @return 編集モード
     */
    public int getEditMode() {
        return editMode;
    }

    /**
     * 編集モードを設定する
     * @param editMode 編集モード
     */
    public void setEditMode(int editMode) {
        this.editMode = editMode;
    }

    /**
     * 選択ラジオボタンインデックスを取得する
     * @return 選択ラジオボタンインデックス
     */
    public int getSelectIndex() {
        return selectIndex;
    }

    /**
     * 選択ラジオボタンインデックスを設定する
     * @param selectIndex 選択ラジオボタンインデックス
     */
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    /**
     * 日付サイズを取得する
     * @return 日付サイズ
     */
    public int getDateSize() {
        return dateSize;
    }

    /**
     * 日付サイズを設定する
     * @param dateSize 日付サイズ
     */
    public void setDateSize(int dateSize) {
        this.dateSize = dateSize;
    }

    /**
     * 申込区分サイズを取得する
     * @return 申込区分サイズ
     */
    public int getEntryKbnSize() {
        return entryKbnSize;
    }

    /**
     * 申込区分サイズを設定する
     * @param entryKbnSize 申込区分サイズ
     */
    public void setEntryKbnSize(int entryKbnSize) {
        this.entryKbnSize = entryKbnSize;
    }

    /**
     * オプショナルサイズを取得する
     * @return オプショナルサイズ
     */
    public int getOptionalSize() {
        return optionalSize;
    }

    /**
     * オプショナルサイズを設定する
     * @param optionalSize オプショナルサイズ
     */
    public void setOptionalSize(int optionalSize) {
        this.optionalSize = optionalSize;
    }

    /**
     * 全国大会マスタ情報を取得する
     * @return 全国大会マスタ情報
     */
    public UIEntryMst getUiEntryMst() {
        return uiEntryMst;
    }

    /**
     * 全国大会マスタ情報を設定する
     * @param uiEntryMst 全国大会マスタ情報
     */
    public void setUiEntryMst(UIEntryMst uiEntryMst) {
        this.uiEntryMst = uiEntryMst;
    }

    /**
     * 全国大会マスタ情報リストを取得する
     * @return 全国大会マスタ情報リスト
     */
    public List getMstInfoList() {
        return mstInfoList;
    }

    /**
     * 全国大会マスタ情報リストを設定する
     * @param mstInfoList 全国大会マスタ情報リスト
     */
    public void setMstInfoList(List mstInfoList) {
        this.mstInfoList = mstInfoList;
    }

    /**
     * 全国大会日付情報リストを取得する
     * @return 全国大会日付情報リスト
     */
    public List getDateInfoList() {
        return dateInfoList;
    }

    /**
     * 全国大会日付情報リストを設定する
     * @param dateInfoList 全国大会日付情報リスト
     */
    public void setDateInfoList(List dateInfoList) {
        this.dateInfoList = dateInfoList;
    }

    /**
     * 申込区分リストを取得する
     * @return 申込区分リスト
     */
    public List getEntryKbnList() {
        return entryKbnList;
    }

    /**
     * 申込区分リストを設定する
     * @param entryKbnList 申込区分リスト
     */
    public void setEntryKbnList(List entryKbnList) {
        this.entryKbnList = entryKbnList;
    }

    /**
     * オプショナルリストを取得する
     * @return オプショナルリスト
     */
    public List getOptionalList() {
        return optionalList;
    }

    /**
     * オプショナルリストを設定する
     * @param optionalList オプショナルリスト
     */
    public void setOptionalList(List optionalList) {
        this.optionalList = optionalList;
    }

    /**
     * 編集・削除出来る全国大会情報有無を判断する
     * @return true:有、false：無
     */
    public boolean isExistRegistData(){
        boolean rstFlg = getMstInfoList() == null || getMstInfoList().isEmpty()
            ? false : true;
        if (rstFlg) {
            UIEntryMst entity = (UIEntryMst) getMstInfoList().get(0);
            if(entity.getEntryYear().compareTo(getSysNendo()) < 0) {
                rstFlg = false;
            }
        }
        return rstFlg;
    }

    /**
     * 全国大会マスタ情報存在有無判断する
     * @return true:無、false：有
     */
    public boolean isEmptyMstInfoList(){
        return getMstInfoList() == null || getMstInfoList().isEmpty()
            ? true : false;
    }

    /**
     * 対象処理を取得する
     * @return 対象処理
     */
    public String getTargetProcess() {
        if(NationalRegistConstants.EDIT_MODE_INSERT == getEditMode()){
            return NationalRegistConstants.PROCESS_INSERT;
        }
        else if(NationalRegistConstants.EDIT_MODE_UPDATE == getEditMode()){
            return NationalRegistConstants.PROCESS_UPDATE;
        }
        else if(NationalRegistConstants.EDIT_MODE_DELETE == getEditMode()){
            return NationalRegistConstants.PROCESS_DELETE;
        }
        return NationalRegistConstants.EMPTY;
    }

    /**
     * 本部又はオーナーの申込の開始有無を判定する
     * @return true:開始、false:未開始
     */
    public boolean isReadOnly() {
        // 本部又はオーナー申込開始日≦システム日付の場合、true
        return (NationalRegistConstants.EDIT_MODE_UPDATE == getEditMode()
                && getSysDate() != null
                && ((getUiEntryMst().getHonbuEntryFrom() != null
                        && getUiEntryMst().getHonbuEntryFrom().compareTo(getSysDate()) < 1)
                    || (getUiEntryMst().getOnerEntryFrom() !=null
                        && getUiEntryMst().getOnerEntryFrom().compareTo(getSysDate()) < 1)))
                ? true : false;
    }

    /**
     * システム日付の年度を取得する
     * @return システム日付の年度
     */
    public String getSysNendo() {
        String sysNendo = null;
        try{
            sysNendo = DateManager.getCurrentYear(getSysDate());
        }catch(Exception e){
            // 何もしない
        }
        return sysNendo;
    }

    /**
     * セッションキー有効チェックを行う
     * @return true：有効、false：無効
     */
    public boolean isValidSessionKey(){
        return mkSession.isValidSessionKey(
            getNowSessionKey(), getSessionKey());
    }

    /**
     * 全国大会マスタ登録情報を削除する
     */
    public void clear() {
        setSelectIndex(0);
        setUiEntryMst(new UIEntryMst());
        setMstInfoList(null);
        setDateInfoList(null);
        setEntryKbnList(initSelectionList(NationalRegistConstants.SEL_KBN_ENTRY));
        setOptionalList(initSelectionList(NationalRegistConstants.SEL_KBN_OPTIONAL));
    }

    /**
     * セレクションリストを初期化する(申込区分、オプショナル用)
     * @param selectionKbn セレクション区分
     * @return セレクションリスト
     */
    private List initSelectionList(String selectionKbn) {
        List selectList = new ArrayList();
        int maxSize = 0;
        if("1".equals(selectionKbn)){
        	maxSize = 10;
        }else{
        	maxSize = 30;
        }
        for(int i = 0; i < maxSize; i++) {
            UIEntrySelection info = new UIEntrySelection();
            info.setSelectionKbn(selectionKbn);
            selectList.add(info);
        }
        return selectList;
    }
}