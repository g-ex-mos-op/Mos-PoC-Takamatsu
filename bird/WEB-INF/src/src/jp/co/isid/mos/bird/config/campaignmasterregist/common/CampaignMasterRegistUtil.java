/**
 * 
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UICampMenu;

/**
 * @author xkinu
 *
 */
public class CampaignMasterRegistUtil {
    /**
     * メニュー情報を編集画面用に加工する
     * @param listMenuEntity
     * @return
     */
    public static List makeEditMenuList(List listMenu) {
    	if(listMenu == null) {
    		listMenu = new ArrayList(0);
    	}
        Iterator iteMenu = listMenu.iterator();
        
        // メニュー単位のListを作成
        List listAllMenu = new ArrayList(5);
        for (int i=0; i<5; i++) {
            List listRowMenu = new ArrayList(4);
            for (int r=0; r<4; r++) {
            	if(iteMenu.hasNext()) {
            		listRowMenu.add((UICampMenu) iteMenu.next());
            	}
            	else {
            		listRowMenu.add(new UICampMenu());
            	}
            }
            listAllMenu.add(listRowMenu);
        }
        return listAllMenu;
    }

}
