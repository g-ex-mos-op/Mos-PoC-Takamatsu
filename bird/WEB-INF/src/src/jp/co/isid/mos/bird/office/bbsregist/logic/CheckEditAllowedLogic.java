/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.logic;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;

public interface CheckEditAllowedLogic {

    /**
     * 更新可能ユーザチェック
     * @param uiKeijiban 掲示板情報
     * @param user ログインユーザ
     * @return true:可能 false:不可能
     * @throws ApplicationException
     */
    public abstract boolean execute(UIKeijiban uiKeijiban, MstUser user)
            throws ApplicationException;

}