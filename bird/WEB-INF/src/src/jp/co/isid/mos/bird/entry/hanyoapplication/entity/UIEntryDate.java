package jp.co.isid.mos.bird.entry.hanyoapplication.entity;

public class UIEntryDate {
    
    public static final String TABLE = "BR18ENTD";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    
    public static final String dayKbn_COLUMN = "DAY_KBN";
    
    public static final String fromDt_COLUMN = "FROM_DT";
    
    public static final String toDt_COLUMN = "TO_DT";
    
    public static final String editFlg_COLUMN = "EDIT_FLG";
    
    public static final String displayFlg_COLUMN = "DISPLAY_FLG";
    
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
     * ユーザタイプコード
     */
    private String usertypeCd;
    
    /**
     * 日付区分
     */
    private String dayKbn;
    
    /**
     * 開始日
     */
    private String fromDt;
    
    /**
     * 終了日
     */
    private String toDt;
    
    /**
     * 編集フラグ
     */
    private String editFlg;
    
    /**
     * 表示フラグ
     */
    private String displayFlg;
    
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
     * ユーザタイプコードを取得します。
     * @return ユーザタイプコード
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * ユーザタイプコードを設定します。
     * @param usertypeCd ユーザタイプコード
     */
    public void setUsertypeCd(String usertypeCd) {
        this.usertypeCd = usertypeCd;
    }
    
    /**
     * 日付区分を取得します。
     * @return 日付区分
     */
    public String getDayKbn() {
        return dayKbn;
    }
    /**
     * 日付区分を設定します。
     * @param dayKbn 日付区分
     */
    public void setDayKbn(String dayKbn) {
        this.dayKbn = dayKbn;
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
    public String getDisplayFlg() {
        return displayFlg;
    }
    public void setDisplayFlg(String displayFlg) {
        this.displayFlg = displayFlg;
    }
    public String getEditFlg() {
        return editFlg;
    }
    public void setEditFlg(String editFlg) {
        this.editFlg = editFlg;
    }
    
}
