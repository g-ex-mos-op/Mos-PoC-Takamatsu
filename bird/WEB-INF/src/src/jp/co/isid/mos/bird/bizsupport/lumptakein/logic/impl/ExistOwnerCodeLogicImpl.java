/*
 * 作成日: 2006/04/03
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.ExistOwnerCodeLogic;

/**
 * オーナーコード存在判定ロジック
 * 
 * @author xyuchida
 */
public class ExistOwnerCodeLogicImpl implements ExistOwnerCodeLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L08";

    /**
     * P/LデータDAO
     */
    private TrnPLInfoDao trnPLInfoDao;

    /**
     * P/LデータDAOを取得します。
     * @return P/LデータDAO
     */
    public TrnPLInfoDao getTrnPLInfoDao() {
        return trnPLInfoDao;
    }

    /**
     * P/LデータDAOを設定します。
     * @param trnPLInfoDao P/LデータDAO
     */
    public void setTrnPLInfoDao(TrnPLInfoDao trnPLInfoDao) {
        this.trnPLInfoDao = trnPLInfoDao;
    }

    /**
     * オーナーコード存在判定
     * @param onerCd オーナーコード
     * @return オーナーコード存在有無
     */
    public boolean execute(String onerCd) {
        return getTrnPLInfoDao().getExistOwnerCount(onerCd) > 0;
    }
}
