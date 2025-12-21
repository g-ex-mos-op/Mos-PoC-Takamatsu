package jp.co.isid.mos.bird.minisite.login.interceptors;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * アクセスログ出力処理
 *
 * @author ASPAC
 */
public class LogInterceptor extends AbstractInterceptor {

    static final long serialVersionUID = 1L;

    private static Logger logger_ = Logger.getLogger(ErrorLogInterceptor.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {

        StringBuffer buf = new StringBuffer(100);
		Object ret = null;
		Throwable cause = null;

        try {
            S2Container container = null;
            container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
            buf.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            buf.append(" " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            Field field = null;
            Field[] fields = getTargetClass(invocation).getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName().equals(invocation.getMethod().getName() + "_ACTION_ID")) {
                    field = getTargetClass(invocation).getField(
                            invocation.getMethod().getName() + "_ACTION_ID");
                    break;
                }
            }
            buf.append(" ACTION【" +(field!=null?field+".":"") + invocation.getMethod().getName() + "】が実行されました。");
            logger_.info(buf);
            buf = new StringBuffer(100);
            HttpServletRequest hr = (HttpServletRequest) request;
            String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );
            if(sUserRemoteIP == null || "".equals(sUserRemoteIP)){
            	logger_.info("       RemoteHost:[ " + request.getRemoteHost() + " ]");
            }else{
            	logger_.info("       RemoteHost:[ " + sUserRemoteIP + " ]");
            }
            logger_.info("       user-agent:[ " + request.getHeader("user-agent") + " ]");
            logger_.info("       RequestURI:[ " + request.getRequestURI() + " ]");
            logger_.info("       referer   :[ " + request.getHeader("referer") + " ]");
            logger_.info("       SESSION ID:[ " + request.getSession().getId() + " ]");
	        int parameterCnt = 0;
	        String parameterInfo = "";
	        //パラメータ値の設定
	        Enumeration e = request.getParameterNames();
	        while (e.hasMoreElements()) {
	        	parameterCnt++;
	            String paramKey = (String) e.nextElement();
	            String paramValue = request.getParameter(paramKey);
	            parameterInfo += (parameterCnt == 1?"":", ") + paramKey + "=" + paramValue; //パラメータKey&値
	        }
            logger_.info("       RequestParam:[ " + parameterInfo + " ]");
            ret = invocation.proceed();

        } catch (Throwable t) {
			cause = t;
		}
		if (cause == null) {
			return ret;
		} else {
			throw cause;
		}
	}
}