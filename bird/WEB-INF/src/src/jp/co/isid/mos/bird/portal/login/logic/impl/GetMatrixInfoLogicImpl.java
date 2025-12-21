package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.portal.login.dao.UIUserMatrixInfoDao;
import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;
import jp.co.isid.mos.bird.portal.login.logic.GetMatrixInfoLogic;


/**
 * @author xnkusama
 */
public class GetMatrixInfoLogicImpl implements GetMatrixInfoLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BPO000L03";
    
    private UIUserMatrixInfoDao uiUserMatrixInfoDao;
    
    /* マトリクス情報取得処理 */
    public UIUserMatrixInfo getMatrixInfoLogic(final LoginDto dto) {
        UIUserMatrixInfo retEntity = null;
        
        List list = getUiUserMatrixInfoDao().getUserMatrixInfo(dto.getUserId());
        if (list != null && list.size() > 0) {
            retEntity = (UIUserMatrixInfo) list.get(0);
        }
        
        return retEntity;
    }

    public UIUserMatrixInfoDao getUiUserMatrixInfoDao() {
        return uiUserMatrixInfoDao;
    }

    public void setUiUserMatrixInfoDao(UIUserMatrixInfoDao uiUserMatrixInfoDao) {
        this.uiUserMatrixInfoDao = uiUserMatrixInfoDao;
    }
}
