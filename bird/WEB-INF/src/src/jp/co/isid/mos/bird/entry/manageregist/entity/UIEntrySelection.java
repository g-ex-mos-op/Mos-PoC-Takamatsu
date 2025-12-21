package jp.co.isid.mos.bird.entry.manageregist.entity;

import java.sql.Timestamp;

/**
 * エントリーセレクション管理エンティティ
 * 
 * @author xjung
 */
public class UIEntrySelection {
    /** テーブル名称 */
    public static final String TABLE = "BR53ENTS";
    /** 排他処理用のタイムスタンプ */
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";
    /** カラム名称：エントリーコード */
    public static final String entryCd_COLUMN = "ENTRY_CD";
    /** カラム名称：エントリー年 */
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    /** カラム名称：エントリー回 */
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    /** カラム名称：セレクション区分 */
    public static final String selectionKbn_COLUMN = "SELECTION_KBN";
    /** カラム名称：セレクションインデックス */
    public static final String selectionIndex_COLUMN = "SELECTION_INDEX";
    /** カラム名称：セレクション名称 */
    public static final String selectionName_COLUMN = "SELECTION_NAME";
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
     * セレクション区分
     */
    private String selectionKbn;
    
    /**
     * セレクションインデックス
     */
    private String selectionIndex;
    
    /**
     * セレクション名称
     */
    private String selectionName;
    
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
     * セレクション区分を取得します。
     * @return セレクション区分
     */
    public String getSelectionKbn() {
        return selectionKbn;
    }
    /**
     * セレクション区分を設定します。
     * @param selectionKbn セレクション区分
     */
    public void setSelectionKbn(String selectionKbn) {
        this.selectionKbn = selectionKbn;
    }
    
    /**
     * セレクションインデックスを取得します。
     * @return セレクションインデックス
     */
    public String getSelectionIndex() {
        return selectionIndex;
    }
    /**
     * セレクションインデックスを設定します。
     * @param selectionIndex セレクションインデックス
     */
    public void setSelectionIndex(String selectionIndex) {
        this.selectionIndex = selectionIndex;
    }
    
    /**
     * セレクション名称を取得します。
     * @return セレクション名称
     */
    public String getSelectionName() {
        return selectionName != null ? selectionName.trim() : selectionName;
    }
    /**
     * セレクション名称を設定します。
     * @param selectionName セレクション名称
     */
    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
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
