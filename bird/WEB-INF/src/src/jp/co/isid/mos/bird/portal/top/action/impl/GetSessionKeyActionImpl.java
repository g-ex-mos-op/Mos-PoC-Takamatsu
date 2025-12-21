/**
 *
 */
package jp.co.isid.mos.bird.portal.top.action.impl;

import jp.co.isid.mos.bird.portal.top.action.GetSessionKeyAction;

/**
 * リンクアクション
 *
 * 作成日:2009/01/13
 * @author xkinu
 *
 */
public class GetSessionKeyActionImpl implements GetSessionKeyAction {


	public String initialize() {
		return null;
	}

	public String ajaxGetSessionKey() {
		return "has";
	}

}
