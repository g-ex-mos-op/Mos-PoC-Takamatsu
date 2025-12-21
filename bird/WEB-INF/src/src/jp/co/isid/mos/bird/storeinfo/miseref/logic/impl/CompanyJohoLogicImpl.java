package jp.co.isid.mos.bird.storeinfo.miseref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.CodCompanyJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.CompanyJohoLogic;

/**
 * ユーザー所属管理会社の検索ロジック
 * @author xnkusama
 */
public class CompanyJohoLogicImpl implements CompanyJohoLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BSI001L01";

    /*【DAO】*/
    private CodCompanyJohoDao codCompanyJohoDao;
    
    /**
     * ユーザー所属管理会社の検索を行う
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String userId) throws ApplicationException {
        return getCodCompanyJohoDao().select(userId);
    }
    
    /**
     * @return codCompanyJohoDao を戻します。
     */
    public CodCompanyJohoDao getCodCompanyJohoDao() {
        return codCompanyJohoDao;
    }
    /**
     * @param codCompanyJohoDao codCompanyJohoDao を設定。
     */
    public void setCodCompanyJohoDao(CodCompanyJohoDao codCompanyJohoDao) {
        this.codCompanyJohoDao = codCompanyJohoDao;
    }
}
