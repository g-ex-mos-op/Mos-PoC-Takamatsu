/*
 * 作成日: 2006/2/20
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;

/**
 * ユーザ情報の更新ロジックインターフェース
 * @author itamoto
 */
public interface UpdateUserLogic {

    /**
     * 登録内容のチェック処理
     * @param userRegistDto
     */
    public List execute(UserRegistDto userRegistDto);
}
