/*
 * 作成日: 2006/3/14
 */
package jp.co.isid.mos.bird.bizsupport.plregist.action;

/**
 * P/Lデータ入力　条件画面アクションインターフェース
 * @author itamoto
 */
public interface PlRegistSelectAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 検索
     */
	public String search();

    /**
     * 実行
     */
    public String execute();

    /**
     * 登録状況確認画面へ遷移する。
     */
    public String executeStateConfirm();
    
    
    /**
	 * 条件画面表示モード切替
     */
    public String changeSelectViewMode();
}
