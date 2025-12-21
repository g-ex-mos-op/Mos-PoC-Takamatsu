package jp.co.isid.mos.bird.bizsupport.budgetregist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetregist.dao.TrnBudgetInfoDao;
import jp.co.isid.mos.bird.bizsupport.budgetregist.dto.BudgetRegistDto;
import jp.co.isid.mos.bird.bizsupport.budgetregist.entity.TrnBudgetInfo;
import jp.co.isid.mos.bird.bizsupport.budgetregist.logic.BudgetRegistLogic;

/**
 * —\ZXVƒƒWƒbƒN
 * 
 * @author Aspac
 */
public class BudgetRegistLogicImpl implements BudgetRegistLogic {

    
    /** ƒƒWƒbƒNID ’è‹` */
    public static final String LOGIC_ID = "BBS022L06";


    /** —\Zî•ñXVDao */
    private TrnBudgetInfoDao trnBudgetInfoDao;
    

    /**
     * —\Zî•ñXVDao‚ğæ“¾‚µ‚Ü‚·B
     * @return
     */
    public TrnBudgetInfoDao getTrnBudgetInfoDao() {
        return trnBudgetInfoDao;
    }

    /**
     * —\Zî•ñXVDao‚ğİ’è‚µ‚Ü‚·B
     * @param trnBudgetInfoDao
     */
    public void setTrnBudgetInfoDao(TrnBudgetInfoDao trnBudgetInfoDao) {
        this.trnBudgetInfoDao = trnBudgetInfoDao;
    }


    /**
     * —\Z“o˜^ƒƒWƒbƒN
     * @param budgetRegistDto —\Z“o˜^DTO
     * @return —\Z“o˜^ƒf[ƒ^
     */
    public void execute(BudgetRegistDto budgetRegistDto) {
        
        List lstMise = budgetRegistDto.getListBudget();
        
        for (Iterator ite = lstMise.iterator(); ite.hasNext();) {
            TrnBudgetInfo budgetInfo = (TrnBudgetInfo) ite.next();
            if(getTrnBudgetInfoDao().updateBudgetInfo(budgetInfo) <= 0 ) {
                getTrnBudgetInfoDao().insert(budgetInfo);
            }
        }
        return;
    }
    
}
