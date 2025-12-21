package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.portal.login.logic.CheckRemoteSessionTimeExtendLimitCheckLogic;

/**
 * 社外アクセスチェック
 * @author xnkusama
 */
public class CheckRemoteSessionTimeExtendLimitCheckLogicImpl implements CheckRemoteSessionTimeExtendLimitCheckLogic {

    /**
     * 内部接続チェック処理
     * @param request
     * @return boolean true:内部接続
     */
    public boolean checkAllow(HttpServletRequest request) {
    	// 新BIG-IP用に記述方法の変更: BIG-IP自身のIPではなく、クライアントのIPを取得する
    	// リモートIPのチェック
        HttpServletRequest hr = (HttpServletRequest) request;
        String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );

        Set allowIpKeySet = (Set) BirdContext.getPropertyKeySet("allowHonbuMenuIP");

		if (sUserRemoteIP == null || "".equals(sUserRemoteIP.trim())) {
			sUserRemoteIP = request.getRemoteAddr();
			if (sUserRemoteIP == null || "".equals(sUserRemoteIP.trim())) {
				return false;
			}
		}

        for (Iterator iteAllow = allowIpKeySet.iterator(); iteAllow.hasNext();) {
            String allowKey = (String) iteAllow.next();
            String allowIP = BirdContext.getProperty("allowHonbuMenuIP", allowKey);

            if (sUserRemoteIP.startsWith(allowIP)) {
                return true;
            }
        }
        return false;
    }

}
