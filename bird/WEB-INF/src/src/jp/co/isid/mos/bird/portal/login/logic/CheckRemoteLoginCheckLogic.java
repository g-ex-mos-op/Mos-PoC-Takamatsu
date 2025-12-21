/*
 * 作成日: 2009/02/12
 */
package jp.co.isid.mos.bird.portal.login.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * 社外アクセスチェック
 * @author xnkusama
 */
public interface CheckRemoteLoginCheckLogic {
    /**
     * 社外からアクセスのアクセスチェック
     * @param userId
     * @param request
     * @return
     */
    public boolean checkRemote(String userId, HttpServletRequest request);
}
