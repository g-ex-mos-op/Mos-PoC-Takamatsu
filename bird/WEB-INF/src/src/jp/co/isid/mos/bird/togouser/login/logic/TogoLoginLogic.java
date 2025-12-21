/*/
 * 作成日: 2008/10/28
 */
package jp.co.isid.mos.bird.togouser.login.logic;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.togouser.login.dto.TogoUserLoginDto;
import jp.co.isid.mos.bird.togouser.login.entity.UITogoUser;

/**
 * ログインロジック
 * @author K.Nihonyanagi
 */
public interface TogoLoginLogic {
    /* ログイン */
    public UITogoUser execute(final TogoUserLoginDto dto, HttpServletRequest request) throws ApplicationException;
}
