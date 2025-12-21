package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.UIJigyoDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListJigyoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 事業プルダウンリストを生成する
 * @author Aspac
 */
public class ListJigyoLogicImpl implements ListJigyoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L02";

    
    /**
     * 事業リストDao
     */
    private UIJigyoDao uiTempStoreReplaceJigyoDao;
    
    
    /**
     * 事業リストDaoを取得する
     * @return 事業リストDao
     */
    public UIJigyoDao getUiJigyoDao() {
        return uiTempStoreReplaceJigyoDao;
    }
    
    
    /**
     * 事業リストDaoを設定する
     * @param 事業リストDao
     */
    public void setUiJigyoDao(UIJigyoDao uiTempStoreReplaceJigyoDao) {
        this.uiTempStoreReplaceJigyoDao = uiTempStoreReplaceJigyoDao;
    }


    /**
     * 事業プルダウンリストを生成する
     * 
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException {
        
        //事業リストを取得する
        List listJigyo = getUiJigyoDao().getJigyoList(companyCd);
        
        return listJigyo;
    }
    
    
}
