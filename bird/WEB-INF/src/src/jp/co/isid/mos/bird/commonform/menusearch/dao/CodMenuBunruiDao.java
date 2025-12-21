/**
 * 
 */
package jp.co.isid.mos.bird.commonform.menusearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.menusearch.entity.CodMenuBunrui;

/**
 * メニュー分類Dao
 * 
 * 
 * 作成日:2008/08/26
 * @author xkinu
 *
 */
public interface CodMenuBunruiDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = CodMenuBunrui.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "";
    /**
     * 検索情報取得
     * 
     * @return
     */
    public List select();

}
