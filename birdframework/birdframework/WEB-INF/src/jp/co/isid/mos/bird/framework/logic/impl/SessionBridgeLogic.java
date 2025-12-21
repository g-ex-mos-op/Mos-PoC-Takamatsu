/*
 * 作成日: 2005/12/16
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.dao.SessionBridgeDao;
import jp.co.isid.mos.bird.framework.entity.CtlSessionBridge;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * セッションブリッジキーよりe-mosslesのユーザーIDを取得
 * @author xnkusama
 */
public class SessionBridgeLogic {

    public String getUserId(final String sessionKey) {
        String userID = "";
        
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        SessionBridgeDao sessionDao = (SessionBridgeDao) s2Con.getComponent(SessionBridgeDao.class);
        
        List sessionInfo = sessionDao.getUserIDbySessionKey(sessionKey);
        
        if (sessionInfo != null && sessionInfo.size() > 0) {
            userID = ((CtlSessionBridge) sessionInfo.get(0)).getUserId();
        }
        
        return userID;
    }
}
