/*
 * çÏê¨ì˙: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.filterregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.togouser.filterregist.dao.CodBumonDao;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetCodBumonLogic;

/**
 * @author S.yamauchi
 *
 */
public class GetCodBumonLogicImpl implements GetCodBumonLogic{

    /* ÉçÉWÉbÉNÇhÇc*/ 
    public static final String LOGIC_ID = "BUR002L01";
    
	private CodBumonDao codBumonDao;

	
    public List execute(String bumon) throws ApplicationException {
        List bumonlist = getCodBumonDao().select(bumon);
        return bumonlist;
	}


    public CodBumonDao getCodBumonDao() {
        return codBumonDao;
    }


    public void setCodBumonDao(CodBumonDao codBumonDao) {
        this.codBumonDao = codBumonDao;
    }


}
