/*
 * çÏê¨ì˙: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.filterregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.togouser.filterregist.dao.UserSaisinDao;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetTogoUserLogic;

/**
 * @author S.yamauchi
 *
 */
public class GetTogoUserLogicImpl implements GetTogoUserLogic{

    /* ÉçÉWÉbÉNÇhÇc*/ 
    public static final String LOGIC_ID = "BUR002L03";
    
	private UserSaisinDao UserSaisinDao;

	
    public List execute(String userId) throws ApplicationException {
        List userlist = getUserSaisinDao().select(userId);
        return userlist;
	}


    public UserSaisinDao getUserSaisinDao() {
        return UserSaisinDao;
    }


    public void setUserSaisinDao(UserSaisinDao rankCodeDao) {
        this.UserSaisinDao = rankCodeDao;
    }


}
