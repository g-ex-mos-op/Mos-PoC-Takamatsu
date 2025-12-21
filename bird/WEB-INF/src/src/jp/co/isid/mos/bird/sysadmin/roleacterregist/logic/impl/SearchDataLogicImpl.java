/*
 * 作成日: 2006/02/01
 */
package jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.UIBirdMenuDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.dao.UIRoleDao;
import jp.co.isid.mos.bird.sysadmin.roleacterregist.logic.SearchDataLogic;

/**
 * 権限未設定BIRDメニュー・ロールの検索
 * @exception ApplicationException
 * @return List
 */
public class SearchDataLogicImpl implements SearchDataLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA007L01";

	private UIRoleDao uIRoleDao;
	private UIBirdMenuDao uIBirdMenuDao;
	private String birdMenu = "BIRDMENU";
	private String role = "ROLE";
	
	/* BIRDメニューのhashMapの名称 */
	public String getBirdMenu() {
		return birdMenu;
	}
	
	public String getRole() {
		return role;
	}
	
	/* UIRoleDao DI */
	public void setUIRoleDao(UIRoleDao uIRoleDao) {
		this.uIRoleDao = uIRoleDao;
	}
	
	public UIRoleDao getUIRoleDao() {
		return this.uIRoleDao;
	}
	
	/* UIBirdMenuDao DI */
	public void setUIBirdMenuDao(UIBirdMenuDao uIBirdMenuDao) {
		this.uIBirdMenuDao = uIBirdMenuDao;
	}
	
	public UIBirdMenuDao getUIBirdMenuDao() {
		return this.uIBirdMenuDao;
	}

	/**
	 * 権限未設定BIRDメニュー・ロールの検索
	 * @author xkhata
	 */
	
	public Map execute(Map listMap) throws ApplicationException {
		Map listHashMap = new HashMap();
		
        //初期化処理が行われていない場合(false)
		if ( !((Boolean)listMap.get("init")).booleanValue() ) {
			listHashMap.put( birdMenu,getUIBirdMenuDao().getBirdMenu() );
		} 
        
        //初期化処理が行われている場合(true)
        else {
            
            //プルダウンにて機能が選択されている場合
			if ( listMap.get("pull").equals("0") ) {
				listHashMap.put( birdMenu,getUIBirdMenuDao().getBirdMenu() );
			} 
            //プルダウンにてロールが選択されている場合
            else {
				listHashMap.put( role,getUIRoleDao().getRole() );
			}
		}			
		return listHashMap;
	}

}
