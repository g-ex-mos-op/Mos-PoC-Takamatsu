/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;

/**
 * 食材準備目安表情報検索ロジックインターフェース
 * 
 * 作成日:2008/09/19
 * @author xkinu
 *
 */
public interface SearchShokuzaiMeyasuLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 * @return
	 */
    public List execute(MenuBetuReqDto requestDto);

}
