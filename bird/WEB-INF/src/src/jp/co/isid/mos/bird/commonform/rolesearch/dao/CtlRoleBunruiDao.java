/*
 * çÏê¨ì˙: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.entity.CtlRoleBunrui;

/**
 * @author xyuchida
 *
 */
public interface CtlRoleBunruiDao {

    public static final Class BEAN = CtlRoleBunrui.class;

    public static final String select_QUERY = "ORDER BY SORT_SEQ";

	public List select();
}
