/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action;

/**
 * テナント入金明細PDF登録
 * 初期画面アクションインターフェース
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface ConditionAction {
    /**
     * 初期化処理
     */
    public String initialize();
    /**
     * 店舗選択フォーム呼び出し処理
     * 
     * １．画面遷移区分に現行画面区分を設定
     * 　　（エラー等の次画面遷移時に処理を実行する上での判断材料の区分になります。）
     * @return 店検索フォームViewID
     */
    public String callMiseForm();
    /**
     * 実行or再検索
     * @return
     */
    public String search();
	/**
	 * 新規登録画面呼び出し処理
	 */
	public String callInsertForm();
	/**
	 * 更新登録画面呼び出し処理
	 */
	public String callUpdateForm();
	/**
	 * 削除登録画面呼び出し処理
	 */
	public String callDeleteForm();

}
