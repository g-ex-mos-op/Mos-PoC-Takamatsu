/**
 * 
 */
package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.UIUserEmosslesAppId;

/**
 * ログインユーザ e-mosslesサイト区分Dao
 * 
 * 作成日:2008/11/10
 * @author xkinu
 *
 */
public interface UIUserEmosslesAppIdDao {
    public Class BEAN = UIUserEmosslesAppId.class;

    public static final String select_ARGS = "userId";
    
    /**
     * 指定ユーザーのe-mosslesサイト区分取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public UIUserEmosslesAppId select(String userId);


}
