/*
 * 作成日: 2006/01/11
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlUserRole;


/**
 * ユーザーロール
 * @author xnkusama
 */
public interface CtlUserRoleDao {

    public Class BEAN = CtlUserRole.class;

    public static final String getUserRoleList_ARGS = "userId";
    
    /**
     * 指定ユーザーのロール一覧取得
     * @param user_id ユーザＩＤ
     * @return 
     */   
    public List getUserRoleList(String userId);

}
