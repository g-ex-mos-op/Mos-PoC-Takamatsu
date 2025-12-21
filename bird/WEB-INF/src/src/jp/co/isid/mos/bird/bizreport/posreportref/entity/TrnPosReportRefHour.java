package jp.co.isid.mos.bird.bizreport.posreportref.entity;

import java.math.BigDecimal;


public class TrnPosReportRefHour {
    
    public static final String TABLE = "BT77MRAL";
    
    public static final String compnayCd_COLUMN = "COMPNAY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String eigyoDt_COLUMN = "EIGYO_DT";
    
    public static final String dataNum_COLUMN = "DATA_NUM";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String shuSysDt_COLUMN = "SHU_SYS_DT";
    
    public static final String shuSysTime_COLUMN = "SHU_SYS_TIME";
    
    public static final String uriKen0000_COLUMN = "URI_KEN_0000";
    
    public static final String uriKin0000_COLUMN = "URI_KIN_0000";
    
    public static final String uriKen0100_COLUMN = "URI_KEN_0100";
    
    public static final String uriKin0100_COLUMN = "URI_KIN_0100";
    
    public static final String uriKen0200_COLUMN = "URI_KEN_0200";
    
    public static final String uriKin0200_COLUMN = "URI_KIN_0200";
    
    public static final String uriKen0300_COLUMN = "URI_KEN_0300";
    
    public static final String uriKin0300_COLUMN = "URI_KIN_0300";
    
    public static final String uriKen0400_COLUMN = "URI_KEN_0400";
    
    public static final String uriKin0400_COLUMN = "URI_KIN_0400";
    
    public static final String uriKen0500_COLUMN = "URI_KEN_0500";
    
    public static final String uriKin0500_COLUMN = "URI_KIN_0500";
    
    public static final String uriKen0600_COLUMN = "URI_KEN_0600";
    
    public static final String uriKin0600_COLUMN = "URI_KIN_0600";
    
    public static final String uriKen0700_COLUMN = "URI_KEN_0700";
    
    public static final String uriKin0700_COLUMN = "URI_KIN_0700";
    
    public static final String uriKen0800_COLUMN = "URI_KEN_0800";
    
    public static final String uriKin0800_COLUMN = "URI_KIN_0800";
    
    public static final String uriKen0900_COLUMN = "URI_KEN_0900";
    
    public static final String uriKin0900_COLUMN = "URI_KIN_0900";
    
    public static final String uriKen1000_COLUMN = "URI_KEN_1000";
    
    public static final String uriKin1000_COLUMN = "URI_KIN_1000";
    
    public static final String uriKen1100_COLUMN = "URI_KEN_1100";
    
    public static final String uriKin1100_COLUMN = "URI_KIN_1100";
    
    public static final String uriKen1200_COLUMN = "URI_KEN_1200";
    
    public static final String uriKin1200_COLUMN = "URI_KIN_1200";
    
    public static final String uriKen1300_COLUMN = "URI_KEN_1300";
    
    public static final String uriKin1300_COLUMN = "URI_KIN_1300";
    
    public static final String uriKen1400_COLUMN = "URI_KEN_1400";
    
    public static final String uriKin1400_COLUMN = "URI_KIN_1400";
    
    public static final String uriKen1500_COLUMN = "URI_KEN_1500";
    
    public static final String uriKin1500_COLUMN = "URI_KIN_1500";
    
    public static final String uriKen1600_COLUMN = "URI_KEN_1600";
    
    public static final String uriKin1600_COLUMN = "URI_KIN_1600";
    
    public static final String uriKen1700_COLUMN = "URI_KEN_1700";
    
    public static final String uriKin1700_COLUMN = "URI_KIN_1700";
    
    public static final String uriKen1800_COLUMN = "URI_KEN_1800";
    
    public static final String uriKin1800_COLUMN = "URI_KIN_1800";
    
    public static final String uriKen1900_COLUMN = "URI_KEN_1900";
    
    public static final String uriKin1900_COLUMN = "URI_KIN_1900";
    
    public static final String uriKen2000_COLUMN = "URI_KEN_2000";
    
    public static final String uriKin2000_COLUMN = "URI_KIN_2000";
    
    public static final String uriKen2100_COLUMN = "URI_KEN_2100";
    
    public static final String uriKin2100_COLUMN = "URI_KIN_2100";
    
    public static final String uriKen2200_COLUMN = "URI_KEN_2200";
    
    public static final String uriKin2200_COLUMN = "URI_KIN_2200";
    
    public static final String uriKen2300_COLUMN = "URI_KEN_2300";
    
    public static final String uriKin2300_COLUMN = "URI_KIN_2300";
    
    public static final String uriKenOther_COLUMN = "URI_KEN_OTHER";
    
    public static final String uriKinOther_COLUMN = "URI_KIN_OTHER";
    
    
    public static final String allKin_COLUMN = "ALL_KIN";
    
    /**
     * 企業コード
     */
    private String compnayCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 営業日
     */
    private String eigyoDt;
    
    /**
     * 回数
     */
    private String dataNum;
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * 集計日付
     */
    private String shuSysDt;
    
    /**
     * 集計時刻
     */
    private String shuSysTime;
    
    /**
     * 時間0000売上件数
     */
    private BigDecimal uriKen0000;
    
