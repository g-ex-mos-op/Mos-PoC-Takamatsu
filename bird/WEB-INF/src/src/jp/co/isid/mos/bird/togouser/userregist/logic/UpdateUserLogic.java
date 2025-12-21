/*
 * 作成日: 2008/11/13
 */
package jp.co.isid.mos.bird.togouser.userregist.logic;

import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;

/**
 * ユーザ情報の更新ロジックインターフェース
 * @author K.Nihonyanagi
 */
public interface UpdateUserLogic {

    /**
     * 登録内容のチェック処理
     * @param uiTogoUserDto
     */
    public void execute(UITogoUserDto uiTogoUserDto);
}
