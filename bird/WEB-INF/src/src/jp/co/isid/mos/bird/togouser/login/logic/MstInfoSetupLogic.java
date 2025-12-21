/*
 * 作成日: 2008/10/29
 */
package jp.co.isid.mos.bird.togouser.login.logic;

import jp.co.isid.mos.bird.togouser.login.entity.UITogoUser;


/**
 * ユーザー関連マスタ情報を取得しセッションに格納する。
 * @author K.Nihonyanagi
 */
public interface MstInfoSetupLogic {
    public UITogoUser execute(final String userId);
}
