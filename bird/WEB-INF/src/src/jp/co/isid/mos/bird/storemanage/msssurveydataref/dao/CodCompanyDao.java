/*
 * ì¬“ú: 2006/08/03
 */
package jp.co.isid.mos.bird.storemanage.msssurveydataref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.CodCompany;



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
