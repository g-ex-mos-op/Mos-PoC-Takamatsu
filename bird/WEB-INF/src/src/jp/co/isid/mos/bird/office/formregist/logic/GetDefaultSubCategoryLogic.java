/*
 * 作成日: 2006/02/02
 */
package jp.co.isid.mos.bird.office.formregist.logic;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;

/**
 * デフォルトサブカテゴリの取得
 * @author xytamura
 */
public interface GetDefaultSubCategoryLogic {
    /**
     * デフォルトサブカテゴリの取得
     * @param CondtionDto 条件画面DTO
     * @param String ユーザーID
     * @return String サブカテゴリID
     * @exception ApplicationException
     */
    public String execute(FormConditionDto dto, String userId) throws ApplicationException;
}