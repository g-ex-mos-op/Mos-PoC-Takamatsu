/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * @author Noh

 */
public interface MlResultStatusRefAction extends CommonAction {
	
	/* 検索 */
	public String result();
	
	
	/* 戻る */
	public String back();
	
	/* オーナー選択処理 */
	public String onerSearch();
	
	/* 店舗選択処理 */
	public String miseSrearch();

}
