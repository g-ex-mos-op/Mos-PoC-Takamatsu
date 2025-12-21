/**
 * 
 */
package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.TrnMyMenu;

/**
 * マイメニュー情報DAO
 * 
 * 作成日:2008/12/18
 * @author xkinu
 *
 */
public interface TrnMyMenuDao {
	/** エンティティクラス */
    public static final Class BEAN = TrnMyMenu.class;
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
                = "sakujoFlg, lastUser, lastPgm";
    
    /**
     * 新規登録処理
     * 
     * @param entity
     * @return
     */
    public int insert(TrnMyMenu entity);
    /**
     * 更新登録処理
     * 
     * @param entity
     * @return
     */
    public int update(TrnMyMenu entity);
}
