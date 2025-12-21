/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action;

/**
 * 初期条件画面アクションインターフェース
 * @author itamoto
 */
public interface UserRegistSelectUserTypeAction {

    /**
     * 条件画面起動（初期処理）
     */
	public String initialize();

    /**
     * 新規登録処理
     */
	public String regist();
}
