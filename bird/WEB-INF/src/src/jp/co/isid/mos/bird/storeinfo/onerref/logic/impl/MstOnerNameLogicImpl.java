package jp.co.isid.mos.bird.storeinfo.onerref.logic.impl;


import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

import jp.co.isid.mos.bird.storeinfo.onerref.dao.MstOnerDao;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.MstOnerNameLogic;

/**
 * オーナー統合マスタの検索ロジック
 * @author xayumi
 */
public class MstOnerNameLogicImpl implements MstOnerNameLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BSI002L05";

    /*【DAO】*/
    private MstOnerDao mstOnerDao;
    
    /**
     * 条件画面プルダウン用、店リストを取得する。
     * @param String userType ユーザータイプ
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String companyCd, String onerCd) throws ApplicationException {
        return getMstOnerDao().selectOnerName(companyCd, onerCd);
    }
    
    /**
     * グループ統合オーナーマスタの検索Daoの設定
     * @return mstOnerDao を戻します。
     */
    public MstOnerDao getMstOnerDao() {
        return mstOnerDao;
    }
    /**
     * グループ統合オーナーマスタの検索Daoの設定
     * @param mstOnerDao mstOnerDao を設定。
     */
    public void setMstOnerDao(MstOnerDao mstOnerDao) {
        this.mstOnerDao = mstOnerDao;
    }
}
