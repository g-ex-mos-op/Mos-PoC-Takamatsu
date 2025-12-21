package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;


import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.TrnNBUPInfo;


/**
 * ’lˆøC³DAO
 * @author Aspac
 *
 */
public interface TrnNBUPDao {

    public static final Class BEAN = TrnNBUPInfo.class;
     
    public static final String update_NO_PERSISTENT_PROPS = "firstUser, firstPgm, firstTmsp";
    
    /**
     * ’Ç‰Áˆ—
     * @param trnNBUPInfo
     * @return
     */
    public int insert(TrnNBUPInfo trnNBUPInfo);
    
    
    /**
     * XVˆ—
     * @param trnNBUPInfo
     * @return
     */
    public int update(TrnNBUPInfo trnNBUPInfo);
    
}

