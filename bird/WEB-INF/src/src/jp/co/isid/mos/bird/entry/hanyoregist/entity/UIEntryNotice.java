package jp.co.isid.mos.bird.entry.hanyoregist.entity;

import java.sql.Timestamp;

public class UIEntryNotice {
    
    public static final String TABLE = "BR51ENCI";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String notice1_COLUMN = "NOTICE1";
    
    public static final String notice2_COLUMN = "NOTICE2";
    
    public static final String notice3_COLUMN = "NOTICE3";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
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
     * 事業方針説明会
     */
    private String notice1;
    
    /**
     * 研修マスタ登録
     */
    private String notice2;
    
    /**
     * 
     */
    private String notice3;
    
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
     * 事業方針説明会を取得します。
     * @return 事業方針説明会
     */
    public String getNotice1() {
        return notice1;
    }
    /**
     * 事業方針説明会を設定します。
     * @param notice1 事業方針説明会
     */
    public void setNotice1(String notice1) {
        this.notice1 = notice1;
    }
    
    /**
     * 研修マスタ登録を取得します。
     * @return 研修マスタ登録
     */
    public String getNotice2() {
        return notice2;
    }
    /**
     * 研修マスタ登録を設定します。
     * @param notice2 研修マスタ登録
     */
    public void setNotice2(String notice2) {
        this.notice2 = notice2;
    }
    
    /**
     * を取得します。
     * @return 
     */
    public String getNotice3() {
        return notice3;
    }
    /**
     * を設定します。
     * @param notice3 
     */
    public void setNotice3(String notice3) {
        this.notice3 = notice3;
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
