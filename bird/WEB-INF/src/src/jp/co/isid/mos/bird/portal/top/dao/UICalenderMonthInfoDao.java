/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.top.entity.UICalenderMonthInfo;

/**
 * 月次カレンダー情報取得Dao
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public interface UICalenderMonthInfoDao {
    public static final Class BEAN = UICalenderMonthInfo.class;
	/**
	 * 対象年月情報取得SQLパラメータ
	 */
	public static final String select_ARGS = "targetYM, targetLastYM";
	/**
	 * 対象年月月次情報取得SQL実行処理
	 * @param targetYM
	 * @param targetLastYM
	 * @return
	 */
	public List select(String targetYM, String targetLastYM);
}
