package jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity;

public class UIKenshuInfo {
    
    public static final String TABLE = "BR17ENTL";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String targetCd_COLUMN = "TARGET_CD";
    
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
     * ターゲットコード
     */
    private String targetCd;
    
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
     * ターゲットコードを取得します。
     * @return ターゲットコード
     */
    public String getTargetCd() {
        return targetCd;
    }
    /**
     * ターゲットコードを設定します。
     * @param targetCd ターゲットコード
     */
    public void setTargetCd(String targetCd) {
        this.targetCd = targetCd;
    }
    
}
