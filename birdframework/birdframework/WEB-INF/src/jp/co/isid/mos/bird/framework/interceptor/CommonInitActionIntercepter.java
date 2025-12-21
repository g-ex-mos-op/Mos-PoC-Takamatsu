/*
 * 作成日: 2005/12/15
 * 変更日: 2007/02/05 BR58RETVより遷移画面を取得できなかった場合の遷移先を変更
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.EmosBirdUserCooperationDao;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlEmosBirdUserCooperation;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.LoginLogic;
import jp.co.isid.mos.bird.framework.logic.SelectViewIdLogic;
import jp.co.isid.mos.bird.framework.logic.impl.LoginLogicImpl;
import jp.co.isid.mos.bird.framework.logic.impl.SessionBridgeLogic;
import jp.co.isid.mos.bird.framework.util.BirdMessageFormatter;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * アクション共通インターセプター
 *  2006/02/08 セッションエラー時の処理を変更
 *  2006/04/26 再ログイン時セッションブリッジ対応
 *  2006/10/25 e-mosslesから遷移された場合に、ヘルプ画面で使用する情報をセットするよう変更
 * @author xnkusama
 */
public class CommonInitActionIntercepter extends AbstractInterceptor{
    
    static final long serialVersionUID = 1L;
    private static final String SESSION_KEY = "BIRD_SESSION_KEY";
    private static final String SESSION_KEY_BIRD_USER_INFO = "birdUserInfo";
    //private static final String SESSION_KEY_USER_MST = "mstUser";
    private static final String PAGE_KEY_LOGIN = "BPO000V01";
    
    //遷移先ViewIDを取得ロジック
    private SelectViewIdLogic selectViewIdLogic;
    
    /**
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        HttpSession session = (HttpSession) container.getComponent("session");
        
        /* ユーザーセッション関連処理 */
        String pageKey = setupUserSession(session, request);
        if (pageKey != null) {
//--- 2006/02/09 add
            if (PAGE_KEY_LOGIN.equals(pageKey)) {
                // SessionErrorでログイン画面へ遷移時のみの処理
                handleSessionError(invocation);
            }
//--- 2006/02/09 add end
            // 戻り値がnull以外の場合は、処理を中断し該当ページへ遷移
            return pageKey;
        }

        /* 共通DTO関連処理 */
        setupCommonDto(request);
        
        //MenuActionImpl作成
        createMenuAction(request, invocation.getMethod().getName());

        //各画面の初期アクションを実行
        Object ret = invocation.proceed();

