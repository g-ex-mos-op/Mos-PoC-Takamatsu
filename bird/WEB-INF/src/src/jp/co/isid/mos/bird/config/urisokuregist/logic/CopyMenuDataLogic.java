/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.config.urisokuregist.dto.RequestDto;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;

/**
 * [機能設定]【売上速報設定】
 * 前月分メニュー情報コピー処理
 * ロジックインターフェース
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public interface CopyMenuDataLogic {
	/**
	 * 実行処理
	 * 
	 * @param sessionDto
	 * @param requestDto
	 * @return
	 */
	public List execute(SessionDto sessionDto, RequestDto requestDto);
}
