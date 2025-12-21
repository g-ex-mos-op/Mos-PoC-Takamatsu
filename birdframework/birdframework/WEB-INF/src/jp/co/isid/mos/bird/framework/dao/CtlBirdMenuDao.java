/*
 * 作成日: 2005/11/21
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;


/**
 * メニューを取得します
 * @author xytamura
 */
public interface CtlBirdMenuDao {

    public Class BEAN = CtlBirdMenu.class;

    public static final String getMainMenu_ARGS = "userId";
    public static final String getMainMenu_SQL =            
         " SELECT DISTINCT "
    +    "        BR02.MENU_ID "
    +    " ,      BR02.MENU_NAME "
    +    " ,      BR02.SUB_MENU_ID " 
    +    " ,      BR02.SUB_MENU_NAME "
    +    " ,      BR02.VIEW_ID "
    +    " ,      BR02.INIT_VIEW_ID "
    +    " ,      BR02.SORT_SEQ "
    +    " FROM   BR05ACTR BR05 "
    +    " ,      BR02BMNU BR02 "
    +    " ,      BR04USRL BR04 "
    +    " WHERE  BR05.MENU_ID     = BR02.MENU_ID " 
    +    " AND    BR05.SUB_MENU_ID = BR02.SUB_MENU_ID " 
    +    " AND    BR04.ROLE_CD   = BR05.ROLE_CD "
    +    " AND    BR05.ENABLE_FLG  = '1' "
    +    " AND BR02.MENU_ID  = '00' "
    +    " AND BR04.USER_ID = /*userId*/ "
    +    " ORDER BY BR02.SORT_SEQ";

    
    public static final String getSubMenu_ARGS = "userId, menuId";
    public static final String getSubMenu_SQL =            
         " SELECT  DISTINCT "
    +    "        BR02.MENU_ID "
    +    " ,      BR02.MENU_NAME "
    +    " ,      BR02.SUB_MENU_ID " 
    +    " ,      BR02.SUB_MENU_NAME " 
    +    " ,      BR02.VIEW_ID "  
    +    " ,      BR02.INIT_VIEW_ID "    
    +    " ,      BR02.SORT_SEQ "
    +    " FROM   BR05ACTR BR05 "
    +    " ,      BR02BMNU BR02 "
    +    " ,      BR04USRL BR04 "
    +    " WHERE  BR05.MENU_ID     = BR02.MENU_ID " 
    +    " AND    BR05.SUB_MENU_ID = BR02.SUB_MENU_ID " 
    +    " AND    BR04.ROLE_CD   = BR05.ROLE_CD "
    +    " AND    BR05.ENABLE_FLG  = '1' "
    +    " AND BR02.MENU_ID  != '00' "
    +    " AND BR05.MENU_ID = /*menuId*/"    
    +    " AND BR04.USER_ID = /*userId*/ "
    +    " ORDER BY BR02.SORT_SEQ";

//    public static final String getHelpMenu_SQL =            
//         " SELECT  DISTINCT "
//    +    "        BR02.MENU_ID "
//    +    " ,      BR02.MENU_NAME "
//    +    " ,      BR02.SUB_MENU_ID " 
//    +    " ,      BR02.SUB_MENU_NAME " 
//    +    " ,      BR02.VIEW_ID "  
//    +    " ,      BR02.INIT_VIEW_ID "    
//    +    " FROM   BR02BMNU BR02 "
//    +    " WHERE "
//    +    " BR02.MENU_ID = '99'"
//    +    " AND BR02.SUB_MENU_ID = '01'";
    
    
    
    /**
     * アクセスできるメインメニューを取得する
     * @param user_id ユーザＩＤ
     * @return メニュー
     */   
    public List getMainMenu(String userId);

    /**
     * アクセスできサブるメニューを取得する
     * @param user_id ユーザＩＤ
     * @param menuId メニューＩＤ
     * @return メニュー
     */   
    public List getSubMenu(String userId, String menuId);

//    /**
//     * ヘルプメニューを取得する
//     */
//    public CtlBirdMenu getHelpMenu();
       

//以下、ユーザロールデフォルト設定対応(2006/12/06)
    /**
     * 上限拡張可能なメインメニューを取得する
     * @param user_id ユーザＩＤ
     * @return メニュー
     */   
    public List getExtraMainMenu(String userId);
    
    public static final String getExtraMainMenu_ARGS = "userId";

    /**
     * 上限拡張可能なサブメニューを取得する
     * @param user_id ユーザＩＤ
     * @return メニュー
     */   
    public List getExtraSubMenu(String userId, String subMenuId);
    
    public static final String getExtraSubMenu_ARGS = "userId, subMenuId";

    
}
