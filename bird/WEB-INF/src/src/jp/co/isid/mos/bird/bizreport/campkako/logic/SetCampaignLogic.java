/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.logic;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;

/**
 * キャンペーン条件項目情報設定ロジックインターフェース
 * @author xnkusama
 *
 */
public interface SetCampaignLogic {
	/**
	 * 処理実行
	 * 
	 * @param requestDto
	 */
    public void execute(RequestNipoDto requestDto);

}
