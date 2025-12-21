package jp.co.isid.mos.bird.portal.login.action.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dao.CtlTogoUserRirekiDao;
import jp.co.isid.mos.bird.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.EmosBirdUserCooperationDao;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlEmosBirdUserCooperation;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.logic.LoginLogic;
import jp.co.isid.mos.bird.framework.logic.impl.LoginLogicImpl;
import jp.co.isid.mos.bird.framework.logic.impl.SessionBridgeLogic;
import jp.co.isid.mos.bird.portal.login.action.LoginAction;
import jp.co.isid.mos.bird.portal.login.dto.MatrixDto;
import jp.co.isid.mos.bird.portal.login.dto.MatrixRegistDto;
import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;
import jp.co.isid.mos.bird.portal.login.logic.CheckRemoteLoginCheckLogic;
import jp.co.isid.mos.bird.portal.login.logic.CheckRemoteSessionTimeExtendLimitCheckLogic;
import jp.co.isid.mos.bird.portal.login.logic.GetMatrixInfoLogic;
import jp.co.isid.mos.bird.portal.login.logic.impl.GetMatrixInfoLogicImpl;


/**
 * ログイン画面アクション
 * @author xnkusama
 */
public class LoginActionImpl implements LoginAction {

    /* アクションID */
    public static final String loginInit_ACTION_ID      = "BPO000A01";
    public static final String login_ACTION_ID          = "BPO000A02";
    public static final String changePassword_ACTION_ID = "BPO000A03";
    public static final String redirect_ACTION_ID       = "BPO000A04";

    /**
     * 半角アルファベットを示す定数です。
     */
    public static final String HANKAKU_LATIN =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /** ログインリクエスト日時(IE9対応用) **/
    private long requestTime = (new Date()).getTime();

	private LoginDto _loginDto;
    private MatrixDto matrixDto;
    private MatrixRegistDto matrixRegistDto;
    private HttpSession _session;
    private String _pageKey = null;

    private MstUserShozokuDao mstUserShozokuDao;
    private CtlTogoUserRirekiDao ctlTogoUserRirekiDao;

    private GetGamenRoleLogic gamenRoleLogic;
    private CheckRemoteLoginCheckLogic loginCheckRemoteLoginCheckLogic;

    private CheckRemoteSessionTimeExtendLimitCheckLogic loginCheckRemoteSessionTimeExtendLimitCheckLogic;

    /*パスワードポリシー*/
    private Map birdPasswordPolicy;

    /*ブラウザチェック用MSG*/
    private String browserCheckMsg = "お使いのブラウザでは、一部のメニューが正常に動作しない場合がございます。";

    /* LoginDto DI */
    public void setLoginDto(final LoginDto dto) {
        this._loginDto = dto;
    }
	private LoginDto getLoginDto() {
	    return this._loginDto;
    }
    /* MatrixRegistDto DI */
    public void setMatrixRegistDto(final MatrixRegistDto dto) {
        this.matrixRegistDto = dto;
    }
    private MatrixRegistDto getMatrixRegistDto() {
        return this.matrixRegistDto;
    }

