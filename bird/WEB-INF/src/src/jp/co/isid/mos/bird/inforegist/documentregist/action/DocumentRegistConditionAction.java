/*
 * 作成日: 2006/01/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.action;

/**
 * 文書登録 条件画面アクション インターフェイス
 * @author xnkusama
 */
public interface DocumentRegistConditionAction {

    /**
     * 初期処理
     * @return
     */
	public String initialize();
    
    /**
     * 文書一覧検索処理
     * @return
     */
    public String search();

    /**
     * タブ変更処理
     * @return
     */
    public String changeTab();
    
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
     * 順位設定アクション
     * @return
     */
    public String registRank();
    
    /**
     * 新規登録アクション
     * @return
     */
    public String goInsert();
}
