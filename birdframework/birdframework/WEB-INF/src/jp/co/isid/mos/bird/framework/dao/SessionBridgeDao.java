package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlSessionBridge;

/**
 * @author xnkusama
 *
 */
public interface SessionBridgeDao {
	public Class BEAN = CtlSessionBridge.class;
    
	public static final String getUserIDbySessionKey_ARGS = "session_key";
    public static final String getUserIDbySessionKey_SQL =            
            " SELECT "
       +    "        USER_ID "
       +    " ,      SESSION_KEY "
       +    " ,      LOGIN_TMSP " 
       +    " FROM   BR07SBIF "
       +    " WHERE  SESSION_KEY = /*session_key*/ ";

    /**
     * セッションブリッジキーよりユーザーIDを取得
     * @param session_key
     * @return String ユーザーID
     */
    public List getUserIDbySessionKey(String session_key);
}
