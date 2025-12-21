package jp.co.isid.mos.bird.bizreport.takuhainiporef.entity;

/**
 * 宅配対象店舗数エンティティクラス
 * 
 * @author xjung
 */
public class TotalMiseCount {
    /** テーブル名称 */
    public static final String TABLE = "BM01TENM";
    /** カラム名称：宅配対象店舗数 */
    public static final String totalTenpoCount_COLUMN = "TOTAL_TENPO_COUNT";
    
    /**
     * 宅配対象店舗数
     */
    private int totalTenpoCount;
    
    /**
     * 宅配対象店舗数を取得します。
     * @return 宅配対象店舗数
     */
    public int getTotalTenpoCount() {
        return totalTenpoCount;
    }

    /**
     * 宅配対象店舗数を設定します。
     * @param totalTenpoCount 宅配対象店舗数
     */
    public void setTotalTenpoCount(int totalTenpoCount) {
        this.totalTenpoCount = totalTenpoCount;
    }    
}