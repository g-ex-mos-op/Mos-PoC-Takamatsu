package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;


import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.TrnADUPInfo;


/**
 * Œ»‹àİ‚“úŸC³DAO
 * @author Aspac
 *
 */
public interface TrnADUPDao {

    public static final Class BEAN = TrnADUPInfo.class;

    public static final String update_NO_PERSISTENT_PROPS = "firstUser, firstPgm, firstTmsp";

    /**
     * ’Ç‰Áˆ—
     * @param trnADUPInfo
     * @return
     */
    public int insert(TrnADUPInfo trnADUPInfo);
    
    
    /**
     * XVˆ—
     * @param trnADUPInfo
     * @return
     */
    public int update(TrnADUPInfo trnADUPInfo);
    
}

