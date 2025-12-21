/**
 * 
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.campaignmasterregist.dao.UICampMenuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UICampMenu;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.CreateMenuListLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * @author xkinu
 *
 */
public class CreateMenuListLogicImpl implements CreateMenuListLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L08";
    
    /** DAO */
    private UICampMenuDao campmasterregistUICampMenuDao;
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.campaignmasterregist.logic.CreateMenuListLogic#execute(java.util.List)
	 */
	public List execute(List listsMenu) {
        // パラメータチェック
        validate(listsMenu);
        List targetMenu = new ArrayList(0);
        for(int r=0; r<listsMenu.size(); r++) {
        	List listMenu = (List)listsMenu.get(r);
        	for(int i=0; i<listMenu.size(); i++) {
        		UICampMenu entity = (UICampMenu)listMenu.get(i);
        		if(!CommonUtil.isNull(entity.getMenuCd())) {
        			targetMenu.add(entity.getMenuCd());
        		}
        	}
        }
        // メニュー情報の取得
        List listMenu = getCampmasterregistUICampMenuDao().getMenuList(targetMenu);
        return listMenu;
	}
    /**
     * パラメータチェック
     * @param campId
     */
    private void validate(List listMenu) {
        if (listMenu == null || listMenu.size()==0) {
            throw new NotNullException("メニューリスト");
        }
    }
	/**
	 * @return campmasterregistUICampMenuDao を戻します。
	 */
	public UICampMenuDao getCampmasterregistUICampMenuDao() {
		return campmasterregistUICampMenuDao;
	}
	/**
	 * @param campmasterregistUICampMenuDao 設定する campmasterregistUICampMenuDao。
	 */
	public void setCampmasterregistUICampMenuDao(
			UICampMenuDao campmasterregistUICampMenuDao) {
		this.campmasterregistUICampMenuDao = campmasterregistUICampMenuDao;
	}

}
