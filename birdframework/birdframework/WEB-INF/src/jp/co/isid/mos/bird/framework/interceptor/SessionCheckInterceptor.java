/*
 * 作成日: 2006/01/19
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoSessionException;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * セッションチェック用Interceptor
 * e-mosslesから遷移しない画面用
 * @author xnkusama
 */
public class SessionCheckInterceptor extends AbstractInterceptor{
    
    static final long serialVersionUID = 1L;
    private static final String SESSION_KEY_BIRD_USER_INFO = "birdUserInfo";
    
    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //e-mosslesユーザーID
        String eMosUserId = "";
        //BIRDユーザーID
        String birdUserId = "";
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpSession session = (HttpSession) container.getComponent("session");
        
        if (session == null) {
            throw new NoSessionException();
        }
        
        BirdUserInfo userInfo = (BirdUserInfo) session.getAttribute(SESSION_KEY_BIRD_USER_INFO);
        if (userInfo == null) {
            throw new NoSessionException();
        }
        
        //各画面の初期アクションを実行
        Object ret = invocation.proceed();

        return ret;
    }
}
