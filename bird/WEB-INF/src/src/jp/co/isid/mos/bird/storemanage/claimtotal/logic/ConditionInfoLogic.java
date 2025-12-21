/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimtotal.logic;

import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalRequestDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalSessionDto;

/**
 * 条件項目の取得設定ロジック
 * 
 * @author xnkusama
 *
 */
public interface ConditionInfoLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 * @return
	 */
    public void execute(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto);

}
