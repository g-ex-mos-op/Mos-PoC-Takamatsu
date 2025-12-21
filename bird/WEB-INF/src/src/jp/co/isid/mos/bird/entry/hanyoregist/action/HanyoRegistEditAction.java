/*
 * 作成日: 2006/4/18
 */
package jp.co.isid.mos.bird.entry.hanyoregist.action;

/**
 * 汎用研修マスタ登録　編集画面アクションインターフェース
 * @author itamoto
 */
public interface HanyoRegistEditAction {

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
