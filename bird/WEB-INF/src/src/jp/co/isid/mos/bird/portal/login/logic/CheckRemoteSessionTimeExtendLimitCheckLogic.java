/*
 * 作成日: 2018/08/17
 */
package jp.co.isid.mos.bird.portal.login.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * 社外アクセスチェック
 * @author xnkusama
 */
public interface CheckRemoteSessionTimeExtendLimitCheckLogic {
    /**
     * 社外からアクセスのアクセスチェック
     * @param request
     * @return
     */
    public boolean checkAllow(HttpServletRequest request);
}
