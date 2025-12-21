package jp.co.isid.mos.bird.framework.entity;

public class CtlDate {
    
    public static final String TABLE = "BR33CDAY";
    
    public static final String dayKbn_COLUMN = "DAY_KBN";
    
    public static final String cntDate_COLUMN = "CNT_DATE";
    
    /**
     * 日付区分
     */
    private String dayKbn;
    
    /**
     * コントロール日付
     */
    private String cntDate;
    
    /**
     * @return cntDate を戻します。
     */
    public String getCntDate() {
        return cntDate;
    }
    /**
     * @param cntDate cntDate を設定。
     */
    public void setCntDate(String cntDate) {
        this.cntDate = cntDate;
    }
    /**
     * @return dayKbn を戻します。
     */
    public String getDayKbn() {
        return dayKbn;
    }
    /**
     * @param dayKbn dayKbn を設定。
     */
    public void setDayKbn(String dayKbn) {
        this.dayKbn = dayKbn;
    }
}
