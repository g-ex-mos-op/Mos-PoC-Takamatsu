/*
 * 作成日: 2008/10/29
 */
package jp.co.isid.mos.bird.togouser.login.logic;


/**
 * ユーザー認証に失敗した回数の更新を行います。
 * @author K.Nihonyanagi
 */
public interface UserInfoSetupLogic {
    public void execute(final String userId, final String torokuKbn);
}
