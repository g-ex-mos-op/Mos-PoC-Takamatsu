/*
 * 作成日: 2006/04/04
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.EntryDateDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetTargetMLLogic;
/**
 * PL情報取得ロジック
 * @author xnkusama
 */
public class GetTargetMLLogicImpl implements GetTargetMLLogic {
    
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM008L01";
    
    private EntryDateDao mlrrEntryDateDao;
    
    /**
     * 対象一覧を取得する
     * @param String companyCd
     * @param String sysDt
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd, String sysDt) throws ApplicationException {
        return getMlrrEntryDateDao().getList(companyCd, sysDt);
    }

    public EntryDateDao getMlrrEntryDateDao() {
        return mlrrEntryDateDao;
    }

    public void setMlrrEntryDateDao(EntryDateDao mlrrEntryDateDao) {
        this.mlrrEntryDateDao = mlrrEntryDateDao;
    }    
}