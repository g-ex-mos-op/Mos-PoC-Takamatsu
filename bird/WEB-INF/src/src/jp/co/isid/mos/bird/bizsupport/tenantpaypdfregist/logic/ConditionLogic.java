/**
 * 初期化処理ロジック
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;

/**
 * テナント入金明細PDF情報登録画面初期化ロジック
 * 
 * 作成日:2009/06/19
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
