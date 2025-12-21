/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.dao.UIRoleDao;
import jp.co.isid.mos.bird.commonform.rolesearch.logic.SearchRoleLogic;

/**
 * @author xyuchida
 *
 */
public class SearchRoleLogicImpl implements SearchRoleLogic {

    public static final String LOGIC_ID = "BCO004L02";

    /**
	 * ロール情報DAO
	 */
	private UIRoleDao uIRoleDao;

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
	 * ロールリスト取得
	 */
	public List execute(String bunruiCd) {
		return getUIRoleDao().select(bunruiCd);
	}

	/**
	 * 指定ロールコードに対応するロールリスト取得
	 */
	public List execute(List roleCds) {
        if (roleCds == null || roleCds.size() <= 0) {
            return new ArrayList();
        }
		return getUIRoleDao().selectByRoleCd(roleCds);
	}
}
