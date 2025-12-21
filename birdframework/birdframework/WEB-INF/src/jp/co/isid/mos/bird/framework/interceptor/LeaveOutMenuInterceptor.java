/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;
import jp.co.isid.mos.bird.framework.logic.GetLeaveOutMenuLogic;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * メニュー権限チェックのインターセプター
 * @author xytamura
 */
public class LeaveOutMenuInterceptor extends AbstractInterceptor {
    static final long serialVersionUID = 1L;

    private GetLeaveOutMenuLogic getLeaveOutMenuLogic;
    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    	try {
	        //各画面の初期アクションを実行
	        Object o= methodInvocation.proceed();
	        //初期表示画面VIEWID取得
	        String initViewId = getInitViewId(getViewId((String)o));
	        //除外メニューか否かの状況値設定処理
	        setLeaveOutStatus(initViewId.substring(0, 6));
	        //初期表示画面VIEWID設定処理
	        getMenuActionImpl().setInitViewId(initViewId);
            
            String methodName = methodInvocation.getMethod().getName();
            /* 初期処理の場合、パラメータよりMenuId、SubMenuIdを取得 */
            if (methodName != null && methodName.indexOf("initialize") >= 0) {
                S2Container container = SingletonS2ContainerFactory.getContainer();
                HttpServletRequest request = (HttpServletRequest) container.getComponent("request");

                String menuId = null;
                String subMenuId = null;
                Enumeration e = request.getParameterNames();
                while (e.hasMoreElements()) {
                    String s = (String) e.nextElement();
                    if (s.indexOf(":initMenuId") != -1) {
                        menuId = request.getParameter(s) == null ? "" : request.getParameter(s);
                    }
                    else if (s.indexOf(":initSubMenuId") != -1) {
                        subMenuId = request.getParameter(s) == null ? "" : request.getParameter(s);
                    }
                    if (menuId != null && subMenuId != null) {
                        break;
                    }
                }
                
                getMenuActionImpl().setSelectedMainMenu(initViewId, menuId, subMenuId);
            }
            /* 初期処理以外の場合、遷移先ViewIdからMenuId、SubMenuIdをセット */
            else {
                if (o != null) {
                    String viewId = (String) o;
                    if (viewId.trim().length() > 6) {
                        viewId = viewId.substring(0, 6);
                    }
                    CtlBirdMenu ctlBirdMenu = getMenuActionImpl().getMenuInfoByViewId(viewId);
                    if (ctlBirdMenu != null) {
                        getMenuActionImpl().setInitMenuId(ctlBirdMenu.getMenuId());
                        getMenuActionImpl().setInitSubMenuId(ctlBirdMenu.getSubMenuId());
                    }
                }
            }
            
	        return o;
    	}
    	catch (Throwable e) {
	        //除外メニューか否かの状況値設定処理
	        setLeaveOutStatus(getMenuActionImpl().getInitViewId().substring(0, 6));
            //メニュー選択処理
            S2Container container = SingletonS2ContainerFactory.getContainer();
            HttpServletRequest request = (HttpServletRequest) container.getComponent("request");

            String menuId = null;
            String subMenuId = null;
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String s = (String) enumeration.nextElement();
                if (s.indexOf(":initMenuId") != -1) {
                    menuId = request.getParameter(s) == null ? "" : request.getParameter(s);
                }
                else if (s.indexOf(":initSubMenuId") != -1) {
                    subMenuId = request.getParameter(s) == null ? "" : request.getParameter(s);
                }
                if (menuId != null && subMenuId != null) {
                    break;
                }
            }
            
            getMenuActionImpl().setSelectedMainMenu(null, menuId, subMenuId);
    		throw e;
    	}
    }
    /**
     * 初期表示画面VIEWID取得
     * 
     * 表示中メニューリストから遷移先の初期表示画面VIEWIDを取得します。
     * @param screenId 8桁の半角英数字
     */
    private String getInitViewId(String responseViewId) {
    	responseViewId = getViewId(responseViewId);
    	List listMenu = getPullDownMenuDto().getMenu1st();
    	for(int i=0; i<listMenu.size(); i++) {
            List submenu= ((CtlBirdMenu)listMenu.get(i)).getSubMenuList();
            for(int j = 0; j < submenu.size(); j++){
            	CtlBirdMenu eSubMenu = ((CtlBirdMenu)submenu.get(j));
                String menuId = eSubMenu.getViewId();
                if(responseViewId.substring(0, 6).equals(menuId)){                   
                    return eSubMenu.getInitViewId();
                }
            }
    	}
    	listMenu = getPullDownMenuDto().getMenu2nd();
    	for(int i=0; i<listMenu.size(); i++) {
            List submenu= ((CtlBirdMenu)listMenu.get(i)).getSubMenuList();
            for(int j = 0; j < submenu.size(); j++){
            	CtlBirdMenu eSubMenu = ((CtlBirdMenu)submenu.get(j));
                String menuId = eSubMenu.getViewId();
                if(responseViewId.substring(0, 6).equals(menuId)){                   
                    return eSubMenu.getInitViewId();
                }
            }
    	}
    	return responseViewId;
    }
    /**
     * リクエストされた表示画面IDを取得します。
     * 
     * @param request リクエスト
     * @return 表示画面ID(VIEW_ID)
     */
    private String getViewId(final String viewId) {
 		String menuId = getMenuActionImpl().getInitViewId().substring(0,6);
        if (viewId != null && viewId.length() >= 6) {
    		if(viewId.indexOf(menuId)<0) {
    			return viewId;
    			
    		}
        }
        return getMenuActionImpl().getInitViewId();
    }
    /**
     * 除外メニューか否かの状況値設定処理
     * 
     * @param execMethodName
     * @param responseViewId 遷移先画面VIEWID
     */
    private void setLeaveOutStatus(final String responseViewId) {
       	getPullDownMenuDto().setLeaveOutMenu(getGetLeaveOutMenuLogic().isLeaveOutMenu(responseViewId));
    }
    /**
     * 
     * @return
     */
    private MenuActionImpl getMenuActionImpl() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (MenuActionImpl) container.getComponent(MenuActionImpl.class);
    }
    /**
     * 
     * @return
     */
    private PullDownMenuDto getPullDownMenuDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
    }
	/**
	 * @return getLeaveOutMenuLogic を戻します。
	 */
	public GetLeaveOutMenuLogic getGetLeaveOutMenuLogic() {
		return getLeaveOutMenuLogic;
	}

	/**
	 * @param getLeaveOutMenuLogic を クラス変数getLeaveOutMenuLogicへ設定します。
	 */
	public void setGetLeaveOutMenuLogic(GetLeaveOutMenuLogic getLeaveOutMenuLogic) {
		this.getLeaveOutMenuLogic = getLeaveOutMenuLogic;
	}

}