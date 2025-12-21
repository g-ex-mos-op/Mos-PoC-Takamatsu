/*
 * 作成日: 2006/3/27
 */
package jp.co.isid.mos.bird.bizsupport.plcontentconfirm.action;

/**
 * P/Lデータ内容確認　条件画面アクションインターフェース
 * @author itamoto
 */
public interface PlContentFormAction {

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
	 * 条件画面表示モード切替
     */
    public String changeSelectViewMode();

    /**
     * 登録状況確認画面へ遷移する。
     * @return
     */
    public String executeStateConfirm();
}
