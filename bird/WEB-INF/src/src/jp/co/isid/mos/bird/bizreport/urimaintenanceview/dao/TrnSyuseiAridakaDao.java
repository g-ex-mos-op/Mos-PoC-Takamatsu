/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.TrnSyuseiAridaka;

/**
 * DAOy‰ïŒv‹æ•ª•Êİ‚“úŸC³z
 * 
 * ì¬“ú:2012/08/10
 * @author xkawa
 *
 */
public interface TrnSyuseiAridakaDao {
	/** ƒGƒ“ƒeƒBƒeƒBƒNƒ‰ƒXFTrnSyuseiAridaka */
	public static final Class BEAN = TrnSyuseiAridaka.class;
	/** ˆø”:”„ãC³–¾×ŒŸõ */
	public static final String select_ARGS = "sysDate, companyCd, syuseiDate";

	/**
	 * ”„ãC³–¾×ŒŸõ
	 * 
	 * @param sysDate(•K{)
	 * @param companyCd(•K{)
	 * @param yyyyMMdd(•K{)
	 * @return List[[‰ïŒv‹æ•ª•Êİ‚“úŸC³]]
	 */
	public List select(String sysDate, String companyCd, String syuseiDate);
	
}
