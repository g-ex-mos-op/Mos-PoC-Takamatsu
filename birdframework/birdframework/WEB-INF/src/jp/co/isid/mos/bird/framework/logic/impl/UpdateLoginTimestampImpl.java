package jp.co.isid.mos.bird.framework.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.CtlUserLogDao;
import jp.co.isid.mos.bird.framework.dao.CtlUserLoginDao;
import jp.co.isid.mos.bird.framework.entity.CtlUserLog;
import jp.co.isid.mos.bird.framework.entity.CtlUserLogin;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.UpdateLoginTimestamp;



/**
 * ログイン時間の更新と前回ログイン時間の取得
 * 更新日：2009/04/16 ログイン履歴機能追加
 * @author xkinu
 */
public class UpdateLoginTimestampImpl implements UpdateLoginTimestamp {

    /**
     * 更新日：2009/04/16 ログイン履歴機能追加
     *
     * @param id
     * @return
     * @throws ApplicationException
     */
    public CtlUserLogin registLoginTimes(final BirdUserInfo birdUserInfo) throws ApplicationException {
        String userId = birdUserInfo.getUserID();
        List loginData = null;
        S2Container container = null;

        container = SingletonS2ContainerFactory.getContainer();
        /* 共通FWDAO【ユーザーログイン時間】 */
        CtlUserLoginDao dao = (CtlUserLoginDao) container.getComponent(CtlUserLoginDao.class);
        /* 共通FWDAO【ユーザーログイン時間】.[ログイン時間情報を取得する]を実行 */
        loginData = dao.getUserLogin(userId);
        /* 該当データなし */
        if (loginData == null || loginData.size() == 0) {
            //新規
        	dao.insertUserLogin(userId);
        }
        else {
        	//更新
        	dao.updateUserLogin(userId);
        }
        //登録後のログイン時間情報を取得する
        loginData = dao.getUserLogin(userId);
        CtlUserLogin eLoginInfo = (CtlUserLogin) loginData.get(0);

        //ログイン履歴を登録します。
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        Timestamp logintmsp = eLoginInfo.getCurrentLoginTmsp();
    	// 新BIG-IP用に記述方法の変更: BIG-IP自身のIPではなく、クライアントのIPを取得する
    	// リモートIPのチェック
        HttpServletRequest hr = (HttpServletRequest) request;
        String remoteAddr = hr.getHeader( "x-forwarded-for" );
        //履歴用エンティティを作成し、ログイン時間を設定します。
        CtlUserLog eRireki = new CtlUserLog();
        eRireki.setUserId(userId);//ログインユーザーID
        eRireki.setLoginTmsp(logintmsp);//ログイン時間
        eRireki.setLoginDt((logintmsp.toString().substring(0,10)).replaceAll("-",""));//ログイン年月日

        if(remoteAddr==null || ("").equals(remoteAddr)){
            remoteAddr = request.getRemoteAddr();
        }
        if(remoteAddr != null && remoteAddr.getBytes().length >15) {
        	remoteAddr = remoteAddr.substring(0,15);
        }

        eRireki.setRemoteAddr(remoteAddr);
        String userAgent = request.getHeader("user-agent");
        if(userAgent != null && userAgent.getBytes().length >200) {
        	userAgent = userAgent.substring(0,200);
        }
        eRireki.setUserAgent(userAgent);
        eRireki.setAppId(birdUserInfo.getEmosslesAppId());
        //DAO【ログイン履歴情報】．新規登録を実行します。
        CtlUserLogDao daoRireki = (CtlUserLogDao) container.getComponent(CtlUserLogDao.class);

        daoRireki.insert(eRireki);

        return eLoginInfo;
    }

 }
