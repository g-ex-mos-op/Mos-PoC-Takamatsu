/*
 * 作成日: 2007/02/21
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;

/**
 * 店舗による支部情報の取得
 * @author xlee
 */
public class GetSibuHoyuTenpoLogicImpl implements GetSibuHoyuTenpoLogic {

    /* DAO */
    private MstSibuDao mstSibuDao;
    
    /**
     * 店舗が保有している支部情報の取得
     * @param companyCd 企業コード
     * @param userId    ユーザID
     * @param limit     制限区分
     * @return 支部情報
     */
    public List execute(String companyCd, String userId, boolean limit){

        List sibuHoyuTenpoList = null;

        sibuHoyuTenpoList = mstSibuDao.getSibuHoyuTenpo(companyCd, userId, limit);
        
        return sibuHoyuTenpoList;
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
