/*
 * 作成日: 2006/02/06
 *
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.framework.dao.CtlUserMenuDao;
import jp.co.isid.mos.bird.framework.entity.CtlUserMenu;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.logic.AccessCtlCheckLogic;

/**
 * メニュー権限チェック
 * @author xytamura
 */
public class AccessCtlCheckLogicImpl implements AccessCtlCheckLogic {

    /**
     * ユーザメニューDao
     */
    private CtlUserMenuDao ctlUserMenuDao;
    
    /**
     * 共通検索フォーム
     */
    private static final String COMMON_SELECT_FORM = "BCO";

    /**
     * ポータル
     */
    private static final String PORTAL = "BPO";

    /** 個別設定フラグ：使用可(1) */
    private static final String CUSTOMIZE_FLG_KA   = "1";
    /** 個別設定フラグ：使用不可(9) */
    private static final String CUSTOMIZE_FLG_FUKA = "9";
    /** 上限拡張フラグ：拡張設定可 */
    private static final String EXTRA_KA       = "1";
    /** 使用可能フラグ：使用可 */
    private static final String ENABLE_KA      = "1";

    /** アクセス権限チェック対象外画面リスト */
    private static final String CHK_OFF_SCR_LIST = "chkOffScreenList";

    /**
     * メニュー権限チェック
     * @param userId ユーザＩＤ
     * @param pgmId  機能ＩＤ
     */
    public List execute(final String userId, final String pgmId){

        //返却用リスト
        List retList = new ArrayList();

        //コンテナ取得
        S2Container s2Con = SingletonS2ContainerFactory.getContainer();
        //アクセス権限チェック対象外画面リスト取得
        List chkOffScreenList = (List) s2Con.getComponent(CHK_OFF_SCR_LIST);
        if (chkOffScreenList != null && !chkOffScreenList.isEmpty()) {
            for (int i = 0; i < chkOffScreenList.size(); i++) {
                // 機能ＩＤがアクセス権限チェック対象外画面の場合、処理中断
                if (pgmId.equals(chkOffScreenList.get(i))) {
                    return retList;
                }
            }
        }

        //上限拡張可能メニューリスト取得
        if(pgmId.indexOf(COMMON_SELECT_FORM) < 0 && pgmId.indexOf(PORTAL) < 0){

            //リスト取得
            List extraMenu = getCtlUserMenuDao().getExtraMenu(userId, pgmId);

            //メニューがない場合
            if(extraMenu == null || extraMenu.size() == 0){
                throw new CannotAccessException();
            }

            //アクセス可能なメニューのみにする
            retList = setAccessMenu(extraMenu);

            //アクセス可能なメニューが存在しない時
            if(retList == null || retList.size() == 0) {
                throw new CannotAccessException();
            }
        }

        //検索フォームとポータル以外
//        if(pgmId.indexOf(COMMON_SELECT_FORM) < 0 && pgmId.indexOf(PORTAL) < 0){
//            //アクセス可能メニューの取得
//            enableMenu = getCtlUserMenuDao().getEnableMenu(userId, pgmId);
//            //アクセスできるメニューがない場合
//            if(enableMenu.size() == 0){
//                throw new CannotAccessException();
//            }
//        }
//        if(pgmId.indexOf(COMMON_SELECT_FORM) < 0 && pgmId.indexOf(PORTAL) < 0){
//            //アクセス不可能メニューの取得
//            List disableMenu = getCtlUserMenuDao().getDisableMenu(userId, pgmId);
//            //アクセス不可能メニューの場合
//            if(disableMenu.size() > 0){
//                throw new CannotAccessException();
//            }
//        }
        
        return retList;
    }

    /**
     * アクセス可能なメニューの有無を判定する。
     * @param userId ユーザＩＤ
     * @param pgmId  機能ＩＤ
     * @return アクセス可能メニュー有無(true:有、false:無)
     */
    public boolean isAccessMenu(final String userId, final String pgmId){
        boolean flg = true;

        try {
            // メニュー権限チェック
            execute(userId, pgmId);
        } catch (CannotAccessException ce) {
            // アクセス可能なメニューが存在しない時は「false」
            flg = false;
        }

        return flg;
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
            CtlUserMenu entity = (CtlUserMenu) menu.get(i);
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
                    
                    CtlUserMenu entity2 = (CtlUserMenu)tmpMap.get(key);
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
            CtlUserMenu entity = (CtlUserMenu)tmpMap.get(key);
            tmpList.add(entity);
        }
        //セットしなおす
        menu = tmpList;
        
        //-------------------------------------------------------
        // <2> ユーザ別設定を反映する
        //--------------------------------------------------------
        List retList = new ArrayList();
        
        for(int i=0; i < menu.size(); i++) {
            
            CtlUserMenu entity = (CtlUserMenu)menu.get(i);
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
     * ユーザメニューDaoを取得します。
     * @return ユーザメニューDao
     */
    public CtlUserMenuDao getCtlUserMenuDao() {
        return ctlUserMenuDao;
    }
    
    /**
     * ユーザメニューDaoを設定します。
     * @param ctlUserMenuDao ユーザメニューDao
     */
    public void setCtlUserMenuDao(CtlUserMenuDao ctlUserMenuDao) {
        this.ctlUserMenuDao = ctlUserMenuDao;
    }

}
