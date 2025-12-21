/*
 * çÏê¨ì˙: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.togouser.common.dao.UITogoUserMenuDao;
import jp.co.isid.mos.bird.togouser.common.logic.GetTogoUserMenuLogic;

/**
 * @author S.yamauchi
 *
 */
public class GetTogoUserMenuLogicImpl implements GetTogoUserMenuLogic{

    /* ÉçÉWÉbÉNÇhÇc*/ 
    public static final String LOGIC_ID = "BUR001L09";
    
	private UITogoUserMenuDao UITogoMenuUserDao;

	
    public List execute() throws ApplicationException {
        List menulist = getUITogoUserMenuDao().getMenuInfo();
        return menulist;
	}


    public UITogoUserMenuDao getUITogoUserMenuDao() {
        return UITogoMenuUserDao;
    }


    public void setUITogoUserMenuDao(UITogoUserMenuDao rankCodeDao) {
        this.UITogoMenuUserDao = rankCodeDao;
    }


}
