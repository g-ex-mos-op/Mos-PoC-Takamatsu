/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.CtlUserMenu;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MismatchException;
import jp.co.isid.mos.bird.framework.logic.AccessCtlCheckLogic;
import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * メニュー権限チェックのインターセプター
 * @author xytamura
 */
public class AccessCtlCheckInterceptor extends AbstractInterceptor {

    //権限エラー画面へ遷移
    private static final String ACCESS_ERR = "access.Err";

    //ブラウザエラー画面へ遷移
    private static final String BROWSER_ERR = "browser.Err";

    /**
     * メニュー権限チェックロジック
     */
    private AccessCtlCheckLogic accessCtlCheckLogic;

    private final String PARAM_BROWSER_CHK = "browserCheck";

    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        S2Container container = SingletonS2ContainerFactory.getContainer();

        HttpServletRequest request = (HttpServletRequest) container
                .getComponent("request");
        BirdUserInfo userInfo = (BirdUserInfo) request.getSession()
                .getAttribute("birdUserInfo");

        Map errerDataMap = (HashMap) container.getComponent("errorDataMap");

        String pgmId = getPgmId(request);
        if (pgmId.trim().length() != 0) {
            try {
                //アクセス権限チェック
                List menu = getAccessCtlCheckLogic().execute(
                        userInfo.getUserID(), pgmId);

                //ブラウザチェック
                browserCheck(request, menu);

            } catch (CannotAccessException caex) {
                return ACCESS_ERR;
            } catch (MismatchException caex) {
                return BROWSER_ERR;

            } catch (ApplicationException aex) {
                throw aex;

            } catch (Exception ex) {
                throw new FtlSystemException("権限チェック処理", ex.toString(), ex);
            }
        }

        Object o = methodInvocation.proceed();
        
        if (o != null
	            && o instanceof String
	            && !errerDataMap.containsKey(String.valueOf(o).trim())
	            && String.valueOf(o).length() >= 6
	            && !(String.valueOf(o).substring(0,6)).equals(pgmId.trim())) {					          
         
        	try {
                //アクセス権限チェック(遷移先)
                List menu = getAccessCtlCheckLogic().execute(
                        userInfo.getUserID(), String.valueOf(o).substring(0,6));

                //ブラウザチェック
                browserCheck(request, menu);

            } catch (CannotAccessException caex) {
                return ACCESS_ERR;
            } catch (MismatchException caex) {
                return BROWSER_ERR;

            } catch (ApplicationException aex) {
                throw aex;

            } catch (Exception ex) {
                throw new FtlSystemException("権限チェック処理", ex.toString(), ex);
            }
        }
        
        return o;
    }

    /**
     * 画面ＩＤから機能ＩＤ（前６桁）を取得します。
     * @param request リクエスト
     * @return 機能ＩＤ
     */
    private String getPgmId(final HttpServletRequest request) {
        String viewId = "";
        Enumeration e = request.getParameterNames();
                
        while (e.hasMoreElements()) {
            // e-mosslesから遷移した場合        
        	String s = (String) e.nextElement();
            if (s.indexOf("BIRD_PAGE_KEY") != -1) {
                viewId = (String) request.getParameter(s);
                break;
            }
        }
        if ("".equals(viewId)) {
           // menuから遷移した場合
            e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String s = (String) e.nextElement();
                if (s.indexOf("selectedViewId") != -1) {
                    viewId = (String) request.getParameter(s);
                    break;
                }
            }
        }
        
        if ("".equals(viewId)) {
            // 通常から遷移した場合
            e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String s = (String) e.nextElement();
                if (s.indexOf(":viewId") != -1) {
                    viewId = (String) request.getParameter(s);
                    break;
                }
            }
        }

        if (viewId.length() >= 6) {
            return viewId.substring(0, 6);

        } else {
            return "";
        }
    }

    /**
     * メニュー権限チェックロジックを取得します。
     * @return メニュー権限チェックロジック
     */
    public AccessCtlCheckLogic getAccessCtlCheckLogic() {
        return accessCtlCheckLogic;
    }

    /**
     * メニュー権限チェックロジックを設定します。
     * @param accessCtlCheckLogic メニュー権限チェックロジック
     */
    public void setAccessCtlCheckLogic(AccessCtlCheckLogic accessCtlCheckLogic) {
        this.accessCtlCheckLogic = accessCtlCheckLogic;
    }

    /**
     * ブラウザチェック
     * @param request HttpServletRequest
     * @param menu メニュー
     */
    private void browserCheck(HttpServletRequest request, List menu) {
        if (menu == null) {
            return;
        }
        for (int i = 0; i < menu.size(); i++) {
            String param = ((CtlUserMenu) menu.get(i)).getParam();
            if (param.indexOf(PARAM_BROWSER_CHK) >= 0) {

                String userAgent = request.getHeader("user-agent");
                if (userAgent == null || userAgent.indexOf("MSIE") <= 0
                        || userAgent.indexOf("Opera") >= 0
                        || userAgent.indexOf("Sleipnir") >= 0
                        || userAgent.indexOf("Windows") <= 0) {
                    throw new MismatchException("推奨ブラウザ");
                }
            }
        }
    }

}