package jp.co.isid.mos.bird.sysadmin.useractregist.entity;

import java.sql.Timestamp;
/**
 * 個別ユーザーアクセス権限エンティティ
 * 
 * 作成日:2009/12/21
 * @author xkinu
 *
 */
public class CtlUserAct {
    
    public static final String TABLE = "BR54USAC";
    
    public static final String TIMESTAMP_PROPERTY = "lastTmsp";

    
    public static final String userId_COLUMN = "USER_ID";
    
    public static final String menuId_COLUMN = "MENU_ID";
    
    public static final String subMenuId_COLUMN = "SUB_MENU_ID";
    
    public static final String customizeFlg_COLUMN = "CUSTOMIZE_FLG";

    public static final String firstUser_COLUMN = "FIRST_USER";
    
    public static final String firstPgm_COLUMN = "FIRST_PGM";
    
    public static final String firstTmsp_COLUMN = "FIRST_TMSP";
    
    public static final String lastUser_COLUMN = "LAST_USER";
    
    public static final String lastPgm_COLUMN = "LAST_PGM";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * ユーザID
     */
    private String userId;
    
    /**
     * メニューID
     */
    private String menuId;
    
    /**
     * サブメニューID
     */
    private String subMenuId;
    
    /**
     * 個別設定フラグ
     */
    private String customizeFlg;

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
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return customizeFlg を戻します。
     */
    public String getCustomizeFlg() {
        return customizeFlg;
    }
    /**
     * @param customizeFlg 設定する customizeFlg。
     */
    public void setCustomizeFlg(String customizeFlg) {
        this.customizeFlg = customizeFlg;
    }
	/**
	 * サブメニューID
	 * @return クラス変数subMenuId を戻します。
	 */
	public String getSubMenuId() {
		return subMenuId;
	}
	/**
	 * サブメニューID
	 * @param subMenuId を クラス変数subMenuIdへ設定します。
	 */
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
    
}
