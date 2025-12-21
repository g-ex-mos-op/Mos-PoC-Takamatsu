package jp.co.isid.mos.bird.storemanage.mlresultregist.entity;

public class EntryDate {
    
    public static final String TABLE = "BR18ENTD";
    
    public static final String entryCd_COLUMN = "ENTRY_CD";
    
    public static final String entryYear_COLUMN = "ENTRY_YEAR";
    
    public static final String entryKai_COLUMN = "ENTRY_KAI";
    
    public static final String fromDt_COLUMN = "FROM_DT";
    
    public static final String toDt_COLUMN = "TO_DT";
    
    public static final String entryTitle_COLUMN = "ENTRY_TITLE";
    
    public static final String updateNum_COLUMN = "UPDATE_NUM";
    
    public static final String totalEntryNum_COLUMN = "TOTAL_ENTRY_NUM";
    
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
     * 現在の更新者数
     */
    private String updateNum;
    
    /**
     * 全申込者数
     */
    private String totalEntryNum;
    
    /**
     * 登録対象科目
     */
    private String targetKamoku;
    
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
     * 開始日を取得します。
     * @return 開始日
     */
    public String getFromDt() {
        if (!isNull(fromDt)) {
            if (fromDt.trim().length() == 6) {
                fromDt = fromDt.trim() + "01";
            }
        }
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
        if (!isNull(toDt)) {
            if (toDt.trim().length() == 6) {
                toDt = toDt.trim() + "01";
            }
        }
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
    
    
    /**
     * 回の前ゼロ除去
     */
    public String getEntryKaiRemoveZero() {
        String kai = getEntryKai();
        while (kai.length() > 0) {
            if ("0".equals(kai.substring(0, 1))) {
                kai = kai.substring(1);
            }
            else {
                break;
            }
        }
        return kai;
    }
    public void setEntryKaiRemoveZero(String value) {
    }
    
    private boolean isNull(String value) {
        return (value == null || value.trim().equals("")) ? true : false;
    }

    /**
     * 登録対象科目
     */
    public String getTargetKamoku() {
        return isNull(targetKamoku) ? "0" : targetKamoku;
    }
    public void setTargetKamoku(String targetKamoku) {
        this.targetKamoku = targetKamoku;
    }
    public String getTotalEntryNum() {
        return totalEntryNum;
    }
    public void setTotalEntryNum(String totalEntryNum) {
        this.totalEntryNum = totalEntryNum;
    }
    public String getUpdateNum() {
        return updateNum;
    }
    public void setUpdateNum(String updateNum) {
        this.updateNum = updateNum;
    }
}
