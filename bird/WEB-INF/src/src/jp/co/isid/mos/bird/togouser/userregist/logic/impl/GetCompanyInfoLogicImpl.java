/*
 * 作成日: 2008/11/10
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.dao.CodCompanyDao;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetCompanyInfoLogic;

/**
 * ユーザー登録画面のプルダウンに表示する会社情報を取得する
 * @author K.Nihonyanagi
 *
 */
public class GetCompanyInfoLogicImpl implements GetCompanyInfoLogic {
	/* ロジックNo */
    public static final String LOGIC_ID = "BUR001L02";

    private CodCompanyDao codCompanyDao;

    public CodCompanyDao getCodCompanyDao() {
        return codCompanyDao;
    }

    public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
        this.codCompanyDao = codCompanyDao;
    }

	public List execute(String companyCd) {
		return getCodCompanyDao().getCompany(companyCd);
	}
}
