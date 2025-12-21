/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.common.dto;

import jp.co.isid.mos.bird.common.entity.UIOutLink;

/**
 * 外部リンク用ＤＴＯ
 *
 * 作成日:2009/01/22
 * @author xkinu
 *
 */
public class OutLinkDto {
	private String exceptionMessage;
	/**メンテナンス中判断フラグ*/
	private boolean menteFlg = true;
    /* 外部リンク情報 */
    private String linkId;
    private String dougaCd;

    private UIOutLink targetLinkInfo;
	/**
	 * @return targetLinkInfo を戻します。
	 */
	public UIOutLink getTargetLinkInfo() {
		return targetLinkInfo;
	}
	/**
	 * @param targetLinkInfo を クラス変数targetLinkInfoへ設定します。
	 */
	public void setTargetLinkInfo(UIOutLink targetLinkInfo) {
		this.targetLinkInfo = targetLinkInfo;
	}
	/**
	 * 対象リンクはメンテナンス中か否か
	 * @return
	 */
	public boolean isTargetMainte() {
		return !isNull(getTargetLinkInfo().getDisableStaTmp().toString());
	}
	/**
	 * @return linkId を戻します。
	 */
	public String getLinkId() {
		return linkId;
	}
	/**
	 * @param linkId を クラス変数linkIdへ設定します。
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	/**
	 * 対象URL
	 * @return
	 */
	public String getTargetUrl() {
		String url = getTargetLinkInfo().getUrl();
		if(!isNull(getTargetLinkInfo().getParam())) {
			url += getTargetLinkInfo().getParam();
		}
		return url;
	}
	/**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * 異常処理時のエラーメッセージ取得処理
     *
     * @return
     */
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	/**
	 * 異常処理時のエラーメッセージ設定処理
	 * @param message
	 */
	public void setExceptionMessage(String message) {
		this.exceptionMessage = message;
	}
	public boolean isMenteFlg() {
		return menteFlg;
	}
	public void setMenteFlg(boolean menteFlg) {
		this.menteFlg = menteFlg;
	}
	public String getDougaCd() {
		return dougaCd;
	}
	public void setDougaCd(String dougaCd) {
		this.dougaCd = dougaCd;
	}

}
