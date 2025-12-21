/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.SessionDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.RequestDto;


/**
 * LOGIC【条件項目の取得設定】インターフェース
 * 
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public interface ConditionLogic {
	/**
	 * 実行
	 * @param birdUserInfo
	 * @param birdDateInfo
	 * @param sessionDto
	 * @param requestDto
	 */
	public void execute(
			BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
		  , SessionDto sessionDto, RequestDto requestDto);
}
