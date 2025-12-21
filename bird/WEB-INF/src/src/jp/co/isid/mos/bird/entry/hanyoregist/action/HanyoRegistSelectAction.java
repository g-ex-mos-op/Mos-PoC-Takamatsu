/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoregist.action;

/**
 * 汎用研修マスタ登録　条件画面アクションインターフェース
 * @author itamoto
 */
public interface HanyoRegistSelectAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * プルダウン変更
     */
	public String changePullDown();

    /**
     * 新規登録
     */
    public String regist();

    /**
	 * 編集
     */
    public String edit();

    /**
	 * 削除
     */
    public String delete();
}
