/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampSumMenu;

/**
 * @author xnkusama
 *
 */
public interface MstCampSumMenuDao {

    public static final Class BEAN = MstCampSumMenu.class;

    public static final String updateCampaignMenu_PERSISTENT_PROPS = "convValue, sumMenuCd, lastUser, lastPgm, lastTmsp";
    public static final String getMenuInfo_ARGS = "campId, companyCd";
    
    /**
     * キャンペーン対象メニューマスタの新規登録
     * @param entity
     * @return
     */
    public int insertCampaignMenu(MstCampSumMenu entity);
    
    /**
     * キャンペーン対象メニューマスタの削除
     * @param entity
     * @return
     */
    public int updateCampaignMenu(MstCampSumMenu entity);
    
    /**
     * 登録済みメニュー情報取得
     */
    public MstCampSumMenu getMenuInfo(String menuCd);
}