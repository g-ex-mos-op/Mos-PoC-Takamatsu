/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.logic;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;

/**
 * 会社条件項目情報設定ロジックインターフェース
 * @author xnkusama
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
