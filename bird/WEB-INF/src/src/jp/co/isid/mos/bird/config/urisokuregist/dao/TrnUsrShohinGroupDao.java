/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.dao;

import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnUsrShohinGroup;

/**
 * 売上速報レポート用商品グループDao
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface TrnUsrShohinGroupDao {
    public static final Class BEAN = TrnUsrShohinGroup.class;
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    /**
     * 更新登録処理時のパラメータ
     */
    public static final String update_ARGS = "entity";
    
    public static final String update_PERSISTENT_PROPS 
                = "menuCd, dispWord, dataKbn, lastUser, lastPgm";
    
    /**
     * 新規登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int insert(TrnUsrShohinGroup entity);
    /**
     * 更新登録処理
     * 
     * @param entity　エンティティ
     * @return
     */
    public int update(TrnUsrShohinGroup entity);

}
