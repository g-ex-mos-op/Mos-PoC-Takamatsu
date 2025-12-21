/*
 * 作成日: 2006/2/28
 */
package jp.co.isid.mos.bird.sysadmin.help.action.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.sysadmin.help.action.HelpMenuAction;
import jp.co.isid.mos.bird.sysadmin.help.dto.UserMenuDto;
import jp.co.isid.mos.bird.sysadmin.help.logic.SearchMenuLogic;
import jp.co.isid.mos.bird.sysadmin.help.logic.impl.SearchMenuLogicImpl;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ヘルプアクションクラス
 * 更新：2006/10/26 ヘルプ不具合に伴う修正
 * 更新：2012/03/28 xkinu IEキャッシュ対応
 *       ヘルプファイル更新履歴追加。
 * 　　　最新のヘルプファイルの読込ためのパラメータとして使用されます。
 * @author lee
 */
public class HelpMenuActionImpl implements HelpMenuAction {

    public static String initialize_ACTION_ID = "BSA009A01";

	private UserMenuDto userMenuDto;
	
	private BirdUserInfo birdUserInfo;
	
	/**
	 * 更新：2012/03/28 xkinu IEキャッシュ対応
	 *       ヘルプファイル更新履歴追加。
	 * 　　　最新のヘルプファイルの読込ためのパラメータとして使用されます。
	 */
	public String initialize() {

		S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        //---2006/10/26 表示PDFを判断するshowViewIdパラメータの取得方法を変更
        //   showViewIdプロパティへDIした値を使用せずにRequestから取得して使用する
        Enumeration e = request.getParameterNames();
        String showViewId = "";
        while (e.hasMoreElements()) {
            String s = (String) e.nextElement();
            if (s.indexOf("showViewId") != -1) {
                showViewId = request.getParameter(s) == null ? "" : request.getParameter(s);
                break;
            }
        }

        if (showViewId != null && showViewId.trim().length() >= 6) {
            showViewId = showViewId.substring(0,6);
        }
        else {
            showViewId = "";
        }
		SearchMenuLogic searchMenuLogic = (SearchMenuLogic) container.getComponent(SearchMenuLogicImpl.class);
		searchMenuLogic.execute(getBirdUserInfo().getUserID(), getUserMenuDto(), showViewId);
		
		return null;
	}

	/**
	 * @return userMenuDto を戻します。
	 */
	public UserMenuDto getUserMenuDto() {
		return userMenuDto;
	}

	/**
	 * @param userMenuDto
	 *            を設定。
	 */
	public void setUserMenuDto(UserMenuDto userMenuDto) {
		this.userMenuDto = userMenuDto;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo
	 *            birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
}

