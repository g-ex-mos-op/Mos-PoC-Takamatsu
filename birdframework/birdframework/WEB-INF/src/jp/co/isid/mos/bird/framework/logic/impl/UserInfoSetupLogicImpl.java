/*
 * 作成日: 2006/01/11
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.CtlUserCompanyDao;
import jp.co.isid.mos.bird.framework.dao.CtlUserRoleDao;
import jp.co.isid.mos.bird.framework.dao.MstUserDao;
import jp.co.isid.mos.bird.framework.dao.MyMenuDao;
import jp.co.isid.mos.bird.framework.dao.UIUserEmosslesAppIdDao;
import jp.co.isid.mos.bird.framework.dao.UIUserMiseDao;
import jp.co.isid.mos.bird.framework.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIUserEmosslesAppId;
import jp.co.isid.mos.bird.framework.exception.NoSessionException;
import jp.co.isid.mos.bird.framework.logic.UserInfoSetupLogic;

/**
 * ログイン時にユーザー関連情報をセッションに保持しておく
 * @author xnkusama
 */
public class UserInfoSetupLogicImpl implements UserInfoSetupLogic {
    /*ユーザータイプ*/
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_ONER = "02";
    private static final String USER_TYPE_MISE = "03";

    public BirdUserInfo execute(final String userId, final HttpServletRequest request) {

        BirdUserInfo userInfo = new BirdUserInfo(userId);

        S2Container s2Con = SingletonS2ContainerFactory.getContainer();

        /* ユーザー情報 */
        MstUserDao mstUserDao = (MstUserDao) s2Con.getComponent(MstUserDao.class);
        List mstUserList = mstUserDao.getMstUser(userId);
        if (mstUserList.size() > 0) {
            //ユーザー名称の半角・全角スペースのトリムを行う
            MstUser mstUser = (MstUser) mstUserList.get(0);
            String name = mstUser.getUser_name();
            while(!(" ".indexOf(name.charAt(name.length() - 1)) < 0 && "　".indexOf(name.charAt(name.length() - 1)) < 0)) {
                name = StringUtil.rtrim(name, " ");
                name = StringUtil.rtrim(name, "　");
            }
            mstUser.setUser_name(name);
            userInfo.setInfo(mstUser, BirdUserInfo.MST_USER);
        }
        else {
            throw new NoSessionException();
        }

        /* ユーザ対応オーナー */
        UIUserOnerDao userOnerDao = (UIUserOnerDao) s2Con.getComponent(UIUserOnerDao.class);
        List userOnerList = userOnerDao.getUserOnerList(userId);
        userInfo.setInfo(userOnerList, BirdUserInfo.MST_USER_ONER);

        /* ユーザ対応店舗 */
        UIUserMiseDao userMiseDao = (UIUserMiseDao) s2Con.getComponent(UIUserMiseDao.class);
        List userMiseList = userMiseDao.getUserMiseInfo(userId);
        userInfo.setInfo(userMiseList, BirdUserInfo.MST_USER_MISE);

        /* ユーザロール */
        CtlUserRoleDao userRoleDao = (CtlUserRoleDao) s2Con.getComponent(CtlUserRoleDao.class);
        List userRoleList = userRoleDao.getUserRoleList(userId);
        userInfo.setInfo(userRoleList, BirdUserInfo.MST_USER_ROLE);

        /* ユーザ所属会社 */
        CtlUserCompanyDao userCompanyDao = (CtlUserCompanyDao) s2Con.getComponent(CtlUserCompanyDao.class);
        List userCompanyList = userCompanyDao.getUserCompanyList(userId);
        userInfo.setInfo(userCompanyList, BirdUserInfo.MST_USER_COMPANY);

        userInfo.setLocal(checkLocal(userInfo, request));
        /* ログインユーザ サイト区分
         * このサイト区分によりログイン後の画面のヘッダー部のカラー分けを行います。*/
        userInfo.setInfo(getUserSiteKbn(userInfo), BirdUserInfo.TRN_EMOSSLES_APP_ID);
        /* ログインユーザ マイメニューリスト*/
        MyMenuDao myMenuDao = (MyMenuDao) s2Con.getComponent(MyMenuDao.class);
        List listMyMenu = myMenuDao.select(userId);
        userInfo.setInfo(listMyMenu, BirdUserInfo.MY_MENU_LIST);
        /* ブラウザーの種別 */
        String browserType = checkBrowserType(request);
        userInfo.setInfo(browserType, BirdUserInfo.BROWSER_TYPE);
        return userInfo;
    }

    /**
     * ブラウザーの種別を取得
     */
    private String checkBrowserType(final HttpServletRequest request) {
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
    	return type;
    }

    /**
     * ログインユーザーサイト区分判別
     */
    private UIUserEmosslesAppId getUserSiteKbn(BirdUserInfo userInfo) {
        UIUserEmosslesAppId entityEmosslesAppId = new UIUserEmosslesAppId();

        if (USER_TYPE_MISE.equals(userInfo.getMstUser().getUserTypeCd())) {
            //ユーザータイプ＝店舗の場合は所属区分で判断
            UIUserEmosslesAppIdDao userEmosslesAppIdDao = (UIUserEmosslesAppIdDao) SingletonS2ContainerFactory.getContainer().getComponent(UIUserEmosslesAppIdDao.class);
            entityEmosslesAppId = userEmosslesAppIdDao.select(userInfo.getUserID());
        }
        else if (USER_TYPE_HONBU.equals(userInfo.getMstUser().getUserTypeCd())) {
            //ユーザータイプ＝本部の場合は、サイト区分＝うみ
            entityEmosslesAppId.setAppId("2");
        }
        else if (USER_TYPE_ONER.equals(userInfo.getMstUser().getUserTypeCd())) {
            //ユーザータイプ＝オーナーの場合は、サイト区分＝やま
            entityEmosslesAppId.setAppId("1");
        }
        return entityEmosslesAppId;
    }

    /**
     * 内部接続チェック処理
     * @param userInfo
     * @param request
     * @return boolean true:内部接続
     */
    private boolean checkLocal(BirdUserInfo userInfo, HttpServletRequest request) {
    	// 新BIG-IP用に記述方法の変更: BIG-IP自身のIPではなく、クライアントのIPを取得する
    	// リモートIPのチェック

        HttpServletRequest hr = (HttpServletRequest) request;
        String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );

        Set allowIpKeySet = (Set) BirdContext.getPropertyKeySet("allowIP");
        Set denyIpKeySet = (Set) BirdContext.getPropertyKeySet("denyIP");

		if (sUserRemoteIP == null || "".equals(sUserRemoteIP.trim())) {
			sUserRemoteIP = request.getRemoteAddr();
			if (sUserRemoteIP == null || "".equals(sUserRemoteIP.trim())) {
				return false;
			}
		}

        for (Iterator iteAllow = allowIpKeySet.iterator(); iteAllow.hasNext();) {
            String allowKey = (String) iteAllow.next();
            String allowIP = BirdContext.getProperty("allowIP", allowKey);

            if (sUserRemoteIP.startsWith(allowIP)) {
                for (Iterator iteDeny = denyIpKeySet.iterator(); iteDeny.hasNext();) {
                    String denyKey = (String) iteDeny.next();
                    String denyIP = BirdContext.getProperty("denyIP", denyKey);
                    if (sUserRemoteIP.startsWith(denyIP)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
