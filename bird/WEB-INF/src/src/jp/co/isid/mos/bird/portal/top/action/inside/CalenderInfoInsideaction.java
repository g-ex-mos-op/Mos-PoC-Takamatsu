/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.action.inside;

/**
 * カレンダー情報アクションインターフェース
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public interface CalenderInfoInsideaction {
    /**
     * 初期処理
     */
	public String initialize();
	/**
	 * カレンダー情報画面表示
	 * 
	 * カレンダー情報画面を表示します。
	 * @return
	 */
	public String calenderInfo();
	/**
	 * 次月処理
	 * 
	 * 表示中の翌月のカレンダー情報画面を表示します。
	 * @return
	 */
	public String nextMonth();
	/**
	 * 前月処理
	 * 
	 * 表示中の前月のカレンダー情報画面を表示します。
	 * @return
	 */
	public String lastMonth();

}
