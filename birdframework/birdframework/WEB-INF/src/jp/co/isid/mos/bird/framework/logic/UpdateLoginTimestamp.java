/*
 * 作成日: 2006/03/07
 */
package jp.co.isid.mos.bird.framework.logic;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.CtlUserLogin;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ログイン時間の更新と前回ログイン時間の取得
 * @author xkinu
 */
public interface UpdateLoginTimestamp {
    /* ログイン */
    public CtlUserLogin registLoginTimes(final BirdUserInfo birdUserInfo) throws ApplicationException;
}
