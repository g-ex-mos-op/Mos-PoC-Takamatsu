/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.sysadmin.userroleregist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.sysadmin.userroleregist.dao.UIRoleDao;
import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.sysadmin.userroleregist.entity.UIRole;
import jp.co.isid.mos.bird.sysadmin.userroleregist.logic.SearchRoleLogic;

/**
 * @author xylee
 *
 */
public class SearchRoleLogicImpl implements SearchRoleLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA011L01";

	/**
	 * ロール情報DAO
	 */
	private UIRoleDao uIRoleDao;

	/**
	 * BIRDユーザ情報DAO
	 */
	private UIBirdUserDao uIBirdUserDao;
	
	/**
     * ロール情報DAOを取得します。
     * 
     * @return ロール情報DAO
     */
	public UIRoleDao getUIRoleDao() {
		return uIRoleDao;
	}

    /**
     * ロール情報DAOを設定します。
     * 
     * @param uIRoleDao ロール情報DAO
     */
	public void setUIRoleDao(UIRoleDao uIRoleDao) {
		this.uIRoleDao = uIRoleDao;
	}

	/**
     * BIRDユーザ情報を取得します。
     * 
     * @return BIRDユーザ情報DAO
     */
	public UIBirdUserDao getUIBirdUserDao() {
		return uIBirdUserDao;
	}

    /**
     * BIRDユーザ情報DAOを設定します。
     * 
     * @param uIBirdUserDao BIRDユーザ情報DAO
     */
	public void setUIBirdUserDao(UIBirdUserDao uIBirdUserDao) {
		this.uIBirdUserDao = uIBirdUserDao;
	}
	
	/**
	 * ロールリスト取得
	 */
	public Map execute(String userId) {
		
		if (userId == null || "".equals(userId.trim())) {
			throw new NotNullException("ユーザーID");
        }
		//コード欄 半角英数字,文字列長チェック
		CodeVerifier codeVerifier = new CodeVerifier(true);
		if (!codeVerifier.validate(userId) || userId.getBytes().length > 8) {
			throw new InvalidInputException("ユーザーID");
		}
		
		List birdUser = getUIBirdUserDao().getBirdUser(userId);
		Map roleInfo = new HashMap();		
		if (birdUser == null || birdUser.size() <= 0) {
			throw new NotExistException("ユーザー");
        }
		String userTypeCd = ((UIBirdUser) birdUser.get(0)).getUsertypeCd();
		List getRole = getUIRoleDao().getRole(userId);
		List getRoleKanri = getUIRoleDao().getRoleKanri(userId,userTypeCd);
		List uIRoleNew = new ArrayList();
		
		for (int i = 0; i < getRoleKanri.size(); i++) {
			UIRole uIRole = (UIRole) getRoleKanri.get(i);
			String roleCd1 = uIRole.getRoleCd();
			String k = "0";
			for (int j = 0; j < getRole.size(); j++) {
				String roleCd2 = ((UIRole) getRole.get(j)).getRoleCd();
				if(roleCd1.equals(roleCd2)){
					k="1";
					break;
				}
			}
			if(k.equals("0")){
				uIRole.setCheckedRole(false);
				uIRoleNew.add(uIRole);
			}
		}
		for (int m = 0; m < getRole.size(); m++) {
			UIRole uIRole = (UIRole) getRole.get(m);
			uIRole.setCheckedRole(true);
			uIRoleNew.add(uIRole);
		}
		roleInfo.put("rollInfo1st", birdUser);
		roleInfo.put("rollInfo2nd", uIRoleNew);
		
		return roleInfo;
	}
}
