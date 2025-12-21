package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.CtlSessionBridge;

/**
 * @author xnkusama
 *
 */
public interface SessionListenerDao {
    public Class BEAN = CtlSessionBridge.class;
    
    public static final String delete_ARGS = "session_key";
    public static final String delete_SQL =            
            " DELETE "
       +    " FROM   BR07SBIF "
       +    " WHERE  SESSION_KEY = /*session_key*/ ";

    /**
     * 指定のセッションブリッジキーを削除
     * @param session_key
     */
    public void delete(String session_key);
}
