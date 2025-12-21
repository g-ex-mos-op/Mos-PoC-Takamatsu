/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UICampMenu;

/**
 * @author xnkusama
 *
 */
public interface UICampMenuDao {

    public static final Class BEAN = UICampMenu.class;

    public static final String getCampaignMenuList_ARGS = "campId, kakoFlg";
    public static final String getMenuList_ARGS = "menuCds";

    /**
     * 指定キャンペーンメニュー一覧の取得
     * 
     * @param campId キャンペーン識別番号
     * @param kakoFlg 過去キャンペーンフラグ
     * @return List メニュー一覧
     */
    public List getCampaignMenuList(String campId, boolean kakoFlg);
    /**
     * 指定メニュー一覧の取得
     * 
     * @param menuCds メニューコードリスト
     * @return List メニュー一覧
     */
    public List getMenuList(List menuCds);
}