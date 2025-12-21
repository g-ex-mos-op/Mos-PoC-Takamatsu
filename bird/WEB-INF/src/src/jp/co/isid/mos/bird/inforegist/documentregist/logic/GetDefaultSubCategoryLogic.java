/*
 * 作成日: 2006/02/02
 */
package jp.co.isid.mos.bird.inforegist.documentregist.logic;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistConditionDto;

/**
 * デフォルトサブカテゴリの取得
 * @author xnkusama
 */
public interface GetDefaultSubCategoryLogic {
    /**
     * デフォルトサブカテゴリの取得
     * @param CondtionDto 条件画面DTO
     * @return String サブカテゴリID
     * @exception ApplicationException
     */
    public String getDefaultSubCategory(DocumentRegistConditionDto dto,MstUser mstUser) throws ApplicationException;
}