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
public interface CheckCategoryLogic {

    /**
     * 登録内容チェック
     * 
     * @param categoryRegistDto カテゴリ登録DTO
     */
    public void execute(CategoryRegistDto categoryRegistDto);
}
