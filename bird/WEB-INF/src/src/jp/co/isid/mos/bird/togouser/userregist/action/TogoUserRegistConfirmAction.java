/*
 * 作成日: 2008/11/06
 */
package jp.co.isid.mos.bird.togouser.userregist.action;

/**
 * 確認画面（オーナー）アクションインターフェース
 * @author K.Nihonyanagi
 */
public interface TogoUserRegistConfirmAction {
    public String initialize();
    /**
     * 新規登録処理
     */
	public String regist();

     /**
	 * 取消処理
	 */
    public String cancel();
}
