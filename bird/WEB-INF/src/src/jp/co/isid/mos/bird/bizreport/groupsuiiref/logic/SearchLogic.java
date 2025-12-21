package jp.co.isid.mos.bird.bizreport.groupsuiiref.logic;

import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;



/**
 * グループ業績売上推移表
 * @author xkinu
 *
 */
public interface SearchLogic extends GroupSuiiLogic {
	/**
	 * 実行処理
	 * 
	 * @param isCvs
	 * @param parameterDto
	 * @param suiiRefResultDto
	 * @return
	 */
	public SuiiRefResultDto execute(
			boolean isCvs, SuiiRefParameterDto parameterDto, SuiiRefResultDto suiiRefResultDto);
}
