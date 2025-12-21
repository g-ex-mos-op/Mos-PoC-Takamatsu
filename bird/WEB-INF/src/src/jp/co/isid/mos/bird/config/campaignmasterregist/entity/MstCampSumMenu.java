package jp.co.isid.mos.bird.config.campaignmasterregist.entity;

import java.sql.Timestamp;

public class MstCampSumMenu {
    
    public static final String TABLE = "BM62SYMM";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuName_COLUMN = "MENU_NAME";
    
    public static final String convValue_COLUMN = "CONV_VALUE";
    
    public static final String sumMenuCd_COLUMN = "SUM_MENU_CD";
    
    public static final String sumMenuName_COLUMN = "SUM_MENU_NAME";
    
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    /**
     * メニューコード
     */
    private String menuCd;
    /**
     * メニュー名称
     */
    private String menuName;
    /**
     * 換算値
     */
    private String convValue;
    
    /**
     * 集約メニューコード
     */
    private String sumMenuCd;
    
    /**
     * 集約メニュー名称
     */
    private String sumMenuName;
    /**
     * 登録ユーザーID
     */
    private String firstUser;
    
    /**
     * 登録タイムスタンプ
     */
    private Timestamp firstTmsp;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 更新ユーザーID
     */
    private String lastUser;
    
    /**
     * 更新タイムスタンプ
     */
    private Timestamp lastTmsp;
    
    /**
     * 更新プログラム
     */
    private String lastPgm;
    
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
     * 換算値を取得します。
     * @return 換算値
     */
    public String getConvValue() {
        return convValue;
    }
    /**
     * 換算値を設定します。
     * @param convValue 換算値
     */
    public void setConvValue(String convValue) {
        this.convValue = convValue;
    }
    
    /**
     * 集約メニューコードを取得します。
     * @return 集約メニューコード
     */
    public String getSumMenuCd() {
        return sumMenuCd;
    }
    /**
     * 集約メニューコードを設定します。
     * @param sumMenuCd 集約メニューコード
     */
    public void setSumMenuCd(String sumMenuCd) {
        this.sumMenuCd = sumMenuCd;
    }
    
    /**
     * 登録ユーザーIDを取得します。
     * @return 登録ユーザーID
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーIDを設定します。
     * @param firstUser 登録ユーザーID
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }
    
    /**
     * 登録タイムスタンプを取得します。
     * @return 登録タイムスタンプ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録タイムスタンプを設定します。
     * @param firstTmsp 登録タイムスタンプ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 登録プログラムを取得します。
     * @return 登録プログラム
     */
    public String getFirstPgm() {
        return firstPgm;
    }
    /**
     * 登録プログラムを設定します。
     * @param firstPgm 登録プログラム
     */
    public void setFirstPgm(String firstPgm) {
        this.firstPgm = firstPgm;
    }
    
    /**
     * 更新ユーザーIDを取得します。
     * @return 更新ユーザーID
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 更新ユーザーIDを設定します。
     * @param lastUser 更新ユーザーID
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 更新タイムスタンプを取得します。
     * @return 更新タイムスタンプ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新タイムスタンプを設定します。
     * @param lastTmsp 更新タイムスタンプ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
    /**
     * 更新プログラムを取得します。
     * @return 更新プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 更新プログラムを設定します。
     * @param lastPgm 更新プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getSumMenuName() {
        return sumMenuName;
    }
    public void setSumMenuName(String sumMenuName) {
        this.sumMenuName = sumMenuName;
    }
    
}
