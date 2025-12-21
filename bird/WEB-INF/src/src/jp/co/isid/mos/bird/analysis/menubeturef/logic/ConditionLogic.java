/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic;

import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.SessionDto;


/**
 * 条件項目の取得設定ロジック
 * 
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
