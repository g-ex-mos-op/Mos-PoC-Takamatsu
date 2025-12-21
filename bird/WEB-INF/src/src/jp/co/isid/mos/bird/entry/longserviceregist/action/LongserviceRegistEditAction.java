/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceregist.action;

/**
 * 汎用研修マスタ登録　編集画面アクションインターフェース
 * @author itamoto
 */
public interface LongserviceRegistEditAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 確認
     */
	public String confirm();

    /**
     * 戻る
     */
    public String cancel();
}
