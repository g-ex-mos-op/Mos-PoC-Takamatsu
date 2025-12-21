/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic;

import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.SessionDto;


/**
 * 条件項目の取得設定ロジック
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public interface ConditionLogic {
	/**
	 * 処理実行
	 * 
	 * @param sessionDto
	 * @param requestDto
	 */
    public void execute(SessionDto sessionDto, RequestDto requestDto);

}
