package jp.co.isid.mos.bird.togouser.login.action.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.togouser.common.logic.GetTogoUserMenuLogic;
import jp.co.isid.mos.bird.togouser.filterregist.dto.TogoUserInfoDto;
import jp.co.isid.mos.bird.togouser.login.action.TogoLoginAction;
import jp.co.isid.mos.bird.togouser.login.dto.TogoUserLoginDto;
import jp.co.isid.mos.bird.togouser.login.logic.TogoLoginLogic;
import jp.co.isid.mos.bird.togouser.login.logic.impl.TogoLoginLogicImpl;
import jp.co.isid.mos.bird.togouser.common.dto.TogoUserMenuDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * ログイン画面アクション
 * @author K.Nihonyanagi
 * 更新日:2012/03/13 IE9対応 CSS・JS外部ファイル設定用動的値追加
 */
public class TogoLoginActionImpl implements TogoLoginAction {

    /* アクションID */
    public static final String loginInit_ACTION_ID      = "BUR000A01";
    public static final String login_ACTION_ID          = "BUR000A02";

    /** ログインリクエスト日時(IE9対応用) 2012/03/13追加**/
    private long requestTime = (new Date()).getTime();
    
    private TogoUserLoginDto _loginDto;
    private UITogoUserDto uiTogoUserDto;
    private TogoUserInfoDto togoUserInfoDto;   
    private HttpSession _session;
    private String _pageKey = null;
    private GetTogoUserMenuLogic getTogoUserMenuLogic;
    private TogoUserMenuDto togoUserMenuDto;
    /* LoginDto DI */
    public void setTogoUserLoginDto(final TogoUserLoginDto dto) {
        this._loginDto = dto;
    }
	private TogoUserLoginDto getLoginDto() {
	    return this._loginDto;
    }
    
    /**
     * ログインボタン アクション
     * @return
     */
	public String login() throws ApplicationException {
		S2Container container = null;
        // 遷移先のページ情報
        setPageKey(null);
        
        // ユーザーIDの前ゼロ付加
        String userID = getLoginDto().getUserId();
        CodeFormatter formatter = new CodeFormatter(8);
        formatter.setFormatPattern("00000000");
        if (userID != null && !userID.trim().equals("")) {
            getLoginDto().setUserId(formatter.format(userID, true));
        }
        container = SingletonS2ContainerFactory.getContainer();
        
        // セッションエラー用のメッセージをクリア
        _session.setAttribute("NoSessionTogoMessage", "");
        
        /* １．ロジック【ログイン処理】を実行 */
		TogoLoginLogic loginLogic = (TogoLoginLogic) container.getComponent(TogoLoginLogicImpl.class);
        loginLogic.execute(getLoginDto(), getRequest());
        
        //メニュー検索
        List listBunshoCategory = getGetTogoUserMenuLogic().execute();
        getTogoUserMenuDto().setMenulist(listBunshoCategory);
        
        getUiTogoUserDto().setLoginUserId(getLoginDto().getUserId());
        getTogoUserInfoDto().setStrLoginUser(getLoginDto().getUserId());
        
        if (_session.getAttribute("NoSessionReturnTogoViewID") != null) {
            String returnViewId = (String) _session.getAttribute("NoSessionReturnTogoViewID");
            if (!"".equals(returnViewId.trim())) {
                _session.setAttribute("NoSessionReturnTogoViewID", "");
                return returnViewId;
            }
        }
        
        setPageKey("BUR001V01");
        return _pageKey;
	}
    
    public void setSession(HttpSession session) {
        this._session = session;
    }

