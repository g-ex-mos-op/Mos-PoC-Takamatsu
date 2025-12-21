/*
 * 作成日: 2006/02/14
 */
package jp.co.isid.mos.bird.framework.logic;


import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * 対象ユーザに紐付くユーザ一覧をユーザタイプ別に取得します
 * @author xylee
 */
public interface GetBirdUserLogic {

    public List execute(final MstUser mstUser, final List userOner,final List userMise);
}