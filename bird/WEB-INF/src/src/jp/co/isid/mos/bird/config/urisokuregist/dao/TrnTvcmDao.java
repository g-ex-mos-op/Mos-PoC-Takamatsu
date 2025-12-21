/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnTvcm;

/**
 * TVCM放映日Dao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface TrnTvcmDao {

    public static final Class BEAN = TrnTvcm.class;
    /**
     * 検索時のパラメータ
     */
    public static final String select_ARGS = "taishoYm";
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 削除処理時のパラメータ
     */
    public static final String delete_ARGS = "entity";
    
    /**
     * 対象年月のTVCM放映日の取得
     * 
     * @param taishoYm　対象年月
     * @return
     */
    public List select(String taishoYm);
    /**
     * 新規登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int insert(TrnTvcm entity);
    /**
     * 更新登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int delete(TrnTvcm entity);

}
