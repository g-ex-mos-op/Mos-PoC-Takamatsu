/*
 * 作成日: 2008/11/07
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.dao.CodBumonCompanyDao;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonCompanyLogic;

/**
 * 部門リスト検索用ロジック
 * @author K.Nihonyanagi
 */
public class GetBumonCompanyLogicImpl implements GetBumonCompanyLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L06";

    private CodBumonCompanyDao codBumonCompanyDao;
    /**
     * 部門リスト検索処理
	 */
	public List execute(String companyCd) {
		return getCodBumonCompanyDao().getBumonListCompany(companyCd);
	}
    public CodBumonCompanyDao getCodBumonCompanyDao() {
        return codBumonCompanyDao;
    }
    public void setCodBumonCompanyDao(CodBumonCompanyDao codBumonCompanyDao) {
        this.codBumonCompanyDao = codBumonCompanyDao;
    }


}
