/*
 * 作成日: 2006/02/01
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ロール別アクセス権限設定Dto
 * @author xkhata
 */
public class RoleActRegistDto {

	private List uIRole;
	private List uIBirdMenu;
	private List uIRoleBunrui;
	private Map uIAllRoleMap;
	private Map uIAllBirdMenuMap;
	private String bunruiStr;
	private String menuStr;
	private List initFlgList;
	private List uIViewList;
	private boolean viewFlg1;
	private boolean viewFlg2;
	private boolean viewFlg3;
	private boolean viewFlg4;
	private boolean viewFlg5;
	private boolean initFlg;
	
	/* 機能を選択して登録の情報 */
	public void setUIBirdMenu(final List uIBirdMenu) {
		this.uIBirdMenu = uIBirdMenu;
	}
	
	public List getUIBirdMenu() {
		return this.uIBirdMenu;
	}
	
	/* ロールを選択して登録の情報 */
	public void setUIRole(final List uIRole) {
		this.uIRole = uIRole;
	}
	
	public List getUIRole() {
		return this.uIRole;
	}
	/* ロール分類情報 */
	public void setUIRoleBunrui(final List uIRoleBunrui) {
		this.uIRoleBunrui = uIRoleBunrui;
	}
	
	public List getUiRoleBunrui() {
		return this.uIRoleBunrui;
	}
	
	/* 更新対象ロールの情報 */
	public void setUIAllRoleMap(Map uIAllRoleMap) {
		this.uIAllRoleMap = uIAllRoleMap;
	}
	
	public Map getUIAllRoleMap() {
		return this.uIAllRoleMap;
	}
	
	/* 更新対象メニューの情報 */
	public void setUiAllBirdMenuMap(Map uIAllBirdMenuMap) {
		this.uIAllBirdMenuMap = uIAllBirdMenuMap;
	}
	
	public Map getUiAllBirdMenuMap() {
		return this.uIAllBirdMenuMap;
	}
	
	/* 選択されている分類コード */
	public void setBunruiStr(String bunruiStr) {
		this.bunruiStr = bunruiStr;
	}
	
	public String getBunruiStr() {
		return this.bunruiStr;
	}
	
	/* 選択されているメニューコード */
	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}
	
	public String getMenuStr() {
		return this.menuStr;
	}
	/* 初期化フラグ */
	public void setInitFlg(boolean initFlg) {
		this.initFlg = initFlg;
	}
	public boolean getInitFlg() {
		return this.initFlg;
	}
	
	/* 初期処理済みリスト */
	public void setInitFlgList(List initFlgList) {
		this.initFlgList = initFlgList;
	}
	public List getInitFlgList() {
		return this.initFlgList;
	}
	
	/* 表示用リスト */
	public void setUIViewList(List uIViewList) {
		this.uIViewList  = uIViewList;
	}
	public List getUIViewList() {
		return this.uIViewList;
	}
	
	/* 表示非表示のフラグ１ */
	public void setViewFlg1(boolean viewFlg1) {
		this.viewFlg1 = viewFlg1;
	}
	
	public boolean getViewFlg1() {
		return this.viewFlg1;
	}
	
	/* 表示非表示のフラグ２ */
	public void setViewFlg2(boolean viewFlg2) {
		this.viewFlg2 = viewFlg2;
	}
	
	public boolean getViewFlg2() {
		return this.viewFlg2;
	}
	/* 表示非表示のフラグ３ */
	public void setViewFlg3(boolean viewFlg3) {
		this.viewFlg3 = viewFlg3;
	}
	
	public boolean getViewFlg3() {
		return this.viewFlg3;
	}
	/* 表示非表示のフラグ４ */
	public void setViewFlg4(boolean viewFlg4) {
		this.viewFlg4 = viewFlg4;
	}
	
	public boolean getViewFlg4() {
		return this.viewFlg4;
	}
	/* 表示非表示のフラグ５ */
	public void setViewFlg5(boolean viewFlg5) {
		this.viewFlg5 = viewFlg5;
	}
	
	public boolean getViewFlg5() {
		return this.viewFlg5;
	}
	
	/* クリア */
	public void clear() {
		setUIRole(null);
		setUIBirdMenu(null);
		setUIRoleBunrui(null);
		setUIAllRoleMap( new HashMap());
		setUiAllBirdMenuMap( new HashMap());
		setInitFlg(false);
		setBunruiStr(null);
		setMenuStr(null);
		setInitFlgList(null);
	}
}
