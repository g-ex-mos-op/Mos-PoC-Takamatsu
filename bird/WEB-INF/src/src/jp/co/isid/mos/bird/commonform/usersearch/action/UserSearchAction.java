/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.action;

/**
 * ユーザー検索処理アクションインターフェース
 * @author m.onodera
 */
public interface UserSearchAction {

    /**
     * ユーザー検索初期処理
     */
	public String initialize();

	/**
     * 部門リスト読込処理
     */
    public String loadBumonList();

    /**
     * ロールリスト読込処理
     */
    public String loadRoleList();

    
	/**
     * ユーザー検索処理
     */
	public String search();

    /**
     * ユーザー検索決定処理
     */
    public String select();
    
    /**
     * ユーザー検索取消（戻る）処理
     */
    public String cancel();

    /**
     * ユーザー検索クリアー処理
     */
    public String listClear();

    /**
     * ページ切り替え
     */
    public String changePage();
}
