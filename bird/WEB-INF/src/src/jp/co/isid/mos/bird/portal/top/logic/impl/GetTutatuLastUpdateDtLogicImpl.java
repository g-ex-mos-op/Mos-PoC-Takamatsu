/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.logic.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.dao.UITutatuLastUpdateDtDao;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.logic.GetTutatuLastUpdateDtLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

/**
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class GetTutatuLastUpdateDtLogicImpl implements
		GetTutatuLastUpdateDtLogic {
    /** ロジックID */
    public static final String LOGIC_ID = PortalTopUtil.SCREEN_ID+"L02";

    /** 通達最新更新日取得Dao */
	private UITutatuLastUpdateDtDao portalTopUITutatuLastUpdateDtDao;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.portal.top.logic.GetTutatuLastUpdateDtLogic#execute(jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.portal.top.dto.PortalTopDto)
	 */
	public String execute(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo,
			PortalTopDto portalTopDto) {
		// TODO 自動生成されたメソッド・スタブ
		return getPortalTopUITutatuLastUpdateDtDao().select(birdDateInfo.getSysDate(), birdUserInfo.getUserID());
	}
	/**
	 * @return portalTopUITutatuLastUpdateDtDao を戻します。
	 */
	public UITutatuLastUpdateDtDao getPortalTopUITutatuLastUpdateDtDao() {
		return portalTopUITutatuLastUpdateDtDao;
	}
	/**
	 * @param portalTopUITutatuLastUpdateDtDao を クラス変数portalTopUITutatuLastUpdateDtDaoへ設定します。
	 */
	public void setPortalTopUITutatuLastUpdateDtDao(
			UITutatuLastUpdateDtDao portalTopUITutatuLastUpdateDtDao) {
		this.portalTopUITutatuLastUpdateDtDao = portalTopUITutatuLastUpdateDtDao;
	}

}
