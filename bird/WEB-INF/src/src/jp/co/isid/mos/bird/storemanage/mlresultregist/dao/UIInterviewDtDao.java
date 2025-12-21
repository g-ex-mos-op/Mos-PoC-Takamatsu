package jp.co.isid.mos.bird.storemanage.mlresultregist.dao;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UIInterviewDt;


/**
 * –ÊÚ“ú
 * @author kusama
 */
public interface UIInterviewDtDao {

    public static final Class BEAN = UIInterviewDt.class;

    public static final String insertInterviewDt_ARGS = "entity";
    public static final String updateInterviewDt_ARGS = "entity";
    
    /**
     * –ÊÚ“ú‚Ì“o˜^
     */
    public void insertInterviewDt(UIInterviewDt entity);
    
    /**
     * –ÊÚ“ú‚ÌXV
     * @param 
     * @return List
     */
    public int updateInterviewDt(UIInterviewDt entity);
    
}