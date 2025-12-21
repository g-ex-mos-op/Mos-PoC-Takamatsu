/*
 * 作成日: 2006/1/12
 */
package jp.co.isid.mos.bird.commonform.usersearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.dao.CodBumonDao;
import jp.co.isid.mos.bird.commonform.usersearch.logic.GetBumonLogic;

/**
 * 部門リスト検索用ロジック
 * @author m.onodera
 */
public class GetBumonLogicImpl implements GetBumonLogic {

    public static final String LOGIC_ID = "BCO003L01";
    
    private CodBumonDao codBumonDao;

    public CodBumonDao getCodBumonDao() {
        return codBumonDao;
    }

    public void setCodBumonDao(CodBumonDao codBumonDao) {
        this.codBumonDao = codBumonDao;
    }

    /**
     * 部門リスト検索処理
	 */
	public List execute(String companyCd) {
		return getCodBumonDao().select(companyCd);
	}
}
