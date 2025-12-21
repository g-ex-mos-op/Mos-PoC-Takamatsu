/**
 * 
 */
package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.TrnOutSystemLog;

/**
 * 外部システムログ
 * 
 * ※登録系のログ出力も行います。
 * 
 * 作成日:2009/01/13
 * @author xkinu
 *
 */
public interface TrnOutSystemLogDao {
	/** エンティティクラス */
    public static final Class BEAN = TrnOutSystemLog.class;
    /**
     * 新規登録処理時のパラメータ
     */
    public static final String insert_ARGS = "entity";
    
    /**
     * 新規登録処理
     * 
     * @param entity
     * @return
     */
    public int insert(TrnOutSystemLog entity);

}
