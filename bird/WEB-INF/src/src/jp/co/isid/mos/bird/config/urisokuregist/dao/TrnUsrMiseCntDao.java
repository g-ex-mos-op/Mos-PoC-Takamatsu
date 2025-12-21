/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnUsrMiseCnt;

/**
 * 売上速報レポート用店舗数Dao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface TrnUsrMiseCntDao {
    public static final Class BEAN = TrnUsrMiseCnt.class;
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
                = "miseCntAll, lastUser, lastPgm";
    
    /**
     * 新規登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int insert(TrnUsrMiseCnt entity);
    /**
     * 更新登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int update(TrnUsrMiseCnt entity);
	
}
