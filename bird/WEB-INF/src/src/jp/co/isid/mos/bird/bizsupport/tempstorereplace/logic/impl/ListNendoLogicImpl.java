package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.UINendoDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.UINendoList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListNendoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 年度プルダウンリストを生成する
 * @author Aspac
 */
public class ListNendoLogicImpl implements ListNendoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L01";

    
    /**
     * 年度リストDao
     */
    private UINendoDao uiTempStoreReplaceNendoDao;
    /**
     * 年度リストDaoを取得する
     * @return 年度リストDao
     */
    public UINendoDao getUiNendoDao() {
        return uiTempStoreReplaceNendoDao;
    }
    /**
     * 年度リストDaoを設定する
     * @param 年度リストDao
     */
    public void setUiNendoDao(UINendoDao uiTempStoreReplaceNendoDao) {
        this.uiTempStoreReplaceNendoDao = uiTempStoreReplaceNendoDao;
    }


    /**
     * 年度プルダウンリストを生成する
     * 
     * @return List
     * @exception ApplicationException
     */
    public List execute() throws ApplicationException {
        
        //BT44MSJYグループ事業計画予算(月次)から予算対象年月リストを取得する
        List listNengetu = getUiNendoDao().getNendoList();
        
        List listTemp = new ArrayList();
        for (Iterator ite = listNengetu.iterator(); ite.hasNext();) {
            UINendoList uinendo = (UINendoList) ite.next();
            String yosanDt = uinendo.getYosanDt();
            //取得した年月から年度を生成する
            String nendo = DateManager.getCurrentYear(yosanDt);
            if(!listTemp.contains(nendo)){
                listTemp.add(nendo);
            }
        }
        
        //年度プルダウンリスト生成
        List listNendo = new ArrayList();
        for(int i=0; i<listTemp.size(); i++ ) {
            String nendo = (String)listTemp.get(i);
            listNendo.add(new SelectItem(nendo, nendo));
        }
        
        return listNendo;
    }
    
    
}
