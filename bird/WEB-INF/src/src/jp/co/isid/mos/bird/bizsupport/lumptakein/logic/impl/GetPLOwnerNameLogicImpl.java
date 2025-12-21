/*
 * 作成日: 2006/04/03
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLOwnerNameLogic;

/**
 * P/Lデータオーナー名称取得ロジック
 * 
 * @author xyuchida
 */
public class GetPLOwnerNameLogicImpl implements GetPLOwnerNameLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L07";

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
     * P/Lデータオーナー名称取得
     * @param onerCd オーナーコード
     * @return オーナー名称
     */
    public String execute(String onerCd) {
        return getTrnPLInfoDao().getOwnerName(onerCd);
    }
}
