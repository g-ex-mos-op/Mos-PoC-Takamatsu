/*
 * çÏê¨ì˙: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UITaishoKenshuInfo;

/**
 * @author Noh
 *
 */
public interface UITaishoKenshuInfoDao {
	
	public static final Class BEAN = UITaishoKenshuInfo.class;
    public static final String select_ARGS = "sysDate, userTypeCd, companyCd";
	/**
	 * 
	 * @param sysDate
	 * @param userTypeCd
	 * @param companyCd
	 * @return
	 */
	public List select(String sysDate, String userTypeCd, String companyCd);
}
