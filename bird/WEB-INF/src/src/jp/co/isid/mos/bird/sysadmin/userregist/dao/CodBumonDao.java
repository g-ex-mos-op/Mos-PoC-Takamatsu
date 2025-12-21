/*
 * ì¬“ú: 2006/1/12
 */
package jp.co.isid.mos.bird.sysadmin.userregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodBumon;

/**
 * •”–åƒŠƒXƒgDao
 * @author m.onodera
 */
public interface CodBumonDao {

    public static final Class BEAN = CodBumon.class;
    public static final String select_ARGS = "R_COMPANY_CD";

    public List select(String companyCd);
}
