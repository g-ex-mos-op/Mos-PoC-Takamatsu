package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import jp.co.isid.mos.bird.framework.dao.CtlBirdMenuDao;
import jp.co.isid.mos.bird.framework.dao.CtlGamenRoleDao;
import jp.co.isid.mos.bird.framework.dao.CtlUserRoleDao;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;
import jp.co.isid.mos.bird.framework.entity.MstUser;

/**
 * ユーザがのアクセスできるメニューを取得します
 * @author xytamura
 */
public class GetAccessMenuLogic {

    /**
     * メニューを取得
     */
    private static final Class CLASS_BIRD_MENU_DAO = CtlBirdMenuDao.class;
    
    /**
     * 汎用画面別ロール取得Dao
     */
    private static final Class CLASS_GAMEN_ROLE_DAO = CtlGamenRoleDao.class;
    
    /**
     * ユーザロール
     */
    private static final Class CLASS_USER_ROLE_DAO = CtlUserRoleDao.class;
    
    private static final int MENU_MAX = 12;

    private static final int SEPARATE_CNT = MENU_MAX / 2;

    /**
     * １段目のメニュー取得キー
     */
    private static final String MENU_FIRST = "menu1st";
    
    /**
     * ２段目のメニュー取得キー
     */
    private static final String MENU_SECOND = "menu2nd";
    
    /**
     * 登録系：１段目のメニュー取得キー
     */
    private static final String REGIST_MENU_FIRST = "registMenu1st";
    
    /**
     * 登録系：２段目のメニュー取得キー
     */
    private static final String REGIST_MENU_SECOND = "registMenu2nd";
    
    /**
     * ヘルプのメニュー取得キー
     */
    private static final String MENU_HELP = "help";

    /**
     * メニュー表示フラグ
     */
    private static final String MENU_DISP_FLG = "MENU_DISP_FLG";
    
    /** メニュー表示区分： */
    //非表示(1)
//    private static final String DISP_KBUN_NO_DISP  = "0";
    //表示(1)
    private static final String DISP_KBUN_DISP     = "1";
    //外部システム（上部メニュー表示）
    private static final String DISP_KBUN_GAIBU_MENU_DISP = "2";
    /** 個別設定フラグ：使用可(1) */
    private static final String CUSTOMIZE_FLG_KA   = "1";
    /** 個別設定フラグ：使用不可(9) */
    private static final String CUSTOMIZE_FLG_FUKA = "9";
    /** 上限拡張フラグ：拡張設定可 */
    private static final String EXTRA_KA       = "1";
    /** 使用可能フラグ：使用可 */
    private static final String ENABLE_KA      = "1";
    
    /**
     * 登録系、照会系 判断メニューID
     * 下記の値以上のものが登録系とする
     */
    private String registMenuIdThresholdAmount;

    /**
     * メニューの取得ロジックを実行します。
     * @param mstUser ユーザ情報
     * @return メニュー
     */
    public Map execute(final MstUser mstUser) {

        //使用可能なメニューのリストを取得
        //List enableMenu = getEnableMenu(mstUser);

        //使用可能でかつメニュー表示可能なメニューのリストを取得
        Map menuMap = getEnableDispMenu(mstUser);
        List enableMenu = (List) menuMap.get("MAIN_MENU");

        //
        Map allmenu = separateMenu(enableMenu);

        //ヘルプメニュー
        allmenu = createHelpMenu(allmenu);
        //メニュー表示フラグを戻りMapにセット
        allmenu.put(MENU_DISP_FLG, menuMap.get(MENU_DISP_FLG));
        
        return allmenu;

    }

    /**
     * アクセス可能なメニューを取得します。
     * @param mstUser ユーザ情報
     * @return アクセス可能なメニュー
     */
    private Map getEnableDispMenu(final MstUser mstUser) {
        Map retMap = new HashMap();
        boolean menuDispFlg = true;
        
        //コンテナ取得
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();

        //DAOクラス取得
        CtlBirdMenuDao birdMenuDao       = (CtlBirdMenuDao)s2Con.getComponent(CLASS_BIRD_MENU_DAO);
        CtlGamenRoleDao gamenRoleMenuDao = (CtlGamenRoleDao)s2Con.getComponent(CLASS_GAMEN_ROLE_DAO);
        CtlUserRoleDao ctlUserRoleDao    = (CtlUserRoleDao)s2Con.getComponent(CLASS_USER_ROLE_DAO);

        
        List mainMenu = null;
        
        //メニュー非表示ロールを取得
        List listMenuNG = gamenRoleMenuDao.getMenuHiHyoziRole(mstUser.getUser_id());

        //ユーザロールを取得
        List listUserRole = ctlUserRoleDao.getUserRoleList(mstUser.getUser_id());

        //メニュー非表示ロールにのみ所属していたら
        if(listMenuNG.size() == listUserRole.size()){
            mainMenu = new ArrayList();
            menuDispFlg = false;
        }else{
            
            //上限拡張可能メインメニュー取得
            mainMenu = birdMenuDao.getExtraMainMenu(mstUser.getUser_id());
            
            //アクセス可能なメニューのみにする
            mainMenu = setAccessMenu(mainMenu);

            //表示可能なメニューのみにする
            mainMenu = setDispMenu(mainMenu);
        }
        
        for (int i = 0; i < mainMenu.size(); i++) {
            CtlBirdMenu bridMenuMain = (CtlBirdMenu) mainMenu.get(i);
            String subMenuId = bridMenuMain.getSubMenuId();

            //上限拡張可能サブメニュー取得
            List subMenu = birdMenuDao.getExtraSubMenu(mstUser.getUser_id(), subMenuId);
            
            //アクセス可能なメニューのみにする
            subMenu = setAccessMenu(subMenu);

            //表示可能なメニューのみにする
            subMenu = setDispMenu(subMenu);

            //サブメニューの個別アクセス設定を適用
            bridMenuMain.setSubMenuList(subMenu);
        }
        
        retMap.put("MAIN_MENU", mainMenu);
        retMap.put(MENU_DISP_FLG, Boolean.valueOf(menuDispFlg));
        
        return retMap;
    }


