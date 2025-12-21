package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity;

import java.util.List;

public class UIReserveTotalInfo {
    
   /**
     * 店舗コード
     */    
    private String tenpoCd;
    
    /**
     * 店舗名
     */
    private String tenpoNm;
    
    /**
     *　予約日
     */
    private String reserveDt ;
    
    /**
     * 時間帯　開始
     */
    private String timeStart;
    
    /**
     * 時間帯　終了
     */
    private String timeEnd;
    
    /**
     * 時間単位
     */
    private String timeUnit;
    
    /**
     * 商品グループコード
     */
    private String shohinGroupCd;
	
    /**
     * 商品グループ名
     */
    private String shohinGroupNm;
    
    /**
     * 商品コード
     */
    private String menuCd;

    /**
     * 商品名
     */
    private String menuNm;
    
    /**
     * 各時間
     */
    private List timeZn;
    
    /**
     * 予約数
     */
    private List reserveAmt;
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getTenpoCd() {
        return tenpoCd;
    }
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setTenpoCd(String tenpoCd) {
        this.tenpoCd = tenpoCd;
    }

	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getTenpoNm() {
        return tenpoNm;
    }
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setTenpoNm(String tenpoNm) {
        this.tenpoNm = tenpoNm;
    }
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getReserveDt() {
        return reserveDt;
    }
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setReserveDt(String reserveDt) {
        this.reserveDt = reserveDt;
    }
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getTimeStart() {
        return timeStart;
    }
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getTimeEnd() {
        return timeEnd;
    }
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getTimeUnit() {
        return timeUnit;
    }

    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
    
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setShohinGroupCd(String shohinGroupCd) {
        this.shohinGroupCd = shohinGroupCd;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getShohinGroupCd() {
        return shohinGroupCd;
    }

    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setShohinGroupNm(String shohinGroupNm) {
        this.shohinGroupNm = shohinGroupNm;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getShohinGroupNm() {
        return shohinGroupNm;
    }
    
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getMenuCd() {
        return menuCd;
    }
    
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public String getMenuNm() {
        return menuNm;
    }

    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setTimeZn(List timeZn) {
        this.timeZn = timeZn;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public List getTimeZn() {
        return timeZn;
    }
        
    /**
     * 表示区分を設定します。
     * @param viewKbn 表示区分
     */
    public void setReserveAmt(List reserveAmt) {
        this.reserveAmt = reserveAmt;
    } 
    
	/**
     * 表示区分を取得します。
     * @return 販売数合計
     */
    public List getReserveAmt() {
        return reserveAmt;
    }
}
