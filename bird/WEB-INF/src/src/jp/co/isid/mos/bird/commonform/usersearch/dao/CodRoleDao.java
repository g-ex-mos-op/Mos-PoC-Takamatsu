/*
 * ì¬“ú: 2006/1/12
 */
package jp.co.isid.mos.bird.commonform.usersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.entity.CodRole;

/**
 * •”–åƒŠƒXƒgDao
 * @author m.onodera
 */
public interface CodRoleDao {

    public static final Class BEAN = CodRole.class;

    public List select();
}
