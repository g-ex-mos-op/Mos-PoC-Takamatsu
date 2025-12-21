/**
 * 作成日: 2017/04/01
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao.CodKbCompanyJohoDao;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.CodKbCompanyJoho;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.KbCompanyJohoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 株式報酬制度の会社の検索ロジック
 * @author xayumi
 */
public class KbCompanyJohoLogicImpl implements KbCompanyJohoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BBS038L03";

    /*【DAO】*/
    private CodKbCompanyJohoDao CodKbCompanyJohoDao;

    /**
     * 株式報酬制度の会社の検索を行う
     * @param String userId ユーザーID
     * @return List
     * @exception ApplicationException
     */
    public List<CodKbCompanyJoho> execute() throws ApplicationException {
    	List<CodKbCompanyJoho> companyList = getCodKbCompanyJohoDao().select();
    	CodKbCompanyJoho brank = new CodKbCompanyJoho();
    	brank.setKbCompanyCd("");
    	companyList.add(0, brank);
        return companyList;
    }

	/**
	 * codKbCompanyJohoDaoを返す
	 * @return codKbCompanyJohoDao
	 */
	public CodKbCompanyJohoDao getCodKbCompanyJohoDao() {
		return CodKbCompanyJohoDao;
	}

	/**
	 * codKbCompanyJohoDaoをセットする
	 * @param codKbCompanyJohoDao
	 */
	public void setCodKbCompanyJohoDao(CodKbCompanyJohoDao codKbCompanyJohoDao) {
		CodKbCompanyJohoDao = codKbCompanyJohoDao;
	}


}
