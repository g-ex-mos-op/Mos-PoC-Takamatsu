package jp.co.isid.mos.bird.commonform.svsearchnew.logic.impl;


import java.util.List;
import jp.co.isid.mos.bird.commonform.svsearchnew.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.GetCompanyNameLogic;
import jp.co.isid.mos.bird.commonform.svsearchnew.common.SvSearchNewConst;

/**
 * âÔé–ñºèÃéÊìæÉçÉWÉbÉN
 * @author kusama
 *
 */
public class GetCompanyNameLogicImpl implements GetCompanyNameLogic {

    public static final String LOGIC_ID = SvSearchNewConst.VIEW_ID + "L02";

    private CodCompanyDao newCodCompanyDao;

    public CodCompanyDao getNewCodCompanyDao() {
        return newCodCompanyDao;
    }

    public void setNewCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.newCodCompanyDao = codCompanyDao;
    }

	public List execute(List rCompanyCdList) {
		return getNewCodCompanyDao().getCompanyByCodeList(rCompanyCdList);
	}
}
