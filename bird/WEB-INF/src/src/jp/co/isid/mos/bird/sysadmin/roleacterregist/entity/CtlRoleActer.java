package jp.co.isid.mos.bird.sysadmin.roleacterregist.entity;

import java.sql.Timestamp;

public class CtlRoleActer {
    
    public static final String TABLE = "BR05ACTR";
    
    public static final String roleCd_COLUMN      = "ROLE_CD";
    
    public static final String roleName_COLUMN    = "ROLE_NAME";
    
    public static final String menuId_COLUMN      = "MENU_ID";
    
    public static final String subMenuId_COLUMN   = "SUB_MENU_ID";
    
    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";
    
    public static final String enableFlg_COLUMN   = "ENABLE_FLG";

    public static final String extraFlg_COLUMN    = "EXTRA_FLG";
    
    public static final String firstUser_COLUMN   = "FIRST_USER";
    
    public static final String firstPgm_COLUMN    = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN   = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN    = "LAST_USER";
    
    public static final String lastPgm_COLUMN     = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN    = "LAST_TMSP";
    
    public static final String checkFlg_COLUMN    = "";
    
    /**
     * ロールコード
     */
    private String roleCd;
    
    /**
     * ロール名称
     */
    private String roleName;
    
    /**
     * メニューID
     */
    private String menuId;
    
    /**
     * サブメニューID
     */
    private String subMenuId;
    
    /**
     * サブメニュー名称
     */
    private String subMenuName;
    
    /**
     * 使用可能フラグ
     */
    private String enableFlg;

    /**
     * 上限拡張フラグ
     */
    private String extraFlg;
    
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
     * 選択フラグ
     */
    private boolean checkFlg;
    /**
     * 選択フラグ(上限)
     */
    private boolean checkFlgLimit;
    
    /**
     * ロールコードを取得します。
     * @return ロールコード
     */
    public String getRoleCd() {
        return roleCd;
    }
    /**
     * ロールコードを設定します。
     * @param roleCd ロールコード
     */
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }
    
    /**
     * ロール名称を取得します。
     * @return ロール名称
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * ロール名称を設定します。
     * @param roleName ロール名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * メニューIDを取得します。
     * @return メニューID
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * メニューIDを設定します。
     * @param menuId メニューID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * サブメニューIDを取得します。
     * @return サブメニューID
     */
    public String getSubMenuId() {
        return subMenuId;
    }
    /**
     * サブメニューIDを設定します。
     * @param subMenuId サブメニューID
     */
    public void setSubMenuId(String subMenuId) {
        this.subMenuId = subMenuId;
    }
    
    /**
     * サブメニュー名称を取得します。
     * @return サブメニュー名称
     */
    public String getSubMenuName() {
        return subMenuName;
    }
    /**
     * サブメニュー名称を設定します。
     * @param subMenuName サブメニュー名称
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
    
    /**
     * 使用可能フラグを取得します。
     * @return 使用可能フラグ
     */
    public String getEnableFlg() {
        return enableFlg;
    }
    /**
     * 使用可能フラグを設定します。
     * @param enableFlg 使用可能フラグ
     */
    public void setEnableFlg(String enableFlg) {
        this.enableFlg = enableFlg;
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
    
    /**
     * 選択フラグを取得します。
     * @return 選択フラグ
     */
    public boolean getCheckFlg() {
        return checkFlg;
    }
    /**
     * 選択フラグを設定します。
     * @param checkFlg 選択フラグ
     */
    public void setCheckFlg(boolean checkFlg) {
        this.checkFlg = checkFlg;
    }

    /**
     * 選択フラグ(上限)を取得します。
     * @return 選択フラグ(上限)
     */
    public boolean getCheckFlgLimit() {
        return checkFlgLimit;
    }
    /**
     * 選択フラグ(上限)を設定します。
     * @param checkFlgLimit 選択フラグ(上限)
     */
    public void setCheckFlgLimit(boolean checkFlgLimit) {
        this.checkFlgLimit = checkFlgLimit;
    }
    /**
     * @return extraFlg を戻します。
     */
    public String getExtraFlg() {
        return extraFlg;
    }
    /**
     * @param extraFlg 設定する extraFlg。
     */
    public void setExtraFlg(String extraFlg) {
        this.extraFlg = extraFlg;
    }

}
