/**
 * 
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnControlShozoku;

/**
 * 本日更新所属情報Dao
 * 
 * 作成日:2010/06/01
 * @author xkinu
 *
 */
public interface UIShozokuDao {
	public Class BEAN = TrnControlShozoku.class;
	public static final String select_ARGS = "lastTmspDt";
	/**
	 * 本日更新所属情報取得処理
	 * @param lastTmspDt yyyy-MM-dd
	 * @return
	 */
	public List select( String lastTmspDt);
}
