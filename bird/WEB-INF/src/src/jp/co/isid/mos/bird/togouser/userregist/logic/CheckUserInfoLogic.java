/*
 * 作成日: 2008/11/06
 */
package jp.co.isid.mos.bird.togouser.userregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;

/**
 * 登録内容のチェックロジックインターフェース
 * @author K.Nihonyanagi
 */
public interface CheckUserInfoLogic {

    /**
     * 登録内容のチェック処理
     * @param UItogoUserDto
     */
    public List execute(UITogoUserDto uiTogoUserDto);
}
