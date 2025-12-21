/*
 * 作成日: 2009/05/26
 *
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.SelectViewIdLogic;
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
public class CommonInitTogoActionIntercepter extends AbstractInterceptor{
    
    static final long serialVersionUID = 1L;
    private static final String SESSION_KEY = "BIRD_SESSION_KEY";
    private static final String PAGE_KEY_LOGIN = "BUR000V01";
    
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
//        createMenuAction(request, invocation.getMethod().getName());

        //各画面の初期アクションを実行
        Object ret = invocation.proceed();

//        if (ret != null) {
//            String showViewId = ret.toString();
//            if (showViewId.trim().length() > 0) {
//                getMenuActionImpl().setShowViewId(showViewId);
//            }
//        }
        return ret;
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
        BirdDateInfo birdDateInfo = null;
        
        /* １．BIRD通常ログイン確認 */
        if (session != null) {
            birdDateInfo = (BirdDateInfo) session.getAttribute("togoDateInfo");
            if (birdDateInfo != null) {
//                MstUser userBean = birdUserInfo.getMstUser();
//                if (userBean != null) {
//                    // ログイン済みの場合は、処理終了
//                    //---2006/03/06 add メニューDTOのフラグをクリア
//                    PullDownMenuDto pullDownMenuDto = getPullDownMenuDto();
//                    if (pullDownMenuDto != null && !isNull(sessionKey)) {
//                        pullDownMenuDto.setClearFlg(true);
//                    }
                    //2006/04/26 追加
                    //セッション内のセッションブリッジキーとリクエストのセッションブリッジキーが一致しているか 
//                    String logionUserSessionKey = (String)birdUserInfo.getInfo(BirdUserInfo.SESSION_BRIDGE_KEY);
//                    if(sessionKey == null){
//                        if(!browserIdCheck(logionUserSessionKey, request)){
//                            return "BPO000V01";
//                        }
//                        return sRet;
//                    }else if(sessionKey.equals(logionUserSessionKey)){
//                        return sRet;
//                    }
//                }
                return sRet;
            }
        }
        
        /* ２．セッションブリッジキー有無の確認 */
        if (birdDateInfo == null) {
            // セッションエラー
//--- 2006/02/08 update ログイン画面へ遷移させる            
//            throw new NoSessionException();
            // ログイン画面用の文字列をリターンする
            String msg = BirdMessageFormatter.getMessage("E0413", new String[]{""});
            session.setAttribute("NoSessionTogoMessage", msg);
            return "BUR000V01";
        }
        return sRet;
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
                    S2Container container = SingletonS2ContainerFactory.getContainer();
                    HttpSession session = (HttpSession) container.getComponent("session");
                    session.setAttribute("NoSessionTogoMessage", msg);
                    
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
                    session.setAttribute("NoSessionReturnTogoViewID", returnViewID);
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
