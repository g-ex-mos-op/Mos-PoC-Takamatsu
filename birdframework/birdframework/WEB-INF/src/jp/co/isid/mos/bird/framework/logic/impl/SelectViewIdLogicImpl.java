/*
 * 作成日: 2007/01/22
 * 変更日: 2007/02/05 
 *           DBから取得できなかった場合の遷移先画面IDを変更する為、
 *           取得できなかった場合の戻り値を変更
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import jp.co.isid.mos.bird.framework.dao.SelectViewIdDao;
import jp.co.isid.mos.bird.framework.logic.SelectViewIdLogic;


/**
 * 遷移先ViewIDを取得ロジッククラス
 * @author xsong
 *
 */
public class SelectViewIdLogicImpl implements SelectViewIdLogic{
	
	/**
     * ポータルトップ画面・ビューＩＤ
     */
//--- 2007/02/05 start 取得できなかった場合の画面IDを変更
//	 private static final String initViewId = "BPO001V01";
     private static final String initViewId = "";
//--- 2007/02/05 end	 
	 /**
	  * セッションエラー時遷移先情報DAO
	  */
	 private SelectViewIdDao selectViewIdDao;
	 
	 /** 
	  * 遷移先ViewIDを取得する。
	  * @param id 画面ID
	  * @return DBから取得できた場合：DBの画面ID、取得できなかった場合：BPO001V01
	  */
	public String getSelectId(String id) {
  	
	
		// 遷移先ViewIDを取得
		String idDB = getSelectViewIdDao().getViewID(id);

		return (idDB != null && idDB.length() > 0) ? idDB.trim() : initViewId;
	}

	public SelectViewIdDao getSelectViewIdDao() {
		return selectViewIdDao;
	}

	public void setSelectViewIdDao(SelectViewIdDao selectViewIdDao) {
		this.selectViewIdDao = selectViewIdDao;
	}

}
