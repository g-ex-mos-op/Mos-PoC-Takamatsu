/**
 *
 */
package jp.co.isid.mos.bird.portal.top.action.inside.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dto.OutLinkDto;
import jp.co.isid.mos.bird.common.entity.UIOutLink;
import jp.co.isid.mos.bird.common.logic.GetOutLinkLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.action.inside.AllianceInfoInsideaction;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;

public class AllianceInfoInsideactionImpl implements AllianceInfoInsideaction {

    /* 共通ロジック【外部リンク情報取得】 */
    private GetOutLinkLogic commonGetOutLinkLogic;
    /* portalTopDto */
    private PortalTopDto portalTopDto;
    /* outLinkDto */
    private OutLinkDto commonOutLinkDto;
	/**
     * 初期処理
     */
	public String initialize() {
		return null;
	}

	public String getOutLinkInfo() {
		//１．共通ロジック【外部リンク情報取得】実行を実行し、List[[外部リンク]]を取得します。
		String targetLinkId = getCommonOutLinkDto().getLinkId();
		List listOutLink = getCommonGetOutLinkLogic().execute(getBirdUserInfo(), new String[]{"3"}, targetLinkId,null);
		List afterlistOutLink = new ArrayList();
		if(listOutLink!=null&&listOutLink.size()!=0){
			for(int i=0;i<listOutLink.size();i++){
				UIOutLink outLink = (UIOutLink) listOutLink.get(i);
				String outLindId = outLink.getLinkId();
				if ("0025".equals(outLindId)) {
					afterlistOutLink.add(outLink);
				}
			}
		}
		getPortalTopDto().setListOutLink(afterlistOutLink);
		return "BPO001V04";
	}

    /**
     *
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

	public GetOutLinkLogic getCommonGetOutLinkLogic() {
		return commonGetOutLinkLogic;
	}

	public void setCommonGetOutLinkLogic(GetOutLinkLogic commonGetOutLinkLogic) {
		this.commonGetOutLinkLogic = commonGetOutLinkLogic;
	}

	public PortalTopDto getPortalTopDto() {
		return portalTopDto;
	}

	public void setPortalTopDto(PortalTopDto portalTopDto) {
		this.portalTopDto = portalTopDto;
	}

	public OutLinkDto getCommonOutLinkDto() {
		return commonOutLinkDto;
	}

	public void setCommonOutLinkDto(OutLinkDto commonOutLinkDto) {
		this.commonOutLinkDto = commonOutLinkDto;
	}

}
