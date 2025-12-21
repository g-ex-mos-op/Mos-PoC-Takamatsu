/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;

/**
 * 修了情報全クリアロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface AllClearLogic {
	/**
	 * 処理実行
	 * 
	 * @param dto
	 * @return
	 */
	public int execute(MlCompletionregistDto dto);
}
