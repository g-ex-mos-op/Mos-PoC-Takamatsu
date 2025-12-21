package jp.co.isid.mos.bird.framework.logic.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.control.SessionListenerBean;
import jp.co.isid.mos.bird.framework.dao.CtlLoginFailKaisuDao;
import jp.co.isid.mos.bird.framework.dao.LoginDao;
import jp.co.isid.mos.bird.framework.dao.MstOnerDao;
import jp.co.isid.mos.bird.framework.dto.HeaderDto;
import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.entity.CtlUserLogin;
import jp.co.isid.mos.bird.framework.entity.MstOner;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UILoginBirdUser;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.framework.logic.LoginLogic;
import jp.co.isid.mos.bird.framework.logic.PasswordLockLogic;
import jp.co.isid.mos.bird.framework.logic.UpdateLoginTimestamp;
import jp.co.isid.mos.bird.framework.logic.UserInfoSetupLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;


/**
 * ログインロジック
 *
 * @author xnkusama
 * 更新日 2012/02/09 xkinu 追加 p-mossles時のチェック処理仕様変更
 * 　　・パスワード有効期限チェックは行わない。
 * 　　・ＩＤとパスワードの同一チェックは行わない。
 */
public class LoginLogicImpl implements LoginLogic{

    //ユーザータイプ
    private static final String USER_TYPE_ONER = "02";
    //メニュー情報
    private static final String MENU_FIRST = "menu1st";
    private static final String MENU_SECOND = "menu2nd";
    private static final String REGIST_MENU_FIRST = "registMenu1st";
    private static final String REGIST_MENU_SECOND = "registMenu2nd";
    private static final String MENU_HELP = "help";
    private static final String MENU_DISP_FLG = "MENU_DISP_FLG";

    private static final String ERR_MSG_01 = "ログイン";
    private static final String ERR_MSG_02 = "ログインに失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。";
    private static final String ERR_MSG_03 = "そのユーザーIDは現在使用停止になっている";
    private static final String ERR_MSG_04 = "そのユーザーＩＤは現在使用停止になっている";
    private static final String ERR_MSG_05 = "ＩＤとパスワードが同じです。パスワードを変更してください。";

    /*DAO*/
    private CtlLoginFailKaisuDao ctlLoginFailKaisuDao;
    /*LOGIC*/
    private PasswordLockLogic passwordLockLogic;


