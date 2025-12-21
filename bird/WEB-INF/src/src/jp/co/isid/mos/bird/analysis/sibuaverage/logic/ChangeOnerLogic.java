/**
 * 
 */
package jp.co.isid.mos.bird.analysis.sibuaverage.logic;

import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto;

/**
 * オーナー変更ロジック
 * 
 * 集計区分の各支部情報の変更処理を行います。
 * 作成日:2012/11/02
 * @author xkinu
 *
 */
public interface ChangeOnerLogic {
	/**
	 * 実行処理
	 * @param sessionDto
	 * @param requestDto
	 */
    public void execute(SibuAverageDto sessionDto, SibuAverageReqDto requestDto);

}
