/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.logic;

import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;

/**
 * 会社条件項目情報設定ロジックインターフェース
 * @author xkinu
 *
 */
public interface SetCompanyLogic {
	/**
	 * 処理実行
	 * 
	 * @param sessionDto
	 * @param requestDto
	 */
    public void execute(SessionDto sessionDto, RequestDto requestDto);

}
