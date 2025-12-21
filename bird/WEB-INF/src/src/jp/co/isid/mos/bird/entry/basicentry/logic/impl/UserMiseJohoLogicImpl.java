package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.basicentry.logic.UserMiseJohoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ユーザー保有店情報取得 ロジック
 * @author xnkusama
 */
public class UserMiseJohoLogicImpl implements UserMiseJohoLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BEN002L08";

    /*【DAO】*/
    private MstMiseDao uiEntryMstBasicEntryDao;
    
    /**
     * ユーザーの保有店舗一覧の取得
     * @param String companyCd 会社コード
     * @param String onerCd オーナーコード
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String onerCd, String sysDt) throws ApplicationException {
        return getMstMiseBasicEntryDao().selectMiseList(companyCd, onerCd, sysDt);
    }

    public MstMiseDao getMstMiseBasicEntryDao() {
        return uiEntryMstBasicEntryDao;
    }

    public void setMstMiseBasicEntryDao(MstMiseDao mstMiseBasicEntryDao) {
        this.uiEntryMstBasicEntryDao = mstMiseBasicEntryDao;
    }
    
}
