package jp.co.isid.mos.bird.pmossles.sentaku.dto;

import java.util.List;

public class SentakuDto {
	/*
	 * ÉäÉìÉNèÓïÒ
	 */
	private List listInfoLink;
	/*
	 * ëJà⁄âÊñ ID
	 */
	private String toViewId;

	private String linkViewId;

	private String linkViewName;

	private String paramOne;

	private String paramTwo;

	public List getListInfoLink() {
		return listInfoLink;
	}

	public void setListInfoLink(List listInfoLink) {
		this.listInfoLink = listInfoLink;
	}

	public String getToViewId() {
		return toViewId;
	}

	public void setToViewId(String toViewId) {
		this.toViewId = toViewId;
	}

	public String getLinkViewId() {
		return linkViewId;
	}

	public void setLinkViewId(String linkViewId) {
		this.linkViewId = linkViewId;
	}

	public String getLinkViewName() {
		return linkViewName;
	}

	public void setLinkViewName(String linkViewName) {
		this.linkViewName = linkViewName;
	}

	public String getParamOne() {
		return paramOne;
	}

	public void setParamOne(String paramOne) {
		this.paramOne = paramOne;
	}

	public String getParamTwo() {
		return paramTwo;
	}

	public void setParamTwo(String paramTwo) {
		this.paramTwo = paramTwo;
	}

}
