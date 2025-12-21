/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;

/**
 * テナント入金明細PDF情報検索ロジック
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 */
    public List execute(RequestDto requestDto);

}
