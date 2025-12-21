/*
 * 作成日: 2006/02/01

 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.dto;

import java.util.List;

/**
 * @author xkhata
 */
public class RoleActConditionDto {

	private List uIRole;
	private List uIBirdMenu;
	private List selectPull;
	private String selectPullName;
	//private boolean uIFlg;

    /** 機能orロールのプルダウンの値(0･･･機能、1･･･ロール) */
    private String selectPullId;

    /** 初期処理フラグ(true･･･初期処理済、false･･･初期処理未) */
    private boolean initFlg;


    /////////////////////////////////以下、セッター・ゲッター//////////////////////////

	/* 機能を選択して登録の情報 */
	public List getUIBirdMenu() {
		return this.uIBirdMenu;
	}
	
	public void setUIBirdMenu(List UIBirdMenu) {
		this.uIBirdMenu = UIBirdMenu;
	}
		
	/* ロールを選択して登録の情報 */
	public List getUIRole() {
		return this.uIRole;
	}
	
	public void setUIRole(List UIRole) {
		this.uIRole = UIRole;
	}
		
	/* 選択プルダウン情報 */
	public List getSelectPull() {
		return this.selectPull;
	}
		
	public void setSelectPull(List selectPull) {
		this.selectPull = selectPull;
	}

    /** 
     * 選択プルダウンの値(0･･･機能、1･･･ロール)を取得する。
     */
	public String getSelectPullId() {
		return selectPullId;
	}
    /** 
     * 選択プルダウンの値(0･･･機能、1･･･ロール)を設定する。
     */
	public void setSelectPullId(String selectPullId) {
		this.selectPullId = selectPullId;
	}
	
	/* 選択プルダウン名称情報*/
	public String getSelectPullName() {
		return selectPullName;
	}
	public void setSelectPullName(String selectPullName) {
		this.selectPullName = selectPullName;
	}
	
	
	/* 初期処理フラグ*/
	public boolean getInitFlg() {
		return this.initFlg;
	}
	
	public void setInitFlg(boolean initFlg) {
		this.initFlg = initFlg;
	}
	
	public void clear() {
		setUIBirdMenu(null);
		setUIRole(null);
		setInitFlg(false);
	}
	
}
