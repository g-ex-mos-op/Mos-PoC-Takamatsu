/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.action;

/**
 * 通達登録 条件画面アクション インターフェイス
 * @author m.onodera
 */
public interface ConditionAction {

    /**
     * 初期処理
     * @return
     */
	public String initialize();
    
    /**
     * 通達一覧検索処理
     * @return
     */
    public String search();

    /**
     * 削除ボタンアクション
     * @return
     */
    public String goDelete();
    
    /**
     * 変更ボタンアクション  
     *    編集画面へ遷移
     * @return
     */
    public String goRegist();
    
    /**
     * ページ切替アクション
     * @return
     */
    public String changePage();

    /**
     * 新規登録アクション
     * @return
     */
    public String goInsert();
}