    /**
     * ログイン処理
     * @param LoginDto ログイン情報
     * @param HttpSession
     */
    public UILoginBirdUser execute(final LoginDto dto, HttpServletRequest request) throws ApplicationException {
        HttpSession session = request.getSession();
        String userId = dto.getUserId();
        String userPswd = dto.getUserPswd();
        UILoginBirdUser entityUser;

        List userData = null;
        S2Container container = null;

        container = SingletonS2ContainerFactory.getContainer();

        /* ユーザー情報取得 */
        LoginDao dao = (LoginDao) container.getComponent(LoginDao.class);
        userData = dao.getUserInfo(userId);


        /* 該当データなし */
        if (userData == null || userData.size() == 0) {
            // ログインエラー
            throw new GenericErrorException(ERR_MSG_01, ERR_MSG_02);
        }
        entityUser = (UILoginBirdUser) userData.get(0);

        //<<認証ロックのチェック>>
        CtlLoginFailKaisu clfKaisu = getCtlLoginFailKaisuDao().getCtlLoginFailKaisu(userId);
        if (clfKaisu != null && dto.getPswdLockCnt() != 0) {
            BigDecimal failCount = clfKaisu.getLoginFail();
            if (failCount.compareTo(new BigDecimal(dto.getPswdLockCnt())) >= 0 ){
                throw new CannotExecuteWithReasonException("そのユーザーIDはロックされている", "ログイン");
            }
        }

        /* ロジック【日付情報取得】を実行 */
        DateInfoSetupLogic dateInfoLogic =
                (DateInfoSetupLogic) container.getComponent(DateInfoSetupLogicImpl.class);
        BirdDateInfo birdDateInfo = dateInfoLogic.getBirdDateInfo();

        /* ストップフラグ確認 */
        if ("1".equals(entityUser.getStopFlg())&& !dto.isPmossles()) {
            throw new CannotExecuteWithReasonException(ERR_MSG_03, ERR_MSG_01);
        }
        //2006/05/01 追加
        //e-mosslesからの遷移の場合はチェックしない。
        //p-mosslesからの遷移の場合はチェックしない。(2012/02/09追加)
        if(!dto.isFromEmosFlg() && !dto.isPmossles()){
            /* パスワード有効期限チェック */
            checkPasswordInvalid(entityUser.getPswdUpdtDt(), birdDateInfo.getSysDate(), dto.getPswdAvailableTerm());
        }

        /* パスワードチェック */
        // セッションブリッジキーが存在する場合は、パスワードのチェックはなし
        byte[] pswdByte = entityUser.getUserPswd();
        String pswd = "";
        if (pswdByte != null) {
            pswd = new String(pswdByte);
        }
        String sessionBridgeKey = dto.getSessionBridgeKey();
        if (sessionBridgeKey == null || "".equals(sessionBridgeKey.trim())) {
            if (userPswd == null || !rtrim(userPswd).equals(rtrim(pswd))) {
                // パスワードロックカウント
                getPasswordLockLogic().execute(userId, PasswordLockLogic.LOGIN_FAILURE, dto.getGamenId());
                // ログインエラー
                throw new GenericErrorException(ERR_MSG_01, ERR_MSG_02);
            }
        }

        /* セッションリスナーを作成し、セッションに格納 */
        SessionListenerBean sessionListener = new SessionListenerBean(dto.getUserId(), dto.getSessionBridgeKey());
        session.setAttribute("listener", sessionListener);

        /* ロジック【マスタ情報取得】を実行 */
        UserInfoSetupLogic userInfoLogic =
                (UserInfoSetupLogic) container.getComponent(UserInfoSetupLogicImpl.class);
        BirdUserInfo birdUserInfo = userInfoLogic.execute(userId, request);

        /* オーナー解約チェック */
        if (USER_TYPE_ONER.equals(birdUserInfo.getMstUser().getUserTypeCd())) {
            checkOnerKaiyaku(birdUserInfo, birdDateInfo);
        }
        //p-mosslesからの遷移の場合はチェックしない。(2012/02/09追加)
        if(!dto.isPmossles()) {
	        /* パスワード同一チェック*/
	        checkPasswordSameId(userId, userPswd.trim());
        }
        /* セッションに格納 */
        session.setAttribute("birdUserInfo", birdUserInfo);
        session.setAttribute("birdDateInfo", birdDateInfo);

        GetAccessMenuLogic logic = (GetAccessMenuLogic)container.getComponent(GetAccessMenuLogic.class);
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto)container.getComponent(PullDownMenuDto.class);
        MstUser user = new MstUser();
        user.setUser_id(birdUserInfo.getUserID());
        Map alldata = (Map)logic.execute(user);
        pullDownMenuDto.setMenu1st((List)alldata.get(MENU_FIRST));
        pullDownMenuDto.setMenu2nd((List)alldata.get(MENU_SECOND));
        pullDownMenuDto.setRegistMenu1st((List)alldata.get(REGIST_MENU_FIRST));
        pullDownMenuDto.setRegistMenu2nd((List)alldata.get(REGIST_MENU_SECOND));
        pullDownMenuDto.setMenuHelp((CtlBirdMenu)alldata.get(MENU_HELP));
        pullDownMenuDto.setMenuDispFlg(((Boolean) alldata.get(MENU_DISP_FLG)).booleanValue());
        pullDownMenuDto.setClearFlg(true);

        //登録系メニューが存在するかをPullDownMenuDtoにセット
        List listRegist = pullDownMenuDto.getRegistMenu1st();
        pullDownMenuDto.setExistRegitMenu(false);
        if (listRegist != null && !listRegist.isEmpty()) {
            CtlBirdMenu entity = (CtlBirdMenu) listRegist.get(0);
            if (entity.getSubMenuList() != null && !entity.getSubMenuList().isEmpty()) {
                pullDownMenuDto.setExistRegitMenu(true);
            }
        }
        //切替メニュー状態フラグをリセット
        pullDownMenuDto.setMenuDispPtn(false);

        createHeader(birdUserInfo);

        // パスワードロッククリア
        getPasswordLockLogic().execute(userId, PasswordLockLogic.LOGIN_SUCCESS, dto.getGamenId());

