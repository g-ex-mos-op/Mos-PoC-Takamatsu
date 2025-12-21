/**
 * 
 */
package jp.co.isid.mos.bird.analysis.posdata.dao;

import java.util.List;

import jp.co.isid.mos.bird.analysis.posdata.entity.UIOnerSibu;

/**
 * DAOyƒI[ƒi[x•”î•ñz
 * 
 * ì¬“ú:2013/02/26
 * @author xkinu
 *
 */
public interface UIZipOnerSibuDao {
	public static final Class BEAN = UIOnerSibu.class;
	public static final String select_ARGS = "companyCd, onerCd";
	/**
	 * ŒŸõ
	 * @param companyCd
	 * @param onerCd
	 * @return
	 */
	public List select(String companyCd, String onerCd);
}
