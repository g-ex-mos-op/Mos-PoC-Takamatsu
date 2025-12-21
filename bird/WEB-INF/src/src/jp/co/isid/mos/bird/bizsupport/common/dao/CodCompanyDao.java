/*
 * ì¬“ú: 2006/11/30
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.CodCompany;



/**
 * ŠÇ—‰ïĞî•ñæ“¾
 * 
 * @author inazawa
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