    /**
     * 画面遷移用のキー設定処理
     * @param pageKey
     */
    private void setPageKey(String pageKey) {
        this._pageKey = pageKey;
    }
    
//    /**
//     * リダイレクト
//     */
//    public String redirect() {
//        S2Container container      = SingletonS2ContainerFactory.getContainer();
//        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
//        HttpSession session        = (HttpSession) container.getComponent("session");
//        BirdUserInfo birdUserInfo  = null;
//
//        
//        //--------------------------------------------------
//        // <1> 遷移先が設定されていない場合(ログイン画面へ)
//        //--------------------------------------------------
//        //リクエストより遷移先を取得
//        Object pageKey = request.getParameter("BIRD_PAGE_KEY");
//        if (pageKey == null || "".equals(pageKey.toString().trim())) {
//
//            //ログイン画面へ遷移
//            return "BUR000V01";
//        }
//
//        //----------------------------------------------------------------
//        // <2> BIRD内からの遷移(セッションブリッジキーが設定なし)、
//        //     またはセッションエラーの場合
//        //----------------------------------------------------------------
//        //セッションブリッジキー取得
//        String sessionKey = request.getParameter("BIRD_SESSION_KEY");
//        if (sessionKey == null || "".equals(sessionKey.trim())) {
//
//            //ログイン画面へ遷移
//            return "BUR000V01";
//        }
//
//        //---------------------------------------
//        // <3> BIRDユーザーIDを取得できない場合
//        //---------------------------------------
//        //セッションブリッジキーよりe-mosslesユーザーID取得
//        String eMosUserId = getEmosUserID(sessionKey);
//
//        //BIRDユーザ取得
//        String birdUserId = getBirdUserID(eMosUserId);
//
//        if (birdUserId == null || "".equals(birdUserId.trim())) {
//
//            //ログイン画面へ遷移
//            return "BUR000V01";
//        }
//
//        
//        //BIRDログイン判定
//        boolean isLogin = false;
//        if(session != null){
//            //ユーザ情報取得
//            birdUserInfo = (BirdUserInfo) session.getAttribute("birdUserInfo");
//            if (birdUserInfo != null) {
//                MstUser userBean = birdUserInfo.getMstUser();
//                if (userBean != null) {
//                    //ログイン済
//                    isLogin = true;
//                }
//            }
//        }
//
//        if(isLogin){
//            //---------------------------------------
//            // <4> ログイン済の場合
//            //---------------------------------------
//
//            //メニューDTOのフラグをクリア
//            PullDownMenuDto pullDownMenuDto = getPullDownMenuDto();
//            if (pullDownMenuDto != null && !isNull(sessionKey)) {
//                pullDownMenuDto.setClearFlg(true);
//            }
//
//            //セッション内のセッションブリッジキーとリクエストのセッションブリッジキーが一致しているか 
//            String logionUserSessionKey = (String)birdUserInfo.getInfo(BirdUserInfo.SESSION_BRIDGE_KEY);
//            if(!sessionKey.equals(logionUserSessionKey)){
//                //再ログイン処理
//                login(birdUserId, sessionKey, request);
//            }
//        } else {
//            //---------------------------------------
//            // <5> ログイン未の場合
//            //---------------------------------------
//            //自動ログイン処理
//            login(birdUserId, sessionKey, request);
//        }
//        //指定ページへ遷移
//        return pageKey.toString();
//
//    }

    public String loginInit() {
        Object msg = "";
        if (_session != null) { 
            msg = _session.getAttribute("NoSessionTogoMessage");
            if (msg == null) {
                msg = "";
            }
        }
        getLoginDto().setMsgSessionError((String) msg);
        
        return null;
    }

    private HttpServletRequest getRequest() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (HttpServletRequest) container.getComponent("request");
    }
    public UITogoUserDto getUiTogoUserDto() {
        return uiTogoUserDto;
    }
    public void setUiTogoUserDto(UITogoUserDto uiTogoUserDto) {
        this.uiTogoUserDto = uiTogoUserDto;
    }
    public TogoUserInfoDto getTogoUserInfoDto() {
        return togoUserInfoDto;
    }
    public void setTogoUserInfoDto(TogoUserInfoDto togoUserInfoDto) {
        this.togoUserInfoDto = togoUserInfoDto;
    }
    public GetTogoUserMenuLogic getGetTogoUserMenuLogic() {
        return getTogoUserMenuLogic;
    }

    public void setGetTogoUserMenuLogic(GetTogoUserMenuLogic getGetTogoUserMenuLogic) {
        this.getTogoUserMenuLogic = getGetTogoUserMenuLogic;
    }    
    
    public TogoUserMenuDto getTogoUserMenuDto() {
        return togoUserMenuDto;
    }

    public void setTogoUserMenuDto(TogoUserMenuDto TogoUserMenuDto) {
        this.togoUserMenuDto = TogoUserMenuDto;
    }
	/**
	 * ログインリクエスト日時取得処理
	 * 
	 * 作成日 2012/03/13(IE9対応用)
	 * @return クラス変数requestTime を戻します。
	 */
	public long getRequestTime() {
		return requestTime;
	}
	/**
	 * ログインリクエスト日時設定処理
	 * 作成日 2012/03/13(IE9対応用)
	 * @param requestTime を クラス変数requestTimeへ設定します。
	 */
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
}