/*
 * 作成日: 2006/01/11
 */
package jp.co.isid.mos.bird.framework.logic;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.entity.UILoginBirdUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ログインロジック
 * @author xnkusama
 */
public interface LoginLogic {
    /* ログイン */
    public UILoginBirdUser execute(final LoginDto dto, HttpServletRequest request) throws ApplicationException;
}
