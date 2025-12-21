/*
 * çÏê¨ì˙: 2006/03/08
 *
 */
package jp.co.isid.mos.bird.commonform.ownersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.ownersearch.logic.GetCompanyNameLogic;

/**
 * @author xkinu
 *
 */
public class GetCompanyNameLogicImpl implements GetCompanyNameLogic {

    public static final String LOGIC_ID = "BCO006L02";

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
