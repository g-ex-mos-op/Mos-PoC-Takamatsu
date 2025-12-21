/*
 * ì¬“ú: 2006/03/02
 */
package jp.co.isid.mos.bird.bizadmin.accountref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountref.entity.CtlUserCompany;

/**
 * @author —¶
 */
public interface CtlUserCompanyDao {
	public static final Class BEAN = CtlUserCompany.class;
	
	public static final String select_ARGS = "userId, zokuseiKbn";
	/**
	 * 
	 * @param userId [•K{]
	 * @param zokuseiKbn
	 * @return
	 */
	public List select(String userId, String zokuseiKbn);
}
