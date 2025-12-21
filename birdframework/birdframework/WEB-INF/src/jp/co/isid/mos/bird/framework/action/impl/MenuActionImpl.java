/*
 * 作成日: 2005/12/02
 *
 */
package jp.co.isid.mos.bird.framework.action.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.CtlGamenRoleDao;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.logic.RegOutputLogLogic;
import jp.co.isid.mos.bird.framework.logic.impl.GetAccessMenuLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * メニュー作成アクション
 * 更新：2006/10/26 ヘルプ不具合に伴う修正
 * @author xytamura 
 */
public class MenuActionImpl {
    
    /**
     * メニューＤＴＯ
     */
    private PullDownMenuDto pullDownMenuDto; 
    
    /**
     * メニュー取得ロジック
     */
    private static final Class LOGIC_GET_ACCESSE_MENU = GetAccessMenuLogic.class;
    /**
     * ポータルトップViewID
     */
    private static final String PORTAL_TOP_VIEW_ID = "BPO001";

    /* GetOutLinkLogic */
    private RegOutputLogLogic regOutputLogLogic ;
    
    /**
     * ユーザ情報
     */
    //private static final Class ENTITY_MST_USER = MstUser.class;
    
    /**
     * 遷移先の初期表示VIEWID
     */
    private String initViewId="BPO001V01"; 
    /**
     * 遷移先のメニューID
     */
    private String initMenuId;
    /**
     * 遷移先のサブメニューID
     */
    private String initSubMenuId;
    /**
     * 遷移先VIEWID
     * 
     * 選択画面系の場合は、遷移元のVIEWIDが設定されています。
     */
    private String showViewId="BPO001V01"; 

    /**
     * 選択されたビューＩＤ
     */
    private String selectedViewId; 
    
    /**
     * 現在選択中のメインメニューのビューＩＤ
     */
    private String currentMainViewId;
    /**
     * BIRDユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    /**
     * 登録系メニュー 基準値
     */
    private String registMenuIdThresholdAmount;
    
    private static final String MENU_FIRST = "menu1st";

    private static final String MENU_SECOND = "menu2nd";

    private static final String MENU_HELP = "help";
    
    private static final String LOGOUT = "LOGOUT";
    
    /**
     * メニュー切替アクション
     */
    public String changeMenu() {
        //getPullDownMenuDto().setMenuDispPtn(!getPullDownMenuDto().isMenuDispPtn());
        return null;
    }
    /**
     * メニュー作成アクションを実行します。
     */
    public String menuCreate()  {
                
        S2Container container = SingletonS2ContainerFactory.getContainer();
        
        MstUser mstUser = getBirdUserInfo().getMstUser();
        GetAccessMenuLogic logic = (GetAccessMenuLogic)container.getComponent(LOGIC_GET_ACCESSE_MENU);
        
        Map alldata = (Map)logic.execute(mstUser);
        
        pullDownMenuDto.setMenu1st((List)alldata.get(MENU_FIRST));        
        pullDownMenuDto.setMenu2nd((List)alldata.get(MENU_SECOND));
        pullDownMenuDto.setMenuHelp((CtlBirdMenu)alldata.get(MENU_HELP));
        
        return null;
    }
    
    
    /**
     * メニューＤＴＯをセットします。
     * @param pullDownMenuDto メニューＤＴＯ
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto ){
        this.pullDownMenuDto = pullDownMenuDto;    
    }
    
    /**
     * メニューＤＴＯを取得します。
     * @return メニューＤＴＯ
     */
    public PullDownMenuDto getPullDownMenuDto(){
        return pullDownMenuDto;    
    }
    
    /**
     * 遷移先のVIEWIDをセット
     * @param showViewId
     */
    public void setShowViewId(String showViewId){
        //---2006/10/26 update 共通フォーム系の画面IDはセットしないよう変更
        if (showViewId != null && !showViewId.trim().equals("") && !showViewId.startsWith("BCO")) {
            this.showViewId = showViewId;
        }
    }
    
