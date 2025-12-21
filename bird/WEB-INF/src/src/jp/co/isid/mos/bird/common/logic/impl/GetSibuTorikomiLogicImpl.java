/*
 * 作成日: 2007/01/11
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;

/**
 * 支部取込コードによる支部情報の取得
 * @author mwatanabe
 */
public class GetSibuTorikomiLogicImpl implements GetSibuTorikomiLogic {

    /* DAO */
    private MstSibuDao mstSibuDao;
    
    /**
     * 支部取込コードによる支部情報の取得
     * @param companyCd 企業コード
     * @param userId    ユーザID
     * @param limit     制限区分
     * @return 支部情報
     */
    public List execute(String companyCd, String userId, boolean limit){

        List sibuTorikomiList = null;

        sibuTorikomiList = mstSibuDao.getSibuTorikomi(companyCd, userId, limit);
        
        return sibuTorikomiList;
    }

    
    /**
     * @return mstSibuDao を戻します。
     */
    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }
    
    /**
     * @param mstSibuDao mstSibuDao を設定。
     */
    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }
}
