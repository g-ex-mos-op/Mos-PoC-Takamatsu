/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.action;

/**
 * 機能選択処理アクションインターフェース
 * @author itamoto
 */
public interface FunctionSearchAction {

    /**
     * 機能選択初期処理
     */
	public String initialize();

    /**
     * 機能検索処理
     */
	public String search();

    /**
     * 機能選択決定処理
     */
    public String select();

    /**
     * 機能選択取消処理
     */
    public String cancel();
}
