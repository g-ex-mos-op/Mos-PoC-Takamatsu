/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;

/**
 * 検索結果取得ロジック
 * 
 * @author xnkusama
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
