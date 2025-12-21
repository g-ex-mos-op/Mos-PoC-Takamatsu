/*
 * çÏê¨ì˙: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIUserRole;

/**
 * @author xyuchida
 *
 */
public interface UIUserRoleDao {

    public static final Class BEAN = UIUserRole.class;

	public List select(String roleCd);
}
