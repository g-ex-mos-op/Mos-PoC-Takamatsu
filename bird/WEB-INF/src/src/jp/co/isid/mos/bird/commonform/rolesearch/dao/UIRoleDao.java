/*
 * çÏê¨ì˙: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.entity.UIRole;

/**
 * @author xyuchida
 *
 */
public interface UIRoleDao {

    public static final Class BEAN = UIRole.class;

	public List select(String bunruiCd);

	public List selectByRoleCd(List roleCds);
}
