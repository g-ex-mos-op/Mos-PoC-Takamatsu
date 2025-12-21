package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity;

public class UIReserveTimeInfo {
    
    public static final String TABLE = "";
    
    public static final String reserveStateTime_COLUMN = "RESERVE_STATE_TIME";
    
    public static final String reserveEndTime_COLUMN = "RESERVE_END_TIME";
    
    /**
     * 最初販売時間
     */
    private String reserveStateTime;
    
    /**
     * 最後販売時間
     */
    private String reserveEndTime;
    
    /**
     * 最初販売時間を取得します。
     * @return 最初販売時間
     */
    public String getReserveStateTime() {
        return reserveStateTime;
    }
    /**
     * 最初販売時間を設定します。
     * @param saleStateTime 最初販売時間
     */
    public void setReserveStateTime(String reserveStateTime) {
        this.reserveStateTime = reserveStateTime;
    }
    
    /**
     * 最後販売時間を取得します。
     * @return 最後販売時間
     */
    public String getReserveEndTime() {
        return reserveEndTime;
    }
    /**
     * 最後販売時間を設定します。
     * @param saleEndTime 最後販売時間
     */
    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime;
    }
    
}
