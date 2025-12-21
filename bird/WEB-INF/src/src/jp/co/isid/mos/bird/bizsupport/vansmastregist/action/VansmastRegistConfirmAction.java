/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.action;

/**
 * バンズ倉庫別登録　確認画面アクションインターフェース
 * @author narita
 */
public interface VansmastRegistConfirmAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 戻る
     */
    public String back();
    
    /**
     * 登録
     */
	public String regist();
}
