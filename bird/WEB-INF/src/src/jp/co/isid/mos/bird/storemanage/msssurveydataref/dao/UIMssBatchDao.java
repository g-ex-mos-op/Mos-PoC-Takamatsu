/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.msssurveydataref.dao;

import jp.co.isid.mos.bird.storemanage.msssurveydataref.entity.UIMssBatch;
/**
 * 分析ネーム取得
 */
public interface UIMssBatchDao {
    
    public static final Class BEAN = UIMssBatch.class;
    
    public static final String selectJobName_ARGS = "pgmId,pgmKbn";

    public static final String updateMssBatch_ARGS = "pgmId,pgmKbn,statKbn,errCd";

    
    /**
     * バッチステータス検索
     * @param pgmId,pgmKbn
     * @return UIMssBatch
     */
    public UIMssBatch selectJobName(String[] pmgId,String pgmKbn);

    public void updateMssBatch(String pmgId,String pgmKbn,String statKbn,String errCd);
    

}