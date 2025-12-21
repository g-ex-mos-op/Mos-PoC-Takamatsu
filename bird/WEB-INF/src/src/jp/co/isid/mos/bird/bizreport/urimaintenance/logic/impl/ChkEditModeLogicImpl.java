package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.MstBatchStateDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.MstMatchStateInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.ChkEditModeLogic;


/**
 * 編集モードチェックロジック
 * @author Aspac
 *
 */
public class ChkEditModeLogicImpl implements ChkEditModeLogic {
    
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L06";
    
    /**
     * 売上在高日次修正情報Dao
     */
    private MstBatchStateDao mstBatchStateDao;
    
    
    /**
     * 編集が可能かチェックを行う
     * @param listUri
     * @return true:編集モード false:参照モード
     */
    public boolean execute(List listUri) {
        
        boolean editFlg = true;
        
        //バッチステータス取得
        MstMatchStateInfo stateInfo = getMstBatchStateDao().getState(
                UriMaintenanceConstants.BATCH_PGM_ID,
                UriMaintenanceConstants.BATCH_PGM_KBN);
        
        String stateKbn = stateInfo.getStatKbn();
        
        if(stateKbn.equals(UriMaintenanceConstants.BATCH_STATE_KBN_REF)) {
            editFlg = false;
        }
        else {
        
            for (int i=0; i<listUri.size()-1; i++) {
                UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(i);
                if(!entity.isInsertFlg() && entity.getUpFlg()!=null ) {
                    if(entity.getUpFlg().equals(UriMaintenanceConstants.UP_FLG_REF)) {
                        editFlg = false;
                        break;
                    }
                }
            }
        }
        return editFlg;
    }
    
    
    public MstBatchStateDao getMstBatchStateDao() {
        return mstBatchStateDao;
    }
    public void setMstBatchStateDao(MstBatchStateDao mstBatchStateDao) {
        this.mstBatchStateDao = mstBatchStateDao;
    }
    
    
}
