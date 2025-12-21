/*
 * 作成日: 2006/01/11
 */
package jp.co.isid.mos.bird.framework.logic;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * ログイン時にユーザー関連情報をセッションに保持しておく
 * @author xnkusama
 */
public interface UserInfoSetupLogic {
    public BirdUserInfo execute(final String userId, final HttpServletRequest request);
}
