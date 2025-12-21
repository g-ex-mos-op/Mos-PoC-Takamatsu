package jp.co.isid.mos.bird.entry.projectplanoffer.entity;

import java.sql.Timestamp;

public class UIOfferEntryState {
    
    public static final String TABLE = "BT20ENON";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String onerCd_COLUMN = "ONER_CD";
    
    public static final String entryFlg_COLUMN = "ENTRY_FLG";
    
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
     * 企業コード
     */
    private String companyCd;
    
    /**
     * オーナーコード
     */
    private String onerCd;
    
    /**
     * タブ番号
     */
    private String entryFlg;
    
    /**
     * 参加者の店舗名
     */
    private String firstUser;
    
    /**
     * 漢字-氏
     */
    private String firstPgm;
    
    /**
     * 漢字-名
     */
    private Timestamp firstTmsp;
    
    /**
     * カナ-氏
     */
    private String lastUser;
    
    /**
     * カナ-名
     */
    private String lastPgm;
    
    /**
     * 性別
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
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * オーナーコードを取得します。
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナーコードを設定します。
     * @param onerCd オーナーコード
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    
    /**
     * タブ番号を取得します。
     * @return タブ番号
     */
    public String getEntryFlg() {
        return entryFlg;
    }
    /**
     * タブ番号を設定します。
     * @param entryFlg タブ番号
     */
    public void setEntryFlg(String entryFlg) {
        this.entryFlg = entryFlg;
    }
    
    /**
     * 参加者の店舗名を取得します。
     * @return 参加者の店舗名
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 参加者の店舗名を設定します。
     * @param firstUser 参加者の店舗名
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 漢字-氏を取得します。
     * @return 漢字-氏
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 漢字-氏を設定します。
     * @param firstPgm 漢字-氏
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 漢字-名を取得します。
     * @return 漢字-名
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 漢字-名を設定します。
     * @param firstTmsp 漢字-名
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * カナ-氏を取得します。
     * @return カナ-氏
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * カナ-氏を設定します。
     * @param lastUser カナ-氏
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * カナ-名を取得します。
     * @return カナ-名
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * カナ-名を設定します。
     * @param lastPgm カナ-名
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 性別を取得します。
     * @return 性別
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 性別を設定します。
     * @param lastTmsp 性別
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
