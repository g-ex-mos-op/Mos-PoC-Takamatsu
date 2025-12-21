/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.functionsearch.dao.UIFunctionDao;
import jp.co.isid.mos.bird.commonform.functionsearch.dto.FunctionSearchConditionDto;
import jp.co.isid.mos.bird.commonform.functionsearch.logic.SearchFunctionLogic;

/**
 * 機能カテゴリ検索処理ロジック
 * @author itamoto
 */
public class SearchFunctionLogicImpl implements SearchFunctionLogic {

    /* UIGroupTogoOnerDao */
    private UIFunctionDao uiFunctionDao;

    /**
     * 機能検索Daoを取得します。
     * @return uiFunctionDao
     */
    public UIFunctionDao getUIFunctionDao() {
        return uiFunctionDao;
    }

    /**
     * 機能検索Daoを設定します。
     * @param uiFunctionDao
     */
    public void setUIFunctionDao(UIFunctionDao uiFunctionDao) {
        this.uiFunctionDao = uiFunctionDao;
    }

    /**
     * 機能検索処理
     * @param functionSearchConditionDto
     * @param indexSearchList
     */
    public List execute(FunctionSearchConditionDto functionSearchConditionDto) {
        return getUIFunctionDao().select(
                functionSearchConditionDto.getFunctionCategoryId(),
                (functionSearchConditionDto.getFunctionName() == null) ? null
                        : "%" + functionSearchConditionDto.getFunctionName() + "%");
    }
}
