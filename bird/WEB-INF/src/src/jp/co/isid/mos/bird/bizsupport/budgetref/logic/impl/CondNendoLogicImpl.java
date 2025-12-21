/*
 * 作成日: 2006/11/30
 *
 */
package jp.co.isid.mos.bird.bizsupport.budgetref.logic.impl;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.budgetref.dao.TrnBudgetRegistDummyDao;
import jp.co.isid.mos.bird.bizsupport.budgetref.logic.CondNendoLogic;

/**
 * ユーザー所得ロジック
 * 
 * @author inazawa
 */
public class CondNendoLogicImpl implements CondNendoLogic{
    /*Dao[条件画面用年度取得]*/
    private TrnBudgetRegistDummyDao trnBudgetRegistDummyDao;
    /** ロジックID定義 */
    public static final String LOGIC_ID = "BBS023L02";
    
    
    
    public List execute() {
        return getTrnBudgetRegistDummyDao().getNendo();
    }


    /**
     * trnBudgetRegistDummyDaoを取得
     * @return trnBudgetRegistDummyDao
     */
    public TrnBudgetRegistDummyDao getTrnBudgetRegistDummyDao() {
        return trnBudgetRegistDummyDao;
    }

    /**
     * trnBudgetRegistDummyDaoを設定
     * @param trnBudgetRegistDummyDao
     */
    public void setTrnBudgetRegistDummyDao(
            TrnBudgetRegistDummyDao trnBudgetRegistDummyDao) {
        this.trnBudgetRegistDummyDao = trnBudgetRegistDummyDao;
    }




    
}
