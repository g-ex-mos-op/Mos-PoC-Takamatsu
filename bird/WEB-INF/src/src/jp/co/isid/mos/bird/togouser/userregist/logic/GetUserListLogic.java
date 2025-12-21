/*
 * 作成日: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.userregist.logic;

import java.util.List;

/**
 * 部門リスト取得インターフェース
 * @author K.Nihonyanagi
 */
public interface GetUserListLogic {

    public List execute(String userId);
}
