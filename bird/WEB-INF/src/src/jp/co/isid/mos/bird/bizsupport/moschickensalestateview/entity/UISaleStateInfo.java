package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity;

import java.math.BigDecimal;

public class UISaleStateInfo {
    
    public static final String TABLE = "";
    
    public static final String dt_COLUMN = "DT";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNm_COLUMN = "MENU_NM";
    
    public static final String kazuKei_COLUMN = "KAZU_KEI";
    
    public static final String reserveAmt_COLUMN = "RESERVE_AMT";
    
    public static final String sumGroup_COLUMN = "SUM_GROUP";
    
    /**
     * 表示日付
     */
    private String dt;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名
     */
    private String menuNm;
    
    /**
     * 販売数
     */
    private BigDecimal kazuKei;
    
    /**
     * 予約数
     */
    private BigDecimal reserveAmt;
    
    /**
     * 集計グループ
     */
    private String sumGroup;
    
    /**
     * 表示日付を取得します。
     * @return 表示日付
     */
    public String getDt() {
        return dt;
    }
    /**
     * 表示日付を設定します。
     * @param saleDt 表示日付
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
     * メニュー名を取得します。
     * @return メニュー名
     */
    public String getMenuNm() {
        return menuNm;
    }
    /**
     * メニュー名を設定します。
     * @param menuNm メニュー名
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }
    
    /**
     * 販売数を取得します。
     * @return 販売数
     */
    public BigDecimal getKazuKei() {
        return kazuKei;
    }
    /**
     * 販売数を設定します。
     * @param kazuKei 販売数
     */
    public void setKazuKei(BigDecimal kazuKei) {
        this.kazuKei = kazuKei;
    }
    
    /**
     * 予約数を取得します。
     * @return 予約数
     */
    public BigDecimal getReserveAmt() {
        return reserveAmt;
    }
    /**
     * 予約数を設定します。
     * @param reserveAmt 予約数
     */
    public void setReserveAmt(BigDecimal reserveAmt) {
        this.reserveAmt = reserveAmt;
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
    
}