    /**
     * 時間0000売上金額
     */
    private BigDecimal uriKin0000;
    
    /**
     * 時間0100売上件数
     */
    private BigDecimal uriKen0100;
    
    /**
     * 時間0100売上金額
     */
    private BigDecimal uriKin0100;
    
    /**
     * 時間0200売上件数
     */
    private BigDecimal uriKen0200;
    
    /**
     * 時間0200売上金額
     */
    private BigDecimal uriKin0200;
    
    /**
     * 時間0300売上件数
     */
    private BigDecimal uriKen0300;
    
    /**
     * 時間0300売上金額
     */
    private BigDecimal uriKin0300;
    
    /**
     * 時間0400売上件数
     */
    private BigDecimal uriKen0400;
    
    /**
     * 時間0400売上金額
     */
    private BigDecimal uriKin0400;
    
    /**
     * 時間0500売上件数
     */
    private BigDecimal uriKen0500;
    
    /**
     * 時間0500売上金額
     */
    private BigDecimal uriKin0500;
    
    /**
     * 時間0600売上件数
     */
    private BigDecimal uriKen0600;
    
    /**
     * 時間0600売上金額
     */
    private BigDecimal uriKin0600;
    
    /**
     * 時間0700売上件数
     */
    private BigDecimal uriKen0700;
    
    /**
     * 時間0700売上金額
     */
    private BigDecimal uriKin0700;
    
    /**
     * 時間0800売上件数
     */
    private BigDecimal uriKen0800;
    
    /**
     * 時間0800売上金額
     */
    private BigDecimal uriKin0800;
    
    /**
     * 時間0900売上件数
     */
    private BigDecimal uriKen0900;
    
    /**
     * 時間0900売上金額
     */
    private BigDecimal uriKin0900;
    
    /**
     * 時間1000売上件数
     */
    private BigDecimal uriKen1000;
    
    /**
     * 時間1000売上金額
     */
    private BigDecimal uriKin1000;
    
    /**
     * 時間1100売上件数
     */
    private BigDecimal uriKen1100;
    
    /**
     * 時間1100売上金額
     */
    private BigDecimal uriKin1100;
    
    /**
     * 時間1200売上件数
     */
    private BigDecimal uriKen1200;
    
    /**
     * 時間1200売上金額
     */
    private BigDecimal uriKin1200;
    
    /**
     * 時間1300売上件数
     */
    private BigDecimal uriKen1300;
    
    /**
     * 時間1300売上金額
     */
    private BigDecimal uriKin1300;
    
    /**
     * 時間1400売上件数
     */
    private BigDecimal uriKen1400;
    
    /**
     * 時間1400売上金額
     */
    private BigDecimal uriKin1400;
    
    /**
     * 時間1500売上件数
     */
    private BigDecimal uriKen1500;
    
    /**
     * 時間1500売上金額
     */
    private BigDecimal uriKin1500;
    
    /**
     * 時間1600売上件数
     */
    private BigDecimal uriKen1600;
    
    /**
     * 時間1600売上金額
     */
    private BigDecimal uriKin1600;
    
    /**
     * 時間1700売上件数
     */
    private BigDecimal uriKen1700;
    
    /**
     * 時間1700売上金額
     */
    private BigDecimal uriKin1700;
    
    /**
     * 時間1800売上件数
     */
    private BigDecimal uriKen1800;
    
    /**
     * 時間1800売上金額
     */
    private BigDecimal uriKin1800;
    
    /**
     * 時間1900売上件数
     */
    private BigDecimal uriKen1900;
    
    /**
     * 時間1900売上金額
     */
    private BigDecimal uriKin1900;
    
    /**
     * 時間2000売上件数
     */
    private BigDecimal uriKen2000;
    
    /**
     * 時間2000売上金額
     */
    private BigDecimal uriKin2000;
    
    /**
     * 時間2100売上件数
     */
    private BigDecimal uriKen2100;
    
    /**
     * 時間2100売上金額
     */
    private BigDecimal uriKin2100;
    
    /**
     * 時間2200売上件数
     */
    private BigDecimal uriKen2200;
    
    /**
     * 時間2200売上金額
     */
    private BigDecimal uriKin2200;
    
    /**
     * 時間2300売上件数
     */
    private BigDecimal uriKen2300;
    
    /**
     * 時間2300売上金額
     */
    private BigDecimal uriKin2300;
    
    /**
     * 時間その他売上件数
     */
    private BigDecimal uriKenOther;
    
    /**
     * 時間その他売上金額
     */
    private BigDecimal uriKinOther;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0000;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0100;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0200;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0300;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0400;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0500;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0600;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0700;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0800;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi0900;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1000;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1100;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1200;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1300;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1400;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1500;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1600;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1700;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1800;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi1900;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi2000;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi2100;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi2200;
    
    /**
     * 構成比
     */
    private BigDecimal kouseiHi2300;

    /**
     * 構成比
     */
    private BigDecimal kouseiHiOther;

