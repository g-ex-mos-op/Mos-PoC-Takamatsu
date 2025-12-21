/**
 * 初期化処理ロジック
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;

/**
 * テナント入金明細PDF情報登録ロジック
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface RegistLogic {
	/**
	 * 処理実行
	 * 
	 * @param sessionDto
	 */
    public void execute(SessionDto sessionDto);

}
