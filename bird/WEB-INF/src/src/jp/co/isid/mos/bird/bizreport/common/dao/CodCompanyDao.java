/*
 * ì¬“ú: 2006/04/14
 */
package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;


/**
 * ŠÇ—‰ïĞî•ñæ“¾
 * 
 * @author xkinu
 */
public interface CodCompanyDao {

    public static final Class BEAN = CodCompany.class;
    public static final String select_ARGS = "birdUserInfo, isForeignIn";

    /**
     * ŠÇ—‰ïĞŠé‹Æî•ñ‚Ìæ“¾
     * 
     * @param birdUserInfo
     * @param isForeignIn
     * @return
     */
    public List select(BirdUserInfo birdUserInfo, boolean isForeignIn);
}            
