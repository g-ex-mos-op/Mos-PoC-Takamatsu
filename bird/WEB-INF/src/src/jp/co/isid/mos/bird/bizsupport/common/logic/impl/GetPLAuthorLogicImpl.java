/*
 * 作成日: 2006/3/15
 */
package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import jp.co.isid.mos.bird.bizsupport.common.dao.TrnPLAuthorInfoDao;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLAuthorLogic;

import org.seasar.framework.log.Logger;

/**
 * PL作成者情報の取得Logic
 * @author itamoto
 */
public class GetPLAuthorLogicImpl implements GetPLAuthorLogic {

    private static Logger logger_ = Logger.getLogger(GetPLAuthorLogicImpl.class);
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS001L04";
    
    /* PL作成者情報Dao */
    private TrnPLAuthorInfoDao trnPLAuthorInfoDao;

	/**
	 * PL作成者情報Daoの設定
	 * @return trnPLAuthorInfoDao を戻します。
	 */
	public TrnPLAuthorInfoDao getTrnPLAuthorInfoDao() {
		return trnPLAuthorInfoDao;
	}
	/**
	 * PL作成者情報Daoの設定
	 * @param trnPLAuthorInfoDao trnPLAuthorInfoDao を設定。
	 */
	public void setTrnPLAuthorInfoDao(TrnPLAuthorInfoDao trnPLAuthorInfoDao) {
		this.trnPLAuthorInfoDao = trnPLAuthorInfoDao;
	}

	/**
     * 作成者情報を取得する
     * @param onerCd
     */
    public TrnPLAuthorInfo execute(String onerCd) {
    	return trnPLAuthorInfoDao.getAuther(onerCd);
    }
}
