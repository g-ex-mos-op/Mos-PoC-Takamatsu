package jp.co.isid.mos.bird.commonform.svsearchnew.logic;


import java.util.List;

import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchConditionDto;

/**
 * SV検索処理ロジックインターフェース
 * @author kusama
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
