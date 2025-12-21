/*
 * ì¬“ú: 2006/04/14
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.CodCompany;

/**
 * ŠÇ—‰ïĞî•ñæ“¾
 * 
 * @author xkinu
 */
public interface CodCompanyDao {

    public static final Class BEAN = CodCompany.class;
    public static final String select_ARGS = "userId";

    /**
     * ŠÇ—‰ïĞŠé‹Æî•ñ‚Ìæ“¾
     * @param String userId ƒ†[ƒU[ID
     * @return List
     */
    public List select(String userId);
    
}            
