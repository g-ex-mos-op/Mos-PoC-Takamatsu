/*
 * 作成日: 2005/12/15
 * 変更日: 2007/02/05 BR58RETVより遷移画面を取得できなかった場合の遷移先を変更
 */
package jp.co.isid.mos.bird.framework.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.EmosBirdUserCooperationDao;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlEmosBirdUserCooperation;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.LoginLogic;
import jp.co.isid.mos.bird.framework.logic.impl.LoginLogicImpl;
import jp.co.isid.mos.bird.framework.logic.impl.SessionBridgeLogic;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.jsf.util.MessageUtil;

/**
 * アクション共通インターセプター
 * 
 * アクション共通インターセプター(CommonInitActionIntercepter)はセッションエラー時にログイン画面へ遷移しますが、
 * このインターセプターはセッションエラー画面を表示します。
 * 
 * 作成日:2009/04/13
 * @author xkinu
 *
 */
public class InsideActionIntercepter extends AbstractInterceptor{
    
    static final long serialVersionUID = 1L;
    private static final String SESSION_KEY = "BIRD_SESSION_KEY";
    private static final String SESSION_KEY_BIRD_USER_INFO = "birdUserInfo";
    private static final String PAGE_KEY_SESSION_ERR = "session.Err";
    
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
        	if(PAGE_KEY_SESSION_ERR.equals(pageKey)) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, MessageUtil.getErrorMessage("E0413", new String[]{""}));
        	}
        	// 戻り値がnull以外の場合は、処理を中断し該当ページへ遷移
            return pageKey;
        }

        /* 共通DTO関連処理 */
        setupCommonDto(request);
        
        //各画面の初期アクションを実行
        Object ret = invocation.proceed();

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
            // セッションエラー閉じる画面用のVIEW_IDをリターンします。
            return PAGE_KEY_SESSION_ERR;
        }

        /* ３．BIRDユーザーIDの取得 */
        //セッションブリッジキーよりe-mosslesユーザーID取得
        eMosUserId = getEmosUserID(sessionKey);
        //BIRDユーザ取得
        birdUserId = getBirdUserID(eMosUserId);
        if (birdUserId == null || "".equals(birdUserId.trim())) {
            // ログイン画面用の文字列をリターンする
            return PAGE_KEY_SESSION_ERR;
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
    
    private PullDownMenuDto getPullDownMenuDto() {
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	return (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
    }
    
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    
}
