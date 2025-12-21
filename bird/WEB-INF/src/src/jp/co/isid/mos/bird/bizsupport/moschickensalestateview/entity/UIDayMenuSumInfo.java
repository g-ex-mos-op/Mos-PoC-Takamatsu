package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity;

import java.math.BigDecimal;

public class UIDayMenuSumInfo {
    
    public static final String TABLE = "";
    
    public static final String dt_COLUMN = "DT";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String sumGroup_COLUMN = "SUM_GROUP";
    
    public static final String baseKazuKeiSum_COLUMN = "BASE_KAZU_KEI_SUM";
    
    public static final String baseReserveSum_COLUMN = "BASE_RESERVE_SUM";
    
    public static final String reserveSum_COLUMN = "RESERVE_SUM";
    
    public static final String kazuKeiSum_COLUMN = "KAZU_KEI_SUM";
    
    /**
     * 表示日付
     */
    private String dt;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    
    /**
     * メニューコード
     */
    private String sumGroup;
    
    /**
     * 予約数合計
     */
    private BigDecimal reserveSum;
    
    /**
     * 販売数合計
     */
    private BigDecimal kazuKeiSum;
    
    /**
     * 予約数合計
     */
    private BigDecimal baseReserveSum;
    
    /**
     * 販売数合計
     */
    private BigDecimal baseKazuKeiSum;
    
    
    /**
     * 表示日付を取得します。
     * @return 表示日付
     */
    public String getDt() {
        return dt;
    }
    /**
     * 表示日付を設定します。
     * @param dt 表示日付
     */
    public void setDt(String dt) {
        this.dt = dt;
    }
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    
    /**
     * 集計グループコードを取得します。
     * @return 集計グループコード
     */
    public String getSumGroup() {
        return sumGroup;
    }
    /**
     * 集計グループコードを設定します。
     * @param sumGroup 集計グループコード
     */
    public void setSumGroup(String sumGroup) {
        this.sumGroup = sumGroup;
    }
    
    /**
     * 予約数合計を取得します。
     * @return 予約数合計
     */
    public BigDecimal getBaseReserveSum() {
        return baseReserveSum;
    }
    /**
     * 予約数合計を設定します。
     * @param reserveSum 予約数合計
     */
    public void setBaseReserveSum(BigDecimal baseReserveSum) {
        this.baseReserveSum = baseReserveSum;
    }
    
    /**
     * 販売数合計を取得します。
     * @return 販売数合計
     */
    public BigDecimal getBaseKazuKeiSum() {
        return baseKazuKeiSum;
    }
    /**
     * 販売数合計を設定します。
     * @param kazuKeiSum 販売数合計
     */
    public void setBaseKazuKeiSum(BigDecimal baseKazuKeiSum) {
        this.baseKazuKeiSum = baseKazuKeiSum;
    }
    
    /**
     * 予約数合計を取得します。
     * @return 予約数合計
     */
    public BigDecimal getReserveSum() {
        return reserveSum;
    }
    /**
     * 予約数合計を設定します。
     * @param reserveSum 予約数合計
     */
    public void setReserveSum(BigDecimal reserveSum) {
        this.reserveSum = reserveSum;
    }
    
    /**
     * 販売数合計を取得します。
     * @return 販売数合計
     */
    public BigDecimal getKazuKeiSum() {
        return kazuKeiSum;
    }
    /**
     * 販売数合計を設定します。
     * @param kazuKeiSum 販売数合計
     */
    public void setKazuKeiSum(BigDecimal kazuKeiSum) {
        this.kazuKeiSum = kazuKeiSum;
    }
    
}
