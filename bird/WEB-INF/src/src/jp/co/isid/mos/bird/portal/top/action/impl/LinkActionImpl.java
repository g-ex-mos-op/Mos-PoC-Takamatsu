/**
 *
 */
package jp.co.isid.mos.bird.portal.top.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.action.impl.OutLinkActionImpl;
import jp.co.isid.mos.bird.common.entity.UIOutLink;
import jp.co.isid.mos.bird.common.logic.GetOutLinkLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.portal.top.action.LinkAction;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

/**
 * リンクアクション
 *
 * 作成日:2009/01/13
 * @author xkinu
 *
 */
public class LinkActionImpl extends OutLinkActionImpl implements LinkAction {

    /* アクションID */
    public static String initialize_ACTION_ID =  PortalTopUtil.SCREEN_ID+"A51";
    public static String linkUp_ACTION_ID     = PortalTopUtil.SCREEN_ID+"A52";

    /* portalTopDto */
    private PortalTopDto portalTopDto;
    /* 共通ロジック【外部リンク情報取得】 */
    private GetOutLinkLogic commonGetOutLinkLogic;

    private BirdUserInfo birdUserInfo;

	/**
	 * 初期表示リンクタイプ
	 *
	 * out:外部リンク、mymenu:マイメニューリンク
	 */
	private String linkType = "out";

    /* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.portal.top.action.LinkAction#initialize()
	 */
	public String initialize() {
		//１．共通ロジック【外部リンク情報取得】実行を実行し、List[[外部リンク]]を取得します。
		List listOutLink = getCommonGetOutLinkLogic().execute(getBirdUserInfo(), new String[]{"3"}, null,null);
		List afterlistOutLink = new ArrayList();
		boolean b = true;
		int count = 0;
		for (int i = 0; i < listOutLink.size(); i++) {
			UIOutLink outLink = (UIOutLink) listOutLink.get(i);
			String outLindId = outLink.getLinkId();
			if ("0025".equals(outLindId)) {
				count++;
			}
			if (b && "0025".equals(outLindId)) {
				afterlistOutLink.add(outLink);
				b = false;
			}
			if (b && !"0025".equals(outLindId)) {
				afterlistOutLink.add(outLink);
			}
			if (!b && !"0025".equals(outLindId)) {
				afterlistOutLink.add(outLink);
			}
		}
		if(count >1){
			getPortalTopDto().setCount(count);
		}

        //２．DTO【自画面】.[[外部リンク]]へ、処理１のList[[外部リンク]]情報を設定します。
        getPortalTopDto().setListOutLink(afterlistOutLink);
		return null;
	}

	/**
	 * @return commonGetOutLinkLogic を戻します。
	 */
	public GetOutLinkLogic getCommonGetOutLinkLogic() {
		return commonGetOutLinkLogic;
	}

	/**
	 * @param commonGetOutLinkLogic を クラス変数commonGetOutLinkLogicへ設定します。
	 */
	public void setCommonGetOutLinkLogic(GetOutLinkLogic commonGetOutLinkLogic) {
		this.commonGetOutLinkLogic = commonGetOutLinkLogic;
	}

	/**
	 * @return portalTopDto を戻します。
	 */
	public PortalTopDto getPortalTopDto() {
		return portalTopDto;
	}

	/**
	 * @param portalTopDto を クラス変数portalTopDtoへ設定します。
	 */
	public void setPortalTopDto(PortalTopDto portalTopDto) {
		this.portalTopDto = portalTopDto;
	}

	/**
	 * out:外部リンク、mymenu:マイメニューリンク
	 * @return linkType を戻します。
	 */
	public String getLinkType() {
		return linkType;
	}

	/**
	 * out:外部リンク、mymenu:マイメニューリンク
	 * @param linkType を クラス変数linkTypeへ設定します。
	 */
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

}
