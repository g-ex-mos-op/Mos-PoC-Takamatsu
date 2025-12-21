package jp.co.isid.mos.bird.common.entity;

public class CtlHolidayInfo {
    
    public static final String TABLE = "TR13HLDY";
    
    public static final String shukuDate_COLUMN = "SHUKU_DATE";
    
    public static final String shukuName_COLUMN = "SHUKU_NAME";
    
    public static final String shukuKbn_COLUMN = "SHUKU_KBN";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";

//    public static final String getHoliday_QUERY = "SHUKU_DATE = ?";
    /**
     * 祝祭日日付
     */
    private String shukuDate;
    
    /**
     * 祝祭日名称
     */
    private String shukuName;
    
    /**
     * 祝日区分
     */
    private String shukuKbn;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時タイムスタンプ
     */
    private String lastTmsp;
    
    /**
     * 祝祭日日付を取得します。
     * @return 祝祭日日付
     */
    public String getShukuDate() {
        return shukuDate;
    }
    /**
     * 祝祭日日付を設定します。
     * @param shukuDate 祝祭日日付
     */
    public void setShukuDate(String shukuDate) {
        this.shukuDate = shukuDate;
    }
    
    /**
     * 祝祭日名称を取得します。
     * @return 祝祭日名称
     */
    public String getShukuName() {
        return shukuName;
    }
    /**
     * 祝祭日名称を設定します。
     * @param shukuName 祝祭日名称
     */
    public void setShukuName(String shukuName) {
        this.shukuName = shukuName;
    }
    
    /**
     * 祝日区分を取得します。
     * @return 祝日区分
     */
    public String getShukuKbn() {
        return shukuKbn;
    }
    /**
     * 祝日区分を設定します。
     * @param shukuKbn 祝日区分
     */
    public void setShukuKbn(String shukuKbn) {
        this.shukuKbn = shukuKbn;
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
     * 修正時タイムスタンプを取得します。
     * @return 修正時タイムスタンプ
     */
    public String getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時タイムスタンプを設定します。
     * @param lastTmsp 修正時タイムスタンプ
     */
    public void setLastTmsp(String lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
