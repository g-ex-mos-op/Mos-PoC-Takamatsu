/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.rolesearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.rolesearch.dao.CtlRoleBunruiDao;
import jp.co.isid.mos.bird.commonform.rolesearch.logic.SearchRoleBunruiLogic;

/**
 * @author xyuchida
 *
 */
public class SearchRoleBunruiLogicImpl implements SearchRoleBunruiLogic {

    public static final String LOGIC_ID = "BCO004L01";

    /**
	 * ロール分類情報DAO
	 */
	private CtlRoleBunruiDao ctlRoleBunruiDao;

	/**
     * ロール分類情報DAOを取得します。
     * 
     * @return ロール分類情報DAO
     */
	public CtlRoleBunruiDao getCtlRoleBunruiDao() {
		return ctlRoleBunruiDao;
	}

    /**
     * ロール分類情報DAOを設定します。
     * 
     * @param ctlRoleBunruiDao ロール分類情報DAO
     */
	public void setCtlRoleBunruiDao(CtlRoleBunruiDao ctlRoleBunruiDao) {
		this.ctlRoleBunruiDao = ctlRoleBunruiDao;
	}

	/**
	 * ロール分類リスト取得
	 */
	public List execute() {
		return getCtlRoleBunruiDao().select();
	}
}