    /**
     * 総合計
     */
    private BigDecimal allKin;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompnayCd() {
        return compnayCd;
    }
    /**
     * 企業コードを設定します。
     * @param compnayCd 企業コード
     */
    public void setCompnayCd(String compnayCd) {
        this.compnayCd = compnayCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
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
     * 回数を取得します。
     * @return 回数
     */
    public String getDataNum() {
        return dataNum;
    }
    /**
     * 回数を設定します。
     * @param dataNum 回数
     */
    public void setDataNum(String dataNum) {
        this.dataNum = dataNum;
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
     * 集計日付を取得します。
     * @return 集計日付
     */
    public String getShuSysDt() {
        return shuSysDt;
    }
    /**
     * 集計日付を設定します。
     * @param shuSysDt 集計日付
     */
    public void setShuSysDt(String shuSysDt) {
        this.shuSysDt = shuSysDt;
    }
    
    /**
     * 集計時刻を取得します。
     * @return 集計時刻
     */
    public String getShuSysTime() {
        return shuSysTime;
    }
    /**
     * 集計時刻を設定します。
     * @param shuSysTime 集計時刻
     */
    public void setShuSysTime(String shuSysTime) {
        this.shuSysTime = shuSysTime;
    }
    
    /**
     * 時間0000売上件数を取得します。
     * @return 時間0000売上件数
     */
    public BigDecimal getUriKen0000() {
        return uriKen0000;
    }
    /**
     * 時間0000売上件数を設定します。
     * @param uriKen0000 時間0000売上件数
     */
    public void setUriKen0000(BigDecimal uriKen0000) {
        this.uriKen0000 = uriKen0000;
    }
    
    /**
     * 時間0000売上金額を取得します。
     * @return 時間0000売上金額
     */
    public BigDecimal getUriKin0000() {
        return uriKin0000;
    }
    /**
     * 時間0000売上金額を設定します。
     * @param uriKin0000 時間0000売上金額
     */
    public void setUriKin0000(BigDecimal uriKin0000) {
        this.uriKin0000 = uriKin0000;
    }
    
    /**
     * 時間0100売上件数を取得します。
     * @return 時間0100売上件数
     */
    public BigDecimal getUriKen0100() {
        return uriKen0100;
    }
    /**
     * 時間0100売上件数を設定します。
     * @param uriKen0100 時間0100売上件数
     */
    public void setUriKen0100(BigDecimal uriKen0100) {
        this.uriKen0100 = uriKen0100;
    }
    
    /**
     * 時間0100売上金額を取得します。
     * @return 時間0100売上金額
     */
    public BigDecimal getUriKin0100() {
        return uriKin0100;
    }
    /**
     * 時間0100売上金額を設定します。
     * @param uriKin0100 時間0100売上金額
     */
    public void setUriKin0100(BigDecimal uriKin0100) {
        this.uriKin0100 = uriKin0100;
    }
    
    /**
     * 時間0200売上件数を取得します。
     * @return 時間0200売上件数
     */
    public BigDecimal getUriKen0200() {
        return uriKen0200;
    }
    /**
     * 時間0200売上件数を設定します。
     * @param uriKen0200 時間0200売上件数
     */
    public void setUriKen0200(BigDecimal uriKen0200) {
        this.uriKen0200 = uriKen0200;
    }
    
    /**
     * 時間0200売上金額を取得します。
     * @return 時間0200売上金額
     */
    public BigDecimal getUriKin0200() {
        return uriKin0200;
    }
    /**
     * 時間0200売上金額を設定します。
     * @param uriKin0200 時間0200売上金額
     */
    public void setUriKin0200(BigDecimal uriKin0200) {
        this.uriKin0200 = uriKin0200;
    }
    
    /**
     * 時間0300売上件数を取得します。
     * @return 時間0300売上件数
     */
    public BigDecimal getUriKen0300() {
        return uriKen0300;
    }
    /**
     * 時間0300売上件数を設定します。
     * @param uriKen0300 時間0300売上件数
     */
    public void setUriKen0300(BigDecimal uriKen0300) {
        this.uriKen0300 = uriKen0300;
    }
    
    /**
     * 時間0300売上金額を取得します。
     * @return 時間0300売上金額
     */
    public BigDecimal getUriKin0300() {
        return uriKin0300;
    }
    /**
     * 時間0300売上金額を設定します。
     * @param uriKin0300 時間0300売上金額
     */
    public void setUriKin0300(BigDecimal uriKin0300) {
        this.uriKin0300 = uriKin0300;
    }
    
    /**
     * 時間0400売上件数を取得します。
     * @return 時間0400売上件数
     */
    public BigDecimal getUriKen0400() {
        return uriKen0400;
    }
    /**
     * 時間0400売上件数を設定します。
     * @param uriKen0400 時間0400売上件数
     */
    public void setUriKen0400(BigDecimal uriKen0400) {
        this.uriKen0400 = uriKen0400;
    }
    
    /**
     * 時間0400売上金額を取得します。
     * @return 時間0400売上金額
     */
    public BigDecimal getUriKin0400() {
        return uriKin0400;
    }
    /**
     * 時間0400売上金額を設定します。
     * @param uriKin0400 時間0400売上金額
     */
    public void setUriKin0400(BigDecimal uriKin0400) {
        this.uriKin0400 = uriKin0400;
    }
    
    /**
     * 時間0500売上件数を取得します。
     * @return 時間0500売上件数
     */
    public BigDecimal getUriKen0500() {
        return uriKen0500;
    }
    /**
     * 時間0500売上件数を設定します。
     * @param uriKen0500 時間0500売上件数
     */
    public void setUriKen0500(BigDecimal uriKen0500) {
        this.uriKen0500 = uriKen0500;
    }
    
    /**
     * 時間0500売上金額を取得します。
     * @return 時間0500売上金額
     */
    public BigDecimal getUriKin0500() {
        return uriKin0500;
    }
    /**
     * 時間0500売上金額を設定します。
     * @param uriKin0500 時間0500売上金額
     */
    public void setUriKin0500(BigDecimal uriKin0500) {
        this.uriKin0500 = uriKin0500;
    }
    
    /**
     * 時間0600売上件数を取得します。
     * @return 時間0600売上件数
     */
    public BigDecimal getUriKen0600() {
        return uriKen0600;
    }
    /**
     * 時間0600売上件数を設定します。
     * @param uriKen0600 時間0600売上件数
     */
    public void setUriKen0600(BigDecimal uriKen0600) {
        this.uriKen0600 = uriKen0600;
    }
    
    /**
     * 時間0600売上金額を取得します。
     * @return 時間0600売上金額
     */
    public BigDecimal getUriKin0600() {
        return uriKin0600;
    }
    /**
     * 時間0600売上金額を設定します。
     * @param uriKin0600 時間0600売上金額
     */
    public void setUriKin0600(BigDecimal uriKin0600) {
        this.uriKin0600 = uriKin0600;
    }
    
    /**
     * 時間0700売上件数を取得します。
     * @return 時間0700売上件数
     */
    public BigDecimal getUriKen0700() {
        return uriKen0700;
    }
    /**
     * 時間0700売上件数を設定します。
     * @param uriKen0700 時間0700売上件数
     */
    public void setUriKen0700(BigDecimal uriKen0700) {
        this.uriKen0700 = uriKen0700;
    }
    
    /**
     * 時間0700売上金額を取得します。
     * @return 時間0700売上金額
     */
    public BigDecimal getUriKin0700() {
        return uriKin0700;
    }
    /**
     * 時間0700売上金額を設定します。
     * @param uriKin0700 時間0700売上金額
     */
    public void setUriKin0700(BigDecimal uriKin0700) {
        this.uriKin0700 = uriKin0700;
    }
    
    /**
     * 時間0800売上件数を取得します。
     * @return 時間0800売上件数
     */
    public BigDecimal getUriKen0800() {
        return uriKen0800;
    }
    /**
     * 時間0800売上件数を設定します。
     * @param uriKen0800 時間0800売上件数
     */
    public void setUriKen0800(BigDecimal uriKen0800) {
        this.uriKen0800 = uriKen0800;
    }
    
    /**
     * 時間0800売上金額を取得します。
     * @return 時間0800売上金額
     */
    public BigDecimal getUriKin0800() {
        return uriKin0800;
    }
    /**
     * 時間0800売上金額を設定します。
     * @param uriKin0800 時間0800売上金額
     */
    public void setUriKin0800(BigDecimal uriKin0800) {
        this.uriKin0800 = uriKin0800;
    }
    
    /**
     * 時間0900売上件数を取得します。
     * @return 時間0900売上件数
     */
    public BigDecimal getUriKen0900() {
        return uriKen0900;
    }
    /**
     * 時間0900売上件数を設定します。
     * @param uriKen0900 時間0900売上件数
     */
    public void setUriKen0900(BigDecimal uriKen0900) {
        this.uriKen0900 = uriKen0900;
    }
    
    /**
     * 時間0900売上金額を取得します。
     * @return 時間0900売上金額
     */
    public BigDecimal getUriKin0900() {
        return uriKin0900;
    }
    /**
     * 時間0900売上金額を設定します。
     * @param uriKin0900 時間0900売上金額
     */
    public void setUriKin0900(BigDecimal uriKin0900) {
        this.uriKin0900 = uriKin0900;
    }
    
    /**
     * 時間1000売上件数を取得します。
     * @return 時間1000売上件数
     */
    public BigDecimal getUriKen1000() {
        return uriKen1000;
    }
    /**
     * 時間1000売上件数を設定します。
     * @param uriKen1000 時間1000売上件数
     */
    public void setUriKen1000(BigDecimal uriKen1000) {
        this.uriKen1000 = uriKen1000;
    }
    
    /**
     * 時間1000売上金額を取得します。
     * @return 時間1000売上金額
     */
    public BigDecimal getUriKin1000() {
        return uriKin1000;
    }
    /**
     * 時間1000売上金額を設定します。
     * @param uriKin1000 時間1000売上金額
     */
    public void setUriKin1000(BigDecimal uriKin1000) {
        this.uriKin1000 = uriKin1000;
    }
    
    /**
     * 時間1100売上件数を取得します。
     * @return 時間1100売上件数
     */
    public BigDecimal getUriKen1100() {
        return uriKen1100;
    }
    /**
     * 時間1100売上件数を設定します。
     * @param uriKen1100 時間1100売上件数
     */
    public void setUriKen1100(BigDecimal uriKen1100) {
        this.uriKen1100 = uriKen1100;
    }
    
    /**
     * 時間1100売上金額を取得します。
     * @return 時間1100売上金額
     */
    public BigDecimal getUriKin1100() {
        return uriKin1100;
    }
    /**
     * 時間1100売上金額を設定します。
     * @param uriKin1100 時間1100売上金額
     */
    public void setUriKin1100(BigDecimal uriKin1100) {
        this.uriKin1100 = uriKin1100;
    }
    
    /**
     * 時間1200売上件数を取得します。
     * @return 時間1200売上件数
     */
    public BigDecimal getUriKen1200() {
        return uriKen1200;
    }
    /**
     * 時間1200売上件数を設定します。
     * @param uriKen1200 時間1200売上件数
     */
    public void setUriKen1200(BigDecimal uriKen1200) {
        this.uriKen1200 = uriKen1200;
    }
    
    /**
     * 時間1200売上金額を取得します。
     * @return 時間1200売上金額
     */
    public BigDecimal getUriKin1200() {
        return uriKin1200;
    }
    /**
     * 時間1200売上金額を設定します。
     * @param uriKin1200 時間1200売上金額
     */
    public void setUriKin1200(BigDecimal uriKin1200) {
        this.uriKin1200 = uriKin1200;
    }
    
    /**
     * 時間1300売上件数を取得します。
     * @return 時間1300売上件数
     */
    public BigDecimal getUriKen1300() {
        return uriKen1300;
    }
    /**
     * 時間1300売上件数を設定します。
     * @param uriKen1300 時間1300売上件数
     */
    public void setUriKen1300(BigDecimal uriKen1300) {
        this.uriKen1300 = uriKen1300;
    }
    
    /**
     * 時間1300売上金額を取得します。
     * @return 時間1300売上金額
     */
    public BigDecimal getUriKin1300() {
        return uriKin1300;
    }
    /**
     * 時間1300売上金額を設定します。
     * @param uriKin1300 時間1300売上金額
     */
    public void setUriKin1300(BigDecimal uriKin1300) {
        this.uriKin1300 = uriKin1300;
    }
    
    /**
     * 時間1400売上件数を取得します。
     * @return 時間1400売上件数
     */
    public BigDecimal getUriKen1400() {
        return uriKen1400;
    }
    /**
     * 時間1400売上件数を設定します。
     * @param uriKen1400 時間1400売上件数
     */
    public void setUriKen1400(BigDecimal uriKen1400) {
        this.uriKen1400 = uriKen1400;
    }
    
    /**
     * 時間1400売上金額を取得します。
     * @return 時間1400売上金額
     */
    public BigDecimal getUriKin1400() {
        return uriKin1400;
    }
    /**
     * 時間1400売上金額を設定します。
     * @param uriKin1400 時間1400売上金額
     */
    public void setUriKin1400(BigDecimal uriKin1400) {
        this.uriKin1400 = uriKin1400;
    }
    
    /**
     * 時間1500売上件数を取得します。
     * @return 時間1500売上件数
     */
    public BigDecimal getUriKen1500() {
        return uriKen1500;
    }
    /**
     * 時間1500売上件数を設定します。
     * @param uriKen1500 時間1500売上件数
     */
    public void setUriKen1500(BigDecimal uriKen1500) {
        this.uriKen1500 = uriKen1500;
    }
    
    /**
     * 時間1500売上金額を取得します。
     * @return 時間1500売上金額
     */
    public BigDecimal getUriKin1500() {
        return uriKin1500;
    }
    /**
     * 時間1500売上金額を設定します。
     * @param uriKin1500 時間1500売上金額
     */
    public void setUriKin1500(BigDecimal uriKin1500) {
        this.uriKin1500 = uriKin1500;
    }
    
    /**
     * 時間1600売上件数を取得します。
     * @return 時間1600売上件数
     */
    public BigDecimal getUriKen1600() {
        return uriKen1600;
    }
    /**
     * 時間1600売上件数を設定します。
     * @param uriKen1600 時間1600売上件数
     */
    public void setUriKen1600(BigDecimal uriKen1600) {
        this.uriKen1600 = uriKen1600;
    }
    
    /**
     * 時間1600売上金額を取得します。
     * @return 時間1600売上金額
     */
    public BigDecimal getUriKin1600() {
        return uriKin1600;
    }
    /**
     * 時間1600売上金額を設定します。
     * @param uriKin1600 時間1600売上金額
     */
    public void setUriKin1600(BigDecimal uriKin1600) {
        this.uriKin1600 = uriKin1600;
    }
    
    /**
     * 時間1700売上件数を取得します。
     * @return 時間1700売上件数
     */
    public BigDecimal getUriKen1700() {
        return uriKen1700;
    }
    /**
     * 時間1700売上件数を設定します。
     * @param uriKen1700 時間1700売上件数
     */
    public void setUriKen1700(BigDecimal uriKen1700) {
        this.uriKen1700 = uriKen1700;
    }
    
    /**
     * 時間1700売上金額を取得します。
     * @return 時間1700売上金額
     */
    public BigDecimal getUriKin1700() {
        return uriKin1700;
    }
    /**
     * 時間1700売上金額を設定します。
     * @param uriKin1700 時間1700売上金額
     */
    public void setUriKin1700(BigDecimal uriKin1700) {
        this.uriKin1700 = uriKin1700;
    }
    
    /**
     * 時間1800売上件数を取得します。
     * @return 時間1800売上件数
     */
    public BigDecimal getUriKen1800() {
        return uriKen1800;
    }
    /**
     * 時間1800売上件数を設定します。
     * @param uriKen1800 時間1800売上件数
     */
    public void setUriKen1800(BigDecimal uriKen1800) {
        this.uriKen1800 = uriKen1800;
    }
    
    /**
     * 時間1800売上金額を取得します。
     * @return 時間1800売上金額
     */
    public BigDecimal getUriKin1800() {
        return uriKin1800;
    }
    /**
     * 時間1800売上金額を設定します。
     * @param uriKin1800 時間1800売上金額
     */
    public void setUriKin1800(BigDecimal uriKin1800) {
        this.uriKin1800 = uriKin1800;
    }
    
    /**
     * 時間1900売上件数を取得します。
     * @return 時間1900売上件数
     */
    public BigDecimal getUriKen1900() {
        return uriKen1900;
    }
    /**
     * 時間1900売上件数を設定します。
     * @param uriKen1900 時間1900売上件数
     */
    public void setUriKen1900(BigDecimal uriKen1900) {
        this.uriKen1900 = uriKen1900;
    }
    
    /**
     * 時間1900売上金額を取得します。
     * @return 時間1900売上金額
     */
    public BigDecimal getUriKin1900() {
        return uriKin1900;
    }
    /**
     * 時間1900売上金額を設定します。
     * @param uriKin1900 時間1900売上金額
     */
    public void setUriKin1900(BigDecimal uriKin1900) {
        this.uriKin1900 = uriKin1900;
    }
    
    /**
     * 時間2000売上件数を取得します。
     * @return 時間2000売上件数
     */
    public BigDecimal getUriKen2000() {
        return uriKen2000;
    }
    /**
     * 時間2000売上件数を設定します。
     * @param uriKen2000 時間2000売上件数
     */
    public void setUriKen2000(BigDecimal uriKen2000) {
        this.uriKen2000 = uriKen2000;
    }
    
    /**
     * 時間2000売上金額を取得します。
     * @return 時間2000売上金額
     */
    public BigDecimal getUriKin2000() {
        return uriKin2000;
    }
    /**
     * 時間2000売上金額を設定します。
     * @param uriKin2000 時間2000売上金額
     */
    public void setUriKin2000(BigDecimal uriKin2000) {
        this.uriKin2000 = uriKin2000;
    }
    
    /**
     * 時間2100売上件数を取得します。
     * @return 時間2100売上件数
     */
    public BigDecimal getUriKen2100() {
        return uriKen2100;
    }
    /**
     * 時間2100売上件数を設定します。
     * @param uriKen2100 時間2100売上件数
     */
    public void setUriKen2100(BigDecimal uriKen2100) {
        this.uriKen2100 = uriKen2100;
    }
    
    /**
     * 時間2100売上金額を取得します。
     * @return 時間2100売上金額
     */
    public BigDecimal getUriKin2100() {
        return uriKin2100;
    }
    /**
     * 時間2100売上金額を設定します。
     * @param uriKin2100 時間2100売上金額
     */
    public void setUriKin2100(BigDecimal uriKin2100) {
        this.uriKin2100 = uriKin2100;
    }
    
    /**
     * 時間2200売上件数を取得します。
     * @return 時間2200売上件数
     */
    public BigDecimal getUriKen2200() {
        return uriKen2200;
    }
    /**
     * 時間2200売上件数を設定します。
     * @param uriKen2200 時間2200売上件数
     */
    public void setUriKen2200(BigDecimal uriKen2200) {
        this.uriKen2200 = uriKen2200;
    }
    
    /**
     * 時間2200売上金額を取得します。
     * @return 時間2200売上金額
     */
    public BigDecimal getUriKin2200() {
        return uriKin2200;
    }
    /**
     * 時間2200売上金額を設定します。
     * @param uriKin2200 時間2200売上金額
     */
    public void setUriKin2200(BigDecimal uriKin2200) {
        this.uriKin2200 = uriKin2200;
    }
    
    /**
     * 時間2300売上件数を取得します。
     * @return 時間2300売上件数
     */
    public BigDecimal getUriKen2300() {
        return uriKen2300;
    }
    /**
     * 時間2300売上件数を設定します。
     * @param uriKen2300 時間2300売上件数
     */
    public void setUriKen2300(BigDecimal uriKen2300) {
        this.uriKen2300 = uriKen2300;
    }
    
    /**
     * 時間2300売上金額を取得します。
     * @return 時間2300売上金額
     */
    public BigDecimal getUriKin2300() {
        return uriKin2300;
    }
    /**
     * 時間2300売上金額を設定します。
     * @param uriKin2300 時間2300売上金額
     */
    public void setUriKin2300(BigDecimal uriKin2300) {
        this.uriKin2300 = uriKin2300;
    }
    
    /**
     * 時間その他売上件数を取得します。
     * @return 時間その他売上件数
     */
    public BigDecimal getUriKenOther() {
        return uriKenOther;
    }
    /**
     * 時間その他売上件数を設定します。
     * @param uriKenOther 時間その他売上件数
     */
    public void setUriKenOther(BigDecimal uriKenOther) {
        this.uriKenOther = uriKenOther;
    }
    
    /**
     * 時間その他売上金額を取得します。
     * @return 時間その他売上金額
     */
    public BigDecimal getUriKinOther() {
        return uriKinOther;
    }
    /**
     * 時間その他売上金額を設定します。
     * @param uriKinOther 時間その他売上金額
     */
    public void setUriKinOther(BigDecimal uriKinOther) {
        this.uriKinOther = uriKinOther;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0000() {
        if(getUriKin0000() != null && getUriKin0000().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0000 = getUriKin0000().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0000 = kouseiHi0000.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0000 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi0000;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0100() {
        if(getUriKin0000() != null && getUriKin0100().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0100 = getUriKin0100().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0100 = kouseiHi0100.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0100 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi0100;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0200() {
        if(getUriKin0200() != null && getUriKin0200().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0200 = getUriKin0200().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0200 = kouseiHi0200.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0200 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi0200;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0300() {
        if(getUriKin0300() != null && getUriKin0300().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0300 = getUriKin0300().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0300 = kouseiHi0300.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0300 = new BigDecimal(0.0).setScale(1);            
        }
        return kouseiHi0300;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0400() {
        if(getUriKin0400() != null && getUriKin0400().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0400 = getUriKin0400().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0400 = kouseiHi0400.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0400 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi0400;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0500() {
        if(getUriKin0500() != null && getUriKin0500().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0500 = getUriKin0500().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0500 = kouseiHi0500.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0500 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi0500;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0600() {
        if(getUriKin0600() != null && getUriKin0600().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0600 = getUriKin0600().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0600 = kouseiHi0600.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0600 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi0600;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0700() {
        if(getUriKin0700() != null && getUriKin0700().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0700 = getUriKin0700().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0700 = kouseiHi0700.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0700 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi0700;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0800() {
        if(getUriKin0800() != null && getUriKin0800().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0800 = getUriKin0800().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0800 = kouseiHi0800.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0800 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi0800;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi0900() {
        if(getUriKin0900() != null && getUriKin0900().compareTo(new BigDecimal(0)) != 0){
            kouseiHi0900 = getUriKin0900().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi0900 = kouseiHi0900.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi0900 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi0900;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1000() {
        if(getUriKin1000() != null && getUriKin1000().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1000 = getUriKin1000().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1000 = kouseiHi1000.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1000 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1000;
    }

    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1100() {
        if(getUriKin1100() != null && getUriKin1100().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1100 = getUriKin1100().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1100 = kouseiHi1100.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1100 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1100;
    }
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1200() {
        if(getUriKin1200() != null && getUriKin1200().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1200 = getUriKin1200().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1200 = kouseiHi1200.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1200 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1200;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1300() {
        if(getUriKin1300() != null && getUriKin1300().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1300 = getUriKin1300().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1300 = kouseiHi1300.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1300 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1300;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1400() {
        if(getUriKin1400() != null && getUriKin1400().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1400 = getUriKin1400().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1400 = kouseiHi1400.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1400 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1400;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1500() {
        if(getUriKin1500() != null && getUriKin1500().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1500 = getUriKin1500().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1500 = kouseiHi1500.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1500 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1500;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1600() {
        if(getUriKin1600() != null && getUriKin1600().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1600 = getUriKin1600().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1600 = kouseiHi1600.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1600 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1600;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1700() {
        if(getUriKin1700() != null && getUriKin1700().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1700 = getUriKin1700().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1700 = kouseiHi1700.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1700 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1700;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1800() {
        if(getUriKin1800() != null && getUriKin1800().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1800 = getUriKin1800().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1800 = kouseiHi1800.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1800 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1800;
    }
    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi1900() {
        if(getUriKin1900() != null && getUriKin1900().compareTo(new BigDecimal(0)) != 0){
            kouseiHi1900 = getUriKin1900().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi1900 = kouseiHi1900.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi1900 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi1900;
    }

    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi2000() {
        if(getUriKin2000() != null && getUriKin2000().compareTo(new BigDecimal(0)) != 0){
            kouseiHi2000 = getUriKin2000().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi2000 = kouseiHi2000.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi2000 = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHi2000;
    }

    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi2100() {
        if(getUriKin2100() != null && getUriKin2100().compareTo(new BigDecimal(0)) != 0){
            kouseiHi2100 = getUriKin2100().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi2100 = kouseiHi2100.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi2100 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi2100;
    }

    
    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi2200() {
        if(getUriKin2200() != null && getUriKin2200().compareTo(new BigDecimal(0)) != 0){
            kouseiHi2200 = getUriKin2200().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi2200 = kouseiHi2200.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi2200 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi2200;
    }

    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHi2300() {
        if(getUriKin2300() != null && getUriKin2300().compareTo(new BigDecimal(0)) != 0){
            kouseiHi2300 = getUriKin2300().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHi2300 = kouseiHi2300.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHi2300 = new BigDecimal(0.0).setScale(1);
        }
        return kouseiHi2300;
    }

    /**
     * 構成比を取得します。
     * @return 構成比
     */
    public BigDecimal getKouseiHiOther() {
        if(getUriKinOther() != null && getUriKinOther().compareTo(new BigDecimal(0)) != 0){
            kouseiHiOther = getUriKinOther().divide(getAllKin(),3, BigDecimal.ROUND_HALF_UP);
            kouseiHiOther = kouseiHiOther.multiply(new BigDecimal(100)).setScale(1);
        }else{
            kouseiHiOther = new BigDecimal(0.0).setScale(1);;
        }
        return kouseiHiOther;
    }
    
    /**
     * 総合計を取得します。
     * @return 総合計
     */
    public BigDecimal getAllKin() {
        BigDecimal total0000 = new BigDecimal(0);
        BigDecimal total0100 = new BigDecimal(0);
        BigDecimal total0200 = new BigDecimal(0);
        BigDecimal total0300 = new BigDecimal(0);
        BigDecimal total0400 = new BigDecimal(0);
        BigDecimal total0500 = new BigDecimal(0);
        BigDecimal total0600 = new BigDecimal(0);
        BigDecimal total0700 = new BigDecimal(0);
        BigDecimal total0800 = new BigDecimal(0);
        BigDecimal total0900 = new BigDecimal(0);
        BigDecimal total1000 = new BigDecimal(0);
        BigDecimal total1100 = new BigDecimal(0);
        BigDecimal total1200 = new BigDecimal(0);
        BigDecimal total1300 = new BigDecimal(0);
        BigDecimal total1400 = new BigDecimal(0);
        BigDecimal total1500 = new BigDecimal(0);
        BigDecimal total1600 = new BigDecimal(0);
        BigDecimal total1700 = new BigDecimal(0);
        BigDecimal total1800 = new BigDecimal(0);
        BigDecimal total1900 = new BigDecimal(0);
        BigDecimal total2000 = new BigDecimal(0);
        BigDecimal total2100 = new BigDecimal(0);
        BigDecimal total2200 = new BigDecimal(0);
        BigDecimal total2300 = new BigDecimal(0);
        if(getUriKin0000() != null){
            total0000 = getUriKin0000();
        }
        if(getUriKin0100() != null){
            total0100 = getUriKin0100().add(total0000);
        }else{
            total0100 = total0000;
        }
        if(getUriKin0200() != null){
            total0200 = getUriKin0200().add(total0100);
        }else{
            total0200 = total0100;
        }
        if(getUriKin0300() != null){
            total0300 = getUriKin0300().add(total0200);
        }else{
            total0300 = total0200;
        }
        if(getUriKin0400() != null){
            total0400 = getUriKin0400().add(total0300);
        }else{
            total0400 = total0300;
        }
        if(getUriKin0500() != null){
            total0500 = getUriKin0500().add(total0400);
        }else{
            total0500 = total0400;
        }
        if(getUriKin0600() != null){
            total0600 = getUriKin0600().add(total0500);
        }else{
            total0600 = total0500;
        }
        if(getUriKin0700() != null){
            total0700 = getUriKin0700().add(total0600);
        }else{
            total0700 = total0600;
        }
        if(getUriKin0800() != null){
            total0800 = getUriKin0800().add(total0700);
        }else{
            total0800 = total0700;
        }
        if(getUriKin0900() != null){
            total0900 = getUriKin0900().add(total0800);
        }else{
            total0900 = total0800;
        }
        if(getUriKin1000() != null){
            total1000 = getUriKin1000().add(total0900);
        }else{
            total1000 = total0900;
        }
        if(getUriKin1100() != null){
            total1100 = getUriKin1100().add(total1000);
        }else{
            total1100 = total1000;
        }
        if(getUriKin1200() != null){
            total1200 = getUriKin1200().add(total1100);
        }else{
            total1200 = total1100;
        }
        if(getUriKin1300() != null){
            total1300 = getUriKin1300().add(total1200);
        }else{
            total1300 = total1200;
        }
        if(getUriKin1400() != null){
            total1400 = getUriKin1400().add(total1300);
        }else{
            total1400 = total1300;
        }
        if(getUriKin1500() != null){
            total1500 = getUriKin1500().add(total1400);
        }else{
            total1500 = total1400;
        }
        if(getUriKin1600() != null){
            total1600 = getUriKin1600().add(total1500);
        }else{
            total1600 = total1500;
        }
        if(getUriKin1700() != null){
            total1700 = getUriKin1700().add(total1600);
        }else{
            total1700 = total1600;
        }
        if(getUriKin1800() != null){
            total1800 = getUriKin1800().add(total1700);
        }else{
            total1800 = total1700;
        }
        if(getUriKin1900() != null){
            total1900 = getUriKin1900().add(total1800);
        }else{
            total1900 = total1800;
        }
        if(getUriKin2000() != null){
            total2000 = getUriKin2000().add(total1900);
        }else{
            total2000 = total1900;
        }
        if(getUriKin2100() != null){
            total2100 = getUriKin2100().add(total2000);
        }else{
            total2100 = total2000;
        }
        if(getUriKin2200() != null){
            total2200 = getUriKin2200().add(total2100);
        }else{
            total2200 = total2100;
        }
        if(getUriKin2300() != null){
            total2300 = getUriKin2300().add(total2200);
        }else{
            total2300 = total2200;
        }
        if(getUriKinOther() != null){
            allKin = getUriKinOther().add(total2300);
        }else{
            allKin = total2300;
        }
        return allKin;
    }

    
}
