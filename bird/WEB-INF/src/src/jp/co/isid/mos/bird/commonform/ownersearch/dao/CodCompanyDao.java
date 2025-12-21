/*
 * ì¬“ú: 2006/03/07
 *
 */
package jp.co.isid.mos.bird.commonform.ownersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.entity.CodCompany;


/**
 * ‰ïĞƒŠƒXƒgDaoî•ñ
 * 
 * @author xkinu
 * 
 */
public interface CodCompanyDao {

    public static final Class BEAN = CodCompany.class;

    public List getCompanyAll(String userId);
    
    /**
     * 
     * @param rCompanyCdList
     * @return
     */
    public List getCompanyByCodeList(List rCompanyCdList);
}
