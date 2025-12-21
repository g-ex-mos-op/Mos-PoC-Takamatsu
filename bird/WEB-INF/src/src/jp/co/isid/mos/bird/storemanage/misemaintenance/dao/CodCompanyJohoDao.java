/*
 * ì¬“ú: 2006/02/17
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodCompanyJoho;

/**
 * ŠÇ—‰ïĞî•ñæ“¾
 * @author kusama
 */
public interface CodCompanyJohoDao {

    public static final Class BEAN = CodCompanyJoho.class;
    public static final String select_ARGS = "userId";

    /**
     * ŠÇ—‰ïĞŠé‹Æî•ñ‚Ìæ“¾
     * @param String userId ƒ†[ƒU[ID
     * @return List
     */
    public List select(String userId);
    
}            
