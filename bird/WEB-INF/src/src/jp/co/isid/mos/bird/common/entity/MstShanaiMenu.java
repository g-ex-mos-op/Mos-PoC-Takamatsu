package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

public class MstShanaiMenu {
    
    public static final String TABLE = "PC10SMNU";
    
    public static final String menuCd_COLUMN = "MENU_CD";
    
    public static final String menuName10_COLUMN = "MENU_NAME_10";
    
    public static final String menuName8_COLUMN = "MENU_NAME_8";
    
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    
    public static final String menuBunrui_COLUMN = "MENU_BUNRUI";
    
    public static final String menuHanbai_COLUMN = "MENU_HANBAI";
    
    public static final String menuShokuzai_COLUMN = "MENU_SHOKUZAI";
    
    public static final String menuStaDt_COLUMN = "MENU_STA_DT";
    
    public static final String menuEndDt_COLUMN = "MENU_END_DT";
    
    public static final String menuKbn1_COLUMN = "MENU_KBN1";
    
    public static final String menuKbn2_COLUMN = "MENU_KBN2";
    
    public static final String menuKbn3_COLUMN = "MENU_KBN3";
    
    public static final String menuDt1_COLUMN = "MENU_DT1";
    
    public static final String menuDt2_COLUMN = "MENU_DT2";
       
    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * メニューコード
     */
    private String menuCd;
    
    /**
     * メニュー名称
     */
    private String menuName10;
    
    /**
     * メニュー名称
     */
    private String menuName8;
    
    /**
     * メニュー名称
     */
    private String menuNameKj;
    
    /**
     * メニュー分類
     */
    private String menuBunrui;
    
    /**
     * メニュー販売形態
     */
    private String menuHanbai;
    
    /**
     * メニュー食材区分
     */
    private String menuShokuzai;
    
    /**
     * 販売開始日
     */
    private String menuStaDt;
    
    /**
     * 販売終了日
     */
    private String menuEndDt;
    
    /**
     * メニュー区分１
     */
    private String menuKbn1;
    
    /**
     * メニュー区分２
     */
    private String menuKbn2;
    
    /**
     * メニュー区分３
     */
    private String menuKbn3;
    
    /**
     * メニュー日付１
     */
    private String menuDt1;
    
    /**
     * メニュー日付２
     */
    private String menuDt2;
    
    /**
     * 選択フラグ
     */
    private boolean selectFlg = false;
    
    /**
     * 登録ユーザー
     */
    private String firstUser;
    
    /**
     * 登録プログラム
     */
    private String firstPgm;
    
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp firstTmsp;
    
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
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
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuName10() {
        return menuName10;
    }
    /**
     * メニュー名称を設定します。
     * @param menuName10 メニュー名称
     */
    public void setMenuName10(String menuName10) {
        this.menuName10 = menuName10;
    }
    
    /**
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuName8() {
        return menuName8;
    }
    /**
     * メニュー名称を設定します。
     * @param menuName8 メニュー名称
     */
    public void setMenuName8(String menuName8) {
        this.menuName8 = menuName8;
    }
    
    /**
     * メニュー名称を取得します。
     * @return メニュー名称
     */
    public String getMenuNameKj() {
        return menuNameKj;
    }
    /**
     * メニュー名称を設定します。
     * @param menuNameKj メニュー名称
     */
    public void setMenuNameKj(String menuNameKj) {
        this.menuNameKj = menuNameKj;
    }
    
    /**
     * メニュー分類を取得します。
     * @return メニュー分類
     */
    public String getMenuBunrui() {
        return menuBunrui;
    }
    /**
     * メニュー分類を設定します。
     * @param menuBunrui メニュー分類
     */
    public void setMenuBunrui(String menuBunrui) {
        this.menuBunrui = menuBunrui;
    }
    
