/*
 * çÏê¨ì˙: 2006/03/08
 *
 */
package jp.co.isid.mos.bird.commonform.usersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetCompanyNameLogic;

/**
 * @author xnkusama
 *
 */
public class GetCompanyNameLogicImpl implements GetCompanyNameLogic {

    public static final String LOGIC_ID = "BCO003L03";

    private CodCompanyDao codCompanyDao;

    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }

	public List execute(List rCompanyCdList) {
		return getCodCompanyDao().getCompanyByCodeList(rCompanyCdList);
	}
}
