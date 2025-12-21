package jp.co.isid.mos.bird.bizreport.camprank.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.camprank.entity.UICampRankData;


/**
 * キャンペーンデータDao
 * @author xnkusama
 *
 */
public interface UICampRankDataDao {

    public static final Class BEAN = UICampRankData.class;
    
    public static final String getCampaignData_ARGS = "companyCd, campId, kikanFrom, kikanTo, rankMode";
    public static final String getCampaignDataFromTotal_ARGS = "companyCd, campId, kikanFrom, kikanTo, rankMode";
    public static final String getCampaignDataKikan_ARGS = "companyCd, campId, kikanFrom, kikanTo, kikanFromZen, rankMode";
    
    /**
     * キャンペーン情報の取得（対象日タブ用）
     * @param companyCd
     * @param campId
     * @param kikanFrom
     * @param kikanTo
     * @param rankMode
     * @return
     */
    public List getCampaignData
            (String companyCd, String campId, String kikanFrom, String kikanTo, String rankMode);
    
    /**
     * キャンペーン情報の取得
     * 　期間タブかつ直近日以外用
     * @param companyCd
     * @param campId
     * @param kikanFrom
     * @param kikanTo
     * @param kikanFromZen BD03から累計を取得ときのFrom日
     * @param rankMode
     * @return
     */
    public List getCampaignDataKikan
            (String companyCd, String campId, String kikanFrom, String kikanTo, String kikanFromZen, String rankMode);
    /**
     * 直近日指定データ取得
     * @param companyCd
     * @param campId
     * @param kikanFrom
     * @param kikanTo
     * @param rankMode
     * @return
     */
    public List getCampaignDataFromTotal
            (String companyCd, String campId, String kikanFrom, String kikanTo, String rankMode);

}