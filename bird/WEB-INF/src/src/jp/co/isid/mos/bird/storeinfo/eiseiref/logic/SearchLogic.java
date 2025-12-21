/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.RequestDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.ResultDto;


/**
 * LOGIC【検索結果取得】インターフェース
 * 
 * 作成日:2012/12/05
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * 実行
	 * 
	 * @param birdUserInfo
	 * @param birdDateInfo
	 * @param requestDto
	 * @return
	 */
	public ResultDto execute(
			BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
		  , RequestDto requestDto);
}