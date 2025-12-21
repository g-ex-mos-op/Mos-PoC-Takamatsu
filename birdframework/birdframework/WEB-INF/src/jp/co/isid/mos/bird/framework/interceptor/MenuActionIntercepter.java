/*
 * 作成日: 2005/12/15
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ヘルプアクション用Intercepter
 * @author xnkusama
 */
public class MenuActionIntercepter extends AbstractInterceptor{
    
    static final long serialVersionUID = 1L;
    
    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");

        String pageKey = "BPO001V01";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String s = (String) e.nextElement();
            if (s.indexOf("showViewId") != -1) {
                if (!isNull(pageKey)) {
                    pageKey = request.getParameter(s);
                }
                break;
            }
        }
        //遷移先のVIEWIDをセット
        getMenuActionImpl().setShowViewId(pageKey);
        
        //各画面の初期アクションを実行
        Object ret = invocation.proceed();
        
        return ret;
    }
    private MenuActionImpl getMenuActionImpl() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (MenuActionImpl) container.getComponent(MenuActionImpl.class);
    }
    
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}