/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.action;

/**
 * オーナー検索処理アクションインターフェース
 * @author itamoto
 */
public interface OwnerSearchAction {

    /**
     * オーナ検索初期処理
     */
	public String initialize();

    /**
     * 支部リスト読込処理
     */
    public String loadSibuList();

    /**
     * オーナ検索処理
     */
	public String search();

    /**
     * オーナ検索決定処理
     */
    public String select();

    /**
     * オーナ検索取消処理
     */
    public String cancel();
}
