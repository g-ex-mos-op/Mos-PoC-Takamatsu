package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.UIUriMainteInfoDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.UriMainteListLogic;



/**
 * 売上情報リスト(比較用)取得ロジック
 * @author Aspac
 *
 */
public class UriMainteListLogicImpl implements UriMainteListLogic {
    
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L07";
    
    
    /**
     * 売上情報Dao
     */
    private UIUriMainteInfoDao uIUriMainteInfoDao;
    
    

    /**
     * 売上情報の取得を行う
     * @return
     */
    public List execute(String companyCd, String miseCd, String targetYM, String sysdate) {
        
        List listUriWork = new ArrayList();
        
        //売上在高日次＋取引を取得する（BT65SADY + BT95NBKI）
        List listUri = getUIUriMainteInfoDao().getUriMainteInfo(companyCd, miseCd, targetYM+"%");
        
        for (Iterator ite = listUri.iterator(); ite.hasNext();) {
            UIUriMainteInfo uriInfo = (UIUriMainteInfo) ite.next();
            UIUriMainteWorkInfo uriWorkInfo = new UIUriMainteWorkInfo();
            UriMaintenanceCommon.copyEntity(uriWorkInfo, uriInfo, sysdate);
            listUriWork.add(uriWorkInfo);
        }
        
        return listUriWork;
    }
    

    public UIUriMainteInfoDao getUIUriMainteInfoDao() {
        return uIUriMainteInfoDao;
    }
    public void setUIUriMainteInfoDao(UIUriMainteInfoDao uriMainteInfoDao) {
        uIUriMainteInfoDao = uriMainteInfoDao;
    }

   
    
}
