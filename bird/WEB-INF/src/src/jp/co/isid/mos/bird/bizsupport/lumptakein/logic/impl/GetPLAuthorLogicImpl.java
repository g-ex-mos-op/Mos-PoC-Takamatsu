/*
 * 作成日: 2006/03/17
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLAuthorInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLAuthorLogic;

/**
 * P/L作成者情報取得ロジック
 * 
 * @author xyuchida
 */
public class GetPLAuthorLogicImpl implements GetPLAuthorLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L05";

    /**
     * P/L作成者情報DAO
     */
    private TrnPLAuthorInfoDao trnPLAuthorInfoDao;

    /**
     * P/L作成者情報DAOを取得します。
     * @return P/L作成者情報DAO
     */
    public TrnPLAuthorInfoDao getTrnPLAuthorInfoDao() {
        return trnPLAuthorInfoDao;
    }

    /**
     * P/L作成者情報DAOを設定します。
     * @param trnPLAuthorInfoDao P/L作成者情報DAO
     */
    public void setTrnPLAuthorInfoDao(TrnPLAuthorInfoDao trnPLAuthorInfoDao) {
        this.trnPLAuthorInfoDao = trnPLAuthorInfoDao;
    }

    /**
     * P/L作成者情報取得
     * @param　onerCd オーナーコード
     * @return P/L作成者情報
     */
    public TrnPLAuthorInfo execute(String onerCd) {
        return getTrnPLAuthorInfoDao().getAuthor(onerCd);
    }
}
