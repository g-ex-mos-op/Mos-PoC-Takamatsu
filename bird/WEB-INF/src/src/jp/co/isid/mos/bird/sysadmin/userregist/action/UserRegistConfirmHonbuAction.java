/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action;

/**
 * 確認画面（オーナー）アクションインターフェース
 * @author itamoto
 */
public interface UserRegistConfirmHonbuAction {

    /**
     * 条件画面起動（初期処理）
     */
	public String initialize();

    /**
     * 新規登録処理
     */
	public String regist();

    /**
     * ユーザを登録してユーザロール設定画面へ（オーナー）
     */
	public String setupUserRoll();

    /**
	 * 取消処理
	 */
    public String cancel();
}
