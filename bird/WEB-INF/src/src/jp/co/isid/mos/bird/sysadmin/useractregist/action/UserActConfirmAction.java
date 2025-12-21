/**
 * 
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.action;

/**
 * ユーザー別アクセス権限設定（確認画面）アクションインターフェース
 * 
 * 作成日:2009/12/16
 * @author xkinu
 *
 */
public interface UserActConfirmAction {
	/**
	 * 初期化アクション
	 * @return
	 */
	public String initialize();
	/**
	 * 戻るアクション
	 * @return
	 */
	public String back();
	/**
	 * 更新アクション
	 * @return
	 */
	public String regist();
}
