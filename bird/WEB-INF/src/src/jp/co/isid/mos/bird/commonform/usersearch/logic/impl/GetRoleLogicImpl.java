/*
 * 作成日: 2006/06/28
 *
 */
package jp.co.isid.mos.bird.commonform.usersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.dao.CodRoleDao;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetRoleLogic;

/**
 * ロールリストの取得ロジック
 * @author inazawa
 *
 */
public class GetRoleLogicImpl implements GetRoleLogic {
	/* ロジックNo */
    public static final String LOGIC_ID = "BCO003L04";

    private CodRoleDao codRoleDao;

    public CodRoleDao getCodRoleDao() {
        return codRoleDao;
    }

    public void setCodRoleDao(CodRoleDao codRoleDao) {
        this.codRoleDao = codRoleDao;
    }

	public List execute() {
		return getCodRoleDao().select();
	}
}
