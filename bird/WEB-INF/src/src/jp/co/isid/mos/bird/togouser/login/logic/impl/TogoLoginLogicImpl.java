package jp.co.isid.mos.bird.togouser.login.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.SessionListenerBean;
import jp.co.isid.mos.bird.framework.dao.CtlLoginFailKaisuDao;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.framework.logic.impl.DateInfoSetupLogicImpl;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.togouser.login.dao.UITogoUserDao;
import jp.co.isid.mos.bird.togouser.login.dto.TogoUserLoginDto;
import jp.co.isid.mos.bird.togouser.login.entity.UITogoUser;
import jp.co.isid.mos.bird.togouser.login.logic.MstInfoSetupLogic;
import jp.co.isid.mos.bird.togouser.login.logic.TogoLoginLogic;
import jp.co.isid.mos.bird.togouser.login.logic.UserInfoSetupLogic;


/**
 * @author K.Nihonyanagi
 * @date   2008/10/28
 */
public class TogoLoginLogicImpl implements TogoLoginLogic{

    private UserInfoSetupLogic uisl = null;
    private CtlLoginFailKaisuDao clfkaisuDao = null;
    private MstInfoSetupLogic misl = null;

    /**
     * ログイン処理
     * @param LoginDto ログイン情報
     * @param HttpSession
     */
    public UITogoUser execute(final TogoUserLoginDto dto, HttpServletRequest request) throws ApplicationException {
        HttpSession session = request.getSession();
        String userId = dto.getUserId();
        String userPswd = dto.getUserPswd();
        UITogoUser entityUser;

        List userData = null;
        S2Container container = null;

        container = SingletonS2ContainerFactory.getContainer();

        if (userPswd==null){
            userPswd="";
        }

        //１．Dao【ユーザー情報の取得】を実行
        //  　パラメータ： パラメータ．DTO．ユーザーID
        UITogoUserDao dao = (UITogoUserDao) container.getComponent(UITogoUserDao.class);
        userData = dao.getUserInfo(userId);


        //２．処理１で取得した[ユーザー]が０件の場合
        //    ２-a、エラーを発生させる。
        //　　コード：E0608
        //　　MSG：”ログイン”
        //         ”ログインに失敗しました。ユーザーIDが登録されておりません。”
        if (userData == null || userData.size() == 0) {
            throw new GenericErrorException("ログイン", "ログインに失敗しました。ユーザーＩＤが登録されておりません。");
        }
        //２の処理がＯＫなので、ユーザデータを取得する。
        entityUser = (UITogoUser) userData.get(0);


        //<<認証ロックのチェック>>
        //３．Dao【ログイン失敗回数情報の検索】を実行する。
        //    パラメータ： パラメータ．ユーザーID
        //    ３-a、[ログイン失敗回数]の取得件数≠0　AND  [ログイン失敗回数]．ログイン失敗回数　>= 30 の場合
        //    ３-a-1、エラーを発生させる。
        //    コード：    E0404
        //    MSG：”そのユーザーIDはロックされている”
        //         ”ログイン”
        CtlLoginFailKaisu clfKaisu = getClfkaisuDao().getCtlLoginFailKaisu(userId);
        if (clfKaisu != null) {
            BigDecimal failCount = clfKaisu.getLoginFail();
            if (failCount.compareTo(new BigDecimal("30")) >= 0 ){
                throw new CannotExecuteWithReasonException("そのユーザーIDはロックされている", "ログイン");
            }
        }

        //<<ストップフラグチェック>>
        //４．処理１．[ユーザー]．ストップフラグ ＝ ’１’の場合
        //　　４-a、エラーを発生させる。
        //　　コード：E0404
        //　　 　MSG：”そのユーザーIDは使用停止の”
        //　　　    　”ログイン”
        if ("1".equals(entityUser.getStopFlg())) {
            throw new CannotExecuteWithReasonException("そのユーザーIDは使用停止の", "ログイン");
        }


        //以下５の処理用：ロジック【日付情報取得】を実行
        DateInfoSetupLogic dateInfoLogic =
                (DateInfoSetupLogic) container.getComponent(DateInfoSetupLogicImpl.class);
        BirdDateInfo togoDateInfo = dateInfoLogic.getBirdDateInfo();
        //<<パスワード変更日チェック>>
        //５．処理１．[ユーザー]．パスワード更新日 ＋ 90日 ＜ システム日付の場合
        //　５-a、エラーを発生させる。
        //　　コード：E0404
        //　 　　MSG：”パスワードの有効期限が切れている”
        //            ”ログイン”
        //            ”パスワードを変更して下さい。”
        Object pswdUpdtDtPlus90 = null;
        try {
            pswdUpdtDtPlus90 = DateManager.getNextDate(entityUser.getPswdUpdtDt(), 90);
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        if (pswdUpdtDtPlus90.toString().compareTo(togoDateInfo.getSysDate()) < 0) {
            throw new CannotExecuteWithReasonException("パスワードの有効期限が切れている", "ログイン", "パスワードを変更して下さい。");
        }


        //<<パスワードチェック>>
        //６．パラメータ．DTO．パスワード ≠ 処理１．[ユーザー]．パスワードの場合
        //　　６-a、ロジック【認証ロックの更新】を実行する
        //　　パラメータ：  パラメータ．DTO．ユーザーID、'1'(失敗)
        //　　６-ｂ、エラーを発生させる。
        //　　　　コード：E0608
        //　　　　　 MSG：”ログイン”
        //　　　　　　　  ”ログインに失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。”
        if (!userPswd.equals(entityUser.getUserPswd().trim())){
            getUisl().execute(userId, "1");
            throw new GenericErrorException("ログイン", "ログインに失敗しました。ユーザーＩＤとパスワードを再度ご確認下さい。");
        }



        /* パスワードチェック */
        // セッションブリッジキーが存在する場合は、パスワードのチェックはなし
//        String pswdByte = entityUser.getUserPswd();
//        String pswd = "";
//        if (pswdByte != null) {
//            pswd = new String(pswdByte);
//        }
//        String sessionBridgeKey = dto.getSessionBridgeKey();
//        if (sessionBridgeKey == null || "".equals(sessionBridgeKey.trim())) {
//            if (userPswd == null || !userPswd.trim().equals(pswd)) {
//                // ログインエラー
//                throw new GenericErrorException(ERR_MSG_01, ERR_MSG_02);
//            }
//        }
//
        /* セッションリスナーを作成し、セッションに格納 */
        SessionListenerBean sessionListener = new SessionListenerBean(dto.getUserId(), dto.getSessionBridgeKey());
        session.setAttribute("listener", sessionListener);



        //８．ロジック【マスタ情報取得】を実行する
        //    パラメータ：ユーザーID
        session.setAttribute("TOGO_USER_INFO", dao);
        /* セッションに格納 */
        session.setAttribute("togoDateInfo", togoDateInfo);

        /* ブラウザーの種別 */
    	String type = "trident";
    	String userAgent = request.getHeader("user-agent");
    	if (userAgent != null) {
    		if(userAgent.indexOf("Mac") >= 0 && userAgent.indexOf("Safari") >= 0){ // MACのSafariブラウザー
    			type = "safari";
    		}else if (userAgent.indexOf("Chrome") >= 0 || userAgent.indexOf("Safari") >= 0) { // WindowsのChromeとSafariブラウザー
    			type = "webkit";
    		}else if(userAgent.indexOf("Firefox") >= 0) { // WindowsのFirefoxブラウザー
    			type = "gecko";
    		}
    	}
    	session.setAttribute("browserType", type);

        //１０．ロジック【認証ロックの更新】を実行する
        //      パラメータ：  パラメータ．DTO．ユーザーID、'0'(成功)
        getUisl().execute(userId, "0");

        return (UITogoUser) userData.get(0);
    }

    public UserInfoSetupLogic getUisl() {
        return uisl;
    }

    public void setUisl(UserInfoSetupLogic uisl) {
        this.uisl = uisl;
    }

    public CtlLoginFailKaisuDao getClfkaisuDao() {
        return clfkaisuDao;
    }

    public void setClfkaisuDao(CtlLoginFailKaisuDao clfkaisuDao) {
        this.clfkaisuDao = clfkaisuDao;
    }

    public MstInfoSetupLogic getMisl() {
        return misl;
    }

    public void setMisl(MstInfoSetupLogic misl) {
        this.misl = misl;
    }

}
