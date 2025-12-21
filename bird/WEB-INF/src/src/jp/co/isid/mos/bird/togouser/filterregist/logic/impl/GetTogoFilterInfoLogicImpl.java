/*
 * çÏê¨ì˙: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.filterregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.togouser.filterregist.dao.BumonFilterDao;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetTogoFilterInfoLogic;

/**
 * @author S.yamauchi
 *
 */
public class GetTogoFilterInfoLogicImpl implements GetTogoFilterInfoLogic{

    /* ÉçÉWÉbÉNÇhÇc*/ 
    public static final String LOGIC_ID = "BUR002L02";
    
	private BumonFilterDao bumonFilterDao;

	
    public List execute(String user_id,String bumonCd) throws ApplicationException {
        List userlist = getBumonFilterDao().select(user_id,bumonCd);
        return userlist;
	}
    

    public BumonFilterDao getBumonFilterDao() {
        return bumonFilterDao;
    }


    public void setBumonFilterDao(BumonFilterDao bumonFilterDao) {
        this.bumonFilterDao = bumonFilterDao;
    }


}
