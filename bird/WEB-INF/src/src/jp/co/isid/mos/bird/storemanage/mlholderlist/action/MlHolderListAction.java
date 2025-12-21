/*
 * ì¬“ú: 2006/04/20
 */
package jp.co.isid.mos.bird.storemanage.mlholderlist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * @author lee
 */
public interface MlHolderListAction extends CommonAction {
	
	public String initialize();
	/* ŒŸõ */
	public String result();
	
	public String onerSearch();
	
	public String miseSrearch();
	
}