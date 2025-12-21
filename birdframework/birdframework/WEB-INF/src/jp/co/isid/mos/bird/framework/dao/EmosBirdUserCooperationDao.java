package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlEmosBirdUserCooperation;

/**
 * e-mossles <--> BIRD ユーザー連携情報
 * @author xnkusama
 */
public interface EmosBirdUserCooperationDao {
	public Class BEAN = CtlEmosBirdUserCooperation.class;
    
	public static final String getBirdUserIdByEmosUserId_ARGS = "user_id";
    public static final String getBirdUserIdByEmosUserId_SQL =            
            " SELECT "
       +    "        USER_ID "
       +    " FROM   BR08UCIF "
       +    " WHERE  E_USER_ID = /*user_id*/ ";

    /**
     * e-mosslesユーザーIDよりBIRDユーザーIDを取得する
     * @param user_id e-mosslesユーザーID
     * @return String ユーザーID
     */
    public List getBirdUserIdByEmosUserId(String user_id);
}
