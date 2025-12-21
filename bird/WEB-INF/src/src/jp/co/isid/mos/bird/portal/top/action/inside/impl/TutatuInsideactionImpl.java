/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.action.inside.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.action.inside.TutatuInsideaction;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.logic.GetTutatuLastUpdateDtLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ポータル売上速報出力アクション
 * 
 * @author xkinu
 */
public class TutatuInsideactionImpl implements TutatuInsideaction {

    /* アクションID */
    public static String initialize_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A11";

    /* portalTopDto */
    private PortalTopDto portalTopDto;
    
    private GetTutatuLastUpdateDtLogic portalTopGetTutatuLastUpdateDtLogic;
    /**
	 * @return portalTopGetTutatuLastUpdateDtLogic を戻します。
	 */
	public GetTutatuLastUpdateDtLogic getPortalTopGetTutatuLastUpdateDtLogic() {
		return portalTopGetTutatuLastUpdateDtLogic;
	}


	/**
	 * @param portalTopGetTutatuLastUpdateDtLogic を クラス変数portalTopGetTutatuLastUpdateDtLogicへ設定します。
	 */
	public void setPortalTopGetTutatuLastUpdateDtLogic(
			GetTutatuLastUpdateDtLogic portalTopGetTutatuLastUpdateDtLogic) {
		this.portalTopGetTutatuLastUpdateDtLogic = portalTopGetTutatuLastUpdateDtLogic;
	}


	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
    	getPortalTopDto().setTutatuLastUpdateDt(
    			getPortalTopGetTutatuLastUpdateDtLogic().execute(getBirdUserInfo(), getBirdDateInfo(), getPortalTopDto()));
    	return null;
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
}

