package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;

import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.MstMatchStateInfo;


/**
 * バッチ処理ステータス取得DAO
 * @author Aspac
 *
 */
public interface MstBatchStateDao {

    public static final Class BEAN = MstMatchStateInfo.class;
    
    public static final String getState_ARGS = "pgmId, pgmKbn";
    
    
    /**
     * 更新処理
     * @param 
     * @return
     */
    public MstMatchStateInfo getState(String pgmId, String pgmKbn);
    
}

