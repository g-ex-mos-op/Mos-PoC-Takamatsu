/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.action;

/**
 * 汎用研修マスタ登録　条件画面アクションインターフェース
 * @author itamoto
 */
public interface HanyoApplicationSelectAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * プルダウン変更
     */
	public String changePullDown();
    
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