    /**
     * 遷移先のビューＩＤを取得します
     * @param showViewId
     */
    public String getShowViewId(){
        
    	return showViewId;
    }
    /**
     * プルダウンメニューが選択された
     * @return
     */
    public String selectMenu(){
        getPullDownMenuDto().setClearFlg(true);
        //現在選択中のメインメニューのビューＩＤを設定
        setSelectedMainMenu();
        //ポータルトップへ遷移の時は、選択メニューIDをクリア
        if (showViewId != null && showViewId.startsWith("BPO001")) {
            setInitMenuId("");
            setInitSubMenuId("");
        }

        //---2009/02/09 登録系メニューはログ出力しない
//        if (getInitMenuId() != null) {
//            if (getInitMenuId().compareTo(getRegistMenuIdThresholdAmount()) >= 0) {
//                S2Container container = SingletonS2ContainerFactory.getContainer();
//                HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
//                getRegOutputLogLogic().execute(request, getBirdUserInfo(), "REG");
//            }
//        }
        
        return showViewId;
    }
    /**
     * BIRDユーザー情報を設定します
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }


    /**
     * BIRDユーザー情報を取得します
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    
    /**
     * ログアウトします。
     */
    public String logout() {
        HttpSession session =  (HttpSession)SingletonS2ContainerFactory.getContainer().getComponent("session");
        session.invalidate();
        return LOGOUT;
    }

    /**
     * 選択されたビューＩＤ
     * @return 選択されたビューＩＤ
     */
    public String getSelectedViewId() {
        return selectedViewId;
    }
    
    /**
     * 選択されたビューＩＤを設定します。
     * @param 選択されたビューＩＤ
     */
    public void setSelectedViewId(String selectedViewId) {
        this.selectedViewId = selectedViewId;
    }
    
    /**
     * 現在選択中のメインメニューのビューＩＤを取得します。
     * @return 現在選択中のメインメニューのビューＩＤ
     */
    public String getCurrentMainViewId() {
        return currentMainViewId;
    }
    
    /**
     * 現在選択中のメインメニューのビューＩＤを取得します。
     * @param 現在選択中のメインメニューのビューＩＤ
     */
    public void setCurrentMainViewId(String currentMainViewId) {
        this.currentMainViewId = currentMainViewId;
    }
    
    /**
     * 選択されたカテゴリー(メインメニュー)を設定
     */
    public void setSelectedMainMenu(){
        //現在選択されているメニューIDを取得
        String selectedMenuId = getSelectedMenuId();
        
        //選択メニュークリア
        clearSelectMenuFlg();
        
        //ポータルトップへの遷移の場合は処理なし
        if (PORTAL_TOP_VIEW_ID.equals(getSelectedViewId())) {
            return;
        }
        
        String menuId = getInitMenuId();
        String subMenuId = getInitSubMenuId();
        List menu1= pullDownMenuDto.getMenu1st();
        boolean selectMenu = setSelectMemnuFlg(menu1, getSelectedViewId(), menuId, subMenuId);
        if (!selectMenu) {
            List menu2= pullDownMenuDto.getMenu2nd();
            selectMenu = setSelectMemnuFlg(menu2, getSelectedViewId(), menuId, subMenuId);
        }
        if (!selectMenu) {
            List registMenu1 = pullDownMenuDto.getRegistMenu1st();
            selectMenu = setSelectMemnuFlg(registMenu1, getSelectedViewId(), menuId, subMenuId);
        }
        if (!selectMenu) {
            List registMenu2 = pullDownMenuDto.getRegistMenu2nd();
            selectMenu = setSelectMemnuFlg(registMenu2, getSelectedViewId(), menuId, subMenuId);
        }
        //見つからない場合は、MenuID、SubMenuIdで探す
        if (!selectMenu) {
            selectMenu = setSelectedMainMenu(menuId, subMenuId);
        }
        
        //見つからない場合は、元の選択メニューに戻す
        if (!selectMenu) {
            if (selectedMenuId != null) {
                setSelectedMainMenu(selectedMenuId, null);
            }
        }
        
        //ユーザータイプ取得
		String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
		//ユーザータイプがオーナー又は店舗の場合、
		//メニューコードが"09"とサブメニューコードが"01"の時、バンズ自動設定数量照会/変更にVIEW_IDを設定する
		if ("02".equals(userTypeCd) || "03".equals(userTypeCd)) {
			if ("09".equals(menuId) && "01".equals(subMenuId)) {
				setBunsAutoShowViewId();
			}
		}
    }
    /**
	 * 選択されたカテゴリー(メインメニュー)を設定
	 * 
	 * @param viewId
	 * @param menuId
	 * @param subMenuId
	 */
    public void setSelectedMainMenu(String viewId, String menuId, String subMenuId){
        //現在選択されているメニューIDを取得
        String selectedMenuId = getSelectedMenuId();
        
        //選択メニュークリア
        clearSelectMenuFlg();
        
        String subViewId = viewId;
        if (viewId != null && subViewId.length() > 6) {
            subViewId = viewId.substring(0, 6);
        }
        //ポータルトップへの遷移の場合は処理なし
        if (PORTAL_TOP_VIEW_ID.equals(subViewId)) {
            return;
        }
        
        List menu1= pullDownMenuDto.getMenu1st();
        boolean selectMenu = setSelectMemnuFlg(menu1, subViewId, menuId, subMenuId);
        if (!selectMenu) {
            List menu2= pullDownMenuDto.getMenu2nd();
            selectMenu = setSelectMemnuFlg(menu2, subViewId, menuId, subMenuId);
        }
        if (!selectMenu) {
            List registMenu1 = pullDownMenuDto.getRegistMenu1st();
            selectMenu = setSelectMemnuFlg(registMenu1, subViewId, menuId, subMenuId);
        }
        if (!selectMenu) {
            List registMenu2 = pullDownMenuDto.getRegistMenu2nd();
            selectMenu = setSelectMemnuFlg(registMenu2, subViewId, menuId, subMenuId);
        }
        //見つからない場合は、MenuID、SubMenuIdで探す
        if (!selectMenu) {
            selectMenu = setSelectedMainMenu(menuId, subMenuId);
        }
        
        //見つからない場合は、元の選択メニューに戻す
        if (!selectMenu) {
            if (selectedMenuId != null) {
                setSelectedMainMenu(selectedMenuId, null);
            }
        }
    }
    
