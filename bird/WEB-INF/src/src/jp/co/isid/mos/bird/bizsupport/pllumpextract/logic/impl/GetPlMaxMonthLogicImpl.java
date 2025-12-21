/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.impl;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.UIPlMaxMonthDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetPlMaxMonthLogic;

/**
 * @author xtoshi
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class GetPlMaxMonthLogicImpl implements GetPlMaxMonthLogic {

    /* Dao */
    private UIPlMaxMonthDao uIPlMaxMonthDao;
    
    /* ロジックID */    
    private static final String LOGIC_ID = "BBS007L05";
    
   /**
    * uIPlMaxMonthDao設定
    * @return
    */
    public UIPlMaxMonthDao getUIPlMaxMonthDao() {
    	return this.uIPlMaxMonthDao;
    }
    
    /**
     * uIPlMaxMonthDao設定
     * @param uIPlMaxMonthDao
     */
    public void setUIPlMaxMonthDao( UIPlMaxMonthDao uIPlMaxMonthDao ) {
    	this.uIPlMaxMonthDao = uIPlMaxMonthDao;
    }
    
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetOnerInfoLogic#execute(java.lang.String, java.lang.String)
	 */
	public String execute() {
		String plMaxMonth = "";
        
        plMaxMonth = getUIPlMaxMonthDao().getPlMaxMonth();
        
		return plMaxMonth;
        
	}

}
