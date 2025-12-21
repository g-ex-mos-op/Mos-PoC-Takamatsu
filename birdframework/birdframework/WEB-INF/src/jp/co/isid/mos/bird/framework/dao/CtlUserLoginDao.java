/*
 * 作成日: 2006/03/08
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlUserLogin;


/**
 * ユーザーログイン時間
 * 
 * @author xkinu
 */
public interface CtlUserLoginDao {

    public Class BEAN = CtlUserLogin.class;
    /** SELECT時 USER_ID */
    public static final String getUserLogin_ARGS = "id";
    public static final String getUserLogin_SQL =            
        " select USER_ID "
   +    " ,      CURRENT_LOGIN_TMSP "
   +    " ,      LAST_LOGIN_TMSP "
   +    " from   BR43ULOG "
   +    " where  USER_ID = /*id*/";
    
    /** INSERT時 USER_ID */
    public static final String insertUserLogin_ARGS = "id";
    public static final String insertUserLogin_SQL =            
        " insert into  BR43ULOG "
   +    " values (/*id*/"
   +	" ,       current timestamp "
   +    " ,       '1111-11-11-11.11.11.11111' "
   +    " ) ";
    
    /** UPDATE時 USER_ID */
    public static final String updateUserLogin_ARGS = "id";
    public static final String updateUserLogin_SQL =            
        " update BR43ULOG  "
   +    " set    CURRENT_LOGIN_TMSP = current timestamp "
   +    " ,      LAST_LOGIN_TMSP = CURRENT_LOGIN_TMSP "
   +    " where  USER_ID = /*id*/";
   
    /**
     * ログイン時間情報を取得する
     * @return 
     */   
    public List getUserLogin(String id);
    /**
     * ログイン時間情報の新規登録を行う
     */   
    public void insertUserLogin(String id);
    /**
     * ログイン時間情報の更新登録を行う
     */   
    public void updateUserLogin(String id);

}
