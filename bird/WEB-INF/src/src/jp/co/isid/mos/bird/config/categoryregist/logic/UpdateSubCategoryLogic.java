/*
 * 作成日: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic;

import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;

/**
 * @author xyuchida
 *
 */
public interface UpdateSubCategoryLogic {

    /**
     * 情報サブカテゴリ更新
     * 
     * @param userId 更新ユーザID
     * @param categoryRegistDto カテゴリ登録DTO
     */
    public void execute(String userId, CategoryRegistDto categoryRegistDto);
}
