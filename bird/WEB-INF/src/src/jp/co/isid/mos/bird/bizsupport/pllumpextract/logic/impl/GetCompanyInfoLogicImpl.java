/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.dao.CodCompanyInfoDao;
import jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetCompanyInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public class GetCompanyInfoLogicImpl implements GetCompanyInfoLogic {

    /* ロジックID */    
    private static final String LOGIC_ID = "BBS007L01";
    
    /* CodCompanyInfoDao */
    private CodCompanyInfoDao codCompanyInfoDao;
    
    /**
     * CodCompanyInfoDao設定
     * @return
     */
    public CodCompanyInfoDao getCodCompanyInfoDao() {
    	return this.codCompanyInfoDao;
    }
    /**
     * CodCompanyInfoDao設定
     * @param
     */
    public void setCodCompanyInfoDao ( CodCompanyInfoDao codCompanyInfoDao ) {
    	this.codCompanyInfoDao = codCompanyInfoDao;
    }
    
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.pllumpextract.logic.GetCompanyInfoLogic#execute(java.lang.String)
	 */
	public List execute(String rCompanyCd) {

        List compList = new ArrayList();
        
        compList  = getCodCompanyInfoDao().getCompanyInfo( rCompanyCd );
        
        if ( compList == null || compList.size() == 0 ) {
        	throw new NotExistException("管理会社");
        }
        
		return compList;
	}

}
