/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;

/**
 * LOGICyŒŸõŒ‹‰Êæ“¾z
 * 
 * ì¬“ú:2013/05/02
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * Àsˆ—
	 * 
	 * @param isCvs
	 * @param parameterDto
	 * @param suiiRefResultDto
	 * @return
	 */
	public SuiiRefResultDto execute(
			boolean isCvs, SuiiRefParameterDto parameterDto, SuiiRefResultDto suiiRefResultDto);

}
