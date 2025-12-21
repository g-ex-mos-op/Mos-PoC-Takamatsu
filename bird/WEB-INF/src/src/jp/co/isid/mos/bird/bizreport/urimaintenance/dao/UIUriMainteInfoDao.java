package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteInfo;


/**
 * åªã‡ç›çÇDAO
 * @author Aspac
 *
 */
public interface UIUriMainteInfoDao {

    public static final Class BEAN = UIUriMainteInfo.class;
   
    public static final String getUriMainteInfo_ARGS = "companyCd, miseCd, targetYM";
    
    /**
     * åªã‡ç›çÇéÊìæ
     * @param companyCd
     * @param miseCd
     * @param targetYM
     * @return åªã‡ç›çÇ
     */
    public List getUriMainteInfo(String companyCd, String miseCd, String targetYM);

    
}

