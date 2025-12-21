/**
 *
 */
package jp.co.isid.mos.bird.minisite.login.action.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.dto.ErrHtmlElementDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.minisite.login.action.LoginAction;
import jp.co.isid.mos.bird.minisite.login.logic.LoginLogic;
/**
 * ログインアクション
 *
 * 作成日:2018/08/05
 * @author zcj
 *
 */
public class LoginActionImpl implements LoginAction {
    /* アクションID：MNI000A01 初期化処理 */
    public static final String initialize_ACTION_ID = "MNI000A01";
    /* アクションID：MNI000A01 ログイン処理 */
    public static final String login_ACTION_ID = "MNI000A01";
    /** ログインリクエスト日時(IE9対応用) **/
    private long requestTime = (new Date()).getTime();
    private HttpSession session;

	private String key;

	/** LOGIC【minisiteログイン処理】*/
	private LoginLogic minisiteLoginLogic;

	/**
	 * 初期化処理
	 */
	public String initialize() {
		return null;
	}

	/**
	 * ログイン処理
	 *
	 *
	 * @see jp.co.isid.mos.bird.pmossles.action.LoginAction#login()
	 */
	public String login() {
        //１．LOGIC【p-mosslesログイン】を実行します。
		String nextViewId = getMinisiteLoginLogic().execute(getRequest());
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

	public LoginLogic getMinisiteLoginLogic() {
		return minisiteLoginLogic;
	}

	public void setMinisiteLoginLogic(LoginLogic minisiteLoginLogic) {
		this.minisiteLoginLogic = minisiteLoginLogic;
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
	 * @return クラス変数requestTime を戻します。
	 */
	public long getRequestTime() {
		return requestTime;
	}

	/**
	 * ログインリクエスト日時設定処理
	 *
	 * @param requestTime を クラス変数requestTimeへ設定します。
	 */
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
