/*
 * ì¬“ú: 2006/3/2
 *
 */
package jp.co.isid.mos.bird.commonform.ownersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.entity.CodCompany;


/**
 * ‰ïĞƒR[ƒhæ“¾Dao
 * @author itamoto
 */
public interface CodCompanyOwnerDao {

    public static final Class BEAN = CodCompany.class;

    public static final String getCompanyByCodeList_QUERY
            = "R_COMPANY_CD in /*companyCdList*/('00', '70') order by R_COMPANY_CD";

    public List getAllCompany(String userId);

    public List getCompanyByCodeList(List rCompanyCdList);
}
