package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity;

import java.math.BigDecimal;

public class UISaleStateTotalInfo {
    
    /**
     * 表示日付
     */    
    private String menuCd;
    
    /**
     * メニューコード
     */
    private String menuNm;
    
    /**
     * 予約数合計
     */
    private String evyDate ;
    
    /**
     * 予約数合計
     */
    private String dayKnj ;
    
    /**
     * 販売数合計
     */
    private BigDecimal kazuKeiSum;
    
    /**
     * 販売数合計
     */
    private BigDecimal reserveSum;
	
    /**
     * 表示区分
     */
    private String viewDt;
    
    private boolean futureKbn = false;

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
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getMenuNm() {
        return menuNm;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getEvyDate() {
        return evyDate;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setEvyDate(String evyDate) {
        this.evyDate = evyDate;
    }
    
    /**
     * メニューコードを取得します。
     * @return メニューコード
     */
    public String getDayKnj() {
        return dayKnj;
    }
    /**
     * メニューコードを設定します。
     * @param menuCd メニューコード
     */
    public void setDayKnj(String dayKnj) {
        this.dayKnj = dayKnj;
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
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getViewDt() {
        return viewDt;
    }
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setViewDt(String viewDt) {
        this.viewDt = viewDt;
    }
    
	/**
     * 販売数合計の表示区分
     * @return 販売数合計
     */
    public boolean getFutureKbn() {
        return futureKbn;
    }
    /**
     *  販売数合計の表示区分
     * @param futureKbn 販売数合計の表示区分
     */
    public void setFutureKbn(boolean futureKbn) {
        this.futureKbn = futureKbn;
    }
}
