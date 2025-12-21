/*
 * 作成日: 2006/01/16
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;

/**
 * 公開対象の検索
 * @author xytamura
 */
public interface SearchKokaiLogic {

    /**
     * 公開対象の検索
     * @param publicTargetSearchConditionDto 公開対象DTO
     * @param publicTargetSearchDto 公開対象パラメータ受け渡し用DTO
     * @return 公開対象DTO
     */
    public abstract PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchConditionDto,
            PublicTargetSearchDto publicTargetSearchDto);
}