/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.logic;

import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;

/**
 * 入力値チェックロジックインターフェース
 * 
 * 作成日:2010/11/18
 * @author xkinu
 *
 */
public interface CheckInputParamLogic {
	/**
	 * 
	 * @param birdDateInfo BIRD日付情報
	 * @param registDto    DTO【POS速報設定情報保持】
	 */
	public void execute(
			  BirdDateInfo birdDateInfo
			, PosReportRegistDto registDto);
}
