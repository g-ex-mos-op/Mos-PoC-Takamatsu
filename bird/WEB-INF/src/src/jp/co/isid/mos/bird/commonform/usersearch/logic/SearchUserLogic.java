/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.logic;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchConditionDto;

/**
 * ユーザー検索処理ロジックインターフェース
 * @author m.onodera
 */
public interface SearchUserLogic {

    /**
     * ユーザー検索処理
     * @param userSearchDto
     */
    public List execute(UserSearchConditionDto userSearchConditionDto);
}
