/**
 * 
 */
package jp.co.isid.mos.bird.common.action;

/**
 * ヘッダー部アクションインターフェース
 * 
 * 作成日:2008/11/28
 * @author xkinu
 *
 */
public interface HeaderAction {
	/**
	 * 総合検索処理
	 * @return 各検索結果表示画面VIEW_ID
	 */
	public String callSearch();
}
