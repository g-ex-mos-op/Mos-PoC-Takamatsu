/*
 * 作成日: 2006/1/12
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.dao.CodBumonDao;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetBumonLogic;

/**
 * 部門リスト検索用ロジック
 * @author m.onodera
 */
public class GetBumonLogicImpl implements GetBumonLogic {

    /** ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA006L05";

    private CodBumonDao userRegistCodBumonDao;

    public CodBumonDao getUserRegistCodBumonDao() {
        return userRegistCodBumonDao;
    }

    public void setUserRegistCodBumonDao(CodBumonDao codBumonDao) {
        this.userRegistCodBumonDao = codBumonDao;
    }

    /**
     * 部門リスト検索処理
	 */
	public List execute(String companyCd) {
		return getUserRegistCodBumonDao().select(companyCd);
	}
}
