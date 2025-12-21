/*
 * 作成日: 2006/4/18
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.action;

/**
 * 汎用研修マスタ登録　確認画面アクションインターフェース
 * @author itamoto
 */
public interface LongserviceOfferConfirmAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 戻る
     */
    public String cancel();
    
    /**
     * 終了
     */
	public String end();
}
