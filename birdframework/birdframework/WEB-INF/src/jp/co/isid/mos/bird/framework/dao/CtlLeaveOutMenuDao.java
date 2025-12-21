package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.CtlLeaveOutMenu;



/**
 * マイメニュー除外メニュー情報
 * 
 * @author kinu
 *
 */
public interface CtlLeaveOutMenuDao {
	public static final Class BEAN = CtlLeaveOutMenu.class;

	public static final String select_ARGS = "screenId";
	/**
	 * 指定画面IDの除外メニュー件数取得
	 * 
	 * @param viewId
	 * @return
	 */
    public int select(String screenId);
}
