/*
 * 作成日: 2006/3/14
 */
package jp.co.isid.mos.bird.bizsupport.plcontentconfirm.action;

/**
 * P/Lデータ入力　編集画面アクションインターフェース
 * @author itamoto
 */
public interface PlContentConfirmAction {

    /**
     * 編集画面起動（初期処理）
     */
	public String initialize();

    /**
     * タブ切り替え
     */
//	public String changeTab();

    /**
     * 実行
     */
//    public String execute();

    /**
     * 戻る
     */
    public String cancel();
    
    
    /**
     * 次へ
     */
    public String next();
}
