package jp.co.isid.mos.bird.commonform.svsearchnew.dao;


import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearchnew.entity.CodCompany;



/**
 * ‰ïĞƒŠƒXƒgDaoî•ñ
 * 
 * @author kusama
 */
public interface CodCompanyDao {

    public static final Class BEAN = CodCompany.class;
    /**
     * 
     * @param userId
     * @return
     */
    public List getCompanyAll(String userId);
    
    /**
     * 
     * @param rCompanyCdList
     * @return
     */
    public List getCompanyByCodeList(List rCompanyCdList);
}
