/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimtotal.logic;

import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalRequestDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalSessionDto;

/**
 * 検索ロジック
 * 
 * @author xnkusama
 *
 */
public interface SearchLogic {
    
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 * @return
	 */
    public void execute(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto);

}
