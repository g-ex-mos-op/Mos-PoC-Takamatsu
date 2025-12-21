/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.dao;

import java.util.List;

import jp.co.isid.mos.bird.portal.top.entity.UICalenderDayInfo;

/**
 * 日次カレンダー情報取得Dao
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public interface UICalenderDayInfoDao {
    public static final Class BEAN = UICalenderDayInfo.class;
	/**
	 * 対象年月情報取得SQLパラメータ
	 */
	public static final String select_ARGS = "companyCd, targetYM";
	/**
	 * 対象年月日次情報取得SQL実行処理
	 * @param companyCd
	 * @param targetYM
	 * @return
	 */
	public List select(String companyCd, String targetYM);
}
