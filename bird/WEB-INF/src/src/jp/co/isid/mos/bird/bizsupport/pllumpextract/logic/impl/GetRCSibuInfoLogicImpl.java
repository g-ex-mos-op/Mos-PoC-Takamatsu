/*
 * 作成日: 2006/04/07
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.MstSibuInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetRCSibuInfoLogic;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class GetRCSibuInfoLogicImpl implements GetRCSibuInfoLogic {

    /* ロジックID */    
    private static final String LOGIC_ID = "BBS007L02";
    
    /* 支部取得Dao */
    public MstSibuInfoDao mstSibuInfoDao;
    
    /**
     * 支部情報Dao
     * @return MstSibuInfoDao
     */
    public MstSibuInfoDao getMstSibuInfoDao() {
    	return this.mstSibuInfoDao;
    }
    /**
     * 支部情報Dao
     * @param mstSibuInfoDao
     */
    public void setMstSibuInfoDao( MstSibuInfoDao mstSibuInfoDao ) {
    	this.mstSibuInfoDao = mstSibuInfoDao; 
    }
        
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetRCSibuInfoLogic#execute(java.util.HashMap)
	 */
	public List execute(String companyCd) {
		List rcList = new ArrayList();
		
        rcList = getMstSibuInfoDao().getRCSibuInfo( companyCd );
             
		return rcList;
	}
}
