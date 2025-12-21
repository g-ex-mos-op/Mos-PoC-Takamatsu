package jp.co.isid.mos.bird.pmossles.sentaku.action.impl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.pmossles.sentaku.action.SentakuAction;
import jp.co.isid.mos.bird.pmossles.sentaku.dto.SentakuDto;

public class SentakuActionImpl implements SentakuAction {

	// ÉvÉãÉ_ÉEÉìèÓïÒ
	private PullDownMenuDto pullDownMenuDto;

	private SentakuDto sentakuDto;

	@Override
	public String initialize() {
		return null;
	}

	@Override
	public String execute() {
		getPullDownMenuDto().setClearFlg(false);
		String viewId = getSentakuDto().getToViewId();
		if (viewId.equals("BCM002V01")) {
			getPullDownMenuDto().setClearFlg(true);
		}
		if (viewId.equals("BCM004V01")) {
			getPullDownMenuDto().setClearFlg(true);
			SentakuDto dto2 = (SentakuDto)getSentakuDto().getListInfoLink().get(1);
			getCommonCodeDto().setParam("PARAM", dto2.getParamOne() + "&" + dto2.getParamTwo());

		}
		return viewId;
	}

	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	public SentakuDto getSentakuDto() {
		return sentakuDto;
	}

	public void setSentakuDto(SentakuDto sentakuDto) {
		this.sentakuDto = sentakuDto;
	}

	public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

}
