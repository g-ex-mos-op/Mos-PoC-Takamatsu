/*/
 * 作成日: 2008/10/27
 *
 */
package jp.co.isid.mos.bird.togouser.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.togouser.common.entiy.CtlTogoUserMenu;


/**
 * ログイン画面用BIRDユーザー情報DAO
 * @author S.yamauchi
 */
public interface UITogoUserMenuDao {

    public Class BEAN = CtlTogoUserMenu.class;

    public static final String getMenuInfo_ARGS = "";
    
    /**
     * ユーザー情報の取得
     * @param userId
     * @return
     */
    public List getMenuInfo();
    
}