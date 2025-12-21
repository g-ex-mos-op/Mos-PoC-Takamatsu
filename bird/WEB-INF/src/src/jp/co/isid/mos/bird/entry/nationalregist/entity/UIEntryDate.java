package jp.co.isid.mos.bird.entry.nationalregist.entity;

import java.sql.Timestamp;

/**
 * エントリー日付管理エンティティ
 * 
 * @author xjung
 */
public class UIEntryDate {
    /** テーブル名称 */    
    public static final String TABLE = "BR18ENTD";
    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    /** カラム名称：エントリーコード */
    public static final String entryCd_COLUMN = "ENTRY_CD";
    /** カラム名称：エントリー年 */   
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    /** カラム名称：エントリー回 */
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    /** カラム名称：ユーザータイプコード */
    public static final String usertypeCd_COLUMN = "USERTYPE_CD";
    /** カラム名称：日付区分 */
    public static final String dayKbn_COLUMN = "DAY_KBN";
    /** カラム名称：開始日 */
    public static final String fromDt_COLUMN = "FROM_DT";
    /** カラム名称：終了日 */
    public static final String toDt_COLUMN = "TO_DT";
    /** カラム名称：登録ユーザー */
    public static final String firstUser_COLUMN = "FIRST_USER";
    /** カラム名称：登録プログラム */
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    /** カラム名称：登録時タイムスタンプ */
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    /** カラム名称：修正ユーザー */
    public static final String lastUser_COLUMN = "LAST_USER";
    /** カラム名称：修正プログラム */
    public static final String lastPgm_COLUMN = "LAST_PGM";
    /** カラム名称：修正時タイムスタンプ */
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
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
     * ユーザータイプコード
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
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
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
     * ユーザータイプコードを取得します。
     * @return ユーザータイプコード
     */
    public String getUsertypeCd() {
        return usertypeCd;
    }
    /**
     * ユーザータイプコードを設定します。
     * @param usertypeCd ユーザータイプコード
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
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
