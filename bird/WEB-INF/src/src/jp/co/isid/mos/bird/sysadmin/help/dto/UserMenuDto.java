/*
 * 作成日: 2006/02/28
 *
 */
package jp.co.isid.mos.bird.sysadmin.help.dto;

import java.util.List;

/**
 * @author xylee
 * 更新：2012/03/28 xkinu IEキャッシュ対応
 *       ヘルプファイル更新履歴追加。
 * 　　　最新のヘルプファイルの読込ためのパラメータとして使用されます。
 */
public class UserMenuDto {

    /**
	 * ユーザヘルプリスト
	 */
	private List userHelpList;
	
    /**
	 * ユーザヘルプリストメニュー
	 */
	private List userHelpMenuList;
	
	/**
	 * ビューID
	 */
	private String viewId;
	
	/**
	 * メニューID
	 */
	private String menuId="0";
	
	/**
	 * パース
	 */
	private String filePass;
	
	/**
	 * divId
	 */
	private String divId="bbb";
	
	/**
	 * パイル検査
	 */
	private boolean fileCheck;
	/**
	 * ヘルプファイル更新履歴
	 * 
	 * IEキャッシュ対応
	 * 最新のヘルプファイルの読込ためのパラメータとして使用されます。
	 */
	private String helpVersion;
	/**
	 * @return fileCheck を戻します。
	 */
	public boolean getFileCheck() {
		return fileCheck;
	}
	/**
	 * @param fileCheck を設定。
	 */
	public void setFileCheck(boolean fileCheck) {
		this.fileCheck = fileCheck;
	}
	
	/**
	 * @return menuId を戻します。
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId を設定。
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	/**
	 * @return filePass を戻します。
	 */
	public String getFilePass() {
		return filePass;
	}
	/**
	 * @param filePass filePass を設定。
	 */
	public void setFilePass(String filePass) {
		this.filePass = filePass;
	}
	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}
	/**
	 * @param viewId viewId を設定。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	/**
     * ユーザヘルプリストメニューを取得します。
     * 
     * @return ユーザヘルプリストメニュー
     */
	public List getUserHelpMenuList() {
		return userHelpMenuList;
	}

    /**
     * ユーザヘルプリストメニューを設定します。
     * 
     * @param userHelpMenuList ユーザヘルプリストメニュー
     */
	public void setUserHelpMenuList(List userHelpMenuList) {
		this.userHelpMenuList = userHelpMenuList;
	}
	
	/**
     * ユーザヘルプリストを取得します。
     * 
     * @return ユーザヘルプリスト
     */
	public List getUserHelpList() {
		return userHelpList;
	}

    /**
     * ユーザヘルプリストを設定します。
     * 
     * @param userHelpList ユーザヘルプリスト
     */
	public void setUserHelpList(List userHelpList) {
		this.userHelpList = userHelpList;
	}
	
	/**
     * ユーザヘルプリストを設定します。
     * 
     * @param Divid ユーザヘルプリスト
     */
	public String getDivid() {
		return divId;
	}
	/**
	 * @return クラス変数helpVersion を戻します。
	 */
	public String getHelpVersion() {
		return helpVersion;
	}
	/**
	 * @param helpVersion を クラス変数helpVersionへ設定します。
	 */
	public void setHelpVersion(String helpVersion) {
		this.helpVersion = helpVersion;
	}
	
}
