package jp.co.isid.mos.bird.bizreport.camprank.dao;

import jp.co.isid.mos.bird.bizreport.camprank.entity.CampRankTenpoCount;



/**
 * キャンペーンベスト１００対象店舗数Dao
 * @author xnkusama
 *
 */
public interface CampRankTenpoCountDao {

    public static final Class BEAN = CampRankTenpoCount.class;
    public static final String getTaishoTenpoCount_ARGS = "campId, fromDt, toDt";
    
    /**
     * キャンペーン対象店舗数の取得
     * @param campId
     * @return
     */
    public CampRankTenpoCount getTaishoTenpoCount(String campId, String fromDt, String toDt);
    

}