/**
 * 
 */
package jp.co.isid.mos.bird.common.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.action.HeaderAction;
import jp.co.isid.mos.bird.common.dto.PortalSearchInfoDto;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ヘッダー部アクション
 * 作成日:2008/11/28
 * @author xkinu
 *
 */
public class HeaderActionImpl implements HeaderAction {
    /**
     * メニューＤＴＯ
     */
    private PullDownMenuDto pullDownMenuDto;
    /**
     * 
     */
    private PortalSearchInfoDto portalSearchInfoDto;

    /* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.portal.header.action.HeaderAction#callSearch()
	 * 更新日:2013/09/03 周春建　201308-004_e-mosslesツールバーにお知らせ（タイトル検索）を追加
	 */
	public String callSearch() {
		String viewId= getPortalSearchInfoDto().getRequestViewId();
		if (viewId.length() == 10) {
			String viewIdkbnFlg = viewId.substring(9);
			getPortalSearchInfoDto().setSearchKbnFlg(viewIdkbnFlg);
		}
		viewId= viewId.substring(0,9);
        //リクエストアクションを”初期化”に設定します。
        getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_INIT);
        //リクエスト元画面VIEWIDを設定します。
        getPortalSearchInfoDto().setRequesterViewId(viewId);
        
        getMenuActionImpl().setSelectedViewId(viewId.substring(0,6));
        getMenuActionImpl().setInitViewId(viewId);
        String subViewId = getMenuActionImpl().getSelectedViewId();
        getPullDownMenuDto().setClearFlg(true);
        List menu1= pullDownMenuDto.getMenu1st();
        setSelectMemnuFlg(menu1, subViewId);
        List menu2= pullDownMenuDto.getMenu2nd();
        setSelectMemnuFlg(menu2, subViewId);
        //選択検索画面VIEWIDをリターンします。
		return viewId;
	}

    /**
     * 選択されてたメインメニューのフラグをＯＮに設定
     * @param menu メインメニュー
     * @param selectViewId 選択されたメニュー
     */
    private void setSelectMemnuFlg(List menu, String selectViewId){
        //フラグクリア
        for(int i = 0; i < menu.size(); i++){
            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
            ((CtlBirdMenu)menu.get(i)).setSelectFlg(false);                            
            for(int j = 0; j < submenu.size(); j++){
                ((CtlBirdMenu)submenu.get(j)).setSelectFlg(false);
            }
        }
        
        //選択されてたメインメニューのフラグをONに設定
        ofMenu:for(int i = 0; i < menu.size(); i++){
            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
            for(int j = 0; j < submenu.size(); j++){
            	CtlBirdMenu eSubMenu = ((CtlBirdMenu)submenu.get(j));
                String viewId = eSubMenu.getViewId();
                if(selectViewId.equals(viewId)){
                    ((CtlBirdMenu)menu.get(i)).setSelectFlg(true);                            
                    eSubMenu.setSelectFlg(true);
                    getMenuActionImpl().setInitViewId(eSubMenu.getInitViewId());
                    break ofMenu;
                }
            }
        }
    }
    private MenuActionImpl getMenuActionImpl() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (MenuActionImpl) container.getComponent(MenuActionImpl.class);
    }
	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	/**
	 * @return portalSearchInfoDto を戻します。
	 */
	public PortalSearchInfoDto getPortalSearchInfoDto() {
		return portalSearchInfoDto;
	}

	/**
	 * @param portalSearchInfoDto を クラス変数portalSearchInfoDtoへ設定します。
	 */
	public void setPortalSearchInfoDto(PortalSearchInfoDto portalSearchInfoDto) {
		this.portalSearchInfoDto = portalSearchInfoDto;
	}

}
