/*
 * çÏê¨ì˙: 2006/01/13
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CtlCompany;

/**
 * @author xytamura
 *
 */
public interface CtlCompanyDao {

    public static final Class BEAN = CtlCompany.class;
    public static final String getCompanyList_SQL 
    =  "SELECT "
        +  "    BC02.R_COMPANY_CD, "
        +  "    BC02.COMPANY_NAME "
        +  "FROM"
        +  "    BC02COMP AS BC02 "
        +  "WHERE "
        +  "    BC02.R_COMPANY_CD NOT IN (SELECT R_COMPANY_CD FROM BC29NCOM) "
        +  "ORDER BY "
        +  "    BC02.R_COMPANY_CD ";


    
    public List getCompanyList();
}