    /**
     * 登録系、照会系メニューにわけます
     * @param mainMenu    メニュー
     * @param referenceMenu
     * @param registMenu
     * @return 
     */
    private void separateRegistMenu(List mainMenu, List referenceMenu, List registMenu) {
        if (mainMenu == null || mainMenu.size() == 0) {
            return;
        }

        for(int i=0; i < mainMenu.size(); i++) {
            
            CtlBirdMenu birdMenuMain = (CtlBirdMenu) mainMenu.get(i);
            
            if (birdMenuMain.getSubMenuId().compareTo(getRegistMenuIdThresholdAmount()) >= 0) {
                //登録系
                registMenu.add(birdMenuMain);
            }
            else {
                //照会系
                referenceMenu.add(birdMenuMain);
            }
        }
    }

    
    /**
     * メニュー表示可能なメニューのみにします。
     * @param menu    メニュー
     * @return アクセス可能メニュー
     */
    private List setDispMenu(List menu) {
        if (menu == null || menu.size() == 0) {
            return menu;
        }

        List retList = new ArrayList();
        
        for(int i=0; i < menu.size(); i++) {
            
            CtlBirdMenu entity = (CtlBirdMenu)menu.get(i);
            String dispKbn   = entity.getMenuDispKbn();

            //2009/01/14 MENU_DISP_KBN = 1(表示) or 2(外部システム・メニュー表示)に変更
            if(dispKbn != null && (dispKbn.equals(DISP_KBUN_DISP) || dispKbn.equals(DISP_KBUN_GAIBU_MENU_DISP))) {
                //リストに追加
                retList.add(entity);
            }
        }
        return retList;
    }
    
    /**
     * アクセス可能メニューのみにします。
     * @param menu    メニュー
     * @return アクセス可能メニュー
     */
    private List setAccessMenu(List menu) {

        if (menu == null || menu.size() == 0) {
            return menu;
        }
        
        //-------------------------------------------------------
        // <1> [menu_id]と[sub_menu_id]をキーとして重複行をなくす
        //--------------------------------------------------------
        List keyList = new ArrayList();
        Map tmpMap   = new HashMap();
        for(int i=0; i < menu.size(); i++){
            CtlBirdMenu entity = (CtlBirdMenu) menu.get(i);
            String menuId     = entity.getMenuId();
            String subMenuId  = entity.getSubMenuId();
            String enableFlg  = entity.getEnableFlg();
            String extraFlg   = entity.getExtraFlg();
            String key = "";

            if(menuId != null && menuId.length() == 2 &&
                    subMenuId != null && subMenuId.length() == 2){
                
                //キー
                key = menuId + subMenuId;

                if(tmpMap.containsKey(key)){
                    
                    CtlBirdMenu entity2 = (CtlBirdMenu)tmpMap.get(key);
                    String enableFlg2  = entity2.getEnableFlg();
                    String extraFlg2   = entity2.getExtraFlg();

                    //上限フラグ判定・セット
                    if(extraFlg2.equals(EXTRA_KA)){
                        //なにもしない
                    }else if(extraFlg.equals(EXTRA_KA)){
                        entity2.setExtraFlg(EXTRA_KA);
                    }
                    
                    //初期フラグ判定・セット
                    if(entity2.getExtraFlg().equals(EXTRA_KA) && enableFlg2.equals(ENABLE_KA)){
                        //なにもしない
                    }else if(entity2.getExtraFlg().equals(EXTRA_KA) && enableFlg.equals(ENABLE_KA)){
                        entity2.setEnableFlg(ENABLE_KA);
                    }

                    //セットしなおす
                    tmpMap.put(key, entity2);
                }else{
                    tmpMap.put(key, entity);
                    keyList.add(key);
                }
            }
        }

        List tmpList = new ArrayList();
        for(int i=0; i < keyList.size(); i++){
            String key = (String)keyList.get(i);
            CtlBirdMenu entity = (CtlBirdMenu)tmpMap.get(key);
            tmpList.add(entity);
        }
        //セットしなおす
        menu = tmpList;

        
        //-------------------------------------------------------
        // <2> ユーザ別設定を反映する
        //-------------------------------------------------------
        List retList = new ArrayList();
        
        for(int i=0; i < menu.size(); i++) {
            
            CtlBirdMenu entity = (CtlBirdMenu)menu.get(i);
            String enableFlg   = entity.getEnableFlg();
            String custFlg     = entity.getCustomizeFlg();
            
            //ユーザ別アクセス制御で「使用不可」設定されている時
            if(custFlg != null && custFlg.equals(CUSTOMIZE_FLG_FUKA)){
                //リストに追加しない
            }
            //ユーザ別アクセス制御で「使用可能」設定されている時
            else if(custFlg != null && custFlg.equals(CUSTOMIZE_FLG_KA)){
                retList.add(entity);
            }
            //初期使用可で、ユーザ別アクセス制御が設定されていない時
            else if(enableFlg != null && enableFlg.equals(ENABLE_KA) &&
                    (custFlg == null || !custFlg.equals(CUSTOMIZE_FLG_FUKA))
                    ){
                retList.add(entity);
            }
        }
        return retList;
    }
    

