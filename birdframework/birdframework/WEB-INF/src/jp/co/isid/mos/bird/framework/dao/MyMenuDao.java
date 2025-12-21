/**
 * 
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MyMenu;

/**
 * マイメニューリスト取得Dao
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public interface MyMenuDao {
	/** エンティティクラス */
    public static final Class BEAN = MyMenu.class;
    /** 検索処理時のパラメータ*/
    public static final String select_ARGS = "userId";
    
    /**
     * 検索条件よりマイメニュー情報を取得
     * @param userId     ユーザID
     * @return List      検索結果
     */
    public List select(String userId);

}
