package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.dao.TrnTempStoreDao;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreInfo;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.entity.TrnTempStoreStateList;
import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.StateListRegistLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 仮店舗置換え状況を更新する
 * @author Aspac
 */
public class StateListRegistLogicImpl implements StateListRegistLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L08";
    
    
    /**
     * 実店舗確定Dao
     */
    private TrnTempStoreDao trnTempStoreDao;
    
    

    /**
     * 実店舗確定Daoを取得します。
     * @return 実店舗確定Dao
     */
    public TrnTempStoreDao getTrnTempStoreDao() {
        return trnTempStoreDao;
    }
    /**
     * 実店舗確定Daoを設定します。
     * @param 実店舗確定Dao
     */
    public void setTrnTempStoreDao(TrnTempStoreDao trnTempStoreDao) {
        this.trnTempStoreDao = trnTempStoreDao;
    }



    /**
     * 仮店舗置換え状況を更新する
     * 
     * @param List stateList 仮店舗置換え状況リスト
     * @return
     * @exception ApplicationException
     */
    public void execute(List stateList, String companyCd, String nendo, String userId) throws ApplicationException {

        
        for (Iterator ite = stateList.iterator(); ite.hasNext();) {
            
            TrnTempStoreStateList stateRec = (TrnTempStoreStateList) ite.next();
            TrnTempStoreInfo stateInfo = new TrnTempStoreInfo();
            
//            stateInfo.setCompanyCd(companyCd);//会社コード
//            stateInfo.setKariCd(stateRec.getKariCd());//仮店番
//            stateInfo.setNendo(nendo);//年度
//            
//            if(isNull(stateRec.getKakuteiDt())) {
//                getTrnTempStoreDao().deleteTempStoreInfo(stateInfo);
//            }
//            else {
//                if(!stateRec.getSetFlg().equals("1")) {
//                    
//                    stateInfo.setMiseCd(stateRec.getMiseCd());
//                    stateInfo.setKakuteiDt(stateRec.getKakuteiDt());
//                    stateInfo.setKariCd(stateRec.getKariCd());
//                    stateInfo.setSetFlg("0");
//                    stateInfo.setLastUser(userId);
//                    stateInfo.setLastPgm("BBS025");
//                    stateInfo.setLastTmsp(DateManager.getCurrentTimestamp());
//                    
//                    if(getTrnTempStoreDao().updateTempStoreInfo(stateInfo) <= 0) {
//                        stateInfo.setFirstUser(userId);
//                        stateInfo.setFirstPgm("BBS025");
//                        stateInfo.setFirstTmsp(DateManager.getCurrentTimestamp());                        
//                        getTrnTempStoreDao().insert(stateInfo);
//                    }
//                }
//            }
            
            //Delete-Insert
            if(!stateRec.getSetFlg().equals("1")) {
                stateInfo.setCompanyCd(companyCd);
                stateInfo.setKariCd(stateRec.getKariCd());
                stateInfo.setNendo(nendo);
                getTrnTempStoreDao().deleteTempStoreInfo(stateInfo);
                if(!isNull(stateRec.getKakuteiDt())){
                    stateInfo.setMiseCd(stateRec.getMiseCd());
                    stateInfo.setKakuteiDt(stateRec.getKakuteiDt());
                    stateInfo.setSetFlg("0");
                    stateInfo.setLastUser(userId);
                    stateInfo.setLastPgm("BBS025");
                    stateInfo.setLastTmsp(DateManager.getCurrentTimestamp());
                    stateInfo.setFirstUser(userId);
                    stateInfo.setFirstPgm("BBS025");
                    stateInfo.setFirstTmsp(DateManager.getCurrentTimestamp());
                    
                    getTrnTempStoreDao().insert(stateInfo);
                }
            }
        }
    }
    
    
    /**
     * Nullチェック
     */
    private boolean isNull(String str){
        if(str==null || str.equals("")) return true;
        return false;
    }
    
}