        if (ret != null) {
            String showViewId = ret.toString();
            if (showViewId.trim().length() > 0) {
                getMenuActionImpl().setShowViewId(showViewId);
            }
        }
        return ret;
    }

    private String getEmosUserID(String sessionKey) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        SessionBridgeLogic logic = (SessionBridgeLogic)container.getComponent(SessionBridgeLogic.class);

        return logic.getUserId(sessionKey);
    }
    
    /**
     * 共通パラメータ用DTO関連処理
     */
    private void setupCommonDto(HttpServletRequest request) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        
        CommonCodeDto commonCodeDto = (CommonCodeDto) container.getComponent(CommonCodeDto.class);
        if (commonCodeDto != null) {
            String sIsUseCommonDto = "";
            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()) {
                String pName = (String) e.nextElement();
                if (pName.lastIndexOf(":isUseCommonDto") != -1) {
                    sIsUseCommonDto = request.getParameter(pName);
                    break;
                }
            }
            if ("1".equals(sIsUseCommonDto)) {
                commonCodeDto.setUseCommonDto(true);
            }
            else {
                commonCodeDto.setUseCommonDto(false);
            }
        }
    }
    
    /*
     * e-mosslesユーザーIDからBIRDユーザーIDの取得
     */
    private String getBirdUserID(String eMosUserId) {
        String birdUserId = "";
        List daoResult;
        CtlEmosBirdUserCooperation entity;
        EmosBirdUserCooperationDao dao; 
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        dao = (EmosBirdUserCooperationDao) 
                container.getComponent(EmosBirdUserCooperationDao.class);
        
        daoResult = dao.getBirdUserIdByEmosUserId(eMosUserId);
        
        if (daoResult.size() > 0) {
            entity = (CtlEmosBirdUserCooperation) daoResult.get(0);
            birdUserId = entity.getBirdUserId();
        }
        
        return birdUserId;
    }
    
    /**
     * ログイン関連処理
     *  １．BIRD通常ログイン確認　　             ログイン済 --> 処理終了 
     *  ２．セッションブリッジキー有無の確認     キーなし   --> セッションエラー
     *  　　　　（2006/02/08 変更  ログイン画面へ遷移）
     *  ３．BIRDユーザーIDの取得                 取得NG     --> ログイン画面へ
     *  ４．自動ログイン処理
     * 2006/02/08 ２番の場合もログイン画面へ遷移するように変更
     * @author xnkusama
     * @param HttpSession
     * @param HttpServletRequest
     * @return String 
     * @exception ApplicationException
     */
    private String setupUserSession(HttpSession session, HttpServletRequest request) 
                        throws ApplicationException
    {
        //画面遷移用戻り値
        String sRet = null;
        //e-mosslesユーザーID
        String eMosUserId = "";
        //BIRDユーザーID
        String birdUserId = "";
        // BIRDユーザー情報
        BirdUserInfo birdUserInfo = null;
        //セッションブリッジキー
        String sessionKey = request.getParameter(SESSION_KEY);
        
        
        /* １．BIRD通常ログイン確認 */
        if (session != null) {
            birdUserInfo = (BirdUserInfo) session.getAttribute(SESSION_KEY_BIRD_USER_INFO);
            if (birdUserInfo != null) {
                MstUser userBean = birdUserInfo.getMstUser();
                if (userBean != null) {
                    // ログイン済みの場合は、処理終了
                	//---2006/03/06 add メニューDTOのフラグをクリア
                	PullDownMenuDto pullDownMenuDto = getPullDownMenuDto();
                	if (pullDownMenuDto != null && !isNull(sessionKey)) {
                		pullDownMenuDto.setClearFlg(true);
                	}
                    //2006/04/26 追加
                    //セッション内のセッションブリッジキーとリクエストのセッションブリッジキーが一致しているか 
                    String logionUserSessionKey = (String)birdUserInfo.getInfo(BirdUserInfo.SESSION_BRIDGE_KEY);
                    if(sessionKey == null){
//                        if(!browserIdCheck(logionUserSessionKey, request)){
//                            return "BPO000V01";
//                        }
                        return sRet;
                    }else if(sessionKey.equals(logionUserSessionKey)){
                        return sRet;
                    }
                }
            }
        }
        
        /* ２．セッションブリッジキー有無の確認 */
        if (sessionKey == null || "".equals(sessionKey.trim())) {
            // セッションエラー
//--- 2006/02/08 update ログイン画面へ遷移させる            
//            throw new NoSessionException();
            // ログイン画面用の文字列をリターンする
            return "BPO000V01";
        }

        /* ３．BIRDユーザーIDの取得 */
        //セッションブリッジキーよりe-mosslesユーザーID取得
        eMosUserId = getEmosUserID(sessionKey);
        //BIRDユーザ取得
        birdUserId = getBirdUserID(eMosUserId);
        if (birdUserId == null || "".equals(birdUserId.trim())) {
            // ログイン画面用の文字列をリターンする
            return "BPO000V01";
        }
        
        /* ４．自動ログイン処理 */
        // ログインしていない場合、ログイン関連処理
        login(birdUserId, sessionKey, request);
        
        return sRet;
    }
    
    
    private void login(String birdUserId, String sessionBridgeKey, HttpServletRequest request) {
        S2Container container = null;
        container = SingletonS2ContainerFactory.getContainer();
        
        
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(birdUserId);
        loginDto.setSessionBridgeKey(sessionBridgeKey);
        //2006/05/01 e-mosslesからの遷移フラグ
        loginDto.setFromEmosFlg(true);
        
        LoginLogic loginLogic = (LoginLogic) container.getComponent(LoginLogicImpl.class);
        loginLogic.execute(loginDto, request);
        
        //2006/04/26 追加
        //セッションブリッジキーを保持
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        birdUserInfo.setInfo(sessionBridgeKey, BirdUserInfo.SESSION_BRIDGE_KEY);
        
    }
    
    private void handleSessionError(MethodInvocation invocation) throws ApplicationException {
        Field field = null;
        Field[] fields = getTargetClass(invocation).getFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName().equals(invocation.getMethod().getName() + "_ACTION_ID")) {
                    field = getTargetClass(invocation).getField(
                            invocation.getMethod().getName() + "_ACTION_ID");
                    break;
                }
            }
            
            if (field != null) {
                Object actionID = field.get(null);
                if (actionID != null) {
                    String msg = BirdMessageFormatter.getMessage("E0413", new String[]{""});
//                  msg += "<br/>なお、POS端末からご利用の際は、画面を一度閉じてから再度起動してください。";
                    S2Container container = SingletonS2ContainerFactory.getContainer();
                    HttpSession session = (HttpSession) container.getComponent("session");
                    session.setAttribute("NoSessionMessage", msg);
                    
                    //---2007/02/05 start BR58RETVより遷移画面を取得できなかった場合のデフォルトを変更
                    //---           BPO001V01(ポータルトップ) → アクションID + "V01"
//                    //2007/01/22 START
//                    //session.setAttribute("NoSessionReturnViewID", ((String) actionID).substring(0,6) + "V01");
//                                       
//                    // セッションエラー時、再ログイン後に遷移する画面VIEW_IDの取得
//                    session.setAttribute("NoSessionReturnViewID", getSelectViewIdLogic().getSelectId(((String) actionID).substring(0,6)));
                    String sActionID = ((String) actionID).substring(0,6);
                    String returnViewID = getSelectViewIdLogic().getSelectId(sActionID);
                    if (isNull(returnViewID)) {
                        returnViewID = sActionID + "V01";
                    }
                    session.setAttribute("NoSessionReturnViewID", returnViewID);
                    //2007/01/22 END
                    //---2007/02/05 end
                }
            }
            

        }
        catch (NoSuchFieldException nsfe) {
            throw new FtlSystemException("初期処理");
        }
        catch (IllegalAccessException iae) {
            throw new FtlSystemException("初期処理");
        }

    }

    /**
     * e-mosslesから遷移された場合は、MenuActionImplを生成する
     * add 2006/10/25
     * @param request
     * @param String methodName
     */
    private void createMenuAction(HttpServletRequest request, String methodName) {
        String sessionKey = request.getParameter(SESSION_KEY);
        // e-mosslessから遷移された場合
        if (!isNull(sessionKey)) {
            String pageKey = request.getParameter("BIRD_PAGE_KEY");
            if (pageKey == null) {
                pageKey = "";
            }
            getMenuActionImpl().setShowViewId(pageKey);
        }
        // BIRD内での遷移
        else {
            // 初期アクション(initialize)では処理を行わない
            if (methodName == null || methodName.indexOf("initialize") < 0) {
                String pageKey = "";
                String showViewId = request.getParameter("showViewId");
                
                if (!isNull(showViewId)) {
                    pageKey = showViewId;
                }
                else {
                    Enumeration e = request.getParameterNames();
                    while (e.hasMoreElements()) {
                        String s = (String) e.nextElement();
                        if (s.indexOf(":viewId") != -1) {
                            pageKey = request.getParameter(s) == null ? "" : request.getParameter(s);
                            break;
                        }
                    }
                }
                getMenuActionImpl().setShowViewId(pageKey);
            }
        }
    }
    
    private PullDownMenuDto getPullDownMenuDto() {
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	return (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
    }

    private MenuActionImpl getMenuActionImpl() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (MenuActionImpl) container.getComponent(MenuActionImpl.class);
    }
    
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

	public SelectViewIdLogic getSelectViewIdLogic() {
		return selectViewIdLogic;
	}

	public void setSelectViewIdLogic(SelectViewIdLogic selectViewIdLogic) {
		this.selectViewIdLogic = selectViewIdLogic;
	}
    
//    /**
//     * 作成中
//     * @param logionUserSessionKey
//     * @param request
//     * @return
//     */
//    private boolean browserIdCheck(String logionUserSessionKey, HttpServletRequest request){
//        S2Container container = SingletonS2ContainerFactory.getContainer();
//        BrowserElementDto browserElementDto = (BrowserElementDto)container.getComponent(BrowserElementDto.class);
//        String browserSession = browserElementDto.getBrowserId();
//        
//        if(browserSession == null && logionUserSessionKey == null){
//            return true;
//        }
//        
//        if(browserSession != null && browserSession.equals(logionUserSessionKey)){
//            return true;    
//        }
//        return false;
//    }
}
