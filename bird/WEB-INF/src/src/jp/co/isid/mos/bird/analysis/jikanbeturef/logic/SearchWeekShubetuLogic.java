/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic;

import java.util.List;

import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;

/**
 * メニュー別種別売上情報検索ロジックインターフェース
 * 
 * 作成日:2008/09/19
 * @author xkinu
 *
 */
public interface SearchWeekShubetuLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 * @return
	 */
    public List execute(RequestDto requestDto);

}
