/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchConditionDto;

/**
 * オーナ検索処理ロジックインターフェース
 * @author itamoto
 */
public interface SearchOwnerLogic {

    /**
     * オーナ検索処理
     * @param ownerSearchDto
     */
    public List execute(OwnerSearchConditionDto ownerSearchConditionDto, List indexSearchList);
}
