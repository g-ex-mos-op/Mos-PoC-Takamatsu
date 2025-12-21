/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.UIOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetOnerInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class GetOnerInfoLogicImpl implements GetOnerInfoLogic {

    /* Dao */
    private UIOnerInfoDao uIOnerInfoDao;
    
    /* ロジックID */    
    private static final String LOGIC_ID = "BBS007L04";
    
   /**
    * uIOnerInfoDao設定
    * @return
    */
    public UIOnerInfoDao getUIOnerInfoDao() {
    	return this.uIOnerInfoDao;
    }
    
    /**
     * uIOnerInfoDao設定
     * @param uIOnerInfoDao
     */
    public void setUIOnerInfoDao( UIOnerInfoDao uIOnerInfoDao ) {
    	this.uIOnerInfoDao = uIOnerInfoDao;
    }
    
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetOnerInfoLogic#execute(java.lang.String, java.lang.String)
	 */
	public List execute(String companyCd, String onerCd) {
		List onerList = new ArrayList();
        
        onerList = getUIOnerInfoDao().getOnerInfo( companyCd, onerCd );
        
        if ( onerList == null || onerList.size() == 0 ) {
        	throw new NotExistException("オーナー");
        }
        
		return onerList;
        
	}

}
