/*/
 * 作成日: 2008/10/27
 *
 */
package jp.co.isid.mos.bird.togouser.login.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.login.entity.UITogoUser;


/**
 * ログイン画面用BIRDユーザー情報DAO
 * @author K.Nihonyanagi
 */
public interface UITogoUserDao {

    public Class BEAN = UITogoUser.class;

    public static final String getUserInfo_ARGS = "userId";
    
    /**
     * ユーザー情報の取得
     * @param userId
     * @return
     */
    public List getUserInfo(String userId);
    
}
