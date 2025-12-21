/**
 * 
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import jp.co.isid.mos.bird.framework.dao.CtlLeaveOutMenuDao;
import jp.co.isid.mos.bird.framework.logic.GetLeaveOutMenuLogic;

/**
 * マイメニュー登録可能画面判断ロジック
 * 
 * 作成日:2008/11/19
 * @author xkinu
 *
 */
public class GetLeaveOutMenuLogicImpl implements GetLeaveOutMenuLogic {

	private CtlLeaveOutMenuDao ctlLeaveOutMenuDao;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.GetLeaveOutMenuLogic#isLeaveOutMenu(java.lang.String)
	 */
	public boolean isLeaveOutMenu(String screenId) {
		// TODO 自動生成されたメソッド・スタブ
		return getCtlLeaveOutMenuDao().select(screenId) > 0;
	}
	/**
	 * @return ctlLeaveOutMenuDao を戻します。
	 */
	public CtlLeaveOutMenuDao getCtlLeaveOutMenuDao() {
		return ctlLeaveOutMenuDao;
	}
	/**
	 * @param ctlLeaveOutMenuDao を クラス変数ctlLeaveOutMenuDaoへ設定します。
	 */
	public void setCtlLeaveOutMenuDao(CtlLeaveOutMenuDao ctlLeaveOutMenuDao) {
		this.ctlLeaveOutMenuDao = ctlLeaveOutMenuDao;
	}

}
