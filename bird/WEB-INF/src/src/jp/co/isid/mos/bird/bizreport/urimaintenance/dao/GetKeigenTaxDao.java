/**
 *
 */
package jp.co.isid.mos.bird.bizreport.urimaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.GetKeigenTaxData;

/**
 * DAOyŒ»‹àİ‚“úŸ”„ãÁ”ïÅ–¾×C³z
 *
 * ì¬“ú:2019/07/09
 * @author USIŸR
 *
 */
public interface GetKeigenTaxDao {
	/** ƒGƒ“ƒeƒBƒeƒBƒNƒ‰ƒXFGetKeigenTaxData */
	public static final Class BEAN = GetKeigenTaxData.class;
	/** ˆø”:V‹K“o˜^ */
	public static final String insert_ARGS = "entity";
	/** ˆø”:XV“o˜^ */
	public static final String update_ARGS = "entity";
	/** ˆø”:C³ó‹µŒŸõ */
	public static final String select_ARGS = "sysData, companyCd, miseCd, targetYM";
	/** ˆø”:Œ»‹àİ‚i“úŸj”„ãÁ”ïÅ–¾×ŒŸõ */
	public static final String selectBD66_ARGS = "sysData, companyCd, miseCd, targetYM";

	/**
	 * V‹K“o˜^
	 * @param entity
	 * @return
	 */
	public int insert(GetKeigenTaxData entity);

	/** NO_PERSISTENT_PROPSƒAƒmƒe[ƒVƒ‡ƒ“ */
	public static final String update_NO_PERSISTENT_PROPS = "firstUser, firstPgm, firstTmsp";
	/**
	 * XV“o˜^
	 * @param entity
	 * @return
	 */
	public int update(GetKeigenTaxData entity);
	/**
	 * C³ó‹µŒŸõ
	 *
	 * @param sysData(•K{)
	 * @param companyCd(•K{)
	 * @param miseCd(•K{)
	 * @param targetYM(•K{)
	 * @return List[[Œ»‹àİ‚i“úŸj”„ãÁ”ïÅ–¾×C³]]
	 */
	public List select(String sysData, String companyCd, String miseCd, String targetYM);
}
