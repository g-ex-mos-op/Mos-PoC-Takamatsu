package jp.co.isid.mos.bird.entry.eventlist.entity;

public class CtlEntryMaster {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String entryName_COLUMN = "ENTRY_NAME";
    
    public static final String openFromDt_COLUMN = "OPEN_FROM_DT";
    
    public static final String openToDt_COLUMN = "OPEN_TO_DT";
    
    public static final String onerApplyFromDt_COLUMN = "ONER_APPLY_FROM_DT";
    
    public static final String onerApplyToDt_COLUMN = "ONER_APPLY_TO_DT";
    
    public static final String applyFromDt_COLUMN = "APPLY_FROM_DT";
    
    public static final String applyToDt_COLUMN = "APPLY_TO_DT";
    
    public static final String entryType_COLUMN = "ENTRY_TYPE";
    
    public static final String viewId_COLUMN = "VIEW_ID";
    
    /**
     * エントリーコード
     */
    private String entryCd;
    
    /**
     * エントリー年
     */
    private String entryYear;
    
    /**
     * エントリー回
     */
    private String entryKai;
    
    /**
     * タイトル
     */
    private String entryTitle;
    
    /**
     * エントリー名称
     */
    private String entryName;
    
    /**
     * 開始日(開催日)
     */
    private String openFromDt;
    
    /**
     * 終了日(開催日)
     */
    private String openToDt;
    
    /**
     * 開始日(オーナー申込日)
     */
    private String onerApplyFromDt;
    
    /**
     * 終了日(オーナー申込日)
     */
    private String onerApplyToDt;
    
    /**
     * 開始日(指定ユーザタイプの申込日)
     */
    private String applyFromDt;
    
    /**
     * 終了日(指定ユーザタイプの申込日)
     */
    private String applyToDt;
    
    /**
     * 区分
     */
    private String entryType;
    
    /**
     * ビューID
     */
    private String viewId;
    
    /**
     * エントリーコードを取得します。
     * @return エントリーコード
     */
    public String getEntryCd() {
        return entryCd;
    }
    /**
     * エントリーコードを設定します。
     * @param entryCd エントリーコード
     */
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    
    /**
     * エントリー年を取得します。
     * @return エントリー年
     */
    public String getEntryYear() {
        return entryYear;
    }
    /**
     * エントリー年を設定します。
     * @param entryYear エントリー年
     */
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    
    /**
     * エントリー回を取得します。
     * @return エントリー回
     */
    public String getEntryKai() {
        return entryKai;
    }
    /**
     * エントリー回を設定します。
     * @param entryKai エントリー回
     */
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    
    /**
     * タイトルを取得します。
     * @return タイトル
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * タイトルを設定します。
     * @param entryTitle タイトル
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * エントリー名称を取得します。
     * @return エントリー名称
     */
    public String getEntryName() {
        return entryName;
    }
    /**
     * エントリー名称を設定します。
     * @param entryName エントリー名称
     */
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }
    
    /**
     * 開始日(開催日)を取得します。
     * @return 開始日(開催日)
     */
    public String getOpenFromDt() {
        return openFromDt;
    }
    /**
     * 開始日(開催日)を設定します。
     * @param openFromDt 開始日(開催日)
     */
    public void setOpenFromDt(String openFromDt) {
        this.openFromDt = openFromDt;
    }
    
    /**
     * 終了日(開催日)を取得します。
     * @return 終了日(開催日)
     */
    public String getOpenToDt() {
        return openToDt;
    }
    /**
     * 終了日(開催日)を設定します。
     * @param openToDt 終了日(開催日)
     */
    public void setOpenToDt(String openToDt) {
        this.openToDt = openToDt;
    }
    
    /**
     * 開始日(オーナー申込日)を取得します。
     * @return 開始日(オーナー申込日)
     */
    public String getOnerApplyFromDt() {
        return onerApplyFromDt;
    }
    /**
     * 開始日(オーナー申込日)を設定します。
     * @param onerApplyFromDt 開始日(オーナー申込日)
     */
    public void setOnerApplyFromDt(String onerApplyFromDt) {
        this.onerApplyFromDt = onerApplyFromDt;
    }
    
    /**
     * 終了日(オーナー申込日)を取得します。
     * @return 終了日(オーナー申込日)
     */
    public String getOnerApplyToDt() {
        return onerApplyToDt;
    }
    /**
     * 終了日(オーナー申込日)を設定します。
     * @param onerApplyToDt 終了日(オーナー申込日)
     */
    public void setOnerApplyToDt(String onerApplyToDt) {
        this.onerApplyToDt = onerApplyToDt;
    }
    
    /**
     * 開始日(指定ユーザタイプの申込日)を取得します。
     * @return 開始日(指定ユーザタイプの申込日)
     */
    public String getApplyFromDt() {
        return applyFromDt;
    }
    /**
     * 開始日(指定ユーザタイプの申込日)を設定します。
     * @param applyFromDt 開始日(指定ユーザタイプの申込日)
     */
    public void setApplyFromDt(String applyFromDt) {
        this.applyFromDt = applyFromDt;
    }
    
    /**
     * 終了日(指定ユーザタイプの申込日)を取得します。
     * @return 終了日(指定ユーザタイプの申込日)
     */
    public String getApplyToDt() {
        return applyToDt;
    }
    /**
     * 終了日(指定ユーザタイプの申込日)を設定します。
     * @param applyToDt 終了日(指定ユーザタイプの申込日)
     */
    public void setApplyToDt(String applyToDt) {
        this.applyToDt = applyToDt;
    }
    
    /**
     * エントリータイプを取得します。
     * @return エントリータイプ
     */
    public String getEntryType() {
        return entryType;
    }
    /**
     * エントリータイプを設定します。
     * @param entryType エントリータイプ
     */
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }
    
    /**
     * ビューIDを取得します。
     * @return ビューID
     */
    public String getViewId() {
        return viewId;
    }
    /**
     * ビューIDを設定します。
     * @param viewId ビューID
     */
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }
}
