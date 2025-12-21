/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.action;

/**
 * 汎用研修マスタ登録　編集画面アクションインターフェース
 * @author itamoto
 */
public interface LongserviceOfferEditAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 入力欄追加
     */
	public String insert();
	
    /**
     * 登録・終了
     */
	public String regist();
	
    /**
     * スタッフ選択
     */
	public String callStaffForm();

    /**
     * 戻る
     */
    public String back();
}
