/**
 * 
 */
package jp.co.isid.mos.bird.pmossles.login.action.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.dto.ErrHtmlElementDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.pmossles.login.action.LoginAction;
import jp.co.isid.mos.bird.pmossles.login.logic.LoginLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
/**
 * ログインアクション
 * 
 * Office(WordやExcel)のハイパーリンクからは
 * 別セッション(OFFICEとIEの仕様?:2011/01/14現在)になります。
 * 補足：URLの直書き、テキストファイルからのリンクは正常に処理が行われます。
 * 
 * 作成日:2010/07/22
 * @author xkinu
 *
 */
public class LoginActionImpl implements LoginAction {
    /* アクションID：PPO000A01 初期化処理 */
    public static final String initialize_ACTION_ID = "PPO000A01";
    /* アクションID：PPO000A02 ログイン処理 */
    public static final String login_ACTION_ID = "PPO000A02";
    /** ログインリクエスト日時(IE9対応用) **/
    private long requestTime = (new Date()).getTime();
    private HttpSession session;
	private String logprm;
	private String viewId;

	/** LOGIC【p-mosslesログイン処理】*/
	private LoginLogic pmosLoginLogic;
	/**
	 * 初期化処理
	 */
	public String initialize() {
		return null;
	}
	/**
	 * ログイン処理
	 * 
	 * 注意：Officeのハイパーリンクからは別セッション(OFFICEとIEの仕様?:2011/01/14現在)になります。    
	 * 補足：URLの直書き、テキストファイルからのリンクは同一セッションの処理でログイン処理が行われます。
	 * 
	 * @see jp.co.isid.mos.bird.pmossles.action.LoginAction#login()
	 */
	public String login() {
        //１．LOGIC【p-mosslesログイン】を実行します。
		String nextViewId = getPmosLoginLogic().execute(getRequest());
        //２．ACTION【メニュー制御アクション】.遷移先VIEWIDへ処理１の変数．VIEWIDを設定します。
        getMenuAction().setShowViewId(nextViewId);
        // ログ用：リクエストURLをセッションから削除します。
        session.removeAttribute(PK_FIRSTURL);
        //３．処理１の変数．VIEWIDをリターンします。
		return nextViewId;	
	}
    /**
     * メニューアクション取得処理
     * 
     * @return
     */
    private MenuActionImpl getMenuAction() {
        return (MenuActionImpl) getS2Container().getComponent(MenuActionImpl.class);
    }
	/**
	 * @return クラス変数logprm を戻します。
	 */
	public String getLogprm() {
		return logprm;
	}

	/**
	 * @param logprm を クラス変数logprmへ設定します。
	 */
	public void setLogprm(String logprm) {
		this.logprm = logprm;
	}

	/**
	 * @return クラス変数viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * @param viewId を クラス変数viewIdへ設定します。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	public LoginLogic getPmosLoginLogic() {
		return pmosLoginLogic;
	}

	public void setPmosLoginLogic(LoginLogic pmosLoginLogic) {
		this.pmosLoginLogic = pmosLoginLogic;
	}

	private S2Container getS2Container() {
		return SingletonS2ContainerFactory.getContainer();
	}
    /**
     * リクエスト情報取得処理
     * @return
     */
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) SingletonS2ContainerFactory.getContainer().getComponent("request");
    }
    public boolean isFirstAccess() {
    	return getRequest().getHeader("referer") == null;
    }
    /**
     * エラー判断処理
     * @return
     */
	public boolean isError() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		LoginDto loginDto = (LoginDto) container.getComponent(LoginDto.class);
		ErrHtmlElementDto errDto = 
			(ErrHtmlElementDto) container.getComponent(ErrHtmlElementDto.class);
		
		return !CommonUtil.isNull(loginDto.getMsgSessionError()) 
					|| errDto.isException();
	}
    public void setSession(HttpSession session) {
        this.session = session;
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
