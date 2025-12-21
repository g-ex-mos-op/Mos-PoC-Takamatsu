/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampMenu;

/**
 * @author xnkusama
 *
 */
public interface MstCampMenuDao {

    public static final Class BEAN = MstCampMenu.class;

    public static final String deleteCampaignMenu_SQL
        = "delete from bm61cpmn where camp_id = /*campId*/'200806'";

    /**
     * キャンペーン対象メニューマスタの新規登録
     * @param entity
     * @return
     */
    public int insertCampaignMenu(MstCampMenu entity);
    
    /**
     * キャンペーン対象メニューマスタの削除
     * @param campId
     * @return
     */
    public int deleteCampaignMenu(String campId);
}