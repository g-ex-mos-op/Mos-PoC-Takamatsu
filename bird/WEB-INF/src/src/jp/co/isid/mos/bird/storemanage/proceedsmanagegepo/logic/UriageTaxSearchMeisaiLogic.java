package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.UriageTaxMeisaiRequestResultDto;

/**
 * 売上金管理月報売上消費税明細検索ロジック インターフェース
 * 2019/07/16
 * @author USI 張
 *
 */
public interface UriageTaxSearchMeisaiLogic {

	/**
     * 売上消費税明細検索
     * @param proceedsManageGepoDto 売上金管理月報・条件部DTOクラス
     * @param meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTOクラス
     * @param UriageTaxMeisaiRequestResultDto 売上消費税明細照会・結果部DTOクラス
     */
    public void uriageTaxExecute(
    		ProceedsManageGepoDto proceedsManageGepoDto
		  , MeisaiRequestDto meisaiRequestJokenDto
		  , UriageTaxMeisaiRequestResultDto UriageTaxMeisaiRequestResultDto);
}