    /**
     * ダミーメニューセット処理
     * @param mainMenu メニュー
     * @return メニュー
     */
    private List setDummyMenu(List mainMenu) {
        int maxCnt = MENU_MAX - mainMenu.size();
        for (int i = 0; i < maxCnt; i++) {
            CtlBirdMenu ctlBirdMenu = new CtlBirdMenu();
            ctlBirdMenu.setSubMenuList(new ArrayList(0));
            mainMenu.add(ctlBirdMenu);
        }
        return mainMenu;
    }

    /**
     * メニューを１段目と２段目に分割します。
     * @param mainMenu メニュー
     * @return １段目と２段目に分割されたメニュー
     */
    private Map separateMenu(List mainMenu) {
        Map allMenu = new HashMap();

        //登録系List
        List listRegist = new ArrayList();
        List listReference = new ArrayList();
        //照会系、登録系にわける
        separateRegistMenu(mainMenu, listReference, listRegist);
        
        //それぞれのメニュー数が１２になるようダミーメニューをセット
        listReference = setDummyMenu(listReference);
        listRegist = setDummyMenu(listRegist);
        
        //照会系メニューを２段に分割
        List temp1 = listReference.subList(0, SEPARATE_CNT);
        List temp2 = listReference.subList(SEPARATE_CNT, listReference.size());
        List menu1st = new ArrayList();
        List menu2nd = new ArrayList();

        for (int i = 0; i < temp1.size(); i++) {
            menu1st.add(temp1.get(i));
        }
        for (int i = 0; i < temp2.size(); i++) {
            menu2nd.add(temp2.get(i));
        }

        allMenu.put(MENU_FIRST, menu1st);
        allMenu.put(MENU_SECOND, menu2nd);

        //登録系メニューを２段に分割
        temp1 = listRegist.subList(0, SEPARATE_CNT);
        temp2 = listRegist.subList(SEPARATE_CNT, listRegist.size());
        List registMenu1st = new ArrayList();
        List registMenu2nd = new ArrayList();

        for (int i = 0; i < temp1.size(); i++) {
            registMenu1st.add(temp1.get(i));
        }
        for (int i = 0; i < temp2.size(); i++) {
            registMenu2nd.add(temp2.get(i));
        }

        allMenu.put(REGIST_MENU_FIRST, registMenu1st);
        allMenu.put(REGIST_MENU_SECOND, registMenu2nd);
        
        return allMenu;

    }
    /**
     *  ヘルプを作成します。
     * @param menu
     * @param mstUser
     * @return
     */
    private Map createHelpMenu(Map menu){
        CtlBirdMenu ctlBirdMenu = new CtlBirdMenu();
        ctlBirdMenu.setViewId("BSA009");        
        ctlBirdMenu.setInitViewId("BSA009V01");        
        ctlBirdMenu.setMenuId("99");
        ctlBirdMenu.setMenuName("ヘルプ");
        ctlBirdMenu.setSubMenuId("01");
        ctlBirdMenu.setSubMenuName("ヘルプ");
        
        menu.put(MENU_HELP, ctlBirdMenu);
        return menu;
    }

    public String getRegistMenuIdThresholdAmount() {
        return registMenuIdThresholdAmount;
    }

    public void setRegistMenuIdThresholdAmount(String registMenuIdThresholdAmount) {
        this.registMenuIdThresholdAmount = registMenuIdThresholdAmount;
    }

}