package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestResultDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;

/**
 * 売上金管理月報明細検索ロジック インターフェース
 * @author xogawa
 *
 */
public interface SearchMeisaiLogic {

    /**
     * 明細検索
     * @param proceedsManageGepoDto 売上金管理月報・条件部DTOクラス
     * @param meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTOクラス
     * @param meisaiRequestResultDto 売上金管理月報明細照会・結果部DTOクラス
     */
    public void execute(
    		ProceedsManageGepoDto proceedsManageGepoDto
		  , MeisaiRequestDto meisaiRequestJokenDto
		  , MeisaiRequestResultDto meisaiRequestResultDto);

}
