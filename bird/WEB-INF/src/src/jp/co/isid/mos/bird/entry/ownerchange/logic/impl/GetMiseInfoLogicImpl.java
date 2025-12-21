/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.ownerchange.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.entry.ownerchange.dao.MstMiseInfoDao;

/**
 * 店舗情報取得ロジック
 * @author xkonishi
 *
 */
public class GetMiseInfoLogicImpl implements GetMiseInfoLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L06";
    
    /**
     * 店舗情報Dao
     */
    MstMiseInfoDao mstMiseInfoDao;
  
    /**
     * 店舗情報取得ロジック
     * @param 会社コード
     * @param オーナーコード
     * @param システム日付
     * @return 店舗情報
     */    
    public List execute(String companyCd, String onerCd, String sysDate) {

        // Dao【店舗情報．店舗情報検索】を実行
        List miseInfo = mstMiseInfoDao.select(companyCd, onerCd, sysDate);
        
        return miseInfo;
    }

    public MstMiseInfoDao getMstMiseInfoDao() {
        return mstMiseInfoDao;
    }

    public void setMstMiseInfoDao(MstMiseInfoDao mstMiseInfoDao) {
        this.mstMiseInfoDao = mstMiseInfoDao;
    }
}