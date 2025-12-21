/*
 * ì¬“ú: 2006/03/07
 *
 */
package jp.co.isid.mos.bird.commonform.usersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.entity.CodCompany;


/**
 * ‰ïĞƒŠƒXƒgDaoî•ñ
 * 
 * @author xkinu
 * 
 */
public interface CodCompanyDao {

    public static final Class BEAN = CodCompany.class;

    public static final String getCompanyByCodeList_QUERY = "R_COMPANY_CD in /*companyCdList*/('00', '70') order by R_COMPANY_CD";
    
    public List getCompanyAll();
    
    /**
     * 
     * @param rCompanyCdList
     * @return
     */
    public List getCompanyByCodeList(List rCompanyCdList);
}