        return (UILoginBirdUser) userData.get(0);
    }

    /**
     * パスワード有効期限チェック
     * @param updt    パスワード変更日
     * @param sysDate システム日付
     * @param pswdAvailableTerm パスワード有効期間
     */
    private void checkPasswordInvalid(String updt, String sysDate, int pswdAvailableTerm) throws ApplicationException {

        //パスワード有効期間＝０の場合は、チェックなし
        if (pswdAvailableTerm == 0) {
            return;
        }

        String limitDay;
        try {
            limitDay = DateManager.getNextDate(updt, pswdAvailableTerm);
        }
        catch (Exception ex) {
            throw new FtlSystemException("パスワード有効期限チェック");
        }

        if(sysDate.compareTo(limitDay) > 0) {
            throw new CannotExecuteWithReasonException("パスワードの有効期限が切れている",
                                                        "ログイン",
                                                        "パスワードを変更して下さい。");
        }
    }

    /**
     * ヘッダー情報を作成
     * @param birdUserInfo ログインユーザ情報
     */
    private void createHeader(BirdUserInfo birdUserInfo){
        MstUser user = (MstUser)birdUserInfo.getInfo(BirdUserInfo.MST_USER);

        S2Container container = SingletonS2ContainerFactory.getContainer();
        //前回ログイン時間取得処理
        UpdateLoginTimestamp lastLoginLogic = (UpdateLoginTimestamp)container.getComponent(UpdateLoginTimestampImpl.class);
        CtlUserLogin userLogin = lastLoginLogic.registLoginTimes(birdUserInfo);

        HeaderDto dto = (HeaderDto)container.getComponent(HeaderDto.class);
        dto.setUserId(user.getUser_id());
        dto.setUserNameKj(user.getUser_name());
        dto.setLastLogin(userLogin.getLastLoginTmsp());
    }

    /**
     * オーナーユーザーの解約済みチェック
     *
     */
    private void checkOnerKaiyaku(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo) {
        String sysDt = birdDateInfo.getSysDate();
        S2Container container = SingletonS2ContainerFactory.getContainer();
        MstOnerDao mstOnerDao = (MstOnerDao) container.getComponent(MstOnerDao.class);

        List listUserOner = birdUserInfo.getUserOner();

        UIUserOner uiUserOner = (UIUserOner) listUserOner.get(0);

        MstOner mstOner = mstOnerDao.selectOner(uiUserOner.getCompanyCd(), uiUserOner.getOnerCd());

        if (!(mstOner.getKeiyakuSta() == null || mstOner.getKeiyakuSta().trim().equals("")) && mstOner.getKeiyakuSta().compareTo(sysDt) > 0) {
            throw new CannotExecuteWithReasonException(ERR_MSG_04, ERR_MSG_01);
        }
        if (!(mstOner.getKeiyakuEnd() == null || mstOner.getKeiyakuEnd().trim().equals("")) && mstOner.getKeiyakuEnd().compareTo(sysDt) < 0) {
            throw new CannotExecuteWithReasonException(ERR_MSG_04, ERR_MSG_01);
        }

    }
    /**
     * パスワード確認処理（全ユーザー対象)
     * ＩＤと同じものだった場合、ログイン不可となり、
     * 「ＩＤとパスワードが同じです。パスワードを変更してください。」とメッセージが出る。
     *
     * @param userId
     * @param userPswd
     */
    private void checkPasswordSameId(String userId, String userPswd) {
    	if(userId.equals(userPswd)) {
	        // ログインエラー
	        throw new GenericErrorException(ERR_MSG_01, ERR_MSG_05);
    	}
    }

    private String rtrim(String value) {
        if (value == null || "".equals(value.trim())) {
            return value;
        }
        int pos = value.length();
        for (int i = value.length(); i > 0; i--) {
            if (!" ".equals(value.substring(i -1, i))) {
                pos = i;
                break;
            }
        }
        return value.substring(0, pos);
    }

    public CtlLoginFailKaisuDao getCtlLoginFailKaisuDao() {
        return ctlLoginFailKaisuDao;
    }

    public void setCtlLoginFailKaisuDao(CtlLoginFailKaisuDao ctlLoginFailKaisuDao) {
        this.ctlLoginFailKaisuDao = ctlLoginFailKaisuDao;
    }

    public PasswordLockLogic getPasswordLockLogic() {
        return passwordLockLogic;
    }

    public void setPasswordLockLogic(PasswordLockLogic passwordLockLogic) {
        this.passwordLockLogic = passwordLockLogic;
    }

}
