package jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.dao.UICampMenuDao;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.GetMenuListLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * キャンペーンメニュー情報の取得
 * @author xnkusama
 *
 */
public class GetMenuListLogicImpl implements GetMenuListLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BCF006L02";
    
    /** DAO */
    private UICampMenuDao campmasterregistUICampMenuDao;
    
    public List execute(String campId, boolean kakoFlg) {
        // パラメータチェック
        validate(campId);
        
        // メニュー情報の取得
        List listMenu = getCampmasterregistUICampMenuDao().getCampaignMenuList(campId, kakoFlg);
        return listMenu;
    }

    
    private boolean isNull(String value) {
        return value == null || value.trim().equals("") ? true : false;
    }
    
    /**
     * パラメータチェック
     * @param campId
     */
    private void validate(String campId) {
        if (isNull(campId)) {
            throw new NotNullException("キャンペーン識別番号");
        }
    }
    
    public UICampMenuDao getCampmasterregistUICampMenuDao() {
        return campmasterregistUICampMenuDao;
    }

    public void setCampmasterregistUICampMenuDao(
            UICampMenuDao campmasterregistUICampMenuDao) {
        this.campmasterregistUICampMenuDao = campmasterregistUICampMenuDao;
    }

}
