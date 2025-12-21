/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.action.inside.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.action.inside.ScheduleInsideaction;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.logic.GetScheduleLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ポータルスケジュール出力アクション
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class ScheduleInsideactionImpl implements ScheduleInsideaction {

    /* アクションID */
    public static String initialize_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A41";

    /* portalTopDto */
    private PortalTopDto portalTopDto;
    /* GetSokuLogic */
    private GetScheduleLogic portalTopGetScheduleLogic;


    /**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        // 売上情報取得
        portalTopDto.setListSchedule(getPortalTopGetScheduleLogic().execute(
        		getBirdUserInfo(),getBirdDateInfo(), getPortalTopDto()));
        return null;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * ポータルページ用Dto設定処理
     * @return portalTopDto
     */
    public PortalTopDto getPortalTopDto() {
        return portalTopDto;
    }
    /**
     * ポータルページ用Dto設定処理
     * @param portalTopDto
     */
    public void setPortalTopDto(PortalTopDto portalTopDto) {
        this.portalTopDto = portalTopDto;
    }
	/**
	 * @return portalTopGetScheduleLogic を戻します。
	 */
	public GetScheduleLogic getPortalTopGetScheduleLogic() {
		return portalTopGetScheduleLogic;
	}
	/**
	 * @param portalTopGetScheduleLogic を クラス変数portalTopGetScheduleLogicへ設定します。
	 */
	public void setPortalTopGetScheduleLogic(
			GetScheduleLogic portalTopGetScheduleLogic) {
		this.portalTopGetScheduleLogic = portalTopGetScheduleLogic;
	}
}

