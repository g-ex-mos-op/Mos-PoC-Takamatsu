/*
 * 作成日: 2005/11/21
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlDisableMenu;


/**
 * 個別アクセス制限情報を取得する
 * @author xytamura
 */
public interface CtlDisableMenuDao {

    public Class BEAN = CtlDisableMenu.class;

    public static final String getDisableMenu_ARGS = "userId, menuId";
    public static final String getDisableMenu_SQL =            
         " SELECT  "
    +    "        BR06.MENU_ID "
    +    " ,      BR06.SUB_MENU_ID " 
    +    " FROM   BR06USAC BR06 "
    +    " WHERE  " 
    +    "     BR06.DISABLE_FLG  = '1' "
    +    " AND BR06.USER_ID = /*userId*/ "
    +    " AND BR06.MENU_ID = /*menuId*/ "
    +    " ORDER BY  BR06.MENU_ID, BR06.SUB_MENU_ID";

    
    /**
     * 個別アクセス制限情報を取得する
     * @param user_id ユーザＩＤ
     * @return メニュー
     */   
    public List getDisableMenu(String userId, String menuId);

 
    
    
}
