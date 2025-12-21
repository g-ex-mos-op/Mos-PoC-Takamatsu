package jp.co.isid.mos.bird.commonform.svsearchnew.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearchnew.common.SvSearchNewConst;
import jp.co.isid.mos.bird.commonform.svsearchnew.dao.CodCompanyDao;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.GetCompanyLogic;

/**
 * 会社コード取得ロジック
 * @author kusama
 */
public class GetCompanyLogicImpl implements GetCompanyLogic {

    public static final String LOGIC_ID = SvSearchNewConst.VIEW_ID + "L01";

    private CodCompanyDao newCodCompanyDao;

    public CodCompanyDao getNewCodCompanyDao() {
        return newCodCompanyDao;
    }

    public void setNewCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.newCodCompanyDao = codCompanyDao;
    }
    /**
 	 * 会社コード取得
　   * 
	 * @param userId
	 * @return
     */
	public List execute(String userId) {
		return getNewCodCompanyDao().getCompanyAll(userId);
	}
}
