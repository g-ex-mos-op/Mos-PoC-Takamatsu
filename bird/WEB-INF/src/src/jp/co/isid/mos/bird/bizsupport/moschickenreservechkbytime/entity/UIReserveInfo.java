package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity;

import java.math.BigDecimal;

public class UIReserveInfo {
    
    public static final String TABLE = "BM40CMGP";
    
    public static final String reserveDt_COLUMN = "RESERVE_DT";
    
    public static final String menuGroup_COLUMN = "MENU_GROUP";
    
    public static final String menuGroupNm_COLUMN = "MENU_GROUP_NM";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    public static final String sumGroup_COLUMN = "SUM_GROUP";
    
    public static final String reserveHh_COLUMN = "RESERVE_HH";
    
    public static final String reserveMm_COLUMN = "RESERVE_MM";
    
    public static final String reserveAmt_COLUMN = "RESERVE_AMT";
    
    /**
     * お渡し日
     */
    private String reserveDt;
    
    /**
     * メニューグループ
     */
    private String menuGroup;
    
    /**
     * メニューグループ名称
     */
    private String menuGroupNm;
    
    /**
     * メニュー名
     */
    private String menuCd;
    
    /**
     * メニューグループ名称
     */
    private String menuNameKj;
    
    /**
     * 集計グループ
     */
    private String sumGroup;
    
    /**
     * お渡し予約時間(時)
     */
    private String reserveHh;
    
    /**
     * お渡し予約時間(分)
     */
    private String reserveMm;
    
    /**
     * 予約数
     */
    private BigDecimal reserveAmt;
    
    /**
     * お渡し日を取得します。
     * @return お渡し日
     */
    public String getReserveDt() {
        return reserveDt;
    }
    /**
     * お渡し日を設定します。
     * @param reserveDt お渡し日
     */
    public void setReserveDt(String reserveDt) {
        this.reserveDt = reserveDt;
    }
    
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
     * メニューグループ名称を取得します。
     * @return メニューグループ名称
     */
    public String getMenuGroupNm() {
        return menuGroupNm;
    }
    /**
     * メニューグループ名称を設定します。
     * @param menuGroupNm メニューグループ名称
     */
    public void setMenuGroupNm(String menuGroupNm) {
        this.menuGroupNm = menuGroupNm;
    }
    
    /**
     * メニュー名を取得します。
     * @return メニュー名
     */
    public String getMenuCd() {
        return menuCd;
    }
    /**
     * メニュー名を設定します。
     * @param menuCd メニュー名
     */
    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }
    
    /**
     * メニューグループ名称を取得します。
     * @return メニューグループ名称
     */
    public String getMenuNameKj() {
        return menuNameKj;
    }
    /**
     * メニューグループ名称を設定します。
     * @param menuNameKj メニューグループ名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
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
     * お渡し予約時間(時)を取得します。
     * @return お渡し予約時間(時)
     */
    public String getReserveHh() {
        return reserveHh;
    }
    /**
     * お渡し予約時間(時)を設定します。
     * @param reserveHh お渡し予約時間(時)
     */
    public void setReserveHh(String reserveHh) {
        this.reserveHh = reserveHh;
    }
    
    /**
     * お渡し予約時間(分)を取得します。
     * @return お渡し予約時間(分)
     */
    public String getReserveMm() {
        return reserveMm;
    }
    /**
     * お渡し予約時間(分)を設定します。
     * @param reserveMm お渡し予約時間(分)
     */
    public void setReserveMm(String reserveMm) {
        this.reserveMm = reserveMm;
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
    
}
