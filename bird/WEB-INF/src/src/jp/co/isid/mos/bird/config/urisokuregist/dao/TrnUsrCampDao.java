/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnUsrCamp;

/**
 * 売上速報レポート用キャンペーンDao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface TrnUsrCampDao {
    public static final Class BEAN = TrnUsrCamp.class;
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
                = "titleFlg, jissekiFlg, lastUser, lastPgm";
    
    /**
     * 新規登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int insert(TrnUsrCamp entity);
    /**
     * 更新登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int update(TrnUsrCamp entity);
    
}
