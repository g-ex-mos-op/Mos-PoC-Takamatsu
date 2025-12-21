/*
 * ì¬“ú: 2006/02/01
 *
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.roleacterregist.entity.UIRoleBunrui;

/**
 * 
 *
 */
public interface UIRoleBunruiDao {

	public Class BEAN = UIRoleBunrui.class;
	
	/**
     * ƒ[ƒ‹•ª—Ş‚Ìæ“¾
     * @param
     * @return
     */
	public List getRoleBunrui();
}
