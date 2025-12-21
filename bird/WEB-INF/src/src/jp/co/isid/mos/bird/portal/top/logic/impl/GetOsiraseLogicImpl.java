/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.portal.top.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.logic.GetInfoAccessControlLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.dao.UIOsiraseDao;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.logic.GetOsiraseLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

/**
 * お知らせ情報取得処理ロジック
 * @author itamoto
 */
public class GetOsiraseLogicImpl implements GetOsiraseLogic {

    /** ロジックID */
    public static final String LOGIC_ID = PortalTopUtil.SCREEN_ID+"L04";
    /* UIWhasNewDao */
    private UIOsiraseDao portalTopUIOsiraseDao;

    private GetInfoAccessControlLogic getInfoAccessControlLogic;

    /**
     * お知らせ情報取得処理
     * @param  userInfo
     * @param  birdDateInfo
     * @param  portalTopDto
     * @return List
     */
    public List execute(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo,
			PortalTopDto portalTopDto) {
		// １．Dao【お知らせ情報．お知らせ情報の取得[メインテーブル]】を実行
		List whatsNewList = getPortalTopUIOsiraseDao().select(birdUserInfo.getUserID(),
				birdDateInfo.getSysDate());

		// 個別アクセス制御
		whatsNewList = getGetInfoAccessControlLogic().execute("05",
				birdUserInfo, whatsNewList);

		return whatsNewList;
    }

    public GetInfoAccessControlLogic getGetInfoAccessControlLogic() {
        return getInfoAccessControlLogic;
    }
    public void setGetInfoAccessControlLogic(
            GetInfoAccessControlLogic getInfoAccessControlLogic) {
        this.getInfoAccessControlLogic = getInfoAccessControlLogic;
    }

	/**
	 * @return portalTopUIOsiraseDao を戻します。
	 */
	public UIOsiraseDao getPortalTopUIOsiraseDao() {
		return portalTopUIOsiraseDao;
	}

	/**
	 * @param portalTopUIOsiraseDao を クラス変数portalTopUIOsiraseDaoへ設定します。
	 */
	public void setPortalTopUIOsiraseDao(UIOsiraseDao portalTopUIOsiraseDao) {
		this.portalTopUIOsiraseDao = portalTopUIOsiraseDao;
	}
}
