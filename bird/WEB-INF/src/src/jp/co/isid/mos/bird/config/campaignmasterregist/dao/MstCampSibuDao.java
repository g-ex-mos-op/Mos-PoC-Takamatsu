/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampSibu;

/**
 * @author xnkusama
 *
 */
public interface MstCampSibuDao {

    public static final Class BEAN = MstCampSibu.class;

    public static final String deleteCampaignSibu_SQL
        = "delete from bm63cssb where camp_id = /*campId*/'200806'";

    /**
     * 設定データの取得
     * @param campId
     * @return
     */
    public List getSibuList(String campId);
    
    /**
     * キャンペーン支部設定の新規登録
     * @param entity
     * @return
     */
    public int insertCampaignSibu(MstCampSibu entity);
    
    /**
     * キャンペーン支部設定の削除
     * @param campId
     * @return
     */
    public int deleteCampaignSibu(String campId);
}