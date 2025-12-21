package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.UISibuDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListSibuLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 支部プルダウンリストを生成する
 * @author Aspac
 */
public class ListSibuLogicImpl implements ListSibuLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L03";

    
    /**
     * 支部リストDao
     */
    private UISibuDao uiTempStoreReplaceSibuDao;
    
    
    /**
     * 支部リストDaoを取得する
     * @return 支部リストDao
     */
    public UISibuDao getUiSibuDao() {
        return uiTempStoreReplaceSibuDao;
    }
    
    
    /**
     * 支部リストDaoを設定する
     * @param 支部リストDao
     */
    public void setUiSibuDao(UISibuDao uiTempStoreReplaceSibuDao) {
        this.uiTempStoreReplaceSibuDao = uiTempStoreReplaceSibuDao;
    }


    /**
     * 支部プルダウンリストを生成する
     * 
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException {
        
        //事業リストを取得する
        List listSibu = getUiSibuDao().getSibuList(companyCd);
        
        return listSibu;
    }
    
    
}
