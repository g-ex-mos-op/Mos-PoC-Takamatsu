/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.logic;

import jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto.RequestDto;

/**
 * オーダー提供時間ダウンロード
 * 
 * 作成日:2009/10/15
 * @author xkinu
 *
 */
public interface ConditionLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 */
    public void execute(RequestDto requestDto);

}
