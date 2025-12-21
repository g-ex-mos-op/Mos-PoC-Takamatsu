/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.logic;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;

/**
 * 条件項目の取得設定ロジック
 * 
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * 処理実行
	 * 
	 * @param sessionDto
	 * @param requestDto
	 * @return
	 */
    public List execute(SessionDto sessionDto, RequestDto requestDto);

}