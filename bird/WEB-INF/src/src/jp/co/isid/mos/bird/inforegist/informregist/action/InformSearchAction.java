/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.action;

/**
 * インフォメーション登録初期画面アクションインターフェース
 * @author itamoto
 */
public interface InformSearchAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 新規登録処理
     */
	public String regist();

    /**
     * 検索処理
     */
    public String search();

    /**
     * 編集（検索実行後のみ）処理
     */
    public String edit();

    /**
     * 削除（検索実行後のみ）処理
     */
    public String delete();
    /**
     * 照会(リンク)
     */
    public String view();
}
