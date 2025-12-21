/*
 * 作成日: 2006/01/30
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic;

import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;

/**
 * 公開対象データ作成ロジック
 * @author xytamura
 */
public interface CreateKokaiTargetLogic {
    
    public abstract PublicTargetSearchDto execute(
            PublicTargetSearchConditionDto selectedData,
            PublicTargetSearchDto returnData);
}