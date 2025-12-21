/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;

/**
 * 検索結果取得ロジック
 * 
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
    public List execute(RequestNipoDto requestDto);

}
