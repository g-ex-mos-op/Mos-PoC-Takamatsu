/*
 * 作成日: 2006/02/27
 *
 */
package jp.co.isid.mos.bird.sysadmin.help.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.sysadmin.help.dao.HelpVersionDao;
import jp.co.isid.mos.bird.sysadmin.help.dao.UIBirdMenuDao;
import jp.co.isid.mos.bird.sysadmin.help.dto.UserMenuDto;
import jp.co.isid.mos.bird.sysadmin.help.entity.HelpVersion;
import jp.co.isid.mos.bird.sysadmin.help.entity.UIBirdMenu;
import jp.co.isid.mos.bird.sysadmin.help.logic.SearchMenuLogic;

/**
 * 更新：2006/12/07　ユーザロールデフォルト設定対応にて
 * 更新：2012/03/28 xkinu IEキャッシュ対応
 *       ヘルプファイル更新履歴追加。
 * 　　　最新のヘルプファイルの読込ためのパラメータとして使用されます。
 * @author xylee
 */
public class SearchMenuLogicImpl implements SearchMenuLogic {

	/**
	 * メニュー情報DAO
	 */
	private UIBirdMenuDao uIBirdMenuDao;
	/**
	 * ヘルプファイル更新履歴DAO
	 */
	private HelpVersionDao helpVersionDao;
	
	/**
     * メニュー情報を取得します。
     * 
     * @return メニュー情報DAO
     */
	public UIBirdMenuDao getUIBirdMenuDao() {
		return uIBirdMenuDao;
	}

    /**
     * メニュー情報DAOを設定します。
     * 
     * @param uIBirdMenuDao メニュー情報DAO
     */
	public void setUIBirdMenuDao(UIBirdMenuDao uIBirdMenuDao) {
		this.uIBirdMenuDao = uIBirdMenuDao;
	}

    /** 個別設定フラグ：使用可(1) */
    private static final String CUSTOMIZE_FLG_KA   = "1";
    /** 個別設定フラグ：使用不可(9) */
    private static final String CUSTOMIZE_FLG_FUKA = "9";
    /** 上限拡張フラグ：拡張設定可 */
    private static final String EXTRA_KA       = "1";
    /** 使用可能フラグ：使用可 */
    private static final String ENABLE_KA      = "1";

	/**
	 * メニュー情報リスト取得
	 */
	public void execute(String userId, UserMenuDto userMenuDto, String viewId) {

        List retMenu = null;
        
        //上限拡張可能メインメニュー取得
        retMenu = getUIBirdMenuDao().getExtraMenu(userId);
        
        //アクセス可能なメニューのみにする
        retMenu = setAccessMenu(retMenu);

        setHelpMenu(userMenuDto, viewId, retMenu);
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
            UIBirdMenu entity = (UIBirdMenu) menu.get(i);
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
                    
                    UIBirdMenu entity2 = (UIBirdMenu)tmpMap.get(key);
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
            UIBirdMenu entity = (UIBirdMenu)tmpMap.get(key);
            tmpList.add(entity);
        }
        //セットしなおす
        menu = tmpList;
        
        //-------------------------------------------------------
        // <2> ユーザ別設定を反映する
        //-------------------------------------------------------
        List retList = new ArrayList();
        
        for(int i=0; i < menu.size(); i++) {
            
            UIBirdMenu entity = (UIBirdMenu)menu.get(i);
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
     * 
     * @param userMenuDto
     * @param showViewId
     * @param helpList
     */
    private void setHelpMenu(UserMenuDto userMenuDto, String showViewId, List helpList) {
		String checkMenuId = "";
        
		List listHelpMenu = new ArrayList();
		List UserHelpMenu = new ArrayList();
        boolean isDoneSetMenu = false;
		String filePass = BirdContext.getProperty("fileProperty", "filePathHelp");
		userMenuDto.setFilePass(filePass);
		
		for (Iterator it = helpList.iterator(); it.hasNext();) {
			UIBirdMenu uIBirdMenu = (UIBirdMenu) it.next();
			String url = filePass + File.separator + uIBirdMenu.getViewId() + ".pdf" ;
			File fileCheck = new File(url);
			if (fileCheck.exists()) {
				uIBirdMenu.setFileCheck(true);
			}else{
				uIBirdMenu.setFileCheck(false);
			}
			UserHelpMenu.add(uIBirdMenu);
			if (!checkMenuId.equals(uIBirdMenu.getMenuId())) {
				listHelpMenu.add(uIBirdMenu);
				checkMenuId = uIBirdMenu.getMenuId();
			}
			if (showViewId.equals(uIBirdMenu.getViewId()) && !isDoneSetMenu) {
				userMenuDto.setMenuId(uIBirdMenu.getMenuId());
                isDoneSetMenu = true;
			}
		}
		String url2 = filePass + File.separator + showViewId + ".pdf" ;
		if (new File(url2).exists()) {
			userMenuDto.setFileCheck(true);
		}else{
			userMenuDto.setFileCheck(false);
		}
		userMenuDto.setUserHelpList(UserHelpMenu);
		userMenuDto.setViewId(showViewId);
		userMenuDto.setUserHelpMenuList(listHelpMenu);
		//ヘルプ更新履歴を設定します(追加:2012/03/28)
		HelpVersion version = getHelpVersionDao().select();
		userMenuDto.setHelpVersion(version.getVersion());
    }

	/**
	 * @return クラス変数helpVersionDao を戻します。
	 */
	public HelpVersionDao getHelpVersionDao() {
		return helpVersionDao;
	}

	/**
	 * @param helpVersionDao を クラス変数helpVersionDaoへ設定します。
	 */
	public void setHelpVersionDao(HelpVersionDao helpVersionDao) {
		this.helpVersionDao = helpVersionDao;
	}
}
