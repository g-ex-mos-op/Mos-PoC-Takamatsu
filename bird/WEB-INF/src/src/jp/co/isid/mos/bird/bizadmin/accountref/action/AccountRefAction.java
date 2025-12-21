/*
 * 作成日: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;


/**
 * アカウント情報照会アクションインターフェース
 * 
 * 作成日:2010/03/17
 * @author xkinu
 *
 */
public interface AccountRefAction extends CommonAction {
	public String callUserSearchForm();
	public String reference();
	public String back();
}
     