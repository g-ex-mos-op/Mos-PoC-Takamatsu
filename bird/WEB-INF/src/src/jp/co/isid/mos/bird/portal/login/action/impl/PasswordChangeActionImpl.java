package jp.co.isid.mos.bird.portal.login.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.portal.login.action.PasswordChangeAction;
import jp.co.isid.mos.bird.portal.login.logic.CheckRemoteLoginCheckLogic;
import jp.co.isid.mos.bird.portal.login.logic.UpdatePasswordLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * パスワード変更画面 アクション
 * @author xnkusama
 */
public class PasswordChangeActionImpl implements PasswordChangeAction {

    /* アクションID */
    public static final String changePassword_ACTION_ID = "BPO000A11";
    public static final String back_ACTION_ID           = "BPO000A12";
    /* DTO */
	private LoginDto _loginDto;
    /* LOGIC */
    private UpdatePasswordLogic loginUpdatePasswordLogicImpl;
    private CheckRemoteLoginCheckLogic loginCheckRemoteLoginCheckLogic;
    
    /**
     * DTOを設定します
     * @param loginDto
     */
    public void setLoginDto(final LoginDto loginDto) {
        this._loginDto = loginDto;
    }
    /**
     * DTOを取得します
     * @param loginDto
     */
	private LoginDto getLoginDto() {
	    return this._loginDto;
    }
    
    /**
     * パスワード変更ボタン アクション
     * @return
     */
    public String changePassword() throws ApplicationException {
        
        /* オーナーユーザーの後ゼロ3桁補完 */
        String userID = getLoginDto().getUserId();
        if (isOnerUser(userID)) {
            userID = userID + "000";
            if (userID.length() > 8) {
                userID = userID.substring(0, 8);
            }
            getLoginDto().setUserId(userID);
        }
        
        /* 外部アクセスチェック */
        /* 社外からのアクセスチェック */
        if (!getLoginCheckRemoteLoginCheckLogic().checkRemote(userID, getRequest())) {
            //セッション内のBirdUserInfoを削除
            HttpSession session = getRequest().getSession();
            if (session != null) {
                session.setAttribute("birdUserInfo", null);
            }
            throw new GenericErrorException("ログイン", "ログインに失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。");
        }
        /* １．ロジック【パスワード変更】を実行する */
        getLoginUpdatePasswordLogicImpl().update(getLoginDto());
        
        return "BPO000V01";
	}
    
    /**
     * 戻るボタン アクション
     * @return
     */
    public String back() {
        return "BPO000V01";
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
            if (DefaultJapaneseVerifier.HANKAKU_LATIN.indexOf(checkStr) != -1) {
                isOner = true;
                break;
            }
        }
        return isOner;
    }
    private HttpServletRequest getRequest() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (HttpServletRequest) container.getComponent("request");
    }
    
    public UpdatePasswordLogic getLoginUpdatePasswordLogicImpl() {
        return loginUpdatePasswordLogicImpl;
    }
    public void setLoginUpdatePasswordLogicImpl(
            UpdatePasswordLogic updatePasswordLogicImpl) {
        this.loginUpdatePasswordLogicImpl = updatePasswordLogicImpl;
    }
    public CheckRemoteLoginCheckLogic getLoginCheckRemoteLoginCheckLogic() {
        return loginCheckRemoteLoginCheckLogic;
    }
    public void setLoginCheckRemoteLoginCheckLogic(
            CheckRemoteLoginCheckLogic loginCheckRemoteLoginCheckLogic) {
        this.loginCheckRemoteLoginCheckLogic = loginCheckRemoteLoginCheckLogic;
    }
}