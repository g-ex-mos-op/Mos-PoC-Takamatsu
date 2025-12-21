/*
 * 作成日: 2006/02/13
 *
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.action;

/**
 * ロール別アクセス権限設定アクションインターフェース
 * 
 * 作成日:2009/01/30
 * @author xkinu
 *
 */
public interface RoleActRegistAction {
	/**
	 * 初期化処理
	 * @return
	 */
	public String initialize();
	/**
	 * 戻る
	 * @return null
	 */
	public String back();
	/**
	 * 更新
	 * @return null
	 */
	public String update();
	/**
	 * タブの変更
	 * @return null
	 */
	public String changeTab();
}
