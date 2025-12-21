/*
 * 作成日: 2006/08/22
 *
 */
package jp.co.isid.mos.bird.commonform.svsearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearch.action.SvSearchAction;
import jp.co.isid.mos.bird.commonform.svsearch.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.svsearch.logic.GetCompanyLogic;

/**
 * 会社コード取得ロジック
 * @author kinugawa(ASPAC)
 */
public class GetCompanyLogicImpl implements GetCompanyLogic {

    public static final String LOGIC_ID = SvSearchAction.SCREEN_ID+"L01";

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
