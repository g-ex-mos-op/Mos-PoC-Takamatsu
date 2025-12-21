/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;

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
    public void execute(RequestNipoDto requestDto);

}