    /**
     * ログインボタン アクション
     * @return
     */
	public String login() throws ApplicationException {
		S2Container container = null;
        // 遷移先のページ情報
        setPageKey(null);
        getLoginDto().setPmossles(false);
        // ユーザーIDの前ゼロ付加
        String userID = getLoginDto().getUserId();
//2008/12/24 オーナーユーザーの場合のみ後ゼロ補完
//        CodeFormatter formatter = new CodeFormatter(8);
//        formatter.setFormatPattern("00000000");
//        if (userID != null && !userID.trim().equals("")) {
//            getLoginDto().setUserId(formatter.format(userID, true));
//        }
        if (isOnerUser(userID)) {
            userID = userID + "000";
            if (userID.length() > 8) {
                userID = userID.substring(0, 8);
            }
            getLoginDto().setUserId(userID);
        }
    	//パスワード有効期限チェック実行判断フラグをtrueに設定します。
        getMatrixDto().setPswdUpdtChekFlg(true);

        container = SingletonS2ContainerFactory.getContainer();

        // セッションエラー用のメッセージをクリア
        _session.setAttribute("NoSessionMessage", "");

        /* ０．ユーザーの所属区分取得を取得し、パスワードロック回数をセット */
        CtlPasswordPolicy ctlPswdPolicy = (CtlPasswordPolicy) getBirdPasswordPolicy().get(getPswdCheckType(getLoginDto().getUserId()));

        //パスワードポリシー情報をLoginDtoへセット
        getLoginDto().setPswdLockCnt(ctlPswdPolicy.getFailureToLockCnt());
        getLoginDto().setPswdAvailableTerm(ctlPswdPolicy.getPswdAvailableTerm());
        getLoginDto().setGamenId("BPO000");

        /* １．ロジック【ログイン処理】を実行 */
		LoginLogic loginLogic = (LoginLogic) container.getComponent(LoginLogicImpl.class);
        loginLogic.execute(getLoginDto(), getRequest());

        /* 社外からのアクセスチェック */
        if (!getLoginCheckRemoteLoginCheckLogic().checkRemote(userID, getRequest())) {
//            //セッション内のBirdUserInfoを削除
//            HttpSession session = getRequest().getSession();
//            if (session != null) {
//                session.setAttribute("birdUserInfo", null);
//            }
//            throw new GenericErrorException("ログイン", "ログインに失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。");
        }

        // 内部IPからアクセスの場合は、セッションタイムアウト2時間をセットする
        if (getLoginCheckRemoteSessionTimeExtendLimitCheckLogic().checkAllow(getRequest())) {
            _session.setMaxInactiveInterval(120*60);
        }

        BirdUserInfo birdUserInfo = (BirdUserInfo) _session.getAttribute("birdUserInfo");
        //内部接続の場合は、マトリクス画面ではなくトップ画面へ直接遷移させる
        if (birdUserInfo != null && birdUserInfo.isLocal()) {
            /* セッションエラー時にログインへ遷移してきた場合は、元の画面へ戻る */
            if (_session.getAttribute("NoSessionReturnViewID") != null) {
                String returnViewId = (String) _session.getAttribute("NoSessionReturnViewID");
                if (!"".equals(returnViewId.trim())) {
                    _session.setAttribute("NoSessionReturnViewID", "");
                    return returnViewId;
                }
            }
            return "BPO001V01";
        }

        /* ６．ロジック【マトリクス認証情報取得】を実行 */
        GetMatrixInfoLogic getMatrixInfoLogic =
                (GetMatrixInfoLogic) container.getComponent(GetMatrixInfoLogicImpl.class);
        UIUserMatrixInfo uiUserMatrixInfo = getMatrixInfoLogic.getMatrixInfoLogic(getLoginDto());
        /* ページ遷移 */
        if (uiUserMatrixInfo == null) {
            /* ７．処理６でデータを取得できなかった場合 */
            getMatrixRegistDto().setPageKey("BPO000V01");
            getMatrixDto().setMatrixResetFlg(false);
            setPageKey("BPO000V03");
        }
        else {
            /* ８．処理６でデータを取得できた場合 */
            getMatrixDto().clear();
            setPageKey("BPO000V02");
        }

        return _pageKey;
	}

    /**
     * チェック用ユーザータイプ判定
     * @param userId
     * @param userShozokuKbn
     * @return
     */
    private String getPswdCheckType(String userId) {
        String pswdCheckType = "";

        //共通Dao【ユーザ所属．ユーザー所属の取得】を実行
        List listUserShozoku = getMstUserShozokuDao().getMstUserShozoku(userId);
        if (listUserShozoku != null && !listUserShozoku.isEmpty()) {
            //取得できた場合、1件目の所属区分でチェックタイプを取得
            pswdCheckType = ((MstUserShozoku) listUserShozoku.get(0)).getShozokuKbn();
        }
        else {
            throw new GenericErrorException("ログイン", "ログインに失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。");
        }

        return pswdCheckType;
    }

