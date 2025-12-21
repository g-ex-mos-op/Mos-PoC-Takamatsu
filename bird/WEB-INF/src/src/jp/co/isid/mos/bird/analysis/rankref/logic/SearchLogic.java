/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.logic;

import java.util.List;

import jp.co.isid.mos.bird.analysis.rankref.dto.RequestDto;

/**
 * 検索ロジックインターフェース
 * 
 * 作成日:2008/10/21
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 * @return
	 */
    public List execute(RequestDto requestDto);

}
