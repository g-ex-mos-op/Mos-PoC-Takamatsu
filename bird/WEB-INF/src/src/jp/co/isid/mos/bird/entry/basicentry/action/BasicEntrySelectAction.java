/*
 * 作成日: 2006/06/16
 */
package jp.co.isid.mos.bird.entry.basicentry.action;

/**
 * ベーシック研修参加申込　条件画面アクションインターフェース
 * @author itamoto
 */
public interface BasicEntrySelectAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 実行
     */
    public String regist();

    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm();
}