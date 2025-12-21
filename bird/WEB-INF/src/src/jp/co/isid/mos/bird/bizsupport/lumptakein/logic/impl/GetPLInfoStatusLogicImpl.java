/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLInfoStatusLogic;

/**
 * P/Lデータ登録状況取得ロジック
 * 
 * @author xyuchida
 */
public class GetPLInfoStatusLogicImpl implements GetPLInfoStatusLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L09";

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
     * P/Lデータ登録状況取得ロジック
     * @param plType P/Lの種類
     * @param onerCd オーナーコード
     * @return P/Lデータリスト
     */
    public List execute(String plYm, String onerCd) {
        // P/Lデータ登録状況取得
        return getTrnPLInfoDao().getPLInfoStatus(plYm, onerCd);
    }
}
