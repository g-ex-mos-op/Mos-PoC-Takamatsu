package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.dao.CodCompanyJohoDao;
import jp.co.isid.mos.bird.entry.mlentry.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ユーザー所属管理会社の検索ロジック
 * @author Aspac
 */
public class CompanyJohoLogicImpl implements CompanyJohoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BEN008L02";

    /*【DAO】*/
    private CodCompanyJohoDao codCompanyJohoHanyoApplicationDao;
    
    /**
     * ユーザー所属管理会社の検索を行う
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String userId) throws ApplicationException {
        return getCodCompanyJohoHanyoApplicationDao().select(userId);
    }
    
    public CodCompanyJohoDao getCodCompanyJohoHanyoApplicationDao() {
        return codCompanyJohoHanyoApplicationDao;
    }

    public void setCodCompanyJohoHanyoApplicationDao(
            CodCompanyJohoDao codCompanyJohoHanyoApplicationDao) {
        this.codCompanyJohoHanyoApplicationDao = codCompanyJohoHanyoApplicationDao;
    }
}