    /**
     * メニュー販売形態を取得します。
     * @return メニュー販売形態
     */
    public String getMenuHanbai() {
        return menuHanbai;
    }
    /**
     * メニュー販売形態を設定します。
     * @param menuHanbai メニュー販売形態
     */
    public void setMenuHanbai(String menuHanbai) {
        this.menuHanbai = menuHanbai;
    }
    
    /**
     * メニュー食材区分を取得します。
     * @return メニュー食材区分
     */
    public String getMenuShokuzai() {
        return menuShokuzai;
    }
    /**
     * メニュー食材区分を設定します。
     * @param menuShokuzai メニュー食材区分
     */
    public void setMenuShokuzai(String menuShokuzai) {
        this.menuShokuzai = menuShokuzai;
    }
    
    /**
     * 販売開始日を取得します。
     * @return 販売開始日
     */
    public String getMenuStaDt() {
        return menuStaDt;
    }
    /**
     * 販売開始日を設定します。
     * @param menuStaDt 販売開始日
     */
    public void setMenuStaDt(String menuStaDt) {
        this.menuStaDt = menuStaDt;
    }
    
    /**
     * 販売終了日を取得します。
     * @return 販売終了日
     */
    public String getMenuEndDt() {
        return menuEndDt;
    }
    /**
     * 販売終了日を設定します。
     * @param menuEndDt 販売終了日
     */
    public void setMenuEndDt(String menuEndDt) {
        this.menuEndDt = menuEndDt;
    }
    
    /**
     * メニュー区分１を取得します。
     * @return メニュー区分１
     */
    public String getMenuKbn1() {
        return menuKbn1;
    }
    /**
     * メニュー区分１を設定します。
     * @param menuKbn1 メニュー区分１
     */
    public void setMenuKbn1(String menuKbn1) {
        this.menuKbn1 = menuKbn1;
    }
    
    /**
     * メニュー区分２を取得します。
     * @return メニュー区分２
     */
    public String getMenuKbn2() {
        return menuKbn2;
    }
    /**
     * メニュー区分２を設定します。
     * @param menuKbn2 メニュー区分２
     */
    public void setMenuKbn2(String menuKbn2) {
        this.menuKbn2 = menuKbn2;
    }
    
    /**
     * メニュー区分３を取得します。
     * @return メニュー区分３
     */
    public String getMenuKbn3() {
        return menuKbn3;
    }
    /**
     * メニュー区分３を設定します。
     * @param menuKbn3 メニュー区分３
     */
    public void setMenuKbn3(String menuKbn3) {
        this.menuKbn3 = menuKbn3;
    }
    
    /**
     * メニュー日付１を取得します。
     * @return メニュー日付１
     */
    public String getMenuDt1() {
        return menuDt1;
    }
    /**
     * メニュー日付１を設定します。
     * @param menuDt1 メニュー日付１
     */
    public void setMenuDt1(String menuDt1) {
        this.menuDt1 = menuDt1;
    }
    
    /**
     * メニュー日付２を取得します。
     * @return メニュー日付２
     */
    public String getMenuDt2() {
        return menuDt2;
    }
    /**
     * メニュー日付２を設定します。
     * @param menuDt2 メニュー日付２
     */
    public void setMenuDt2(String menuDt2) {
        this.menuDt2 = menuDt2;
    }
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean isSelectFlg() {
        return selectFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param selectFlg 選択フラグ
     */
    public void setSelectFlg(boolean selectFlg) {
        this.selectFlg = selectFlg;
    }
    
    /**
     * 登録ユーザーを取得します。
     * @return 登録ユーザー
     */
    public String getFirstUser() {
        return firstUser;
    }
    /**
     * 登録ユーザーを設定します。
     * @param firstUser 登録ユーザー
     */
    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
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
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getFirstTmsp() {
        return firstTmsp;
    }
    /**
     * 登録時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param firstTmsp 登録時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setFirstTmsp(Timestamp firstTmsp) {
        this.firstTmsp = firstTmsp;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
}
