/*
 * 作成日: 2006/03/03
 *
 */
package jp.co.isid.mos.bird.commonform.usersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetCompanyLogic;

/**
 * 会社リストの取得ロジック
 * @author xkinu
 *
 */
public class GetCompanyLogicImpl implements GetCompanyLogic {
	/* ロジックNo */
    public static final String LOGIC_ID = "BCO003L02";

    private CodCompanyDao codCompanyDao;

    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }

	public List execute() {
		return getCodCompanyDao().getCompanyAll();
	}
}
