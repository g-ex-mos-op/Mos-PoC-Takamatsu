/*
 * 作成日: 2006/4/18
 */
package jp.co.isid.mos.bird.entry.longserviceregist.action;

/**
 * 汎用研修マスタ登録　確認画面アクションインターフェース
 * @author itamoto
 */
public interface LongserviceRegistConfirmAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 登録
     */
	public String regist();

    /**
     * 戻る
     */
    public String cancel();
}
