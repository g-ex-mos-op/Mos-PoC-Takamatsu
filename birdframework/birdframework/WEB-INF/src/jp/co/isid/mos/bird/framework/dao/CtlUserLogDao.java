/**
 * 
 */
package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.CtlUserLog;

/**
 * ユーザーログ(ログイン履歴)登録処理Dao
 * 
 * 作成日:2009/04/16
 * @author xkinu
 *
 */
public interface CtlUserLogDao {
	public Class BEAN = CtlUserLog.class;
	/**
	 * 新規登録
	 * @param entity　対象エンティティ
	 * @return
	 */
	public int insert(CtlUserLog entity);
}
