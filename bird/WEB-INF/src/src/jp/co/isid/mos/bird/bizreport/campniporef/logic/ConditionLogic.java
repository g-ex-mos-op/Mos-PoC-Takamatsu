/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;

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
	 * @param requestDto
	 * @return
	 */
    public void execute(RequestNipoDto requestDto);

}
