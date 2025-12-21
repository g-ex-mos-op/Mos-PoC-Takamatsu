/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action;

/**
 * 登録画面（オーナー）アクションインターフェース
 * @author itamoto
 */
public interface UserRegistEditHonbuAction {

    /**
     * 条件画面起動（初期処理）
     */
	public String initialize();

    /**
     * 新規登録処理
     */
	public String regist();

    /**
     * 取消処理
     */
    public String cancel();

    /**
     * オーナ選択起動処理
     */
    public String searchOner();

    /**
     * 業態選択起動処理
     */
    public String searchGyotai();

    /**
     * 業態クリア処理
     */
    public String clearGyotai();
}
