/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.action;

/**
 * バンズ倉庫別登録　編集画面アクションインターフェース
 * @author narita
 */
public interface VansmastRegistEditAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 入力欄追加
     */
	public String insert();
	
    /**
     * 確認
     */
	public String enforcement();

    /**
     * 戻る
     */
    public String back();
}
