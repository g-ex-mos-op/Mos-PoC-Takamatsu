/*
 * 作成日: 009/02/19
 */
package jp.co.isid.mos.bird.sysadmin.useractregist.logic;


import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * 対象ユーザに紐付くユーザ一覧をユーザタイプ別に取得します
 * @see jp.co.isid.mos.bird.framework.logic.GetBirdUserLogic
 * @author xnkusama
 */
public interface GetBirdUserLogic {

    public List execute(final MstUser mstUser, final List userOner,final List userMise);
}