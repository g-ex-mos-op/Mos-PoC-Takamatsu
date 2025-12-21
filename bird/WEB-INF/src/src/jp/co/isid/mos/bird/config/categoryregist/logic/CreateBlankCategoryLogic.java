/*
 * 作成日: 2006/02/22
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;

/**
 * @author xyuchida
 *
 */
public interface CreateBlankCategoryLogic {

    /**
     * ブランクカテゴリ生成
     * 
     * @param categoryRegistDto カテゴリ登録DTO
     * @return ブランクカテゴリリスト
     */
    public List execute(CategoryRegistDto categoryRegistDto);
}
