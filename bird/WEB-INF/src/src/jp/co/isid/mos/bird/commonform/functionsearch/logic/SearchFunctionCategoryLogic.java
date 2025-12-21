/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.functionsearch.dto.FunctionSearchConditionDto;

/**
 * 機能カテゴリ検索処理ロジックインターフェース
 * @author itamoto
 */
public interface SearchFunctionCategoryLogic {

    /**
     * 機能カテゴリ検索処理
     * @param functionSearchConditionDto
     */
    public List execute(FunctionSearchConditionDto functionSearchConditionDto);
}
