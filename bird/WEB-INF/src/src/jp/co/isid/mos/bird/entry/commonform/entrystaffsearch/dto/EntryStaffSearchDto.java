package jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;

/**
 * エントリー系共通 スタッフ選択 画面間データ共有用DTO
 * @author xnkusama
 */
public class EntryStaffSearchDto {

    /* 遷移区分 */
    public static final int RETURNKIND_INIT = 0;
    public static final int RETURNKIND_SELECT = 1;
    public static final int RETURNKIND_CANCEL = 2;

    /*----------------------------*
     * 呼出元画面でセットする情報 *
     *----------------------------*/
    // 初期処理フラグ
    private boolean initialFlag;
    // 遷移元情報
    private String navigationCase;
    // 会社コード
    private String companyCd;
    // オーナーコード
    private String onerCd;
    // ウィンドウID
    private int windowId;
    // モード (0:通常 1:ML受験申込)
    private int mode;
    // エントリー年 (モード：1 で使用)
    private String entryYear;
    // エントリー回 (モード：1 で使用)
    private String entryKai;
    /*------------------------------*
     * スタッフ選択画面での結果情報 *
     *------------------------------*/
    // 遷移区分（0:初期値 1:決定 2:戻る）
    private int returnKind;
    // スタッフ情報
    private MstStaff mstStaff;

    /**
     * クリア処理
     * @return
     */
    public void clear() {
        setNavigationCase(null);
        setCompanyCd(null);
        setOnerCd(null);
        setWindowId(0);
        setReturnKind(0);
        setMode(0);
        setEntryYear(null);
        setEntryKai(null);
    }
    
    public String getNavigationCase() {
        return navigationCase;
    }
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getOnerCd() {
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public int getReturnKind() {
        return returnKind;
    }
    public void setReturnKind(int returnKind) {
        this.returnKind = returnKind;
    }
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public int getMode() {
        return mode;
    }
    public void setMode(int mode) {
        this.mode = mode;
    }
    public String getEntryYear() {
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    public String getEntryKai() {
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }

    public boolean isInitialFlag() {
        return initialFlag;
    }

    public void setInitialFlag(boolean initialFlag) {
        this.initialFlag = initialFlag;
    }

    public MstStaff getMstStaff() {
        return mstStaff;
    }

    public void setMstStaff(MstStaff mstStaff) {
        this.mstStaff = mstStaff;
    }
}
