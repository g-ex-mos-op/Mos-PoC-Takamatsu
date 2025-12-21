package jp.co.isid.mos.bird.framework.interceptor.log;

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

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * アクセスログ出力処理
 * @author itamoto
 */
public class LogInterceptor extends AbstractInterceptor {

    static final long serialVersionUID = 1L;

    private static Logger logger_ = Logger.getLogger(LogInterceptor.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {

        StringBuffer buf = new StringBuffer(100);
		Object ret = null;
		Throwable cause = null;

        try {
            S2Container container = null;
            container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
            BirdUserInfo userInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");
            MstUser mstUser = (userInfo == null) ? null : userInfo.getMstUser();

            buf.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            buf.append(" " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            boolean viewIdFlag = false;
            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String s = (String) e.nextElement();
                if (s.indexOf(":viewId") != -1) {
                	buf.append(" "
                            + ((request.getParameter(s) == null || request
                                    .getParameter(s).equals("")) ? "########"
                                    : request.getParameter(s)));
                    viewIdFlag = true;
                    break;
                }
            }
            if (viewIdFlag == false) {
            	buf.append(" ########");
            }

            Field field = null;
            Field[] fields = getTargetClass(invocation).getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName().equals(invocation.getMethod().getName() + "_ACTION_ID")) {
                    field = getTargetClass(invocation).getField(
                            invocation.getMethod().getName() + "_ACTION_ID");
                    break;
                }
            }
            buf.append(" "
                    + ((field == null) ? "########"
                            : (field.get(null) == null) ? "########" : field
                                    .get(null)));
            buf.append(" "
                    + ((mstUser == null) ? "########"
                            : (mstUser.getUser_id() == null) ? "########"
                                    : mstUser.getUser_id()));
            HttpServletRequest hr = (HttpServletRequest) request;
            String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );
            if(sUserRemoteIP == null || "".equals(sUserRemoteIP)){
            	buf.append(" " + request.getRemoteHost());
            }else{
            	buf.append(" " + sUserRemoteIP);
            }

            buf.append(" " + request.getHeader("user-agent"));
            buf.append(" " + request.getRequestURI());
buf.append(" " + request.getHeader("referer"));
            logger_.info(buf);

            ret = invocation.proceed();
            buf.append(" : ");
			buf.append(ret);

        } catch (Throwable t) {
			buf.append(" Throwable:");
			buf.append(t);
			cause = t;
		}
		if (cause == null) {
			return ret;
		} else {
			throw cause;
		}
	}
}