/*
 * 作成日: 2006/01/30
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;

/**
 * 業態の検索ロジック
 * @author xytamura
 */
public interface SearchGyotaiLogic {
    /**
     * 業態の検索ロジック
     * @param publicTargetSearchConditionDto 公開対象DTO
     * @param publicTargetSearchDto 公開対象パラメータ受け渡し用DTO
     * @return
     */
    public abstract PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchConditionDto);
}