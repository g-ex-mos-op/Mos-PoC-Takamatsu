/**
 * 
 */
package jp.co.isid.mos.bird.analysis.posdata.logic;

import jp.co.isid.mos.bird.analysis.posdata.dto.PosDataFormDto;

/**
 * LOGIC【Zip対象オーナーチェック】
 * 作成日:2013/03/15
 * @author xkinu
 *
 */
public interface ZipOnerCheckLogic {
	/**
	 * 実行処理
	 * @param sessionDto
	 * @return
	 */
	public boolean execute(PosDataFormDto sessionDto);
}
