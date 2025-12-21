/*
 * 作成日: 2006/4/17
 */
package jp.co.isid.mos.bird.entry.longserviceregist.action;

/**
 * 汎用研修マスタ登録　条件画面アクションインターフェース
 * @author itamoto
 */
public interface LongserviceRegistSelectAction {

    /**
     * 初期処理 
     * @return 画面遷移情報
     */
    public String initialize();

    /**
     * 新規登録
     * @return 画面遷移情報
     */
    public String insert();

    /**
     * 編集 
     * @return 画面遷移情報
     */
    public String update();

    /**
     * 削除
     * @return 画面遷移情報
     */
    public String delete();
}
