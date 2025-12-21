/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearch.dto.SvSearchConditionDto;

/**
 * SV検索処理ロジックインターフェース
 * @author kinugawa (ASPAC)
 */
public interface SearchLogic {

    /**
     * SV検索処理
     * 
     * @param svSearchConditionDto
     * @param indexSearchList
     * @return
     */
    public List execute(SvSearchConditionDto svSearchConditionDto, List indexSearchList);
}
