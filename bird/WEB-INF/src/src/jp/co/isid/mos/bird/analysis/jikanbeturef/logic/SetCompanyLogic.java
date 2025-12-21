/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic;

import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.SessionDto;

/**
 * 会社条件項目情報設定ロジックインターフェース
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public interface SetCompanyLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 */
    public void execute(SessionDto sesstion, RequestDto requestDto);

}
