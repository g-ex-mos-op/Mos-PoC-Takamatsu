/*
 * 作成日: 2006/2/17
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;

/**
 * 登録内容のチェックロジックインターフェース
 * @author itamoto
 */
public interface CheckUserLogic {

    /**
     * 登録内容のチェック処理
     * @param userRegistDto
     */
    public List execute(UserRegistDto userRegistDto);
}
