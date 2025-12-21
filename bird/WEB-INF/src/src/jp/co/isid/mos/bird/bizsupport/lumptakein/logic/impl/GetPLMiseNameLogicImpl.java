/*
 * 作成日: 2006/03/24
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLMiseNameLogic;

/**
 * P/Lデータ店名称取得ロジック
 * 
 * @author xyuchida
 */
public class GetPLMiseNameLogicImpl implements GetPLMiseNameLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L06";

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
     * P/Lデータ店名称取得
     * @param plType P/Lの種類
     * @param miseCd 店コード(本社P/Lの場合オーナーコード)
     * @return 店名称
     */
    public String execute(String plType, String miseCd) {
        String miseNameKj = null;
        if (plType.equals("0")) {
            // 本社P/L
            miseNameKj = getTrnPLInfoDao().getOwnerName(miseCd);
        } else if (plType.equals("1")) {
            // 店舗P/L
            miseNameKj = getTrnPLInfoDao().getMiseName(miseCd);
        }
        return miseNameKj;
    }
}