    /**
     * 選択されたカテゴリー(メインメニュー)を設定
     * @param menuId 
     * @param subMenuId
     */
    private boolean setSelectedMainMenu(String menuId, String subMenuId){
        if (menuId == null) {
            return false;
        }
        boolean selectMenu = setSelectMemnuFlg(pullDownMenuDto.getMenu1st(), menuId, subMenuId);
        if (!selectMenu) {
            selectMenu = setSelectMemnuFlg(pullDownMenuDto.getMenu2nd(), menuId, subMenuId);
        }
        if (!selectMenu) {
            selectMenu = setSelectMemnuFlg(pullDownMenuDto.getRegistMenu1st(), menuId, subMenuId);
        }
        if (!selectMenu) {
            selectMenu = setSelectMemnuFlg(pullDownMenuDto.getRegistMenu2nd(), menuId, subMenuId);
        }
        return selectMenu;
    }
    private boolean setSelectMemnuFlg(List menu, String menuId, String subMenuId) {
        boolean selected = false;
        ofMenu:for (Iterator ite = menu.iterator(); ite.hasNext();) {
            CtlBirdMenu ctlBirdMenuMain = (CtlBirdMenu) ite.next();
            List submenu= ctlBirdMenuMain.getSubMenuList();
            for(Iterator ite2 = submenu.iterator(); ite2.hasNext();) {
                CtlBirdMenu eSubMenu = ((CtlBirdMenu) ite2.next());
                if(menuId.equals(eSubMenu.getMenuId())){
                    if (subMenuId != null) {
                        if (subMenuId.equals(eSubMenu.getSubMenuId())) {
                            ctlBirdMenuMain.setSelectFlg(true);
                            eSubMenu.setSelectFlg(true);
                            selected = true;
                            break ofMenu;
                        }
                    }
                    else {
                        ctlBirdMenuMain.setSelectFlg(true);
                        selected = true;
                        break ofMenu;
                    }
                }
            }
        }
        return selected;
    }
    
