package jp.co.isid.mos.bird.storeinfo.miseref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstMiseDao;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.SchMiseListLogic;

/**
 * 店統合マスタの検索ロジック
 * @author xayumi
 */
public class SchMiseListLogicImpl implements SchMiseListLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BSI001L04";

    /*【DAO】*/
    private MstMiseDao mstMiseDao;
    
    /**
     * 条件画面プルダウン用、店リストを取得する。
     * @param String userType ユーザータイプ
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String onerCd) throws ApplicationException {
        return getMstMiseDao().selectMiseList(companyCd, onerCd);
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
