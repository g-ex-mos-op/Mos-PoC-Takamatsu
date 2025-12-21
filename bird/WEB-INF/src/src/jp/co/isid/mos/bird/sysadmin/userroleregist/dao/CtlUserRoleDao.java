/*
 * 作成日: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.dao;

import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.CtlUserRole;

/**
 * ロール情報DAO
 * @author xylee
 */
public interface CtlUserRoleDao {

    public Class BEAN = CtlUserRole.class;
    public static final String deleteUserRole_ARGS = "userId";
    public static final String insertUserRole_ARGS = "entity";
    /**
     * 
     * ロール情報の削除
     * @param userId
     * @return
     */
    public int deleteUserRole(String userId);
    
    /**
     * 選択ロール情報の挿入
     * @param entity
     * @return
     */
    public int insertUserRole(CtlUserRole entity);
    
}
