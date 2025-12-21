/*
 * 作成日: 2006/02/22
 *
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.common.code.SetteiKbn;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class UserActConditionDto {
	private String sessionKey;
	private List userList;
	private List allUserList;
	private String selectUserId;
	private String selectUserName;
	private String seteiKbn;
	private boolean initFlg;
	
	/* 表示用のユーザ一覧 */
	public void setUserList(List userList) {
		this.userList = userList;
	}
	public List getUserList() {
		return this.userList;
	}
	/**
	 * 対象ユーザー存在判断処理
	 * @return true:存在有り　false:存在無し
	 */
	public boolean isExistUser() {
		if (userList != null) {
			return this.userList.size() > 0;
		}
		return false;
	}
	/* ユーザ一覧情報 */
	public void setAllUserList( List allUserList ) {
		this.allUserList = allUserList;
	}
	public List getAllUserList() {
		return this.allUserList;
	}
	
	/* 選択されたユーザID */
	public void setSelectUserId(String selectUserId) {
		this.selectUserId = selectUserId;
	}
	public String getSelectUserId() {
		return this.selectUserId;
	}
	
	/* 選択されたユーザネーム */
	public void setSelectUserName(String selectUserName) {
		this.selectUserName = selectUserName;
	}
	public String getSelectUserName() {
		return this.selectUserName;
	}

	/* 初期化フラグ */
	public void setInitFlg(boolean initFlg) {
		this.initFlg = initFlg;
	}
	public boolean getInitFlg() {
		return this.initFlg;
	}
	
	/* 設定区分 */
	public void setSeteiKbn(String seteiKbn) {
		this.seteiKbn = seteiKbn;
	}
	public String getSeteiKbn() {
		return this.seteiKbn;
	}

	public void clear() {
		if( getUserList() != null ) {
			getUserList().clear();
		}
		if ( getAllUserList() != null ) {
			getAllUserList().clear();
		}
		setSelectUserId(null);
		setSelectUserName(null);
		setInitFlg(false);
	}
	/**
	 * @return クラス変数sessionKey を戻します。
	 */
	public String getSessionKey() {
		return sessionKey;
	}
	/**
	 * @param sessionKey を クラス変数sessionKeyへ設定します。
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	/**
	 * システム管理者か否か判断処理
	 * 
	 * @return true:システム管理者
	 */
	public boolean isSystemAdminUser() {
	    /* システム管理者設定区分 */
		return SetteiKbn.isSystemAdminUser(getSeteiKbn());

	}
	/**
	 * ユーザー管理者か否か判断処理
	 * 
	 * @return true:ユーザー管理者
	 */
	public boolean isUserAdminUser() {
	    /* ユーザー管理者設定区分 */
		return SetteiKbn.isUserAdminUser(getSeteiKbn());

	}
}
