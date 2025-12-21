/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampMise;

/**
 * @author xnkusama
 *
 */
public interface MstCampMiseDao {

    public static final Class BEAN = MstCampMise.class;

    public static final String deleteCampaignMise_SQL
        = "delete from bm64csms where camp_id = /*campId*/'200806'";

    /**
     * 設定データの取得
     * @param campId
     * @return
     */
    public List getMiseList(String campId);
    
    /**
     * キャンペーン店舗設定の新規登録
     * @param entity
     * @return
     */
    public int insertCampaignMise(MstCampMise entity);
    
    /**
     * キャンペーン店舗設定の削除
     * @param campId
     * @return
     */
    public int deleteCampaignMise(String campId);
}