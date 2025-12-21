/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic;

import java.util.List;

import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;

/**
 * 曜日別情報検索ロジック
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public interface SearchWeekLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 * @return
	 */
    public List execute(RequestDto requestDto);

}
