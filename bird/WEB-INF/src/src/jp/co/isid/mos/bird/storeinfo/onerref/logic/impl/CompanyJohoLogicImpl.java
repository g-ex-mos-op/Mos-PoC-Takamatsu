/*
 * 作成日: 2006/3/7
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.dao.CodCompanyJohoDao;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.CompanyJohoLogic;

/**
 * 会社コード取得ロジック
 * @author itamoto
 */
public class CompanyJohoLogicImpl implements CompanyJohoLogic {

    public static final String LOGIC_ID = "BSI002L01";

    /**
     * 管理会社情報（CodCompanyJohoDao）
     */
    private CodCompanyJohoDao codCompanyJohoDao;
    
	/**
	 * 管理会社情報Daoの設定
	 * @return codCompanyJohoDao を戻します。
	 */
	public CodCompanyJohoDao getCodCompanyJohoDao() {
		return codCompanyJohoDao;
	}
	/**
	 * 管理会社情報Daoの設定
	 * @param codCompanyJohoDao codCompanyJohoDao を設定。
	 */
	public void setCodCompanyJohoDao(CodCompanyJohoDao codCompanyJohoDao) {
		this.codCompanyJohoDao = codCompanyJohoDao;
	}

	/**
	 * ユーザー所属管理会社の検索
	 * @return 検索結果
	 */
	public List execute(String userId) {
		return codCompanyJohoDao.select(userId);
	}
}
