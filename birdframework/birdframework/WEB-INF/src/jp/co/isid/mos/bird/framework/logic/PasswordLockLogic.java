/*
 * 作成日: 2008/10/29
 */
package jp.co.isid.mos.bird.framework.logic;


/**
 * ユーザー認証に失敗した回数の更新を行います。
 * @author K.Nihonyanagi
 */
public interface PasswordLockLogic {
    /*登録区分*/
    public static final String LOGIN_SUCCESS = "0";
    public static final String LOGIN_FAILURE = "1";
    
    public void execute(final String userId, final String torokuKbn, String gamenId);
}
