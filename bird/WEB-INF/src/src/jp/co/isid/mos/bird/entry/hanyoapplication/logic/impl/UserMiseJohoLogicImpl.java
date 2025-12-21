package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.UserMiseJohoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * ユーザー保有店情報取得 ロジック
 * @author xnkusama
 */
public class UserMiseJohoLogicImpl implements UserMiseJohoLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BEN005L08";

    /*【DAO】*/
    private MstMiseDao mstMiseHanyoAppDao;
    
    /**
     * ユーザーの保有店舗一覧の取得
     * @param String companyCd 会社コード
     * @param String onerCd オーナーコード
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String onerCd) throws ApplicationException {
        return getMstMiseHanyoAppDao().selectMiseList(companyCd, onerCd);
    }

    public MstMiseDao getMstMiseHanyoAppDao() {
        return mstMiseHanyoAppDao;
    }

    public void setMstMiseHanyoAppDao(MstMiseDao mstMiseHanyoAppDao) {
        this.mstMiseHanyoAppDao = mstMiseHanyoAppDao;
    }
    
}
