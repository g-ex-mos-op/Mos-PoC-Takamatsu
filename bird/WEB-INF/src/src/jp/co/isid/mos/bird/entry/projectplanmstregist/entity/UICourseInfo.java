package jp.co.isid.mos.bird.entry.projectplanmstregist.entity;

/**
 * 対象事業方針説明会情報エンティティー
 * 
 * @author xkinu
 *
 */
public class UICourseInfo {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String entryPlace_COLUMN = "ENTRY_PLACE";
    
    public static final String numberLimit_COLUMN = "NUMBER_LIMIT";
    
    public static final String placeLimit_COLUMN = "PLACE_LIMIT";
    
    public static final String note_COLUMN = "NOTE";
    
    public static final String notice1_COLUMN = "NOTICE1";

    public static final String execDt_COLUMN = "EXEC_DT";
    
    public static final String entryFrom_COLUMN = "ENTRY_FROM";
    
    public static final String entryTo_COLUMN = "ENTRY_TO";
    
    public static final String dispFrom_COLUMN = "DISP_FROM";
    
    public static final String dispTo_COLUMN = "DISP_TO";
    
    public static final String onerEntryFrom_COLUMN = "ONER_ENTRY_FROM";
    
    public static final String onerEntryTo_COLUMN = "ONER_ENTRY_TO";
    
    public static final String onerDispFrom_COLUMN = "ONER_DISP_FROM";
    
    public static final String onerDispTo_COLUMN = "ONER_DISP_TO";
    
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
     * エントリータイトル
     */
    private String entryTitle;
    
    /**
     * 開催場所
     */
    private String entryPlace;
    
    /**
     * 申込定員
     */
    private java.math.BigDecimal numberLimit;
    
    /**
     * 会場定員
     */
    private java.math.BigDecimal placeLimit;
    
    /**
     * 備考
     */
    private String note;
    /**
     * 注意事項
     */
    private String notice1;
    
    /**
     * 開催日
     */
    private String execDt;
    
    /**
     * 申込開始日
     */
    private String entryFrom;
    
    /**
     * 申込終了日
     */
    private String entryTo;
    
    /**
     * 表示開始日
     */
    private String dispFrom;
    
    /**
     * 表示終了日
     */
    private String dispTo;
    
    /**
     * オーナー申込開始日
     */
    private String onerEntryFrom;
    
    /**
     * オーナー申込終了日
     */
    private String onerEntryTo;
    
    /**
     * オーナー表示開始日
     */
    private String onerDispFrom;
    
    /**
     * オーナー表示終了日
     */
    private String onerDispTo;
    
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
     * エントリータイトルを取得します。
     * @return エントリータイトル
     */
    public String getEntryTitle() {
        return entryTitle;
    }
    /**
     * エントリータイトルを設定します。
     * @param entryTitle エントリータイトル
     */
    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }
    
    /**
     * 開催場所を取得します。
     * @return 開催場所
     */
    public String getEntryPlace() {
        return entryPlace;
    }
    /**
     * 開催場所を設定します。
     * @param entryPlace 開催場所
     */
    public void setEntryPlace(String entryPlace) {
        this.entryPlace = entryPlace;
    }
    
    /**
     * 申込定員を取得します。
     * @return 申込定員
     */
    public java.math.BigDecimal getNumberLimit() {
        return numberLimit;
    }
    /**
     * 申込定員を設定します。
     * @param numberLimit 申込定員
     */
    public void setNumberLimit(java.math.BigDecimal numberLimit) {
        this.numberLimit = numberLimit;
    }
    
    /**
     * 会場定員を取得します。
     * @return 会場定員
     */
    public java.math.BigDecimal getPlaceLimit() {
        return placeLimit;
    }
    /**
     * 会場定員を設定します。
     * @param placeLimit 会場定員
     */
    public void setPlaceLimit(java.math.BigDecimal placeLimit) {
        this.placeLimit = placeLimit;
    }
    
    /**
     * 備考を取得します。
     * @return 備考
     */
    public String getNote() {
        return note;
    }
    /**
     * 備考を設定します。
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /**
     * 開催日を取得します。
     * @return 開催日
     */
    public String getExecDt() {
        return execDt;
    }
    /**
     * 開催日を設定します。
     * @param execDt 開催日
     */
    public void setExecDt(String date) {
        this.execDt = date;
    }
    
    /**
     * 申込開始日を取得します。
     * @return 申込開始日
     */
    public String getEntryFrom() {
        return entryFrom;
    }
    /**
     * 申込開始日を設定します。
     * @param entryFrom 申込開始日
     */
    public void setEntryFrom(String date) {
        this.entryFrom = date;
    }
    
    /**
     * 申込終了日を取得します。
     * @return 申込終了日
     */
    public String getEntryTo() {
        return entryTo;
    }
    /**
     * 申込終了日を設定します。
     * @param entryTo 申込終了日
     */
    public void setEntryTo(String date) {
        this.entryTo = date;
    }
    
    /**
     * 表示開始日を取得します。
     * @return 表示開始日
     */
    public String getDispFrom() {
        return dispFrom;
    }
    /**
     * 表示開始日を設定します。
     * @param dispFrom 表示開始日
     */
    public void setDispFrom(String date) {
        this.dispFrom = date;
    }
    
    /**
     * 表示終了日を取得します。
     * @return 表示終了日
     */
    public String getDispTo() {
        return dispTo;
    }
    /**
     * 表示終了日を設定します。
     * @param dispTo 表示終了日
     */
    public void setDispTo(String date) {
        this.dispTo = date;
    }
    
    /**
     * オーナー申込開始日を取得します。
     * @return オーナー申込開始日
     */
    public String getOnerEntryFrom() {
        return onerEntryFrom;
    }
    /**
     * オーナー申込開始日を設定します。
     * @param onerEntryFrom オーナー申込開始日
     */
    public void setOnerEntryFrom(String date) {
        this.onerEntryFrom = date;
    }
    
    /**
     * オーナー申込終了日を取得します。
     * @return オーナー申込終了日
     */
    public String getOnerEntryTo() {
        return onerEntryTo;
    }
    /**
     * オーナー申込終了日を設定します。
     * @param onerEntryTo オーナー申込終了日
     */
    public void setOnerEntryTo(String date) {
        this.onerEntryTo = date;
    }
    
    /**
     * オーナー表示開始日を取得します。
     * @return オーナー表示開始日
     */
    public String getOnerDispFrom() {
        return onerDispFrom;
    }
    /**
     * オーナー表示開始日を設定します。
     * @param onerDispFrom オーナー表示開始日
     */
    public void setOnerDispFrom(String date) {
        this.onerDispFrom = date;
    }
    
    /**
     * オーナー表示終了日を取得します。
     * 型はYYYYMMDD
     * 
     * @return オーナー表示終了日
     */
    public String getOnerDispTo() {
        return onerDispTo;
    }
    /**
     * オーナー表示終了日を設定します。
     * 型はYYYYMMDD
     * 
     * @param onerDispTo オーナー表示終了日
     */
    public void setOnerDispTo(String date) {
        this.onerDispTo = date;
    }
    /**
     * @return notice1 を戻します。
     */
    public String getNotice1() {
        return notice1;
    }
    /**
     * @param notice1 設定する notice1。
     */
    public void setNotice1(String notice1) {
        this.notice1 = notice1;
    }
}
