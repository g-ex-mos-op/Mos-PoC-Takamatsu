/*
 * ì¬“ú: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.sysadmin.userregist.dao;

import jp.co.isid.mos.bird.sysadmin.userregist.entity.CtlUserRole;

/**
 * ƒ[ƒ‹î•ñDAO
 * @author xylee
 */
public interface CtlUserRoleDao {

    public Class BEAN = CtlUserRole.class;
    public static final String insertUserRole_ARGS = "entity";
    
    /**
     * ‘I‘ğƒ[ƒ‹î•ñ‚Ì‘}“ü
     * @param entity
     * @return
     */
    public int insertUserRole(CtlUserRole entity);
    
}
