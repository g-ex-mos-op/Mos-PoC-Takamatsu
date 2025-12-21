package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity;

import java.math.BigDecimal;

public class UIReserveSumInfo {
    
    public static final String TABLE = "BT70CRSV";
    
    public static final String menuGroup_COLUMN = "MENU_GROUP";
    
    public static final String sumGroup_COLUMN = "SUM_GROUP";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String reserveTime_COLUMN = "RESERVE_TIME";
    
    public static final String reserveTotal_COLUMN = "RESERVE_TOTAL";
    
    /**
     * メニューグループ
     */
    private String menuGroup;
    
    /**
     * 集計グループ
     */
    private String sumGroup;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * お渡し予約時間(時)(分)
     */
    private String reserveTime;
    
    /**
     * 合計
     */
    private BigDecimal reserveTotal;
    
    /**
     * メニューグループを取得します。
     * @return メニューグループ
     */
    public String getMenuGroup() {
        return menuGroup;
    }
    /**
     * メニューグループを設定します。
     * @param menuGroup メニューグループ
     */
    public void setMenuGroup(String menuGroup) {
        this.menuGroup = menuGroup;
    }
    
    /**
     * 集計グループを取得します。
     * @return 集計グループ
     */
    public String getSumGroup() {
        return sumGroup;
    }
    /**
     * 集計グループを設定します。
     * @param sumGroup 集計グループ
     */
    public void setSumGroup(String sumGroup) {
        this.sumGroup = sumGroup;
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
     * お渡し予約時間(時)(分)を取得します。
     * @return お渡し予約時間(時)(分)
     */
    public String getReserveTime() {
        return reserveTime;
    }
    /**
     * お渡し予約時間(時)(分)を設定します。
     * @param reserveTime お渡し予約時間(時)(分)
     */
    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }
    
    /**
     * 合計を取得します。
     * @return 合計
     */
    public BigDecimal getReserveTotal() {
        return reserveTotal;
    }
    /**
     * 合計を設定します。
     * @param reserveTotoal 合計
     */
    public void setReserveTotal(BigDecimal reserveTotal) {
        this.reserveTotal = reserveTotal;
    }
    
}
