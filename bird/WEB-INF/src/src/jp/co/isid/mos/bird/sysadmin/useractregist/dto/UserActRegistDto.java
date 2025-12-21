/*
 * 作成日: 2006/02/24
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.dto;

import java.util.List;

/**
 * @author xkhata
 */
public class UserActRegistDto {
	private String sessionKey;
	private List roleList;
	private String userId;
	private String userName;
	
	/* ロールリスト情報 */
	public void setRoleList( List roleList ) {
		this.roleList = roleList;
	}
	public List getRoleList() {
		return this.roleList;
	}
	
	/* ユーザ情報 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return this.userName;
	}
	
	public void clear() {
		if ( getRoleList() != null) {
			getRoleList().clear();	
		}
		setUserId(null);
		setUserName(null);
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
}
