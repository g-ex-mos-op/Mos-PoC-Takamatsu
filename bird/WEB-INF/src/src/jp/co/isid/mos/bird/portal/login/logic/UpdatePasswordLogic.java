/*
 * 作成日: 2006/01/12
 */
package jp.co.isid.mos.bird.portal.login.logic;

import jp.co.isid.mos.bird.framework.dto.LoginDto;

/**
 * パスワード変更ロジック インターフェイス
 * @author xnkusama
 */
public interface UpdatePasswordLogic {
    /* パスワード変更 */
    public void update(final LoginDto dto);
}
