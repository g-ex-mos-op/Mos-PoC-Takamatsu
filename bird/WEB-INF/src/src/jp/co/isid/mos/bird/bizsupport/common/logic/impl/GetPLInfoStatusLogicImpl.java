package jp.co.isid.mos.bird.bizsupport.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLInfoStatusLogic;

/**
 * P/Lデータ登録状況取得ロジック
 * 
 * @author Aspac
 */
public class GetPLInfoStatusLogicImpl implements GetPLInfoStatusLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS014L01";

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
     * @param closeMiseFlg true:クローズ店を含む false:含まない
     * @return P/Lデータリスト
     */
    public List execute(String plYm, String onerCd, boolean closeMiseFlg) {
        // P/Lデータ登録状況取得
        return getTrnPLInfoDao().getPLInfoStatus(plYm, onerCd, closeMiseFlg);
    }
}
