package jp.co.isid.mos.bird.entry.ownerchange.entity;

public class TrnEntryData {
    
    public static final String TABLE = "BT23MLEJ";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String staffId_COLUMN = "STAFF_ID";
    
    public static final String fromDt_COLUMN = "FROM_DT";
    
    public static final String toDt_COLUMN = "TO_DT";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
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
     * スタッフID
     */
    private String staffId;
    
    /**
     * 開始日
     */
    private String fromDt;
    
    /**
     * 終了日
     */
    private String toDt;
    
    /**
     * タイトル
     */
    private String entryTitle;
    
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
     * スタッフIDを取得します。
     * @return スタッフID
     */
    public String getStaffId() {
        return staffId;
    }
    /**
     * スタッフIDを設定します。
     * @param staffId スタッフID
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    /**
     * 開始日を取得します。
     * @return 開始日
     */
    public String getFromDt() {
        return fromDt;
    }
    /**
     * 開始日を設定します。
     * @param fromDt 開始日
     */
    public void setFromDt(String fromDt) {
        this.fromDt = fromDt;
    }
    
    /**
     * 終了日を取得します。
     * @return 終了日
     */
    public String getToDt() {
        return toDt;
    }
    /**
     * 終了日を設定します。
     * @param toDt 終了日
     */
    public void setToDt(String toDt) {
        this.toDt = toDt;
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
}
