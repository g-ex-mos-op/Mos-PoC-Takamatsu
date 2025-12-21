package jp.co.isid.mos.bird.commonform.menusearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.menusearch.entity.UIMenuInfo;

/**
 * メニュー選択画面
 * 社内メニュー情報Daoインターフェース
 * 
 * @author xkinu
 *
 */
public interface UIMenuInfoDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = UIMenuInfo.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "searchType, cdFrom, cdTo, name1, name2, name3, name4, menuBunrui, sortType, sysDate";
    /**
     * 検索情報取得
     * 
     * @param searchType
     * @param cdFrom
     * @param cdTo
     * @param name1
     * @param name2
     * @param name3
     * @param name4
     * @param menuBunrui
     * @param sortType
     * @param sysDate
     * @return
     */
    public List select(String searchType, String cdFrom, String cdTo
    		, String name1, String name2, String name3, String name4
    		, String menuBunrui, String sortType, String sysDate);

}
