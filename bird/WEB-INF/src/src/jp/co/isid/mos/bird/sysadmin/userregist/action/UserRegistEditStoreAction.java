/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action;

/**
 * 登録画面（店舗）アクションインターフェース
 * @author itamoto
 */
public interface UserRegistEditStoreAction {

    /**
     * 条件画面起動（初期処理）
     */
	public String initialize();

    /**
     * 登録ボタン処理
     */
	public String regist();

    /**
     * 取消処理
     */
    public String cancel();

    /**
     * 店選択起動処理
     */
    public String searchMise();
}
