package jp.co.isid.mos.bird.storeinfo.onerref.logic.impl;


import jp.co.isid.mos.bird.framework.exception.ApplicationException;

import jp.co.isid.mos.bird.storeinfo.onerref.dao.MstMiseDao;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstMise;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.MstMiseJohoLogic;

/**
 * 店統合マスタの検索ロジック
 * @author xayumi
 */
public class MstMiseJohoLogicImpl implements MstMiseJohoLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BSI002L04";

    /*【DAO】*/
    private MstMiseDao mstMiseDao;
    
    /**
     * 条件画面プルダウン用、店リストを取得する。
     * @param String userType ユーザータイプ
     * @return List  
     * @exception ApplicationException
     */
    public MstMise execute(String companyCd, String miseCd) throws ApplicationException {
        return getMstMiseDao().selectMiseMst(companyCd, miseCd);
    }
    
    /**
     * @return mstMiseDao を戻します。
     */
    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }
    /**
     * @param mstMiseDao mstMiseDao を設定。
     */
    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
}
