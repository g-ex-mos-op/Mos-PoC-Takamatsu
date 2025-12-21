/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic;

import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.SessionDto;

/**
 * 会社条件項目情報設定ロジックインターフェース
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
