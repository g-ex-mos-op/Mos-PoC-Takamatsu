package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.portal.login.logic.CheckRemoteLoginCheckLogic;

/**
 * 社外アクセスチェック
 * @author xnkusama
 */
public class CheckRemoteLoginCheckLogicImpl implements CheckRemoteLoginCheckLogic {

    private GetGamenRoleLogic gamenRoleLogic;

    /**
     * 社外からアクセスのアクセスチェック
     * BR11GMRLで登録されているロール以外は、社外アクセス不可
     * @param userId
     * @param request
     * @return true:アクセス可
     */
    public boolean checkRemote(String userId, HttpServletRequest request) {
        if (!checkLocal(request)) {
            GamenRoleDto gamenRoleDto = new GamenRoleDto();
            gamenRoleDto.setUserId(userId);
            gamenRoleDto.setGamenId("LOGIN");
            gamenRoleDto.setBunruiCd("01");

            List listGamenRole = getGamenRoleLogic().getGamenRole(gamenRoleDto);

            //取得できない場合は、ログインエラー
            if (listGamenRole == null || listGamenRole.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 内部接続チェック処理
     * @param request
     * @return boolean true:内部接続
     */
    private boolean checkLocal(HttpServletRequest request) {
    	// 新BIG-IP用に記述方法の変更: BIG-IP自身のIPではなく、クライアントのIPを取得する
    	// リモートIPのチェック
        HttpServletRequest hr = (HttpServletRequest) request;
        String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );

        Set allowIpKeySet = (Set) BirdContext.getPropertyKeySet("allowHonbuMenuIP");
        Set denyIpKeySet = (Set) BirdContext.getPropertyKeySet("denyHonbuMenuIP");

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
                for (Iterator iteDeny = denyIpKeySet.iterator(); iteDeny.hasNext();) {
                    String denyKey = (String) iteDeny.next();
                    String denyIP = BirdContext.getProperty("denyHonbuMenuIP", denyKey);
                    if (sUserRemoteIP.startsWith(denyIP)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }

    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }
}
