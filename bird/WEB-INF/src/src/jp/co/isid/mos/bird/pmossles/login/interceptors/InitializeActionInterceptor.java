/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.dto.ErrHtmlElementDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.pmossles.login.action.LoginAction;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * p-mosslesログイン処理アクションリスナー
 * 
 * ユーザーセッション関連オブジェクトの初期化
 * 
 * 作成日:2010/12/03
 * @author xkinu
 *
 */
public class InitializeActionInterceptor extends AbstractInterceptor {
    static final long serialVersionUID = 1L;

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        HttpServletRequest request = getRequest();
    	//セッションを取得します。
        HttpSession session = request.getSession();
        if(request.getHeader("referer") == null) {
	        // ログ用：リクエストURLをセッションから削除します。
	        session.removeAttribute(LoginAction.PK_FIRSTURL);
	        // ログイン用パラメータ：店舗識別コードをセッションから削除します。
	        session.removeAttribute(LoginAction.PK_VIEWID);
	        // ログイン用パラメータ：店舗識別コードをセッションから削除します。
	        session.removeAttribute(LoginAction.PK_LOGPRM);
        }
        else if (!isError() && session.getAttribute(LoginAction.PK_FIRSTURL) == null) {
	        //p-mossles起動ログインURLをセッションへ設定します。
	        session.setAttribute(LoginAction.PK_FIRSTURL
	        		, request.getHeader("referer"));
	        //メニューアクション取得
	        MenuActionImpl menuAction = getMenuAction();
	        //メニュー情報の初期化
	        menuAction.setInitViewId(LoginAction.SCREEN_ID+"V01");
	        menuAction.setInitMenuId("");
	        menuAction.setInitSubMenuId("");
        }
       	return invocation.proceed();
    }
    /**
     * リクエスト情報取得処理
     * @return
     */
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) SingletonS2ContainerFactory.getContainer().getComponent("request");
    }
    /**
     * メニューアクション取得処理
     * 
     * @return
     */
    private MenuActionImpl getMenuAction() {
        return (MenuActionImpl) SingletonS2ContainerFactory.getContainer().getComponent(MenuActionImpl.class);
    }
    /**
     * エラー判断処理
     * @return
     */
	private boolean isError() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		LoginDto loginDto = (LoginDto) container.getComponent(LoginDto.class);
		ErrHtmlElementDto errDto = 
			(ErrHtmlElementDto) container.getComponent(ErrHtmlElementDto.class);
		
		return !CommonUtil.isNull(loginDto.getMsgSessionError()) 
					|| errDto.isException();
	}
}
