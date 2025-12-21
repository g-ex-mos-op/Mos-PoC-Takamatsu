package jp.co.isid.mos.bird.analysis.menubeturef.entity;

/**
 * 前年営業日情報
 * 
 * 作成日:2008/09/17
 * @author xkinu
 *
 */
public class UILastYearEigyoDt {
    
    public static final String TABLE = "BM45ZDAY";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String dogetuDt_COLUMN = "DOGETU_DT";
    
    public static final String dogetuYear_COLUMN = "DOGETU_YEAR";
    
    public static final String dogetuMonth_COLUMN = "DOGETU_MONTH";
    
    public static final String doyoDt_COLUMN = "DOYO_DT";
    
    public static final String doyoYear_COLUMN = "DOYO_YEAR";
    
    public static final String doyoMonth_COLUMN = "DOYO_MONTH";
    
    public static final String dojituDt_COLUMN = "DOJITU_DT";
    
    public static final String dojituYear_COLUMN = "DOJITU_YEAR";
    
    public static final String dojituMonth_COLUMN = "DOJITU_MONTH";
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 前年同月営業日
     */
    private String dogetuDt;
    
    /**
     * 前年同月営業年
     */
    private String dogetuYear;
    
    /**
     * 前年同月営業月
     */
    private String dogetuMonth;
    
    /**
     * 前年同曜日営業日
     */
    private String doyoDt;
    
    /**
     * 前年同曜日営業年
     */
    private String doyoYear;
    
    /**
     * 前年同曜日営業月
     */
    private String doyoMonth;
    
    /**
     * 前年同日営業日
     */
    private String dojituDt;
    
    /**
     * 前年同日営業年
     */
    private String dojituYear;
    
    /**
     * 前年同日営業月
     */
    private String dojituMonth;
    
    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * 前年同月営業日を取得します。
     * @return 前年同月営業日
     */
    public String getDogetuDt() {
        return dogetuDt;
    }
    /**
     * 前年同月営業日を設定します。
     * @param dogetuDt 前年同月営業日
     */
    public void setDogetuDt(String dogetuDt) {
        this.dogetuDt = dogetuDt;
    }
    
    /**
     * 前年同月営業年を取得します。
     * @return 前年同月営業年
     */
    public String getDogetuYear() {
        return dogetuYear;
    }
    /**
     * 前年同月営業年を設定します。
     * @param dogetuYear 前年同月営業年
     */
    public void setDogetuYear(String dogetuYear) {
        this.dogetuYear = dogetuYear;
    }
    
    /**
     * 前年同月営業月を取得します。
     * @return 前年同月営業月
     */
    public String getDogetuMonth() {
        return dogetuMonth;
    }
    /**
     * 前年同月営業月を設定します。
     * @param dogetuMonth 前年同月営業月
     */
    public void setDogetuMonth(String dogetuMonth) {
        this.dogetuMonth = dogetuMonth;
    }
    
    /**
     * 前年同曜日営業日を取得します。
     * @return 前年同曜日営業日
     */
    public String getDoyoDt() {
        return doyoDt;
    }
    /**
     * 前年同曜日営業日を設定します。
     * @param doyoDt 前年同曜日営業日
     */
    public void setDoyoDt(String doyoDt) {
        this.doyoDt = doyoDt;
    }
    
    /**
     * 前年同曜日営業年を取得します。
     * @return 前年同曜日営業年
     */
    public String getDoyoYear() {
        return doyoYear;
    }
    /**
     * 前年同曜日営業年を設定します。
     * @param doyoYear 前年同曜日営業年
     */
    public void setDoyoYear(String doyoYear) {
        this.doyoYear = doyoYear;
    }
    
    /**
     * 前年同曜日営業月を取得します。
     * @return 前年同曜日営業月
     */
    public String getDoyoMonth() {
        return doyoMonth;
    }
    /**
     * 前年同曜日営業月を設定します。
     * @param doyoMonth 前年同曜日営業月
     */
    public void setDoyoMonth(String doyoMonth) {
        this.doyoMonth = doyoMonth;
    }
    
    /**
     * 前年同日営業日を取得します。
     * @return 前年同日営業日
     */
    public String getDojituDt() {
        return dojituDt;
    }
    /**
     * 前年同日営業日を設定します。
     * @param dojituDt 前年同日営業日
     */
    public void setDojituDt(String dojituDt) {
        this.dojituDt = dojituDt;
    }
    
    /**
     * 前年同日営業年を取得します。
     * @return 前年同日営業年
     */
    public String getDojituYear() {
        return dojituYear;
    }
    /**
     * 前年同日営業年を設定します。
     * @param dojituYear 前年同日営業年
     */
    public void setDojituYear(String dojituYear) {
        this.dojituYear = dojituYear;
    }
    
    /**
     * 前年同日営業月を取得します。
     * @return 前年同日営業月
     */
    public String getDojituMonth() {
        return dojituMonth;
    }
    /**
     * 前年同日営業月を設定します。
     * @param dojituMonth 前年同日営業月
     */
    public void setDojituMonth(String dojituMonth) {
        this.dojituMonth = dojituMonth;
    }
    
}
