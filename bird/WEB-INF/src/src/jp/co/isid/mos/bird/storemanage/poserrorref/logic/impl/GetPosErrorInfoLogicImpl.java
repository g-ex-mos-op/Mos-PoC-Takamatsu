/*
 * 作成日:2007/02/08
 */
package jp.co.isid.mos.bird.storemanage.poserrorref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.poserrorref.dao.TrnShushinErrorDao;
import jp.co.isid.mos.bird.storemanage.poserrorref.logic.GetPosErrorInfoLogic;

/**
 * POS集信エラー店舗一覧 集信エラー店舗情報取得ロジック
 * @author xkonishi
 *
 */
public class GetPosErrorInfoLogicImpl implements GetPosErrorInfoLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BSM015L02";
    
    /**
     * 集信エラー店舗情報Dao
     */
    private TrnShushinErrorDao trnShushinErrorDao;
    
    
    /**
     * 集信エラー店舗情報取得
     * @param companyCd  会社コード
     * @param miseCd     店コード
     * @param onerCd     オーナーコード
     * @param userTypeCd ユーザータイプコード
     * @param shuDt      集信日
     * @return 集信エラー店舗情報リスト
     */
    public List execute(String companyCd, String miseCd, String onerCd,
            String userTypeCd, String shuDt) {
        
        //【集信エラー店舗情報Dao.集信エラー店舗情報取得】を実行。
        return trnShushinErrorDao.select(companyCd, miseCd, onerCd, userTypeCd, shuDt);
    }


    public TrnShushinErrorDao getTrnShushinErrorDao() {
        return trnShushinErrorDao;
    }


    public void setTrnShushinErrorDao(TrnShushinErrorDao trnShushinErrorDao) {
        this.trnShushinErrorDao = trnShushinErrorDao;
    }    
}