    /**
     * パスワード変更ボタン アクション
     * @return
     */
    public String changePassword() {
        return "BPO000V04";
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

    /**
     * リダイレクト
     */
    public String redirect() {
        S2Container container      = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        HttpSession session        = (HttpSession) container.getComponent("session");
        BirdUserInfo birdUserInfo  = null;


        //--------------------------------------------------
        // <1> 遷移先が設定されていない場合(ログイン画面へ)
        //--------------------------------------------------
        //リクエストより遷移先を取得
        Object pageKey = request.getParameter("BIRD_PAGE_KEY");
        if (pageKey == null || "".equals(pageKey.toString().trim())) {

            //ログイン画面へ遷移
            return "BPO000V01";
        }

        //----------------------------------------------------------------
        // <2> BIRD内からの遷移(セッションブリッジキーが設定なし)、
        //     またはセッションエラーの場合
        //----------------------------------------------------------------
        //セッションブリッジキー取得
        String sessionKey = request.getParameter("BIRD_SESSION_KEY");
        if (sessionKey == null || "".equals(sessionKey.trim())) {

            //ログイン画面へ遷移
            return "BPO000V01";
        }

        //---------------------------------------
        // <3> BIRDユーザーIDを取得できない場合
        //---------------------------------------
        //セッションブリッジキーよりe-mosslesユーザーID取得
        String eMosUserId = getEmosUserID(sessionKey);

        //BIRDユーザ取得
        String birdUserId = getBirdUserID(eMosUserId);

        if (birdUserId == null || "".equals(birdUserId.trim())) {

            //ログイン画面へ遷移
            return "BPO000V01";
        }


        //BIRDログイン判定
        boolean isLogin = false;
        if(session != null){
            //ユーザ情報取得
            birdUserInfo = (BirdUserInfo) session.getAttribute("birdUserInfo");
            if (birdUserInfo != null) {
                MstUser userBean = birdUserInfo.getMstUser();
                if (userBean != null) {
                    //ログイン済
                    isLogin = true;
                }
            }
        }

        if(isLogin){
            //---------------------------------------
            // <4> ログイン済の場合
            //---------------------------------------

            //メニューDTOのフラグをクリア
            PullDownMenuDto pullDownMenuDto = getPullDownMenuDto();
            if (pullDownMenuDto != null && !isNull(sessionKey)) {
                pullDownMenuDto.setClearFlg(true);
            }

            //セッション内のセッションブリッジキーとリクエストのセッションブリッジキーが一致しているか
            String logionUserSessionKey = (String)birdUserInfo.getInfo(BirdUserInfo.SESSION_BRIDGE_KEY);
            if(!sessionKey.equals(logionUserSessionKey)){
                //再ログイン処理
                login(birdUserId, sessionKey, request);
            }
        } else {
            //---------------------------------------
            // <5> ログイン未の場合
            //---------------------------------------
            //自動ログイン処理
            login(birdUserId, sessionKey, request);
        }
        //指定ページへ遷移
        return pageKey.toString();

    }

    /**
     * ログイン関連処理
     * @param  birdUserId
     * @param  sessionBridgeKey
     * @param  request
     */
    private void login(String birdUserId, String sessionBridgeKey, HttpServletRequest request) {
        S2Container container = null;
        container = SingletonS2ContainerFactory.getContainer();

        //DTOにセット
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(birdUserId);
        loginDto.setSessionBridgeKey(sessionBridgeKey);
        loginDto.setFromEmosFlg(true);                    //e-mosslesからの遷移フラグ

        //ログイン実行
        LoginLogic loginLogic = (LoginLogic) container.getComponent(LoginLogicImpl.class);
        loginLogic.execute(loginDto, request);

        //セッションブリッジキーを保持
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        birdUserInfo.setInfo(sessionBridgeKey, BirdUserInfo.SESSION_BRIDGE_KEY);
    }

    /**
     * セッションキーより、e-mossのユーザIDを取得する処理
     * @param  sessionKey
     * @return e-mossのuserId
     */
    private String getEmosUserID(String sessionKey) {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        SessionBridgeLogic logic = (SessionBridgeLogic)container.getComponent(SessionBridgeLogic.class);

        return logic.getUserId(sessionKey);
    }


    /**
     * e-mosslesユーザーIDからBIRDユーザーIDの取得
     * @param  e-mossのuserId
     * @return birdのuserId
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

    public String loginInit() {
        Object msg = "";
        if (_session != null) {
            msg = _session.getAttribute("NoSessionMessage");
            if (msg == null) {
                msg = "";
            }
        }
        getLoginDto().setMsgSessionError((String) msg);

        return null;
    }

    /**
     * オーナーユーザー判別
     * 英数字が入力されている場合はオーナーユーザーと判断する
     * @return
     */
    private boolean isOnerUser(String userId) {
        boolean isOner = false;
        if (CommonUtil.isNull(userId)) {
            return false;
        }
        for (int i = 0; i < userId.length(); i++) {
            String checkStr = userId.substring(i, i + 1);
            if (HANKAKU_LATIN.indexOf(checkStr) != -1) {
                isOner = true;
                break;
            }
        }
        return isOner;
    }

	public MatrixDto getMatrixDto() {
		return matrixDto;
	}
	public void setMatrixDto(MatrixDto matrixDto) {
		this.matrixDto = matrixDto;
	}

    private HttpServletRequest getRequest() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (HttpServletRequest) container.getComponent("request");
    }
    private PullDownMenuDto getPullDownMenuDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
    }
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    public MstUserShozokuDao getMstUserShozokuDao() {
        return mstUserShozokuDao;
    }
    public void setMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
        this.mstUserShozokuDao = mstUserShozokuDao;
    }
    public CtlTogoUserRirekiDao getCtlTogoUserRirekiDao() {
        return ctlTogoUserRirekiDao;
    }
    public void setCtlTogoUserRirekiDao(CtlTogoUserRirekiDao ctlTogoUserRirekiDao) {
        this.ctlTogoUserRirekiDao = ctlTogoUserRirekiDao;
    }
    public Map getBirdPasswordPolicy() {
        return birdPasswordPolicy;
    }
    public void setBirdPasswordPolicy(Map birdPasswordPolicy) {
        this.birdPasswordPolicy = birdPasswordPolicy;
    }
    public String getBrowserCheckMsg() {
        return browserCheckMsg;
    }
    public void setBrowserCheckMsg(String browserCheckMsg) {
        this.browserCheckMsg = browserCheckMsg;
    }
    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }
    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }
    public CheckRemoteLoginCheckLogic getLoginCheckRemoteLoginCheckLogic() {
        return loginCheckRemoteLoginCheckLogic;
    }
    public CheckRemoteSessionTimeExtendLimitCheckLogic getLoginCheckRemoteSessionTimeExtendLimitCheckLogic() {
        return loginCheckRemoteSessionTimeExtendLimitCheckLogic;
    }
    public void setLoginCheckRemoteLoginCheckLogic(
            CheckRemoteLoginCheckLogic loginCheckRemoteLoginCheckLogic) {
        this.loginCheckRemoteLoginCheckLogic = loginCheckRemoteLoginCheckLogic;
    }
    public void setLoginCheckRemoteSessionTimeExtendLimitCheckLogic(
            CheckRemoteSessionTimeExtendLimitCheckLogic loginCheckRemoteSessionTimeExtendLimitCheckLogic) {
        this.loginCheckRemoteSessionTimeExtendLimitCheckLogic = loginCheckRemoteSessionTimeExtendLimitCheckLogic;
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
    /* 外部サイトURL:ポップアップブロック対応 ADD 2013/04/22*/
    private String callSiteUrl="";
	/*
	 * アクション【外部リンク呼出】
	 * ポップアップブロック対応 ADD 2013/04/22
	 * @see jp.co.isid.mos.bird.portal.login.action.LoginAction#callSite()
	 */
	public String callSite() {
		//callsite.htmlを呼出します。
		return "PPO000V05";
	}
	/**
	 * @return クラス変数callSiteUrl を戻します。
	 */
	public String getCallSiteUrl() {
		return callSiteUrl;
	}
	/**
	 * @param callSiteUrl を クラス変数callSiteUrlへ設定します。
	 */
	public void setCallSiteUrl(String callSiteUrl) {
		this.callSiteUrl = callSiteUrl;
	}

}