/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstMenu;

/**
 * @author xnkusama
 *
 */
public interface MstMenuDao {

    public static final Class BEAN = MstMenu.class;

    public static final String getMenuName_ARGS = "menuCd";

    /**
     * メニュー名称取得
     * 
     * @param menuCd メニューコード
     * @return MstMenu
     */
    public MstMenu getMenuName(String menuCd);
}