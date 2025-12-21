/*
 * 作成日: 2006/3/2
 *
 */
package jp.co.isid.mos.bird.commonform.ownersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.ownersearch.logic.GetCompanyLogic;

/**
 * 会社コード取得ロジック
 * @author itamoto
 */
public class GetCompanyLogicImpl implements GetCompanyLogic {

    public static final String LOGIC_ID = "BCO006L01";

    private CodCompanyDao codCompanyDao;

    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }
    /**
 	 * 会社コード取得
　   * 
	 * @param userId
	 * @return
     */
	public List execute(String userId) {
		return getCodCompanyDao().getCompanyAll(userId);
	}
}
