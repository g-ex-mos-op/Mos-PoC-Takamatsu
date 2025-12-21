package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;


/**
 * Œ»‹àİ‚“úŸC³DAO
 * @author Aspac
 *
 */
public interface UIUriMainteWorkInfoDao {

    public static final Class BEAN = UIUriMainteWorkInfo.class;
   
    public static final String getUriMainteWorkInfo_ARGS = "companyCd, miseCd, targetYM, sysdate";
    
    /**
     * Œ»‹àİ‚“úŸC³æ“¾
     * @param companyCd
     * @param miseCd
     * @param targetYM
     * @param sysdate
     * @return Œ»‹àİ‚
     */
    public List getUriMainteWorkInfo(String companyCd, String miseCd, String targetYM, String sysdate);

    
}