    /**
     * ViewID、MenuIDからメニュー情報を取得する
     * @param viewId
     * @return
     */
    public CtlBirdMenu getMenuInfoByViewId(String viewId) {
        //ViewIDとMenuIDでメニュー情報を探す
        CtlBirdMenu ctlBirdMenu = null;
        ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getMenu1st(), viewId, getInitMenuId());
        if (ctlBirdMenu == null) {
            ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getMenu2nd(), viewId, getInitMenuId());
        }
        if (ctlBirdMenu == null) {
            ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getRegistMenu1st(), viewId, getInitMenuId());
        }
        if (ctlBirdMenu == null) {
            ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getRegistMenu2nd(), viewId, getInitMenuId());
        }
        
        if (ctlBirdMenu == null && getInitMenuId() != null) {
            ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getMenu1st(), viewId, null);
            if (ctlBirdMenu == null) {
                ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getMenu2nd(), viewId, null);
            }
            if (ctlBirdMenu == null) {
                ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getRegistMenu1st(), viewId, null);
            }
            if (ctlBirdMenu == null) {
                ctlBirdMenu = searchMenuInfoByViewId(pullDownMenuDto.getRegistMenu2nd(), viewId, null);
            }
        }
        
        return ctlBirdMenu;
    }
    private CtlBirdMenu searchMenuInfoByViewId(List menu, String selectViewId, String menuId) {
        ofMenu:for (Iterator ite = menu.iterator(); ite.hasNext();) {
            List submenu= ((CtlBirdMenu) ite.next()).getSubMenuList();
            for(Iterator ite2 = submenu.iterator(); ite2.hasNext();) {
                CtlBirdMenu eSubMenu = ((CtlBirdMenu) ite2.next());
                String viewId = eSubMenu.getViewId();
                if(selectViewId.equals(viewId)){
                    if (menuId != null) {
                        if (menuId.equals(eSubMenu.getMenuId())) {
                            return eSubMenu;
                        }
                    }
                    else {
                        return eSubMenu;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * メインメニュー選択フラグクリア
     */
    private void clearSelectMenuFlg() {
        doClearList(getPullDownMenuDto().getMenu1st());
        doClearList(getPullDownMenuDto().getMenu2nd());
        doClearList(getPullDownMenuDto().getRegistMenu1st());
        doClearList(getPullDownMenuDto().getRegistMenu2nd());
        
    }
    
    
    /**
     * 指定ViewIDのみでメニューを選択する
     *  ※個店ポータル、オーナー照会用
     * @param String ViewID
     */
    public void doSelectMainMenu(String viewID) {
        String selectViewId = viewID;
        if (selectViewId != null && selectViewId.length() > 6) {
            selectViewId = selectViewId.substring(0, 6);
        }
        boolean isSelect = doSelectMainMenuFromViewId(getPullDownMenuDto().getMenu1st(), selectViewId);
        if (!isSelect) {
            isSelect = doSelectMainMenuFromViewId(getPullDownMenuDto().getMenu2nd(), selectViewId);
        }
        if (!isSelect) {
            isSelect = doSelectMainMenuFromViewId(getPullDownMenuDto().getRegistMenu1st(), selectViewId);
        }
        if (!isSelect) {
            isSelect = doSelectMainMenuFromViewId(getPullDownMenuDto().getRegistMenu2nd(), selectViewId);
        }
    }
    /**
     * 注意！：doSelectMainMenu(String)専用のメソッド
     * @param menu
     * @param selectViewId
     * @return
     */
    private boolean doSelectMainMenuFromViewId(List menu, String selectViewId) {
        ofMenu:for (Iterator ite = menu.iterator(); ite.hasNext();) {
            CtlBirdMenu eMainMenu = (CtlBirdMenu) ite.next();
            List submenu= eMainMenu.getSubMenuList();
            for(Iterator ite2 = submenu.iterator(); ite2.hasNext();) {
                CtlBirdMenu eSubMenu = ((CtlBirdMenu) ite2.next());
                String viewId = eSubMenu.getViewId();
                if(selectViewId.equals(viewId)){
                    clearSelectMenuFlg();
                    eMainMenu.setSelectFlg(true);
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * 現在選択されているメニューIDを取得する
     * @return
     */
    private String getSelectedMenuId() {
        String menuId = null;
        CtlBirdMenu ctlBirdMenu;
        ctlBirdMenu = getSelectedMenuInfo(getPullDownMenuDto().getMenu1st());
        if (ctlBirdMenu == null) {
            ctlBirdMenu = getSelectedMenuInfo(getPullDownMenuDto().getMenu2nd());
        }
        if (ctlBirdMenu == null) {
            ctlBirdMenu = getSelectedMenuInfo(getPullDownMenuDto().getRegistMenu1st());
        }
        if (ctlBirdMenu == null) {
            ctlBirdMenu = getSelectedMenuInfo(getPullDownMenuDto().getRegistMenu2nd());
        }
        
        if (ctlBirdMenu != null) { 
            if ("00".equals(ctlBirdMenu.getMenuId())) {
                menuId = ctlBirdMenu.getSubMenuId();
            }
            else {
                menuId = ctlBirdMenu.getMenuId();
            }
        }
        return menuId;
    }
    /**
     * 指定List内の現在選択メニューIDを取得
     * ※選択されているメニューがない場合は、Nullを返す
     * @param menu 選択されているメニューのメニューID
     * @return
     */
    private CtlBirdMenu getSelectedMenuInfo(List menu) {
        for (Iterator ite = menu.iterator(); ite.hasNext();) {
            CtlBirdMenu ctlBirdMenu = (CtlBirdMenu) ite.next();
            if (ctlBirdMenu.isSelectFlg()) {
                return ctlBirdMenu;
            }
        }
        return null;
    }
    
    private void doClearList(List menu) {
        //フラグクリア
        for(int i = 0; i < menu.size(); i++){
            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
            ((CtlBirdMenu)menu.get(i)).setSelectFlg(false);                            
            for(int j = 0; j < submenu.size(); j++){
                ((CtlBirdMenu)submenu.get(j)).setSelectFlg(false);
            }
        }
    }
    
    /**
     * 選択されてたメインメニューのフラグをＯＮに設定
     * @param menu メインメニュー
     * @param selectViewId 選択されたメニュー
     * @param menuId
     */
    private boolean setSelectMemnuFlg(List menu, String selectViewId, String menuId, String subMenuId){
        if ("undefined".equals(menuId)) {
            menuId = null;
        }
        if ("undefined".equals(subMenuId)) {
            subMenuId = null;
        }

        if (selectViewId == null || menuId == null) {
            return false;
        }
        
        boolean selectMenu = false;
        
//        //フラグクリア
//        for(int i = 0; i < menu.size(); i++){
//            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
//            ((CtlBirdMenu)menu.get(i)).setSelectFlg(false);                            
//            for(int j = 0; j < submenu.size(); j++){
//                ((CtlBirdMenu)submenu.get(j)).setSelectFlg(false);
//            }
//        }
//        
        //選択されてたメインメニューのフラグをONに設定
        ofMenu:for(int i = 0; i < menu.size(); i++){
            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
            for(int j = 0; j < submenu.size(); j++){
            	CtlBirdMenu eSubMenu = ((CtlBirdMenu)submenu.get(j));
                String viewId = eSubMenu.getViewId();
                if(selectViewId.equals(viewId)){
                    if (subMenuId == null) {
                        ((CtlBirdMenu)menu.get(i)).setSelectFlg(true);
                        eSubMenu.setSelectFlg(true);
                        setInitViewId(eSubMenu.getInitViewId());
                        selectMenu = true;
                        break ofMenu;
                    }
                    else {
                        if (menuId != null && !"".equals(menuId) && menuId.equals(eSubMenu.getMenuId())) {
                            if (subMenuId != null && !"".equals(subMenuId) && subMenuId.equals(eSubMenu.getSubMenuId())) {
                                ((CtlBirdMenu)menu.get(i)).setSelectFlg(true);
                                eSubMenu.setSelectFlg(true);
                                setInitViewId(eSubMenu.getInitViewId());
                                selectMenu = true;
                                break ofMenu;
                            }
                        }
                    }
                }
            }
        }
        
        return selectMenu;
    }
    
    /**
     * バンズ自動設定数量照会/変更画面にVIEW_IDを設定する
     * ・画面編集できるの場合、showViewIdに「BBS026V02」を設定する
     * ・画面確認・照会できるだけの場合、showViewIdに「BBS026V03」を設定する
     */
    private void setBunsAutoShowViewId(){
    	//コンテナ取得
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        //DAOクラス取得
    	CtlGamenRoleDao gamenRoleMenuDao = (CtlGamenRoleDao)s2Con.getComponent(CtlGamenRoleDao.class);
    	//ユーザー取得
    	MstUser mstUser = getBirdUserInfo().getMstUser();
    	//画面ロールを取得
		List gamenRoles = gamenRoleMenuDao.getGamenRole(mstUser.getUser_id(), "BBS026", "02");
		//画面ロールは存在しない場合、照会画面にshowViewIdに「BBS026V03」を設定する
		if (gamenRoles == null || gamenRoles.size() <= 0) {
			setShowViewId("BBS026V03");
		}else{
			//画面ロールは存在している場合、照会画面にshowViewIdに「BBS026V02」を設定する
			setShowViewId("BBS026V02");
		}
    }
    
	/**
	 * @return initViewId を戻します。
	 */
	public String getInitViewId() {
		return initViewId;
	}


	/**
	 * @param initViewId を クラス変数initViewIdへ設定します。
	 */
	public void setInitViewId(String initViewId) {
		this.initViewId = initViewId;
	}


    public String getInitMenuId() {
        return initMenuId;
    }


    public void setInitMenuId(String initMenuId) {
        this.initMenuId = initMenuId;
    }


    public String getInitSubMenuId() {
        return initSubMenuId;
    }

    public void setInitSubMenuId(String initSubMenuId) {
        this.initSubMenuId = initSubMenuId;
    }
    public RegOutputLogLogic getRegOutputLogLogic() {
        return regOutputLogLogic;
    }
    public void setRegOutputLogLogic(RegOutputLogLogic regOutputLogLogic) {
        this.regOutputLogLogic = regOutputLogLogic;
    }
    public String getRegistMenuIdThresholdAmount() {
        return registMenuIdThresholdAmount;
    }
    public void setRegistMenuIdThresholdAmount(String registMenuIdThresholdAmount) {
        this.registMenuIdThresholdAmount = registMenuIdThresholdAmount;
    }
}